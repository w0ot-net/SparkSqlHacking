package org.apache.commons.codec.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;

public class URLCodec implements BinaryEncoder, BinaryDecoder, StringEncoder, StringDecoder {
   protected static final byte ESCAPE_CHAR = 37;
   /** @deprecated */
   @Deprecated
   protected static final BitSet WWW_FORM_URL;
   private static final BitSet WWW_FORM_URL_SAFE = new BitSet(256);
   /** @deprecated */
   @Deprecated
   protected volatile String charset;

   public static final byte[] decodeUrl(byte[] bytes) throws DecoderException {
      if (bytes == null) {
         return null;
      } else {
         ByteArrayOutputStream buffer = new ByteArrayOutputStream();

         for(int i = 0; i < bytes.length; ++i) {
            int b = bytes[i];
            if (b == 43) {
               buffer.write(32);
            } else if (b == 37) {
               try {
                  ++i;
                  int u = Utils.digit16(bytes[i]);
                  ++i;
                  int l = Utils.digit16(bytes[i]);
                  buffer.write((char)((u << 4) + l));
               } catch (ArrayIndexOutOfBoundsException e) {
                  throw new DecoderException("Invalid URL encoding: ", e);
               }
            } else {
               buffer.write(b);
            }
         }

         return buffer.toByteArray();
      }
   }

   public static final byte[] encodeUrl(BitSet urlsafe, byte[] bytes) {
      if (bytes == null) {
         return null;
      } else {
         if (urlsafe == null) {
            urlsafe = WWW_FORM_URL_SAFE;
         }

         ByteArrayOutputStream buffer = new ByteArrayOutputStream();

         for(byte c : bytes) {
            int b = c;
            if (c < 0) {
               b = 256 + c;
            }

            if (urlsafe.get(b)) {
               if (b == 32) {
                  b = 43;
               }

               buffer.write(b);
            } else {
               buffer.write(37);
               char hex1 = Utils.hexDigit(b >> 4);
               char hex2 = Utils.hexDigit(b);
               buffer.write(hex1);
               buffer.write(hex2);
            }
         }

         return buffer.toByteArray();
      }
   }

   public URLCodec() {
      this(CharEncoding.UTF_8);
   }

   public URLCodec(String charset) {
      this.charset = charset;
   }

   public byte[] decode(byte[] bytes) throws DecoderException {
      return decodeUrl(bytes);
   }

   public Object decode(Object obj) throws DecoderException {
      if (obj == null) {
         return null;
      } else if (obj instanceof byte[]) {
         return this.decode((byte[])obj);
      } else if (obj instanceof String) {
         return this.decode((String)obj);
      } else {
         throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be URL decoded");
      }
   }

   public String decode(String str) throws DecoderException {
      if (str == null) {
         return null;
      } else {
         try {
            return this.decode(str, this.getDefaultCharset());
         } catch (UnsupportedEncodingException e) {
            throw new DecoderException(e.getMessage(), e);
         }
      }
   }

   public String decode(String str, String charsetName) throws DecoderException, UnsupportedEncodingException {
      return str == null ? null : new String(this.decode(StringUtils.getBytesUsAscii(str)), charsetName);
   }

   public byte[] encode(byte[] bytes) {
      return encodeUrl(WWW_FORM_URL_SAFE, bytes);
   }

   public Object encode(Object obj) throws EncoderException {
      if (obj == null) {
         return null;
      } else if (obj instanceof byte[]) {
         return this.encode((byte[])obj);
      } else if (obj instanceof String) {
         return this.encode((String)obj);
      } else {
         throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be URL encoded");
      }
   }

   public String encode(String str) throws EncoderException {
      if (str == null) {
         return null;
      } else {
         try {
            return this.encode(str, this.getDefaultCharset());
         } catch (UnsupportedEncodingException e) {
            throw new EncoderException(e.getMessage(), e);
         }
      }
   }

   public String encode(String str, String charsetName) throws UnsupportedEncodingException {
      return str == null ? null : StringUtils.newStringUsAscii(this.encode(str.getBytes(charsetName)));
   }

   public String getDefaultCharset() {
      return this.charset;
   }

   /** @deprecated */
   @Deprecated
   public String getEncoding() {
      return this.charset;
   }

   static {
      for(int i = 97; i <= 122; ++i) {
         WWW_FORM_URL_SAFE.set(i);
      }

      for(int i = 65; i <= 90; ++i) {
         WWW_FORM_URL_SAFE.set(i);
      }

      for(int i = 48; i <= 57; ++i) {
         WWW_FORM_URL_SAFE.set(i);
      }

      WWW_FORM_URL_SAFE.set(45);
      WWW_FORM_URL_SAFE.set(95);
      WWW_FORM_URL_SAFE.set(46);
      WWW_FORM_URL_SAFE.set(42);
      WWW_FORM_URL_SAFE.set(32);
      WWW_FORM_URL = (BitSet)WWW_FORM_URL_SAFE.clone();
   }
}
