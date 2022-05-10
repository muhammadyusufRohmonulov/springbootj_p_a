package com.example.springbootj_p_a.Uzbekistan.controllerUzb;

import com.example.springbootj_p_a.Uzbekistan.DTO.ViloyatDTO;
import com.example.springbootj_p_a.Uzbekistan.Davlat;
import com.example.springbootj_p_a.Uzbekistan.Tuman;
import com.example.springbootj_p_a.Uzbekistan.Viloyat;
import com.example.springbootj_p_a.Uzbekistan.binUzb.DavlatRepository;
import com.example.springbootj_p_a.Uzbekistan.binUzb.TumanRepository;
import com.example.springbootj_p_a.Uzbekistan.binUzb.ViloyatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/viloyat")
public class ViloyatController {
    @Autowired
    ViloyatRepository viloyatRepository;
    @Autowired
    DavlatRepository davlatRepository;
    @PostMapping
    public String addData(@RequestBody ViloyatDTO viloyatDTO){
        Optional<Davlat> optionalDavlat = davlatRepository.findById(viloyatDTO.getDavlatID());
        if (!optionalDavlat.isPresent()){
            return "Bunday ma'lumot mavjud";
        }
        Viloyat viloyat1 = new Viloyat();
        viloyat1.setViloyatNomi(viloyatDTO.getViloyatNomi());
        viloyat1.setDavlat(optionalDavlat.get());
        if (viloyatRepository.existsByViloyatNomi(viloyat1.getViloyatNomi())){
            return "Bunday Viloyat Mavjud";
        }
        viloyatRepository.save(viloyat1);
        return "Malumot Joylandi";
    }
    @PutMapping(value = "/{id}")
    public String update(@PathVariable Integer id , @RequestBody Viloyat viloyat){
        Optional<Viloyat> optionalViloyat = viloyatRepository.findById(id);
        boolean holat = viloyatRepository.existsByViloyatNomi(viloyat.getViloyatNomi());
        if (holat){
            if (optionalViloyat.isPresent()){
                Viloyat viloyat1 = optionalViloyat.get();
                viloyat1.setViloyatNomi(viloyat.getViloyatNomi());
                viloyatRepository.save(viloyat1);
                return "Malumot tahrirlandi";
            }
        }
        return "Malumot topilmadi";
    }
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Viloyat> optionalViloyat = viloyatRepository.findById(id);
        if (optionalViloyat.isPresent()){
            viloyatRepository.deleteById(id);
            return "Malumot o'chirildi";
        }
        return "Malumot topilmadi";
    }
}
