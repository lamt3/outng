package com.dapp.outng.common.interceptors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	private static Logger LOGGER = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		long start = System.currentTimeMillis();
		ClientHttpResponse response = execution.execute(request, body);
		logRequestDetails(request, body);
		LOGGER.info(doGetResponseDump(response, System.currentTimeMillis() - start));
		return response;
	}

	private void logRequestDetails(HttpRequest request, byte[] body) {
		// LOGGER.error("Headers: {}", request.getHeaders());
		// LOGGER.error("Request Method: {}", request.getMethod());
		// LOGGER.error("Request URI: {}", request.getURI());
		LOGGER.info(getRequestDump(request, body));
	}

	private String getRequestDump(HttpRequest request, byte[] body) {
		try {
			StringBuilder curlBuilder = new StringBuilder("curl -v -i ");
			StringBuilder headersBuilder = new StringBuilder("Headers : \n");
			String requestBody = new String(body, "UTF-8");

			HttpHeaders headers = request.getHeaders();
			Set<Entry<String, List<String>>> entrySet = headers.entrySet();
			for (Entry<String, List<String>> entry : entrySet) {
				if (CollectionUtils.isNotEmpty(entry.getValue())) {
					String header = entry.getKey() + ":" + entry.getValue().get(0);
					headersBuilder.append("/t").append(header).append("\n");
					if (!StringUtils.equalsIgnoreCase("Content-Length", entry.getKey())
							&& !StringUtils.equalsIgnoreCase("Accept-Charset", entry.getKey())) {
						curlBuilder.append("-H \"").append(header).append("\" ");
					}
				}
			}

			boolean hasBody = StringUtils.isNotBlank(requestBody);
			if (hasBody) {
				curlBuilder.append("-d '").append(requestBody).append("' ");
			}

			if (!(hasBody && request.getMethod() == HttpMethod.POST) && (request.getMethod() != HttpMethod.GET)) {
				curlBuilder.append("-X ").append(request.getMethod().name()).append(' ');
			}

			curlBuilder.append(request.getURI());

			StringBuilder dump = new StringBuilder(
					"===========================request begin================================================\n");
			dump.append("URI : " + request.getURI() + "\n");
			dump.append("Method : " + request.getMethod() + "\n");
			dump.append(headersBuilder);
			dump.append("Request Body : \n" + requestBody + "\n");
			dump.append("cURL : \n" + curlBuilder + "\n");
			dump.append("===========================request end================================================\n");

			return dump.toString();

		} catch (Exception e) {
			String msg = "Error with " + CustomClientHttpRequestInterceptor.class.getSimpleName()
					+ " request handling -- log and continue -- " + e.toString();
			LOGGER.warn(msg, e);
			return msg;
		}
	}

	private String doGetResponseDump(ClientHttpResponse response, long duration) throws IOException {
		StringBuilder headersBuilder = new StringBuilder("Headers : \n");
		HttpHeaders headers = response.getHeaders();
		Set<Entry<String, List<String>>> entrySet = headers.entrySet();
		for (Entry<String, List<String>> h : entrySet) {
			if (CollectionUtils.isNotEmpty(h.getValue())) {
				String header = h.getKey() + ":" + h.getValue().get(0);
				headersBuilder.append("\t").append(header).append("\n");
			}
		}

		StringBuilder dump = new StringBuilder(
				"===========================response begin================================================\n");
		dump.append("status code: " + response.getStatusCode() + "\n");
		dump.append("status text: " + response.getStatusText() + "\n");
		dump.append("Response Body: " + convertStreamToString(response.getBody()) + "\n");
		dump.append("duration: " + duration + "ms\n");
		dump.append(headersBuilder);
		return dump.toString();
	}

	private String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
