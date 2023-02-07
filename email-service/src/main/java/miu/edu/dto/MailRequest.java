package miu.edu.dto;

import lombok.Data;

@Data
public class MailRequest {

    private String name;
    private String to;
    private String from;
    private String subject;

    private String role;

    private String password;
}
