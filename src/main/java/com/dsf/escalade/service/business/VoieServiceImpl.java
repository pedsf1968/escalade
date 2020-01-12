package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.Voie;
import com.dsf.escalade.repository.business.VoieRepository;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.dto.UserDto;
import com.dsf.escalade.web.dto.VoieDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("VoieService")
public class VoieServiceImpl implements VoieService {
   private final SiteService siteService;
   private final TopoService topoService;
   private final VoieRepository voieRepository;
   private final UserService userService;

   public VoieServiceImpl(SiteService siteService, TopoService topoService, VoieRepository voieRepository, UserService userService) {
      this.siteService = siteService;
      this.topoService = topoService;
      this.voieRepository = voieRepository;
      this.userService = userService;
   }


   @Override
   public VoieDto getOne(Integer id) {
      Voie voie = voieRepository.getOne(id);
      VoieDto voieDto = new VoieDto();

      voieDto.setId(voie.getId());
      voieDto.setName(voie.getName());
      voieDto.setLatitude(voie.getLatitude());
      voieDto.setLongitude(voie.getLongitude());
      voieDto.setParentId(voie.getParentId());
      voieDto.setPhotoLink(voie.getPhotoLink());
      voieDto.setMapLink(voie.getMapLink());
      voieDto.setNbComment(voie.getNbComment());
      voieDto.setCotationId(voie.getCotationId());
      voieDto.setHeigth(voie.getHeigth());
      voieDto.setIsEquipped(voie.getIsEquipped());

      if (voie.getManagerId() != null) {
         voieDto.setAliasManager(userService.getOne(voie.getManagerId()).getAlias());
      }

      return voieDto;
   }

   @Override
   public List<VoieDto> findByParentId(Integer parentId) {
      List<VoieDto> voieDtos = new ArrayList<>();
      VoieDto voieDto = null;

      for(Voie voie : voieRepository.findByParentId(parentId)){
         voieDto = new VoieDto();

         voieDto.setId(voie.getId());
         voieDto.setName(voie.getName());
         voieDto.setLatitude(voie.getLatitude());
         voieDto.setLongitude(voie.getLongitude());
         voieDto.setParentId(voie.getParentId());
         voieDto.setPhotoLink(voie.getPhotoLink());
         voieDto.setMapLink(voie.getMapLink());
         voieDto.setNbComment(voie.getNbComment());
         voieDto.setCotationId(voie.getCotationId());
         voieDto.setHeigth(voie.getHeigth());
         voieDto.setIsEquipped(voie.getIsEquipped());

         if (voie.getManagerId() != null) {
            voieDto.setAliasManager(userService.getOne(voie.getManagerId()).getAlias());
         }

         voieDtos.add(voieDto);
      }

      return voieDtos;
   }

   @Override
   public Integer save(VoieDto voieDto) {
      Voie voie = new Voie();

      // if new Lane we increase Topo lane counter
      if(voieDto.getId() == null){
         topoService.increaseLaneCounter(siteService.getTopoId(voieDto.getParentId()));
      }

      voie.setType(SiteType.VOIE);
      voie.setId(voieDto.getId());
      voie.setName(voieDto.getName());
      voie.setLatitude(voieDto.getLatitude());
      voie.setLongitude(voieDto.getLongitude());
      voie.setParentId(voieDto.getParentId());
      voie.setPhotoLink(voieDto.getPhotoLink());
      voie.setMapLink(voieDto.getMapLink());
      voie.setNbComment(voieDto.getNbComment());
      voie.setCotationId(voieDto.getCotationId());
      voie.setHeigth(voieDto.getHeigth());
      voie.setIsEquipped(voieDto.getIsEquipped());

      if (voieDto.getAliasManager() != null) {
         voie.setManagerId(userService.findByAlias(voieDto.getAliasManager()).getId());
      }

      return voieRepository.save(voie).getId();
   }

   @Override
   public Integer delete(VoieDto voieDto) {
     Integer voieId = voieDto.getId();

      if(voieId!=null){
         voieRepository.delete(voieRepository.getOne(voieId));
         topoService.decreaseLaneCounter(siteService.getTopoId(voieDto.getParentId()));
         return voieId;
      }

      return null;
   }

   @Override
   public Boolean hasRight(VoieDto voieDto){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      UserDto userDto = userService.findByAlias(voieDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())) {
         return Boolean.TRUE;
      }

      return Boolean.FALSE;
   }
}
