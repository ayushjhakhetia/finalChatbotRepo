package com.impetus.rpacoordinator.utils;

import java.util.ResourceBundle;

public class CoordinatorUtils {

    public static String readConfigurationProperty(String propertyName) {
        ResourceBundle rb = ResourceBundle.getBundle("configurations");
        String value = rb.getString(propertyName);
        System.out.println("Configuration Property Name: " + propertyName + " and value is: " + value);
        return value;
    }
}
