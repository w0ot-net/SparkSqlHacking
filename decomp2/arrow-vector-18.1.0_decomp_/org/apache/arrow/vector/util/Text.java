package org.apache.arrow.vector.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.DataInput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Optional;

@JsonSerialize(
   using = TextSerializer.class
)
public class Text extends ReusableByteArray {
   private static ThreadLocal ENCODER_FACTORY = new ThreadLocal() {
      protected CharsetEncoder initialValue() {
         return Charset.forName("UTF-8").newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
      }
   };
   private static ThreadLocal DECODER_FACTORY = new ThreadLocal() {
      protected CharsetDecoder initialValue() {
         return Charset.forName("UTF-8").newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
      }
   };
   public static final int DEFAULT_MAX_LEN = 1048576;
   private static final int LEAD_BYTE = 0;
   private static final int TRAIL_BYTE_1 = 1;
   private static final int TRAIL_BYTE = 2;
   static final int[] bytesFromUTF8 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
   static final int[] offsetsFromUTF8 = new int[]{0, 12416, 925824, 63447168, -100130688, -2113396608};

   public Text() {
   }

   public Text(String string) {
      this.set(string);
   }

   public Text(Text utf8) {
      this.set(utf8);
   }

   public Text(byte[] utf8) {
      this.set(utf8);
   }

   public byte[] copyBytes() {
      byte[] result = new byte[this.length];
      System.arraycopy(this.bytes, 0, result, 0, this.length);
      return result;
   }

   public byte[] getBytes() {
      return this.bytes;
   }

   public int charAt(int position) {
      if (position > this.length) {
         return -1;
      } else if (position < 0) {
         return -1;
      } else {
         ByteBuffer bb = ByteBuffer.wrap(this.bytes).position(position);
         return bytesToCodePoint(bb.slice());
      }
   }

   public int find(String what) {
      return this.find(what, 0);
   }

   public int find(String what, int start) {
      try {
         ByteBuffer src = ByteBuffer.wrap(this.bytes, 0, this.length);
         ByteBuffer tgt = encode(what);
         byte b = tgt.get();
         src.position(start);

         while(src.hasRemaining()) {
            if (b == src.get()) {
               src.mark();
               tgt.mark();
               boolean found = true;
               int pos = src.position() - 1;

               while(tgt.hasRemaining()) {
                  if (!src.hasRemaining()) {
                     tgt.reset();
                     src.reset();
                     found = false;
                     break;
                  }

                  if (tgt.get() != src.get()) {
                     tgt.reset();
                     src.reset();
                     found = false;
                     break;
                  }
               }

               if (found) {
                  return pos;
               }
            }
         }

         return -1;
      } catch (CharacterCodingException e) {
         e.printStackTrace();
         return -1;
      }
   }

   public void set(String string) {
      try {
         ByteBuffer bb = encode(string, true);
         this.bytes = bb.array();
         this.length = bb.limit();
      } catch (CharacterCodingException e) {
         throw new RuntimeException("Should not have happened ", e);
      }
   }

   public void set(byte[] utf8) {
      this.set(utf8, 0, utf8.length);
   }

   public void set(Text other) {
      this.set(other.getBytes(), 0, (int)other.getLength());
   }

   public void set(byte[] utf8, int start, int len) {
      super.set(utf8, (long)start, (long)len);
   }

   public void append(byte[] utf8, int start, int len) {
      this.setCapacity(this.length + len, true);
      System.arraycopy(utf8, start, this.bytes, this.length, len);
      this.length += len;
   }

   public void clear() {
      this.length = 0;
   }

   public String toString() {
      try {
         return decode(this.bytes, 0, this.length);
      } catch (CharacterCodingException e) {
         throw new RuntimeException("Should not have happened ", e);
      }
   }

   public void readWithKnownLength(DataInput in, int len) throws IOException {
      this.setCapacity(len, false);
      in.readFully(this.bytes, 0, len);
      this.length = len;
   }

   public boolean equals(Object o) {
      return !(o instanceof Text) ? false : super.equals(o);
   }

   public static String decode(byte[] utf8) throws CharacterCodingException {
      return decode(ByteBuffer.wrap(utf8), true);
   }

   public static String decode(byte[] utf8, int start, int length) throws CharacterCodingException {
      return decode(ByteBuffer.wrap(utf8, start, length), true);
   }

   public static String decode(byte[] utf8, int start, int length, boolean replace) throws CharacterCodingException {
      return decode(ByteBuffer.wrap(utf8, start, length), replace);
   }

