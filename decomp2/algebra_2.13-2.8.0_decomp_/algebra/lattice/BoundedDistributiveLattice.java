package algebra.lattice;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRig;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055ca\u0002\u0006\f!\u0003\r\t\u0001\u0005\u0005\u0006\t\u0002!\t!\u0012\u0005\u0007\u0013\u0002!\t!\u0004&\t\u000bE\u0003A\u0011\t*\b\u000bQ[\u0001\u0012A+\u0007\u000b)Y\u0001\u0012\u0001,\t\u000b%,A\u0011\u00016\t\u000b-,AQ\u00017\t\u000f\u0005\u0005Q\u0001\"\u0001\u0002\u0004!I\u0011QH\u0003\u0002\u0002\u0013%\u0011q\b\u0002\u001b\u0005>,h\u000eZ3e\t&\u001cHO]5ckRLg/\u001a'biRL7-\u001a\u0006\u0003\u00195\tq\u0001\\1ui&\u001cWMC\u0001\u000f\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u0012=M!\u0001A\u0005\rB!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\r\te.\u001f\t\u00043iaR\"A\u0006\n\u0005mY!A\u0004\"pk:$W\r\u001a'biRL7-\u001a\t\u0003;ya\u0001\u0001B\u0005 \u0001\u0001\u0006\t\u0011!b\u0001A\t\t\u0011)\u0005\u0002\"%A\u00111CI\u0005\u0003GQ\u0011qAT8uQ&tw\r\u000b\u0004\u001fK!\u0012t\u0007\u0010\t\u0003'\u0019J!a\n\u000b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G%RCf\u000b\b\u0003')J!a\u000b\u000b\u0002\u0007%sG/\r\u0003%[E*bB\u0001\u00182\u001b\u0005y#B\u0001\u0019\u0010\u0003\u0019a$o\\8u}%\tQ#M\u0003$gQ2TG\u0004\u0002\u0014i%\u0011Q\u0007F\u0001\u0005\u0019>tw-\r\u0003%[E*\u0012'B\u00129smRdBA\n:\u0013\tQD#A\u0003GY>\fG/\r\u0003%[E*\u0012'B\u0012>}\u0001{dBA\n?\u0013\tyD#\u0001\u0004E_V\u0014G.Z\u0019\u0005I5\nT\u0003E\u0002\u001a\u0005rI!aQ\u0006\u0003'\u0011K7\u000f\u001e:jEV$\u0018N^3MCR$\u0018nY3\u0002\r\u0011Jg.\u001b;%)\u00051\u0005CA\nH\u0013\tAEC\u0001\u0003V]&$\u0018\u0001E1t\u0007>lW.\u001e;bi&4XMU5h+\u0005Y\u0005c\u0001'P95\tQJ\u0003\u0002O\u001b\u0005!!/\u001b8h\u0013\t\u0001VJ\u0001\bD_6lW\u000f^1uSZ,'+[4\u0002\t\u0011,\u0018\r\\\u000b\u0002'B\u0019\u0011\u0004\u0001\u000f\u00025\t{WO\u001c3fI\u0012K7\u000f\u001e:jEV$\u0018N^3MCR$\u0018nY3\u0011\u0005e)1#B\u0003X5z\u000b\u0007CA\nY\u0013\tIFC\u0001\u0004B]f\u0014VM\u001a\t\u00043mk\u0016B\u0001/\f\u0005}\u0011u.\u001e8eK\u0012lU-\u001a;TK6LG.\u0019;uS\u000e,g)\u001e8di&|gn\u001d\t\u00033\u0001\u00012!G0^\u0013\t\u00017BA\u0010C_VtG-\u001a3K_&t7+Z7jY\u0006$H/[2f\rVt7\r^5p]N\u0004\"AY4\u000e\u0003\rT!\u0001Z3\u0002\u0005%|'\"\u00014\u0002\t)\fg/Y\u0005\u0003Q\u000e\u0014AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A+\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u00055\u0004HC\u00018{!\rI\u0002a\u001c\t\u0003;A$\u0011bH\u0004!\u0002\u0003\u0005)\u0019\u0001\u0011)\rA,#\u000f\u001e<yc\u0015\u0019\u0013FK:,c\u0011!S&M\u000b2\u000b\r\u001aD'^\u001b2\t\u0011j\u0013'F\u0019\u0006GaJtOO\u0019\u0005I5\nT#M\u0003${yJx(\r\u0003%[E*\u0002\"B>\b\u0001\bq\u0017AA3wQ\t9Q\u0010\u0005\u0002\u0014}&\u0011q\u0010\u0006\u0002\u0007S:d\u0017N\\3\u0002\r5Lg.T1y+\u0011\t)!!\u0004\u0015\r\u0005\u001d\u0011QGA\u001d)\u0011\tI!!\t\u0011\te\u0001\u00111\u0002\t\u0004;\u00055A!C\u0010\tA\u0003\u0005\tQ1\u0001!Q-\ti!JA\t\u0003+\tI\"!\b2\r\rJ#&a\u0005,c\u0011!S&M\u000b2\r\r\u001aD'a\u00066c\u0011!S&M\u000b2\r\rB\u0014(a\u0007;c\u0011!S&M\u000b2\r\rjd(a\b@c\u0011!S&M\u000b\t\u000f\u0005\r\u0002\u0002q\u0001\u0002&\u0005\u0019qN\u001d3\u0011\r\u0005\u001d\u0012qFA\u0006\u001d\u0011\tI#a\u000b\u000e\u00035I1!!\f\u000e\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\r\u00024\t)qJ\u001d3fe*\u0019\u0011QF\u0007\t\u000f\u0005]\u0002\u00021\u0001\u0002\f\u0005\u0019Q.\u001b8\t\u000f\u0005m\u0002\u00021\u0001\u0002\f\u0005\u0019Q.\u0019=\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0005\u0003\u0003BA\"\u0003\u0013j!!!\u0012\u000b\u0007\u0005\u001dS-\u0001\u0003mC:<\u0017\u0002BA&\u0003\u000b\u0012aa\u00142kK\u000e$\b"
)
public interface BoundedDistributiveLattice extends BoundedLattice, DistributiveLattice {
   static BoundedDistributiveLattice minMax(final Object min, final Object max, final Order ord) {
      return BoundedDistributiveLattice$.MODULE$.minMax(min, max, ord);
   }

