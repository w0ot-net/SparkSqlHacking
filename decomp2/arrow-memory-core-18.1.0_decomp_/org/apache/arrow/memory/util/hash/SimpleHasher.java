package org.apache.arrow.memory.util.hash;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.util.MemoryUtil;
import org.checkerframework.checker.nullness.qual.Nullable;

public class SimpleHasher implements ArrowBufHasher {
   public static SimpleHasher INSTANCE = new SimpleHasher();

   protected SimpleHasher() {
   }

   public int hashCode(long address, long length) {
      int hashValue = 0;

      int index;
      for(index = 0; (long)(index + 8) <= length; index += 8) {
         long longValue = MemoryUtil.getLong(address + (long)index);
         int longHash = this.getLongHashCode(longValue);
         hashValue = this.combineHashCode(hashValue, longHash);
      }

      if ((long)(index + 4) <= length) {
         int intValue = MemoryUtil.getInt(address + (long)index);
         hashValue = this.combineHashCode(hashValue, intValue);
         index += 4;
      }

      while((long)index < length) {
         byte byteValue = MemoryUtil.getByte(address + (long)index);
         hashValue = this.combineHashCode(hashValue, byteValue);
         ++index;
      }

      return this.finalizeHashCode(hashValue);
   }

   public int hashCode(ArrowBuf buf, long offset, long length) {
      buf.checkBytes(offset, offset + length);
      return this.hashCode(buf.memoryAddress() + offset, length);
   }

   protected int combineHashCode(int currentHashCode, int newHashCode) {
      return currentHashCode * 37 + newHashCode;
   }

   protected int getLongHashCode(long longValue) {
      return Long.hashCode(longValue);
   }

   protected int finalizeHashCode(int hashCode) {
      return hashCode;
   }

   public int hashCode() {
      return 123;
   }

   public boolean equals(@Nullable Object obj) {
      return obj != null && obj instanceof SimpleHasher;
   }
}
