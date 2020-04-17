package com.example.customtablayoutdemo.model;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/15
 * desc   :
 */
public class StationDetailCDMA extends MyBaseStationDetail {
    private String siteName;
    private double longitude;
    private double latitude;
    private String azimuth;
    private String cellName;
    private String frequency;
    private String pn;
    private String evFreq;
    private String evPn;
    private String bid;
    private String nid;
    private String sid;
    private boolean isCheck;

    public StationDetailCDMA(String siteName, double longitude, double latitude, String azimuth, String cellName, String frequency, String pn, String evFreq, String evPn, String bid, String nid, String sid, boolean isCheck) {
        this.siteName = siteName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.azimuth = azimuth;
        this.cellName = cellName;
        this.frequency = frequency;
        this.pn = pn;
        this.evFreq = evFreq;
        this.evPn = evPn;
        this.bid = bid;
        this.nid = nid;
        this.sid = sid;
        this.isCheck = isCheck;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getEvFreq() {
        return evFreq;
    }

    public void setEvFreq(String evFreq) {
        this.evFreq = evFreq;
    }

    public String getEvPn() {
        return evPn;
    }

    public void setEvPn(String evPn) {
        this.evPn = evPn;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
