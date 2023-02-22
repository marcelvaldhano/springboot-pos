package com.example.parkingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "namaMember", nullable = false)
    private String namaMember;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiredDate", nullable = false)
    private Date expiredDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "platKendaraan", referencedColumnName = "idKendaraan")
    @Size(max=8)
    private KendaraanModel platKendaraan;
}
