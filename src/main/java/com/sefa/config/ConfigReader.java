package com.sefa.config;


import java.util.Properties;

public interface ConfigReader {
    public String getServer1Address();

    public String getServer2Address();

    public boolean isServer1();

    public boolean isServer2();

    public Properties getProperties();
}
