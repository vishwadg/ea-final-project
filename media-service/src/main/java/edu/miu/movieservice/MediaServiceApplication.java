package edu.miu.movieservice;

//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@SpringBootApplication
//@EnableRabbit
@EnableWebMvc
@OpenAPIDefinition
public class MediaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaServiceApplication.class, args);
    }


}
