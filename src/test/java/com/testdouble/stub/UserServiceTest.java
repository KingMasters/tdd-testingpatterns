package com.testdouble.stub;

import com.testdouble.EmailService;
import com.testdouble.User;
import com.testdouble.UserRepository;
import com.testdouble.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UserServiceTest {

    @Test
    void returns_user_name_from_repository() {
        // Arrange
        UserRepository stubRepository = new StubUserRepository();
        EmailService emailService = mock(EmailService.class);
        UserService service = new UserService(stubRepository, emailService);

        // Act
        User user = service.getUser("any-id");

        // Assert
        assertEquals("Stub User", user.getName());
    }
}