package org.apache.parquet.io;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

public class ParquetFileRange {
   private final long offset;
   private final int length;
   private CompletableFuture dataReadFuture;

   public ParquetFileRange(long offset, int length) {
      this.offset = offset;
      this.length = length;
   }

   public long getOffset() {
      return this.offset;
   }

   public int getLength() {
      return this.length;
   }

   public CompletableFuture getDataReadFuture() {
      return this.dataReadFuture;
   }

   public void setDataReadFuture(CompletableFuture dataReadFuture) {
      this.dataReadFuture = dataReadFuture;
   }

   public String toString() {
      return String.format("range[%,d - %,d)", this.offset, this.offset + (long)this.length);
   }
}
