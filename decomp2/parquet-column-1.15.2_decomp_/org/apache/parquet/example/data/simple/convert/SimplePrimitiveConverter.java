package org.apache.parquet.example.data.simple.convert;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;

class SimplePrimitiveConverter extends PrimitiveConverter {
   private final SimpleGroupConverter parent;
   private final int index;

   SimplePrimitiveConverter(SimpleGroupConverter parent, int index) {
      this.parent = parent;
      this.index = index;
   }

   public void addBinary(Binary value) {
      this.parent.getCurrentRecord().add(this.index, value);
   }

   public void addBoolean(boolean value) {
      this.parent.getCurrentRecord().add(this.index, value);
   }

   public void addDouble(double value) {
      this.parent.getCurrentRecord().add(this.index, value);
   }

   public void addFloat(float value) {
      this.parent.getCurrentRecord().add(this.index, value);
   }

   public void addInt(int value) {
      this.parent.getCurrentRecord().add(this.index, value);
   }

   public void addLong(long value) {
      this.parent.getCurrentRecord().add(this.index, value);
   }
}
