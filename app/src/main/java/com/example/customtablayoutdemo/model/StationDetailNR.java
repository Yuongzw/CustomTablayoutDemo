package com.example.customtablayoutdemo.model;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/15
 * desc   :
 */
public class StationDetailNR extends MyBaseStationDetail{
    private String siteName;
    private String siteId;
    private String eNodeBID;
    private double longitude;
    private double latitude;
    private String pci;
    private String ssbArfcn;
    private String centerArfcn;
    private String azimuth;
    private String cellName;
    private boolean isCheck;
    private String tac;
    private String cellId;

    public StationDetailNR(String siteName, String siteId, String eNodeBID, double longitude, double latitude, String pci, String ssbArfcn, String centerArfcn, String azimuth, String cellName, boolean isCheck, String tac, String cellId) {
        this.siteName = siteName;
        this.siteId = siteId;
        this.eNodeBID = eNodeBID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pci = pci;
        this.ssbArfcn = ssbArfcn;
        this.centerArfcn = centerArfcn;
        this.azimuth = azimuth;
        this.cellName = cellName;
        this.isCheck = isCheck;
        this.tac = tac;
        this.cellId = cellId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String geteNodeBID() {
        return eNodeBID;
    }

    public void seteNodeBID(String eNodeBID) {
        this.eNodeBID = eNodeBID;
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

    public String getPci() {
        return pci;
    }

    public void setPci(String pci) {
        this.pci = pci;
    }

    public String getSsbArfcn() {
        return ssbArfcn;
    }

    public void setSsbArfcn(String ssbArfcn) {
        this.ssbArfcn = ssbArfcn;
    }

    public String getCenterArfcn() {
        return centerArfcn;
    }

    public void setCenterArfcn(String centerArfcn) {
        this.centerArfcn = centerArfcn;
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

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }
}
