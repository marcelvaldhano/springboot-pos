package com.example.parkingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
@Entity
@Table(name="kendaraan")

public class KendaraanModel implements Serializable {
    @Id
    @Size(max=8)
    @Column(name = "idKendaraan", nullable = false)
    private String idKendaraan;

    @NotNull
    @Column(name = "jenisKendaraan", nullable = false)
    private String jenisKendaraan;

    @NotNull
    @Column(name = "statusKendaraan", nullable = false)
    private String statusKendaraan;


    @OneToOne(mappedBy = "platKendaraan")
    private MemberModel platKendaraan;


    public String getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getStatusKendaraan() {
        return statusKendaraan;
    }

    public void setStatusKendaraan(String statusKendaraan) {
        this.statusKendaraan = statusKendaraan;
    }
}
