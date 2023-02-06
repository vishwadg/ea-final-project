package miu.edu.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Value("${testingText}")
    private String testingText;

    @RequestMapping("/testingText")
    public String getName() {
        return testingText;
    }
}
