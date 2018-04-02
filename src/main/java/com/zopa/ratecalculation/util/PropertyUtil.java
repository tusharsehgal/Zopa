package com.zopa.ratecalculation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Get the property value from property file.
 */
public class PropertyUtil {
    static Properties properties;

    public static final String FILE_PATH = "/rate.properties";

    static {
        properties = new Properties();

        try {
            properties.load(ClassLoader.class.getResourceAsStream(FILE_PATH));
        } catch (IOException e) {
            System.out.println("Unable to load property file");
        }
    }

    /**
     * Get the property value.
     *
     * @param value property
     * @return property value
     */
    public static int getValue(final String value) {
        return Integer.parseInt(String.valueOf(properties.get(value)));
    }
}
