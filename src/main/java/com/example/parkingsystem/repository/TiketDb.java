package com.example.parkingsystem.repository;

import com.example.parkingsystem.Model.KendaraanModel;
import com.example.parkingsystem.Model.TiketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiketDb extends JpaRepository<TiketModel,Long> {
    TiketModel findByIdTiket(Long idTiket);
    TiketModel getTiketByKendaraan(KendaraanModel kendaraanModel);
}
