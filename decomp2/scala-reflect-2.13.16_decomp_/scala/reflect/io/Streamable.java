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
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LazyRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uu!B\u000e\u001d\u0011\u0003\u0019c!B\u0013\u001d\u0011\u00031\u0003\"B\u0016\u0002\t\u0003acaB\u0017\u0002!\u0003\r\tA\f\u0005\u0006_\r!\t\u0001\r\u0005\u0006i\r1\t!\u000e\u0005\u0006{\r!\tA\u0010\u0005\u0006\u0005\u000e!\ta\u0011\u0005\u0006\u000f\u000e!\t\u0001\u0013\u0005\u0006'\u000e!\t\u0001\u0016\u0005\u00063\u000e!\tA\u0017\u0004\b=\u0006\u0001\n1!\u0001`\u0011\u0015y3\u0002\"\u00011\u0011\u0015\u00117\u0002\"\u0001d\u0011\u0015I7\u0002\"\u0001k\u0011\u0015\u00018\u0002\"\u0001r\u0011\u0015\u00018\u0002\"\u0001\u007f\u0011\u001d\t\ta\u0003C\u0001\u0003\u0007Aq!!\u0004\f\t\u0003\ty\u0001C\u0004\u0002\u000e-!\t!a\u0006\t\u000f\u0005m1\u0002\"\u0001\u0002\u001e!9\u0011\u0011I\u0006\u0005\u0002\u0005\r\u0003bBA!\u0017\u0011\u0005\u0011Q\t\u0005\b\u0003\u0013\nA\u0011AA&\u0011\u00199\u0015\u0001\"\u0001\u0002l!9\u0011\u0011I\u0001\u0005\u0002\u0005]\u0004bBA!\u0003\u0011\u0005\u0011qP\u0001\u000b'R\u0014X-Y7bE2,'BA\u000f\u001f\u0003\tIwN\u0003\u0002 A\u00059!/\u001a4mK\u000e$(\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011A%A\u0007\u00029\tQ1\u000b\u001e:fC6\f'\r\\3\u0014\u0005\u00059\u0003C\u0001\u0015*\u001b\u0005\u0001\u0013B\u0001\u0016!\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012a\t\u0002\u0006\u0005f$Xm]\n\u0003\u0007\u001d\na\u0001J5oSR$C#A\u0019\u0011\u0005!\u0012\u0014BA\u001a!\u0005\u0011)f.\u001b;\u0002\u0017%t\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u0002mA\u0011qgO\u0007\u0002q)\u0011Q$\u000f\u0006\u0002u\u0005!!.\u0019<b\u0013\ta\u0004HA\u0006J]B,Ho\u0015;sK\u0006l\u0017A\u00027f]\u001e$\b.F\u0001@!\tA\u0003)\u0003\u0002BA\t!Aj\u001c8h\u00035\u0011WO\u001a4fe\u0016$\u0017J\u001c9viR\tA\t\u0005\u00028\u000b&\u0011a\t\u000f\u0002\u0014\u0005V4g-\u001a:fI&s\u0007/\u001e;TiJ,\u0017-\\\u0001\u0006Ef$Xm\u001d\u000b\u0002\u0013B\u0019!*\u0014)\u000f\u0005!Z\u0015B\u0001'!\u0003\u001d\u0001\u0018mY6bO\u0016L!AT(\u0003\u0011%#XM]1u_JT!\u0001\u0014\u0011\u0011\u0005!\n\u0016B\u0001*!\u0005\u0011\u0011\u0015\u0010^3\u0002\u0017\tLH/Z:Bg&sGo\u001d\u000b\u0002+B\u0019!*\u0014,\u0011\u0005!:\u0016B\u0001-!\u0005\rIe\u000e^\u0001\fi>\u0014\u0015\u0010^3BeJ\f\u0017\u0010F\u0001\\!\rAC\fU\u0005\u0003;\u0002\u0012Q!\u0011:sCf\u0014Qa\u00115beN\u001c2aC\u0014a!\t\t7!D\u0001\u0002\u00035\u0019'/Z1uS>t7i\u001c3fGV\tA\r\u0005\u0002fO6\taM\u0003\u0002\u001eA%\u0011\u0001N\u001a\u0002\u0006\u0007>$WmY\u0001\u0006G\"\f'o\u001d\u000b\u0003W:\u0004\"!\u001a7\n\u000554'A\u0004\"vM\u001a,'/\u001a3T_V\u00148-\u001a\u0005\u0006_:\u0001\r\u0001Z\u0001\u0006G>$WmY\u0001\u0006Y&tWm\u001d\u000b\u0002eB\u0019!*T:\u0011\u0005Q\\hBA;z!\t1\b%D\u0001x\u0015\tA(%\u0001\u0004=e>|GOP\u0005\u0003u\u0002\na\u0001\u0015:fI\u00164\u0017B\u0001?~\u0005\u0019\u0019FO]5oO*\u0011!\u0010\t\u000b\u0003e~DQa\u001c\tA\u0002\u0011\faA]3bI\u0016\u0014H\u0003BA\u0003\u0003\u0017\u00012aNA\u0004\u0013\r\tI\u0001\u000f\u0002\u0012\u0013:\u0004X\u000f^*ue\u0016\fWNU3bI\u0016\u0014\b\"B8\u0012\u0001\u0004!\u0017A\u00042vM\u001a,'/\u001a3SK\u0006$WM\u001d\u000b\u0003\u0003#\u00012aNA\n\u0013\r\t)\u0002\u000f\u0002\u000f\u0005V4g-\u001a:fIJ+\u0017\rZ3s)\u0011\t\t\"!\u0007\t\u000b=\u001c\u0002\u0019\u00013\u0002\u0017\u0005\u0004\b\u000f\\=SK\u0006$WM]\u000b\u0005\u0003?\t)\u0003\u0006\u0003\u0002\"\u0005]\u0002\u0003BA\u0012\u0003Ka\u0001\u0001B\u0004\u0002(Q\u0011\r!!\u000b\u0003\u0003Q\u000bB!a\u000b\u00022A\u0019\u0001&!\f\n\u0007\u0005=\u0002EA\u0004O_RD\u0017N\\4\u0011\u0007!\n\u0019$C\u0002\u00026\u0001\u00121!\u00118z\u0011\u001d\tI\u0004\u0006a\u0001\u0003w\t\u0011A\u001a\t\bQ\u0005u\u0012\u0011CA\u0011\u0013\r\ty\u0004\t\u0002\n\rVt7\r^5p]F\nQa\u001d7veB$\u0012a\u001d\u000b\u0004g\u0006\u001d\u0003\"B8\u0017\u0001\u0004!\u0017aB2m_NLgnZ\u000b\u0007\u0003\u001b\ni&a\u0015\u0015\t\u0005=\u0013q\r\u000b\u0005\u0003#\n9\u0006\u0005\u0003\u0002$\u0005MCaBA+/\t\u0007\u0011\u0011\u0006\u0002\u0002+\"9\u0011\u0011H\fA\u0002\u0005e\u0003c\u0002\u0015\u0002>\u0005m\u0013\u0011\u000b\t\u0005\u0003G\ti\u0006B\u0004\u0002(]\u0011\r!a\u0018\u0012\t\u0005-\u0012\u0011\r\t\u0004o\u0005\r\u0014bAA3q\tI1\t\\8tK\u0006\u0014G.\u001a\u0005\b\u0003S:\u0002\u0019AA.\u0003\u0019\u0019HO]3b[R\u00191,!\u001c\t\u0011\u0005=\u0004\u0004\"a\u0001\u0003c\n!![:\u0011\t!\n\u0019HN\u0005\u0004\u0003k\u0002#\u0001\u0003\u001fcs:\fW.\u001a \u0015\t\u0005e\u0014Q\u0010\u000b\u0004g\u0006m\u0004\"B8\u001a\u0001\b!\u0007\u0002CA83\u0011\u0005\r!!\u001d\u0015\t\u0005\u0005\u0015Q\u0011\u000b\u0004g\u0006\r\u0005\"B8\u001b\u0001\b!\u0007bBAD5\u0001\u0007\u0011\u0011R\u0001\u0004kJd\u0007\u0003BAF\u0003#k!!!$\u000b\u0007\u0005=\u0015(A\u0002oKRLA!a%\u0002\u000e\n\u0019QK\u0015'"
)
public final class Streamable {
   public static String slurp(final URL url, final Codec codec) {
      return Streamable$.MODULE$.slurp(url, codec);
   }

