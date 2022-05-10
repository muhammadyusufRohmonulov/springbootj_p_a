package com.example.springbootj_p_a.talaba_malumotlari.bin;

import com.example.springbootj_p_a.talaba_malumotlari.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Student_repository extends JpaRepository<Student,Integer> {
//    boolean existsByIsmAndFamilyasiAndTel_raqamAndGuruhId(String ismi,String familyasi,String telRaqam,Integer guruhId);
    boolean existsByIdAndGuruhId(Integer id,Integer id2);
}
