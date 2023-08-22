package org.example.config;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;

public class JsoupConfig {

    private static final String CONFIG_FILE = "config.properties";
    private static final String PROXY_HOST_KEY = "proxy.host";
    private static final String PROXY_PORT_KEY = "proxy.port";

    private Proxy proxy;

    public JsoupConfig() {
        Properties properties = loadProperties();
        String proxyHost = properties.getProperty(PROXY_HOST_KEY);
        int proxyPort = Integer.parseInt(properties.getProperty(PROXY_PORT_KEY));
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("File not found: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
        return properties;
    }

    public Connection connect(String url) {
        return Jsoup.connect(url).proxy(proxy);
    }
}