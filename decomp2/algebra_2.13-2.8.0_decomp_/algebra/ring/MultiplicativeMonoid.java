package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uca\u0002\b\u0010!\u0003\r\t\u0001\u0006\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u0002!\te\u0013\u0005\u0006)\u00021\t!\u0016\u0005\u0006-\u0002!\ta\u0016\u0005\u0006G\u0002!\t\u0005\u001a\u0005\u0006W\u0002!\t\u0001\u001c\u0005\u0006k\u0002!\tE^\u0004\u0006w>A\t\u0001 \u0004\u0006\u001d=A\t! \u0005\b\u00037IA\u0011AA\u000f\u0011\u001d\ty\"\u0003C\u0003\u0003CAaAS\u0005\u0005\u0006\u0005U\u0002\"CA#\u0013\u0005\u0005I\u0011BA$\u0005QiU\u000f\u001c;ja2L7-\u0019;jm\u0016luN\\8jI*\u0011\u0001#E\u0001\u0005e&twMC\u0001\u0013\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u0016EM\u0019\u0001A\u0006\u000f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\u0007\u0005s\u0017\u0010E\u0002\u001e=\u0001j\u0011aD\u0005\u0003?=\u0011q#T;mi&\u0004H.[2bi&4XmU3nS\u001e\u0014x.\u001e9\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\nG\u0001\u0001\u000b\u0011!AC\u0002\u0011\u0012\u0011!Q\t\u0003KY\u0001\"a\u0006\u0014\n\u0005\u001dB\"a\u0002(pi\"Lgn\u001a\u0015\u0007E%bcg\u000f!\u0011\u0005]Q\u0013BA\u0016\u0019\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rjc\u0006M\u0018\u000f\u0005]q\u0013BA\u0018\u0019\u0003\rIe\u000e^\u0019\u0005IE*\u0014D\u0004\u00023k5\t1G\u0003\u00025'\u00051AH]8pizJ\u0011!G\u0019\u0006G]B$(\u000f\b\u0003/aJ!!\u000f\r\u0002\t1{gnZ\u0019\u0005IE*\u0014$M\u0003$yuzdH\u0004\u0002\u0018{%\u0011a\bG\u0001\u0006\r2|\u0017\r^\u0019\u0005IE*\u0014$M\u0003$\u0003\n#5I\u0004\u0002\u0018\u0005&\u00111\tG\u0001\u0007\t>,(\r\\32\t\u0011\nT'G\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u001d\u0003\"a\u0006%\n\u0005%C\"\u0001B+oSR\fa\"\\;mi&\u0004H.[2bi&4X-F\u0001M!\ri\u0015\u000b\t\b\u0003\u001d>k\u0011!E\u0005\u0003!F\tq\u0001]1dW\u0006<W-\u0003\u0002S'\n1Qj\u001c8pS\u0012T!\u0001U\t\u0002\u0007=tW-F\u0001!\u0003\u0015I7o\u00148f)\tA\u0016\r\u0006\u0002Z9B\u0011qCW\u0005\u00037b\u0011qAQ8pY\u0016\fg\u000eC\u0003^\t\u0001\u000fa,\u0001\u0002fmB\u0019Qj\u0018\u0011\n\u0005\u0001\u001c&AA#r\u0011\u0015\u0011G\u00011\u0001!\u0003\u0005\t\u0017a\u00019poR\u0019\u0001%\u001a4\t\u000b\t,\u0001\u0019\u0001\u0011\t\u000b\u001d,\u0001\u0019\u00015\u0002\u00039\u0004\"aF5\n\u0005)D\"aA%oi\u00069\u0001O]8ek\u000e$HC\u0001\u0011n\u0011\u0015qg\u00011\u0001p\u0003\t\t7\u000fE\u0002qe\u0002r!!M9\n\u0005AC\u0012BA:u\u0005=!&/\u0019<feN\f'\r\\3P]\u000e,'B\u0001)\u0019\u0003)!(/\u001f)s_\u0012,8\r\u001e\u000b\u0003oj\u00042a\u0006=!\u0013\tI\bD\u0001\u0004PaRLwN\u001c\u0005\u0006]\u001e\u0001\ra\\\u0001\u0015\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u001b>tw.\u001b3\u0011\u0005uI1CB\u0005\u007f\u0003\u0007\tY\u0001\u0005\u0002\u0018\u007f&\u0019\u0011\u0011\u0001\r\u0003\r\u0005s\u0017PU3g!\u0015i\u0012QAA\u0005\u0013\r\t9a\u0004\u0002\u001e\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u001b>tw.\u001b3Gk:\u001cG/[8ogB\u0011Q\u0004\u0001\t\u0005\u0003\u001b\t9\"\u0004\u0002\u0002\u0010)!\u0011\u0011CA\n\u0003\tIwN\u0003\u0002\u0002\u0016\u0005!!.\u0019<b\u0013\u0011\tI\"a\u0004\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005a\u0018!B1qa2LX\u0003BA\u0012\u0003S!B!!\n\u0002,A!Q\u0004AA\u0014!\r\t\u0013\u0011\u0006\u0003\u0006G-\u0011\r\u0001\n\u0005\u0007;.\u0001\u001d!!\n)\u0007-\ty\u0003E\u0002\u0018\u0003cI1!a\r\u0019\u0005\u0019Ig\u000e\\5oKV!\u0011qGA\u001f)\u0011\tI$a\u0010\u0011\t5\u000b\u00161\b\t\u0004C\u0005uB!B\u0012\r\u0005\u0004!\u0003BB/\r\u0001\b\t\t\u0005\u0005\u0003\u001e\u0001\u0005m\u0002f\u0001\u0007\u00020\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\n\t\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)!\u0011qJA\n\u0003\u0011a\u0017M\\4\n\t\u0005M\u0013Q\n\u0002\u0007\u001f\nTWm\u0019;"
)
public interface MultiplicativeMonoid extends MultiplicativeSemigroup {
   static MultiplicativeMonoid apply(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoid$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeMonoid$.MODULE$.isMultiplicativeCommutative(ev);
   }

