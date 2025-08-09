package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%ba\u0002\u0007\u000e!\u0003\r\tA\u0005\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006%\u00021\ta\u0015\u0005\u0006-\u0002!\ta\u0016\u0005\u00067\u0002!\t\u0005X\u0004\u0006I6A\t!\u001a\u0004\u0006\u00195A\tA\u001a\u0005\u0006m\u001e!\ta\u001e\u0005\u0006q\u001e!)!\u001f\u0005\u0007\u0011\u001e!)!!\u0003\t\u0013\u0005eq!!A\u0005\n\u0005m!!D!eI&$\u0018N^3He>,\bO\u0003\u0002\u000f\u001f\u0005!!/\u001b8h\u0015\u0005\u0001\u0012aB1mO\u0016\u0014'/Y\u0002\u0001+\t\u0019\u0002eE\u0002\u0001)i\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u00111!\u00118z!\rYBDH\u0007\u0002\u001b%\u0011Q$\u0004\u0002\u000f\u0003\u0012$\u0017\u000e^5wK6{gn\\5e!\ty\u0002\u0005\u0004\u0001\u0005\u0013\u0005\u0002\u0001\u0015!A\u0001\u0006\u0004\u0011#!A!\u0012\u0005\r\"\u0002CA\u000b%\u0013\t)cCA\u0004O_RD\u0017N\\4)\r\u0001:#\u0006N\u001d?!\t)\u0002&\u0003\u0002*-\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u00193\u0006\f\u0018.\u001d\t)B&\u0003\u0002.-\u0005\u0019\u0011J\u001c;2\t\u0011z3g\u0006\b\u0003aMj\u0011!\r\u0006\u0003eE\ta\u0001\u0010:p_Rt\u0014\"A\f2\u000b\r*d\u0007O\u001c\u000f\u0005U1\u0014BA\u001c\u0017\u0003\u0011auN\\42\t\u0011z3gF\u0019\u0006GiZT\b\u0010\b\u0003+mJ!\u0001\u0010\f\u0002\u000b\u0019cw.\u0019;2\t\u0011z3gF\u0019\u0006G}\u0002%)\u0011\b\u0003+\u0001K!!\u0011\f\u0002\r\u0011{WO\u00197fc\u0011!sfM\f\u0002\r\u0011Jg.\u001b;%)\u0005)\u0005CA\u000bG\u0013\t9eC\u0001\u0003V]&$\u0018\u0001C1eI&$\u0018N^3\u0016\u0003)\u00032aS(\u001f\u001d\taU*D\u0001\u0010\u0013\tqu\"A\u0004qC\u000e\\\u0017mZ3\n\u0005A\u000b&!B$s_V\u0004(B\u0001(\u0010\u0003\u0019qWmZ1uKR\u0011a\u0004\u0016\u0005\u0006+\u000e\u0001\rAH\u0001\u0002q\u0006)Q.\u001b8vgR\u0019a\u0004W-\t\u000bU#\u0001\u0019\u0001\u0010\t\u000bi#\u0001\u0019\u0001\u0010\u0002\u0003e\fAa];n\u001dR\u0019a$X0\t\u000by+\u0001\u0019\u0001\u0010\u0002\u0003\u0005DQ\u0001Y\u0003A\u0002\u0005\f\u0011A\u001c\t\u0003+\tL!a\u0019\f\u0003\u0007%sG/A\u0007BI\u0012LG/\u001b<f\u000fJ|W\u000f\u001d\t\u00037\u001d\u0019BaB4k]B\u0011Q\u0003[\u0005\u0003SZ\u0011a!\u00118z%\u00164\u0007cA\u000el[&\u0011A.\u0004\u0002\u0017\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9Gk:\u001cG/[8ogB\u00111\u0004\u0001\t\u0003_Rl\u0011\u0001\u001d\u0006\u0003cJ\f!![8\u000b\u0003M\fAA[1wC&\u0011Q\u000f\u001d\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0015\fQ!\u00199qYf,\"A_?\u0015\u0005mt\bcA\u000e\u0001yB\u0011q$ \u0003\u0006C%\u0011\rA\t\u0005\u0006\u007f&\u0001\u001da_\u0001\u0003KZD3!CA\u0002!\r)\u0012QA\u0005\u0004\u0003\u000f1\"AB5oY&tW-\u0006\u0003\u0002\f\u0005EA\u0003BA\u0007\u0003'\u0001BaS(\u0002\u0010A\u0019q$!\u0005\u0005\u000b\u0005R!\u0019\u0001\u0012\t\r}T\u00019AA\u000b!\u0011Y\u0002!a\u0004)\u0007)\t\u0019!\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001eA!\u0011qDA\u0013\u001b\t\t\tCC\u0002\u0002$I\fA\u0001\\1oO&!\u0011qEA\u0011\u0005\u0019y%M[3di\u0002"
)
public interface AdditiveGroup extends AdditiveMonoid {
   static AdditiveGroup apply(final AdditiveGroup ev) {
      return AdditiveGroup$.MODULE$.apply(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveGroup$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static Group additive$(final AdditiveGroup $this) {
      return $this.additive();
   }

   default Group additive() {
      return new Group() {
         // $FF: synthetic field
         private final AdditiveGroup $outer;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

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
            return this.$outer.zero();
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.plus(x, y);
         }

         public Object remove(final Object x, final Object y) {
            return this.$outer.minus(x, y);
         }

         public Object inverse(final Object x) {
            return this.$outer.negate(x);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public Object combineAll(final IterableOnce as) {
            return this.$outer.sum(as);
         }

         public {
            if (AdditiveGroup.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveGroup.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
            }
         }
      };
   }

   Object negate(final Object x);

   // $FF: synthetic method
   static Object minus$(final AdditiveGroup $this, final Object x, final Object y) {
      return $this.minus(x, y);
   }

   default Object minus(final Object x, final Object y) {
      return this.plus(x, this.negate(y));
   }

   // $FF: synthetic method
   static Object sumN$(final AdditiveGroup $this, final Object a, final int n) {
      return $this.sumN(a, n);
   }

   default Object sumN(final Object a, final int n) {
      return n > 0 ? this.positiveSumN(a, n) : (n == 0 ? this.zero() : (n == Integer.MIN_VALUE ? this.positiveSumN(this.negate(this.plus(a, a)), 1073741824) : this.positiveSumN(this.negate(a), -n)));
   }

   // $FF: synthetic method
   static Group additive$mcD$sp$(final AdditiveGroup $this) {
      return $this.additive$mcD$sp();
   }

   default Group additive$mcD$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Group additive$mcF$sp$(final AdditiveGroup $this) {
      return $this.additive$mcF$sp();
   }

   default Group additive$mcF$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Group additive$mcI$sp$(final AdditiveGroup $this) {
      return $this.additive$mcI$sp();
   }

   default Group additive$mcI$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Group additive$mcJ$sp$(final AdditiveGroup $this) {
      return $this.additive$mcJ$sp();
   }

   default Group additive$mcJ$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static double negate$mcD$sp$(final AdditiveGroup $this, final double x) {
      return $this.negate$mcD$sp(x);
   }

   default double negate$mcD$sp(final double x) {
      return BoxesRunTime.unboxToDouble(this.negate(BoxesRunTime.boxToDouble(x)));
   }

   // $FF: synthetic method
   static float negate$mcF$sp$(final AdditiveGroup $this, final float x) {
      return $this.negate$mcF$sp(x);
   }

   default float negate$mcF$sp(final float x) {
      return BoxesRunTime.unboxToFloat(this.negate(BoxesRunTime.boxToFloat(x)));
   }

   // $FF: synthetic method
   static int negate$mcI$sp$(final AdditiveGroup $this, final int x) {
      return $this.negate$mcI$sp(x);
   }

   default int negate$mcI$sp(final int x) {
      return BoxesRunTime.unboxToInt(this.negate(BoxesRunTime.boxToInteger(x)));
   }

   // $FF: synthetic method
   static long negate$mcJ$sp$(final AdditiveGroup $this, final long x) {
      return $this.negate$mcJ$sp(x);
   }

   default long negate$mcJ$sp(final long x) {
      return BoxesRunTime.unboxToLong(this.negate(BoxesRunTime.boxToLong(x)));
   }

   // $FF: synthetic method
   static double minus$mcD$sp$(final AdditiveGroup $this, final double x, final double y) {
      return $this.minus$mcD$sp(x, y);
   }

   default double minus$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.minus(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float minus$mcF$sp$(final AdditiveGroup $this, final float x, final float y) {
      return $this.minus$mcF$sp(x, y);
   }

   default float minus$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.minus(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int minus$mcI$sp$(final AdditiveGroup $this, final int x, final int y) {
      return $this.minus$mcI$sp(x, y);
   }

   default int minus$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.minus(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long minus$mcJ$sp$(final AdditiveGroup $this, final long x, final long y) {
      return $this.minus$mcJ$sp(x, y);
   }

   default long minus$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.minus(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static double sumN$mcD$sp$(final AdditiveGroup $this, final double a, final int n) {
      return $this.sumN$mcD$sp(a, n);
   }

   default double sumN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.sumN(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float sumN$mcF$sp$(final AdditiveGroup $this, final float a, final int n) {
      return $this.sumN$mcF$sp(a, n);
   }

   default float sumN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.sumN(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int sumN$mcI$sp$(final AdditiveGroup $this, final int a, final int n) {
      return $this.sumN$mcI$sp(a, n);
   }

   default int sumN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.sumN(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long sumN$mcJ$sp$(final AdditiveGroup $this, final long a, final int n) {
      return $this.sumN$mcJ$sp(a, n);
   }

   default long sumN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.sumN(BoxesRunTime.boxToLong(a), n));
   }

   static void $init$(final AdditiveGroup $this) {
   }
}
