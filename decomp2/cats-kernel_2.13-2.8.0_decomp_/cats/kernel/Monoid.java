package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=ca\u0002\b\u0010!\u0003\r\t\u0001\u0006\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u00021\ta\u0013\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u00063\u0002!\tE\u0017\u0005\u0006C\u0002!\tA\u0019\u0005\u0006Y\u0002!\t%\u001c\u0005\u0006e\u0002!\te]\u0004\u0006k>A\tA\u001e\u0004\u0006\u001d=A\ta\u001e\u0005\b\u0003\u0013IA\u0011AA\u0006\u0011\u001d\ti!\u0003C\u0003\u0003\u001fAq!a\t\n\t\u0003\t)\u0003C\u0005\u0002@%\t\t\u0011\"\u0003\u0002B\t1Qj\u001c8pS\u0012T!\u0001E\t\u0002\r-,'O\\3m\u0015\u0005\u0011\u0012\u0001B2biN\u001c\u0001!\u0006\u0002\u0016EM\u0019\u0001A\u0006\u000f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\u0007\u0005s\u0017\u0010E\u0002\u001e=\u0001j\u0011aD\u0005\u0003?=\u0011\u0011bU3nS\u001e\u0014x.\u001e9\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\nG\u0001\u0001\u000b\u0011!AC\u0002\u0011\u0012\u0011!Q\t\u0003KY\u0001\"a\u0006\u0014\n\u0005\u001dB\"a\u0002(pi\"Lgn\u001a\u0015\u0007E%bcg\u000f!\u0011\u0005]Q\u0013BA\u0016\u0019\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rjc\u0006M\u0018\u000f\u0005]q\u0013BA\u0018\u0019\u0003\rIe\u000e^\u0019\u0005IE*\u0014D\u0004\u00023k5\t1G\u0003\u00025'\u00051AH]8pizJ\u0011!G\u0019\u0006G]B$(\u000f\b\u0003/aJ!!\u000f\r\u0002\t1{gnZ\u0019\u0005IE*\u0014$M\u0003$yuzdH\u0004\u0002\u0018{%\u0011a\bG\u0001\u0006\r2|\u0017\r^\u0019\u0005IE*\u0014$M\u0003$\u0003\n#5I\u0004\u0002\u0018\u0005&\u00111\tG\u0001\u0007\t>,(\r\\32\t\u0011\nT'G\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u001d\u0003\"a\u0006%\n\u0005%C\"\u0001B+oSR\fQ!Z7qif,\u0012\u0001I\u0001\bSN,U\u000e\u001d;z)\tqu\u000b\u0006\u0002P%B\u0011q\u0003U\u0005\u0003#b\u0011qAQ8pY\u0016\fg\u000eC\u0003T\u0007\u0001\u000fA+\u0001\u0002fmB\u0019Q$\u0016\u0011\n\u0005Y{!AA#r\u0011\u0015A6\u00011\u0001!\u0003\u0005\t\u0017\u0001C2p[\nLg.\u001a(\u0015\u0007\u0001ZF\fC\u0003Y\t\u0001\u0007\u0001\u0005C\u0003^\t\u0001\u0007a,A\u0001o!\t9r,\u0003\u0002a1\t\u0019\u0011J\u001c;\u0002\u0015\r|WNY5oK\u0006cG\u000e\u0006\u0002!G\")A-\u0002a\u0001K\u0006\u0011\u0011m\u001d\t\u0004M&\u0004cBA\u0019h\u0013\tA\u0007$A\u0004qC\u000e\\\u0017mZ3\n\u0005)\\'\u0001D%uKJ\f'\r\\3P]\u000e,'B\u00015\u0019\u0003A\u0019w.\u001c2j]\u0016\fE\u000e\\(qi&|g\u000e\u0006\u0002ocB\u0019qc\u001c\u0011\n\u0005AD\"AB(qi&|g\u000eC\u0003e\r\u0001\u0007Q-A\u0004sKZ,'o]3\u0016\u0003Q\u00042!\b\u0001!\u0003\u0019iuN\\8jIB\u0011Q$C\n\u0004\u0013ad\bcA\u000fzw&\u0011!p\u0004\u0002\u0010\u001b>tw.\u001b3Gk:\u001cG/[8ogB\u0011Q\u0004\u0001\t\u0004{\u0006\u0015Q\"\u0001@\u000b\u0007}\f\t!\u0001\u0002j_*\u0011\u00111A\u0001\u0005U\u00064\u0018-C\u0002\u0002\by\u0014AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u0001<\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005E\u0011q\u0003\u000b\u0005\u0003'\tI\u0002\u0005\u0003\u001e\u0001\u0005U\u0001cA\u0011\u0002\u0018\u0011)1e\u0003b\u0001I!11k\u0003a\u0002\u0003'A3aCA\u000f!\r9\u0012qD\u0005\u0004\u0003CA\"AB5oY&tW-\u0001\u0005j]N$\u0018M\\2f+\u0011\t9#!\f\u0015\r\u0005%\u0012qFA\u001a!\u0011i\u0002!a\u000b\u0011\u0007\u0005\ni\u0003B\u0003$\u0019\t\u0007A\u0005C\u0004\u000221\u0001\r!a\u000b\u0002\u0015\u0015l\u0007\u000f^=WC2,X\rC\u0004\u000261\u0001\r!a\u000e\u0002\u0007\rl'\rE\u0005\u0018\u0003s\tY#a\u000b\u0002,%\u0019\u00111\b\r\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004f\u0001\u0007\u0002\u001e\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\t\t\u0005\u0003\u000b\nY%\u0004\u0002\u0002H)!\u0011\u0011JA\u0001\u0003\u0011a\u0017M\\4\n\t\u00055\u0013q\t\u0002\u0007\u001f\nTWm\u0019;"
)
public interface Monoid extends Semigroup {
   static Monoid instance(final Object emptyValue, final Function2 cmb) {
      return Monoid$.MODULE$.instance(emptyValue, cmb);
   }

