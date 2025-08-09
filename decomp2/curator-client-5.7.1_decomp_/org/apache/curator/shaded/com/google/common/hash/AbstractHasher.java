package org.apache.curator.shaded.com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
abstract class AbstractHasher implements Hasher {
   @CanIgnoreReturnValue
   public final Hasher putBoolean(boolean b) {
      return this.putByte((byte)(b ? 1 : 0));
   }

   @CanIgnoreReturnValue
   public final Hasher putDouble(double d) {
      return this.putLong(Double.doubleToRawLongBits(d));
   }

   @CanIgnoreReturnValue
   public final Hasher putFloat(float f) {
      return this.putInt(Float.floatToRawIntBits(f));
   }

   @CanIgnoreReturnValue
   public Hasher putUnencodedChars(CharSequence charSequence) {
      int i = 0;

      for(int len = charSequence.length(); i < len; ++i) {
         this.putChar(charSequence.charAt(i));
      }

      return this;
   }

   @CanIgnoreReturnValue
   public Hasher putString(CharSequence charSequence, Charset charset) {
      return this.putBytes(charSequence.toString().getBytes(charset));
   }

   @CanIgnoreReturnValue
   public Hasher putBytes(byte[] bytes) {
      return this.putBytes(bytes, 0, bytes.length);
   }

   @CanIgnoreReturnValue
   public Hasher putBytes(byte[] bytes, int off, int len) {
      Preconditions.checkPositionIndexes(off, off + len, bytes.length);

      for(int i = 0; i < len; ++i) {
         this.putByte(bytes[off + i]);
      }

      return this;
   }

   @CanIgnoreReturnValue
   public Hasher putBytes(ByteBuffer b) {
      if (b.hasArray()) {
         this.putBytes(b.array(), b.arrayOffset() + b.position(), b.remaining());
         Java8Compatibility.position(b, b.limit());
      } else {
         for(int remaining = b.remaining(); remaining > 0; --remaining) {
            this.putByte(b.get());
         }
      }

      return this;
   }

   @CanIgnoreReturnValue
   public Hasher putShort(short s) {
      this.putByte((byte)s);
      this.putByte((byte)(s >>> 8));
      return this;
   }

   @CanIgnoreReturnValue
   public Hasher putInt(int i) {
      this.putByte((byte)i);
      this.putByte((byte)(i >>> 8));
      this.putByte((byte)(i >>> 16));
      this.putByte((byte)(i >>> 24));
      return this;
   }

   @CanIgnoreReturnValue
   public Hasher putLong(long l) {
      for(int i = 0; i < 64; i += 8) {
         this.putByte((byte)((int)(l >>> i)));
      }

      return this;
   }

   @CanIgnoreReturnValue
   public Hasher putChar(char c) {
      this.putByte((byte)c);
      this.putByte((byte)(c >>> 8));
      return this;
   }

   @CanIgnoreReturnValue
   public Hasher putObject(@ParametricNullness Object instance, Funnel funnel) {
      funnel.funnel(instance, this);
      return this;
   }
}
