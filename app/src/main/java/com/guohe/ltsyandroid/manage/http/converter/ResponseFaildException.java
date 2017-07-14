package com.guohe.ltsyandroid.manage.http.converter;

import java.io.IOException;

/**
 * Created by shuihan on 2016/12/12.
 */

public class ResponseFaildException extends IOException {

    public ResponseFaildException(String msg){
        super(msg);
    }
}
