package com.example.springbootj_p_a.Uzbekistan.controllerUzb;

import com.example.springbootj_p_a.Uzbekistan.DTO.TumanDTO;
import com.example.springbootj_p_a.Uzbekistan.Tuman;
import com.example.springbootj_p_a.Uzbekistan.Viloyat;
import com.example.springbootj_p_a.Uzbekistan.binUzb.TumanRepository;
import com.example.springbootj_p_a.Uzbekistan.binUzb.ViloyatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Tuman")
public class TumanController {
    @Autowired
   TumanRepository tumanRepository;
    @Autowired
    ViloyatRepository viloyatRepository;
    @PostMapping
    public String addData(@RequestBody TumanDTO tumanDTO){
        Optional<Viloyat> optionalViloyat = viloyatRepository.findById(tumanDTO.getViloyatId());
        if (!optionalViloyat.isPresent()){
            return "Bunday Viloyat Mavjud Emas";
        }
        Tuman tuman = new Tuman();
        tuman.setTumanNomi(tumanDTO.getTumanNomi());
        tuman.setViloyat(optionalViloyat.get());
        if (tumanRepository.existsByTumanNomi(tuman.getTumanNomi())){
            return "Bunday Tuman Mavjud";
        }
        tumanRepository.save(tuman);
        return "Malumot Joylnadi";
    }
    @GetMapping(value = "/oqish/{id}")
    List<Tuman> tumanView(@PathVariable Integer id){
        List<Tuman> tumanList = tumanRepository.findAllByViloyatId(id);
        return tumanList;
    }
}
