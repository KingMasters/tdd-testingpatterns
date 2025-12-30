package com.designpattern.imposter;

class UserService {

    private final EmailSender emailSender;

    UserService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    void register(String email) {
        // burada normalde kullanıcı kaydı yapılır
        // (DB, validation vs. şimdilik yok — TDD’de YAGNI)

        sendWelcomeEmail(email);
    }

    private void sendWelcomeEmail(String email) {
        emailSender.send(email, "Welcome!");
    }
}
