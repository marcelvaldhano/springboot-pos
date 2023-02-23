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
        }

        return "checkout";
    }
}
