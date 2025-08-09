package org.apache.spark.unsafe.types;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.ibm.icu.lang.UCharacter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.UTF8StringBuilder;
import org.apache.spark.unsafe.array.ByteArrayMethods;
import org.apache.spark.unsafe.hash.Murmur3_x86_32;
import org.apache.spark.util.SparkEnvUtils.;

public final class UTF8String implements Comparable, Externalizable, KryoSerializable, Cloneable {
   @Nonnull
   private Object base;
   private long offset;
   private int numBytes;
   private volatile int numChars;
   private volatile UTF8StringValidity isValid;
   private volatile int numBytesValid;
   private volatile IsFullAscii isFullAscii;
   private static byte[] bytesOfCodePointInUTF8 = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   private static final UTF8String COMMA_UTF8 = fromString(",");
   public static final UTF8String EMPTY_UTF8 = fromString("");
   public static final UTF8String ZERO_UTF8 = fromString("0");
   public static final UTF8String SPACE_UTF8 = fromString(" ");
   private static final byte[] UNICODE_REPLACEMENT_CHARACTER = new byte[]{-17, -65, -67};
   private static final byte[] US_ENGLISH_MAPPING = new byte[]{48, 49, 50, 51, 48, 49, 50, 55, 48, 50, 50, 52, 53, 53, 48, 49, 50, 54, 50, 51, 48, 49, 55, 50, 48, 50};

   public Object getBaseObject() {
      return this.base;
   }

   public long getBaseOffset() {
      return this.offset;
   }

   public static UTF8String fromBytes(byte[] bytes) {
      return bytes != null ? new UTF8String(bytes, (long)Platform.BYTE_ARRAY_OFFSET, bytes.length) : null;
   }

   public static UTF8String fromBytes(byte[] bytes, int offset, int numBytes) {
      return bytes != null ? new UTF8String(bytes, (long)(Platform.BYTE_ARRAY_OFFSET + offset), numBytes) : null;
   }

   public static UTF8String fromAddress(Object base, long offset, int numBytes) {
      return new UTF8String(base, offset, numBytes);
   }

   public static UTF8String fromString(String str) {
      return str == null ? null : fromBytes(str.getBytes(StandardCharsets.UTF_8));
   }

   public static UTF8String blankString(int length) {
      byte[] spaces = new byte[length];
      Arrays.fill(spaces, (byte)32);
      return fromBytes(spaces);
   }

   public static boolean isWhitespaceOrISOControl(int codePoint) {
      return Character.isWhitespace(codePoint) || Character.isISOControl(codePoint);
   }

   private UTF8String(Object base, long offset, int numBytes) {
      this.numChars = -1;
      this.isValid = UTF8String.UTF8StringValidity.UNKNOWN;
      this.numBytesValid = -1;
      this.isFullAscii = UTF8String.IsFullAscii.UNKNOWN;
      this.base = base;
      this.offset = offset;
      this.numBytes = numBytes;
   }

   public UTF8String() {
      this((Object)null, 0L, 0);
   }

   public void writeToMemory(Object target, long targetOffset) {
      Platform.copyMemory(this.base, this.offset, target, targetOffset, (long)this.numBytes);
   }

   public void writeTo(ByteBuffer buffer) {
      assert buffer.hasArray();

      byte[] target = buffer.array();
      int offset = buffer.arrayOffset();
      int pos = buffer.position();
      this.writeToMemory(target, (long)(Platform.BYTE_ARRAY_OFFSET + offset + pos));
      buffer.position(pos + this.numBytes);
   }

   @Nonnull
   public ByteBuffer getByteBuffer() {
      Object var2 = this.base;
      if (var2 instanceof byte[] bytes) {
         if (this.offset >= (long)Platform.BYTE_ARRAY_OFFSET) {
            long arrayOffset = this.offset - (long)Platform.BYTE_ARRAY_OFFSET;
            if ((long)bytes.length < arrayOffset + (long)this.numBytes) {
               throw new ArrayIndexOutOfBoundsException();
            }

            return ByteBuffer.wrap(bytes, (int)arrayOffset, this.numBytes);
         }
      }

      return ByteBuffer.wrap(this.getBytes());
   }

   public void writeTo(OutputStream out) throws IOException {
      ByteBuffer bb = this.getByteBuffer();

      assert bb.hasArray();

      out.write(bb.array(), bb.arrayOffset() + bb.position(), bb.remaining());
   }

   public static int numBytesForFirstByte(byte b) {
      int offset = b & 255;
      byte numBytes = bytesOfCodePointInUTF8[offset];
      return numBytes == 0 ? 1 : numBytes;
   }

   public int numBytes() {
      return this.numBytes;
   }

   public int numChars() {
      if (this.numChars == -1) {
         this.numChars = this.getNumChars();
      }

      return this.numChars;
   }

   private int getNumChars() {
      int len = 0;

      for(int i = 0; i < this.numBytes; i += numBytesForFirstByte(this.getByte(i))) {
         ++len;
      }

      return len;
   }

   public long getPrefix() {
      return ByteArray.getPrefix(this.base, this.offset, this.numBytes);
   }

   public byte[] getBytes() {
      if (this.offset == (long)Platform.BYTE_ARRAY_OFFSET) {
         Object var2 = this.base;
         if (var2 instanceof byte[]) {
            byte[] bytes = (byte[])var2;
            if (bytes.length == this.numBytes) {
               return bytes;
            }
         }
      }

      byte[] bytes = new byte[this.numBytes];
      Platform.copyMemory(this.base, this.offset, bytes, (long)Platform.BYTE_ARRAY_OFFSET, (long)this.numBytes);
      return bytes;
   }

   private static boolean isValidContinuationByte(byte b) {
      return b >= -128 && b <= -65;
   }

   private static boolean isValidSecondByte(byte b, byte firstByte) {
      boolean var10000;
      switch (firstByte) {
         case -32 -> var10000 = b >= -96 && b <= -65;
         case -19 -> var10000 = b >= -128 && b <= -97;
         case -16 -> var10000 = b >= -112 && b <= -65;
         case -12 -> var10000 = b >= -128 && b <= -113;
         default -> var10000 = isValidContinuationByte(b);
      }

      return var10000;
   }

