package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tag implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", columnDefinition = "INTEGER(2)")
   private Integer id;
   @Column(name = "nom", columnDefinition = "VARCHAR(20)")
   private String nom;

   public Tag() {
   }

   public Tag(Integer id, String name) {
      this.id = id;
      this.nom = name;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String name) {
      this.nom = name;
   }

   @Override
   public String toString() {
      return "Tag{" +
            "id=" + id +
            ", name='" + nom + '\'' +
            '}';
   }
}
