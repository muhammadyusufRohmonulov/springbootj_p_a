package com.example.springbootj_p_a.Uzbekistan.binUzb;

import com.example.springbootj_p_a.Uzbekistan.Qita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QitaRepository extends JpaRepository<Qita , Integer> {
    boolean existsByQitaNomi(String qitaNomi);
}
