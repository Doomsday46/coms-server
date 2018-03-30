package com.saviorru.comsserver.cli;

import java.util.Locale;
import java.util.ResourceBundle;

public class  TextProgram {

    private static ResourceBundle resourceBundle;

    public  TextProgram(String resource,Locale locale){
        TextProgram.resourceBundle = ResourceBundle.getBundle(resource,locale);
    }
    public  TextProgram(String resource){
        TextProgram.resourceBundle = ResourceBundle.getBundle(resource);
    }
    public  TextProgram(){
        TextProgram.resourceBundle = ResourceBundle.getBundle("programText");
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

}
