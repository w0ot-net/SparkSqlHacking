package algebra.lattice;

import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class DeMorgan$ implements DeMorganFunctions, Serializable {
   public static final DeMorgan$ MODULE$ = new DeMorgan$();

   static {
      MeetSemilatticeFunctions.$init$(MODULE$);
      BoundedMeetSemilatticeFunctions.$init$(MODULE$);
      JoinSemilatticeFunctions.$init$(MODULE$);
      BoundedJoinSemilatticeFunctions.$init$(MODULE$);
      LogicFunctions.$init$(MODULE$);
   }

   public Object complement(final Object x, final Logic ev) {
      return LogicFunctions.complement$(this, x, ev);
   }

   public int complement$mIc$sp(final int x, final Logic ev) {
      return LogicFunctions.complement$mIc$sp$(this, x, ev);
   }

   public long complement$mJc$sp(final long x, final Logic ev) {
      return LogicFunctions.complement$mJc$sp$(this, x, ev);
   }

   public Object nor(final Object x, final Object y, final Logic ev) {
      return LogicFunctions.nor$(this, x, y, ev);
   }

   public int nor$mIc$sp(final int x, final int y, final Logic ev) {
      return LogicFunctions.nor$mIc$sp$(this, x, y, ev);
   }

   public long nor$mJc$sp(final long x, final long y, final Logic ev) {
      return LogicFunctions.nor$mJc$sp$(this, x, y, ev);
   }

   public Object nxor(final Object x, final Object y, final Logic ev) {
      return LogicFunctions.nxor$(this, x, y, ev);
   }

   public int nxor$mIc$sp(final int x, final int y, final Logic ev) {
      return LogicFunctions.nxor$mIc$sp$(this, x, y, ev);
   }

   public long nxor$mJc$sp(final long x, final long y, final Logic ev) {
      return LogicFunctions.nxor$mJc$sp$(this, x, y, ev);
   }

   public Object nand(final Object x, final Object y, final Logic ev) {
      return LogicFunctions.nand$(this, x, y, ev);
   }

   public int nand$mIc$sp(final int x, final int y, final Logic ev) {
      return LogicFunctions.nand$mIc$sp$(this, x, y, ev);
   }

   public long nand$mJc$sp(final long x, final long y, final Logic ev) {
      return LogicFunctions.nand$mJc$sp$(this, x, y, ev);
   }

   public Object zero(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$(this, ev);
   }

   public double zero$mDc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mDc$sp$(this, ev);
   }

   public float zero$mFc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mFc$sp$(this, ev);
   }

   public int zero$mIc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mIc$sp$(this, ev);
   }

   public long zero$mJc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mJc$sp$(this, ev);
   }

   public Object join(final Object x, final Object y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$(this, x, y, ev);
   }

   public double join$mDc$sp(final double x, final double y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mDc$sp$(this, x, y, ev);
   }

   public float join$mFc$sp(final float x, final float y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mFc$sp$(this, x, y, ev);
   }

   public int join$mIc$sp(final int x, final int y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mIc$sp$(this, x, y, ev);
   }

   public long join$mJc$sp(final long x, final long y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mJc$sp$(this, x, y, ev);
   }

   public Object one(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$(this, ev);
   }

   public double one$mDc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mDc$sp$(this, ev);
   }

   public float one$mFc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mFc$sp$(this, ev);
   }

   public int one$mIc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mIc$sp$(this, ev);
   }

   public long one$mJc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mJc$sp$(this, ev);
   }

   public Object meet(final Object x, final Object y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$(this, x, y, ev);
   }

   public double meet$mDc$sp(final double x, final double y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mDc$sp$(this, x, y, ev);
   }

   public float meet$mFc$sp(final float x, final float y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mFc$sp$(this, x, y, ev);
   }

   public int meet$mIc$sp(final int x, final int y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mIc$sp$(this, x, y, ev);
   }

   public long meet$mJc$sp(final long x, final long y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mJc$sp$(this, x, y, ev);
   }

   public final DeMorgan apply(final DeMorgan ev) {
      return ev;
   }

   public final DeMorgan fromBool(final Bool bool) {
      return new DeMorgan(bool) {
         private final Bool bool$1;

         public Object meet(final Object a, final Object b) {
            return DeMorgan.meet$(this, a, b);
         }

         public int meet$mcI$sp(final int a, final int b) {
            return DeMorgan.meet$mcI$sp$(this, a, b);
         }

         public long meet$mcJ$sp(final long a, final long b) {
            return DeMorgan.meet$mcJ$sp$(this, a, b);
         }

         public Object join(final Object a, final Object b) {
            return DeMorgan.join$(this, a, b);
         }

         public int join$mcI$sp(final int a, final int b) {
            return DeMorgan.join$mcI$sp$(this, a, b);
         }

         public long join$mcJ$sp(final long a, final long b) {
            return DeMorgan.join$mcJ$sp$(this, a, b);
         }

         public Object imp(final Object a, final Object b) {
            return DeMorgan.imp$(this, a, b);
         }

         public int imp$mcI$sp(final int a, final int b) {
            return DeMorgan.imp$mcI$sp$(this, a, b);
         }

         public long imp$mcJ$sp(final long a, final long b) {
            return DeMorgan.imp$mcJ$sp$(this, a, b);
         }

         public int and$mcI$sp(final int a, final int b) {
            return Logic.and$mcI$sp$(this, a, b);
         }

         public long and$mcJ$sp(final long a, final long b) {
            return Logic.and$mcJ$sp$(this, a, b);
         }

         public int or$mcI$sp(final int a, final int b) {
            return Logic.or$mcI$sp$(this, a, b);
         }

         public long or$mcJ$sp(final long a, final long b) {
            return Logic.or$mcJ$sp$(this, a, b);
         }

         public int not$mcI$sp(final int a) {
            return Logic.not$mcI$sp$(this, a);
         }

         public long not$mcJ$sp(final long a) {
            return Logic.not$mcJ$sp$(this, a);
         }

         public Object xor(final Object a, final Object b) {
            return Logic.xor$(this, a, b);
         }

         public int xor$mcI$sp(final int a, final int b) {
            return Logic.xor$mcI$sp$(this, a, b);
         }

         public long xor$mcJ$sp(final long a, final long b) {
            return Logic.xor$mcJ$sp$(this, a, b);
         }

         public Object nand(final Object a, final Object b) {
            return Logic.nand$(this, a, b);
         }

         public int nand$mcI$sp(final int a, final int b) {
            return Logic.nand$mcI$sp$(this, a, b);
         }

         public long nand$mcJ$sp(final long a, final long b) {
            return Logic.nand$mcJ$sp$(this, a, b);
         }

         public Object nor(final Object a, final Object b) {
            return Logic.nor$(this, a, b);
         }

         public int nor$mcI$sp(final int a, final int b) {
            return Logic.nor$mcI$sp$(this, a, b);
         }

         public long nor$mcJ$sp(final long a, final long b) {
            return Logic.nor$mcJ$sp$(this, a, b);
         }

         public Object nxor(final Object a, final Object b) {
            return Logic.nxor$(this, a, b);
         }

         public int nxor$mcI$sp(final int a, final int b) {
            return Logic.nxor$mcI$sp$(this, a, b);
         }

         public long nxor$mcJ$sp(final long a, final long b) {
            return Logic.nxor$mcJ$sp$(this, a, b);
         }

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

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
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

         public Object and(final Object a, final Object b) {
            return this.bool$1.and(a, b);
         }

         public Object or(final Object a, final Object b) {
            return this.bool$1.or(a, b);
         }

         public Object not(final Object a) {
            return this.bool$1.complement(a);
         }

         public Object one() {
            return this.bool$1.one();
         }

         public Object zero() {
            return this.bool$1.zero();
         }

         public {
            this.bool$1 = bool$1;
            JoinSemilattice.$init$(this);
            MeetSemilattice.$init$(this);
            Lattice.$init$(this);
            BoundedMeetSemilattice.$init$(this);
            BoundedJoinSemilattice.$init$(this);
            BoundedLattice.$init$(this);
            BoundedDistributiveLattice.$init$(this);
            Logic.$init$(this);
            DeMorgan.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DeMorgan$.class);
   }

   public final DeMorgan apply$mIc$sp(final DeMorgan ev) {
      return ev;
   }

   public final DeMorgan apply$mJc$sp(final DeMorgan ev) {
      return ev;
   }

   public final DeMorgan fromBool$mIc$sp(final Bool bool) {
      return new DeMorgan$mcI$sp(bool) {
         private final Bool bool$2;

         public int meet(final int a, final int b) {
            return DeMorgan$mcI$sp.meet$(this, a, b);
         }

         public int meet$mcI$sp(final int a, final int b) {
            return DeMorgan$mcI$sp.meet$mcI$sp$(this, a, b);
         }

         public int join(final int a, final int b) {
            return DeMorgan$mcI$sp.join$(this, a, b);
         }

         public int join$mcI$sp(final int a, final int b) {
            return DeMorgan$mcI$sp.join$mcI$sp$(this, a, b);
         }

         public int imp(final int a, final int b) {
            return DeMorgan$mcI$sp.imp$(this, a, b);
         }

         public int imp$mcI$sp(final int a, final int b) {
            return DeMorgan$mcI$sp.imp$mcI$sp$(this, a, b);
         }

         public int xor(final int a, final int b) {
            return Logic$mcI$sp.xor$(this, a, b);
         }

         public int xor$mcI$sp(final int a, final int b) {
            return Logic$mcI$sp.xor$mcI$sp$(this, a, b);
         }

         public int nand(final int a, final int b) {
            return Logic$mcI$sp.nand$(this, a, b);
         }

         public int nand$mcI$sp(final int a, final int b) {
            return Logic$mcI$sp.nand$mcI$sp$(this, a, b);
         }

         public int nor(final int a, final int b) {
            return Logic$mcI$sp.nor$(this, a, b);
         }

         public int nor$mcI$sp(final int a, final int b) {
            return Logic$mcI$sp.nor$mcI$sp$(this, a, b);
         }

         public int nxor(final int a, final int b) {
            return Logic$mcI$sp.nxor$(this, a, b);
         }

         public int nxor$mcI$sp(final int a, final int b) {
            return Logic$mcI$sp.nxor$mcI$sp$(this, a, b);
         }

         public CommutativeRig asCommutativeRig() {
            return BoundedDistributiveLattice$mcI$sp.asCommutativeRig$(this);
         }

         public CommutativeRig asCommutativeRig$mcI$sp() {
            return BoundedDistributiveLattice$mcI$sp.asCommutativeRig$mcI$sp$(this);
         }

         public BoundedDistributiveLattice dual() {
            return BoundedDistributiveLattice$mcI$sp.dual$(this);
         }

         public BoundedDistributiveLattice dual$mcI$sp() {
            return BoundedDistributiveLattice$mcI$sp.dual$mcI$sp$(this);
         }

         public boolean isOne(final int a, final Eq ev) {
            return BoundedMeetSemilattice$mcI$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return BoundedMeetSemilattice$mcI$sp.isOne$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice$mcI$sp.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice$mcI$sp.meetSemilattice$mcI$sp$(this);
         }

         public boolean isZero(final int a, final Eq ev) {
            return BoundedJoinSemilattice$mcI$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice$mcI$sp.isZero$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice$mcI$sp.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcI$sp() {
            return BoundedJoinSemilattice$mcI$sp.joinSemilattice$mcI$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$mcI$sp$(this, ev);
         }

         public long meet$mcJ$sp(final long a, final long b) {
            return DeMorgan.meet$mcJ$sp$(this, a, b);
         }

         public long join$mcJ$sp(final long a, final long b) {
            return DeMorgan.join$mcJ$sp$(this, a, b);
         }

         public long imp$mcJ$sp(final long a, final long b) {
            return DeMorgan.imp$mcJ$sp$(this, a, b);
         }

         public long and$mcJ$sp(final long a, final long b) {
            return Logic.and$mcJ$sp$(this, a, b);
         }

         public long or$mcJ$sp(final long a, final long b) {
            return Logic.or$mcJ$sp$(this, a, b);
         }

         public long not$mcJ$sp(final long a) {
            return Logic.not$mcJ$sp$(this, a);
         }

         public long xor$mcJ$sp(final long a, final long b) {
            return Logic.xor$mcJ$sp$(this, a, b);
         }

         public long nand$mcJ$sp(final long a, final long b) {
            return Logic.nand$mcJ$sp$(this, a, b);
         }

         public long nor$mcJ$sp(final long a, final long b) {
            return Logic.nor$mcJ$sp$(this, a, b);
         }

         public long nxor$mcJ$sp(final long a, final long b) {
            return Logic.nxor$mcJ$sp$(this, a, b);
         }

         public CommutativeRig asCommutativeRig$mcD$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcD$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcF$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcF$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcJ$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcJ$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcD$sp() {
            return BoundedDistributiveLattice.dual$mcD$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcF$sp() {
            return BoundedDistributiveLattice.dual$mcF$sp$(this);
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

         public long zero$mcJ$sp() {
            return BoundedJoinSemilattice.zero$mcJ$sp$(this);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice$mcD$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcF$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
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

         public long one$mcJ$sp() {
            return BoundedMeetSemilattice.one$mcJ$sp$(this);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
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

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
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

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public int and(final int a, final int b) {
            return this.and$mcI$sp(a, b);
         }

         public int or(final int a, final int b) {
            return this.or$mcI$sp(a, b);
         }

         public int not(final int a) {
            return this.not$mcI$sp(a);
         }

         public int one() {
            return this.one$mcI$sp();
         }

         public int zero() {
            return this.zero$mcI$sp();
         }

         public int and$mcI$sp(final int a, final int b) {
            return this.bool$2.and$mcI$sp(a, b);
         }

         public int or$mcI$sp(final int a, final int b) {
            return this.bool$2.or$mcI$sp(a, b);
         }

         public int not$mcI$sp(final int a) {
            return this.bool$2.complement$mcI$sp(a);
         }

         public int one$mcI$sp() {
            return this.bool$2.one$mcI$sp();
         }

         public int zero$mcI$sp() {
            return this.bool$2.zero$mcI$sp();
         }

         public {
            this.bool$2 = bool$2;
            JoinSemilattice.$init$(this);
            MeetSemilattice.$init$(this);
            Lattice.$init$(this);
            BoundedMeetSemilattice.$init$(this);
            BoundedJoinSemilattice.$init$(this);
            BoundedLattice.$init$(this);
            BoundedDistributiveLattice.$init$(this);
            Logic.$init$(this);
            DeMorgan.$init$(this);
         }
      };
   }

   public final DeMorgan fromBool$mJc$sp(final Bool bool) {
      return new DeMorgan$mcJ$sp(bool) {
         private final Bool bool$3;

         public long meet(final long a, final long b) {
            return DeMorgan$mcJ$sp.meet$(this, a, b);
         }

         public long meet$mcJ$sp(final long a, final long b) {
            return DeMorgan$mcJ$sp.meet$mcJ$sp$(this, a, b);
         }

         public long join(final long a, final long b) {
            return DeMorgan$mcJ$sp.join$(this, a, b);
         }

         public long join$mcJ$sp(final long a, final long b) {
            return DeMorgan$mcJ$sp.join$mcJ$sp$(this, a, b);
         }

         public long imp(final long a, final long b) {
            return DeMorgan$mcJ$sp.imp$(this, a, b);
         }

         public long imp$mcJ$sp(final long a, final long b) {
            return DeMorgan$mcJ$sp.imp$mcJ$sp$(this, a, b);
         }

         public long xor(final long a, final long b) {
            return Logic$mcJ$sp.xor$(this, a, b);
         }

         public long xor$mcJ$sp(final long a, final long b) {
            return Logic$mcJ$sp.xor$mcJ$sp$(this, a, b);
         }

         public long nand(final long a, final long b) {
            return Logic$mcJ$sp.nand$(this, a, b);
         }

         public long nand$mcJ$sp(final long a, final long b) {
            return Logic$mcJ$sp.nand$mcJ$sp$(this, a, b);
         }

         public long nor(final long a, final long b) {
            return Logic$mcJ$sp.nor$(this, a, b);
         }

         public long nor$mcJ$sp(final long a, final long b) {
            return Logic$mcJ$sp.nor$mcJ$sp$(this, a, b);
         }

         public long nxor(final long a, final long b) {
            return Logic$mcJ$sp.nxor$(this, a, b);
         }

         public long nxor$mcJ$sp(final long a, final long b) {
            return Logic$mcJ$sp.nxor$mcJ$sp$(this, a, b);
         }

         public CommutativeRig asCommutativeRig() {
            return BoundedDistributiveLattice$mcJ$sp.asCommutativeRig$(this);
         }

         public CommutativeRig asCommutativeRig$mcJ$sp() {
            return BoundedDistributiveLattice$mcJ$sp.asCommutativeRig$mcJ$sp$(this);
         }

         public BoundedDistributiveLattice dual() {
            return BoundedDistributiveLattice$mcJ$sp.dual$(this);
         }

         public BoundedDistributiveLattice dual$mcJ$sp() {
            return BoundedDistributiveLattice$mcJ$sp.dual$mcJ$sp$(this);
         }

         public boolean isOne(final long a, final Eq ev) {
            return BoundedMeetSemilattice$mcJ$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice$mcJ$sp.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice$mcJ$sp.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcJ$sp() {
            return BoundedMeetSemilattice$mcJ$sp.meetSemilattice$mcJ$sp$(this);
         }

         public boolean isZero(final long a, final Eq ev) {
            return BoundedJoinSemilattice$mcJ$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice$mcJ$sp.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice$mcJ$sp.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcJ$sp() {
            return BoundedJoinSemilattice$mcJ$sp.joinSemilattice$mcJ$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcJ$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice$mcJ$sp.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public int meet$mcI$sp(final int a, final int b) {
            return DeMorgan.meet$mcI$sp$(this, a, b);
         }

         public int join$mcI$sp(final int a, final int b) {
            return DeMorgan.join$mcI$sp$(this, a, b);
         }

         public int imp$mcI$sp(final int a, final int b) {
            return DeMorgan.imp$mcI$sp$(this, a, b);
         }

         public int and$mcI$sp(final int a, final int b) {
            return Logic.and$mcI$sp$(this, a, b);
         }

         public int or$mcI$sp(final int a, final int b) {
            return Logic.or$mcI$sp$(this, a, b);
         }

         public int not$mcI$sp(final int a) {
            return Logic.not$mcI$sp$(this, a);
         }

         public int xor$mcI$sp(final int a, final int b) {
            return Logic.xor$mcI$sp$(this, a, b);
         }

         public int nand$mcI$sp(final int a, final int b) {
            return Logic.nand$mcI$sp$(this, a, b);
         }

         public int nor$mcI$sp(final int a, final int b) {
            return Logic.nor$mcI$sp$(this, a, b);
         }

         public int nxor$mcI$sp(final int a, final int b) {
            return Logic.nxor$mcI$sp$(this, a, b);
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

         public BoundedDistributiveLattice dual$mcD$sp() {
            return BoundedDistributiveLattice.dual$mcD$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcF$sp() {
            return BoundedDistributiveLattice.dual$mcF$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcI$sp() {
            return BoundedDistributiveLattice.dual$mcI$sp$(this);
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

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcI$sp$(this, a, ev);
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

         public double one$mcD$sp() {
            return BoundedMeetSemilattice.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return BoundedMeetSemilattice.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return BoundedMeetSemilattice.one$mcI$sp$(this);
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

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
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

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
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

         public long and(final long a, final long b) {
            return this.and$mcJ$sp(a, b);
         }

         public long or(final long a, final long b) {
            return this.or$mcJ$sp(a, b);
         }

         public long not(final long a) {
            return this.not$mcJ$sp(a);
         }

         public long one() {
            return this.one$mcJ$sp();
         }

         public long zero() {
            return this.zero$mcJ$sp();
         }

         public long and$mcJ$sp(final long a, final long b) {
            return this.bool$3.and$mcJ$sp(a, b);
         }

         public long or$mcJ$sp(final long a, final long b) {
            return this.bool$3.or$mcJ$sp(a, b);
         }

         public long not$mcJ$sp(final long a) {
            return this.bool$3.complement$mcJ$sp(a);
         }

         public long one$mcJ$sp() {
            return this.bool$3.one$mcJ$sp();
         }

         public long zero$mcJ$sp() {
            return this.bool$3.zero$mcJ$sp();
         }

         public {
            this.bool$3 = bool$3;
            JoinSemilattice.$init$(this);
            MeetSemilattice.$init$(this);
            Lattice.$init$(this);
            BoundedMeetSemilattice.$init$(this);
            BoundedJoinSemilattice.$init$(this);
            BoundedLattice.$init$(this);
            BoundedDistributiveLattice.$init$(this);
            Logic.$init$(this);
            DeMorgan.$init$(this);
         }
      };
   }

   private DeMorgan$() {
   }
}
