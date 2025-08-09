package org.apache.parquet.example.data.simple;

import org.apache.parquet.io.api.RecordConsumer;

public class FloatValue extends Primitive {
   private final float value;

   public FloatValue(float value) {
      this.value = value;
   }

   public float getFloat() {
      return this.value;
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addFloat(this.value);
   }

   public String toString() {
      return String.valueOf(this.value);
   }
}
