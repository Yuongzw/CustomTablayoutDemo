package com.example.customtablayoutdemo.model;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;

import com.example.customtablayoutdemo.util.BaseStationImportFactory;

import java.util.List;
import java.util.Vector;

/**
 * 基站数据解析类
 * 
 * @author Zhengmin Create Time:2010/4/16
 */
public class BaseDataParser {
	private static final String tag = "BaseDataParser";
	/** 从文件加载到的基站列表 */
	private static Vector<BaseStationDetail> dataLoad;
	/** 要显示的基站列表 */
	private static Vector<BaseStationDetail> dataDisplay;

	private double minX = 0;

	private double minY = 0;

	private double maxX = 0;

	private double maxY = 0;
	/** 是否追加基站地图 */
	private static boolean isAppendBase = false;
	// TODO ?????什么值
	private static float xValue = 0;
	private static double ydX = 0;

	private static double ydY = 0;

	private static float yValue = 0;

	private static int viewWidth;

	private static int viewHeight;

	private static BaseStationDetail firstDetail = null;

	private static double height;

	// private static final double MAX_SIZE = 1600 * 1400;

	public BaseDataParser() {

	}

	public static boolean isAppendBase() {
		return isAppendBase;
	}

	public static void setAppendBase(boolean isAppendBase) {
		BaseDataParser.isAppendBase = isAppendBase;
	}

	/** 已经从文件加载的所有基站 */
	public static Vector<BaseStationDetail> getDataLoad() {
		return dataLoad;
	}

	/** 要显示的基站 */
	public static Vector<BaseStationDetail> getDataDisplay() {
		return dataDisplay;
	}

	public static float getXValue() {
		return xValue;
	}

	public static float getYValue() {
		return yValue;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setCoordinate(float x, float y) {
		xValue = x;
		yValue = y;
		// Log.w(tag, "---xvalue="+xvalue+" yvalue="+yvalue);
	}

	public void setViewSize(int width, int height) {
		viewWidth = width;
		viewHeight = height;
	}

	/**
	 * 角度转弧度
	 * 
	 * @param degree
	 * @return
	 */
	static double deg2rad(double degree) {
		return degree / 180 * Math.PI;
	}

	public static BaseStationDetail getFirstDetail() {
		return firstDetail;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public double getYdX() {
		return ydX;
	}

	public double getYdY() {
		return ydY;
	}

	public static double getHeight() {
		return height;
	}

	/**
	 * 解析基站数据文件<BR>
	 * 返回基站数据
	 * 
	 * @param filePath
	 *          文件路径
	 * @param mHandler
	 *          句柄
	 * @return 基站数组
	 */
	public List<BaseStation> parse(String filePath, Handler mHandler) {
		List<BaseStation> baselist = null;
		try {
			baselist = BaseStationImportFactory.getInstance().importFile(filePath);
			for (BaseStation base : baselist) {
				for (BaseStationDetail detail : base.details) {
					dataLoad.add(detail);
					dataDisplay.add(detail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.w("yuongzw", "Walktour_BaseDataParser_Parse_ReadLine_Error:"+ e.toString());
			if (mHandler != null) {
				mHandler.sendEmptyMessage(4001);
			}
		}
		return baselist;

	}

	/**
	 * 获取基站的经纬度范围
	 */
	private void getBaseScope(BaseStation base) {
		if (minX != 0) {
			minX = base.longitude < minX ? base.longitude : minX;
			minY = base.latitude < minY ? base.latitude : minY;
			maxX = base.longitude > maxX ? base.longitude : maxX;
			maxY = base.latitude > maxY ? base.latitude : maxY;
		} else {
			minX = base.longitude;
			minY = base.latitude;
			maxX = base.longitude;
			maxY = base.latitude;
		}
	}

}
