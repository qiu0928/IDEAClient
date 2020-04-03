package jeecg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("jeecg.Client.controller")
@EnableAutoConfiguration
public class UtilAppaton {
    public static void main(String[] args) {
        SpringApplication.run(UtilAppaton.class,args);
    }

}
