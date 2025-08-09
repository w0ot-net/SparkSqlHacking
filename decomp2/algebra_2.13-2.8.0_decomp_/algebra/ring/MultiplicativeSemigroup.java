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
   bytes = "\u0006\u0005\u0005\u0015caB\u0007\u000f!\u0003\r\ta\u0005\u0005\u0006O\u0001!\t\u0001\u000b\u0005\u0006Y\u0001!\t!\f\u0005\u0006+\u00021\tA\u0016\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0007I\u0002\u0001K\u0011C3\t\u000b!\u0004A\u0011A5\b\u000bIt\u0001\u0012A:\u0007\u000b5q\u0001\u0012A;\t\u000f\u0005%\u0001\u0002\"\u0001\u0002\f!9\u0011Q\u0002\u0005\u0005\u0006\u0005=\u0001B\u0002\u0017\t\t\u000b\t)\u0003C\u0005\u00026!\t\t\u0011\"\u0003\u00028\t9R*\u001e7uSBd\u0017nY1uSZ,7+Z7jOJ|W\u000f\u001d\u0006\u0003\u001fA\tAA]5oO*\t\u0011#A\u0004bY\u001e,'M]1\u0004\u0001U\u0011AcN\n\u0004\u0001UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"aA!osB\u0011A\u0004\n\b\u0003;\tr!AH\u0011\u000e\u0003}Q!\u0001\t\n\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0012BA\u0012\u0018\u0003\u001d\u0001\u0018mY6bO\u0016L!!\n\u0014\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\r:\u0012A\u0002\u0013j]&$H\u0005F\u0001*!\t1\"&\u0003\u0002,/\t!QK\\5u\u00039iW\u000f\u001c;ja2L7-\u0019;jm\u0016,\u0012A\f\t\u0004_I*dB\u0001\u00192\u001b\u0005\u0001\u0012BA\u0012\u0011\u0013\t\u0019DGA\u0005TK6LwM]8va*\u00111\u0005\u0005\t\u0003m]b\u0001\u0001B\u00059\u0001\u0001\u0006\t\u0011!b\u0001s\t\t\u0011)\u0005\u0002;+A\u0011acO\u0005\u0003y]\u0011qAT8uQ&tw\r\u000b\u00048}\u000535\n\u0015\t\u0003-}J!\u0001Q\f\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\t\u001bU\t\u0012\b\u0003-\rK!\u0001R\f\u0002\u0007%sG/\r\u0003%;\u0005B\u0012'B\u0012H\u0011*KeB\u0001\fI\u0013\tIu#\u0001\u0003M_:<\u0017\u0007\u0002\u0013\u001eCa\tTa\t'N\u001f:s!AF'\n\u00059;\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013\u001eCa\tTaI)S)Ns!A\u0006*\n\u0005M;\u0012A\u0002#pk\ndW-\r\u0003%;\u0005B\u0012!\u0002;j[\u0016\u001cHcA\u001bX3\")\u0001l\u0001a\u0001k\u0005\t\u0001\u0010C\u0003[\u0007\u0001\u0007Q'A\u0001z\u0003\r\u0001xn\u001e\u000b\u0004ku{\u0006\"\u00020\u0005\u0001\u0004)\u0014!A1\t\u000b\u0001$\u0001\u0019A1\u0002\u00039\u0004\"A\u00062\n\u0005\r<\"aA%oi\u0006Y\u0001o\\:ji&4X\rU8x)\r)dm\u001a\u0005\u0006=\u0016\u0001\r!\u000e\u0005\u0006A\u0016\u0001\r!Y\u0001\u000biJL\bK]8ek\u000e$HC\u00016n!\r12.N\u0005\u0003Y^\u0011aa\u00149uS>t\u0007\"\u00028\u0007\u0001\u0004y\u0017AA1t!\ra\u0002/N\u0005\u0003c\u001a\u0012q\u0002\u0016:bm\u0016\u00148/\u00192mK>s7-Z\u0001\u0018\u001bVdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB\u0004\"\u0001\u001e\u0005\u000e\u00039\u0019B\u0001\u0003<z{B\u0011ac^\u0005\u0003q^\u0011a!\u00118z%\u00164\u0007c\u0001;{y&\u00111P\u0004\u0002!\u001bVdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB4UO\\2uS>t7\u000f\u0005\u0002u\u0001A\u0019a0a\u0002\u000e\u0003}TA!!\u0001\u0002\u0004\u0005\u0011\u0011n\u001c\u0006\u0003\u0003\u000b\tAA[1wC&\u0011Qe`\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\fQ!\u00199qYf,B!!\u0005\u0002\u0018Q!\u00111CA\r!\u0011!\b!!\u0006\u0011\u0007Y\n9\u0002B\u00039\u0015\t\u0007\u0011\bC\u0004\u0002\u001c)\u0001\u001d!a\u0005\u0002\u0005\u00154\bf\u0001\u0006\u0002 A\u0019a#!\t\n\u0007\u0005\rrC\u0001\u0004j]2Lg.Z\u000b\u0005\u0003O\ti\u0003\u0006\u0003\u0002*\u0005=\u0002\u0003B\u00183\u0003W\u00012ANA\u0017\t\u0015A4B1\u0001:\u0011\u001d\tYb\u0003a\u0002\u0003c\u0001B\u0001\u001e\u0001\u0002,!\u001a1\"a\b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005e\u0002\u0003BA\u001e\u0003\u0003j!!!\u0010\u000b\t\u0005}\u00121A\u0001\u0005Y\u0006tw-\u0003\u0003\u0002D\u0005u\"AB(cU\u0016\u001cG\u000f"
)
public interface MultiplicativeSemigroup extends Serializable {
   static MultiplicativeSemigroup apply(final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroup$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroup$.MODULE$.isMultiplicativeCommutative(ev);
   }

