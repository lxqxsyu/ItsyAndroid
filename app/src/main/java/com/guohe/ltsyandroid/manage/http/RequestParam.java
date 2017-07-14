package com.guohe.ltsyandroid.manage.http;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shuihan on 2016/12/6.
 * 封装请求参数
 */

public class RequestParam {

    private static final String ANDROID_OS = "android";
    private Map<String, Object> mParams;

    private RequestParam(Builder builder) {
        mParams = builder.params;
    }

    public Map<String, Object> getParams() {
        //JSONObject jsonMap = new JSONObject(mParams);
        //mParams.clear();
        //params.put("json", Base64.encodeToString(new Des("mogumafia").encrypt(jsonMap.toString().getBytes("utf-8")), 0));
        //mParams.put("json", jsonMap.toString());
        return mParams;
    }

    public static class Builder {
        private Map<String, Object> params;

        public Builder() {
            params = new HashMap<>();
        }

        public Builder setParams(Map<String, Object> params) {
            params.putAll(params);
            return this;
        }

        public Builder setParam(String key, String value) {
            if(key == null || value == null) return this;
            params.put(key, value);
            return this;
        }

        public Builder setParam(String key, Number value){
            if(key == null || value == null) return this;
            params.put(key, value);
            return this;
        }

        public Builder setParam(String key, String[] value) {
            if(key == null || value == null) return this;
            setParam(key, Arrays.asList(value));
            return this;
        }

        public Builder setParam(String key, List<String> value) {
            if(key == null || value == null) return this;
            setParam(key, new HashSet<String>(value));
            return this;
        }

        public Builder setParam(String key, Set<String> value) {
            if(key == null || value == null) return this;
            StringBuilder valbuilder = new StringBuilder();
            Iterator<String> iterator = value.iterator();
            while(iterator.hasNext()){
                valbuilder.append(iterator.next());
                if(iterator.hasNext()) {
                    valbuilder.append(",");
                }
            }
            params.put(key, valbuilder.toString());
            return this;
        }

        public RequestParam builder() {
            //一些公共的配置
          /*  params.put("userid", UserConfigManage.getInstance().getUserId());
            params.put("versionCode", GlobalConfigManage.getInstance().getVersionCode());
            params.put("platform", ANDROID_OS);
            params.put("pushChannelId", UserConfigManage.getInstance().getBaiduChannelId());
            params.put("udid", Installation.getID(CustomApplication.getContext()));
            params.put("bundleid", PackageUtils.getAppPackageName(CustomApplication.getContext()));
            params.put("channel", PackageUtils.getMetaDataApplication(CustomApplication.getContext(), "UMENG_CHANNEL"));
            params.put("appTocket", UserConfigManage.getInstance().getAppTocket());*/
            //params.put("token", getToken());
            //params.put("uuid", )
            return new RequestParam(this);
        }
    }
}
