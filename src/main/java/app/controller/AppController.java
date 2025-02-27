package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "CONTACTS COMPANY");
        model.addAttribute("fragmentName", "home");
        return "layout";
    }
}
