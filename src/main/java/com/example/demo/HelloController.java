package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/") // ブラウザで一番最初に開くページを指定
    public String index(Model model) {
    	model.addAttribute("result","success");
        return "index";
    }
}
