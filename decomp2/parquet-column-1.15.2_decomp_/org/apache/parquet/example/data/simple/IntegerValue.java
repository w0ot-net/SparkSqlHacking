package org.apache.parquet.example.data.simple;

import org.apache.parquet.io.api.RecordConsumer;

public class IntegerValue extends Primitive {
   private final int value;

   public IntegerValue(int value) {
      this.value = value;
   }

   public String toString() {
      return String.valueOf(this.value);
   }

   public int getInteger() {
      return this.value;
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addInteger(this.value);
   }
}
