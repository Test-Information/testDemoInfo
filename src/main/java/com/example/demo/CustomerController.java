package com.example.demo;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    private final CustomerRepository repository;

    // コンストラクタ注入
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }
    
    // Getパラメータ受信時
    // (例) http://localhost:8080/customer?〇〇=〇〇&〇〇=〇〇
    @GetMapping("/customer")
    public String add(
    		@RequestParam (required = false) String name,
    		@RequestParam(required = false) Long balance,
    		@RequestParam(required = false) boolean send,
    		Model model) {
    	
    	if(send == false) {
    		return "customer";
    	}
    	
    	Customer cust =	 new Customer();
    	String errMsg = "";

    	Optional<String> optName = Optional.ofNullable(name);
    	Optional<Long> optBalance = Optional.ofNullable(balance);

    	// 入力値ありの場合、処理を実行
    	if(optName.isPresent()) {
    		cust.setName(name);
    	} else {
    		errMsg += "顧客名が入力されていません\n";//スペースが入力された時の対応が必要
    	}
    	
    	if(optBalance.isPresent())  {
    		cust.setBalance(balance); 
    	} else {
    		errMsg += "金額が入力されていません\n";
    	}
    	
    	// 記号のバリデーションが必要
    	if((StringUtils.hasText(cust.getName())) && (cust.getBalance() != null)){
            repository.save(cust);
            model.addAttribute("result", "顧客情報の作成に成功しました");
            return "customer";
    	} else {
    		model.addAttribute("result", errMsg);
    	}
    	
        return "customer";
    }
}