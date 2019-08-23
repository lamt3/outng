package com.dapp.outng.common.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

public class JsonUtils {
	protected static final ObjectMapper objectMapper = new ObjectMapper();
	protected static final ObjectMapper objectMapperNoPrettyPrint = new ObjectMapper();
	static {

		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

		objectMapperNoPrettyPrint.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapperNoPrettyPrint.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapperNoPrettyPrint.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
		objectMapperNoPrettyPrint.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapperNoPrettyPrint.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
	}
	private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static final <T> String serialize(T object) {
		return serialize(object, true);
	}

	public static final <T> String serialize(T object, boolean prettyPrint) {

		try {

			if (prettyPrint) {
				return objectMapper.writeValueAsString(object);
			} else {
				return objectMapperNoPrettyPrint.writeValueAsString(object);
			}

		} catch (IOException e) {
			LOG.error(e.toString());
		}
		return null;
	}

	public static final <T> T deserialize(String json, Class<T> type) {

		T instance = null;

		try {

			instance = objectMapper.readValue(json, type);

		} catch (Exception e) {

			LOG.error(e.toString());
		}

		return instance;
	}
	
	public static final <T> String toJson(T object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
	public static final <T> T toObject(String json, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

}
