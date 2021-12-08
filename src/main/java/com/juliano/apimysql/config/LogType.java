package com.juliano.apimysql.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LogType {

    INFO("INFO"),
    DEBUG("DEBUG"),
    WARN("warn"),
    ERROR("ERROR");

    @Getter
    private final String value;

}
