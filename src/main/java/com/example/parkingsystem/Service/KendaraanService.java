package com.example.parkingsystem.Service;

import com.example.parkingsystem.Model.KendaraanModel;

public interface KendaraanService {
    KendaraanModel getKendaraanByIdKendaraan(String idKendaraan);
    void addKendaraan(KendaraanModel kendaraanModel);
}
