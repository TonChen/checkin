package com.fredchen.checkin.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 17:11
 */

@Data
@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -6608887773326283314L;

    private Boolean isDel;
    private Date createTime;


}
