package com.sefa.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sefa.config.ConfigReader;
import com.sefa.config.DefaultConfigReader;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;

@WebListener
public class DefaultServletConfig extends GuiceServletContextListener {
    private static final Logger log = Logger.getLogger(DefaultServletConfig.class);
    private static Injector injector = null;
    private static ConfigReader conf = new DefaultConfigReader();


    @Override
    protected Injector getInjector() {
        return getPublicInjector();
    }

    public static Injector getPublicInjector() {
        if (injector == null) {
            if (conf.isServer1()) {
                log.info("injecting as server1");
                injector = Guice.createInjector(new Server1ServletModule(conf));
            } else {
                log.info("injecting as server2");
                injector = Guice.createInjector(new Server2ServletModule(conf));
            }
        }

        return injector;
    }
}
