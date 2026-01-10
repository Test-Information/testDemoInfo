package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/") // ブラウザで一番最初に開くページを指定
    public String index() {
        return "ポートフォリオサイト、制作開始！";
    }
}
