package com.mediumsitompul.querydatasales;

import android.graphics.Picture;

public class DataProvisioning_Product {
    private int idx;
    private String witel;
    private String cust_name;
    private String cust_addr;
    private String google_addr;
    private String inst_addr;
    private String stp_name;
    private String latitude_odp;
    private String longitude_odp;
    private String latitude_inst_addr;
    private String longitude_inst_addr;
    private String hp;
    private String email;
    private String packet_indihome;
    private String url;
    private String url2;



    public DataProvisioning_Product(int idx,
                                    String witel,
                                    String cust_name,
                                    String cust_addr,
                                    String google_addr,
                                    String inst_addr,
                                    String stp_name,
                                    String latitude_odp,
                                    String longitude_odp,
                                    String latitude_inst_addr,
                                    String longitude_inst_addr,
                                    String hp,
                                    String email,
                                    String packet_indihome,
                                    String url) {

        this.idx = idx;
        this.witel = witel;
        this.cust_name = cust_name;
        this.cust_addr = cust_addr;
        this.google_addr = google_addr;
        this.inst_addr = inst_addr;
        this.stp_name = stp_name;
        this.latitude_odp = latitude_odp;
        this.longitude_odp = longitude_odp;
        this.latitude_inst_addr = latitude_inst_addr;
        this.longitude_inst_addr = longitude_inst_addr;
        this.hp = hp;
        this.email= email;
        this.packet_indihome = packet_indihome;
        this.url = url;
        //this.url2 = url2;

    }

    public int getId() {
        return idx;
    }
    public String getWitel() {
        return witel;
    }
    public String getCust_name() {
        return cust_name;
    }
    public String getCust_addr() {
        return cust_addr;
    }
    public String getGoogle_addr() { return google_addr; }
    public String getInst_addr() { return inst_addr; }
    public String getStp_name() { return stp_name; }
    public String getLatitude_odp() { return latitude_odp; }
    public String getLongitude_odp() { return longitude_odp; }
    public String getLatitude_inst_addr() { return latitude_inst_addr; }
    public String getLongitude_inst_addr() { return longitude_inst_addr; }
    public String getHp() { return hp; }
    public String getEmail() { return email; }
    public String getPacket_indihome() { return packet_indihome; }
    public String getUrl() { return url; }
    //public String getUrl2() { return url2; }

}
