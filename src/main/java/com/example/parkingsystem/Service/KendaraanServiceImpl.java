package com.example.parkingsystem.Service;

import com.example.parkingsystem.Model.KendaraanModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parkingsystem.repository.KendaraanDb;

@Service
@Transactional
public class KendaraanServiceImpl implements KendaraanService{
    @Autowired
    KendaraanDb kendaraanDb;
    @Override
    public KendaraanModel getKendaraanByIdKendaraan(String idKendaraan){
        return kendaraanDb.findByIdKendaraan(idKendaraan);
    }

    @Override
    public void addKendaraan(KendaraanModel kendaraanModel) {
        kendaraanDb.save(kendaraanModel);

    }
}
