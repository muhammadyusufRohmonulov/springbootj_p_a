package com.example.springbootj_p_a.talaba_malumotlari.controller;

import com.example.springbootj_p_a.talaba_malumotlari.Guruh;
import com.example.springbootj_p_a.talaba_malumotlari.Student;
import com.example.springbootj_p_a.talaba_malumotlari.bin.FanlarRepository;
import com.example.springbootj_p_a.talaba_malumotlari.bin.GuruhRepository;
import com.example.springbootj_p_a.talaba_malumotlari.bin.Student_repository;
import com.example.springbootj_p_a.talaba_malumotlari.fakultet_DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/Student")
public class StudentController {
    @Autowired
    Student_repository studentRepository;
    @Autowired
    GuruhRepository guruhRepository;
    @PostMapping
    public String addData(@RequestBody StudentDTO studentDTO){
        Optional<Guruh> optionalGuruh = guruhRepository.findById(studentDTO.getGuruhId());
        if (!optionalGuruh.isPresent()){
            return "Bunday guruh mavjud emas";
        }
        Student student = new Student();
        student.setIsmi(studentDTO.getIsmi());
        student.setFamilyasi(studentDTO.getFamilyasi());
        student.setTelRaqam(studentDTO.getTelRaqam());
        student.setGuruh(optionalGuruh.get());

        if (studentRepository.existsByIdAndGuruhId(student.getId(), studentDTO.getGuruhId())){
            return "Bu talaba ushbu guruhda mavjud";
        }
        studentRepository.save(student);
        return "Malumot joylandi";
    }
}
