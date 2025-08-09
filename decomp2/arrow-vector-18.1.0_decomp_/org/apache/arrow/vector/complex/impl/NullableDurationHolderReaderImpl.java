package org.apache.arrow.vector.complex.impl;

import java.time.Duration;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.holders.DurationHolder;
import org.apache.arrow.vector.holders.NullableDurationHolder;
import org.apache.arrow.vector.types.Types;

public class NullableDurationHolderReaderImpl extends AbstractFieldReader {
   private NullableDurationHolder holder;

   public NullableDurationHolderReaderImpl(NullableDurationHolder holder) {
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
      return Types.MinorType.DURATION;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(DurationHolder h) {
      h.value = this.holder.value;
      h.unit = this.holder.unit;
   }

   public void read(NullableDurationHolder h) {
      h.value = this.holder.value;
      h.unit = this.holder.unit;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Duration readDuration() {
      return !this.isSet() ? null : DurationVector.toDuration(this.holder.value, this.holder.unit);
   }

   public Object readObject() {
      return this.readDuration();
   }
}
