package org.ptb.repo;

import org.ptb.model.Controller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorGroupRepository extends MongoRepository<Controller, String> {
	public Controller findById(String id);
}
