package com.testdouble.mock;

import com.testdouble.EmailService;
import com.testdouble.UserRepository;
import com.testdouble.UserService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/*
    Davranışı doğrulamak için
        Mock = “nasıl çağrıldığını test eder”
    Ne zaman?
        “Bu metod çağrıldı mı?”
        “Kaç kere çağrıldı?”
        “Hangi parametreyle?”
    Ideal kullanım
        Void method
        Side-effect var
        External sistem (SMTP, API vs.)
 */
class UserServiceTest {

    @Test
    void sends_welcome_email_when_user_is_registered() {
        // Arrange
        UserRepository repository = mock(UserRepository.class);
        EmailService emailService = mock(EmailService.class);

        UserService service = new UserService(repository, emailService);

        // Act
        service.register("1", "osimhen", "test@example.com");

        // Assert (interaction-based)
        verify(emailService, times(1)).sendWelcomeEmail("test@example.com");
    }
}

