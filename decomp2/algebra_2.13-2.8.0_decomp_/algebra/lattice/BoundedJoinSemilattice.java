package algebra.lattice;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015ba\u0002\u0006\f!\u0003\r\t\u0001\u0005\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u00021\ta\u0012\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u00065\u0002!\teW\u0004\u0006?.A\t\u0001\u0019\u0004\u0006\u0015-A\t!\u0019\u0005\u0006i\u001a!\t!\u001e\u0005\u0006m\u001a!)a\u001e\u0005\n\u0003+1\u0011\u0011!C\u0005\u0003/\u0011aCQ8v]\u0012,GMS8j]N+W.\u001b7biRL7-\u001a\u0006\u0003\u00195\tq\u0001\\1ui&\u001cWMC\u0001\u000f\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u0012=M\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\u0007\u0005s\u0017\u0010E\u0002\u001a5qi\u0011aC\u0005\u00037-\u0011qBS8j]N+W.\u001b7biRL7-\u001a\t\u0003;ya\u0001\u0001B\u0005 \u0001\u0001\u0006\t\u0011!b\u0001A\t\t\u0011)\u0005\u0002\"%A\u00111CI\u0005\u0003GQ\u0011qAT8uQ&tw\r\u000b\u0004\u001fK!\u0012t\u0007\u0010\t\u0003'\u0019J!a\n\u000b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G%RCf\u000b\b\u0003')J!a\u000b\u000b\u0002\u0007%sG/\r\u0003%[E*bB\u0001\u00182\u001b\u0005y#B\u0001\u0019\u0010\u0003\u0019a$o\\8u}%\tQ#M\u0003$gQ2TG\u0004\u0002\u0014i%\u0011Q\u0007F\u0001\u0005\u0019>tw-\r\u0003%[E*\u0012'B\u00129smRdBA\n:\u0013\tQD#A\u0003GY>\fG/\r\u0003%[E*\u0012'B\u0012>}\u0001{dBA\n?\u0013\tyD#\u0001\u0004E_V\u0014G.Z\u0019\u0005I5\nT#\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0007B\u00111\u0003R\u0005\u0003\u000bR\u0011A!\u00168ji\u0006!!0\u001a:p+\u0005a\u0012AB5t5\u0016\u0014x\u000e\u0006\u0002K1R\u00111J\u0014\t\u0003'1K!!\u0014\u000b\u0003\u000f\t{w\u000e\\3b]\")qj\u0001a\u0002!\u0006\u0011QM\u001e\t\u0004#VcbB\u0001*T\u001b\u0005i\u0011B\u0001+\u000e\u0003\u001d\u0001\u0018mY6bO\u0016L!AV,\u0003\u0005\u0015\u000b(B\u0001+\u000e\u0011\u0015I6\u00011\u0001\u001d\u0003\u0005\t\u0017a\u00046pS:\u001cV-\\5mCR$\u0018nY3\u0016\u0003q\u00032!U/\u001d\u0013\tqvK\u0001\nC_VtG-\u001a3TK6LG.\u0019;uS\u000e,\u0017A\u0006\"pk:$W\r\u001a&pS:\u001cV-\\5mCR$\u0018nY3\u0011\u0005e11#\u0002\u0004cK&d\u0007CA\nd\u0013\t!GC\u0001\u0004B]f\u0014VM\u001a\t\u00043\u0019D\u0017BA4\f\u0005aQu.\u001b8TK6LG.\u0019;uS\u000e,g)\u001e8di&|gn\u001d\t\u00033\u0001\u00012!\u00076i\u0013\tY7BA\u0010C_VtG-\u001a3K_&t7+Z7jY\u0006$H/[2f\rVt7\r^5p]N\u0004\"!\u001c:\u000e\u00039T!a\u001c9\u0002\u0005%|'\"A9\u0002\t)\fg/Y\u0005\u0003g:\u0014AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u00011\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005a\\HcA=\u0002\fA\u0019\u0011\u0004\u0001>\u0011\u0005uYH!C\u0010\tA\u0003\u0005\tQ1\u0001!Q!YX%`@\u0002\u0004\u0005\u001d\u0011'B\u0012*Uy\\\u0013\u0007\u0002\u0013.cU\tdaI\u001a5\u0003\u0003)\u0014\u0007\u0002\u0013.cU\tda\t\u001d:\u0003\u000bQ\u0014\u0007\u0002\u0013.cU\tdaI\u001f?\u0003\u0013y\u0014\u0007\u0002\u0013.cUAQa\u0014\u0005A\u0004eD3\u0001CA\b!\r\u0019\u0012\u0011C\u0005\u0004\u0003'!\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001aA!\u00111DA\u0011\u001b\t\tiBC\u0002\u0002 A\fA\u0001\\1oO&!\u00111EA\u000f\u0005\u0019y%M[3di\u0002"
)
public interface BoundedJoinSemilattice extends JoinSemilattice {
   static BoundedJoinSemilattice apply(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilattice$.MODULE$.apply(ev);
   }

