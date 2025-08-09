package org.apache.datasketches.tuple.aninteger;

import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.DeserializeResult;
import org.apache.datasketches.tuple.UpdatableSummary;

public class IntegerSummary implements UpdatableSummary {
   private int value_;
   private final Mode mode_;
   private static final int SERIALIZED_SIZE_BYTES = 5;
   private static final int VALUE_INDEX = 0;
   private static final int MODE_BYTE_INDEX = 4;

   private IntegerSummary(int value, Mode mode) {
      this.value_ = value;
      this.mode_ = mode;
   }

   public IntegerSummary(Mode mode) {
      this.mode_ = mode;
      switch (mode) {
         case Sum:
            this.value_ = 0;
            break;
         case Min:
            this.value_ = Integer.MAX_VALUE;
            break;
         case Max:
            this.value_ = Integer.MIN_VALUE;
            break;
         case AlwaysOne:
            this.value_ = 1;
      }

   }

   public IntegerSummary update(Integer value) {
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
            this.value_ = 1;
      }

      return this;
   }

   public IntegerSummary copy() {
      return new IntegerSummary(this.value_, this.mode_);
   }

   public int getValue() {
      return this.value_;
   }

   public byte[] toByteArray() {
      byte[] bytes = new byte[5];
      ByteArrayUtil.putIntLE(bytes, 0, this.value_);
      bytes[4] = (byte)this.mode_.ordinal();
      return bytes;
   }

   public static DeserializeResult fromMemory(Memory mem) {
      return new DeserializeResult(new IntegerSummary(mem.getInt(0L), IntegerSummary.Mode.values()[mem.getByte(4L)]), 5);
   }

   public static enum Mode {
      Sum,
      Min,
      Max,
      AlwaysOne;
   }
}
