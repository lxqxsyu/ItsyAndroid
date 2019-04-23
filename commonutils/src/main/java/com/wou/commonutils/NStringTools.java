package com.wou.commonutils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NStringTools {
    // 替换所有空格
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 不包�?"" 的集�?
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<String> nSplit(String str, String regex) {
        int index = 0;
        int indexNew = -1;
        List<String> list = new ArrayList<String>();
        try {
            while ((indexNew = str.indexOf(regex, index)) != -1) {
                if (!"".equals(str.substring(index, indexNew))) {
                    list.add(str.substring(index, indexNew));
                }
                index = indexNew + regex.length();
            }
            if (!"".equals(str.substring(index, str.length()))) {
                list.add(str.substring(index, str.length()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static List<String> splitSpace(String str, String regex) {

        int index = 0;
        int indexNew = -1;
        List<String> list = new ArrayList<String>();
        if (str == null || str.equals("")) {
            return list;
        }
        while ((indexNew = str.indexOf(regex, index)) != -1) {
            list.add(str.substring(index, indexNew));
            index = indexNew + regex.length();
        }
        if (!"".equals(str.substring(index, str.length()))) {
            list.add(str.substring(index, str.length()));
        }
        if (list != null && list.size() > 0) {
            list.remove(0);
        }
        return list;
    }

    public static String listToString(List<String> list, String separator) {
        String str = "";
        if (separator == null) {
            separator = "";
        }
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i) + separator;
        }
        if (str.length() > separator.length()) {
            return str.substring(0, str.length() - separator.length());
        }
        return str;
    }

    public static Boolean isNullorEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static String intToString(int i) {
        String result = "";
        switch (i) {
            case 0:
                result = "A";
                break;
            case 1:
                result = "B";
                break;
            case 2:
                result = "C";
                break;
            case 3:
                result = "D";
                break;
            case 4:
                result = "E";
                break;
            case 5:
                result = "F";
                break;
            case 6:
                result = "G";
                break;

            default:
                result = "*";
                break;
        }
        return result;
    }

    public static String getResultString(String content, String defaultStr) {
        if (content == null || content.equals("")) {
            return defaultStr;
        }
        return content.toString();
    }

    public static String getResultIntToString(Integer content, String defaultStr) {
        if (content == null || content.equals("")) {
            return defaultStr;
        }
        return content.toString();
    }

    public static List<Integer> getImageAttrFromUrl(String url) {
        try {
            List<Integer> list = new ArrayList<Integer>();
            String temp = url.substring(url.lastIndexOf("size"), url.lastIndexOf("."));
            String[] result = temp.split("_");
            // if (result.length == 4) {
            list.add(Integer.valueOf(result[1]));
            list.add(Integer.valueOf(result[2]));
            return list;
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
