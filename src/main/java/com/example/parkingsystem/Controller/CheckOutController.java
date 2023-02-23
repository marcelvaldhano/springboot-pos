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
import java.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class CheckOutController {
    @Autowired
    private TiketDb tiketDb;

    @Autowired
    private KendaraanDb kendaraanDb;

    @Autowired
    private TiketService tiketService;

    @Autowired
    private KendaraanService kendaraanService;
    @GetMapping("/checkout")
    public String showCheckOutForm(Model model) {
        return "checkout";
    }
    @PostMapping("/checkout")
    public String checkTicketInformation(@ModelAttribute KendaraanModel kendaraan,
                                   @RequestParam("platKendaraan") String platKendaraan,
                                   @RequestParam("noTiket") Long noTiket, Model model){
        TiketModel tiket=new TiketModel();
        if(kendaraanService.getKendaraanByIdKendaraan(platKendaraan)==null){
            model.addAttribute("error","Plat Kendaraan Yang Anda Masukkan Tidak Terdaftar");
            return "checkout";
        }
        else if(tiketService.getTiketByKendaraan(kendaraanService.getKendaraanByIdKendaraan(platKendaraan)).getIdTiket()!=noTiket){
            model.addAttribute("error","Tiket Yang Anda Masukkan Tidak Valid");
            return "checkout";
        }
        else if(tiketService.getTiketByKendaraan(kendaraanService.getKendaraanByIdKendaraan(platKendaraan)).getStatusTiket().equals("Paid")){
            model.addAttribute("error","Tiket Yang Anda Masukkan Sudah Terbayar");
            return "checkout";
        }
        else{
            LocalDateTime checkoutTime = LocalDateTime.now();
            LocalDateTime checkinTime=tiketService.getTiketByKendaraan(kendaraanService.getKendaraanByIdKendaraan(platKendaraan)).getCheckInTime();
            Long duration = Duration.between(checkinTime, checkoutTime).toHours();
            Long diskon=0L;
            Long total=duration*3000;
            model.addAttribute("payment","payment");
            model.addAttribute("platKendaraan",platKendaraan);
            model.addAttribute("noTiket",noTiket);
            model.addAttribute("checkinTime",checkinTime);
            model.addAttribute("checkoutTime",checkoutTime);
            model.addAttribute("duration",duration);
            model.addAttribute("diskon",diskon);
            model.addAttribute("total",total);
        }
        return "checkout";
    }
    @PostMapping("/checkoutreceipt")
    public String checkOutTiketReceipt(@RequestParam("platKendaraan") String platKendaraan,
                                       @RequestParam("noTiket") Long noTiket,
                                       @RequestParam("checkinTime") LocalDateTime checkinTime,
                                       @RequestParam("checkoutTime") LocalDateTime checkoutTime,
                                       @RequestParam("duration") Long duration,
                                       @RequestParam("diskon") Long diskon,
                                       @RequestParam("total") Long total,
                                       Model model){
        TiketModel tiket=tiketService.getTiketByIdTiket(noTiket);
        tiket.setCheckOutTime(checkoutTime);
        tiket.setDiskon(diskon);
        tiket.setDuration(duration);
        tiket.setTotal(total);
        tiket.setStatusTiket("Paid");
        tiketService.updateTiket(tiket);
        KendaraanModel newKendaraan=kendaraanService.getKendaraanByIdKendaraan(tiket.getKendaraan().getIdKendaraan());
        newKendaraan.setStatusKendaraan("Free");
        kendaraanService.updateKendaraan(newKendaraan);
        model.addAttribute("platKendaraan",platKendaraan);
        model.addAttribute("noTiket",noTiket);
        model.addAttribute("checkinTime",checkinTime);
        model.addAttribute("checkoutTime",checkoutTime);
        model.addAttribute("duration",duration);
        model.addAttribute("diskon",diskon);
        model.addAttribute("total",total);
        model.addAttribute("status",tiket.getStatusTiket());
        return "checkoutreceipt";
    }

}
