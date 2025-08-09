package org.apache.avro.mapred;

public class AvroValue extends AvroWrapper {
   public AvroValue() {
      this((Object)null);
   }

   public AvroValue(Object datum) {
      super(datum);
   }
}
