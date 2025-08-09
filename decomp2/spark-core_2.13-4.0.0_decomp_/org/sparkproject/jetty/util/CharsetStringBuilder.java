package org.sparkproject.jetty.util;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public interface CharsetStringBuilder {
   void append(byte var1);

   void append(char var1);

   default void append(byte[] bytes) {
      this.append((byte[])bytes, 0, bytes.length);
   }

   default void append(byte[] b, int offset, int length) {
      int end = offset + length;

      for(int i = offset; i < end; ++i) {
         this.append(b[i]);
      }

   }

   default void append(CharSequence chars, int offset, int length) {
      int end = offset + length;

      for(int i = offset; i < end; ++i) {
         this.append(chars.charAt(i));
      }

   }

   default void append(ByteBuffer buf) {
      int end = buf.position() + buf.remaining();

      while(buf.position() < end) {
         this.append(buf.get());
      }

   }

   String build() throws CharacterCodingException;

   void reset();

   static CharsetStringBuilder forCharset(Charset charset) {
      Objects.requireNonNull(charset);
      if (charset == StandardCharsets.ISO_8859_1) {
         return new Iso88591StringBuilder();
      } else {
         return (CharsetStringBuilder)(charset == StandardCharsets.US_ASCII ? new UsAsciiStringBuilder() : new DecoderStringBuilder(charset.newDecoder()));
      }
   }

   public static class Iso88591StringBuilder implements CharsetStringBuilder {
      private final StringBuilder _builder = new StringBuilder();

      public void append(byte b) {
         this._builder.append((char)(255 & b));
      }

      public void append(char c) {
         this._builder.append(c);
      }

      public void append(CharSequence chars, int offset, int length) {
         this._builder.append(chars, offset, offset + length);
      }

      public String build() {
         String s = this._builder.toString();
         this._builder.setLength(0);
         return s;
      }

      public void reset() {
         this._builder.setLength(0);
      }
   }

   public static class UsAsciiStringBuilder implements CharsetStringBuilder {
      private final StringBuilder _builder = new StringBuilder();

      public void append(byte b) {
         if (b < 0) {
            throw new IllegalArgumentException();
         } else {
            this._builder.append((char)b);
         }
      }

      public void append(char c) {
         this._builder.append(c);
      }

      public void append(CharSequence chars, int offset, int length) {
         this._builder.append(chars, offset, offset + length);
      }

      public String build() {
         String s = this._builder.toString();
         this._builder.setLength(0);
         return s;
      }

      public void reset() {
         this._builder.setLength(0);
      }
   }

   public static class DecoderStringBuilder implements CharsetStringBuilder {
      private final CharsetDecoder _decoder;
      private final StringBuilder _stringBuilder = new StringBuilder(32);
      private ByteBuffer _buffer = ByteBuffer.allocate(32);

      public DecoderStringBuilder(CharsetDecoder charsetDecoder) {
         this._decoder = charsetDecoder;
      }

      private void ensureSpace(int needed) {
         int space = this._buffer.remaining();
         if (space < needed) {
            int position = this._buffer.position();
            this._buffer = ByteBuffer.wrap(Arrays.copyOf(this._buffer.array(), this._buffer.capacity() + needed - space + 32)).position(position);
         }

      }

      public void append(byte b) {
         this.ensureSpace(1);
         this._buffer.put(b);
      }

      public void append(char c) {
         if (this._buffer.position() > 0) {
            try {
               this._stringBuilder.append(this._decoder.decode(this._buffer.flip()));
               this._buffer.clear();
            } catch (CharacterCodingException e) {
               throw new RuntimeException(e);
            }
         }

         this._stringBuilder.append(c);
      }

      public void append(CharSequence chars, int offset, int length) {
         if (this._buffer.position() > 0) {
            try {
               this._stringBuilder.append(this._decoder.decode(this._buffer.flip()));
               this._buffer.clear();
            } catch (CharacterCodingException e) {
               throw new RuntimeException(e);
            }
         }

         this._stringBuilder.append(chars, offset, offset + length);
      }

      public void append(byte[] b, int offset, int length) {
         this.ensureSpace(length);
         this._buffer.put(b, offset, length);
      }

      public void append(ByteBuffer buf) {
         this.ensureSpace(buf.remaining());
         this._buffer.put(buf);
      }

      public String build() throws CharacterCodingException {
         try {
            if (this._buffer.position() > 0) {
               CharSequence decoded = this._decoder.decode(this._buffer.flip());
               this._buffer.clear();
               if (this._stringBuilder.length() == 0) {
                  String var2 = decoded.toString();
                  return var2;
               }

               this._stringBuilder.append(decoded);
            }

            String var6 = this._stringBuilder.toString();
            return var6;
         } finally {
            this._stringBuilder.setLength(0);
         }
      }

      public void reset() {
         this._stringBuilder.setLength(0);
      }
   }
}