   private static void insertReplacementCharacter(byte[] bytes, int byteIndex) {
      for(byte b : UNICODE_REPLACEMENT_CHARACTER) {
         bytes[byteIndex++] = b;
      }

   }

   public UTF8String makeValid() {
      return this.isValid() ? this : fromBytes(this.makeValidBytes());
   }

   private byte[] makeValidBytes() {
      assert this.numBytesValid > 0;

      byte[] bytes = new byte[this.numBytesValid];
      int byteIndex = 0;
      int byteIndexValid = 0;

      while(byteIndex < this.numBytes) {
         byte firstByte = this.getByte(byteIndex);
         int expectedLen = bytesOfCodePointInUTF8[firstByte & 255];
         int codePointLen = Math.min(expectedLen, this.numBytes - byteIndex);
         if (codePointLen == 0) {
            insertReplacementCharacter(bytes, byteIndexValid);
            byteIndexValid += UNICODE_REPLACEMENT_CHARACTER.length;
            ++byteIndex;
         } else if (codePointLen == 1) {
            if (firstByte >= 0) {
               bytes[byteIndexValid++] = firstByte;
            } else {
               insertReplacementCharacter(bytes, byteIndexValid);
               byteIndexValid += UNICODE_REPLACEMENT_CHARACTER.length;
            }

            ++byteIndex;
         } else {
            byte secondByte = this.getByte(byteIndex + 1);
            if (!isValidSecondByte(secondByte, firstByte)) {
               insertReplacementCharacter(bytes, byteIndexValid);
               byteIndexValid += UNICODE_REPLACEMENT_CHARACTER.length;
               ++byteIndex;
            } else {
               int continuationBytes;
               for(continuationBytes = 2; continuationBytes < codePointLen; ++continuationBytes) {
                  byte nextByte = this.getByte(byteIndex + continuationBytes);
                  if (!isValidContinuationByte(nextByte)) {
                     break;
                  }
               }

               if (continuationBytes < expectedLen) {
                  insertReplacementCharacter(bytes, byteIndexValid);
                  byteIndexValid += UNICODE_REPLACEMENT_CHARACTER.length;
                  byteIndex += continuationBytes;
               } else {
                  for(int i = 0; i < codePointLen; ++i) {
                     bytes[byteIndexValid++] = this.getByte(byteIndex + i);
                  }

                  byteIndex += codePointLen;
               }
            }
         }
      }

      return bytes;
   }

   public boolean isValid() {
      if (this.isValid == UTF8String.UTF8StringValidity.UNKNOWN) {
         this.isValid = this.getIsValid();
      }

      return this.isValid == UTF8String.UTF8StringValidity.IS_VALID;
   }

   private UTF8StringValidity getIsValid() {
      boolean isValid = true;
      int byteIndex = 0;
      int byteCount = 0;

      while(byteIndex < this.numBytes) {
         byte firstByte = this.getByte(byteIndex);
         int expectedLen = bytesOfCodePointInUTF8[firstByte & 255];
         int codePointLen = Math.min(expectedLen, this.numBytes - byteIndex);
         if (codePointLen == 0) {
            byteCount += UNICODE_REPLACEMENT_CHARACTER.length;
            isValid = false;
            ++byteIndex;
         } else if (codePointLen == 1) {
            if (firstByte >= 0) {
               ++byteCount;
            } else {
               byteCount += UNICODE_REPLACEMENT_CHARACTER.length;
               isValid = false;
            }

            ++byteIndex;
         } else {
            byte secondByte = this.getByte(byteIndex + 1);
            if (!isValidSecondByte(secondByte, firstByte)) {
               byteCount += UNICODE_REPLACEMENT_CHARACTER.length;
               isValid = false;
               ++byteIndex;
            } else {
               int continuationBytes;
               for(continuationBytes = 2; continuationBytes < codePointLen; ++continuationBytes) {
                  byte nextByte = this.getByte(byteIndex + continuationBytes);
                  if (!isValidContinuationByte(nextByte)) {
                     break;
                  }
               }

               if (continuationBytes < expectedLen) {
                  byteCount += UNICODE_REPLACEMENT_CHARACTER.length;
                  isValid = false;
                  byteIndex += continuationBytes;
               } else {
                  for(int i = 0; i < codePointLen; ++i) {
                     ++byteCount;
                  }

                  byteIndex += codePointLen;
               }
            }
         }
      }

      this.setNumBytesValid(byteCount);
      return isValid ? UTF8String.UTF8StringValidity.IS_VALID : UTF8String.UTF8StringValidity.NOT_VALID;
   }

   private void setNumBytesValid(int byteCount) {
      if (byteCount < 0) {
         throw new IllegalStateException("Error in UTF-8 byte count");
      } else {
         this.numBytesValid = byteCount;
      }
   }

   public Iterator codePointIterator() {
      return this.codePointIterator(UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_ASSUME_VALID);
   }

   public Iterator codePointIterator(CodePointIteratorType iteratorMode) {
      return (Iterator)(iteratorMode == UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_MAKE_VALID ? this.makeValid().codePointIterator() : new CodePointIterator());
   }

   public Iterator reverseCodePointIterator() {
      return this.reverseCodePointIterator(UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_ASSUME_VALID);
   }

   public Iterator reverseCodePointIterator(CodePointIteratorType iteratorMode) {
      return (Iterator)(iteratorMode == UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_MAKE_VALID ? this.makeValid().reverseCodePointIterator() : new ReverseCodePointIterator());
   }

   public UTF8String substring(int start, int until) {
      if (until > start && start < this.numBytes) {
         int i = 0;

         int c;
         for(c = 0; i < this.numBytes && c < start; ++c) {
            i += numBytesForFirstByte(this.getByte(i));
         }

         int j;
         for(j = i; i < this.numBytes && c < until; ++c) {
            i += numBytesForFirstByte(this.getByte(i));
         }

         if (i > j) {
            byte[] bytes = new byte[i - j];
            Platform.copyMemory(this.base, this.offset + (long)j, bytes, (long)Platform.BYTE_ARRAY_OFFSET, (long)(i - j));
            return fromBytes(bytes);
         } else {
            return EMPTY_UTF8;
         }
      } else {
         return EMPTY_UTF8;
      }
   }

