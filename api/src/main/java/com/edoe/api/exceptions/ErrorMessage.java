package com.edoe.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int status;
    private String type;
    private String title;
    private String detail;
}
