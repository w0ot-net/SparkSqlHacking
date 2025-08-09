package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.TimeMicroWriter;
import org.apache.arrow.vector.holders.NullableTimeMicroHolder;
import org.apache.arrow.vector.holders.TimeMicroHolder;
import org.apache.arrow.vector.types.Types;

public class TimeMicroHolderReaderImpl extends AbstractFieldReader {
   private TimeMicroHolder holder;

   public TimeMicroHolderReaderImpl(TimeMicroHolder holder) {
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
      return true;
   }

   public void read(TimeMicroHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeMicroHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Long readLong() {
      Long value = new Long(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readLong();
   }

   public void copyAsValue(TimeMicroWriter writer) {
      writer.write(this.holder);
   }
}
