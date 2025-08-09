package org.apache.parquet.example.data.simple;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;

public class Int96Value extends Primitive {
   private final Binary value;

   public Int96Value(Binary value) {
      this.value = value;
   }

   public Binary getInt96() {
      return this.value;
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addBinary(this.value);
   }

   public String toString() {
      return "Int96Value{" + this.value + "}";
   }
}
