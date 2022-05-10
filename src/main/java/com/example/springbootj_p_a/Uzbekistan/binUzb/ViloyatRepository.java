package com.example.springbootj_p_a.Uzbekistan.binUzb;

import com.example.springbootj_p_a.Uzbekistan.Viloyat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViloyatRepository extends JpaRepository<Viloyat,Integer> {
  boolean existsByViloyatNomi(String nomi);

}
