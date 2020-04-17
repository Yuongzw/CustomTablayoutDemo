package com.example.customtablayoutdemo.model;

import com.bin.david.form.annotation.SmartColumn;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/15
 * desc   :
 */
public class StationDetailLTE extends MyBaseStationDetail {
    private String siteName;
    private String eNodeBID;
    private String sectorID;
    private double longitude;
    private double latitude;
    private String pci;
    private String earfcn;
    private String azimuth;
    private String cellName;

    private boolean isCheck;
    private String tac;
    private String cellId;

    public StationDetailLTE(String siteName, String eNodeBID, String sectorID, double longitude, double latitude, String pci, String earfcn, String azimuth, String cellName, boolean isCheck, String tac, String cellId) {
        this.siteName = siteName;
        this.eNodeBID = eNodeBID;
        this.sectorID = sectorID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pci = pci;
        this.earfcn = earfcn;
        this.azimuth = azimuth;
        this.cellName = cellName;
        this.isCheck = isCheck;
        this.tac = tac;
        this.cellId = cellId;
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

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String geteNodeBID() {
        return eNodeBID;
    }

    public void seteNodeBID(String eNodeBID) {
        this.eNodeBID = eNodeBID;
    }

    public String getSectorID() {
        return sectorID;
    }

    public void setSectorID(String sectorID) {
        this.sectorID = sectorID;
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

    public String getEarfcn() {
        return earfcn;
    }

    public void setEarfcn(String earfcn) {
        this.earfcn = earfcn;
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
}
