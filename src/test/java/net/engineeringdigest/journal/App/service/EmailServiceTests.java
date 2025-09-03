package net.engineeringdigest.journal.App.service;

import net.engineeringdigest.journal.App.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendMail("somalikaarora@gmail.com","Testing java mail sender","Hello guys");
    }
}
