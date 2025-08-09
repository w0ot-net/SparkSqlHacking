package scala.reflect.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.collection.Iterator;
import scala.io.BufferedSource;
import scala.io.Codec;

public final class Streamable$ {
   public static final Streamable$ MODULE$ = new Streamable$();

   public Object closing(final Closeable stream, final Function1 f) {
      Object var10000;
      try {
         var10000 = f.apply(stream);
      } finally {
         stream.close();
      }

      return var10000;
   }

   public byte[] bytes(final Function0 is) {
      return Streamable.Bytes.toByteArray$(new Streamable.Bytes(is) {
         private final Function0 is$1;

         public long length() {
            return Streamable.Bytes.length$(this);
         }

         public BufferedInputStream bufferedInput() {
            return Streamable.Bytes.bufferedInput$(this);
         }

         public Iterator bytes() {
            return Streamable.Bytes.bytes$(this);
         }

         public Iterator bytesAsInts() {
            return Streamable.Bytes.bytesAsInts$(this);
         }

         public byte[] toByteArray() {
            return Streamable.Bytes.toByteArray$(this);
         }

         public InputStream inputStream() {
            return (InputStream)this.is$1.apply();
         }

         public {
            this.is$1 = is$1;
         }
      });
   }

   public String slurp(final Function0 is, final Codec codec) {
      return Streamable.Chars.slurp$(new Streamable.Chars(is) {
         private final Function0 is$2;

         public Codec creationCodec() {
            return Streamable.Chars.creationCodec$(this);
         }

         public BufferedSource chars(final Codec codec) {
            return Streamable.Chars.chars$(this, codec);
         }

         public Iterator lines() {
            return Streamable.Chars.lines$(this);
         }

         public Iterator lines(final Codec codec) {
            return Streamable.Chars.lines$(this, codec);
         }

         public InputStreamReader reader(final Codec codec) {
            return Streamable.Chars.reader$(this, codec);
         }

         public BufferedReader bufferedReader() {
            return Streamable.Chars.bufferedReader$(this);
         }

         public BufferedReader bufferedReader(final Codec codec) {
            return Streamable.Chars.bufferedReader$(this, codec);
         }

         public Object applyReader(final Function1 f) {
            return Streamable.Chars.applyReader$(this, f);
         }

         public String slurp() {
            return Streamable.Chars.slurp$(this);
         }

         public String slurp(final Codec codec) {
            return Streamable.Chars.slurp$(this, codec);
         }

         public long length() {
            return Streamable.Bytes.length$(this);
         }

         public BufferedInputStream bufferedInput() {
            return Streamable.Bytes.bufferedInput$(this);
         }

         public Iterator bytes() {
            return Streamable.Bytes.bytes$(this);
         }

         public Iterator bytesAsInts() {
            return Streamable.Bytes.bytesAsInts$(this);
         }

         public byte[] toByteArray() {
            return Streamable.Bytes.toByteArray$(this);
         }

         public InputStream inputStream() {
            return (InputStream)this.is$2.apply();
         }

         public {
            this.is$2 = is$2;
         }
      }, codec);
   }

   public String slurp(final URL url, final Codec codec) {
      Function0 slurp_is = () -> url.openStream();
      return Streamable.Chars.slurp$(new Streamable.Chars(slurp_is) {
         private final Function0 is$2;

         public Codec creationCodec() {
            return Streamable.Chars.creationCodec$(this);
         }

         public BufferedSource chars(final Codec codec) {
            return Streamable.Chars.chars$(this, codec);
         }

         public Iterator lines() {
            return Streamable.Chars.lines$(this);
         }

         public Iterator lines(final Codec codec) {
            return Streamable.Chars.lines$(this, codec);
         }

         public InputStreamReader reader(final Codec codec) {
            return Streamable.Chars.reader$(this, codec);
         }

         public BufferedReader bufferedReader() {
            return Streamable.Chars.bufferedReader$(this);
         }

         public BufferedReader bufferedReader(final Codec codec) {
            return Streamable.Chars.bufferedReader$(this, codec);
         }

         public Object applyReader(final Function1 f) {
            return Streamable.Chars.applyReader$(this, f);
         }

         public String slurp() {
            return Streamable.Chars.slurp$(this);
         }

         public String slurp(final Codec codec) {
            return Streamable.Chars.slurp$(this, codec);
         }

         public long length() {
            return Streamable.Bytes.length$(this);
         }

         public BufferedInputStream bufferedInput() {
            return Streamable.Bytes.bufferedInput$(this);
         }

         public Iterator bytes() {
            return Streamable.Bytes.bytes$(this);
         }

         public Iterator bytesAsInts() {
            return Streamable.Bytes.bytesAsInts$(this);
         }

         public byte[] toByteArray() {
            return Streamable.Bytes.toByteArray$(this);
         }

         public InputStream inputStream() {
            return (InputStream)this.is$2.apply();
         }

         public {
            this.is$2 = is$2;
         }
      }, codec);
   }

   private Streamable$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
