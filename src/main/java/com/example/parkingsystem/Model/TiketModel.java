package com.example.parkingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="tiket")
public class TiketModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tiket", nullable = false)
    private Long idTiket;

    @NotNull
    @Column(name = "checkInTime", nullable = false)
    private LocalDateTime checkInTime;

    @Column(name = "checkOutTime")
    private LocalDateTime checkOutTime;

    @Column(name = "duration")
    private long duration;

    @Column(name = "diskon")
    private long diskon;

    @Column(name = "metodePembayaran")
    private String metodePembayaran;

    @NotNull
    @Column(name = "statusTiket",nullable = false)
    private String statusTiket;

    @Column(name = "total")
    private long total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plat_kendaraan", referencedColumnName = "idKendaraan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KendaraanModel kendaraan;

    public Long getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(Long idTiket) {
        this.idTiket = idTiket;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public long getDuration() {
        return duration;
    }

    public long getDiskon() {
        return diskon;
    }

    public void setDiskon(long diskon) {
        this.diskon = diskon;
    }

    public String getStatusTiket() {
        return statusTiket;
    }

    public void setStatusTiket(String statusTiket) {
        this.statusTiket = statusTiket;
    }

    public long getTotal() {
        return total;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public KendaraanModel getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(KendaraanModel kendaraan) {
        this.kendaraan = kendaraan;
    }

    private void setDuration(LocalDateTime checkInTime, LocalDate checkOutTime) {
        this.duration = Duration.between(checkInTime, checkOutTime).toHours();
    }
    private void setTotal(Long duration,Long diskon){
        this.total=duration*3000*(100-diskon);
    }


}
