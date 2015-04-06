package com.sefa.guice;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.sefa.RandomsWebsocketEndpoint;
import com.sefa.RandomListenerServlet;
import com.sefa.config.ConfigReader;
import com.sefa.receiver.EventReceiver;
import com.sefa.receiver.RemoteEventReceiver;
import com.sefa.receiver.TimedMessageReceiver;
import com.sefa.remote.HttpRemoteClient;
import com.sefa.remote.RemoteClient;
import com.sefa.timer.RemoteRepeatedGenerator;
import com.sefa.timer.RepeatedGenerator;
import org.apache.http.client.HttpClient;

public class Server2ServletModule extends ServletModule {
    private ConfigReader conf;

    public Server2ServletModule(ConfigReader conf) {
        this.conf = conf;
    }

    @Override
    protected void configureServlets() {
        Names.bindProperties(binder(), conf.getProperties());
        bindConstant().annotatedWith(Names.named("remoteAddress")).to(conf.getServer1Address());

        bind(RepeatedGenerator.class).to(RemoteRepeatedGenerator.class);
        bind(TimedMessageReceiver.class).to(RandomsWebsocketEndpoint.class);
        bind(RemoteClient.class).to(HttpRemoteClient.class);
        bind(EventReceiver.class).to(RemoteEventReceiver.class);
        bind(HttpClient.class).toInstance(HttpClientFactory.createHttpClient());
        serve("/randoms").with(RandomListenerServlet.class); // bond only if we are server2
    }
}