   static BoundedDistributiveLattice apply(final BoundedDistributiveLattice ev) {
      return BoundedDistributiveLattice$.MODULE$.apply(ev);
   }

   default CommutativeRig asCommutativeRig() {
      return new CommutativeRig() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice $outer;

         public CommutativeMonoid multiplicative() {
            return MultiplicativeCommutativeMonoid.multiplicative$(this);
         }

         public CommutativeMonoid multiplicative$mcD$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcD$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcF$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcF$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcI$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcI$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcJ$sp$(this);
         }

         public double one$mcD$sp() {
            return MultiplicativeMonoid.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne(final Object a, final Eq ev) {
            return MultiplicativeMonoid.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeMonoid.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
         }

         public Object product(final IterableOnce as) {
            return MultiplicativeMonoid.product$(this, as);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcJ$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid.additive$(this);
         }

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
         }

         public double zero$mcD$sp() {
            return AdditiveMonoid.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return AdditiveMonoid.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public Object sumN(final Object a, final int n) {
            return AdditiveMonoid.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
         }

         public Object sum(final IterableOnce as) {
            return AdditiveMonoid.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public Object positiveSumN(final Object a, final int n) {
            return AdditiveSemigroup.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public Object zero() {
            return this.$outer.zero();
         }

         public Object one() {
            return this.$outer.one();
         }

         public Object plus(final Object x, final Object y) {
            return this.$outer.join(x, y);
         }

         public Object times(final Object x, final Object y) {
            return this.$outer.meet(x, y);
         }

         public {
            if (BoundedDistributiveLattice.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice.this;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
               MultiplicativeCommutativeSemigroup.$init$(this);
               MultiplicativeCommutativeMonoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static BoundedDistributiveLattice dual$(final BoundedDistributiveLattice $this) {
      return $this.dual();
   }

   default BoundedDistributiveLattice dual() {
      return new BoundedDistributiveLattice() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice $outer;

         public CommutativeRig asCommutativeRig() {
            return BoundedDistributiveLattice.super.asCommutativeRig();
         }

         public CommutativeRig asCommutativeRig$mcD$sp() {
            return BoundedDistributiveLattice.super.asCommutativeRig$mcD$sp();
         }

         public CommutativeRig asCommutativeRig$mcF$sp() {
            return BoundedDistributiveLattice.super.asCommutativeRig$mcF$sp();
         }

         public CommutativeRig asCommutativeRig$mcI$sp() {
            return BoundedDistributiveLattice.super.asCommutativeRig$mcI$sp();
         }

         public CommutativeRig asCommutativeRig$mcJ$sp() {
            return BoundedDistributiveLattice.super.asCommutativeRig$mcJ$sp();
         }

         public BoundedDistributiveLattice dual$mcD$sp() {
            return BoundedDistributiveLattice.super.dual$mcD$sp();
         }

         public BoundedDistributiveLattice dual$mcF$sp() {
            return BoundedDistributiveLattice.super.dual$mcF$sp();
         }

         public BoundedDistributiveLattice dual$mcI$sp() {
            return BoundedDistributiveLattice.super.dual$mcI$sp();
         }

         public BoundedDistributiveLattice dual$mcJ$sp() {
            return BoundedDistributiveLattice.super.dual$mcJ$sp();
         }

         public double zero$mcD$sp() {
            return BoundedJoinSemilattice.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return BoundedJoinSemilattice.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return BoundedJoinSemilattice.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return BoundedJoinSemilattice.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcD$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcF$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcI$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcI$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcJ$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcJ$sp$(this);
         }

         public double one$mcD$sp() {
            return BoundedMeetSemilattice.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return BoundedMeetSemilattice.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return BoundedMeetSemilattice.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return BoundedMeetSemilattice.one$mcJ$sp$(this);
         }

         public boolean isOne(final Object a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcJ$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcJ$sp$(this);
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
         }

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return MeetSemilattice.meet$mcI$sp$(this, lhs, rhs);
         }

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return MeetSemilattice.meet$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
         }

         public int join$mcI$sp(final int lhs, final int rhs) {
            return JoinSemilattice.join$mcI$sp$(this, lhs, rhs);
         }

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return JoinSemilattice.join$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public Object meet(final Object a, final Object b) {
            return this.$outer.join(a, b);
         }

         public Object join(final Object a, final Object b) {
            return this.$outer.meet(a, b);
         }

         public Object one() {
            return this.$outer.zero();
         }

         public Object zero() {
            return this.$outer.one();
         }

         public BoundedDistributiveLattice dual() {
            return this.$outer;
         }

         public {
            if (BoundedDistributiveLattice.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
               BoundedMeetSemilattice.$init$(this);
               BoundedJoinSemilattice.$init$(this);
               BoundedLattice.$init$(this);
               BoundedDistributiveLattice.$init$(this);
            }
         }
      };
   }

   default CommutativeRig asCommutativeRig$mcD$sp() {
      return this.asCommutativeRig();
   }

   default CommutativeRig asCommutativeRig$mcF$sp() {
      return this.asCommutativeRig();
   }

   default CommutativeRig asCommutativeRig$mcI$sp() {
      return this.asCommutativeRig();
   }

   default CommutativeRig asCommutativeRig$mcJ$sp() {
      return this.asCommutativeRig();
   }

   default BoundedDistributiveLattice dual$mcD$sp() {
      return this.dual();
   }

   default BoundedDistributiveLattice dual$mcF$sp() {
      return this.dual();
   }

   default BoundedDistributiveLattice dual$mcI$sp() {
      return this.dual();
   }

   default BoundedDistributiveLattice dual$mcJ$sp() {
      return this.dual();
   }

   static void $init$(final BoundedDistributiveLattice $this) {
   }
}
