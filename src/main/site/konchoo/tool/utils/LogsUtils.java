package site.konchoo.tool.utils;

import site.konchoo.tool.constants.AppConst;

public final class LogsUtils {
    private LogsUtils() {
    }

    public static String printLog(String message) {
        return " [ " + AppConst.sdf.format(System.currentTimeMillis()) + " ] " + message + "\n";
    }
}
