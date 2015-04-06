package com.sefa.guice;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class HttpClientFactory {
    public static HttpClient createHttpClient() {
        HttpClientBuilder client = HttpClients.custom();
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000)
                .build();
        client.setDefaultRequestConfig(config);
        return client.build();
    }
}
