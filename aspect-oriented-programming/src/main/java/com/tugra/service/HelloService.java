package com.tugra.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(String name) {
        return "Merhaba, " + name + "!";
    }

}