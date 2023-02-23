package com.example.parkingsystem.repository;

import com.example.parkingsystem.Model.KendaraanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KendaraanDb extends JpaRepository<KendaraanModel,Long> {
    KendaraanModel findByIdKendaraan(String idKendaraan);
}
