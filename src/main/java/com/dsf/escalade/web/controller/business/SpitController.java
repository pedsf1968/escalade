package com.dsf.escalade.web.controller.business;

import com.dsf.escalade.service.business.LongueurService;
import com.dsf.escalade.service.business.SiteService;
import com.dsf.escalade.service.business.SpitService;
import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.LongueurDto;
import com.dsf.escalade.web.dto.SpitDto;
import com.dsf.escalade.web.dto.SpitDtoList;
import com.dsf.escalade.web.dto.VoieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class SpitController {
   private final SiteService siteService;
   private final VoieService voieService;
   private final LongueurService longueurService;
   private final SpitService spitService;

   public SpitController(SiteService siteService, VoieService voieService, LongueurService longueurService, SpitService spitService) {
      this.siteService = siteService;
      this.voieService = voieService;
      this.longueurService = longueurService;
      this.spitService = spitService;
   }


   @GetMapping("/spit/add/{longueurId}")
   public String addSpit(@PathVariable("longueurId") Integer longueurId, Model model) {
      LongueurDto longueurDto = longueurService.getOne(longueurId);
      VoieDto voieDto = voieService.getOne(longueurDto.getVoieId());
      Integer topoId = siteService.getTopoId(voieDto.getParentId());
      Integer number = spitService.getLastSpitNumber(topoId);

      SpitDto spitDto = new SpitDto();
      spitDto.setTopoId(topoId);
      spitDto.setNumber(++number);
      spitDto.setVoieId(voieDto.getId());
      spitDto.setLongueurId(longueurId);

      spitService.save(spitDto);

      return PathTable.LONGUEUR_UPDATE_R + longueurId;
   }

   @PostMapping("/spit/update")
   public String updateSpit(@ModelAttribute("LongueurId") Integer longueurId, @ModelAttribute("SpitDtoList") SpitDtoList spitDtoList, Model model){


      spitService.saveAll(spitDtoList);

      return PathTable.LONGUEUR_UPDATE_R + longueurId;
   }


   @GetMapping("/spit/delete/{longueurId}/{number}")
   public String deleteSpit(@PathVariable("longueurId") Integer longueurId, @PathVariable("number") Integer number,Model model) {
      LongueurDto longueurDto = longueurService.getOne(longueurId);
      VoieDto voieDto = voieService.getOne(longueurDto.getVoieId());
      Integer topoId = siteService.getTopoId(voieDto.getParentId());
      SpitDto spitDto = spitService.getOne( topoId, number);

      spitService.delete(spitDto);

      return PathTable.LONGUEUR_UPDATE_R + longueurId;
   }


}
