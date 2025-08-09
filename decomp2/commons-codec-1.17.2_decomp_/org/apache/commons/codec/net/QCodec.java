package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;

public class QCodec extends RFC1522Codec implements StringEncoder, StringDecoder {
   private static final BitSet PRINTABLE_CHARS = new BitSet(256);
   private static final byte SPACE = 32;
   private static final byte UNDERSCORE = 95;
   private boolean encodeBlanks;

   public QCodec() {
      this(StandardCharsets.UTF_8);
   }

   public QCodec(Charset charset) {
      super(charset);
   }

   public QCodec(String charsetName) {
      this(Charset.forName(charsetName));
   }

   public Object decode(Object obj) throws DecoderException {
      if (obj == null) {
         return null;
      } else if (obj instanceof String) {
         return this.decode((String)obj);
      } else {
         throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be decoded using Q codec");
      }
   }

   public String decode(String str) throws DecoderException {
      try {
         return this.decodeText(str);
      } catch (UnsupportedEncodingException e) {
         throw new DecoderException(e.getMessage(), e);
      }
   }

   protected byte[] doDecoding(byte[] bytes) throws DecoderException {
      if (bytes == null) {
         return null;
      } else {
         boolean hasUnderscores = false;

         for(byte b : bytes) {
            if (b == 95) {
               hasUnderscores = true;
               break;
            }
         }

         if (hasUnderscores) {
            byte[] tmp = new byte[bytes.length];

            for(int i = 0; i < bytes.length; ++i) {
               byte b = bytes[i];
               if (b != 95) {
                  tmp[i] = b;
               } else {
                  tmp[i] = 32;
               }
            }

            return QuotedPrintableCodec.decodeQuotedPrintable(tmp);
         } else {
            return QuotedPrintableCodec.decodeQuotedPrintable(bytes);
         }
      }
   }

   protected byte[] doEncoding(byte[] bytes) {
      if (bytes == null) {
         return null;
      } else {
         byte[] data = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, bytes);
         if (this.encodeBlanks) {
            for(int i = 0; i < data.length; ++i) {
               if (data[i] == 32) {
                  data[i] = 95;
               }
            }
         }

         return data;
      }
   }

   public Object encode(Object obj) throws EncoderException {
      if (obj == null) {
         return null;
      } else if (obj instanceof String) {
         return this.encode((String)obj);
      } else {
         throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be encoded using Q codec");
      }
   }

   public String encode(String sourceStr) throws EncoderException {
      return this.encode(sourceStr, this.getCharset());
   }

   public String encode(String sourceStr, Charset sourceCharset) throws EncoderException {
      return this.encodeText(sourceStr, sourceCharset);
   }

   public String encode(String sourceStr, String sourceCharset) throws EncoderException {
      try {
         return this.encodeText(sourceStr, sourceCharset);
      } catch (UnsupportedCharsetException e) {
         throw new EncoderException(e.getMessage(), e);
      }
   }

   protected String getEncoding() {
      return "Q";
   }

   public boolean isEncodeBlanks() {
      return this.encodeBlanks;
   }

   public void setEncodeBlanks(boolean b) {
      this.encodeBlanks = b;
   }

   static {
      PRINTABLE_CHARS.set(32);
      PRINTABLE_CHARS.set(33);
      PRINTABLE_CHARS.set(34);
      PRINTABLE_CHARS.set(35);
      PRINTABLE_CHARS.set(36);
      PRINTABLE_CHARS.set(37);
      PRINTABLE_CHARS.set(38);
      PRINTABLE_CHARS.set(39);
      PRINTABLE_CHARS.set(40);
      PRINTABLE_CHARS.set(41);
      PRINTABLE_CHARS.set(42);
      PRINTABLE_CHARS.set(43);
      PRINTABLE_CHARS.set(44);
      PRINTABLE_CHARS.set(45);
      PRINTABLE_CHARS.set(46);
      PRINTABLE_CHARS.set(47);

      for(int i = 48; i <= 57; ++i) {
         PRINTABLE_CHARS.set(i);
      }

      PRINTABLE_CHARS.set(58);
      PRINTABLE_CHARS.set(59);
      PRINTABLE_CHARS.set(60);
      PRINTABLE_CHARS.set(62);
      PRINTABLE_CHARS.set(64);

      for(int i = 65; i <= 90; ++i) {
         PRINTABLE_CHARS.set(i);
      }

      PRINTABLE_CHARS.set(91);
      PRINTABLE_CHARS.set(92);
      PRINTABLE_CHARS.set(93);
      PRINTABLE_CHARS.set(94);
      PRINTABLE_CHARS.set(96);

      for(int i = 97; i <= 122; ++i) {
         PRINTABLE_CHARS.set(i);
      }

      PRINTABLE_CHARS.set(123);
      PRINTABLE_CHARS.set(124);
      PRINTABLE_CHARS.set(125);
      PRINTABLE_CHARS.set(126);
   }
}
