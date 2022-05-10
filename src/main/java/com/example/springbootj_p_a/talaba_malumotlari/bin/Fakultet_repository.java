package com.example.springbootj_p_a.talaba_malumotlari.bin;

import com.example.springbootj_p_a.talaba_malumotlari.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Fakultet_repository extends JpaRepository<Fakultet,Integer> {
    boolean existsByNomiAndUniversitetId(String Nomi,Integer id);
    List<Fakultet> findAllByUniversitetId(Integer universitetId);
}
