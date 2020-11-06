package com.user.github.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class JsonUtils {
	
private static ObjectMapper mapper;
	
	static {
		mapper = new ObjectMapper();
		
		mapper.registerModule(new JavaTimeModule());
		
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	}
	
	public static <T> T fromJson(String json, Class<T> type) {
		try {
			return mapper.readValue(json, type);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public static <T> T fromJson(InputStream inputStream, Class<T> type) {
		try {
			return mapper.readValue(inputStream, type);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public static <T> String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public static byte[] toJsonBytes(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsBytes(obj);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
}
