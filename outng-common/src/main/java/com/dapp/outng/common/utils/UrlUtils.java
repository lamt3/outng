package com.dapp.outng.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtils {
	private static final Logger LOG = LoggerFactory.getLogger(UrlUtils.class);

	public static String buildUrlWithQueryParam(String endpoint, String queryParam, String query) {
		StringBuilder sb = new StringBuilder(endpoint);
		if (!endpoint.contains("?")) {
			sb.append("?");
		}
		try {
			sb.append(queryParam).append(URLEncoder.encode(query, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOG.error("Unsupported URL Encoding: {} ", e);
		}
		return sb.toString();
	}

	public static String buildUrlWithQueryParams(String endpoint, Map<String, String> queries) {
		StringBuilder sb = new StringBuilder(endpoint);

		if (!endpoint.contains("?")) {
			sb.append("?");
		}

		AtomicInteger count = new AtomicInteger(0);
		AtomicInteger size = new AtomicInteger(queries.size() - 1);

		queries.forEach((k, v) -> {
			try {
				sb.append(k).append("=").append(URLEncoder.encode(v, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				LOG.error("Unsupported URL Encoding: {} ", e);
			}
			if (count != size) {
				sb.append("&");
				count.getAndIncrement();
			}
		});
		return sb.toString();
	}

}
