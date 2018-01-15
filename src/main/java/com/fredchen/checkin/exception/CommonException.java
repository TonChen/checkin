package com.fredchen.checkin.exception;

import com.fredchen.checkin.common.constant.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 16:41
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonException extends RuntimeException {
    private CodeEnum code;
    private String errorMessage;
}