   private static String decode(ByteBuffer utf8, boolean replace) throws CharacterCodingException {
      CharsetDecoder decoder = (CharsetDecoder)DECODER_FACTORY.get();
      if (replace) {
         decoder.onMalformedInput(CodingErrorAction.REPLACE);
         decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
      }

      String str = decoder.decode(utf8).toString();
      if (replace) {
         decoder.onMalformedInput(CodingErrorAction.REPORT);
         decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
      }

      return str;
   }

   public static ByteBuffer encode(String string) throws CharacterCodingException {
      return encode(string, true);
   }

   public static ByteBuffer encode(String string, boolean replace) throws CharacterCodingException {
      CharsetEncoder encoder = (CharsetEncoder)ENCODER_FACTORY.get();
      if (replace) {
         encoder.onMalformedInput(CodingErrorAction.REPLACE);
         encoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
      }

      ByteBuffer bytes = encoder.encode(CharBuffer.wrap(string.toCharArray()));
      if (replace) {
         encoder.onMalformedInput(CodingErrorAction.REPORT);
         encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
      }

      return bytes;
   }

   public static boolean validateUTF8NoThrow(byte[] utf8) {
      return !validateUTF8Internal(utf8, 0, utf8.length).isPresent();
   }

   public static void validateUTF8(byte[] utf8) throws MalformedInputException {
      validateUTF8(utf8, 0, utf8.length);
   }

   public static void validateUTF8(byte[] utf8, int start, int len) throws MalformedInputException {
      Optional<Integer> result = validateUTF8Internal(utf8, start, len);
      if (result.isPresent()) {
         throw new MalformedInputException((Integer)result.get());
      }
   }

   private static Optional validateUTF8Internal(byte[] utf8, int start, int len) {
      int count = start;
      int leadByte = 0;
      int length = 0;

      for(int state = 0; count < start + len; ++count) {
         int aByte = utf8[count] & 255;
         switch (state) {
            case 0:
               leadByte = aByte;
               length = bytesFromUTF8[aByte];
               switch (length) {
                  case 0:
                     if (aByte > 127) {
                        return Optional.of(count);
                     }
                     continue;
                  case 1:
                     if (aByte < 194 || aByte > 223) {
                        return Optional.of(count);
                     }

                     state = 1;
                     continue;
                  case 2:
                     if (aByte < 224 || aByte > 239) {
                        return Optional.of(count);
                     }

                     state = 1;
                     continue;
                  case 3:
                     if (aByte < 240 || aByte > 244) {
                        return Optional.of(count);
                     }

                     state = 1;
                     continue;
                  default:
                     return Optional.of(count);
               }
            case 1:
               if (leadByte == 240 && aByte < 144) {
                  return Optional.of(count);
               }

               if (leadByte == 244 && aByte > 143) {
                  return Optional.of(count);
               }

               if (leadByte == 224 && aByte < 160) {
                  return Optional.of(count);
               }

               if (leadByte == 237 && aByte > 159) {
                  return Optional.of(count);
               }
            case 2:
               if (aByte < 128 || aByte > 191) {
                  return Optional.of(count);
               }

               --length;
               if (length == 0) {
                  state = 0;
               } else {
                  state = 2;
               }
         }
      }

      return Optional.empty();
   }

   public static int bytesToCodePoint(ByteBuffer bytes) {
      bytes.mark();
      byte b = bytes.get();
      bytes.reset();
      int extraBytesToRead = bytesFromUTF8[b & 255];
      if (extraBytesToRead < 0) {
         return -1;
      } else {
         int ch = 0;
         switch (extraBytesToRead) {
            case 5:
               ch += bytes.get() & 255;
               ch <<= 6;
            case 4:
               ch += bytes.get() & 255;
               ch <<= 6;
            case 3:
               ch += bytes.get() & 255;
               ch <<= 6;
            case 2:
               ch += bytes.get() & 255;
               ch <<= 6;
            case 1:
               ch += bytes.get() & 255;
               ch <<= 6;
            case 0:
               ch += bytes.get() & 255;
            default:
               ch -= offsetsFromUTF8[extraBytesToRead];
               return ch;
         }
      }
   }

   public static int utf8Length(String string) {
      CharacterIterator iter = new StringCharacterIterator(string);
      char ch = iter.first();

      int size;
      for(size = 0; ch != '\uffff'; ch = iter.next()) {
         if (ch >= '\ud800' && ch < '\udc00') {
            char trail = iter.next();
            if (trail > '\udbff' && trail < '\ue000') {
               size += 4;
            } else {
               size += 3;
               iter.previous();
            }
         } else if (ch < 128) {
            ++size;
         } else if (ch < 2048) {
            size += 2;
         } else {
            size += 3;
         }
      }

      return size;
   }

   public static class TextSerializer extends StdSerializer {
      public TextSerializer() {
         super(Text.class);
      }

      public void serialize(Text text, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
         jsonGenerator.writeString(text.toString());
      }
   }
}