   public static String slurp(final Function0 is, final Codec codec) {
      return Streamable$.MODULE$.slurp(is, codec);
   }

   public static byte[] bytes(final Function0 is) {
      return Streamable$.MODULE$.bytes(is);
   }

   public static Object closing(final Closeable stream, final Function1 f) {
      return Streamable$.MODULE$.closing(stream, f);
   }

   public interface Bytes {
      InputStream inputStream();

      // $FF: synthetic method
      static long length$(final Bytes $this) {
         return $this.length();
      }

      default long length() {
         return -1L;
      }

      // $FF: synthetic method
      static BufferedInputStream bufferedInput$(final Bytes $this) {
         return $this.bufferedInput();
      }

      default BufferedInputStream bufferedInput() {
         return new BufferedInputStream(this.inputStream());
      }

      // $FF: synthetic method
      static Iterator bytes$(final Bytes $this) {
         return $this.bytes();
      }

      default Iterator bytes() {
         return this.bytesAsInts().map((x$1) -> BoxesRunTime.boxToByte($anonfun$bytes$1(BoxesRunTime.unboxToInt(x$1))));
      }

      // $FF: synthetic method
      static Iterator bytesAsInts$(final Bytes $this) {
         return $this.bytesAsInts();
      }

      default Iterator bytesAsInts() {
         BufferedInputStream in = this.bufferedInput();
         Iterator var10000 = .MODULE$.Iterator();
         JFunction0.mcI.sp continually_elem = () -> in.read();
         if (var10000 == null) {
            throw null;
         } else {
            Iterator..anon.27 var4 = new Iterator..anon.27(continually_elem);
            Object var3 = null;
            return var4.takeWhile((JFunction1.mcZI.sp)(x$2) -> x$2 != -1);
         }
      }

