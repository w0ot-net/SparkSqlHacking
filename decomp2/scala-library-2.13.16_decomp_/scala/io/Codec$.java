package scala.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import scala.util.Properties$;
import scala.util.PropertiesTrait;

public final class Codec$ implements LowPriorityCodecImplicits {
   public static final Codec$ MODULE$ = new Codec$();
   private static final Codec ISO8859;
   private static final Codec UTF8;
   private static Codec fallbackSystemCodec;
   private static volatile boolean bitmap$0;

   static {
      Codec$ var10000 = MODULE$;
      var10000 = MODULE$;
      Charset apply_charSet = StandardCharsets.ISO_8859_1;
      Codec var4 = new Codec(apply_charSet);
      Object var2 = null;
      ISO8859 = var4;
      Codec$ var5 = MODULE$;
      Charset apply_charSet = StandardCharsets.UTF_8;
      UTF8 = new Codec(apply_charSet);
   }

   private Codec fallbackSystemCodec$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            fallbackSystemCodec = this.defaultCharsetCodec();
            bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return fallbackSystemCodec;
   }

   public Codec fallbackSystemCodec() {
      return !bitmap$0 ? this.fallbackSystemCodec$lzycompute() : fallbackSystemCodec;
   }

   public final Codec ISO8859() {
      return ISO8859;
   }

   public final Codec UTF8() {
      return UTF8;
   }

   public Codec defaultCharsetCodec() {
      Charset apply_charSet = Charset.defaultCharset();
      return new Codec(apply_charSet);
   }

   public Codec fileEncodingCodec() {
      return this.apply(PropertiesTrait.encodingString$(Properties$.MODULE$));
   }

   public Codec default() {
      return this.defaultCharsetCodec();
   }

   public Codec apply(final String encoding) {
      return new Codec(Charset.forName(encoding));
   }

   public Codec apply(final Charset charSet) {
      return new Codec(charSet);
   }

   public Codec apply(final CharsetDecoder decoder) {
      return new Codec(decoder, decoder) {
         private final CharsetDecoder _decoder$1;

         public CharsetDecoder decoder() {
            return this._decoder$1;
         }

         public {
            this._decoder$1 = _decoder$1;
         }
      };
   }

   public char[] fromUTF8(final byte[] bytes) {
      return this.fromUTF8(bytes, 0, bytes.length);
   }

   public char[] fromUTF8(final byte[] bytes, final int offset, final int len) {
      ByteBuffer bbuffer = ByteBuffer.wrap(bytes, offset, len);
      CharBuffer cbuffer = this.UTF8().charSet().decode(bbuffer);
      char[] chars = new char[cbuffer.remaining()];
      cbuffer.get(chars);
      return chars;
   }

   public byte[] toUTF8(final CharSequence cs) {
      CharBuffer cbuffer = CharBuffer.wrap(cs, 0, cs.length());
      ByteBuffer bbuffer = this.UTF8().charSet().encode(cbuffer);
      byte[] bytes = new byte[bbuffer.remaining()];
      bbuffer.get(bytes);
      return bytes;
   }

   public byte[] toUTF8(final char[] chars, final int offset, final int len) {
      CharBuffer cbuffer = CharBuffer.wrap(chars, offset, len);
      ByteBuffer bbuffer = this.UTF8().charSet().encode(cbuffer);
      byte[] bytes = new byte[bbuffer.remaining()];
      bbuffer.get(bytes);
      return bytes;
   }

   public Codec string2codec(final String s) {
      return this.apply(s);
   }

   public Codec charset2codec(final Charset c) {
      return new Codec(c);
   }

   public Codec decoder2codec(final CharsetDecoder cd) {
      return this.apply(cd);
   }

   private Codec$() {
   }
}
