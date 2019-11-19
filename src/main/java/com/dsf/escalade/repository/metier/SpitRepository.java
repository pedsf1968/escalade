package com.dsf.escalade.repository.metier;


import com.dsf.escalade.model.metier.Spit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpitRepository extends JpaRepository <Spit, Integer> {
}
