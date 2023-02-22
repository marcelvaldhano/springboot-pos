package com.example.parkingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="kendaraan")

public class KendaraanModel implements Serializable {
    @Id
    @Size(max=12)
    @Column(name = "idKendaraan", nullable = false)
    private String idKendaraan;

    @NotNull
    @Column(name = "jenisKendaraan", nullable = false)
    private String jenisKendaraan;


    @NotNull
    @Column(name = "statusKendaraan", nullable = false)
    private String statusKendaraan;

    @OneToMany(mappedBy = "kendaraan",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<MemberModel> listMember;

    @OneToMany(mappedBy = "kendaraan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<TiketModel> listTiket;


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


    public List<MemberModel> getListMember() {
        return listMember;
    }

    public void setListMember(List<MemberModel> listMember) {
        this.listMember = listMember;
    }

    public List<TiketModel> getListTiket() {
        return listTiket;
    }

    public void setListTiket(List<TiketModel> listTiket) {
        this.listTiket = listTiket;
    }
}