   // $FF: synthetic method
   static Monoid multiplicative$(final MultiplicativeMonoid $this) {
      return $this.multiplicative();
   }

   default Monoid multiplicative() {
      return new Monoid() {
         // $FF: synthetic field
         private final MultiplicativeMonoid $outer;

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineN(final Object a, final int n) {
            return Monoid.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.combineN$mcJ$sp$(this, a, n);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

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

         public Object empty() {
            return this.$outer.one();
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.times(x, y);
         }

         public {
            if (MultiplicativeMonoid.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeMonoid.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   Object one();

   // $FF: synthetic method
   static boolean isOne$(final MultiplicativeMonoid $this, final Object a, final Eq ev) {
      return $this.isOne(a, ev);
   }

   default boolean isOne(final Object a, final Eq ev) {
      return ev.eqv(a, this.one());
   }

   // $FF: synthetic method
   static Object pow$(final MultiplicativeMonoid $this, final Object a, final int n) {
      return $this.pow(a, n);
   }

   default Object pow(final Object a, final int n) {
      Object var10000;
      if (n > 0) {
         var10000 = this.positivePow(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to pow: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.one();
      }

      return var10000;
   }

   // $FF: synthetic method
   static Object product$(final MultiplicativeMonoid $this, final IterableOnce as) {
      return $this.product(as);
   }

   default Object product(final IterableOnce as) {
      return scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), this.one(), (x, y) -> this.times(x, y));
   }

   // $FF: synthetic method
   static Option tryProduct$(final MultiplicativeMonoid $this, final IterableOnce as) {
      return $this.tryProduct(as);
   }

   default Option tryProduct(final IterableOnce as) {
      return (Option)(scala.collection.IterableOnceExtensionMethods..MODULE$.isEmpty$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as)) ? scala.None..MODULE$ : new Some(this.product(as)));
   }

   // $FF: synthetic method
   static Monoid multiplicative$mcD$sp$(final MultiplicativeMonoid $this) {
      return $this.multiplicative$mcD$sp();
   }

   default Monoid multiplicative$mcD$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Monoid multiplicative$mcF$sp$(final MultiplicativeMonoid $this) {
      return $this.multiplicative$mcF$sp();
   }

   default Monoid multiplicative$mcF$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Monoid multiplicative$mcI$sp$(final MultiplicativeMonoid $this) {
      return $this.multiplicative$mcI$sp();
   }

   default Monoid multiplicative$mcI$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Monoid multiplicative$mcJ$sp$(final MultiplicativeMonoid $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default Monoid multiplicative$mcJ$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static double one$mcD$sp$(final MultiplicativeMonoid $this) {
      return $this.one$mcD$sp();
   }

   default double one$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.one());
   }

   // $FF: synthetic method
   static float one$mcF$sp$(final MultiplicativeMonoid $this) {
      return $this.one$mcF$sp();
   }

   default float one$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.one());
   }

   // $FF: synthetic method
   static int one$mcI$sp$(final MultiplicativeMonoid $this) {
      return $this.one$mcI$sp();
   }

   default int one$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.one());
   }

   // $FF: synthetic method
   static long one$mcJ$sp$(final MultiplicativeMonoid $this) {
      return $this.one$mcJ$sp();
   }

   default long one$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.one());
   }

   // $FF: synthetic method
   static boolean isOne$mcD$sp$(final MultiplicativeMonoid $this, final double a, final Eq ev) {
      return $this.isOne$mcD$sp(a, ev);
   }

   default boolean isOne$mcD$sp(final double a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToDouble(a), ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcF$sp$(final MultiplicativeMonoid $this, final float a, final Eq ev) {
      return $this.isOne$mcF$sp(a, ev);
   }

   default boolean isOne$mcF$sp(final float a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToFloat(a), ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcI$sp$(final MultiplicativeMonoid $this, final int a, final Eq ev) {
      return $this.isOne$mcI$sp(a, ev);
   }

   default boolean isOne$mcI$sp(final int a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToInteger(a), ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcJ$sp$(final MultiplicativeMonoid $this, final long a, final Eq ev) {
      return $this.isOne$mcJ$sp(a, ev);
   }

   default boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToLong(a), ev);
   }

   // $FF: synthetic method
   static double pow$mcD$sp$(final MultiplicativeMonoid $this, final double a, final int n) {
      return $this.pow$mcD$sp(a, n);
   }

   default double pow$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.pow(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float pow$mcF$sp$(final MultiplicativeMonoid $this, final float a, final int n) {
      return $this.pow$mcF$sp(a, n);
   }

   default float pow$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.pow(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int pow$mcI$sp$(final MultiplicativeMonoid $this, final int a, final int n) {
      return $this.pow$mcI$sp(a, n);
   }

   default int pow$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.pow(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long pow$mcJ$sp$(final MultiplicativeMonoid $this, final long a, final int n) {
      return $this.pow$mcJ$sp(a, n);
   }

   default long pow$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.pow(BoxesRunTime.boxToLong(a), n));
   }

   // $FF: synthetic method
   static double product$mcD$sp$(final MultiplicativeMonoid $this, final IterableOnce as) {
      return $this.product$mcD$sp(as);
   }

   default double product$mcD$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToDouble(this.product(as));
   }

   // $FF: synthetic method
   static float product$mcF$sp$(final MultiplicativeMonoid $this, final IterableOnce as) {
      return $this.product$mcF$sp(as);
   }

   default float product$mcF$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToFloat(this.product(as));
   }

   // $FF: synthetic method
   static int product$mcI$sp$(final MultiplicativeMonoid $this, final IterableOnce as) {
      return $this.product$mcI$sp(as);
   }

   default int product$mcI$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToInt(this.product(as));
   }

   // $FF: synthetic method
   static long product$mcJ$sp$(final MultiplicativeMonoid $this, final IterableOnce as) {
      return $this.product$mcJ$sp(as);
   }

   default long product$mcJ$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToLong(this.product(as));
   }

   static void $init$(final MultiplicativeMonoid $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
