package com.mediumsitompul.querydatasales.adapter;

public class LogProvisioning_getset {
    private String id;
    private String timestamp;
    private String sf_name;
    private String sf_num;
    private String status;

    public LogProvisioning_getset(String id, String timestamp, String sf_name, String sf_num, String status) {
        this.id = id;
        this.timestamp = timestamp;
        this.sf_name = sf_name;
        this.sf_num = sf_num;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSf_name() {
        return sf_name;
    }

    public void setSf_name(String sf_name) {
        this.sf_name = sf_name;
    }

    public String getSf_num() {
        return sf_num;
    }

    public void setSf_num(String sf_num) {
        this.sf_num = sf_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
