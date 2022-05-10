package com.example.springbootj_p_a.Uzbekistan.controllerUzb;

import com.example.springbootj_p_a.Uzbekistan.Qita;
import com.example.springbootj_p_a.Uzbekistan.Viloyat;
import com.example.springbootj_p_a.Uzbekistan.binUzb.QitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.OpenOption;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Qita")
public class QitaController {
    @Autowired
    QitaRepository qitaRepository;
    @PostMapping
    public String addData(@RequestBody Qita qita){
        if (qitaRepository.existsByQitaNomi(qita.getQitaNomi())){
            return "Bunday Qita Mavjud";
        }
        Qita qita1 = new Qita();
        qita1.setQitaNomi(qita.getQitaNomi());
        if (qitaRepository.existsByQitaNomi(qita1.getQitaNomi())){
            return "Bunday Qita Mavjud";
        }
        qitaRepository.save(qita1);
        return "Malumot Joylandi";
    }
}
