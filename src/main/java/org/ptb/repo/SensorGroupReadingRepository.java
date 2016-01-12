package org.ptb.repo;

import org.ptb.model.ControllerReading;
import org.ptb.model.SensorReading;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorGroupReadingRepository extends MongoRepository<ControllerReading, String> {
	public ControllerReading findById(String id);
}
