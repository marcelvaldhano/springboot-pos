package com.example.parkingsystem.Service;

import com.example.parkingsystem.Model.*;
import java.util.List;

public interface TiketService {
    void addTiket(TiketModel tiket);
//    TiketModel updateTiket(TiketModel tiket);
//    TiketModel getTiketByIdTiket(Long idTiket);
    void changeStatus(TiketModel tiket);
    TiketModel updateTiket(TiketModel tiket);
    TiketModel getTiketByIdTiket(Long noTiket);
    TiketModel getTiketByKendaraan(KendaraanModel kendaraanModel);


}
