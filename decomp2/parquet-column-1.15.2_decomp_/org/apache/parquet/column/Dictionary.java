package org.apache.parquet.column;

import org.apache.parquet.io.api.Binary;

public abstract class Dictionary {
   private final Encoding encoding;

   public Dictionary(Encoding encoding) {
      this.encoding = encoding;
   }

   public Encoding getEncoding() {
      return this.encoding;
   }

   public abstract int getMaxId();

   public Binary decodeToBinary(int id) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public int decodeToInt(int id) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public long decodeToLong(int id) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public float decodeToFloat(int id) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public double decodeToDouble(int id) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public boolean decodeToBoolean(int id) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }
}
