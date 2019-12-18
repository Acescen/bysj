package nuc.bysjxtglxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("nuc.bysjxtglxt.mapper")
public class BysjxtglxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BysjxtglxtApplication.class, args);
    }

}
