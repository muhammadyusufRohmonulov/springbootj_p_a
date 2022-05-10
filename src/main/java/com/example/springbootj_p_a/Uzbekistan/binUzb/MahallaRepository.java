package com.example.springbootj_p_a.Uzbekistan.binUzb;

import com.example.springbootj_p_a.Uzbekistan.Mahalla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MahallaRepository extends JpaRepository<Mahalla,Integer> {
    boolean existsByMahallaNomi(String nomi);
    List<Mahalla> findAllByTumanId(Integer tumId);
}
