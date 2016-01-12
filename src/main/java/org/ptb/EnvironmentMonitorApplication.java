package org.ptb;

import org.ptb.model.Sensor;
import org.ptb.repo.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class EnvironmentMonitorApplication implements CommandLineRunner {

	@Autowired
	private SensorRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(EnvironmentMonitorApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Sensor("Alice", "Alice test"));
		repository.save(new Sensor("Bob", "Bob test"));

		// fetch all customers
		System.out.println("Sensors found with findAll():");
		System.out.println("-------------------------------");
		for (Sensor sensor : repository.findAll()) {
			System.out.println(sensor);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Sensor found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findById("Alice"));
	}
}
