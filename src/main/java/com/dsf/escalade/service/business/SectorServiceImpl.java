package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Sector;
import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.repository.business.SectorRepository;
import com.dsf.escalade.web.dto.SectorDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SectorService")
public class SectorServiceImpl implements SectorService {

   private final SectorRepository sectorRepository;

   public SectorServiceImpl(SectorRepository sectorRepository) {
      this.sectorRepository = sectorRepository;
   }

   @Override
   public List<SectorDto> findAll() {
      List<SectorDto> sectorDtos = new ArrayList<>();
      SectorDto sectorDto;

      for(Sector sector : sectorRepository.findAll()){
         sectorDto = new SectorDto();
         sectorDto.setId(sector.getId());
         sectorDto.setTopoId(sector.getTopoId());
         sectorDto.setName(sector.getName());
         sectorDto.setEquipment(sector.getEquipment());
         sectorDto.setHasComment(sector.getHasComment());
         sectorDto.setLatitude(sector.getLatitude());
         sectorDto.setLongitude(sector.getLongitude());
         sectorDto.setPhotoLink(sector.getPhotoLink());
         sectorDto.setMapLink(sector.getMapLink());

         sectorDtos.add(sectorDto);
      }

      return sectorDtos;
   }

   @Override
   public List<SectorDto> findByTopoId(Integer id) {
      List<SectorDto> sectorDtos = new ArrayList<>();
      SectorDto sectorDto;

      for(Sector sector : sectorRepository.findByTopoId(id)){
         sectorDto = new SectorDto();
         sectorDto.setId(sector.getId());
         sectorDto.setTopoId(sector.getTopoId());
         sectorDto.setName(sector.getName());
         sectorDto.setEquipment(sector.getEquipment());
         sectorDto.setHasComment(sector.getHasComment());
         sectorDto.setLatitude(sector.getLatitude());
         sectorDto.setLongitude(sector.getLongitude());
         sectorDto.setPhotoLink(sector.getPhotoLink());
         sectorDto.setMapLink(sector.getMapLink());

         sectorDtos.add(sectorDto);
      }

      return sectorDtos;
   }

   @Override
   public SectorDto getOne(Integer id) {
      SectorDto sectorDto = new SectorDto();
      Sector sector = sectorRepository.getOne(id);

      sectorDto.setId(sector.getId());
      sectorDto.setTopoId(sector.getTopoId());
      sectorDto.setName(sector.getName());
      sectorDto.setEquipment(sector.getEquipment());
      sectorDto.setHasComment(sector.getHasComment());
      sectorDto.setLatitude(sector.getLatitude());
      sectorDto.setLongitude(sector.getLongitude());
      sectorDto.setPhotoLink(sector.getPhotoLink());
      sectorDto.setMapLink(sector.getMapLink());
      return sectorDto;
   }

   @Override
   public Integer save(SectorDto sectorDto) {
      Sector sector =new Sector();

      sector.setId(sectorDto.getId());
      sector.setTopoId(sectorDto.getTopoId());
      sector.setName(sectorDto.getName());
      sector.setType(SiteType.SECTOR);
      sector.setEquipment(sectorDto.getEquipment());
      sector.setHasComment(sectorDto.getHasComment());
      sector.setLatitude(sectorDto.getLatitude());
      sector.setLongitude(sectorDto.getLongitude());
      sector.setPhotoLink(sectorDto.getPhotoLink());
      sector.setMapLink(sectorDto.getMapLink());

      return sectorRepository.save(sector).getId();
   }

   @Override
   public Integer delete(SectorDto sectorDto) {
      Integer sectorId = sectorDto.getId();

      if(sectorId!=null){
         sectorRepository.delete(sectorRepository.getOne(sectorId));
         return sectorId;
      }

      return null;
   }

}
