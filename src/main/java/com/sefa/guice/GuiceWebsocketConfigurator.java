package com.sefa.guice;

import org.apache.log4j.Logger;

import javax.websocket.server.ServerEndpointConfig;

public class GuiceWebsocketConfigurator extends ServerEndpointConfig.Configurator {
    private static final Logger log = Logger.getLogger(GuiceWebsocketConfigurator.class);

    @Override
    public <T> T getEndpointInstance(Class<T> clazz)
            throws InstantiationException {
        log.info("creating endpoint instance");
        return DefaultServletConfig.getPublicInjector().getInstance(clazz);

    }
}
