package com.testdouble.dummy;

import com.testdouble.AuditService;

public class UserService {

    private final AuditService auditService;

    public UserService(AuditService auditService) {
        this.auditService = auditService;
    }

    public int calculateScore(int value) {
        return value * 2;
    }
}

