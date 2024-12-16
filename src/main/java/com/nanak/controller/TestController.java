package com.nanak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nanak
 * @CreateTime: 2024-12-16
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping
    public String test() {
        return "test";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
