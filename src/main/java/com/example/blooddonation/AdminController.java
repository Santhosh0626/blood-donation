package com.example.blooddonation;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private static final String USER = "admin";
    private static final String PASS = "admin123"; // same as your Admin.java

    @GetMapping({"/", "/login"})
    public String loginPage() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (USER.equals(username) && PASS.equals(password)) {
            session.setAttribute("admin", true);
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() { return "dashboard"; }
}
