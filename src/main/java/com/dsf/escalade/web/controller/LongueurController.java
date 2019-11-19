package com.dsf.escalade.web.controller;

import com.dsf.escalade.repository.metier.LongueurRepository;
import com.dsf.escalade.model.metier.Longueur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class LongueurController {
   @Autowired
   private LongueurRepository longueurRepository;

   @GetMapping(value = "/Longueur")
   public List<Longueur> displayAllSite(){

      return longueurRepository.findAll();
   }


}
