package org.ptb.model;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class Sensor implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1338095518891570079L;

	@Id
	private final String id;
	
    private final String description;
    private String controllerId;
	private final long updateTime;

    public Sensor(final String id, final String description) {
    	this.id = id;
    	this.description = description;
		updateTime = System.currentTimeMillis();
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * @return the controllerId
	 */
	public String getControllerId() {
		return controllerId;
	}

	/**
	 * @param c the controllerId to set
	 */
	public void setController(String c) {
		this.controllerId = c;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controllerId == null) ? 0 : controllerId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (updateTime ^ (updateTime >>> 32));
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
		Sensor other = (Sensor) obj;
		if (controllerId == null) {
			if (other.controllerId != null)
				return false;
		} else if (!controllerId.equals(other.controllerId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (updateTime != other.updateTime)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sensor [id=" + id + ", description=" + description + ", controllerId=" + controllerId + ", updateTime="
				+ updateTime + "]";
	}
	
}
