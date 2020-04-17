package com.example.customtablayoutdemo.model;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/15
 * desc   :
 */
public class StationDetailTDCDMA extends MyBaseStationDetail {
    private String siteName;
    private double longitude;
    private double latitude;
    private String azimuth;
    private String cellName;
    private String uarfcn;
    private String cpi;
    private String lac;
    private String cellId;
    private boolean isCheck;

    public StationDetailTDCDMA(String siteName, double longitude, double latitude, String azimuth, String cellName, String uarfcn, String cpi, String lac, String cellId, boolean isCheck) {
        this.siteName = siteName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.azimuth = azimuth;
        this.cellName = cellName;
        this.uarfcn = uarfcn;
        this.cpi = cpi;
        this.lac = lac;
        this.cellId = cellId;
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

    public String getUarfcn() {
        return uarfcn;
    }

    public void setUarfcn(String uarfcn) {
        this.uarfcn = uarfcn;
    }

    public String getCpi() {
        return cpi;
    }

    public void setCpi(String cpi) {
        this.cpi = cpi;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
