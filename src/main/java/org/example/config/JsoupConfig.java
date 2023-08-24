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
    private static volatile JsoupConfig instance;

    private Proxy proxy;

    private JsoupConfig() {
        Properties properties = loadProperties();
        String proxyHost = properties.getProperty(PROXY_HOST_KEY);
        int proxyPort = Integer.parseInt(properties.getProperty(PROXY_PORT_KEY));
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    }

    public static JsoupConfig getInstance() {
        if (instance == null) {
            synchronized (JsoupConfig.class) {
                if (instance == null) {
                    instance = new JsoupConfig();
                }
            }
        }
        return instance;
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
        return Jsoup.connect(url)
                .header("authority", "leonbets.com")
                .header("accept", "*/*")
                .header("accept-language", "ru,ru-RU;q=0.9")
                .cookie("ABTestSeed", "88")
                .cookie("ipfrom", "149.28.228.65")
                .cookie("_ga", "GA1.1.1118425112.1692298070")
                .cookie("_gcl_au", "1.1.1173518584.1692298071")
                .cookie("_ym_uid", "1692296273203283691")
                .cookie("_ym_d", "1692298073")
                .cookie("referer", "https://leonbets.com/ru-ru/")
                .cookie("dis-request-id", "5d6227655cf9078d0da75d237dc19d83")
                .cookie("dis-timestamp", "2023-08-19T12:32:21-07:00")
                .cookie("dis-remote-addr", "157.245.8.42")
                .cookie("theme", "DARK")
                .cookie("firstTheme", "DARK")
                .cookie("x-app-language", "en_US")
                .cookie("qtag_rfrr", "null-null")
                .cookie("_ym_isad", "1")
                .cookie("_ga_JZZNGY93CC", "GS1.1.1692817147.7.1.1692817162.0.0.0")
                .header("referer", "https://leonbets.com/bets")
                .header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
                .header("x-app-browser", "chrome")
                .header("x-app-env", "prod")
                .header("x-app-language", "en_US")
                .header("x-app-layout", "desktop")
                .header("x-app-modernity", "2019")
                .header("x-app-os", "windows")
                .header("x-app-platform", "web")
                .header("x-app-rendering", "csr")
                .header("x-app-skin", "default")
                .header("x-app-theme", "DARK")
                .header("x-app-version", "6.69.2")
                .header("x-requested-uri", "/bets")
                .method(org.jsoup.Connection.Method.GET)
                .ignoreContentType(true)
                .proxy(proxy);
    }
}