package com.dsf.escalade.model.metier;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpitPK implements Serializable {
   @Column(name = "topo_id", columnDefinition = "INTEGER(10)")
   private Integer topo;
   @Column(name = "numero", columnDefinition = "INTEGER(3)")
   private Integer numero;

   public SpitPK() {super(); }

   public SpitPK(Integer topo, Integer numero) {
      this.topo = topo;
      this.numero = numero;
   }

   public Integer getTopo() {
      return topo;
   }

   public Integer getNumero() {
      return numero;
   }

   public void setTopo(Integer topo) {
      this.topo = topo;
   }

   public void setNumero(Integer numero) {
      this.numero = numero;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof SpitPK)) return false;
      SpitPK spitPK = (SpitPK) o;
      return getTopo().equals(spitPK.getTopo()) &&
            getNumero().equals(spitPK.getNumero());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getTopo(), getNumero());
   }
}
