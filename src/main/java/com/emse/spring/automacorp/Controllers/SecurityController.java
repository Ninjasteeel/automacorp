package com.emse.spring.automacorp.Controllers;
/*
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@CrossOrigin(origins = { "http://localhost:5173" }, maxAge = 3600)
@RestController
@RequestMapping("/api/admin/users")
public class SecurityController {
    private static Logger logger = LogManager.getLogger(SecurityController.class);
    public record User(String username) {}

    @GetMapping(path = "/me")
    public User findUserName(@AuthenticationPrincipal UserDetails userDetails) {
        return new User(userDetails.getUsername());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/api/admin/users/admin-only")
    public String adminOnlyEndpoint() {
        return "Accessible only to Admin";
    }
}
*/