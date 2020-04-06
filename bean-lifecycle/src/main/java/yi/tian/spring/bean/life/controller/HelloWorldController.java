package yi.tian.spring.bean.life.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * indexController
 *
 * @author yitian
 * @date 2020/4/6 10:33
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/index")
    public String index() {
        return "Hello World !!!";
    }

}
