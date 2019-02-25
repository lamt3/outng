package com.dapp.outng.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dapp.outng.common.configs.RestConfig;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class RestUtils {
	private static final Logger LOG = LoggerFactory.getLogger(RestUtils.class);

	public static RestTemplate createRestTemplate(RestConfig restConfig) {
		RestTemplate restTemplate = new RestTemplate(createHttpRequestFactory(restConfig));
		restTemplate.setInterceptors(createRequestInterceptors());
		restTemplate.setMessageConverters(createObjectMapper());
		return restTemplate;
	}

	public static ClientHttpRequestFactory createHttpRequestFactory(RestConfig restConfig) {

		// int timeout = restConfig.getTimeout();
		// HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
		// HttpComponentsClientHttpRequestFactory();
		// clientHttpRequestFactory.setConnectTimeout(timeout);

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(restConfig.getTimeout())
				.setSocketTimeout(5000).build();

		SocketConfig.Builder socketConfigBuilder = SocketConfig.custom().setSoTimeout(5000).setTcpNoDelay(true)
				.setSoLinger(0).setSoKeepAlive(true);

		SocketConfig socketConfig = socketConfigBuilder.build();

		Registry<ConnectionSocketFactory> schemeRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", SSLConnectionSocketFactory.getSocketFactory())
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).build();

		PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(
				schemeRegistry);
		clientConnectionManager.setMaxTotal(50);
		clientConnectionManager.setDefaultMaxPerRoute(50);
		clientConnectionManager.setValidateAfterInactivity(50);

		HttpClientBuilder builder = HttpClientBuilder.create().setConnectionManager(clientConnectionManager)
				.setDefaultRequestConfig(requestConfig).setDefaultSocketConfig(socketConfig)
				.setRetryHandler(new DefaultHttpRequestRetryHandler(2, false));

		HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory(
				builder.build());

		BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(
				httpFactory);

		return bufferingClientHttpRequestFactory;
	}

	public static List<ClientHttpRequestInterceptor> createRequestInterceptors() {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
//		interceptors.add(new CustomClientHttpRequestInterceptor());
		return interceptors;

	}

	public static List<HttpMessageConverter<?>> createObjectMapper() {

		// Add message converter to convert snake casing
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());

		// Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
		converter.setObjectMapper(mapper);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(converter);
		messageConverters.add(new StringHttpMessageConverter());
		return messageConverters;
	}

}
