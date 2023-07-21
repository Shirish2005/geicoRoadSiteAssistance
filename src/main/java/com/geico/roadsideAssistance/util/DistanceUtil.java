package com.geico.roadsideAssistance.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DistanceUtil {

    //Logger logger = LoggerFactory.getLogger(DistanceUtil.class);

    public static Integer getDistance(String lat1, String lon1, String lat2, String log2) {

        final int R = 6371; // Radious of the earth

        Double lati1 = Double.parseDouble(lat1);
        Double long1 = Double.parseDouble(lon1);

        Double lati2 = Double.parseDouble(lat2);
        Double long2 = Double.parseDouble(log2);

        Double latDistance = toRad(lati2 - lati1);
        Double lonDistance = toRad(long2 - long1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lati1)) * Math.cos(toRad(lati2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return new Integer((int) (R * c));
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
