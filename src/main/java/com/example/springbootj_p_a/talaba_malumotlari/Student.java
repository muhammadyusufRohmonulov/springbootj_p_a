package com.example.springbootj_p_a.talaba_malumotlari;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String ismi;
    @Column(nullable = false)
    private String familyasi;
    @Column(nullable = false,unique = true)
    private String telRaqam;
    @ManyToOne
    Guruh guruh;
//    @OneToOne
//    Manzil manzil;
//    @ManyToMany
//    List<Fanlar> fanlarList;
}
