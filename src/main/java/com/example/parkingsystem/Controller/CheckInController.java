package com.example.parkingsystem.Controller;
//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import com.example.parkingsystem.Model.KendaraanModel;
import com.example.parkingsystem.Model.TiketModel;
import com.example.parkingsystem.Service.KendaraanService;
import com.example.parkingsystem.Service.TiketService;
import com.example.parkingsystem.repository.KendaraanDb;
import com.example.parkingsystem.repository.TiketDb;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class CheckInController {
    @Autowired
    private TiketDb tiketDb;

    @Autowired
    private KendaraanDb kendaraanDb;

    @Autowired
    private TiketService tiketService;

    @Autowired
    private KendaraanService kendaraanService;

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/checkin")
    public String showCheckInForm(Model model) {
        return "checkin";
    }

    @PostMapping("/checkin/add")
    public String addCheckin(@ModelAttribute TiketModel tiket,
            @RequestParam("platKendaraan") String platKendaraan,
                             @RequestParam("jenisKendaraan") String jenisKendaraan, Model model){
        KendaraanModel kendaraanModel=new KendaraanModel();
        if(kendaraanService.getKendaraanByIdKendaraan(platKendaraan)==null){
            kendaraanModel.setIdKendaraan(platKendaraan);
            kendaraanModel.setJenisKendaraan(jenisKendaraan);
            kendaraanModel.setStatusKendaraan("On Going");
            kendaraanService.addKendaraan(kendaraanModel);
            LocalDateTime checkinTime = LocalDateTime.now();
            tiket.setStatusTiket("Unpaid");
            tiket.setCheckInTime(checkinTime);
            tiket.setKendaraan(kendaraanModel);
            tiketService.addTiket(tiket);
            model.addAttribute("idTiket",tiket.getIdTiket());
            return "checkinsuccess";
        }
        else if(kendaraanService.getKendaraanByIdKendaraan(platKendaraan)!=null){
            if(kendaraanService.getKendaraanByIdKendaraan(platKendaraan).getStatusKendaraan().equals("On Going")){
                model.addAttribute("error","Maaf plat yang anda masukkan masih dalam status on going");
            }
            else{
                KendaraanModel kendaraanUpdated=kendaraanService.getKendaraanByIdKendaraan(platKendaraan);
                LocalDateTime checkinTime = LocalDateTime.now();
                kendaraanUpdated.setStatusKendaraan("On Going");
                kendaraanService.updateKendaraan(kendaraanUpdated);
                tiket.setStatusTiket("Unpaid");
                tiket.setCheckInTime(checkinTime);
                tiket.setKendaraan(kendaraanUpdated);
                tiketService.addTiket(tiket);
                model.addAttribute("idTiket",tiket.getIdTiket());
                return "checkinsuccess";
            }
        }
        return "checkin";
    }

}