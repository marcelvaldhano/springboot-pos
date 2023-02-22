package com.example.parkingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="member")
public class MemberModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMember", nullable = false)
    private Long idMember;

    @NotNull
    @Size(max = 255)
    @Column(name = "namaMember", nullable = false)
    private String namaMember;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiredDate", nullable = false)
    private Date expireDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_kendaraan",referencedColumnName = "idKendaraan")
    @OnDelete(action=OnDeleteAction.CASCADE)
    @Size(max=12)
    private KendaraanModel kendaraan;


    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
    }

    public String getNamaMember() {
        return namaMember;
    }

    public void setNamaMember(String namaMember) {
        this.namaMember = namaMember;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public KendaraanModel getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(KendaraanModel kendaraan) {
        this.kendaraan = kendaraan;
    }
}
