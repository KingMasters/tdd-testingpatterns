package com.testdouble.fake;

import com.testdouble.EmailService;
import com.testdouble.User;
import com.testdouble.UserService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/*
    Çalışır ama basitleştirilmiş gerçek implementasyon
    Fake = “gerçek gibi davranır ama production için uygun değildir”
    Ne zaman?
    Gerçek implementasyon yavaş / pahalı / karmaşık ise
    In-memory DB gibi
 */

class UserServiceTest {

    @Test
    void user_can_be_registered_and_loaded() {
        // Arrange
        FakeUserRepository fakeRepository = new FakeUserRepository();
        EmailService emailService = mock(EmailService.class);
        UserService service = new UserService(fakeRepository, emailService);

        // Act
        service.register("1", "Ali", "ali@gmail.com");
        User user = service.getUser("1");

        // Assert
        assertNotNull(user);
        assertEquals("1", user.getId());
        assertEquals("Ali", user.getName());
        assertEquals("ali@gmail.com", user.getEmail());
    }
}
