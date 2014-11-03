package com.dwk.util;

public class LBS {
  
  /** 地球半径（单位：公里） */
  public final static double EARTH_RADIUS_KM = 6378.137;

  
  /**
   * 根据经纬度计算地球上任意两点间的距离
   * 
   * @param lng1 起点经度
   * @param lat1 起点纬度
   * @param lng2 终点经度
   * @param lat2 终点纬度
   * @return 两点距离（单位：千米）
   */
  public static double getDistanceOfTwoPoint(double lng1, double lat1, double lng2, double lat2) {
    double radLat1 = Math.toRadians(lat1);
    double radLat2 = Math.toRadians(lat2);
    double radLng1 = Math.toRadians(lng1);
    double radLng2 = Math.toRadians(lng2);
    double deltaLat = radLat1 - radLat2;
    double deltaLng = radLng1 - radLng2;
    double distance =
        2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
            * Math.pow(Math.sin(deltaLng / 2), 2)));
    distance = distance * EARTH_RADIUS_KM;
    distance = Math.round(distance * 10000) / 10000;
    return distance;
  }
  
  
  
  /**
   * 根据经纬度计算地球上任意两点间的距离
   * 
   * @param lng1 起点经度
   * @param lat1 起点纬度
   * @param lng2 终点经度
   * @param lat2 终点纬度
   * @return 两点距离（单位：米）
   */
  public static int getDistanceOfTwoPointInMeter(double lng1, double lat1, double lng2, double lat2) {
    double radLat1 = Math.toRadians(lat1);
    double radLat2 = Math.toRadians(lat2);
    double radLng1 = Math.toRadians(lng1);
    double radLng2 = Math.toRadians(lng2);
    double deltaLat = radLat1 - radLat2;
    double deltaLng = radLng1 - radLng2;
    double distance =
        2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
            * Math.pow(Math.sin(deltaLng / 2), 2)));
    distance = distance * EARTH_RADIUS_KM;
    distance = Math.round(distance * 10000) / 10000;
    return (int)(distance*1000);
  }
  
}
