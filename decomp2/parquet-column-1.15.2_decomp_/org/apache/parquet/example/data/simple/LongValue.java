package org.apache.parquet.example.data.simple;

import org.apache.parquet.io.api.RecordConsumer;

public class LongValue extends Primitive {
   private final long value;

   public LongValue(long value) {
      this.value = value;
   }

   public String toString() {
      return String.valueOf(this.value);
   }

   public long getLong() {
      return this.value;
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addLong(this.value);
   }
}
