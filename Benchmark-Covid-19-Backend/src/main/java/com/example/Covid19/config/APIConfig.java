package com.example.Covid19.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfig {

	@Value("${api.ninja.url}")
	private String ninjaUrl;
	
	@Value("${api.ninja.token}")
	private String ninjaToken;
	
	@Value("${api.brasil.url}")
	private String brasilUrl;
	
	@Value("${api.brasil.token}")
	private String brasilToken;

	public String getNinjaUrl() {
		return ninjaUrl;
	}

	public String getNinjaToken() {
		return ninjaToken;
	}

	public String getBrasilUrl() {
		return brasilUrl;
	}

	public String getBrasilToken() {
		return brasilToken;
	}

}
