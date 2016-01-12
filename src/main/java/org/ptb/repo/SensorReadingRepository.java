package org.ptb.repo;

import org.ptb.model.SensorReading;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorReadingRepository extends MongoRepository<SensorReading, String> {
	public SensorReading findById(String id);
}
