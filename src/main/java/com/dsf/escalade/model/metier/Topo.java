package com.dsf.escalade.model.metier;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topo")
@PrimaryKeyJoinColumn(name = "id")
public class Topo extends Site {
   private String region;
   @Column(name = "adresse_id", columnDefinition = "INTEGER(10)")
   private Integer adresse;
   @Column(name = "date", columnDefinition = "DATE DEFAULT NOW()")
   private Date date;
   @Column(name = "description", columnDefinition = "TEXT")
   private String description;
   @Column(name = "technique", columnDefinition = "TEXT")
   private String technique;
   @Column(name = "acces", columnDefinition = "TEXT")
   private String acces;
   @Column(name = "promoteur_id", columnDefinition = "INTEGER(10)")
   private Integer promoteur;
   @Column(name = "grimpeur_id", columnDefinition = "INTEGER(10)")
   private Integer grimpeur;
   @Enumerated(EnumType.STRING)
   @Column(name = "statut", columnDefinition = "VARCHAR(15) DEFAULT 'Indisponible'")
   private StatutType statut;
   @Column(name = "statut_auto", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean statutAuto;

   protected Topo() {
      super();
   }

     public Topo(int id, String nom, String region, Integer adresse, Date date, String description, String technique, String acces, Integer promoteur, Integer grimpeur, StatutType statut, Boolean statutAuto) {
      super(id, nom, SiteType.TOPO );
      this.region = region;
      this.adresse = adresse;
      this.date = date;
      this.description = description;
      this.technique = technique;
      this.acces = acces;
      this.promoteur = promoteur;
      this.grimpeur = grimpeur;
      this.statut = statut;
      this.statutAuto = statutAuto;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public Integer getAdresse() {
      return adresse;
   }

   public void setAdresse(Integer adresse) {
      this.adresse = adresse;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getTechnique() {
      return technique;
   }

   public void setTechnique(String technique) {
      this.technique = technique;
   }

   public String getAcces() {
      return acces;
   }

   public void setAcces(String acces) {
      this.acces = acces;
   }

   public Integer getPromoteur() {
      return promoteur;
   }

   public void setPromoteur(Integer promoteur) {
      this.promoteur = promoteur;
   }

   public Integer getGrimpeur() {
      return grimpeur;
   }

   public void setGrimpeur(Integer grimpeur) {
      this.grimpeur = grimpeur;
   }

   public StatutType getStatut() {
      return statut;
   }

   public void setStatut(StatutType statut) {
      this.statut = statut;
   }

   public Boolean getStatutAuto() {
      return statutAuto;
   }

   public void setStatutAuto(Boolean statutAuto) {
      this.statutAuto = statutAuto;
   }

   @Override
   public String toString() {
      return String.format(
            "Topo {" +
                  "id=%d, " +
                  "nom='%s', " +
                  "region='%s', " +
                  "adresse='%s'," +
                  "date='%td/%tm/%ty', " +
                  "description='%s', " +
                  "technique='%s'," +
                  "accès='%s', " +
                  "promoteur='%s'," +
                  "grimpeur='%s'," +
                  "statut='%s'," +
                  "auto='%b', " +
                  "a un commentaire='%b', " +
                  "photo='%s', " +
                  "carte='%s'}",
            id, nom, region, adresse,
            date, description, technique, acces,
            promoteur, grimpeur,statut,statutAuto,
            aCommentaire, lienPhoto, lienCarte);
   }


}
