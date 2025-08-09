package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import breeze.storage.Zero;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.util.control.ControlThrowable;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mt!\u0002\t\u0012\u0011\u00031b!\u0002\r\u0012\u0011\u0003I\u0002\"\u0002\u0014\u0002\t\u00039s!\u0002\u0015\u0002\u0011\u0013Kc!B\u0016\u0002\u0011\u0013c\u0003\"\u0002\u0014\u0005\t\u0003!\u0005bB#\u0005\u0003\u0003%\tE\u0012\u0005\b\u001f\u0012\t\t\u0011\"\u0001Q\u0011\u001d!F!!A\u0005\u0002UCqa\u0017\u0003\u0002\u0002\u0013\u0005C\fC\u0004d\t\u0005\u0005I\u0011\u00013\t\u000f%$\u0011\u0011!C!U\"91\u000eBA\u0001\n\u0013a\u0007\"\u00029\u0002\t\u0007\t\bbBA\u0014\u0003\u0011\r\u0011\u0011\u0006\u0005\b\u0003\u0013\nA1AA&\u0003\r\tg.\u001f\u0006\u0003%M\ta\u0001\\5oC2<'\"\u0001\u000b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"aF\u0001\u000e\u0003E\u00111!\u00198z'\r\t!\u0004\t\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\r\u001a\u0012aB4f]\u0016\u0014\u0018nY\u0005\u0003K\t\u0012Q!\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\f\u0002\u000b\u0019{WO\u001c3\u0011\u0005)\"Q\"A\u0001\u0003\u000b\u0019{WO\u001c3\u0014\t\u0011iS\u0007\u000f\t\u0003]Mj\u0011a\f\u0006\u0003aE\nqaY8oiJ|GN\u0003\u000239\u0005!Q\u000f^5m\u0013\t!tF\u0001\tD_:$(o\u001c7UQJ|w/\u00192mKB\u00111DN\u0005\u0003oq\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002:\u0003:\u0011!h\u0010\b\u0003wyj\u0011\u0001\u0010\u0006\u0003{U\ta\u0001\u0010:p_Rt\u0014\"A\u000f\n\u0005\u0001c\u0012a\u00029bG.\fw-Z\u0005\u0003\u0005\u000e\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0011\u000f\u0015\u0003%\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A$\u0011\u0005!kU\"A%\u000b\u0005)[\u0015\u0001\u00027b]\u001eT\u0011\u0001T\u0001\u0005U\u00064\u0018-\u0003\u0002O\u0013\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u0015\t\u00037IK!a\u0015\u000f\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005YK\u0006CA\u000eX\u0013\tAFDA\u0002B]fDqA\u0017\u0005\u0002\u0002\u0003\u0007\u0011+A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002;B\u0019a,\u0019,\u000e\u0003}S!\u0001\u0019\u000f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002c?\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t)\u0007\u000e\u0005\u0002\u001cM&\u0011q\r\b\u0002\b\u0005>|G.Z1o\u0011\u001dQ&\"!AA\u0002Y\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002#\u0006aqO]5uKJ+\u0007\u000f\\1dKR\tQ\u000e\u0005\u0002I]&\u0011q.\u0013\u0002\u0007\u001f\nTWm\u0019;\u0002\u0017I,G-^2f+\u001a+hnY\u000b\u0006eb|\u0018\u0011\u0003\u000b\u0006g\u0006\r\u0011Q\u0003\t\u0006UQ4h0Z\u0005\u0003k\u0012\u0012Q!S7qYJ\u0002\"a\u001e=\r\u0001\u0011)\u00110\u0004b\u0001u\n\ta)\u0005\u0002|-B\u00111\u0004`\u0005\u0003{r\u0011qAT8uQ&tw\r\u0005\u0002x\u007f\u00121\u0011\u0011A\u0007C\u0002i\u0014\u0011\u0001\u0016\u0005\b\u0003\u000bi\u00019AA\u0004\u0003\u0015IW\u000e\u001d73!\u0019QC/!\u0003\u007fKB11$a\u0003\u0002\u0010\u0015L1!!\u0004\u001d\u0005%1UO\\2uS>t\u0017\u0007E\u0002x\u0003#!a!a\u0005\u000e\u0005\u0004Q(!A*\t\u000f\u0005]Q\u0002q\u0001\u0002\u001a\u0005!!-Y:f!!\tY\"!\tw\u0003\u001f)gbA\u0011\u0002\u001e%\u0019\u0011q\u0004\u0012\u0002\u000bU3UO\\2\n\t\u0005\r\u0012Q\u0005\u0002\u0006+&k\u0007\u000f\u001c\u0006\u0004\u0003?\u0011\u0013!\u0003:fIV\u001cWMR;o+\u0019\tY#a\u000e\u00024Q!\u0011QFA\u001d!\u001dQC/a\f\u00026\u0015\u0004baGA\u0006\u0003c)\u0007cA<\u00024\u00111\u00111\u0003\bC\u0002i\u00042a^A\u001c\t\u0019\t\tA\u0004b\u0001u\"9\u00111\b\bA\u0004\u0005u\u0012aA2umBA\u0011qHA#\u0003k\t\t$\u0004\u0002\u0002B)\u0019\u00111I\t\u0002\u000fM,\b\u000f]8si&!\u0011qIA!\u0005E\u0019\u0015M\u001c+sCZ,'o]3WC2,Xm]\u0001\u000be\u0016$WoY3[KJ|WCBA'\u0003/\n\t\u0007\u0006\u0004\u0002P\u0005e\u00131\r\t\u0007U\u0005E\u0013QK3\n\u0007\u0005MCE\u0001\u0003J[Bd\u0007cA<\u0002X\u00111\u0011\u0011A\bC\u0002iDq!!\u0002\u0010\u0001\b\tY\u0006E\u0004+i\u0006u\u0013QK3\u0011\rm\tY!a\u0018f!\r9\u0018\u0011\r\u0003\u0007\u0003'y!\u0019\u0001>\t\u000f\u0005\u0015t\u0002q\u0001\u0002h\u0005\t!\u0010\u0005\u0004\u0002j\u0005=\u0014qL\u0007\u0003\u0003WR1!!\u001c\u0014\u0003\u001d\u0019Ho\u001c:bO\u0016LA!!\u001d\u0002l\t!!,\u001a:p\u0001"
)
public final class any {
   public static UFunc.UImpl reduceZero(final UFunc.UImpl2 impl2, final Zero z) {
      return any$.MODULE$.reduceZero(impl2, z);
   }

   public static UFunc.UImpl2 reduceFun(final CanTraverseValues ctv) {
      return any$.MODULE$.reduceFun(ctv);
   }

   public static UFunc.UImpl2 reduceUFunc(final UFunc.UImpl2 impl2, final UFunc.UImpl base) {
      return any$.MODULE$.reduceUFunc(impl2, base);
   }

   public static Object withSink(final Object s) {
      return any$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return any$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return any$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return any$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return any$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return any$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return any$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return any$.MODULE$.apply(v, impl);
   }

   private static class Found$ extends ControlThrowable implements Product {
      public static final Found$ MODULE$ = new Found$();

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
         return "Found";
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
         return x$1 instanceof Found$;
      }

      public int hashCode() {
         return 68069218;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Found$.class);
      }

      public Found$() {
      }
   }
}
