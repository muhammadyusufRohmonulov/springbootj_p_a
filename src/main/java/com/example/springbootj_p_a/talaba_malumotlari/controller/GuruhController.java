package com.example.springbootj_p_a.talaba_malumotlari.controller;

import com.example.springbootj_p_a.talaba_malumotlari.Fakultet;
import com.example.springbootj_p_a.talaba_malumotlari.Guruh;
import com.example.springbootj_p_a.talaba_malumotlari.bin.GuruhRepository;
import com.example.springbootj_p_a.talaba_malumotlari.bin.Fakultet_repository;
import com.example.springbootj_p_a.talaba_malumotlari.fakultet_DTO.GuruhDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Guruh")
public class GuruhController {
    @Autowired
    GuruhRepository guruhRepository;
    @Autowired
    Fakultet_repository fakultet_repository;
    @PostMapping
    public String addData(@RequestBody GuruhDTO guruhDTO){
        Optional<Fakultet> optionalFakultet = fakultet_repository.findById(guruhDTO.getFakultetId());
        if (!optionalFakultet.isPresent()){
            return "Bunday fakultet mavjud emas";
        }
        Guruh guruh = new Guruh();
        guruh.setNomi(guruhDTO.getGuruhNomi());
        guruh.setFakultet(optionalFakultet.get());
        if (guruhRepository.existsByNomiAndFakultetId(guruhDTO.getGuruhNomi(),guruhDTO.getFakultetId())){
            return "Bunday guruh mavjud";
        }
        guruhRepository.save(guruh);
        return "Malumot joylandi";
    }
    @PutMapping(value = "/{id}")
    public String update(@PathVariable Integer id,@RequestBody GuruhDTO guruhDTO){
        Optional<Guruh> optionalGuruh = guruhRepository.findById(id);
        if (optionalGuruh.isPresent()){
            List<Guruh> guruhList = guruhRepository.findAll();
            for (Guruh i:guruhList) {
                if (i.getNomi().equals(guruhDTO.getGuruhNomi())){
                    return "Fakultetda bunday guruh mavjud";
                }
                Guruh guruh = optionalGuruh.get();
                guruh.setNomi(guruhDTO.getGuruhNomi());
                guruhRepository.save(guruh);
                return "Tahrirlandi";
            }
        }
        return "Malumot topilmadi";
    }
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Guruh> optionalGuruh = guruhRepository.findById(id);
        if (optionalGuruh.isPresent()){
            guruhRepository.deleteById(id);
            fakultet_repository.deleteById(id);
            return "Malumot ochirildi";
        }
        return "Malumot topilmadi";
    }

}
