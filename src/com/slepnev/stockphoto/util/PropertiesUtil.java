package com.slepnev.stockphoto.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {
    
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "application.properties";
    
    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}