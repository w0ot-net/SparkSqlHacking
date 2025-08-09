package org.apache.arrow.memory.util;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.apache.arrow.util.Preconditions;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class ArrowBufPointer implements Comparable {
   public static final int NULL_HASH_CODE = 0;
   private @Nullable ArrowBuf buf;
   private long offset;
   private long length;
   private int hashCode;
   private final ArrowBufHasher hasher;
   private boolean hashCodeChanged;

   public ArrowBufPointer() {
      this(SimpleHasher.INSTANCE);
   }

   public ArrowBufPointer(ArrowBufHasher hasher) {
      this.hashCode = 0;
      this.hashCodeChanged = false;
      Preconditions.checkNotNull(hasher);
      this.hasher = hasher;
      this.buf = null;
   }

   public ArrowBufPointer(ArrowBuf buf, long offset, long length) {
      this(buf, offset, length, SimpleHasher.INSTANCE);
   }

   public ArrowBufPointer(ArrowBuf buf, long offset, long length, ArrowBufHasher hasher) {
      this.hashCode = 0;
      this.hashCodeChanged = false;
      Preconditions.checkNotNull(hasher);
      this.hasher = hasher;
      this.set(buf, offset, length);
   }

   public void set(ArrowBuf buf, long offset, long length) {
      this.buf = buf;
      this.offset = offset;
      this.length = length;
      this.hashCodeChanged = true;
   }

   public @Nullable ArrowBuf getBuf() {
      return this.buf;
   }

   public long getOffset() {
      return this.offset;
   }

   public long getLength() {
      return this.length;
   }

   public boolean equals(@Nullable Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!this.hasher.equals(((ArrowBufPointer)o).hasher)) {
            return false;
         } else {
            ArrowBufPointer other = (ArrowBufPointer)o;
            if (this.buf != null && other.buf != null) {
               return ByteFunctionHelpers.equal(this.buf, this.offset, this.offset + this.length, other.buf, other.offset, other.offset + other.length) != 0;
            } else {
               return this.buf == null && other.buf == null;
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      if (!this.hashCodeChanged) {
         return this.hashCode;
      } else {
         if (this.buf == null) {
            this.hashCode = 0;
         } else {
            this.hashCode = this.hasher.hashCode(this.buf, this.offset, this.length);
         }

         this.hashCodeChanged = false;
         return this.hashCode;
      }
   }

   public int compareTo(ArrowBufPointer that) {
      if (this.buf != null && that.buf != null) {
         return ByteFunctionHelpers.compare(this.buf, this.offset, this.offset + this.length, that.buf, that.offset, that.offset + that.length);
      } else if (this.buf == null && that.buf == null) {
         return 0;
      } else {
         return this.buf == null ? -1 : 1;
      }
   }
}
