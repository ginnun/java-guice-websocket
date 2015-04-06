package com.sefa;

import org.apache.log4j.Logger;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;


public class AppConfig implements ServerApplicationConfig {

    private static final Logger log = Logger.getLogger(AppConfig.class);
    private static final String ENDPOINT_PACKAGE_PREFIX = "";


    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(
            Set<Class<? extends Endpoint>> scanned) {

        Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();

        log.info("Loading Programmatic Endpoints");

        return result;
    }


    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {

        // Deploy all WebSocket endpoints defined by annotations
        Set<Class<?>> results = new HashSet<Class<?>>();

        log.info("Filtering Annotated Endpoints from " + scanned.size() + " scanned matches");

        for (Class<?> clazz : scanned) {
            log.info("Loading Annotated Endpoint " + clazz.getName());
            results.add(clazz);
        }

        return results;
    }
}
