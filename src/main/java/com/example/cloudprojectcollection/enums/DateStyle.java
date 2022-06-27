package com.example.cloudprojectcollection.enums;

/**
 * @author: wwx
 * @date: 2022-06-27
 * @desc:
 **/
public enum DateStyle {
    YYYY_MM("yyyy-MM", false),
    YYYY_MM_DD("yyyy-MM-dd", false),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", false),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", false),
    YYYYMMDDHH("yyyyMMddHH", false),
    YYYYMMDDHHMM("yyyyMMddHHmm", false),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss", false),
    YYYYMMDD("yyyyMMdd", false),
    YYYYMM("yyyyMM", false),
    YYYY_MM_EN("yyyy/MM", false),
    YYYY_MM_DD_EN("yyyy/MM/dd", false),
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm", false),
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss", false),
    YYYYMMDDTHHMMSS("yyyy-MM-dd'T'HH:mm:ss", false),

    YYYY_MM_CN("yyyy年MM月", false),
    YYYY_MM_DD_CN("yyyy年MM月dd日", false),
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm", false),
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss", false),
    YYYY_MM_DD_00_00_00_00("yyyy年MM月dd日 00:00:00", false),
    YYYY_MM_DD_23_59_59_59("yyyy年MM月dd日 23:59:59", false),
    YYYYMMDDHHMMUTC("yyyy-MM-dd'T'HH:mm:ss'Z'", false),
    YYYY_MM_DD_SS_00_00_00_00("yyyy-MM-dd 00:00:00", false),
    YYYY_MM_DD_SS_23_59_59_59("yyyy-MM-dd 23:59:59", false),

    HH_MM("HH:mm", true),
    HH_MM_SS("HH:mm:ss", true),
    HHMMSS("HHmmss", true),
    MM_DD("MM-dd", true),
    MM_DD_HH_MM("MM-dd HH:mm", true),
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss", true),

    MM_DD_EN("MM/dd", true),
    MM_DD_HH_MM_EN("MM/dd HH:mm", true),
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss", true),

    MM_DD_CN("MM月dd日", true),
    MM_DD_HH_MM_CN("MM月dd日 HH:mm", true),

    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss", true);

    private String value;

    private boolean isShowOnly;

    DateStyle(String value, boolean isShowOnly) {
        this.value = value;
        this.isShowOnly = isShowOnly;
    }

    public String getValue() {
        return value;
    }

    public boolean isShowOnly() {
        return isShowOnly;
    }
}
