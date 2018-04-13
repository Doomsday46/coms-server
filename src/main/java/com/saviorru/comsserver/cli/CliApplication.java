package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.Application;
import org.springframework.boot.SpringApplication;

public class CliApplication {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        new ConsoleInterface().run();
    }
}
