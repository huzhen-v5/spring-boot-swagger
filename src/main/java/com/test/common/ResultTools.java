package com.test.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/14.
 */
public class ResultTools {
     static public ResultModel result (int code, String msg, Map data) {
        ResultModel retmap = new ResultModel();
         retmap.code = code;
         retmap.message = msg;
         retmap.data = data;
         return retmap;
    }
}