   static Monoid apply(final Monoid ev) {
      return Monoid$.MODULE$.apply(ev);
   }

   static boolean isIdempotent(final Semigroup ev) {
      return Monoid$.MODULE$.isIdempotent(ev);
   }

   static boolean isCommutative(final Semigroup ev) {
      return Monoid$.MODULE$.isCommutative(ev);
   }

   static Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      return Monoid$.MODULE$.maybeCombine(x, oy, ev);
   }

   static Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      return Monoid$.MODULE$.maybeCombine(ox, y, ev);
   }

   Object empty();

   default boolean isEmpty(final Object a, final Eq ev) {
      return ev.eqv(a, this.empty());
   }

   // $FF: synthetic method
   static Object combineN$(final Monoid $this, final Object a, final int n) {
      return $this.combineN(a, n);
   }

   default Object combineN(final Object a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty() : this.repeatedCombineN(a, n);
      }
   }

   default Object combineAll(final IterableOnce as) {
      return as.iterator().foldLeft(this.empty(), (x, y) -> this.combine(x, y));
   }

   default Option combineAllOption(final IterableOnce as) {
      return (Option)(as.iterator().isEmpty() ? .MODULE$ : new Some(this.combineAll(as)));
   }

   // $FF: synthetic method
   static Monoid reverse$(final Monoid $this) {
      return $this.reverse();
   }

   default Monoid reverse() {
      return new Monoid() {
         // $FF: synthetic field
         private final Monoid $outer;

         public double empty$mcD$sp() {
            return Monoid.super.empty$mcD$sp();
         }

         public float empty$mcF$sp() {
            return Monoid.super.empty$mcF$sp();
         }

         public int empty$mcI$sp() {
            return Monoid.super.empty$mcI$sp();
         }

         public long empty$mcJ$sp() {
            return Monoid.super.empty$mcJ$sp();
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.super.isEmpty(a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.super.isEmpty$mcD$sp(a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.super.isEmpty$mcF$sp(a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.super.isEmpty$mcI$sp(a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.super.isEmpty$mcJ$sp(a, ev);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.super.combineN$mcD$sp(a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.super.combineN$mcF$sp(a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.super.combineN$mcI$sp(a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.super.combineN$mcJ$sp(a, n);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.super.combineAll(as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.super.combineAll$mcD$sp(as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.super.combineAll$mcF$sp(as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.super.combineAll$mcI$sp(as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.super.combineAll$mcJ$sp(as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.super.combineAllOption(as);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.super.reverse$mcD$sp();
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.super.reverse$mcF$sp();
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.super.reverse$mcI$sp();
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.super.reverse$mcJ$sp();
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
            return this.$outer.empty();
         }

         public Object combine(final Object a, final Object b) {
            return this.$outer.combine(b, a);
         }

         public Object combineN(final Object a, final int n) {
            return this.$outer.combineN(a, n);
         }

         public Monoid reverse() {
            return this.$outer;
         }

         public {
            if (Monoid.this == null) {
               throw null;
            } else {
               this.$outer = Monoid.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   default double empty$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.empty());
   }

   default float empty$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.empty());
   }

   default int empty$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.empty());
   }

   default long empty$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.empty());
   }

   default boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return this.isEmpty(BoxesRunTime.boxToDouble(a), ev);
   }

   default boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return this.isEmpty(BoxesRunTime.boxToFloat(a), ev);
   }

   default boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return this.isEmpty(BoxesRunTime.boxToInteger(a), ev);
   }

   default boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return this.isEmpty(BoxesRunTime.boxToLong(a), ev);
   }

   default double combineN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.combineN(BoxesRunTime.boxToDouble(a), n));
   }

   default float combineN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.combineN(BoxesRunTime.boxToFloat(a), n));
   }

   default int combineN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.combineN(BoxesRunTime.boxToInteger(a), n));
   }

   default long combineN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.combineN(BoxesRunTime.boxToLong(a), n));
   }

   default double combineAll$mcD$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToDouble(this.combineAll(as));
   }

   default float combineAll$mcF$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToFloat(this.combineAll(as));
   }

   default int combineAll$mcI$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToInt(this.combineAll(as));
   }

   default long combineAll$mcJ$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToLong(this.combineAll(as));
   }

   default Monoid reverse$mcD$sp() {
      return this.reverse();
   }

   default Monoid reverse$mcF$sp() {
      return this.reverse();
   }

   default Monoid reverse$mcI$sp() {
      return this.reverse();
   }

   default Monoid reverse$mcJ$sp() {
      return this.reverse();
   }

   static void $init$(final Monoid $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
