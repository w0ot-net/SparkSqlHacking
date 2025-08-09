package org.apache.avro.mapred;

public class AvroKey extends AvroWrapper {
   public AvroKey() {
      this((Object)null);
   }

   public AvroKey(Object datum) {
      super(datum);
   }
}
