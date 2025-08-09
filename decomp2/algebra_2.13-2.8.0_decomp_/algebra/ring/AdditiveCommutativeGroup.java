package algebra.ring;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015aaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t%S\u0004\u0006%*A\ta\u0015\u0004\u0006\u0013)A\t\u0001\u0016\u0005\u0006I\u0012!\t!\u001a\u0005\u0006M\u0012!)a\u001a\u0005\u0006\u0011\u0012!)A\u001d\u0005\bu\u0012\t\t\u0011\"\u0003|\u0005a\tE\rZ5uSZ,7i\\7nkR\fG/\u001b<f\u000fJ|W\u000f\u001d\u0006\u0003\u00171\tAA]5oO*\tQ\"A\u0004bY\u001e,'M]1\u0004\u0001U\u0011\u0001#H\n\u0005\u0001E9\u0002\t\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12CA\u0002B]f\u00042\u0001G\r\u001c\u001b\u0005Q\u0011B\u0001\u000e\u000b\u00055\tE\rZ5uSZ,wI]8vaB\u0011A$\b\u0007\u0001\t%q\u0002\u0001)A\u0001\u0002\u000b\u0007qDA\u0001B#\t\u0001\u0013\u0003\u0005\u0002\u0013C%\u0011!e\u0005\u0002\b\u001d>$\b.\u001b8hQ\u0019iBeJ\u00197wA\u0011!#J\u0005\u0003MM\u00111b\u001d9fG&\fG.\u001b>fIF*1\u0005K\u0015,U9\u0011!#K\u0005\u0003UM\t1!\u00138uc\u0011!C\u0006\r\u000b\u000f\u00055\u0002T\"\u0001\u0018\u000b\u0005=r\u0011A\u0002\u001fs_>$h(C\u0001\u0015c\u0015\u0019#gM\u001b5\u001d\t\u00112'\u0003\u00025'\u0005!Aj\u001c8hc\u0011!C\u0006\r\u000b2\u000b\r:\u0004HO\u001d\u000f\u0005IA\u0014BA\u001d\u0014\u0003\u00151En\\1uc\u0011!C\u0006\r\u000b2\u000b\rbTh\u0010 \u000f\u0005Ii\u0014B\u0001 \u0014\u0003\u0019!u.\u001e2mKF\"A\u0005\f\u0019\u0015!\rA\u0012iG\u0005\u0003\u0005*\u0011\u0011$\u00113eSRLg/Z\"p[6,H/\u0019;jm\u0016luN\\8jI\u00061A%\u001b8ji\u0012\"\u0012!\u0012\t\u0003%\u0019K!aR\n\u0003\tUs\u0017\u000e^\u0001\tC\u0012$\u0017\u000e^5wKV\t!\nE\u0002L\u001fnq!\u0001T'\u000e\u00031I!A\u0014\u0007\u0002\u000fA\f7m[1hK&\u0011\u0001+\u0015\u0002\u0011\u0007>lW.\u001e;bi&4Xm\u0012:pkBT!A\u0014\u0007\u00021\u0005#G-\u001b;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3He>,\b\u000f\u0005\u0002\u0019\tM!A!\u0016-]!\t\u0011b+\u0003\u0002X'\t1\u0011I\\=SK\u001a\u00042\u0001G-\\\u0013\tQ&B\u0001\fBI\u0012LG/\u001b<f\u000fJ|W\u000f\u001d$v]\u000e$\u0018n\u001c8t!\tA\u0002\u0001\u0005\u0002^E6\taL\u0003\u0002`A\u0006\u0011\u0011n\u001c\u0006\u0002C\u0006!!.\u0019<b\u0013\t\u0019gL\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002'\u0006)\u0011\r\u001d9msV\u0011\u0001n\u001b\u000b\u0003S2\u00042\u0001\u0007\u0001k!\ta2\u000eB\u0003\u001f\r\t\u0007q\u0004C\u0003n\r\u0001\u000f\u0011.\u0001\u0002fm\"\u0012aa\u001c\t\u0003%AL!!]\n\u0003\r%tG.\u001b8f+\t\u0019h\u000f\u0006\u0002uoB\u00191jT;\u0011\u0005q1H!\u0002\u0010\b\u0005\u0004y\u0002\"B7\b\u0001\bA\bc\u0001\r\u0001k\"\u0012qa\\\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002yB\u0019Q0!\u0001\u000e\u0003yT!a 1\u0002\t1\fgnZ\u0005\u0004\u0003\u0007q(AB(cU\u0016\u001cG\u000f"
)
public interface AdditiveCommutativeGroup extends AdditiveGroup, AdditiveCommutativeMonoid {
   static AdditiveCommutativeGroup apply(final AdditiveCommutativeGroup ev) {
      return AdditiveCommutativeGroup$.MODULE$.apply(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveCommutativeGroup$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static CommutativeGroup additive$(final AdditiveCommutativeGroup $this) {
      return $this.additive();
   }

   default CommutativeGroup additive() {
      return new CommutativeGroup() {
         // $FF: synthetic field
         private final AdditiveCommutativeGroup $outer;

         public CommutativeMonoid reverse() {
            return CommutativeMonoid.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

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
            if (AdditiveCommutativeGroup.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeGroup.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static CommutativeGroup additive$mcD$sp$(final AdditiveCommutativeGroup $this) {
      return $this.additive$mcD$sp();
   }

   default CommutativeGroup additive$mcD$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeGroup additive$mcF$sp$(final AdditiveCommutativeGroup $this) {
      return $this.additive$mcF$sp();
   }

   default CommutativeGroup additive$mcF$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeGroup additive$mcI$sp$(final AdditiveCommutativeGroup $this) {
      return $this.additive$mcI$sp();
   }

   default CommutativeGroup additive$mcI$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeGroup additive$mcJ$sp$(final AdditiveCommutativeGroup $this) {
      return $this.additive$mcJ$sp();
   }

   default CommutativeGroup additive$mcJ$sp() {
      return this.additive();
   }

   static void $init$(final AdditiveCommutativeGroup $this) {
   }
}
