package com.saviorru.comsserver.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class SingletonResourceBundle {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("programText");

    public SingletonResourceBundle(String resource, Locale locale){
        SingletonResourceBundle.resourceBundle = ResourceBundle.getBundle(resource,locale);
    }
    public SingletonResourceBundle(String resource){
        SingletonResourceBundle.resourceBundle = ResourceBundle.getBundle(resource);
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

}
