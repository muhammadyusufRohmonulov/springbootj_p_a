package com.example.springbootj_p_a.talaba_malumotlari.bin;


import com.example.springbootj_p_a.talaba_malumotlari.Universitet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Universitet_repository extends JpaRepository<Universitet,Integer> {

}

