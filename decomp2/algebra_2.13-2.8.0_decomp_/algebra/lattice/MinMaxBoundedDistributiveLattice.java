package algebra.lattice;

import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2Aa\u0002\u0005\u0001\u001b!AA\u0005\u0001B\u0001B\u0003%1\u0003\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003\u0014\u0011!1\u0003A!A!\u0002\u00179\u0003\"B\u0018\u0001\t\u0003\u0001\u0004\"\u0002\u001c\u0001\t\u00039\u0004\"\u0002\u001d\u0001\t\u00039$\u0001I'j]6\u000b\u0007PQ8v]\u0012,G\rR5tiJL'-\u001e;jm\u0016d\u0015\r\u001e;jG\u0016T!!\u0003\u0006\u0002\u000f1\fG\u000f^5dK*\t1\"A\u0004bY\u001e,'M]1\u0004\u0001U\u0011a\"F\n\u0004\u0001=\t\u0003c\u0001\t\u0012'5\t\u0001\"\u0003\u0002\u0013\u0011\tiQ*\u001b8NCbd\u0015\r\u001e;jG\u0016\u0004\"\u0001F\u000b\r\u0001\u0011)a\u0003\u0001b\u0001/\t\t\u0011)\u0005\u0002\u0019=A\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t9aj\u001c;iS:<\u0007CA\r \u0013\t\u0001#DA\u0002B]f\u00042\u0001\u0005\u0012\u0014\u0013\t\u0019\u0003B\u0001\u000eC_VtG-\u001a3ESN$(/\u001b2vi&4X\rT1ui&\u001cW-A\u0002nS:\f1!\\1y\u0003\u0005y\u0007c\u0001\u0015-'9\u0011\u0011FK\u0007\u0002\u0015%\u00111FC\u0001\ba\u0006\u001c7.Y4f\u0013\ticFA\u0003Pe\u0012,'O\u0003\u0002,\u0015\u00051A(\u001b8jiz\"2!\r\u001b6)\t\u00114\u0007E\u0002\u0011\u0001MAQA\n\u0003A\u0004\u001dBQ\u0001\n\u0003A\u0002MAQ!\n\u0003A\u0002M\tAA_3s_V\t1#A\u0002p]\u0016\u0004"
)
public class MinMaxBoundedDistributiveLattice extends MinMaxLattice implements BoundedDistributiveLattice {
   private final Object min;
   private final Object max;

   public CommutativeRig asCommutativeRig() {
      return BoundedDistributiveLattice.asCommutativeRig$(this);
   }

   public CommutativeRig asCommutativeRig$mcD$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcD$sp$(this);
   }

   public CommutativeRig asCommutativeRig$mcF$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcF$sp$(this);
   }

   public CommutativeRig asCommutativeRig$mcI$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcI$sp$(this);
   }

   public CommutativeRig asCommutativeRig$mcJ$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcJ$sp$(this);
   }

   public BoundedDistributiveLattice dual() {
      return BoundedDistributiveLattice.dual$(this);
   }

   public BoundedDistributiveLattice dual$mcD$sp() {
      return BoundedDistributiveLattice.dual$mcD$sp$(this);
   }

   public BoundedDistributiveLattice dual$mcF$sp() {
      return BoundedDistributiveLattice.dual$mcF$sp$(this);
   }

   public BoundedDistributiveLattice dual$mcI$sp() {
      return BoundedDistributiveLattice.dual$mcI$sp$(this);
   }

   public BoundedDistributiveLattice dual$mcJ$sp() {
      return BoundedDistributiveLattice.dual$mcJ$sp$(this);
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

   public Object zero() {
      return this.min;
   }

   public Object one() {
      return this.max;
   }

   public MinMaxBoundedDistributiveLattice(final Object min, final Object max, final Order o) {
      super(o);
      this.min = min;
      this.max = max;
      BoundedMeetSemilattice.$init$(this);
      BoundedJoinSemilattice.$init$(this);
      BoundedLattice.$init$(this);
      BoundedDistributiveLattice.$init$(this);
   }
}
