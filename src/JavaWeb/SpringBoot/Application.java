package JavaWeb.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    } //额 处于蒙的状态 我啥时候装tomcat 了 就直接能打开这个页面

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}