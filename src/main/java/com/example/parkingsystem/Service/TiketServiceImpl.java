package com.example.parkingsystem.Service;

import com.example.parkingsystem.Model.TiketModel;
import com.example.parkingsystem.repository.TiketDb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TiketServiceImpl implements TiketService{
    @Autowired
    TiketDb tiketDb;
    @Override
    public void addTiket(TiketModel tiket){
        tiketDb.save(tiket);
    }
    @Override
    public void changeStatus(TiketModel tiket){
        tiket.setStatusTiket("PAID");
    }

}
