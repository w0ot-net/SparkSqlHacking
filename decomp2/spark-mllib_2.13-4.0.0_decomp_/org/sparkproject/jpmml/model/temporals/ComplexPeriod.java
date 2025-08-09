package org.sparkproject.jpmml.model.temporals;

import java.util.Objects;

public abstract class ComplexPeriod extends Period {
   private Date epoch = null;

   public ComplexPeriod() {
   }

   ComplexPeriod(Date epoch) {
      this.setEpoch(epoch);
   }

   public abstract ComplexPeriod forEpoch(Date var1);

   public Date getEpoch() {
      return this.epoch;
   }

   private void setEpoch(Date epoch) {
      this.epoch = (Date)Objects.requireNonNull(epoch);
   }
}
