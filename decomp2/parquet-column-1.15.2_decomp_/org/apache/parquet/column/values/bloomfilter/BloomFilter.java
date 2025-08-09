package org.apache.parquet.column.values.bloomfilter;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.parquet.io.api.Binary;

public interface BloomFilter {
   void writeTo(OutputStream var1) throws IOException;

   void insertHash(long var1);

   boolean findHash(long var1);

   int getBitsetSize();

   boolean equals(Object var1);

   long hash(int var1);

   long hash(long var1);

   long hash(double var1);

   long hash(float var1);

   long hash(Binary var1);

   long hash(Object var1);

   HashStrategy getHashStrategy();

   Algorithm getAlgorithm();

   Compression getCompression();

   default boolean canMergeFrom(BloomFilter otherBloomFilter) {
      throw new UnsupportedOperationException("Merge API is not implemented.");
   }

   default void merge(BloomFilter otherBloomFilter) throws IOException {
      throw new UnsupportedOperationException("Merge API is not implemented.");
   }

   public static enum HashStrategy {
      XXH64;

      public String toString() {
         return "xxhash";
      }
   }

   public static enum Algorithm {
      BLOCK;

      public String toString() {
         return "block";
      }
   }

   public static enum Compression {
      UNCOMPRESSED;

      public String toString() {
         return "uncompressed";
      }
   }
}
