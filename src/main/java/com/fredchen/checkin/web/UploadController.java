package com.fredchen.checkin.web;

import com.fredchen.checkin.common.util.FileUtil;
import com.fredchen.checkin.common.util.excel.ExcelUtil;
import com.fredchen.checkin.common.util.excel.bean.DataCell;
import com.fredchen.checkin.common.util.excel.bean.DataRecord;
import com.fredchen.checkin.common.util.excel.bean.DataRow;
import com.fredchen.checkin.domain.Staff;
import com.fredchen.checkin.service.IDepartmentService;
import com.fredchen.checkin.service.IStaffService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: fredchen
 * @Date: 2018/1/23 10:03
 */

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private IStaffService staffService;
    @Autowired
    private IDepartmentService departmentService;

    //处理文件上传
    @PostMapping(value = "/uploadCSV")
    public String uploadCSV(@RequestParam("file") @NonNull MultipartFile file, @RequestParam("importDepId") Integer importDepId) throws Exception {

        DataRecord dataRecord = ExcelUtil.importAsCsv(file.getInputStream(), "GB2312");
        Optional<List<DataRow>> dataRows = Optional.ofNullable(dataRecord.getDataRows());
        log.info("dataRows:" + dataRows.get().size());

        val dep = departmentService.findById(importDepId);

        List<DataRow> dataRowList = dataRows.get();
        for (int i = 0; i < dataRowList.size(); i++) {
            DataRow dataRow = dataRowList.get(i);
            int size = dataRow.getDataCells().size();
            String name = getData(dataRow, size, 0);
            String sex = getData(dataRow, size, 1).equals("男") ? "1" : "0";
            String phone = getData(dataRow, size, 2);
            String remark = getData(dataRow, size, 3);
            String address = getData(dataRow, size, 4);

            Staff staff = new Staff();
            staff.setDepartment(dep);
            staff.setTelephone(phone);
            staff.setSex(Integer.valueOf(sex));
            staff.setAddress(address);
            staff.setCheckInTime(new Date());
            staff.setAbsence(false);
            staff.setDescription(remark);
            staff.setName(name);
            staff.setIsDel(false);
            staff.setCreateTime(new Date());
            staffService.save(staff);
        }

        return "uploadCSV success";
    }

    private String getData(DataRow dataRow, int size, int i) {
        if (size < i) {
            return "";
        }
        DataCell dataCell = dataRow.getDataCells().get(i);
        String data = dataCell.getDataString();
        return data;
    }
}