      // $FF: synthetic method
      static byte[] toByteArray$(final Bytes $this) {
         return $this.toByteArray();
      }

      default byte[] toByteArray() {
         LazyRef in$lzy = new LazyRef();
         if (this.length() == -1L) {
            return (byte[])((IterableOnceOps)(new ArrayBuffer()).addAll(this.bytes())).toArray(scala.reflect.ClassTag..MODULE$.Byte());
         } else {
            byte[] arr = new byte[(int)this.length()];
            int len = arr.length;
            int var7 = 0;

            try {
               while(var7 < len) {
                  int loop$1_read = this.in$2(in$lzy).read(arr, var7, len - var7);
                  if (loop$1_read < 0) {
                     break;
                  }

                  var7 += loop$1_read;
               }
            } finally {
               this.in$2(in$lzy).close();
            }

            if (var7 == arr.length) {
               return arr;
            } else {
               Path$ var10000 = Path$.MODULE$;
               String fail_msg = scala.collection.StringOps..MODULE$.format$extension("Could not read entire source (%d of %d bytes)", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var7, len}));
               throw new FileOperationException(fail_msg);
            }
         }
      }

      // $FF: synthetic method
      static byte $anonfun$bytes$1(final int x$1) {
         return (byte)x$1;
      }

      // $FF: synthetic method
      private BufferedInputStream in$lzycompute$1(final LazyRef in$lzy$1) {
         synchronized(in$lzy$1){}

         BufferedInputStream var2;
         try {
            var2 = in$lzy$1.initialized() ? (BufferedInputStream)in$lzy$1.value() : (BufferedInputStream)in$lzy$1.initialize(this.bufferedInput());
         } catch (Throwable var4) {
            throw var4;
         }

         return var2;
      }

      private BufferedInputStream in$2(final LazyRef in$lzy$1) {
         return in$lzy$1.initialized() ? (BufferedInputStream)in$lzy$1.value() : this.in$lzycompute$1(in$lzy$1);
      }

      private void loop$1(final IntRef offset$1, final int len$1, final byte[] arr$1, final LazyRef in$lzy$1) {
         while(true) {
            if (offset$1.elem < len$1) {
               int read = this.in$2(in$lzy$1).read(arr$1, offset$1.elem, len$1 - offset$1.elem);
               if (read >= 0) {
                  offset$1.elem += read;
                  continue;
               }
            }

            return;
         }
      }

      static void $init$(final Bytes $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface Chars extends Bytes {
      // $FF: synthetic method
      static Codec creationCodec$(final Chars $this) {
         return $this.creationCodec();
      }

      default Codec creationCodec() {
         return scala.io.Codec..MODULE$.fallbackSystemCodec();
      }

      // $FF: synthetic method
      static BufferedSource chars$(final Chars $this, final Codec codec) {
         return $this.chars(codec);
      }

      default BufferedSource chars(final Codec codec) {
         return scala.io.Source..MODULE$.fromInputStream(this.inputStream(), codec);
      }

      // $FF: synthetic method
      static Iterator lines$(final Chars $this) {
         return $this.lines();
      }

      default Iterator lines() {
         return this.lines(this.creationCodec());
      }

      // $FF: synthetic method
      static Iterator lines$(final Chars $this, final Codec codec) {
         return $this.lines(codec);
      }

      default Iterator lines(final Codec codec) {
         return this.chars(codec).getLines();
      }

      // $FF: synthetic method
      static InputStreamReader reader$(final Chars $this, final Codec codec) {
         return $this.reader(codec);
      }

      default InputStreamReader reader(final Codec codec) {
         return new InputStreamReader(this.inputStream(), codec.charSet());
      }

      // $FF: synthetic method
      static BufferedReader bufferedReader$(final Chars $this) {
         return $this.bufferedReader();
      }

      default BufferedReader bufferedReader() {
         return this.bufferedReader(this.creationCodec());
      }

      // $FF: synthetic method
      static BufferedReader bufferedReader$(final Chars $this, final Codec codec) {
         return $this.bufferedReader(codec);
      }

      default BufferedReader bufferedReader(final Codec codec) {
         return new BufferedReader(this.reader(codec));
      }

      // $FF: synthetic method
      static Object applyReader$(final Chars $this, final Function1 f) {
         return $this.applyReader(f);
      }

      default Object applyReader(final Function1 f) {
         BufferedReader in = this.bufferedReader();

         Object var10000;
         try {
            var10000 = f.apply(in);
         } finally {
            in.close();
         }

         return var10000;
      }

      // $FF: synthetic method
      static String slurp$(final Chars $this) {
         return $this.slurp();
      }

      default String slurp() {
         return this.slurp(this.creationCodec());
      }

      // $FF: synthetic method
      static String slurp$(final Chars $this, final Codec codec) {
         return $this.slurp(codec);
      }

      default String slurp(final Codec codec) {
         BufferedSource src = this.chars(codec);

         try {
            if (src == null) {
               throw null;
            }

            String mkString_mkString_sep = "";
            String mkString_end = "";
            String mkString_start = "";
            IterableOnceOps.mkString$(src, mkString_start, mkString_mkString_sep, mkString_end);
            Object var10 = null;
            Object var11 = null;
            Object var9 = null;
         } finally {
            src.close();
         }

         return src;
      }

      static void $init$(final Chars $this) {
      }
   }
}
