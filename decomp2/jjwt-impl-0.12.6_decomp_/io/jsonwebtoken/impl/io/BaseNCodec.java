package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.lang.Strings;
import java.util.Arrays;
import java.util.Objects;

abstract class BaseNCodec {
   static final int EOF = -1;
   public static final int MIME_CHUNK_SIZE = 76;
   private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
   private static final int DEFAULT_BUFFER_SIZE = 8192;
   private static final int MAX_BUFFER_SIZE = 2147483639;
   protected static final int MASK_8BITS = 255;
   protected static final byte PAD_DEFAULT = 61;
   protected static final CodecPolicy DECODING_POLICY_DEFAULT;
   static final byte[] CHUNK_SEPARATOR;
   protected final byte pad;
   private final int unencodedBlockSize;
   private final int encodedBlockSize;
   protected final int lineLength;
   private final int chunkSeparatorLength;
   private final CodecPolicy decodingPolicy;

   private static int createPositiveCapacity(int minCapacity) {
      if (minCapacity < 0) {
         throw new OutOfMemoryError("Unable to allocate array size: " + ((long)minCapacity & 4294967295L));
      } else {
         return Math.max(minCapacity, 2147483639);
      }
   }

   /** @deprecated */
   @Deprecated
   protected static boolean isWhiteSpace(byte byteToCheck) {
      return Character.isWhitespace(byteToCheck);
   }

   private static int compareUnsigned(int x, int y) {
      return Integer.compare(x + Integer.MIN_VALUE, y + Integer.MIN_VALUE);
   }

   private static byte[] resizeBuffer(Context context, int minCapacity) {
      int oldCapacity = context.buffer.length;
      int newCapacity = oldCapacity * 2;
      if (compareUnsigned(newCapacity, minCapacity) < 0) {
         newCapacity = minCapacity;
      }

      if (compareUnsigned(newCapacity, 2147483639) > 0) {
         newCapacity = createPositiveCapacity(minCapacity);
      }

      byte[] b = Arrays.copyOf(context.buffer, newCapacity);
      context.buffer = b;
      return b;
   }

   protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength) {
      this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, (byte)61);
   }

   protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength, byte pad) {
      this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, pad, DECODING_POLICY_DEFAULT);
   }

   protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength, byte pad, CodecPolicy decodingPolicy) {
      this.unencodedBlockSize = unencodedBlockSize;
      this.encodedBlockSize = encodedBlockSize;
      boolean useChunking = lineLength > 0 && chunkSeparatorLength > 0;
      this.lineLength = useChunking ? lineLength / encodedBlockSize * encodedBlockSize : 0;
      this.chunkSeparatorLength = chunkSeparatorLength;
      this.pad = pad;
      this.decodingPolicy = (CodecPolicy)Objects.requireNonNull(decodingPolicy, "codecPolicy");
   }

   int available(Context context) {
      return this.hasData(context) ? context.pos - context.readPos : 0;
   }

   protected boolean containsAlphabetOrPad(byte[] arrayOctet) {
      if (arrayOctet == null) {
         return false;
      } else {
         for(byte element : arrayOctet) {
            if (this.pad == element || this.isInAlphabet(element)) {
               return true;
            }
         }

         return false;
      }
   }

   static int length(byte[] bytes) {
      return bytes != null ? bytes.length : 0;
   }

   static boolean isEmpty(byte[] bytes) {
      return length(bytes) == 0;
   }

   public byte[] decode(byte[] pArray) {
      if (isEmpty(pArray)) {
         return pArray;
      } else {
         Context context = new Context();
         this.decode(pArray, 0, pArray.length, context);
         this.decode(pArray, 0, -1, context);
         byte[] result = new byte[context.pos];
         this.readResults(result, 0, result.length, context);
         return result;
      }
   }

   abstract void decode(byte[] var1, int var2, int var3, Context var4);

   public byte[] decode(String pArray) {
      return this.decode(Strings.utf8(pArray));
   }

   public byte[] encode(byte[] pArray) {
      return isEmpty(pArray) ? pArray : this.encode(pArray, 0, pArray.length);
   }

   public byte[] encode(byte[] pArray, int offset, int length) {
      if (isEmpty(pArray)) {
         return pArray;
      } else {
         Context context = new Context();
         this.encode(pArray, offset, length, context);
         this.encode(pArray, offset, -1, context);
         byte[] buf = new byte[context.pos - context.readPos];
         this.readResults(buf, 0, buf.length, context);
         return buf;
      }
   }

   abstract void encode(byte[] var1, int var2, int var3, Context var4);

   public String encodeAsString(byte[] pArray) {
      return Strings.utf8(this.encode(pArray));
   }

   public String encodeToString(byte[] pArray) {
      return Strings.utf8(this.encode(pArray));
   }

   protected byte[] ensureBufferSize(int size, Context context) {
      if (context.buffer == null) {
         context.buffer = new byte[Math.max(size, this.getDefaultBufferSize())];
         context.pos = 0;
         context.readPos = 0;
      } else if (context.pos + size - context.buffer.length > 0) {
         return resizeBuffer(context, context.pos + size);
      }

      return context.buffer;
   }

   protected int getDefaultBufferSize() {
      return 8192;
   }

   public long getEncodedLength(byte[] pArray) {
      long len = (long)((pArray.length + this.unencodedBlockSize - 1) / this.unencodedBlockSize) * (long)this.encodedBlockSize;
      if (this.lineLength > 0) {
         len += (len + (long)this.lineLength - 1L) / (long)this.lineLength * (long)this.chunkSeparatorLength;
      }

      return len;
   }

   boolean hasData(Context context) {
      return context.pos > context.readPos;
   }

   protected abstract boolean isInAlphabet(byte var1);

   public boolean isInAlphabet(byte[] arrayOctet, boolean allowWSPad) {
      for(byte octet : arrayOctet) {
         if (!this.isInAlphabet(octet) && (!allowWSPad || octet != this.pad && !Character.isWhitespace(octet))) {
            return false;
         }
      }

      return true;
   }

   public boolean isInAlphabet(String basen) {
      return this.isInAlphabet(Strings.utf8(basen), true);
   }

   public boolean isStrictDecoding() {
      return this.decodingPolicy == CodecPolicy.STRICT;
   }

   int readResults(byte[] b, int bPos, int bAvail, Context context) {
      if (this.hasData(context)) {
         int len = Math.min(this.available(context), bAvail);
         System.arraycopy(context.buffer, context.readPos, b, bPos, len);
         context.readPos += len;
         if (!this.hasData(context)) {
            context.pos = context.readPos = 0;
         }

         return len;
      } else {
         return context.eof ? -1 : 0;
      }
   }

   static {
      DECODING_POLICY_DEFAULT = CodecPolicy.LENIENT;
      CHUNK_SEPARATOR = new byte[]{13, 10};
   }

   static class Context {
      int ibitWorkArea;
      long lbitWorkArea;
      byte[] buffer;
      int pos;
      int readPos;
      boolean eof;
      int currentLinePos;
      int modulus;

      public String toString() {
         return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", this.getClass().getSimpleName(), Arrays.toString(this.buffer), this.currentLinePos, this.eof, this.ibitWorkArea, this.lbitWorkArea, this.modulus, this.pos, this.readPos);
      }
   }
}
