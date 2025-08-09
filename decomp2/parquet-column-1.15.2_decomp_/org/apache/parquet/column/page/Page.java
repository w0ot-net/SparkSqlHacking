package org.apache.parquet.column.page;

import java.util.OptionalInt;

public abstract class Page {
   private final int compressedSize;
   private final int uncompressedSize;
   private OptionalInt crc = OptionalInt.empty();

   Page(int compressedSize, int uncompressedSize) {
      this.compressedSize = compressedSize;
      this.uncompressedSize = uncompressedSize;
   }

   public int getCompressedSize() {
      return this.compressedSize;
   }

   public int getUncompressedSize() {
      return this.uncompressedSize;
   }

   public void setCrc(int crc) {
      this.crc = OptionalInt.of(crc);
   }

   public OptionalInt getCrc() {
      return this.crc;
   }
}
