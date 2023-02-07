package miu.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AvatarServiceApplication {
    public static void main(String[] args) {
     SpringApplication.run(AvatarServiceApplication.class, args);
    }
}
