package com.hai.minh.ecommerce.constant;

import com.google.common.collect.Lists;

import java.util.List;

public class Constants {
    public static final String ERROR = "error";
    public static final String ERR_CALL_REST_CLIENT_ERROR = "error.callRestClient";
    public static final String ERR_INTERNAL_SERVER_ERROR = "error.internalServer";
    public static final String ERR_UNAUTHORIZED = "error.unauthorized";
    public static final String ERR_INVALID_ARGUMENT = "error.invalidArgument";
    public static final String ERR_INVALID_FILE = "error.invalidFile";

    // Date time format
    public static final String DEFAULT_TIMEZONE = "Asia/Ho_Chi_Minh";
    public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_FORMAT_FILENAME_SAFE = "yyyy-MM-dd-hh-mm-ss";

    // File
    public static final List<String> CSV_EXTENSIONS = Lists.newArrayList(".csv");


}
