package com.sefa.config;

import org.apache.log4j.Logger;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
public class DefaultConfigReader implements ConfigReader {
    private static final Logger log = Logger.getLogger(DefaultConfigReader.class);
    private static Properties prop;
    private String fileName = "server.properties";

    public DefaultConfigReader() {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public String getServer1Address() {
        return prop.getProperty("server1Address");
    }

    @Override
    public String getServer2Address() {
        return prop.getProperty("server2Address");
    }

    @Override
    public boolean isServer1() {
        return "server1".equals(prop.getProperty("actAs").toLowerCase());
    }

    @Override
    public boolean isServer2() {
        return "server2".equals(prop.getProperty("actAs").toLowerCase());
    }

    @Override
    public Properties getProperties() {
        return prop;
    }
}
