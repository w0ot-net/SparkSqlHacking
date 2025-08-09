package org.apache.parquet.example.data.simple;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;

public class BinaryValue extends Primitive {
   private final Binary binary;

   public BinaryValue(Binary binary) {
      this.binary = binary;
   }

   public Binary getBinary() {
      return this.binary;
   }

   public String getString() {
      return this.binary.toStringUsingUTF8();
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addBinary(this.binary);
   }

   public String toString() {
      return this.getString();
   }
}
