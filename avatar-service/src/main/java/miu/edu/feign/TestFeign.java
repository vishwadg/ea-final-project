package miu.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-service")
public interface TestFeign {
    @GetMapping("/user/hello")
    public String hello();
}
