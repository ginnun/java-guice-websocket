package com.sefa.remote;

import com.google.gson.Gson;
import com.sefa.events.IncomingEvent;
import com.sefa.events.RandomsGenerated;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class HttpRemoteClient implements RemoteClient {
    private static final Logger log = Logger.getLogger(HttpRemoteClient.class);
    private HttpPost latestPost;

    @Inject
    private HttpClient httpClient;
    @Inject
    @Named("remoteAddress")
    private String remoteAddress;
    private ContentType contentType = ContentType.create("applivation/json", "UTF-8");
    private Gson gson = new Gson();


    @Override
    public void sendGenerateds(RandomsGenerated generateds) {
        send(generateds, "randoms");
    }

    @Override
    public void sendIncomingEvent(IncomingEvent event) {
        send(event, "event");
    }

    private void send(Object obj, String path) {

        try {
            StringEntity entity = new StringEntity(gson.toJson(obj), contentType);
            if (latestPost != null)
                latestPost.abort();

            latestPost = new HttpPost(remoteAddress + path);
            log.info(remoteAddress + path);
            latestPost.setEntity(entity);
            HttpResponse response = httpClient.execute(latestPost);
            response.getEntity();
        } catch (IOException e) {
            log.error(e);
        }
    }
}
