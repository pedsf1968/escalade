package com.dsf.escalade.service;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.repository.business.TopoRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.TopoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("TopoService")
public class TopoServiceImpl implements TopoService{
   private final TopoRepository topoRepository;
   private final UserRepository userRepository;

   @Autowired
   public TopoServiceImpl(TopoRepository topoRepository, UserRepository userRepository) {
      this.topoRepository = topoRepository;
      this.userRepository = userRepository;
   }

   @Override
   public List<TopoDto> findAll(){
      List<TopoDto> topoDtoList = new ArrayList<>();
      TopoDto topoDto = null;

      for(Topo topo:topoRepository.findAll()){
         topoDto = new TopoDto();

         topoDto.setId(topo.getId());
         topoDto.setName(topo.getName());
         topoDto.setAccess(topo.getAccess());
         topoDto.setLongitude(topo.getLongitude());
         topoDto.setLatitude(topo.getLatitude());
         topoDto.setHasComment(topo.getHasComment());
         topoDto.setPhotoLink(topo.getPhotoLink());
         topoDto.setMapLink(topo.getMapLink());
         topoDto.setRegion(topo.getRegion());
         topoDto.setAddressId(topo.getAddressId());
         topoDto.setDate(topo.getDate());
         topoDto.setDescription(topo.getDescription());
         topoDto.setTechnic(topo.getTechnic());
         topoDto.setStatus(topo.getStatus().toString());
         topoDto.setStatusAuto(topo.getStatusAuto());

         if(topo.getManagerId()!=null) {
            topoDto.setAliasManager(userRepository.getOne(topo.getManagerId()).getAlias());
         }
         if(topo.getClimberId()!=null) {
            topoDto.setAliasClimber(userRepository.getOne(topo.getClimberId()).getAlias());
         }

         topoDtoList.add(topoDto);
      }

      return topoDtoList;
   }

   @Override
   public List<TopoDto> findByManagerId(Integer id) {
      List<TopoDto> topoDtoList = new ArrayList<>();
      TopoDto topoDto = null;

      for(Topo topo:topoRepository.findByManagerId(id)){
         topoDto = new TopoDto();

         topoDto.setId(topo.getId());
         topoDto.setName(topo.getName());
         topoDto.setAccess(topo.getAccess());
         topoDto.setLongitude(topo.getLongitude());
         topoDto.setLatitude(topo.getLatitude());
         topoDto.setHasComment(topo.getHasComment());
         topoDto.setPhotoLink(topo.getPhotoLink());
         topoDto.setMapLink(topo.getMapLink());
         topoDto.setRegion(topo.getRegion());
         topoDto.setAddressId(topo.getAddressId());
         topoDto.setDate(topo.getDate());
         topoDto.setDescription(topo.getDescription());
         topoDto.setTechnic(topo.getTechnic());
         topoDto.setStatus(topo.getStatus().toString());
         topoDto.setStatusAuto(topo.getStatusAuto());

         if(topo.getManagerId()!=null) {
            topoDto.setAliasManager(userRepository.getOne(topo.getManagerId()).getAlias());
         }
         if(topo.getClimberId()!=null) {
            topoDto.setAliasClimber(userRepository.getOne(topo.getClimberId()).getAlias());
         }

         topoDtoList.add(topoDto);
      }

      return topoDtoList;
   }

   @Override
   public TopoDto getOne(Integer id){
      Topo topo = topoRepository.getOne(id);
      TopoDto topoDto = new TopoDto();

      topoDto.setId(topo.getId());
      topoDto.setName(topo.getName());
      topoDto.setAccess(topo.getAccess());
      topoDto.setLongitude(topo.getLongitude());
      topoDto.setLatitude(topo.getLatitude());
      topoDto.setHasComment(topo.getHasComment());
      topoDto.setPhotoLink(topo.getPhotoLink());
      topoDto.setMapLink(topo.getMapLink());
      topoDto.setRegion(topo.getRegion());
      topoDto.setAddressId(topo.getAddressId());
      topoDto.setDate(topo.getDate());
      topoDto.setDescription(topo.getDescription());
      topoDto.setTechnic(topo.getTechnic());
      topoDto.setStatus(topo.getStatus().toString());
      topoDto.setStatusAuto(topo.getStatusAuto());

      if(topo.getManagerId()!=null) {
         topoDto.setAliasManager(userRepository.getOne(topo.getManagerId()).getAlias());
      }
      if(topo.getClimberId()!=null) {
         topoDto.setAliasClimber(userRepository.getOne(topo.getClimberId()).getAlias());
      }

      return topoDto;
   }

   @Override
   public Integer save(TopoDto topoDto) {
      Topo topo = new Topo();

      topo.setId(topoDto.getId());
      topo.setName(topoDto.getName());
      topo.setLongitude(topoDto.getLongitude());
      topo.setLatitude(topoDto.getLatitude());
      topo.setType(SiteType.TOPO);
      topo.setPhotoLink(topoDto.getPhotoLink());
      topo.setMapLink(topoDto.getMapLink());
      topo.setRegion(topoDto.getRegion());
      topo.setAddressId(topoDto.getAddressId());
      topo.setDate(topoDto.getDate());
      topo.setDescription(topoDto.getDescription());
      topo.setTechnic(topoDto.getTechnic());
      topo.setAccess(topoDto.getAccess());
      topo.setManagerId(userRepository.findByAlias(topoDto.getAliasManager()).getId());
      topo.setClimberId(userRepository.findByAlias(topoDto.getAliasClimber()).getId());
      topo.setStatus(StatusType.valueOf(topoDto.getStatus()));
      topo.setStatusAuto(topoDto.getStatusAuto());

      return topoRepository.save(topo).getId();
   }
}
