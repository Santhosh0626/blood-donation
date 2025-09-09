package com.example.blooddonation;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/donors")
public class DonorController {
    private final DonorService donorService;
    public DonorController(DonorService donorService) { this.donorService = donorService; }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("donors", donorService.getAll());
        return "donors-list";
    }

    @GetMapping("/register")
    public String regForm(Model model) {
        model.addAttribute("donor", new Donor());
        return "register-donor";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Donor donor, Model model) {
        donorService.saveDonor(donor);
        model.addAttribute("message", "Donor registered successfully");
        return "register-donor";
    }

    @GetMapping("/search")
    public String searchForm() { return "search-donor"; }

    @PostMapping("/search")
    public String search(@RequestParam(required=false) String bloodGroup,
                         @RequestParam(required=false) String location,
                         Model model) {
        model.addAttribute("donors", donorService.search(bloodGroup, location));
        return "donors-list";
    }

    @GetMapping("/record-donation")
    public String recForm() { return "record-donation"; }

    @PostMapping("/record-donation")
    public String record(@RequestParam Long donorId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                         Model model) {
        donorService.recordDonation(donorId, date);
        model.addAttribute("message", "Donation recorded");
        return "record-donation";
    }
}
