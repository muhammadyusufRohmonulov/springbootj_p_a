package com.example.springbootj_p_a.Uzbekistan.controllerUzb;

import com.example.springbootj_p_a.Uzbekistan.DTO.DavlatDTO;
import com.example.springbootj_p_a.Uzbekistan.Davlat;
import com.example.springbootj_p_a.Uzbekistan.Qita;
import com.example.springbootj_p_a.Uzbekistan.binUzb.DavlatRepository;
import com.example.springbootj_p_a.Uzbekistan.binUzb.QitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/Davlat")
public class DavlatController {
    @Autowired
    DavlatRepository davlatRepository;
    @Autowired
    QitaRepository qitaRepository;
    @PostMapping
    public String addData(@RequestBody DavlatDTO davlatDTO){
        Optional<Qita> optionalQita = qitaRepository.findById(davlatDTO.getQitaId());
        if (!optionalQita.isPresent()){
            return "Bunday Nomli Qita Mavjud Emas";
        }
        Davlat davlat = new Davlat();
        davlat.setDavlatNomi(davlatDTO.getDavlatNomi());
        davlat.setQita(optionalQita.get());
        if (davlatRepository.existsByDavlatNomi(davlat.getDavlatNomi())){
            return "Bunday Davlat Mavjud";
        }
        davlatRepository.save(davlat);
        return "Malumot joylandi";
    }
}
