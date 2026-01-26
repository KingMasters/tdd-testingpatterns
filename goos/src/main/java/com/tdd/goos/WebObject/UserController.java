// File currently empty; replace with a valid Java class so IDE recognizes it as a class
package com.tdd.goos.WebObject;

import javax.servlet.http.HttpServletRequest;
import com.tdd.goos.service.UserService;

/*
GOOS’a göre bir Web Object:
    ✔ HTTP detaylarını saklar
    ✔ Domain’e temiz bir API sunar
    ✔ Testi kolaydır
    ✔ Framework’ü izole eder
    ✔ Adapter / Boundary rolündedir
Ama:
    ❌ Business logic içermez
    ❌ Domain rule bilmez
    ❌ Validation çoğunlukla “shape validation”dır (boş mu, format doğru mu)

 */
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Kötü : Web sizintisi var
    public void createUser(HttpServletRequest request){
        String email = request.getParameter("email");
        userService.create(email);
    }

    // Doğrusu : Web Object kullanımı <CreateUserRequest>
    public void createUser(CreateUserRequest request) {
        userService.create(request.email());
    }


}
