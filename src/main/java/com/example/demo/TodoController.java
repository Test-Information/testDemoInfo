package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TodoController {

    private final TodoRepository repository;

    // コンストラクタ注入
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    // データを保存するURL: http://localhost:8080/add?title=データ内容
    @GetMapping("/add")
    public String add(@RequestParam String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        repository.save(todo);
        return "保存しました: " + title;
    }

    // データを全件表示するURL: http://localhost:8080/list
    @GetMapping("/list")
    public List<Todo> list() {
        return repository.findAll();
    }
}