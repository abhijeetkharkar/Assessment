package com.eurail.test.app.utils;

/**
 * Created  2019-07-17 16:48
 * Copyright (c) 2019 Eurail.com B.V.
 *
 * @author Radion, Rodion.Kyryliak@eurail.com
 */

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpExecutor {
    private static final String DEFAULT_URL_CHARSET = StandardCharsets.UTF_8.name();
    private static final int DEFAULT_SOCKET_TIMEOUT = 60 * 1000;
    private static final int DEFAULT_CONNECT_TIMEOUT = 60 * 1000;

    public HttpResponse execGet(String url) {
        return execute(Request.Get(url));
    }

    public HttpResponse execPost(String url, String body) {
        return execute(addBody(Request.Post(url), body));
    }

    public HttpResponse execPost(String url) {
        return execute(Request.Post(url));
    }

    public HttpResponse execDelete(String url) {
        return execute(Request.Delete(url));
    }

    public static int getHttpStatusCode(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    public static String getBodyAsString(HttpResponse response) {
        try {
            String body = IOUtils.toString(response.getEntity().getContent(), DEFAULT_URL_CHARSET);
            return body == null || body.isEmpty() ? null : body;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Request addBody(Request request, String body) {
        return request.bodyString(body, ContentType.APPLICATION_JSON);
    }

    private HttpResponse execute(Request request) {
        try {
            return request
                    .socketTimeout(DEFAULT_SOCKET_TIMEOUT)
                    .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
                    .execute().returnResponse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
