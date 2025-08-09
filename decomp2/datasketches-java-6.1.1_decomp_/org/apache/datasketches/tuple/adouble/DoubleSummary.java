package org.apache.datasketches.tuple.adouble;

import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.DeserializeResult;
import org.apache.datasketches.tuple.UpdatableSummary;

public final class DoubleSummary implements UpdatableSummary {
   private double value_;
   private final Mode mode_;
   private static final int SERIALIZED_SIZE_BYTES = 9;
   private static final int VALUE_INDEX = 0;
   private static final int MODE_BYTE_INDEX = 8;

   private DoubleSummary(double value, Mode mode) {
      this.value_ = value;
      this.mode_ = mode;
   }

   public DoubleSummary(Mode mode) {
      this.mode_ = mode;
      switch (mode) {
         case Sum:
            this.value_ = (double)0.0F;
            break;
         case Min:
            this.value_ = Double.POSITIVE_INFINITY;
            break;
         case Max:
            this.value_ = Double.NEGATIVE_INFINITY;
            break;
         case AlwaysOne:
            this.value_ = (double)1.0F;
      }

   }

   public DoubleSummary update(Double value) {
      switch (this.mode_) {
         case Sum:
            this.value_ += value;
            break;
         case Min:
            if (value < this.value_) {
               this.value_ = value;
            }
            break;
         case Max:
            if (value > this.value_) {
               this.value_ = value;
            }
            break;
         case AlwaysOne:
            this.value_ = (double)1.0F;
      }

      return this;
   }

   public DoubleSummary copy() {
      return new DoubleSummary(this.value_, this.mode_);
   }

   public double getValue() {
      return this.value_;
   }

   public byte[] toByteArray() {
      byte[] bytes = new byte[9];
      ByteArrayUtil.putDoubleLE(bytes, 0, this.value_);
      bytes[8] = (byte)this.mode_.ordinal();
      return bytes;
   }

   public static DeserializeResult fromMemory(Memory mem) {
      return new DeserializeResult(new DoubleSummary(mem.getDouble(0L), DoubleSummary.Mode.values()[mem.getByte(8L)]), 9);
   }

   public static enum Mode {
      Sum,
      Min,
      Max,
      AlwaysOne;
   }
}
