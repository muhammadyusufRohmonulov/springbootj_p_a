package com.example.springbootj_p_a.talaba_malumotlari.controller;

import com.example.springbootj_p_a.talaba_malumotlari.Fakultet;
import com.example.springbootj_p_a.talaba_malumotlari.Universitet;
import com.example.springbootj_p_a.talaba_malumotlari.bin.Universitet_repository;
import com.example.springbootj_p_a.talaba_malumotlari.bin.Fakultet_repository;
import com.example.springbootj_p_a.talaba_malumotlari.fakultet_DTO.Fakultet_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/Fakultet")
public class Fakuktet_controller {
    @Autowired
    Fakultet_repository fakultetRepository;
    @Autowired
    Universitet_repository universitetRepository;
//    @RequestMapping(value = "/Fakultet",method = RequestMethod.POST)
    @PostMapping
    public String adddata(@RequestBody Fakultet_DTO fakultetDto){
        Optional<Universitet> optionalUniversitet = universitetRepository.findById(fakultetDto.getUniversitetId());
        if (!optionalUniversitet.isPresent()){
            return "Bunday universitet mavjud emas";
        }
        Fakultet fakultet = new Fakultet();
        fakultet.setNomi(fakultetDto.getNomi());
        fakultet.setUniversitet(optionalUniversitet.get());
        if (fakultetRepository.existsByNomiAndUniversitetId(fakultetDto.getNomi(),fakultetDto.getUniversitetId())){
            return "Bunday malumot mavjud";
        }
        fakultetRepository.save(fakultet);
        return "Malumot joylandi";
    }
    @RequestMapping(value = "/Fakultet/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id){
        Optional<Fakultet> optionalFakultet = fakultetRepository.findById(id);
        if (optionalFakultet.isPresent()){
            fakultetRepository.deleteById(id);
            universitetRepository.deleteById(id);
            return "Malumot ochirildi";
        }
        return "Malumot topilmadi";
    }
    @RequestMapping(value = "/Fakultet/{id}",method = RequestMethod.PUT)
    public String update_fakultet(@PathVariable Integer id , @RequestBody Fakultet_DTO fakultetDto){
        Optional<Fakultet> optionalFakultet = fakultetRepository.findById(id);
        if (optionalFakultet.isPresent()){
            List<Fakultet> fakultets = fakultetRepository.findAll();
            for (Fakultet i: fakultets) {
                if (i.getNomi().equals(fakultetDto.getNomi())){
                    return "Universitetda bunday fakultet mavjud";
                }
                Fakultet fakultet = optionalFakultet.get();
                fakultet.setNomi(fakultetDto.getNomi());
                fakultetRepository.save(fakultet);
                return "Tahrirlandi";

            }
        }
        return "Bunday malumot topilmadi";
    }
    @GetMapping(value = "/oqish/{id}")
    public List<Fakultet> oqish(@PathVariable Integer id){
        List<Fakultet> fakultets = fakultetRepository.findAllByUniversitetId(id);

//        Optional<Universitet> optionalUniversitet = universitetRepository.findById(id);
//        if (optionalUniversitet.isPresent()){
//            Fakultet fakultet = new Fakultet();
//            if (universitet.getId().equals(id)){
//                return fakultets;
//            }
//        }
        return fakultets;
    }
}
