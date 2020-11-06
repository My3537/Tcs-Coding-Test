package com.currency.convertor.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"cad_usd",
"fr_usd"
})
public class DataFile {
	
	@JsonProperty("cad_usd")
	private Float cadUsd; 
	@JsonProperty("fr_usd")
	private Float frUsd;

	@JsonProperty("cad_usd")
	public Float getCadUsd() {
	return cadUsd;
	}

	@JsonProperty("cad_usd")
	public void setCadUsd(Float cadUsd) {
	this.cadUsd = cadUsd;
	}

	@JsonProperty("fr_usd")
	public Float getFrUsd() {
	return frUsd;
	}

	@JsonProperty("fr_usd")
	public void setFrUsd(Float frUsd) {
	this.frUsd = frUsd;
	}

	
}