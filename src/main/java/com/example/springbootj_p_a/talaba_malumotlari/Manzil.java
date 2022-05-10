package com.example.springbootj_p_a.talaba_malumotlari;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Manzil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (nullable = false)
    private String viloyat;
    @Column (nullable = false)
    private String tuman;
    @Column (nullable = false)
    private String kocha;

}
