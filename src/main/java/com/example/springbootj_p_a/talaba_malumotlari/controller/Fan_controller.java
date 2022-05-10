package com.example.springbootj_p_a.talaba_malumotlari.controller;

import com.example.springbootj_p_a.talaba_malumotlari.Fanlar;
import com.example.springbootj_p_a.talaba_malumotlari.bin.Fan_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
//@RequestMapping(value = "/Fanlar")
public class Fan_controller {
    @Autowired
    Fan_repository fan_repository;
    @RequestMapping(value = "/fanlar",method = RequestMethod.POST)
//    @PostMapping
    public String addmalumot(@RequestBody Fanlar fanlar){
        boolean holat = fan_repository.existsByFannomi(fanlar.getFannomi());
        if (!holat){
            fan_repository.save(fanlar);
            return "Malumot joylandi";
        }
        return "Bunday fan mavjud";
    }


    @RequestMapping(value = "/Fanlar",method = RequestMethod.GET)
    public List<Fanlar> oqish2(){
        List<Fanlar> fanlarList = fan_repository.findAll();
        return fanlarList;
    }
    @RequestMapping(value = "fanlar/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id){
        Optional<Fanlar> optionalFanlar = fan_repository.findById(id);
        if (optionalFanlar.isPresent()){
            fan_repository.deleteById(id);
            return "Malumot o'chirildi";
        }
        return "Malumot topilmadi";
    }
    @RequestMapping(value = "fanup/{id}" , method = RequestMethod.PUT)
    public String update(@PathVariable Integer id,@RequestBody Fanlar f){
        Optional<Fanlar> fanlarOptional = fan_repository.findById(id);
        boolean holat = fan_repository.existsByFannomi(f.getFannomi());
        if (holat){
            if (fanlarOptional.isPresent()){
                Fanlar fanlar = fanlarOptional.get();
                fanlar.setFannomi(f.getFannomi());
                fan_repository.save(fanlar);
                return "Malumot tahrirlandi";
            }
        }
        return "Malumot topilmadi";
    }
}
