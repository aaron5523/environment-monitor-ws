package org.ptb;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.ptb.model.Sensor;
import org.ptb.model.Controller;
import org.ptb.model.ControllerReading;
import org.ptb.model.SensorReading;
import org.ptb.repo.SensorGroupReadingRepository;
import org.ptb.repo.SensorGroupRepository;
import org.ptb.repo.SensorReadingRepository;
import org.ptb.repo.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SensorWSController {
	
	@Autowired
	private UbidotUpdater update;
	
	@Autowired
	private SensorRepository sensorRepo;

	@Autowired
	private SensorGroupReadingRepository sensorGroupReadingRepo;

	@Autowired
	private SensorGroupRepository controllerRepo;

	@RequestMapping("/")
	public String index() {
		java.util.Date date= new java.util.Date();
		Timestamp currTime = new Timestamp(date.getTime());
		System.out.println(currTime + " New Request");
		return "Greetings from EnvironmentMonitor";
	}
	
	@RequestMapping("/ping")
	public void ping() {
		java.util.Date date= new java.util.Date();
		Timestamp currTime = new Timestamp(date.getTime());
		System.out.println(currTime + " New Ping Request");
//		return "ok";
	}

	/**
	 * Endpoint designed for easy discovery & updates
	 * 
	 * @param controllerId
	 *            - Unique Identifier for reporting sensor controller
	 * @param sensorId
	 *            - Unique for controller sensor identifier
	 * @param rv
	 *            -
	 * @return
	 */
	@RequestMapping("/u/{controllerId}/{sensorId}")
	public ControllerReading addSensorReading(@PathVariable String controllerId, @PathVariable String sensorId,
			@RequestParam(value = "rv", required = false) String rv) {
		Controller c = checkAndGetController(controllerId);
		Sensor sensor = checkAndGetSensor(c, sensorId);
		ControllerReading cr = checkAndGetSensorGroupReading(c, sensor);
		
		if (null != rv) {
			if (! "NAN".equalsIgnoreCase(rv) && ! "testVal".equalsIgnoreCase(rv)) {
				update.setSensorId(sensorId);
				update.setSensorVal(rv);
				update.doUpdate();
			}
			if (null != cr) {
				if (null != cr.getSensorReadingMap()) {
					if (cr.getSensorReadingMap().containsKey(sensor.getId())) {
						cr.getSensorReadingMap().get(sensor.getId()).addValue(rv);
						sensorGroupReadingRepo.save(cr);
						System.out.println("Saved reading to datastore");
					} else {
						cr.getSensorReadingMap().put(sensor.getId(), new SensorReading(sensor.getId()));
						System.out.println("sgr.getSensorReadingMap().get(sensor) null!!!");
						cr.getSensorReadingMap().get(sensor.getId()).addValue(rv);
						sensorGroupReadingRepo.save(cr);
					}
				} else {
					System.out.println("cr.getSensorReadingMap() null!!!");
				}
			} else {
				System.out.println("sgr null!!!");
			}
		}
		return cr;
	}

	private ControllerReading checkAndGetSensorGroupReading(Controller c, Sensor sensor) {
		final String id = Common.getCalcId(c.getId(), new DateTime());
		ControllerReading cr = null;
		try {
			cr = sensorGroupReadingRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (cr == null) {
			cr = new ControllerReading(c);
			sensorGroupReadingRepo.save(cr);
			System.out.println("Saved new ControllerReading: " + cr.toString());
		} else {
			System.out.println("Found ControllerReading: " + cr.toString());
			// System.out.println("Deleting ControllerReading: " + cr.toString()
			// );
			// sensorGroupReadingRepo.delete(cr);
			// System.out.println("Re-creating ControllerReading: " +
			// cr.toString() );
			// cr = new ControllerReading(c);
			// sensorGroupReadingRepo.save(cr);
			// System.out.println("Saved new SensorGroupReading: " +
			// cr.toString() );
		}
		return cr;
	}

	private final Sensor checkAndGetSensor(final Controller c, final String sensorId) {
		final String fullSensorId = Common.getCompoundSensorName(c.getId(), sensorId);
		Sensor sensor = sensorRepo.findById(fullSensorId);
		if (sensor == null) {
			sensor = new Sensor(fullSensorId, "auto create sensor");
			sensor.setController(c.getId());
			sensorRepo.save(sensor);
			c.getSensorMap().put(sensorId, sensor);
			System.out.println("Saved new sensor: " + sensor.toString());
			controllerRepo.save(c);

		} else {
			System.out.println("Found sensor: " + sensor.toString());
			// System.out.println("Deleting sensor: " + sensor.toString() );
			// sensorRepo.delete(sensor);
			// System.out.println("Re-creating sensor: " + sensor.toString() );
			// sensor = new Sensor(fullSensorId,"auto create sensor");
			// sensor.setController(c.getId());
			// sensorRepo.save(sensor);
			// c.getSensorMap().put(sensorId, sensor);
			// System.out.println("Saved new sensor: " + sensor.toString() );
			// controllerRepo.save(c);
		}
		return sensor;
	}

	private final Controller checkAndGetController(String controllerId) {
		Controller c = null;
		try {
			c = controllerRepo.findById(controllerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == c) {
			System.out.println("Creating new Controller: " + controllerId.toString());
			c = new Controller();
			c.setId(controllerId);
			controllerRepo.save(c);
		} else {
			System.out.println("Found Controller: " + c.toString());
			// System.out.println("Deleting Controller: " + c.toString() );
			// controllerRepo.delete(c);
			// System.out.println("Creating new Controller: " +
			// controllerId.toString() );
			// c = new Controller();
			// c.setId(controllerId);
			// controllerRepo.save(c);
		}
		return c;
	}
}
