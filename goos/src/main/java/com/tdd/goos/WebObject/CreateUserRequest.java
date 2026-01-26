package com.tdd.goos.WebObject;

import javax.servlet.http.HttpServletRequest;

class CreateUserRequest {
    private final String email;

    CreateUserRequest(HttpServletRequest request) {
        this.email = request.getParameter("email");
    }

    String email() {
        return email;
    }
}

