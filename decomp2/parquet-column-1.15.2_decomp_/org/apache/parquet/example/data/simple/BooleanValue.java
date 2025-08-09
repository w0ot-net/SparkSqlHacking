package org.apache.parquet.example.data.simple;

import org.apache.parquet.io.api.RecordConsumer;

public class BooleanValue extends Primitive {
   private final boolean bool;

   public BooleanValue(boolean bool) {
      this.bool = bool;
   }

   public String toString() {
      return String.valueOf(this.bool);
   }

   public boolean getBoolean() {
      return this.bool;
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addBoolean(this.bool);
   }
}
