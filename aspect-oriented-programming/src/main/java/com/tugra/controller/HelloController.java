package com.tugra.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class HelloController {

    private final com.tugra.service.HelloService helloService;

    public HelloController(com.tugra.service.HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(path = "/hello")
    public String hello(@RequestParam String name) {
        return helloService.sayHello(name);
    }

}