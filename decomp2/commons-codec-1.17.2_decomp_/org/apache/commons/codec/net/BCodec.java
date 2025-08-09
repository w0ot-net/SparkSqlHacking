package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BaseNCodec;

public class BCodec extends RFC1522Codec implements StringEncoder, StringDecoder {
   private static final CodecPolicy DECODING_POLICY_DEFAULT;
   private final CodecPolicy decodingPolicy;

   public BCodec() {
      this(StandardCharsets.UTF_8);
   }

   public BCodec(Charset charset) {
      this(charset, DECODING_POLICY_DEFAULT);
   }

   public BCodec(Charset charset, CodecPolicy decodingPolicy) {
      super(charset);
      this.decodingPolicy = decodingPolicy;
   }

   public BCodec(String charsetName) {
      this(Charset.forName(charsetName));
   }

   public Object decode(Object value) throws DecoderException {
      if (value == null) {
         return null;
      } else if (value instanceof String) {
         return this.decode((String)value);
      } else {
         throw new DecoderException("Objects of type " + value.getClass().getName() + " cannot be decoded using BCodec");
      }
   }

   public String decode(String value) throws DecoderException {
      try {
         return this.decodeText(value);
      } catch (IllegalArgumentException | UnsupportedEncodingException e) {
         throw new DecoderException(((Exception)e).getMessage(), e);
      }
   }

   protected byte[] doDecoding(byte[] bytes) {
      return bytes == null ? null : (new Base64(0, BaseNCodec.getChunkSeparator(), false, this.decodingPolicy)).decode(bytes);
   }

   protected byte[] doEncoding(byte[] bytes) {
      return bytes == null ? null : Base64.encodeBase64(bytes);
   }

   public Object encode(Object value) throws EncoderException {
      if (value == null) {
         return null;
      } else if (value instanceof String) {
         return this.encode((String)value);
      } else {
         throw new EncoderException("Objects of type " + value.getClass().getName() + " cannot be encoded using BCodec");
      }
   }

   public String encode(String strSource) throws EncoderException {
      return this.encode(strSource, this.getCharset());
   }

   public String encode(String strSource, Charset sourceCharset) throws EncoderException {
      return this.encodeText(strSource, sourceCharset);
   }

   public String encode(String strSource, String sourceCharset) throws EncoderException {
      try {
         return this.encodeText(strSource, sourceCharset);
      } catch (UnsupportedCharsetException e) {
         throw new EncoderException(e.getMessage(), e);
      }
   }

   protected String getEncoding() {
      return "B";
   }

   public boolean isStrictDecoding() {
      return this.decodingPolicy == CodecPolicy.STRICT;
   }

   static {
      DECODING_POLICY_DEFAULT = CodecPolicy.LENIENT;
   }
}
