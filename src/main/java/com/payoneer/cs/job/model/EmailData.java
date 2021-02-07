package com.payoneer.cs.job.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailData {
	@JsonProperty("toEmailIds")
	private String toEmailIds;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("content")
	private String content;
}
