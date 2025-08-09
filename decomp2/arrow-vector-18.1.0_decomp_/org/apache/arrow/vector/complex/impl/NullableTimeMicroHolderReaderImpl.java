package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.NullableTimeMicroHolder;
import org.apache.arrow.vector.holders.TimeMicroHolder;
import org.apache.arrow.vector.types.Types;

public class NullableTimeMicroHolderReaderImpl extends AbstractFieldReader {
   private NullableTimeMicroHolder holder;

   public NullableTimeMicroHolderReaderImpl(NullableTimeMicroHolder holder) {
      this.holder = holder;
   }

   public int size() {
      throw new UnsupportedOperationException("You can't call size on a Holder value reader.");
   }

   public boolean next() {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public void setPosition(int index) {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.TIMEMICRO;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(TimeMicroHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeMicroHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Long readLong() {
      if (!this.isSet()) {
         return null;
      } else {
         Long value = new Long(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readLong();
   }
}
