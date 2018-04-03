package com.saviorru.comsserver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTournamentController {

    @RequestMapping("/tournamenthello")
    public String greeting() {
        return String.format("This is tournament");
    }
}
