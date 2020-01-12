package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Spit;
import com.dsf.escalade.model.business.SpitPK;
import com.dsf.escalade.repository.business.SpitRepository;
import com.dsf.escalade.web.dto.SpitDto;
import com.dsf.escalade.web.dto.SpitDtoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SpitService")
public class SpitServiceImpl implements SpitService {
   private final SpitRepository spitRepository;

   @Autowired
   public SpitServiceImpl(SpitRepository spitRepository) {
      this.spitRepository = spitRepository;
   }

   @Override
   public SpitDtoList findByLongueurId(Integer longueurId) {
      SpitDtoList spitDtoList = new SpitDtoList();
      SpitDto spitDto;

      for(Spit spit : spitRepository.findByLongueurId(longueurId)){
         spitDto = new SpitDto();

         spitDto.setTopoId(spit.getSpitPK().getTopoId());
         spitDto.setVoieId(spit.getVoieId());
         spitDto.setLongueurId(spit.getLongueurId());
         spitDto.setNumber(spit.getSpitPK().getNumber());
         spitDto.setCotationId(spit.getCotationId());
         spitDto.setComment(spit.getComment());
         spitDto.setIsRelay(spit.getIsRelay());

         spitDtoList.addSpitDto(spitDto);
      }

      return spitDtoList;
   }

   @Override
   public void save(SpitDto spitDto) {
      Spit spit = new Spit();
      SpitPK spitPK = new SpitPK();

      spitPK.setTopoId(spitDto.getTopoId());
      spitPK.setNumber(spitDto.getNumber());
      spit.setSpitPK(spitPK);

      spit.setVoieId(spitDto.getVoieId());
      spit.setLongueurId(spitDto.getLongueurId());
      spit.setCotationId(spitDto.getCotationId());
      spit.setComment(spitDto.getComment());
      spit.setIsRelay(spitDto.getIsRelay());

      spitRepository.save(spit);
   }

   @Override
   public void saveAll(SpitDtoList spitDtoList) {
      for(SpitDto spitDto : spitDtoList.getSpitDtos()){
         save(spitDto);
      }
   }

   @Override
   public void delete(SpitDto spitDto) {
      Spit spit = new Spit();
      SpitPK spitPK = new SpitPK();

      spitPK.setTopoId(spitDto.getTopoId());
      spitPK.setNumber(spitDto.getNumber());

      spit.setSpitPK(spitPK);

      spit.setVoieId(spitDto.getVoieId());
      spit.setLongueurId(spitDto.getLongueurId());
      spit.setCotationId(spitDto.getCotationId());
      spit.setComment(spitDto.getComment());
      spit.setIsRelay(spitDto.getIsRelay());

      spitRepository.delete(spit);
   }

   @Override
   public Integer getLastSpitNumber(Integer topoId){

      return spitRepository.findFirstBySpitPKTopoIdOrderBySpitPKNumberDesc(topoId).getSpitPK().getNumber();
   }

   @Override
   public SpitDto getOne(Integer topoId, Integer number){
      SpitDto spitDto = new SpitDto();
      Spit spit = spitRepository.findBySpitPKTopoIdAndSpitPKNumber(topoId,number);

      spitDto.setTopoId(spit.getSpitPK().getTopoId());
      spitDto.setVoieId(spit.getVoieId());
      spitDto.setLongueurId(spit.getLongueurId());
      spitDto.setNumber(spit.getSpitPK().getNumber());
      spitDto.setCotationId(spit.getCotationId());
      spitDto.setComment(spit.getComment());
      spitDto.setIsRelay(spit.getIsRelay());

      return spitDto;
   }

}