   // $FF: synthetic method
   static Semigroup multiplicative$(final MultiplicativeSemigroup $this) {
      return $this.multiplicative();
   }

   default Semigroup multiplicative() {
      return new Semigroup() {
         // $FF: synthetic field
         private final MultiplicativeSemigroup $outer;

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

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
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
            return this.$outer.times(x, y);
         }

         public {
            if (MultiplicativeSemigroup.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeSemigroup.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   Object times(final Object x, final Object y);

   // $FF: synthetic method
   static Object pow$(final MultiplicativeSemigroup $this, final Object a, final int n) {
      return $this.pow(a, n);
   }

   default Object pow(final Object a, final int n) {
      if (n > 0) {
         return this.positivePow(a, n);
      } else {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal non-positive exponent to pow: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      }
   }

   // $FF: synthetic method
   static Object positivePow$(final MultiplicativeSemigroup $this, final Object a, final int n) {
      return $this.positivePow(a, n);
   }

   default Object positivePow(final Object a, final int n) {
      return n == 1 ? a : this.loop$1(a, n - 1, a);
   }

   // $FF: synthetic method
   static Option tryProduct$(final MultiplicativeSemigroup $this, final IterableOnce as) {
      return $this.tryProduct(as);
   }

   default Option tryProduct(final IterableOnce as) {
      return scala.collection.IterableOnceExtensionMethods..MODULE$.toIterator$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as)).reduceOption((x, y) -> this.times(x, y));
   }

   // $FF: synthetic method
   static Semigroup multiplicative$mcD$sp$(final MultiplicativeSemigroup $this) {
      return $this.multiplicative$mcD$sp();
   }

   default Semigroup multiplicative$mcD$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Semigroup multiplicative$mcF$sp$(final MultiplicativeSemigroup $this) {
      return $this.multiplicative$mcF$sp();
   }

   default Semigroup multiplicative$mcF$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Semigroup multiplicative$mcI$sp$(final MultiplicativeSemigroup $this) {
      return $this.multiplicative$mcI$sp();
   }

   default Semigroup multiplicative$mcI$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Semigroup multiplicative$mcJ$sp$(final MultiplicativeSemigroup $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default Semigroup multiplicative$mcJ$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static double times$mcD$sp$(final MultiplicativeSemigroup $this, final double x, final double y) {
      return $this.times$mcD$sp(x, y);
   }

   default double times$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.times(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float times$mcF$sp$(final MultiplicativeSemigroup $this, final float x, final float y) {
      return $this.times$mcF$sp(x, y);
   }

   default float times$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.times(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int times$mcI$sp$(final MultiplicativeSemigroup $this, final int x, final int y) {
      return $this.times$mcI$sp(x, y);
   }

   default int times$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.times(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long times$mcJ$sp$(final MultiplicativeSemigroup $this, final long x, final long y) {
      return $this.times$mcJ$sp(x, y);
   }

   default long times$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.times(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static double pow$mcD$sp$(final MultiplicativeSemigroup $this, final double a, final int n) {
      return $this.pow$mcD$sp(a, n);
   }

   default double pow$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.pow(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float pow$mcF$sp$(final MultiplicativeSemigroup $this, final float a, final int n) {
      return $this.pow$mcF$sp(a, n);
   }

   default float pow$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.pow(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int pow$mcI$sp$(final MultiplicativeSemigroup $this, final int a, final int n) {
      return $this.pow$mcI$sp(a, n);
   }

   default int pow$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.pow(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long pow$mcJ$sp$(final MultiplicativeSemigroup $this, final long a, final int n) {
      return $this.pow$mcJ$sp(a, n);
   }

   default long pow$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.pow(BoxesRunTime.boxToLong(a), n));
   }

   // $FF: synthetic method
   static double positivePow$mcD$sp$(final MultiplicativeSemigroup $this, final double a, final int n) {
      return $this.positivePow$mcD$sp(a, n);
   }

   default double positivePow$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.positivePow(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float positivePow$mcF$sp$(final MultiplicativeSemigroup $this, final float a, final int n) {
      return $this.positivePow$mcF$sp(a, n);
   }

   default float positivePow$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.positivePow(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int positivePow$mcI$sp$(final MultiplicativeSemigroup $this, final int a, final int n) {
      return $this.positivePow$mcI$sp(a, n);
   }

   default int positivePow$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.positivePow(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long positivePow$mcJ$sp$(final MultiplicativeSemigroup $this, final long a, final int n) {
      return $this.positivePow$mcJ$sp(a, n);
   }

   default long positivePow$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.positivePow(BoxesRunTime.boxToLong(a), n));
   }

   private Object loop$1(final Object b, final int k, final Object extra) {
      while(k != 1) {
         Object x = (k & 1) == 1 ? this.times(b, extra) : extra;
         Object var10000 = this.times(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.times(b, extra);
   }

   static void $init$(final MultiplicativeSemigroup $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
