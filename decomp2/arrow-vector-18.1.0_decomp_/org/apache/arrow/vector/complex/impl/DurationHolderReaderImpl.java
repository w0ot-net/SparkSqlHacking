package org.apache.arrow.vector.complex.impl;

import java.time.Duration;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.complex.writer.DurationWriter;
import org.apache.arrow.vector.holders.DurationHolder;
import org.apache.arrow.vector.holders.NullableDurationHolder;
import org.apache.arrow.vector.types.Types;

public class DurationHolderReaderImpl extends AbstractFieldReader {
   private DurationHolder holder;

   public DurationHolderReaderImpl(DurationHolder holder) {
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
      return true;
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
      return DurationVector.toDuration(this.holder.value, this.holder.unit);
   }

   public Object readObject() {
      return this.readDuration();
   }

   public void copyAsValue(DurationWriter writer) {
      writer.write(this.holder);
   }
}
