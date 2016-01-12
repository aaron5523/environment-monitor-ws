package org.ptb.model;

import org.ptb.Common;
import org.springframework.data.annotation.Id;

public class ReadingValue {

	@Id
	private final String id;
	
	private long lastUpdate;
	
	private String readingVal;
	
	public ReadingValue() {
		this.id = getGeneratedId();
		updateRecordTimestamp();
	}
	
	public ReadingValue(String id, String readingVal) {
		this.id = id;
		setReadingVal( readingVal );
	}
	
	public ReadingValue(String readingVal ) {
		this.id = getGeneratedId();
		setReadingVal( readingVal );
	}

	private String getGeneratedId() {
		return Common.getSensorReadingCalcId();
	}

	/**
	 * @return the readingVal
	 */
	public String getReadingVal() {
		return readingVal;
	}

	/**
	 * @param readingVal the readingVal to set
	 */
	public void setReadingVal(String readingVal) {
		this.readingVal = readingVal;
		updateRecordTimestamp();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the lastUpdate
	 */
	public long getLastUpdate() {
		return lastUpdate;
	}
	
	private void updateRecordTimestamp() {
		this.lastUpdate = System.currentTimeMillis();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (lastUpdate ^ (lastUpdate >>> 32));
		result = prime * result + ((readingVal == null) ? 0 : readingVal.hashCode());
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
		ReadingValue other = (ReadingValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdate != other.lastUpdate)
			return false;
		if (readingVal == null) {
			if (other.readingVal != null)
				return false;
		} else if (!readingVal.equals(other.readingVal))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReadingValue [id=" + id + ", lastUpdate=" + lastUpdate + ", readingVal=" + readingVal + "]";
	}

	
}
