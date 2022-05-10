package com.example.springbootj_p_a.Uzbekistan.controllerUzb;

import com.example.springbootj_p_a.Uzbekistan.DTO.MahallaDTO;
import com.example.springbootj_p_a.Uzbekistan.Mahalla;
import com.example.springbootj_p_a.Uzbekistan.Tuman;
import com.example.springbootj_p_a.Uzbekistan.binUzb.MahallaRepository;
import com.example.springbootj_p_a.Uzbekistan.binUzb.TumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Mahalla")
public class MahallaController {
    @Autowired
    MahallaRepository mahallaRepository;
    @Autowired
    TumanRepository tumanRepository;
    @PostMapping
    public String addData(@RequestBody MahallaDTO mahallaDTO){
        Optional<Tuman> optionalTuman = tumanRepository.findById(mahallaDTO.getTumanId());
        if (!optionalTuman.isPresent()){
            return "Bunday Tuman Mavjud Emas";
        }
        Mahalla mahalla = new Mahalla();
        mahalla.setMahallaNomi(mahallaDTO.getMahallaNomi());
        mahalla.setTuman(optionalTuman.get());
        if (mahallaRepository.existsByMahallaNomi(mahallaDTO.getMahallaNomi())){
            return "Bunday Mahalla Nomi Mavjud";
        }
        mahallaRepository.save(mahalla);
        return "Malumot joylandi";
    }
    @GetMapping(value = "/oqish/{id}")
    List<Mahalla> mahallaView (@PathVariable Integer id){
        List<Mahalla> mahallaList = mahallaRepository.findAllByTumanId(id);
        return mahallaList;
    }
}
