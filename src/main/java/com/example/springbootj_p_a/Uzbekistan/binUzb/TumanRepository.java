package com.example.springbootj_p_a.Uzbekistan.binUzb;

import com.example.springbootj_p_a.Uzbekistan.Tuman;
import com.example.springbootj_p_a.Uzbekistan.Viloyat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TumanRepository extends JpaRepository<Tuman,Integer> {
    boolean existsByTumanNomi(String nomi);
    List<Tuman> findAllByViloyatId(Integer viloyatId);
}
