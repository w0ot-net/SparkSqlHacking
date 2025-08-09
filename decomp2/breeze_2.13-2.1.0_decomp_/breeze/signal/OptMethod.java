package breeze.signal;

import breeze.util.Opt;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra!B\u000e\u001d\u0003\u0003\t\u0003\"\u0002\u0015\u0001\t\u0003Is!\u0002\u0017\u001d\u0011\u0003ic!B\u000e\u001d\u0011\u0003q\u0003\"\u0002\u0015\u0004\t\u0003)t!\u0002\u001c\u0004\u0011\u0003;d!B\u001d\u0004\u0011\u0003S\u0004\"\u0002\u0015\u0007\t\u0003Q\u0005bB&\u0007\u0003\u0003%\t\u0005\u0014\u0005\b+\u001a\t\t\u0011\"\u0001W\u0011\u001dQf!!A\u0005\u0002mCq!\u0019\u0004\u0002\u0002\u0013\u0005#\rC\u0004j\r\u0005\u0005I\u0011\u00016\t\u000f=4\u0011\u0011!C!a\"9\u0011OBA\u0001\n\u0003\u0012\bbB:\u0007\u0003\u0003%I\u0001^\u0004\u0006q\u000eA\t)\u001f\u0004\u0006u\u000eA\ti\u001f\u0005\u0006QE!\t\u0001 \u0005\b\u0017F\t\t\u0011\"\u0011M\u0011\u001d)\u0016#!A\u0005\u0002YCqAW\t\u0002\u0002\u0013\u0005Q\u0010C\u0004b#\u0005\u0005I\u0011\t2\t\u000f%\f\u0012\u0011!C\u0001\u007f\"9q.EA\u0001\n\u0003\u0002\bbB9\u0012\u0003\u0003%\tE\u001d\u0005\bgF\t\t\u0011\"\u0003u\u0005%y\u0005\u000f^'fi\"|GM\u0003\u0002\u001e=\u000511/[4oC2T\u0011aH\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001A\t\t\u0003G\u0019j\u0011\u0001\n\u0006\u0003Ky\tA!\u001e;jY&\u0011q\u0005\n\u0002\u0004\u001fB$\u0018A\u0002\u001fj]&$h\bF\u0001+!\tY\u0003!D\u0001\u001d\u0003%y\u0005\u000f^'fi\"|G\r\u0005\u0002,\u0007M\u00111a\f\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\u0007\u0003:L(+\u001a4\u0015\u00035\n\u0011\"Q;u_6\fG/[2\u0011\u0005a2Q\"A\u0002\u0003\u0013\u0005+Ho\\7bi&\u001c7\u0003\u0002\u0004+wy\u0002\"\u0001\r\u001f\n\u0005u\n$a\u0002)s_\u0012,8\r\u001e\t\u0003\u007f\u001ds!\u0001Q#\u000f\u0005\u0005#U\"\u0001\"\u000b\u0005\r\u0003\u0013A\u0002\u001fs_>$h(C\u00013\u0013\t1\u0015'A\u0004qC\u000e\\\u0017mZ3\n\u0005!K%\u0001D*fe&\fG.\u001b>bE2,'B\u0001$2)\u00059\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001N!\tq5+D\u0001P\u0015\t\u0001\u0016+\u0001\u0003mC:<'\"\u0001*\u0002\t)\fg/Y\u0005\u0003)>\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A,\u0011\u0005AB\u0016BA-2\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tav\f\u0005\u00021;&\u0011a,\r\u0002\u0004\u0003:L\bb\u00021\u000b\u0003\u0003\u0005\raV\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003\r\u00042\u0001Z4]\u001b\u0005)'B\u000142\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Q\u0016\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u00111N\u001c\t\u0003a1L!!\\\u0019\u0003\u000f\t{w\u000e\\3b]\"9\u0001\rDA\u0001\u0002\u0004a\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003]\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u001b\u0006aqO]5uKJ+\u0007\u000f\\1dKR\tQ\u000f\u0005\u0002Om&\u0011qo\u0014\u0002\u0007\u001f\nTWm\u0019;\u0002\u0007\u00193E\u000b\u0005\u00029#\t\u0019aI\u0012+\u0014\tEQ3H\u0010\u000b\u0002sR\u0011AL \u0005\bAV\t\t\u00111\u0001X)\rY\u0017\u0011\u0001\u0005\bA^\t\t\u00111\u0001]\u0001"
)
public abstract class OptMethod extends Opt {
   public static class Automatic$ extends OptMethod implements Product, Serializable {
      public static final Automatic$ MODULE$ = new Automatic$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Automatic";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Automatic$;
      }

      public int hashCode() {
         return -617328117;
      }

      public String toString() {
         return "Automatic";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Automatic$.class);
      }
   }

   public static class FFT$ extends OptMethod implements Product, Serializable {
      public static final FFT$ MODULE$ = new FFT$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "FFT";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof FFT$;
      }

      public int hashCode() {
         return 69524;
      }

      public String toString() {
         return "FFT";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(FFT$.class);
      }
   }
}
