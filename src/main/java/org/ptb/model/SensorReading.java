package org.ptb.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.ptb.Common;
import org.springframework.data.annotation.Id;

public class SensorReading implements Serializable {
	private static final long serialVersionUID = 5402574108179494962L;
	
	@Id
	private  String id;
	
	private  String sensorId;
	private Map<Integer, Map<Integer, ReadingValue > > readings;

	public SensorReading() {
		initReadings();
	}
	
	private void initReadings() {
		this.readings = new TreeMap<Integer, Map<Integer, ReadingValue > >();
	}
	
	public SensorReading(final String sensorId) {
		this.sensorId = sensorId;
		this.id = Common.getCalcId(sensorId, new DateTime(System.currentTimeMillis()));
		initReadings();	
	}


	public String getSensorId() {
		return sensorId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the readings
	 */
	public Map< Integer, Map<Integer, ReadingValue> > getReadings() {
		return readings;
	}

	public void addValue(String rawReadingVal) {
		int curHour = Common.getHour(new DateTime() );
		
		if (! this.getReadings().containsKey(curHour) ) {
			this.getReadings().put(curHour, new TreeMap<Integer, ReadingValue>() );
		}
		
		int curMinute = Common.getMinute(new DateTime() );
		ReadingValue readingVal = new ReadingValue(rawReadingVal);
		this.getReadings().get(curHour).put(curMinute, readingVal);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SensorReading [id=" + id  + ", readings=" + readings + "]";
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((readings == null) ? 0 : readings.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorReading other = (SensorReading) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (readings == null) {
			if (other.readings != null)
				return false;
		} else if (!readings.equals(other.readings))
			return false;
		return true;
	}

	/**
	 * @param parent the parent to set
	 */
//	public void setParent(Sensor parent) {
//		this.parent = parent;
//	}

}
