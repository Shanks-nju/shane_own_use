package cn.hxh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipResources {
    @GetMapping("/hello")
    public String swagger() {
        return "hello world";
    }
}
