package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextService {

    @GetMapping("/camelize")
    public String camelCase(TextServiceWorker tsw){ return tsw.getCamelCase(); }

    @GetMapping("/redact")
    public String badWord(TextServiceWorker tsw){ return tsw.fixBadWord(); }

    @PostMapping("/encode")
    public String encode(TextServiceWorker tsw){return tsw.encode();}

    @PostMapping("/s/{find}/{replacement}")
    public String sed(@RequestBody String body, TextServiceWorker tsw){
        tsw.setBody(body);
        return tsw.sed(); }
}
