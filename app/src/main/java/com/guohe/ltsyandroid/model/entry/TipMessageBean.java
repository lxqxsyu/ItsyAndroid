package com.guohe.ltsyandroid.model.entry;

/**
 * Created by shuihan on 2017/7/28.
 */

public class TipMessageBean extends BaseBean {

    private int id;
    private String tipTitle;
    private String tipContent;
    private String tipUrl;

    public TipMessageBean(int id, String tipContent) {
        this.id = id;
        this.tipContent = tipContent;
    }

    public TipMessageBean(int id, String tipTitle, String tipContent, String tipUrl) {
        this.id = id;
        this.tipTitle = tipTitle;
        this.tipContent = tipContent;
        this.tipUrl = tipUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipTitle() {
        return tipTitle;
    }

    public void setTipTitle(String tipTitle) {
        this.tipTitle = tipTitle;
    }

    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public String getTipUrl() {
        return tipUrl;
    }

    public void setTipUrl(String tipUrl) {
        this.tipUrl = tipUrl;
    }
}
