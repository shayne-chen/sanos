package com.shaw.sanos.common.utils;

import okhttp3.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author shaw
 * @date 2022/4/6
 */

public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        setQueryParam(httpBuilder, params);
        Request.Builder requestBuilder = new Request.Builder().url(httpBuilder.build());
        setHeader(requestBuilder, headers);
        try {
            Response response = httpClient.newCall(requestBuilder.build()).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.warn("get请求出错url={},错误信息={}", url, e.getMessage());
        }
        return "";
    }

    public static void downloadFromUrl(String url, Map<String, String> headers, String filePath) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        Request.Builder requestBuilder = new Request.Builder().url(httpBuilder.build());
        setHeader(requestBuilder, headers);
        try {
            Response response = httpClient.newCall(requestBuilder.build()).execute();
            InputStream inputStream = Objects.requireNonNull(response.body()).byteStream();
            OutputStream outputStream = new FileOutputStream(filePath);
            IOUtils.copy(inputStream, outputStream);
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            log.warn("get请求出错url={},错误信息={}", url, e.getMessage());
        }
    }

    private static String doPost(String url, Map<String, String> params, Map<String, String> headers, String body, String contentType) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        setQueryParam(httpBuilder, params);

        MediaType mediaType = MediaType.parse(contentType);
        Request.Builder requestBuilder = new Request.Builder().url(httpBuilder.build());
        setHeader(requestBuilder, headers);
        try {
            Response response = httpClient.newCall(requestBuilder.post(RequestBody.create(mediaType, body)).build()).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.warn("post请求出错,url={},body={},错误信息={}", url, body, e.getMessage());
        }
        return "";
    }

    public static String doPostJson(String url, Map<String, String> params, Map<String, String> headers, String body) {
        return doPost(url, params, headers, body, "application/json;charset=utf-8");
    }

    private static void setHeader(Request.Builder requestBuilder, Map<String, String> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry: headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    private static void setQueryParam(HttpUrl.Builder httpUrlBuilder, Map<String, String> params) {
        if (!CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, String> entry: params.entrySet()) {
                httpUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
    }
}
