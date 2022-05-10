package com.example.springbootj_p_a.Uzbekistan.binUzb;

import com.example.springbootj_p_a.Uzbekistan.Davlat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DavlatRepository extends JpaRepository<Davlat,Integer> {
    boolean existsByDavlatNomi(String nomi);
}
