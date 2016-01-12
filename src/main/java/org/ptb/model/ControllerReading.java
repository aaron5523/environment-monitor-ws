package org.ptb.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.ptb.Common;
import org.springframework.data.annotation.Id;

public class ControllerReading implements Serializable {
	private static final long serialVersionUID = 3290330485398772991L;
	
	@Id
	private String id;
	
	private final Controller c;
	private final Map<String, SensorReading> sensorReadingMap;
	
	public ControllerReading(final Controller c) {
		this.id = Common.getCalcId(c.getId(), new DateTime() );
		this.c = c;
		this.sensorReadingMap = new TreeMap<String, SensorReading>();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the sg
	 */
	public Controller getController() {
		return c;
	}

	/**
	 * @return the sensorReadingMap
	 */
	public Map<String, SensorReading> getSensorReadingMap() {
		if (null == sensorReadingMap) {
			return new TreeMap<String, SensorReading>();
		}
		return sensorReadingMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sensorReadingMap == null) ? 0 : sensorReadingMap.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
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
		ControllerReading other = (ControllerReading) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sensorReadingMap == null) {
			if (other.sensorReadingMap != null)
				return false;
		} else if (!sensorReadingMap.equals(other.sensorReadingMap))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControllerReading [id=" + id + ", c=" + c + ", sensorReadingMap=" + sensorReadingMap + "]";
	}

}
