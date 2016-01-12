package org.ptb.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class Controller implements Serializable {
	private static final long serialVersionUID = -8923575065894044672L;

	@Id
	private  String id;
	
	private final Map<String, Sensor> sensorMap;
	
	public Controller() {
		sensorMap = new HashMap<String,Sensor>();	
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
	 * @return the sensorList
	 */
	public Map<String, Sensor> getSensorMap() {
		return sensorMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sensorMap == null) ? 0 : sensorMap.hashCode());
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
		Controller other = (Controller) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sensorMap == null) {
			if (other.sensorMap != null)
				return false;
		} else if (!sensorMap.equals(other.sensorMap))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Controller [id=" + id + ", sensorMap=" + sensorMap + "]";
	}
}
