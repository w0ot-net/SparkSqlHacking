package algebra.ring;

import cats.kernel.Semigroup;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015caB\u0007\u000f!\u0003\r\ta\u0005\u0005\u0006O\u0001!\t\u0001\u000b\u0005\u0006Y\u0001!\t!\f\u0005\u0006+\u00021\tA\u0016\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0007I\u0002\u0001K\u0011C3\t\u000b!\u0004A\u0011A5\b\u000bIt\u0001\u0012A:\u0007\u000b5q\u0001\u0012A;\t\u000f\u0005%\u0001\u0002\"\u0001\u0002\f!9\u0011Q\u0002\u0005\u0005\u0006\u0005=\u0001B\u0002\u0017\t\t\u000b\t)\u0003C\u0005\u00026!\t\t\u0011\"\u0003\u00028\t\t\u0012\t\u001a3ji&4XmU3nS\u001e\u0014x.\u001e9\u000b\u0005=\u0001\u0012\u0001\u0002:j]\u001eT\u0011!E\u0001\bC2<WM\u0019:b\u0007\u0001)\"\u0001F\u001c\u0014\u0007\u0001)2\u0004\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrCA\u0002B]f\u0004\"\u0001\b\u0013\u000f\u0005u\u0011cB\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u0013\u0003\u0019a$o\\8u}%\t\u0001$\u0003\u0002$/\u00059\u0001/Y2lC\u001e,\u0017BA\u0013'\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019s#\u0001\u0004%S:LG\u000f\n\u000b\u0002SA\u0011aCK\u0005\u0003W]\u0011A!\u00168ji\u0006A\u0011\r\u001a3ji&4X-F\u0001/!\ry#'\u000e\b\u0003aEj\u0011\u0001E\u0005\u0003GAI!a\r\u001b\u0003\u0013M+W.[4s_V\u0004(BA\u0012\u0011!\t1t\u0007\u0004\u0001\u0005\u0013a\u0002\u0001\u0015!A\u0001\u0006\u0004I$!A!\u0012\u0005i*\u0002C\u0001\f<\u0013\tatCA\u0004O_RD\u0017N\\4)\r]r\u0014IR&Q!\t1r(\u0003\u0002A/\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019#iQ#E\u001d\t12)\u0003\u0002E/\u0005\u0019\u0011J\u001c;2\t\u0011j\u0012\u0005G\u0019\u0006G\u001dC%*\u0013\b\u0003-!K!!S\f\u0002\t1{gnZ\u0019\u0005Iu\t\u0003$M\u0003$\u00196{eJ\u0004\u0002\u0017\u001b&\u0011ajF\u0001\u0006\r2|\u0017\r^\u0019\u0005Iu\t\u0003$M\u0003$#J#6K\u0004\u0002\u0017%&\u00111kF\u0001\u0007\t>,(\r\\32\t\u0011j\u0012\u0005G\u0001\u0005a2,8\u000fF\u00026/fCQ\u0001W\u0002A\u0002U\n\u0011\u0001\u001f\u0005\u00065\u000e\u0001\r!N\u0001\u0002s\u0006!1/^7O)\r)Tl\u0018\u0005\u0006=\u0012\u0001\r!N\u0001\u0002C\")\u0001\r\u0002a\u0001C\u0006\ta\u000e\u0005\u0002\u0017E&\u00111m\u0006\u0002\u0004\u0013:$\u0018\u0001\u00049pg&$\u0018N^3Tk6tEcA\u001bgO\")a,\u0002a\u0001k!)\u0001-\u0002a\u0001C\u00061AO]=Tk6$\"A[7\u0011\u0007YYW'\u0003\u0002m/\t1q\n\u001d;j_:DQA\u001c\u0004A\u0002=\f!!Y:\u0011\u0007q\u0001X'\u0003\u0002rM\tyAK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW-A\tBI\u0012LG/\u001b<f'\u0016l\u0017n\u001a:pkB\u0004\"\u0001\u001e\u0005\u000e\u00039\u0019B\u0001\u0003<z{B\u0011ac^\u0005\u0003q^\u0011a!\u00118z%\u00164\u0007c\u0001;{y&\u00111P\u0004\u0002\u001b\u0003\u0012$\u0017\u000e^5wKN+W.[4s_V\u0004h)\u001e8di&|gn\u001d\t\u0003i\u0002\u00012A`A\u0004\u001b\u0005y(\u0002BA\u0001\u0003\u0007\t!![8\u000b\u0005\u0005\u0015\u0011\u0001\u00026bm\u0006L!!J@\u0002\rqJg.\u001b;?)\u0005\u0019\u0018!B1qa2LX\u0003BA\t\u0003/!B!a\u0005\u0002\u001aA!A\u000fAA\u000b!\r1\u0014q\u0003\u0003\u0006q)\u0011\r!\u000f\u0005\b\u00037Q\u00019AA\n\u0003\t)g\u000fK\u0002\u000b\u0003?\u00012AFA\u0011\u0013\r\t\u0019c\u0006\u0002\u0007S:d\u0017N\\3\u0016\t\u0005\u001d\u0012Q\u0006\u000b\u0005\u0003S\ty\u0003\u0005\u00030e\u0005-\u0002c\u0001\u001c\u0002.\u0011)\u0001h\u0003b\u0001s!9\u00111D\u0006A\u0004\u0005E\u0002\u0003\u0002;\u0001\u0003WA3aCA\u0010\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0004\u0005\u0003\u0002<\u0005\u0005SBAA\u001f\u0015\u0011\ty$a\u0001\u0002\t1\fgnZ\u0005\u0005\u0003\u0007\niD\u0001\u0004PE*,7\r\u001e"
)
public interface AdditiveSemigroup extends Serializable {
   static AdditiveSemigroup apply(final AdditiveSemigroup ev) {
      return AdditiveSemigroup$.MODULE$.apply(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveSemigroup$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static Semigroup additive$(final AdditiveSemigroup $this) {
      return $this.additive();
   }

   default Semigroup additive() {
      return new Semigroup() {
         // $FF: synthetic field
         private final AdditiveSemigroup $outer;

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup reverse() {
            return Semigroup.reverse$(this);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.plus(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public {
            if (AdditiveSemigroup.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveSemigroup.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   Object plus(final Object x, final Object y);

   // $FF: synthetic method
   static Object sumN$(final AdditiveSemigroup $this, final Object a, final int n) {
      return $this.sumN(a, n);
   }

   default Object sumN(final Object a, final int n) {
      if (n > 0) {
         return this.positiveSumN(a, n);
      } else {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal non-positive exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      }
   }

   // $FF: synthetic method
   static Object positiveSumN$(final AdditiveSemigroup $this, final Object a, final int n) {
      return $this.positiveSumN(a, n);
   }

   default Object positiveSumN(final Object a, final int n) {
      return n == 1 ? a : this.loop$1(a, n - 1, a);
   }

   // $FF: synthetic method
   static Option trySum$(final AdditiveSemigroup $this, final IterableOnce as) {
      return $this.trySum(as);
   }

   default Option trySum(final IterableOnce as) {
      return scala.collection.IterableOnceExtensionMethods..MODULE$.toIterator$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as)).reduceOption((x, y) -> this.plus(x, y));
   }

   // $FF: synthetic method
   static Semigroup additive$mcD$sp$(final AdditiveSemigroup $this) {
      return $this.additive$mcD$sp();
   }

   default Semigroup additive$mcD$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Semigroup additive$mcF$sp$(final AdditiveSemigroup $this) {
      return $this.additive$mcF$sp();
   }

   default Semigroup additive$mcF$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Semigroup additive$mcI$sp$(final AdditiveSemigroup $this) {
      return $this.additive$mcI$sp();
   }

   default Semigroup additive$mcI$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Semigroup additive$mcJ$sp$(final AdditiveSemigroup $this) {
      return $this.additive$mcJ$sp();
   }

   default Semigroup additive$mcJ$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static double plus$mcD$sp$(final AdditiveSemigroup $this, final double x, final double y) {
      return $this.plus$mcD$sp(x, y);
   }

   default double plus$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.plus(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float plus$mcF$sp$(final AdditiveSemigroup $this, final float x, final float y) {
      return $this.plus$mcF$sp(x, y);
   }

   default float plus$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.plus(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int plus$mcI$sp$(final AdditiveSemigroup $this, final int x, final int y) {
      return $this.plus$mcI$sp(x, y);
   }

   default int plus$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.plus(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long plus$mcJ$sp$(final AdditiveSemigroup $this, final long x, final long y) {
      return $this.plus$mcJ$sp(x, y);
   }

   default long plus$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.plus(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static double sumN$mcD$sp$(final AdditiveSemigroup $this, final double a, final int n) {
      return $this.sumN$mcD$sp(a, n);
   }

   default double sumN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.sumN(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float sumN$mcF$sp$(final AdditiveSemigroup $this, final float a, final int n) {
      return $this.sumN$mcF$sp(a, n);
   }

   default float sumN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.sumN(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int sumN$mcI$sp$(final AdditiveSemigroup $this, final int a, final int n) {
      return $this.sumN$mcI$sp(a, n);
   }

   default int sumN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.sumN(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long sumN$mcJ$sp$(final AdditiveSemigroup $this, final long a, final int n) {
      return $this.sumN$mcJ$sp(a, n);
   }

   default long sumN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.sumN(BoxesRunTime.boxToLong(a), n));
   }

   // $FF: synthetic method
   static double positiveSumN$mcD$sp$(final AdditiveSemigroup $this, final double a, final int n) {
      return $this.positiveSumN$mcD$sp(a, n);
   }

   default double positiveSumN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.positiveSumN(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float positiveSumN$mcF$sp$(final AdditiveSemigroup $this, final float a, final int n) {
      return $this.positiveSumN$mcF$sp(a, n);
   }

   default float positiveSumN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.positiveSumN(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int positiveSumN$mcI$sp$(final AdditiveSemigroup $this, final int a, final int n) {
      return $this.positiveSumN$mcI$sp(a, n);
   }

   default int positiveSumN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.positiveSumN(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long positiveSumN$mcJ$sp$(final AdditiveSemigroup $this, final long a, final int n) {
      return $this.positiveSumN$mcJ$sp(a, n);
   }

   default long positiveSumN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.positiveSumN(BoxesRunTime.boxToLong(a), n));
   }

   private Object loop$1(final Object b, final int k, final Object extra) {
      while(k != 1) {
         Object x = (k & 1) == 1 ? this.plus(b, extra) : extra;
         Object var10000 = this.plus(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.plus(b, extra);
   }

   static void $init$(final AdditiveSemigroup $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
