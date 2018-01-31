package com.fredchen.checkin.exception;

import com.fredchen.checkin.common.constant.MessageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 16:41
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonException extends RuntimeException {
    private MessageEnum code;
    private String errorMessage;
}

