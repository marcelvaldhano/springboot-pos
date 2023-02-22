package com.example.parkingsystem.repository;
import com.example.parkingsystem.Model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDb extends JpaRepository<MemberModel,Long>{
}
