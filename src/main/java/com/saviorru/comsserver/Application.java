package com.saviorru.comsserver;


import com.saviorru.comsserver.cli.ConsoleInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        //new ConsoleInterface().run();
    }

}
