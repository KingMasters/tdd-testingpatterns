package com.testdouble.dummy;

import com.testdouble.AuditService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Dummy = Sadece orada dursun diye verilen, hiç kullanılmayan nesne.
    Davranışı yok
    State yok
    Verify yok
    Return value önemsiz

Dummy nesne örneği: DummyAuditService
    Hiçbir şey yapmaz
    Testte assert edilmez
    Sadece derlenmesi için vardır
 */
public class DummyTest
{
    @Test
    void calculates_score_without_using_audit_service() {
        // Arrange
        AuditService dummyAuditService = new DummyAuditService();
        UserService service = new UserService(dummyAuditService);

        // Act
        int result = service.calculateScore(10);

        // Assert
        assertEquals(20, result);
    }
}
