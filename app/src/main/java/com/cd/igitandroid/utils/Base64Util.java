package com.cd.igitandroid.utils;

import android.util.Base64;

/**
 * Created by ruandong on 2019/9/27.
 */
public class Base64Util {

    public static String decode(String content) {
//        return new String(Base64.decode(content.getBytes(), Base64.URL_SAFE));
//        return new String(Base64.decode(content.getBytes(), Base64.DEFAULT));
//        return new String(Base64.decode(content.getBytes(), Base64.CRLF));
//        return new String(Base64.decode(content.getBytes(), Base64.NO_PADDING));
//        return new String(Base64.decode(content.getBytes(), Base64.NO_WRAP));
        return new String(Base64.decode(content, Base64.DEFAULT));
    }

    public static String encode(String content) {
        return Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
    }


}
