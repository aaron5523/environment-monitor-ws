package org.ptb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
@Configuration
@EnableAutoConfiguration
public class UbidotUpdater {
	@Autowired
	private UbidotUpdateConfiguration config;
	
	private String sensorId;
	private String sensorVal;
	
	public UbidotUpdater() {

	}
	
	public UbidotUpdater(String sensorId, String sensorVal) {
		this.sensorId = sensorId;
		this.sensorVal = sensorVal;
		System.out.println("New UpidotUpdater");

	}
	
	public void doUpdate() {
		String updateSensorId = "";
		if ( sensorId.equalsIgnoreCase("t1") ) {
			updateSensorId = config.getTEMP_ID();
		} else if ( sensorId.equalsIgnoreCase("h1") ){
			updateSensorId = config.getHUMID_ID();
		} else if ( sensorId.equalsIgnoreCase("wt1") ){
			updateSensorId = config.getWorm_bin_temp_id();
		}
		HttpResponse<JsonNode> results;
		try {
			System.out.println("updater variable: " + updateSensorId);
			System.out.println("updater value: " + sensorVal);
			String URI = "http://" + config.getHOST() + config.getGET_PATH();
			System.out.println("URI: " + URI);

			results = Unirest.get(URI)
			  .queryString("token", config.getAPI_KEY() )
			  .queryString("variable", updateSensorId)
			  .queryString("value", sensorVal).asJson();
			System.out.println(results.getBody().getObject().toString(2));

		} catch (UnirestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @return the sensorId
	 */
	public String getSensorId() {
		return sensorId;
	}

	/**
	 * @param sensorId the sensorId to set
	 */
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	/**
	 * @return the sensorVal
	 */
	public String getSensorVal() {
		return sensorVal;
	}

	/**
	 * @param sensorVal the sensorVal to set
	 */
	public void setSensorVal(String sensorVal) {
		this.sensorVal = sensorVal;
	}

}
