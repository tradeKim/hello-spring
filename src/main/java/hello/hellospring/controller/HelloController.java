package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
/*
spring-boot-devtools 라이브러리 추가 : html 자동컴파일
 */
    //Web에서 hi로 입력하면 해당 메소스를 호출한다.(index.html의 href="/hi"가 해당 메소드를 호출)
    @GetMapping("hi")
    public String hello(Model model) {
        model.addAttribute("data", "data 안녕!!");
        //resources하위의 hello.html에 해당 data변수를 Return한다.
        return "hello";
    }

    //get방식으로 변수를 넘긴다.
    //http://localhost:8080/hello-mvc?name=spring%20test
    @GetMapping("hello-mvc")
    public String HelloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //http://localhost:8080/hello-string?name=string%20test
    @GetMapping("hello-string")
    @ResponseBody   //html을 통하지 않고 retrun의 값을 그대로 화면에 표시해준다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    //http://localhost:8080/hello-api?name=Spring
    //api 방식으로 객체를 retrun 하는 경우 기본적으로 json형식(HttpMessageConverter가 처리 해준다.) return 해준다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName((name));
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