   public UTF8String substringSQL(int pos, int length) {
      int len = this.numChars();
      int start = pos > 0 ? pos - 1 : (pos < 0 ? len + pos : 0);
      int end;
      if ((long)start + (long)length > 2147483647L) {
         end = Integer.MAX_VALUE;
      } else if ((long)start + (long)length < -2147483648L) {
         end = Integer.MIN_VALUE;
      } else {
         end = start + length;
      }

      return this.substring(start, end);
   }

   public boolean contains(UTF8String substring) {
      if (substring.numBytes == 0) {
         return true;
      } else {
         byte first = substring.getByte(0);

         for(int i = 0; i <= this.numBytes - substring.numBytes; ++i) {
            if (this.getByte(i) == first && this.matchAt(substring, i)) {
               return true;
            }
         }

         return false;
      }
   }

   public byte getByte(int byteIndex) {
      return Platform.getByte(this.base, this.offset + (long)byteIndex);
   }

   public int getChar(int charIndex) {
      if (charIndex >= 0 && charIndex < this.numChars()) {
         int charCount = 0;

         int byteCount;
         for(byteCount = 0; charCount < charIndex; ++charCount) {
            byteCount += numBytesForFirstByte(this.getByte(byteCount));
         }

         return this.codePointFrom(byteCount);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public int codePointFrom(int byteIndex) {
      if (byteIndex >= 0 && byteIndex < this.numBytes) {
         byte b = this.getByte(byteIndex);
         int numBytes = numBytesForFirstByte(b);
         int var10000;
         switch (numBytes) {
            case 1 -> var10000 = b & 127;
            case 2 -> var10000 = (b & 31) << 6 | this.getByte(byteIndex + 1) & 63;
            case 3 -> var10000 = (b & 15) << 12 | (this.getByte(byteIndex + 1) & 63) << 6 | this.getByte(byteIndex + 2) & 63;
            case 4 -> var10000 = (b & 7) << 18 | (this.getByte(byteIndex + 1) & 63) << 12 | (this.getByte(byteIndex + 2) & 63) << 6 | this.getByte(byteIndex + 3) & 63;
            default -> throw new IllegalStateException("Error in UTF-8 code point");
         }

         return var10000;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public boolean matchAt(UTF8String s, int pos) {
      return s.numBytes + pos <= this.numBytes && pos >= 0 ? ByteArrayMethods.arrayEquals(this.base, this.offset + (long)pos, s.base, s.offset, (long)s.numBytes) : false;
   }

   public boolean startsWith(UTF8String prefix) {
      return this.matchAt(prefix, 0);
   }

   public boolean endsWith(UTF8String suffix) {
      return this.matchAt(suffix, this.numBytes - suffix.numBytes);
   }

   private UTF8String convertAscii(Function charConverter) {
      byte[] bytes = new byte[this.numBytes];

      for(int i = 0; i < this.numBytes; ++i) {
         bytes[i] = (byte)(Character)charConverter.apply((char)this.getByte(i));
      }

      return fromBytes(bytes);
   }

   public UTF8String toUpperCase() {
      if (this.numBytes == 0) {
         return EMPTY_UTF8;
      } else {
         return this.isFullAscii() ? this.toUpperCaseAscii() : this.toUpperCaseSlow();
      }
   }

   public UTF8String toUpperCaseAscii() {
      return this.convertAscii(Character::toUpperCase);
   }

   private UTF8String toUpperCaseSlow() {
      return fromString(this.toString().toUpperCase());
   }

   public UTF8String toLowerCase() {
      if (this.numBytes == 0) {
         return EMPTY_UTF8;
      } else {
         return this.isFullAscii() ? this.toLowerCaseAscii() : this.toLowerCaseSlow();
      }
   }

   public boolean isFullAscii() {
      if (this.isFullAscii == UTF8String.IsFullAscii.UNKNOWN) {
         this.isFullAscii = this.getIsFullAscii();
      }

      return this.isFullAscii == UTF8String.IsFullAscii.FULL_ASCII;
   }

   private IsFullAscii getIsFullAscii() {
      for(int i = 0; i < this.numBytes; ++i) {
         if (this.getByte(i) < 0) {
            return UTF8String.IsFullAscii.NOT_ASCII;
         }
      }

      return UTF8String.IsFullAscii.FULL_ASCII;
   }

   private UTF8String toLowerCaseSlow() {
      return fromString(this.toString().toLowerCase());
   }

   public UTF8String toLowerCaseAscii() {
      return this.convertAscii(Character::toLowerCase);
   }

   public UTF8String toTitleCase() {
      if (this.numBytes == 0) {
         return EMPTY_UTF8;
      } else {
         return this.isFullAscii() ? this.toTitleCaseAscii() : this.toTitleCaseSlow();
      }
   }

   public UTF8String toTitleCaseICU() {
      if (this.numBytes == 0) {
         return EMPTY_UTF8;
      } else {
         return this.isFullAscii() ? this.toTitleCaseAscii() : this.toTitleCaseSlowICU();
      }
   }

   private UTF8String toTitleCaseAscii() {
      byte[] bytes = new byte[this.numBytes];
      byte prev = 32;

      for(int i = 0; i < this.numBytes; ++i) {
         byte curr = this.getByte(i);
         if (prev == 32) {
            bytes[i] = (byte)Character.toTitleCase(curr);
         } else {
            bytes[i] = curr;
         }

         prev = curr;
      }

      return fromBytes(bytes);
   }

   private UTF8String toTitleCaseSlow() {
      StringBuilder sb = new StringBuilder();
      String s = this.toString();
      sb.append(s);
      sb.setCharAt(0, Character.toTitleCase(sb.charAt(0)));

      for(int i = 1; i < s.length(); ++i) {
         if (sb.charAt(i - 1) == ' ') {
            sb.setCharAt(i, Character.toTitleCase(sb.charAt(i)));
         }
      }

      return fromString(sb.toString());
   }

   private UTF8String toTitleCaseSlowICU() {
      StringBuilder sb = new StringBuilder();
      String s = this.toString();
      sb.append(s);
      sb.setCharAt(0, (char)UCharacter.toTitleCase(sb.charAt(0)));

      for(int i = 1; i < s.length(); ++i) {
         if (sb.charAt(i - 1) == ' ') {
            sb.setCharAt(i, (char)UCharacter.toTitleCase(sb.charAt(i)));
         }
      }

      return fromString(sb.toString());
   }

   public int findInSet(UTF8String match) {
      if (match.contains(COMMA_UTF8)) {
         return 0;
      } else {
         int n = 1;
         int lastComma = -1;

         for(int i = 0; i < this.numBytes; ++i) {
            if (this.getByte(i) == 44) {
               if (i - (lastComma + 1) == match.numBytes && ByteArrayMethods.arrayEquals(this.base, this.offset + (long)(lastComma + 1), match.base, match.offset, (long)match.numBytes)) {
                  return n;
               }

               lastComma = i;
               ++n;
            }
         }

         if (this.numBytes - (lastComma + 1) == match.numBytes && ByteArrayMethods.arrayEquals(this.base, this.offset + (long)(lastComma + 1), match.base, match.offset, (long)match.numBytes)) {
            return n;
         } else {
            return 0;
         }
      }
   }

   public UTF8String copyUTF8String(int start, int end) {
      int len = end - start + 1;
      byte[] newBytes = new byte[len];
      Platform.copyMemory(this.base, this.offset + (long)start, newBytes, (long)Platform.BYTE_ARRAY_OFFSET, (long)len);
      return fromBytes(newBytes);
   }

   public UTF8String trim() {
      int s;
      for(s = 0; s < this.numBytes && this.getByte(s) == 32; ++s) {
      }

      if (s == this.numBytes) {
         return EMPTY_UTF8;
      } else {
         int e;
         for(e = this.numBytes - 1; e > s && this.getByte(e) == 32; --e) {
         }

         return s == 0 && e == this.numBytes - 1 ? this : this.copyUTF8String(s, e);
      }
   }

   public UTF8String trimAll() {
      int s;
      for(s = 0; s < this.numBytes && isWhitespaceOrISOControl(this.getByte(s)); ++s) {
      }

      if (s == this.numBytes) {
         return EMPTY_UTF8;
      } else {
         int e;
         for(e = this.numBytes - 1; e > s && isWhitespaceOrISOControl(this.getByte(e)); --e) {
         }

         return s == 0 && e == this.numBytes - 1 ? this : this.copyUTF8String(s, e);
      }
   }

   public UTF8String trim(UTF8String trimString) {
      return trimString != null ? this.trimLeft(trimString).trimRight(trimString) : null;
   }

   public UTF8String trimLeft() {
      int s;
      for(s = 0; s < this.numBytes && this.getByte(s) == 32; ++s) {
      }

      if (s == 0) {
         return this;
      } else {
         return s == this.numBytes ? EMPTY_UTF8 : this.copyUTF8String(s, this.numBytes - 1);
      }
   }

   public UTF8String trimLeft(UTF8String trimString) {
      if (trimString == null) {
         return null;
      } else {
         int searchIdx = 0;

         int trimIdx;
         int searchCharBytes;
         for(trimIdx = 0; searchIdx < this.numBytes; searchIdx += searchCharBytes) {
            UTF8String searchChar = this.copyUTF8String(searchIdx, searchIdx + numBytesForFirstByte(this.getByte(searchIdx)) - 1);
            searchCharBytes = searchChar.numBytes;
            if (trimString.find(searchChar, 0) < 0) {
               break;
            }

            trimIdx += searchCharBytes;
         }

         if (searchIdx == 0) {
            return this;
         } else {
            return trimIdx >= this.numBytes ? EMPTY_UTF8 : this.copyUTF8String(trimIdx, this.numBytes - 1);
         }
      }
   }

   public UTF8String trimRight() {
      int e;
      for(e = this.numBytes - 1; e >= 0 && this.getByte(e) == 32; --e) {
      }

      if (e == this.numBytes - 1) {
         return this;
      } else {
         return e < 0 ? EMPTY_UTF8 : this.copyUTF8String(0, e);
      }
   }

   public UTF8String trimTrailingSpaces(int numSpaces) {
      assert numSpaces > 0;

      int endIdx = this.numBytes - 1;

      for(int trimTo = this.numBytes - numSpaces; endIdx >= trimTo && this.getByte(endIdx) == 32; --endIdx) {
      }

      return this.copyUTF8String(0, endIdx);
   }

   public UTF8String trimRight(UTF8String trimString) {
      if (trimString == null) {
         return null;
      } else {
         int charIdx = 0;
         int numChars = 0;
         int[] stringCharLen = new int[this.numBytes];

         int[] stringCharPos;
         for(stringCharPos = new int[this.numBytes]; charIdx < this.numBytes; ++numChars) {
            stringCharPos[numChars] = charIdx;
            stringCharLen[numChars] = numBytesForFirstByte(this.getByte(charIdx));
            charIdx += stringCharLen[numChars];
         }

         int trimEnd;
         for(trimEnd = this.numBytes - 1; numChars > 0; --numChars) {
            UTF8String searchChar = this.copyUTF8String(stringCharPos[numChars - 1], stringCharPos[numChars - 1] + stringCharLen[numChars - 1] - 1);
            if (trimString.find(searchChar, 0) < 0) {
               break;
            }

            trimEnd -= stringCharLen[numChars - 1];
         }

         if (trimEnd == this.numBytes - 1) {
            return this;
         } else {
            return trimEnd < 0 ? EMPTY_UTF8 : this.copyUTF8String(0, trimEnd);
         }
      }
   }

   public UTF8String reverse() {
      byte[] result = new byte[this.numBytes];

      int len;
      for(int i = 0; i < this.numBytes; i += len) {
         len = numBytesForFirstByte(this.getByte(i));
         Platform.copyMemory(this.base, this.offset + (long)i, result, (long)(Platform.BYTE_ARRAY_OFFSET + result.length - i - len), (long)len);
      }

      return fromBytes(result);
   }

   public UTF8String repeat(int times) {
      if (times <= 0) {
         return EMPTY_UTF8;
      } else {
         byte[] newBytes = new byte[Math.multiplyExact(this.numBytes, times)];
         Platform.copyMemory(this.base, this.offset, newBytes, (long)Platform.BYTE_ARRAY_OFFSET, (long)this.numBytes);

         int toCopy;
         for(int copied = 1; copied < times; copied += toCopy) {
            toCopy = Math.min(copied, times - copied);
            System.arraycopy(newBytes, 0, newBytes, copied * this.numBytes, this.numBytes * toCopy);
         }

         return fromBytes(newBytes);
      }
   }

   public int indexOfEmpty(int start) {
      return 0;
   }

   public int indexOf(UTF8String v, int start) {
      if (v.numBytes() == 0) {
         return this.indexOfEmpty(start);
      } else {
         int i = 0;

         int c;
         for(c = 0; i < this.numBytes && c < start; ++c) {
            i += numBytesForFirstByte(this.getByte(i));
         }

         while(i + v.numBytes <= this.numBytes) {
            if (ByteArrayMethods.arrayEquals(this.base, this.offset + (long)i, v.base, v.offset, (long)v.numBytes)) {
               return c;
            }

            i += numBytesForFirstByte(this.getByte(i));
            ++c;
            if (i >= this.numBytes) {
               return -1;
            }
         }

         return -1;
      }
   }

   public int charPosToByte(int charPos) {
      if (charPos < 0) {
         return -1;
      } else {
         int i = 0;

         for(int c = 0; i < this.numBytes && c < charPos; ++c) {
            i += numBytesForFirstByte(this.getByte(i));
         }

         return i;
      }
   }

   public int bytePosToChar(int bytePos) {
      int i = 0;

      int c;
      for(c = 0; i < this.numBytes && i < bytePos; ++c) {
         i += numBytesForFirstByte(this.getByte(i));
      }

      return c;
   }

   public int find(UTF8String str, int start) {
      assert str.numBytes > 0;

      while(start <= this.numBytes - str.numBytes) {
         if (ByteArrayMethods.arrayEquals(this.base, this.offset + (long)start, str.base, str.offset, (long)str.numBytes)) {
            return start;
         }

         ++start;
      }

      return -1;
   }

   public int rfind(UTF8String str, int start) {
      assert str.numBytes > 0;

      while(start >= 0) {
         if (ByteArrayMethods.arrayEquals(this.base, this.offset + (long)start, str.base, str.offset, (long)str.numBytes)) {
            return start;
         }

         --start;
      }

      return -1;
   }

   public UTF8String subStringIndex(UTF8String delim, int count) {
      if (delim.numBytes != 0 && count != 0) {
         if (count > 0) {
            int idx;
            for(idx = -1; count > 0; --count) {
               idx = this.find(delim, idx + 1);
               if (idx < 0) {
                  return this;
               }
            }

            if (idx == 0) {
               return EMPTY_UTF8;
            } else {
               byte[] bytes = new byte[idx];
               Platform.copyMemory(this.base, this.offset, bytes, (long)Platform.BYTE_ARRAY_OFFSET, (long)idx);
               return fromBytes(bytes);
            }
         } else {
            int idx = this.numBytes - delim.numBytes + 1;

            for(int var6 = -count; var6 > 0; --var6) {
               idx = this.rfind(delim, idx - 1);
               if (idx < 0) {
                  return this;
               }
            }

            if (idx + delim.numBytes == this.numBytes) {
               return EMPTY_UTF8;
            } else {
               int size = this.numBytes - delim.numBytes - idx;
               byte[] bytes = new byte[size];
               Platform.copyMemory(this.base, this.offset + (long)idx + (long)delim.numBytes, bytes, (long)Platform.BYTE_ARRAY_OFFSET, (long)size);
               return fromBytes(bytes);
            }
         }
      } else {
         return EMPTY_UTF8;
      }
   }

   public UTF8String rpad(int len, UTF8String pad) {
      int spaces = len - this.numChars();
      if (spaces > 0 && pad.numBytes() != 0) {
         int padChars = pad.numChars();
         int count = spaces / padChars;
         UTF8String remain = pad.substring(0, spaces - padChars * count);
         int resultSize = Math.toIntExact((long)this.numBytes + (long)pad.numBytes * (long)count + (long)remain.numBytes);
         byte[] data = new byte[resultSize];
         Platform.copyMemory(this.base, this.offset, data, (long)Platform.BYTE_ARRAY_OFFSET, (long)this.numBytes);
         int offset = this.numBytes;

         for(int idx = 0; idx < count; offset += pad.numBytes) {
            Platform.copyMemory(pad.base, pad.offset, data, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)pad.numBytes);
            ++idx;
         }

         Platform.copyMemory(remain.base, remain.offset, data, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)remain.numBytes);
         return fromBytes(data);
      } else {
         return this.substring(0, len);
      }
   }

   public UTF8String lpad(int len, UTF8String pad) {
      int spaces = len - this.numChars();
      if (spaces > 0 && pad.numBytes() != 0) {
         int padChars = pad.numChars();
         int count = spaces / padChars;
         UTF8String remain = pad.substring(0, spaces - padChars * count);
         int resultSize = Math.toIntExact((long)this.numBytes + (long)pad.numBytes * (long)count + (long)remain.numBytes);
         byte[] data = new byte[resultSize];
         int offset = 0;

         for(int idx = 0; idx < count; offset += pad.numBytes) {
            Platform.copyMemory(pad.base, pad.offset, data, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)pad.numBytes);
            ++idx;
         }

         Platform.copyMemory(remain.base, remain.offset, data, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)remain.numBytes);
         offset += remain.numBytes;
         Platform.copyMemory(this.base, this.offset, data, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)this.numBytes());
         return fromBytes(data);
      } else {
         return this.substring(0, len);
      }
   }

   public static UTF8String concat(UTF8String... inputs) {
      long totalLength = 0L;

      for(UTF8String input : inputs) {
         if (input == null) {
            return null;
         }

         totalLength += (long)input.numBytes;
      }

      byte[] result = new byte[Math.toIntExact(totalLength)];
      int offset = 0;

      for(UTF8String input : inputs) {
         int len = input.numBytes;
         Platform.copyMemory(input.base, input.offset, result, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)len);
         offset += len;
      }

      return fromBytes(result);
   }

   public static UTF8String concatWs(UTF8String separator, UTF8String... inputs) {
      if (separator == null) {
         return null;
      } else {
         long numInputBytes = 0L;
         int numInputs = 0;

         for(UTF8String input : inputs) {
            if (input != null) {
               numInputBytes += (long)input.numBytes;
               ++numInputs;
            }
         }

         if (numInputs == 0) {
            return EMPTY_UTF8;
         } else {
            int resultSize = Math.toIntExact(numInputBytes + (long)(numInputs - 1) * (long)separator.numBytes);
            byte[] result = new byte[resultSize];
            int offset = 0;
            int i = 0;

            for(int j = 0; i < inputs.length; ++i) {
               if (inputs[i] != null) {
                  int len = inputs[i].numBytes;
                  Platform.copyMemory(inputs[i].base, inputs[i].offset, result, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)len);
                  offset += len;
                  ++j;
                  if (j < numInputs) {
                     Platform.copyMemory(separator.base, separator.offset, result, (long)(Platform.BYTE_ARRAY_OFFSET + offset), (long)separator.numBytes);
                     offset += separator.numBytes;
                  }
               }
            }

            return fromBytes(result);
         }
      }
   }

   public UTF8String[] split(UTF8String pattern, int limit) {
      if (this.numBytes() != 0 && pattern.numBytes() == 0) {
         int newLimit = limit <= this.numChars() && limit > 0 ? limit : this.numChars();
         byte[] input = this.getBytes();
         int byteIndex = 0;
         int charIndex = 0;

         UTF8String[] result;
         int currCharNumBytes;
         for(result = new UTF8String[newLimit]; charIndex < newLimit; byteIndex += currCharNumBytes) {
            currCharNumBytes = numBytesForFirstByte(input[byteIndex]);
            result[charIndex++] = fromBytes(input, byteIndex, currCharNumBytes);
         }

         return result;
      } else {
         return this.split(pattern.toString(), limit);
      }
   }

   public UTF8String[] splitSQL(UTF8String delimiter, int limit) {
      return delimiter.numBytes() == 0 ? new UTF8String[]{this} : this.split(Pattern.quote(delimiter.toString()), limit);
   }

   private UTF8String[] split(String delimiter, int limit) {
      if (limit == 0) {
         limit = -1;
      }

      String[] splits = this.toString().split(delimiter, limit);
      UTF8String[] res = new UTF8String[splits.length];

      for(int i = 0; i < res.length; ++i) {
         res[i] = fromString(splits[i]);
      }

      return res;
   }

   public UTF8String replace(UTF8String search, UTF8String replace) {
      if (this.numBytes != 0 && search.numBytes != 0) {
         int start = 0;
         int end = this.find(search, start);
         if (end == -1) {
            return this;
         } else {
            int increase = Math.max(0, replace.numBytes - search.numBytes) * 16;

            UTF8StringBuilder buf;
            for(buf = new UTF8StringBuilder(this.numBytes + increase); end != -1; end = this.find(search, start)) {
               buf.appendBytes(this.base, this.offset + (long)start, end - start);
               buf.append(replace);
               start = end + search.numBytes;
            }

            buf.appendBytes(this.base, this.offset + (long)start, this.numBytes - start);
            return buf.build();
         }
      } else {
         return this;
      }
   }

   public UTF8String translate(Map dict) {
      String srcStr = this.toString();
      StringBuilder sb = new StringBuilder();
      int charCount = 0;

      for(int k = 0; k < srcStr.length(); k += charCount) {
         int codePoint = srcStr.codePointAt(k);
         charCount = Character.charCount(codePoint);
         String subStr = srcStr.substring(k, k + charCount);
         String translated = (String)dict.get(subStr);
         if (null == translated) {
            sb.append(subStr);
         } else if (!"\u0000".equals(translated)) {
            sb.append(translated);
         }
      }

      return fromString(sb.toString());
   }

   public boolean toLong(LongWrapper toLongResult) {
      return this.toLong(toLongResult, true);
   }

   private boolean toLong(LongWrapper toLongResult, boolean allowDecimal) {
      int offset;
      for(offset = 0; offset < this.numBytes && isWhitespaceOrISOControl(this.getByte(offset)); ++offset) {
      }

      if (offset == this.numBytes) {
         return false;
      } else {
         int end;
         for(end = this.numBytes - 1; end > offset && isWhitespaceOrISOControl(this.getByte(end)); --end) {
         }

         byte b = this.getByte(offset);
         boolean negative = b == 45;
         if (negative || b == 43) {
            if (end - offset == 0) {
               return false;
            }

            ++offset;
         }

         byte separator = 46;
         int radix = 10;
         long stopValue = -922337203685477580L;
         long result = 0L;

         while(offset <= end) {
            b = this.getByte(offset);
            ++offset;
            if (b == 46 && allowDecimal) {
               break;
            }

            if (b < 48 || b > 57) {
               return false;
            }

            int digit = b - 48;
            if (result < -922337203685477580L) {
               return false;
            }

            result = result * 10L - (long)digit;
            if (result > 0L) {
               return false;
            }
         }

         while(offset <= end) {
            byte currentByte = this.getByte(offset);
            if (currentByte < 48 || currentByte > 57) {
               return false;
            }

            ++offset;
         }

         if (!negative) {
            result = -result;
            if (result < 0L) {
               return false;
            }
         }

         toLongResult.value = result;
         return true;
      }
   }

   public boolean toInt(IntWrapper intWrapper) {
      return this.toInt(intWrapper, true);
   }

   private boolean toInt(IntWrapper intWrapper, boolean allowDecimal) {
      int offset;
      for(offset = 0; offset < this.numBytes && isWhitespaceOrISOControl(this.getByte(offset)); ++offset) {
      }

      if (offset == this.numBytes) {
         return false;
      } else {
         int end;
         for(end = this.numBytes - 1; end > offset && isWhitespaceOrISOControl(this.getByte(end)); --end) {
         }

         byte b = this.getByte(offset);
         boolean negative = b == 45;
         if (negative || b == 43) {
            if (end - offset == 0) {
               return false;
            }

            ++offset;
         }

         byte separator = 46;
         int radix = 10;
         int stopValue = -214748364;
         int result = 0;

         while(offset <= end) {
            b = this.getByte(offset);
            ++offset;
            if (b == 46 && allowDecimal) {
               break;
            }

            if (b < 48 || b > 57) {
               return false;
            }

            int digit = b - 48;
            if (result < -214748364) {
               return false;
            }

            result = result * 10 - digit;
            if (result > 0) {
               return false;
            }
         }

         while(offset <= end) {
            byte currentByte = this.getByte(offset);
            if (currentByte < 48 || currentByte > 57) {
               return false;
            }

            ++offset;
         }

         if (!negative) {
            result = -result;
            if (result < 0) {
               return false;
            }
         }

         intWrapper.value = result;
         return true;
      }
   }

   public boolean toShort(IntWrapper intWrapper) {
      if (this.toInt(intWrapper)) {
         int intValue = intWrapper.value;
         short result = (short)intValue;
         return result == intValue;
      } else {
         return false;
      }
   }

   public boolean toByte(IntWrapper intWrapper) {
      if (this.toInt(intWrapper)) {
         int intValue = intWrapper.value;
         byte result = (byte)intValue;
         return result == intValue;
      } else {
         return false;
      }
   }

   public long toLongExact() {
      LongWrapper result = new LongWrapper();
      if (this.toLong(result, false)) {
         return result.value;
      } else {
         throw new NumberFormatException("invalid input syntax for type numeric: '" + String.valueOf(this) + "'");
      }
   }

   public int toIntExact() {
      IntWrapper result = new IntWrapper();
      if (this.toInt(result, false)) {
         return result.value;
      } else {
         throw new NumberFormatException("invalid input syntax for type numeric: '" + String.valueOf(this) + "'");
      }
   }

   public short toShortExact() {
      int value = this.toIntExact();
      short result = (short)value;
      if (result == value) {
         return result;
      } else {
         throw new NumberFormatException("invalid input syntax for type numeric: '" + String.valueOf(this) + "'");
      }
   }

   public byte toByteExact() {
      int value = this.toIntExact();
      byte result = (byte)value;
      if (result == value) {
         return result;
      } else {
         throw new NumberFormatException("invalid input syntax for type numeric: '" + String.valueOf(this) + "'");
      }
   }

   public String toString() {
      return new String(this.getBytes(), StandardCharsets.UTF_8);
   }

   public String toValidString() {
      return this.isValid() ? this.toString() : new String(this.makeValidBytes(), StandardCharsets.UTF_8);
   }

   public UTF8String clone() {
      return fromBytes(this.getBytes());
   }

   public UTF8String copy() {
      byte[] bytes = new byte[this.numBytes];
      Platform.copyMemory(this.base, this.offset, bytes, (long)Platform.BYTE_ARRAY_OFFSET, (long)this.numBytes);
      return fromBytes(bytes);
   }

   public int compareTo(@Nonnull UTF8String other) {
      if (.MODULE$.isTesting()) {
         throw new UnsupportedOperationException("compareTo should not be used in spark code base. Use binaryCompare or semanticCompare.");
      } else {
         return this.binaryCompare(other);
      }
   }

   public int binaryCompare(UTF8String other) {
      return ByteArray.compareBinary(this.base, this.offset, this.numBytes, other.base, other.offset, other.numBytes);
   }

   public int semanticCompare(UTF8String other, int collationId) {
      return CollationFactory.fetchCollation(collationId).comparator.compare(this, other);
   }

   public boolean equals(Object other) {
      if (other instanceof UTF8String o) {
         return this.binaryEquals(o);
      } else {
         return false;
      }
   }

   public boolean binaryEquals(UTF8String other) {
      return this.numBytes != other.numBytes ? false : ByteArrayMethods.arrayEquals(this.base, this.offset, other.base, other.offset, (long)this.numBytes);
   }

   public boolean semanticEquals(UTF8String other, int collationId) {
      return (Boolean)CollationFactory.fetchCollation(collationId).equalsFunction.apply(this, other);
   }

   public int levenshteinDistance(UTF8String other) {
      int n = this.numChars();
      int m = other.numChars();
      if (n == 0) {
         return m;
      } else if (m == 0) {
         return n;
      } else {
         UTF8String s;
         UTF8String t;
         if (n <= m) {
            s = this;
            t = other;
         } else {
            s = other;
            t = this;
            int swap = n;
            n = m;
            m = swap;
         }

         int[] p = new int[n + 1];
         int[] d = new int[n + 1];

         for(int i = 0; i <= n; p[i] = i++) {
         }

         int j = 0;

         for(int j_bytes = 0; j < m; ++j) {
            int num_bytes_j = numBytesForFirstByte(t.getByte(j_bytes));
            d[0] = j + 1;
            int var16 = 0;

            for(int i_bytes = 0; var16 < n; ++var16) {
               int cost;
               if (s.getByte(i_bytes) == t.getByte(j_bytes) && num_bytes_j == numBytesForFirstByte(s.getByte(i_bytes))) {
                  cost = ByteArrayMethods.arrayEquals(t.base, t.offset + (long)j_bytes, s.base, s.offset + (long)i_bytes, (long)num_bytes_j) ? 0 : 1;
               } else {
                  cost = 1;
               }

               d[var16 + 1] = Math.min(Math.min(d[var16] + 1, p[var16 + 1] + 1), p[var16] + cost);
               i_bytes += numBytesForFirstByte(s.getByte(i_bytes));
            }

            int[] swap = p;
            p = d;
            d = swap;
            j_bytes += num_bytes_j;
         }

         return p[n];
      }
   }

   public int levenshteinDistance(UTF8String other, int threshold) {
      int n = this.numChars();
      int m = other.numChars();
      if (n == 0) {
         return m <= threshold ? m : -1;
      } else if (m == 0) {
         return n <= threshold ? n : -1;
      } else {
         UTF8String s;
         UTF8String t;
         if (n <= m) {
            s = this;
            t = other;
         } else {
            s = other;
            t = this;
            int swap = n;
            n = m;
            m = swap;
         }

         if (m - n > threshold) {
            return -1;
         } else {
            int[] p = new int[n + 1];
            int[] d = new int[n + 1];
            int boundary = Math.min(n, threshold) + 1;

            for(int i = 0; i < boundary; p[i] = i++) {
            }

            Arrays.fill(p, boundary, p.length, Integer.MAX_VALUE);
            Arrays.fill(d, Integer.MAX_VALUE);
            int j = 0;

            for(int j_bytes = 0; j < m; ++j) {
               int num_bytes_j = numBytesForFirstByte(t.getByte(j_bytes));
               d[0] = j + 1;
               int min = Math.max(1, j + 1 - threshold);
               int max = j + 1 > Integer.MAX_VALUE - threshold ? n : Math.min(n, j + 1 + threshold);
               if (min > 1) {
                  d[min - 1] = Integer.MAX_VALUE;
               }

               int lowerBound = Integer.MAX_VALUE;
               int var21 = 0;

               for(int i_bytes = 0; var21 <= max; ++var21) {
                  int num_bytes_i;
                  if (var21 < min - 1) {
                     num_bytes_i = numBytesForFirstByte(s.getByte(i_bytes));
                  } else if (var21 == min - 1) {
                     num_bytes_i = 0;
                  } else {
                     if (ByteArrayMethods.arrayEquals(t.base, t.offset + (long)j_bytes, s.base, s.offset + (long)i_bytes, (long)num_bytes_j)) {
                        d[var21] = p[var21 - 1];
                     } else {
                        d[var21] = 1 + Math.min(Math.min(d[var21 - 1], p[var21]), p[var21 - 1]);
                     }

                     lowerBound = Math.min(lowerBound, d[var21]);
                     num_bytes_i = numBytesForFirstByte(s.getByte(i_bytes));
                  }

                  i_bytes += num_bytes_i;
               }

               if (lowerBound > threshold) {
                  return -1;
               }

               int[] swap = p;
               p = d;
               d = swap;
               j_bytes += num_bytes_j;
            }

            if (p[n] <= threshold) {
               return p[n];
            } else {
               return -1;
            }
         }
      }
   }

   public int hashCode() {
      return Murmur3_x86_32.hashUnsafeBytes(this.base, this.offset, this.numBytes, 42);
   }

   public UTF8String soundex() {
      if (this.numBytes == 0) {
         return EMPTY_UTF8;
      } else {
         byte b = this.getByte(0);
         if (97 <= b && b <= 122) {
            b = (byte)(b - 32);
         } else if (b < 65 || 90 < b) {
            return this;
         }

         byte[] sx = new byte[]{48, 48, 48, 48};
         sx[0] = b;
         int sxi = 1;
         int idx = b - 65;
         byte lastCode = US_ENGLISH_MAPPING[idx];

         for(int i = 1; i < this.numBytes; ++i) {
            b = this.getByte(i);
            if (97 <= b && b <= 122) {
               b = (byte)(b - 32);
            } else if (b < 65 || 90 < b) {
               lastCode = 48;
               continue;
            }

            idx = b - 65;
            byte code = US_ENGLISH_MAPPING[idx];
            if (code != 55) {
               if (code != 48 && code != lastCode) {
                  sx[sxi++] = code;
                  if (sxi > 3) {
                     break;
                  }
               }

               lastCode = code;
            }
         }

         return fromBytes(sx);
      }
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      byte[] bytes = this.getBytes();
      out.writeInt(bytes.length);
      out.write(bytes);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.offset = (long)Platform.BYTE_ARRAY_OFFSET;
      this.numBytes = in.readInt();
      this.base = new byte[this.numBytes];
      in.readFully((byte[])this.base);
   }

   public void write(Kryo kryo, Output out) {
      byte[] bytes = this.getBytes();
      out.writeInt(bytes.length);
      out.write(bytes);
   }

   public void read(Kryo kryo, Input in) {
      this.offset = (long)Platform.BYTE_ARRAY_OFFSET;
      this.numBytes = in.readInt();
      this.base = new byte[this.numBytes];
      in.read((byte[])this.base);
   }

   public static UTF8String toBinaryString(long val) {
      int zeros = Long.numberOfLeadingZeros(val);
      if (zeros == 64) {
         return ZERO_UTF8;
      } else {
         int length = 64 - zeros;
         byte[] bytes = new byte[length];

         do {
            --length;
            bytes[length] = (byte)((val & 1L) == 1L ? 49 : 48);
            val >>>= 1;
         } while(length > 0);

         return fromBytes(bytes);
      }
   }

   private static enum UTF8StringValidity {
      UNKNOWN,
      IS_VALID,
      NOT_VALID;

      // $FF: synthetic method
      private static UTF8StringValidity[] $values() {
         return new UTF8StringValidity[]{UNKNOWN, IS_VALID, NOT_VALID};
      }
   }

   private static enum IsFullAscii {
      UNKNOWN,
      FULL_ASCII,
      NOT_ASCII;

      // $FF: synthetic method
      private static IsFullAscii[] $values() {
         return new IsFullAscii[]{UNKNOWN, FULL_ASCII, NOT_ASCII};
      }
   }

   public static enum CodePointIteratorType {
      CODE_POINT_ITERATOR_ASSUME_VALID,
      CODE_POINT_ITERATOR_MAKE_VALID;

      // $FF: synthetic method
      private static CodePointIteratorType[] $values() {
         return new CodePointIteratorType[]{CODE_POINT_ITERATOR_ASSUME_VALID, CODE_POINT_ITERATOR_MAKE_VALID};
      }
   }

   private class CodePointIterator implements Iterator {
      private int byteIndex = 0;

      public boolean hasNext() {
         return this.byteIndex < UTF8String.this.numBytes;
      }

      public Integer next() {
         if (!this.hasNext()) {
            throw new IndexOutOfBoundsException();
         } else {
            int codePoint = UTF8String.this.codePointFrom(this.byteIndex);
            this.byteIndex += UTF8String.numBytesForFirstByte(UTF8String.this.getByte(this.byteIndex));
            return codePoint;
         }
      }
   }

   private class ReverseCodePointIterator implements Iterator {
      private int byteIndex;

      private ReverseCodePointIterator() {
         this.byteIndex = UTF8String.this.numBytes - 1;
      }

      public boolean hasNext() {
         return this.byteIndex >= 0;
      }

      public Integer next() {
         if (!this.hasNext()) {
            throw new IndexOutOfBoundsException();
         } else {
            while(this.byteIndex > 0 && this.isContinuationByte(UTF8String.this.getByte(this.byteIndex))) {
               --this.byteIndex;
            }

            return UTF8String.this.codePointFrom(this.byteIndex--);
         }
      }

      private boolean isContinuationByte(byte b) {
         return (b & 192) == 128;
      }
   }

   public static class LongWrapper implements Serializable {
      public transient long value = 0L;
   }

   public static class IntWrapper implements Serializable {
      public transient int value = 0;
   }
}
