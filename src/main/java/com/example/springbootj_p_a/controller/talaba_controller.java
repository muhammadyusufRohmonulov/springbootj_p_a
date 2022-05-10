package com.example.springbootj_p_a.controller;

import com.example.springbootj_p_a.bin_qilish.Talaba_repository;
import com.example.springbootj_p_a.model.Talaba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class talaba_controller {
    @Autowired
    Talaba_repository talaba_repository;
    @RequestMapping(value = "/Talaba",method = RequestMethod.GET)
    public List <Talaba> malumot(){
        List<Talaba> talabaList = talaba_repository.findAll();
        return talabaList;
    }
    @RequestMapping(value = "/Talaba",method = RequestMethod.POST)
    public String Joylash(@RequestBody Talaba talaba){
        talaba_repository.save(talaba);
        return "Malumot joylandi";
    }
    @RequestMapping(value = "/Talaba/{id}",method = RequestMethod.GET)
    public Talaba olish(@PathVariable Integer id){
        Optional<Talaba> optionalTalaba = talaba_repository.findById(id);
        if (optionalTalaba.isPresent()){
            Talaba talaba = optionalTalaba.get();
            return talaba;
        }
        return new Talaba();
    }
    @RequestMapping(value = "/Talaba/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id){
        Optional<Talaba> optionalTalaba = talaba_repository.findById(id);
        if (optionalTalaba.isPresent()){
            talaba_repository.deleteById(id);
            return "Malumot o'chirildi";
        }
        return "Malumot topilmadi";
    }
    @RequestMapping(value = "/Talaba/{id}",method = RequestMethod.PUT)
    public String update(@PathVariable Integer id,@RequestBody Talaba t){
        boolean holat = false;
        Optional<Talaba> optionalTalaba = talaba_repository.findById(id);

        if (optionalTalaba.isPresent()){
            Talaba talaba = optionalTalaba.get();
            talaba.setIsm(t.getIsm());
            talaba.setFamilyasi(t.getFamilyasi());
            talaba.setTel_raqam(t.getTel_raqam());
            talaba_repository.save(talaba);
            holat = true;
        }
         return holat ? "Malumot yangilandi" : "Malumot topilmadi";
    }
}
