package com.example.springbootj_p_a.talaba_malumotlari.bin;

import com.example.springbootj_p_a.talaba_malumotlari.Guruh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruhRepository extends JpaRepository<Guruh,Integer> {
    boolean existsByNomiAndFakultetId(String Nomi,Integer id);
}
