package org.example;

import org.example.config.JsoupConfig;
import org.example.parser.LeonParser;
import org.example.parser.LeonParserImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LeonParseApplication {
    public static void main(String[] args) {
        LeonParserImpl leonParser = new LeonParserImpl();
        leonParser.parse();
    }
}