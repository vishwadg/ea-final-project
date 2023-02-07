package miu.edu.service;

import miu.edu.dto.MailRequest;
import miu.edu.dto.MailResponse;

import java.util.Map;

public interface EmailService {
    MailResponse sendEmail(MailRequest mailRequest);
}
