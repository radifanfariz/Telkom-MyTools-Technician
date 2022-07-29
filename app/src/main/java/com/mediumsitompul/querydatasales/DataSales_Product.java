package com.mediumsitompul.querydatasales;

public class DataSales_Product {
    private int idx;
    private String cust_name;
    private String cust_addr;
    private String packet_indihome;
    private String google_addr;
    private String inst_addr;
    private String hp;
    private String url;
    private String lat;
    private String lng;


    public DataSales_Product(int idx, String cust_name, String packet_indihome, String inst_addr, String google_addr, String hp, String url,String lat,String lng) {

        this.idx = idx;
        this.cust_name = cust_name;
        this.cust_addr = cust_addr;
        this.google_addr = google_addr;
        this.inst_addr = inst_addr;
        this.hp = hp;
        this.packet_indihome = packet_indihome;
        this.url = url;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return idx;
    }
    public String getCust_name() {
        return cust_name;
    }
    public String getCust_addr() {
        return cust_addr;
    }
    public String getGoogle_addr() {
        return google_addr;
    }
    public String getInst_addr() {
        return inst_addr;
    }
    public String getHp() { return hp; }
    public String getPacket_indihome() { return packet_indihome; }
    public String getUrl() {
        return url;
    }
    public String getLat(){return lat;}
    public String getLng(){return lng;}


}
