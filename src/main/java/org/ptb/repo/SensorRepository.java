package org.ptb.repo;


import org.ptb.model.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorRepository extends MongoRepository<Sensor, String> {
	public Sensor findById(String id);
}
