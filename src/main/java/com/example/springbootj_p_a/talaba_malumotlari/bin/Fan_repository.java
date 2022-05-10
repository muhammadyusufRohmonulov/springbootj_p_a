package com.example.springbootj_p_a.talaba_malumotlari.bin;

import com.example.springbootj_p_a.talaba_malumotlari.Fanlar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Fan_repository extends JpaRepository<Fanlar,Integer> {
    boolean existsByFannomi(String fannomi);

}
