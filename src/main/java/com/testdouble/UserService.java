package com.testdouble;

public class UserService {

    private final UserRepository repository;
    private final EmailService emailService;

    public UserService(UserRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public void register(String id, String name, String email) {
        User user = new User(id, name, email);
        repository.save(user);
        emailService.sendWelcomeEmail(email);
    }

    public User getUser(String id) {
        return repository.findById(id);
    }
}

