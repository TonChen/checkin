package com.fredchen.checkin.web;

import com.fredchen.checkin.common.util.ObjectUtil;
import com.fredchen.checkin.common.util.excel.ExcelUtil;
import com.fredchen.checkin.common.util.excel.bean.DataCell;
import com.fredchen.checkin.common.util.excel.bean.DataRecord;
import com.fredchen.checkin.common.util.excel.bean.DataRow;
import com.fredchen.checkin.domain.ClassRoom;
import com.fredchen.checkin.domain.Department;
import com.fredchen.checkin.domain.Staff;
import com.fredchen.checkin.service.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: fredchen
 * @Date: 2018/1/23 10:03
 */

@Controller
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private IStaffService staffService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IClassRoomService classRoomService;
    @Autowired
    private ISystemConfigService systemConfigService;
    @Autowired
    private ISystemConfigDetailService systemConfigDetailService;

    //处理文件上传
    @RequestMapping(value = "/uploadCSV")
    public String uploadCSV(@RequestParam("file") @NonNull MultipartFile file) throws Exception {

        DataRecord dataRecord = ExcelUtil.importAsCsv(file.getInputStream(), "GBK");
        Optional<List<DataRow>> dataRows = Optional.ofNullable(dataRecord.getDataRows());
        log.info("dataRows:" + dataRows.get().size());

        //val dep = departmentService.findById(importDepId);

        List<DataRow> dataRowList = dataRows.get();
        int count = 0;
        for (DataRow dataRow : dataRowList) {
            count++;
            if(count == 1){
                continue;
            }
            int size = dataRow.getDataCells().size();
            String name = getData(dataRow, size, 0);
            String sex = ObjectUtil.isEmpty(getData(dataRow, size, 1))?"1":(getData(dataRow, size, 1).equals("男") ? "1" : "0");
            String depName = getData(dataRow, size, 2);
            String roomName = getData(dataRow, size, 3);
            String configName = getData(dataRow, size, 4);
            String absence = ObjectUtil.isEmpty(getData(dataRow, size, 5))?"否":getData(dataRow, size, 5);
            String remark = getData(dataRow, size, 7);

            Department dep = departmentService.findByName(depName);
            if(dep == null){
                dep = new Department();
                dep.setIsDel(false);
                dep.setCreateTime(new Date());
                dep.setName(depName);
                departmentService.save(dep);
            }
            ClassRoom classroom = classRoomService.findByName(roomName);
            if(classroom == null){
                classroom = new ClassRoom();
                classroom.setName(roomName);
                classroom.setIsDel(false);
                classroom.setCreateTime(new Date());
                classRoomService.save(classroom);
            }

            Staff staff = new Staff();
            staff.setDepartment(dep);
            staff.setClassRoom(classroom);
            staff.setFudaoType(configName);
            staff.setSex(Integer.valueOf(sex));
            staff.setCheckInTime(new Date());
            staff.setAbsence("是".equals(absence) ? true : false);
            staff.setDescription(remark);
            staff.setName(name);
            staff.setIsDel(false);
            staff.setCreateTime(new Date());
            staffService.save(staff);
        }

        return "redirect:/staff/list?depId=";
    }

    private String getData(DataRow dataRow, int size, int i) {
        if (size < i) {
            return "";
        }
        DataCell dataCell = dataRow.getDataCells().get(i);
        return dataCell.getDataString();
    }
}
