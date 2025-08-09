package org.apache.parquet.internal.hadoop.metadata;

public class IndexReference {
   private final long offset;
   private final int length;

   public IndexReference(long offset, int length) {
      this.offset = offset;
      this.length = length;
   }

   public long getOffset() {
      return this.offset;
   }

   public int getLength() {
      return this.length;
   }
}
