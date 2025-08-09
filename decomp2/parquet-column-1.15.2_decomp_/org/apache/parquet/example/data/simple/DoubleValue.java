package org.apache.parquet.example.data.simple;

import org.apache.parquet.io.api.RecordConsumer;

public class DoubleValue extends Primitive {
   private final double value;

   public DoubleValue(double value) {
      this.value = value;
   }

   public double getDouble() {
      return this.value;
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addDouble(this.value);
   }

   public String toString() {
      return String.valueOf(this.value);
   }
}
