package yi.tian.spring.bean.life.context;

import org.openjdk.jmh.annotations.Benchmark;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author yitian
 * @date 2020/4/9 14:04
 */
public class WarmUpProcessor {

    @Benchmark
    public void warmUpinit(){
        System.out.println("预热开始");
    }

}
