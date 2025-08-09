package org.xerial.snappy;

public enum BitShuffleType {
   BYTE(1),
   SHORT(2),
   INT(4),
   LONG(8),
   FLOAT(4),
   DOUBLE(8);

   public final int id;

   private BitShuffleType(int var3) {
      this.id = var3;
   }

   public int getTypeSize() {
      return this.id;
   }
}
