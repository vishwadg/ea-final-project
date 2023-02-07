package miu.edu.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.dto.MailRequest;
import miu.edu.dto.MailResponse;
import miu.edu.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    public final EmailService emailService;

    @PostMapping("/send")
    public MailResponse sendEmail(@RequestBody MailRequest mailRequest) {
        return emailService.sendEmail(mailRequest);
    }
}
