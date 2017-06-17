package com.feint.bean;

/**
 * Created by feint on 17/6/16.
 */
public class UrlObj {

    private String url;
    private String description;
    private String patchDate;
    private long hid;


    public long getHid() {
        return hid;
    }

    public void setHid(long hid) {
        this.hid = hid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatchDate() {
        return patchDate;
    }

    public void setPatchDate(String patchDate) {
        this.patchDate = patchDate;
    }
}
