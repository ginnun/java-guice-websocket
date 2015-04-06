package com.sefa.guice;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.sefa.IncomingEventListenerServlet;
import com.sefa.config.ConfigReader;
import com.sefa.random.BasicRandomLongProvider;
import com.sefa.random.BasicRandomStringProvider;
import com.sefa.random.RandomLongProvider;
import com.sefa.random.RandomStringProvider;
import com.sefa.receiver.EventReceiver;
import com.sefa.receiver.RemoteTimedMessageReceiver;
import com.sefa.receiver.TimedMessageReceiver;
import com.sefa.remote.HttpRemoteClient;
import com.sefa.remote.RemoteClient;
import com.sefa.timer.DefaultRepeatedGenerator;
import com.sefa.timer.RepeatedGenerator;
import org.apache.http.client.HttpClient;

public class Server1ServletModule extends ServletModule {

    private ConfigReader conf;

    public Server1ServletModule(ConfigReader conf) {
        this.conf = conf;
    }

    @Override
    protected void configureServlets() {
        Names.bindProperties(binder(), conf.getProperties());
        bindConstant().annotatedWith(Names.named("remoteAddress")).to(conf.getServer2Address());

        bind(HttpClient.class).toInstance(HttpClientFactory.createHttpClient());
        bind(RemoteClient.class).to(HttpRemoteClient.class);
        bind(TimedMessageReceiver.class).to(RemoteTimedMessageReceiver.class);
        bind(EventReceiver.class).to(DefaultRepeatedGenerator.class);
        bind(RepeatedGenerator.class).to(DefaultRepeatedGenerator.class);
        bind(RandomLongProvider.class).to(BasicRandomLongProvider.class);
        bind(RandomStringProvider.class).to(BasicRandomStringProvider.class);

        serve("/event").with(IncomingEventListenerServlet.class); // bond only if we are server1
    }


}