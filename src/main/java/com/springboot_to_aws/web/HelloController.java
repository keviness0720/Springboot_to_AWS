package com.springboot_to_aws.web;

import com.springboot_to_aws.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  //컨트롤러를 JSON을 반환하는 컨트롤러로 만듬.
public class HelloController {
    @GetMapping("/hello")  //HTTP Method인 Get의 요청을 받을 수 있는 API
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,@RequestParam("amount") int amount){
        // @RequestParam 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션(외부에서 가져온 name을 String name에 저장
        return new HelloResponseDto(name, amount);

    }
}
