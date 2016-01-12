package org.ptb;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Common {
	
	public static DateTime roundDate(final DateTime dateTime, final int minutes) {
	    if (minutes < 1 || 60 % minutes != 0) {
	        throw new IllegalArgumentException("minutes must be a factor of 60");
	    }

	    final DateTime hour = dateTime.hourOfDay().roundFloorCopy();
	    final long millisSinceHour = new Duration(hour, dateTime).getMillis();
	    final int roundedMinutes = ((int)Math.round(
	        millisSinceHour / 60000.0 / minutes)) * minutes;
	    return hour.plusMinutes(roundedMinutes);
	}
	
	public static int getMinute(final DateTime dateTime) {
	    final int min = dateTime.getMinuteOfHour();
	    return min;
	}
	
	public static int getHour(final DateTime dateTime) {
	    final int hour = dateTime.getHourOfDay();
	    return hour;
	}
	
	public static String getCalcId(final String sensorId, final DateTime updateTime) {
		return sensorId + ":" + updateTime.toString("yyyyMMdd");
	}
	
	public static String getSensorReadingCalcId() {
		DateTime updateTime = new DateTime(System.currentTimeMillis());
		return "" + updateTime.toString("yyyyMMddHHmm");
	}
	
	public static String getCompoundSensorName(final String controllerId, final String sensorId) {
		return controllerId + ":" + sensorId;
	}
}
