package scala.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.net.URL;
import scala.Function0;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Predef$;
import scala.Some;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.runtime.java8.JFunction0$mcV$sp;

public final class Source$ {
   public static final Source$ MODULE$ = new Source$();
   private static final int DefaultBufSize = 2048;

   public int DefaultBufSize() {
      return DefaultBufSize;
   }

   public BufferedSource stdin() {
      return this.fromInputStream(System.in, Codec$.MODULE$.fallbackSystemCodec());
   }

   public Source fromIterable(final Iterable iterable) {
      return (new Source(iterable) {
         private final Iterator iter;

         public Iterator iter() {
            return this.iter;
         }

         public {
            this.iter = iterable$1.iterator();
         }
      }).withReset(() -> MODULE$.fromIterable(iterable));
   }

   public Source fromChar(final char c) {
      return this.fromIterable(Predef$.MODULE$.wrapCharArray(new char[]{c}));
   }

   public Source fromChars(final char[] chars) {
      return this.fromIterable(Predef$.MODULE$.wrapCharArray(chars));
   }

   public Source fromString(final String s) {
      return this.fromIterable(Predef$.MODULE$.wrapString(s));
   }

   public BufferedSource fromFile(final String name, final Codec codec) {
      return this.fromFile(new File(name), codec);
   }

   public BufferedSource fromFile(final String name, final String enc) {
      return this.fromFile(name, Codec$.MODULE$.apply(enc));
   }

   public BufferedSource fromFile(final URI uri, final Codec codec) {
      return this.fromFile(new File(uri), codec);
   }

   public BufferedSource fromFile(final URI uri, final String enc) {
      return this.fromFile(uri, Codec$.MODULE$.apply(enc));
   }

   public BufferedSource fromFile(final File file, final Codec codec) {
      return this.fromFile(file, this.DefaultBufSize(), codec);
   }

   public BufferedSource fromFile(final File file, final String enc) {
      return this.fromFile(file, Codec$.MODULE$.apply(enc));
   }

   public BufferedSource fromFile(final File file, final String enc, final int bufferSize) {
      return this.fromFile(file, bufferSize, Codec$.MODULE$.apply(enc));
   }

   public BufferedSource fromFile(final File file, final int bufferSize, final Codec codec) {
      FileInputStream inputStream = new FileInputStream(file);
      Function0 var10000 = () -> MODULE$.fromFile(file, bufferSize, codec);
      JFunction0$mcV$sp createBufferedSource_close = () -> inputStream.close();
      Function0 createBufferedSource_reset = var10000;
      Function0 createBufferedSource_resetFn = createBufferedSource_reset == null ? () -> MODULE$.createBufferedSource(is, x$4, reset$1, x$3, codec) : createBufferedSource_reset;
      BufferedSource var11 = (BufferedSource)(new BufferedSource(inputStream, bufferSize, codec)).withReset(createBufferedSource_resetFn).withClose(createBufferedSource_close);
      createBufferedSource_reset = null;
      createBufferedSource_close = null;
      createBufferedSource_resetFn = null;
      return (BufferedSource)var11.withDescription((new StringBuilder(5)).append("file:").append(file.getAbsolutePath()).toString());
   }

   public Source fromBytes(final byte[] bytes, final Codec codec) {
      return this.fromString(new String(bytes, codec.name()));
   }

   public Source fromBytes(final byte[] bytes, final String enc) {
      return this.fromBytes(bytes, Codec$.MODULE$.apply(enc));
   }

   /** @deprecated */
   public Source fromRawBytes(final byte[] bytes) {
      return this.fromString(new String(bytes, Codec$.MODULE$.ISO8859().charSet()));
   }

   public BufferedSource fromURI(final URI uri, final Codec codec) {
      return this.fromFile(new File(uri), codec);
   }

   public BufferedSource fromURL(final String s, final String enc) {
      return this.fromURL(s, Codec$.MODULE$.apply(enc));
   }

   public BufferedSource fromURL(final String s, final Codec codec) {
      return this.fromURL((new URI(s)).toURL(), codec);
   }

   public BufferedSource fromURL(final URL url, final String enc) {
      return this.fromURL(url, Codec$.MODULE$.apply(enc));
   }

   public BufferedSource fromURL(final URL url, final Codec codec) {
      return this.fromInputStream(url.openStream(), codec);
   }

   public BufferedSource createBufferedSource(final InputStream inputStream, final int bufferSize, final Function0 reset, final Function0 close, final Codec codec) {
      Function0 resetFn = reset == null ? () -> MODULE$.createBufferedSource(is, x$4, reset$1, x$3, codec) : reset;
      return (BufferedSource)(new BufferedSource(inputStream, bufferSize, codec)).withReset(resetFn).withClose(close);
   }

   public int createBufferedSource$default$2() {
      return this.DefaultBufSize();
   }

   public Function0 createBufferedSource$default$3() {
      return null;
   }

   public Function0 createBufferedSource$default$4() {
      return null;
   }

   public BufferedSource fromInputStream(final InputStream is, final String enc) {
      return this.fromInputStream(is, Codec$.MODULE$.apply(enc));
   }

   public BufferedSource fromInputStream(final InputStream is, final Codec codec) {
      Function0 x$2 = () -> MODULE$.fromInputStream(is, codec);
      Function0 x$3 = () -> is.close();
      int x$4 = this.DefaultBufSize();
      Function0 createBufferedSource_resetFn = x$2 == null ? () -> MODULE$.createBufferedSource(is, x$4, reset$1, x$3, codec) : x$2;
      return (BufferedSource)(new BufferedSource(is, x$4, codec)).withReset(createBufferedSource_resetFn).withClose(x$3);
   }

   public BufferedSource fromResource(final String resource, final ClassLoader classLoader, final Codec codec) {
      Option var4 = Option$.MODULE$.apply(classLoader.getResourceAsStream(resource));
      if (var4 instanceof Some) {
         InputStream in = (InputStream)((Some)var4).value();
         return this.fromInputStream(in, codec);
      } else if (None$.MODULE$.equals(var4)) {
         throw new FileNotFoundException((new StringBuilder(70)).append("resource '").append(resource).append("' was not found in the classpath from the given classloader.").toString());
      } else {
         throw new MatchError(var4);
      }
   }

   public ClassLoader fromResource$default$2() {
      return Thread.currentThread().getContextClassLoader();
   }

   private Source$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