   Object zero();

   // $FF: synthetic method
   static boolean isZero$(final BoundedJoinSemilattice $this, final Object a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final Object a, final Eq ev) {
      return ev.eqv(a, this.zero());
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$(final BoundedJoinSemilattice $this) {
      return $this.joinSemilattice();
   }

   default BoundedSemilattice joinSemilattice() {
      return new BoundedSemilattice() {
         // $FF: synthetic field
         private final BoundedJoinSemilattice $outer;

         public Object combineN(final Object a, final int n) {
            return BoundedSemilattice.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return BoundedSemilattice.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return BoundedSemilattice.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return BoundedSemilattice.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return BoundedSemilattice.combineN$mcJ$sp$(this, a, n);
         }

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

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
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

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object empty() {
            return this.$outer.zero();
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.join(x, y);
         }

         public {
            if (BoundedJoinSemilattice.this == null) {
               throw null;
            } else {
               this.$outer = BoundedJoinSemilattice.this;
               Semigroup.$init$(this);
               Band.$init$(this);
               CommutativeSemigroup.$init$(this);
               Semilattice.$init$(this);
               Monoid.$init$(this);
               CommutativeMonoid.$init$(this);
               BoundedSemilattice.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static double zero$mcD$sp$(final BoundedJoinSemilattice $this) {
      return $this.zero$mcD$sp();
   }

   default double zero$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.zero());
   }

   // $FF: synthetic method
   static float zero$mcF$sp$(final BoundedJoinSemilattice $this) {
      return $this.zero$mcF$sp();
   }

   default float zero$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.zero());
   }

   // $FF: synthetic method
   static int zero$mcI$sp$(final BoundedJoinSemilattice $this) {
      return $this.zero$mcI$sp();
   }

   default int zero$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.zero());
   }

   // $FF: synthetic method
   static long zero$mcJ$sp$(final BoundedJoinSemilattice $this) {
      return $this.zero$mcJ$sp();
   }

   default long zero$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.zero());
   }

   // $FF: synthetic method
   static boolean isZero$mcD$sp$(final BoundedJoinSemilattice $this, final double a, final Eq ev) {
      return $this.isZero$mcD$sp(a, ev);
   }

   default boolean isZero$mcD$sp(final double a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToDouble(a), ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcF$sp$(final BoundedJoinSemilattice $this, final float a, final Eq ev) {
      return $this.isZero$mcF$sp(a, ev);
   }

   default boolean isZero$mcF$sp(final float a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToFloat(a), ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcI$sp$(final BoundedJoinSemilattice $this, final int a, final Eq ev) {
      return $this.isZero$mcI$sp(a, ev);
   }

   default boolean isZero$mcI$sp(final int a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToInteger(a), ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcJ$sp$(final BoundedJoinSemilattice $this, final long a, final Eq ev) {
      return $this.isZero$mcJ$sp(a, ev);
   }

   default boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToLong(a), ev);
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$mcD$sp$(final BoundedJoinSemilattice $this) {
      return $this.joinSemilattice$mcD$sp();
   }

   default BoundedSemilattice joinSemilattice$mcD$sp() {
      return this.joinSemilattice();
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$mcF$sp$(final BoundedJoinSemilattice $this) {
      return $this.joinSemilattice$mcF$sp();
   }

   default BoundedSemilattice joinSemilattice$mcF$sp() {
      return this.joinSemilattice();
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$mcI$sp$(final BoundedJoinSemilattice $this) {
      return $this.joinSemilattice$mcI$sp();
   }

   default BoundedSemilattice joinSemilattice$mcI$sp() {
      return this.joinSemilattice();
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$mcJ$sp$(final BoundedJoinSemilattice $this) {
      return $this.joinSemilattice$mcJ$sp();
   }

   default BoundedSemilattice joinSemilattice$mcJ$sp() {
      return this.joinSemilattice();
   }

   static void $init$(final BoundedJoinSemilattice $this) {
   }
}
