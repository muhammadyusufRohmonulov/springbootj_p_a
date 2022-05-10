package com.example.springbootj_p_a.talaba_malumotlari.controller;

import com.example.springbootj_p_a.talaba_malumotlari.Manzil;
import com.example.springbootj_p_a.talaba_malumotlari.Universitet;
import com.example.springbootj_p_a.talaba_malumotlari.fakultet_DTO.Universitet_DTO;
import com.example.springbootj_p_a.talaba_malumotlari.bin.Manzil_repository;
import com.example.springbootj_p_a.talaba_malumotlari.bin.Universitet_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Universitet_controller {
    @Autowired
    Universitet_repository universitetRepository;
    @Autowired
    Manzil_repository manzilRepository;
    @RequestMapping(value = "/Universitet",method = RequestMethod.GET)
    public List<Universitet> oqish(){
        List<Universitet> universitetList = universitetRepository.findAll();
        return universitetList;
    }
    @RequestMapping(value = "/Universitet",method = RequestMethod.POST)
    public String addmalumot(@RequestBody Universitet_DTO universitetDto){
        Manzil manzil = new Manzil();
        manzil.setViloyat(universitetDto.getViloyat());
        manzil.setTuman(universitetDto.getTuman());
        manzil.setKocha(universitetDto.getKocha());
        Manzil manzilsaqlash = manzilRepository.save(manzil);
        Universitet universitet = new Universitet();
        universitet.setNomi(universitetDto.getNomi());
        universitet.setManzil(manzilsaqlash);
        universitetRepository.save(universitet);

        return "Malumotlar joylandi";
    }
    @RequestMapping(value = "/Universitet",method = RequestMethod.DELETE)
    public String deletemalumot(@PathVariable Integer id){
        Optional<Universitet> universitetOptional = universitetRepository.findById(id);
        Optional<Manzil> manzilOptional = manzilRepository.findById(id);
        if (universitetOptional.isPresent() && manzilOptional.isPresent()){
            universitetRepository.deleteById(id);
        }
        return null;
    }
    @RequestMapping(value = "/Universitet/{id}",method = RequestMethod.PUT )
    public String update(@RequestBody Universitet_DTO universitetDto,@PathVariable Integer id){
        Optional<Universitet> universitetOptional = universitetRepository.findById(id);
        if (universitetOptional.isPresent()){
            Universitet universitet = new Universitet();
            universitet = universitetOptional.get();
            universitet.setNomi(universitetDto.getNomi());
            universitet.getManzil();
            Manzil manzil = new Manzil();
            manzil = universitet.getManzil();
            manzil.setViloyat(universitetDto.getViloyat());
            manzil.setTuman(universitetDto.getTuman());
            manzil.setKocha(universitetDto.getKocha());
            manzilRepository.save(manzil);
            universitetRepository.save(universitet);
            return "Ma'lumot yangilandi";
        }
        return "Ma'lumot topilmasi";
    }


}
