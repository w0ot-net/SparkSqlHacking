package org.apache.hadoop.hive.serde2.lazy;

public class ByteArrayRef {
   byte[] data;

   public byte[] getData() {
      return this.data;
   }

   public void setData(byte[] data) {
      this.data = data;
   }
}
