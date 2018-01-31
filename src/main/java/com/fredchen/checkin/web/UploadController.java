package com.fredchen.checkin.web;

import com.fredchen.checkin.common.util.excel.ExcelUtil;
import com.fredchen.checkin.common.util.excel.bean.DataCell;
import com.fredchen.checkin.common.util.excel.bean.DataRecord;
import com.fredchen.checkin.common.util.excel.bean.DataRow;
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
    public String uploadCSV(@RequestParam("file") @NonNull MultipartFile file, @RequestParam("importDepId") Integer importDepId) throws Exception {

        DataRecord dataRecord = ExcelUtil.importAsCsv(file.getInputStream(), "GB2312");
        Optional<List<DataRow>> dataRows = Optional.ofNullable(dataRecord.getDataRows());
        log.info("dataRows:" + dataRows.get().size());

        //val dep = departmentService.findById(importDepId);

        List<DataRow> dataRowList = dataRows.get();
        for (DataRow dataRow : dataRowList) {
            int size = dataRow.getDataCells().size();
            String name = getData(dataRow, size, 0);
            String sex = getData(dataRow, size, 1).equals("男") ? "1" : "0";
            String depName = getData(dataRow, size, 2);
            String classRoom = getData(dataRow, size, 3);
            String configName = getData(dataRow, size, 4);
            String absence = getData(dataRow, size, 5);
            String remark = getData(dataRow, size, 7);

            val dep = departmentService.findByName(depName);
            val classroom = classRoomService.findByName(classRoom);

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

        return "redirect:/staff/list?depId=" + importDepId;
    }

    private String getData(DataRow dataRow, int size, int i) {
        if (size < i) {
            return "";
        }
        DataCell dataCell = dataRow.getDataCells().get(i);
        return dataCell.getDataString();
    }
}
