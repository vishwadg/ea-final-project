package edu.miu.ratingservice;

//import edu.miu.ratingservice.config.VaultConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@Log4j2
@EnableDiscoveryClient
@SpringBootApplication
public class RatingServiceApplication {

    public static void main(String[] args) {

        String[] actualArgs = new String[]{"test=test"};
        ConfigurableApplicationContext context = SpringApplication.run(RatingServiceApplication.class, args);


    }

}
