package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.io.build.AbstractStreamBuilder;

public class UnsynchronizedByteArrayInputStream extends InputStream {
   public static final int END_OF_STREAM = -1;
   private final byte[] data;
   private final int eod;
   private int offset;
   private int markedOffset;

   public static Builder builder() {
      return new Builder();
   }

   private static int minPosLen(byte[] data, int defaultValue) {
      requireNonNegative(defaultValue, "defaultValue");
      return Math.min(defaultValue, data.length > 0 ? data.length : defaultValue);
   }

   private static int requireNonNegative(int value, String name) {
      if (value < 0) {
         throw new IllegalArgumentException(name + " cannot be negative");
      } else {
         return value;
      }
   }

   /** @deprecated */
   @Deprecated
   public UnsynchronizedByteArrayInputStream(byte[] data) {
      this(data, data.length, 0, 0);
   }

   /** @deprecated */
   @Deprecated
   public UnsynchronizedByteArrayInputStream(byte[] data, int offset) {
      this(data, data.length, Math.min(requireNonNegative(offset, "offset"), minPosLen(data, offset)), minPosLen(data, offset));
   }

   /** @deprecated */
   @Deprecated
   public UnsynchronizedByteArrayInputStream(byte[] data, int offset, int length) {
      requireNonNegative(offset, "offset");
      requireNonNegative(length, "length");
      this.data = (byte[])Objects.requireNonNull(data, "data");
      this.eod = Math.min(minPosLen(data, offset) + length, data.length);
      this.offset = minPosLen(data, offset);
      this.markedOffset = minPosLen(data, offset);
   }

   private UnsynchronizedByteArrayInputStream(byte[] data, int eod, int offset, int markedOffset) {
      this.data = (byte[])Objects.requireNonNull(data, "data");
      this.eod = eod;
      this.offset = offset;
      this.markedOffset = markedOffset;
   }

   public int available() {
      return this.offset < this.eod ? this.eod - this.offset : 0;
   }

   public void mark(int readLimit) {
      this.markedOffset = this.offset;
   }

   public boolean markSupported() {
      return true;
   }

   public int read() {
      return this.offset < this.eod ? this.data[this.offset++] & 255 : -1;
   }

   public int read(byte[] dest) {
      Objects.requireNonNull(dest, "dest");
      return this.read(dest, 0, dest.length);
   }

   public int read(byte[] dest, int off, int len) {
      Objects.requireNonNull(dest, "dest");
      if (off >= 0 && len >= 0 && off + len <= dest.length) {
         if (this.offset >= this.eod) {
            return -1;
         } else {
            int actualLen = this.eod - this.offset;
            if (len < actualLen) {
               actualLen = len;
            }

            if (actualLen <= 0) {
               return 0;
            } else {
               System.arraycopy(this.data, this.offset, dest, off, actualLen);
               this.offset += actualLen;
               return actualLen;
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void reset() {
      this.offset = this.markedOffset;
   }

   public long skip(long n) {
      if (n < 0L) {
         throw new IllegalArgumentException("Skipping backward is not supported");
      } else {
         long actualSkip = (long)(this.eod - this.offset);
         if (n < actualSkip) {
            actualSkip = n;
         }

         this.offset = Math.addExact(this.offset, Math.toIntExact(n));
         return actualSkip;
      }
   }

   public static class Builder extends AbstractStreamBuilder {
      private int offset;
      private int length;

      public UnsynchronizedByteArrayInputStream get() throws IOException {
         return new UnsynchronizedByteArrayInputStream(this.checkOrigin().getByteArray(), this.offset, this.length);
      }

      public Builder setByteArray(byte[] origin) {
         this.length = ((byte[])Objects.requireNonNull(origin, "origin")).length;
         return (Builder)super.setByteArray(origin);
      }

      public Builder setLength(int length) {
         if (length < 0) {
            throw new IllegalArgumentException("length cannot be negative");
         } else {
            this.length = length;
            return this;
         }
      }

      public Builder setOffset(int offset) {
         if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
         } else {
            this.offset = offset;
            return this;
         }
      }
   }
}
