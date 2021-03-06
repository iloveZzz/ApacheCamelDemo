package com.test.client.helloworld;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * HttpClient访问Camel
 * 
 * @author CYX
 * @time 2017年12月18日上午8:50:14
 */
public class TestHttpClient {

	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8282/doHelloWorld");

		// 添加参数
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("body", "是啥"));

		httpPost.setHeader("ContentType", "UTF-8");
		UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		httpPost.setEntity(urlEntity);

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		InputStream inputStream = entity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		// 读取HTTP请求内容
		String buffer = null;
		StringBuffer sb = new StringBuffer();
		while ((buffer = br.readLine()) != null) {
			// 在页面中显示读取到的请求参数
			sb.append(buffer + "\n");
		}

		System.out.println("接收返回数据:\n" + URLDecoder.decode(sb.toString().trim(), "utf-8"));

		System.out.println("Login form get: " + response.getStatusLine() + entity.getContent());

	}

}
