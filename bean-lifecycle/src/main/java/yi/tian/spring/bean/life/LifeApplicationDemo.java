package yi.tian.spring.bean.life;

import org.openjdk.jmh.annotations.Benchmark;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main程序
 *
 * @author yitian
 * @date 2020/4/6 10:28
 */
@SpringBootApplication(scanBasePackages = "yi.tian.spring.bean.life")
public class LifeApplicationDemo {


    public static void main(String[] args) {

        SpringApplication.run(LifeApplicationDemo.class,args);

    }



}
