package com.testdouble.dummy;

import com.testdouble.AuditService;

public class DummyAuditService implements AuditService {

    @Override
    public void audit(String message) {
        // bilerek boş
    }
}

