package com.example.parkingsystem.Controller;
import ch.qos.logback.core.model.Model;
import com.example.parkingsystem.Model.TiketModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckInController {
    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/checkin")
    public String showCheckInForm(Model model) {
        return "checkin";
    }

    @PostMapping("/checkin/add")
    public String addPesananSubmit(
            @ModelAttribute TiketModel tiketModel, Model model
    ) {
        return "checkin";
    }

}