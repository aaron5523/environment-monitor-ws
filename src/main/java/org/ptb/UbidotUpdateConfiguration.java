package org.ptb;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
@ComponentScan
public class UbidotUpdateConfiguration {
	@Value("${ubidots.host}")
	private String host;
    
	@Value("${ubidots.path}")
	private String path;
	
	@Value("${ubidots.api_key}")
	private String api_key;
    
    @Value("${ubidots.temp_id}")
	private String temp_id;
    
    @Value("${ubidots.humid_id}")
	private String humid_id;
    
    public UbidotUpdateConfiguration() {
    	
    }

    @PostConstruct
    public void init() {
        System.out.println("================== " + host + "================== ");
        System.out.println("================== " + path + "================== ");
        System.out.println("================== " + api_key + "================== ");
        System.out.println("================== " + temp_id + "================== ");
        System.out.println("================== " + humid_id + "================== ");
    }
	/**
	 * @return the aPI_KEY
	 */
	public String getAPI_KEY() {
		return api_key;
	}

	/**
	 * @return the tEMP_ID
	 */
	public String getTEMP_ID() {
		return temp_id;
	}

	/**
	 * @return the hUMID_ID
	 */
	public String getHUMID_ID() {
		return humid_id;
	}

	/**
	 * @return the hOST
	 */
	public String getHOST() {
		return host;
	}

	/**
	 * @return the gET_PATH
	 */
	public String getGET_PATH() {
		return path;
	}
    
    
}
