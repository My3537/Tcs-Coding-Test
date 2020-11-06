package com.currency.convertor.types;

import org.junit.platform.commons.util.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"buildID",
"fileName",
"version"
})
public class Build {
	
	@JsonProperty("buildID")
	private String buildID;
	@JsonProperty("fileName")
	private String fileName;
	@JsonProperty("version")
	private String version;

	@JsonProperty("buildID")
	public String getBuildID() {
		return buildID;
	}

	@JsonProperty("buildID")
	public void setBuildID(String buildID) {
		this.buildID = buildID;
	}

	@JsonProperty("fileName")
	public String getFileName() {
		return fileName;
	}

	@JsonProperty("fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("buildID", buildID)
				.append("fileName", fileName).append("version", version)
				.toString();
	}

}