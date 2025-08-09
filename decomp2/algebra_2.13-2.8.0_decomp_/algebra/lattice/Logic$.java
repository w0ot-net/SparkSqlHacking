package algebra.lattice;

import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Logic$ implements LogicFunctions, Serializable {
   public static final Logic$ MODULE$ = new Logic$();

   static {
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

   public final Logic apply(final Logic ev) {
      return ev;
   }

   public final Logic fromHeyting(final Heyting h) {
      return new Logic(h) {
         private final Heyting h$1;

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

         public Object and(final Object a, final Object b) {
            return this.h$1.and(a, b);
         }

         public Object or(final Object a, final Object b) {
            return this.h$1.or(a, b);
         }

         public Object not(final Object a) {
            return this.h$1.complement(a);
         }

         public Object zero() {
            return this.h$1.zero();
         }

         public Object one() {
            return this.h$1.one();
         }

         public Object meet(final Object lhs, final Object rhs) {
            return this.h$1.meet(lhs, rhs);
         }

         public Object join(final Object lhs, final Object rhs) {
            return this.h$1.join(lhs, rhs);
         }

         public {
            this.h$1 = h$1;
            JoinSemilattice.$init$(this);
            MeetSemilattice.$init$(this);
            Lattice.$init$(this);
            BoundedMeetSemilattice.$init$(this);
            BoundedJoinSemilattice.$init$(this);
            BoundedLattice.$init$(this);
            BoundedDistributiveLattice.$init$(this);
            Logic.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Logic$.class);
   }

   public final Logic apply$mIc$sp(final Logic ev) {
      return ev;
   }

   public final Logic apply$mJc$sp(final Logic ev) {
      return ev;
   }

   public final Logic fromHeyting$mIc$sp(final Heyting h) {
      return new Logic$mcI$sp(h) {
         private final Heyting h$2;

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

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return MeetSemilattice.meet$mcJ$sp$(this, lhs, rhs);
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

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return JoinSemilattice.join$mcJ$sp$(this, lhs, rhs);
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

         public int zero() {
            return this.zero$mcI$sp();
         }

         public int one() {
            return this.one$mcI$sp();
         }

         public int meet(final int lhs, final int rhs) {
            return this.meet$mcI$sp(lhs, rhs);
         }

         public int join(final int lhs, final int rhs) {
            return this.join$mcI$sp(lhs, rhs);
         }

         public int and$mcI$sp(final int a, final int b) {
            return this.h$2.and$mcI$sp(a, b);
         }

         public int or$mcI$sp(final int a, final int b) {
            return this.h$2.or$mcI$sp(a, b);
         }

         public int not$mcI$sp(final int a) {
            return this.h$2.complement$mcI$sp(a);
         }

         public int zero$mcI$sp() {
            return this.h$2.zero$mcI$sp();
         }

         public int one$mcI$sp() {
            return this.h$2.one$mcI$sp();
         }

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return this.h$2.meet$mcI$sp(lhs, rhs);
         }

         public int join$mcI$sp(final int lhs, final int rhs) {
            return this.h$2.join$mcI$sp(lhs, rhs);
         }

         public {
            this.h$2 = h$2;
            JoinSemilattice.$init$(this);
            MeetSemilattice.$init$(this);
            Lattice.$init$(this);
            BoundedMeetSemilattice.$init$(this);
            BoundedJoinSemilattice.$init$(this);
            BoundedLattice.$init$(this);
            BoundedDistributiveLattice.$init$(this);
            Logic.$init$(this);
         }
      };
   }

   public final Logic fromHeyting$mJc$sp(final Heyting h) {
      return new Logic$mcJ$sp(h) {
         private final Heyting h$3;

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

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return MeetSemilattice.meet$mcI$sp$(this, lhs, rhs);
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

         public int join$mcI$sp(final int lhs, final int rhs) {
            return JoinSemilattice.join$mcI$sp$(this, lhs, rhs);
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

         public long zero() {
            return this.zero$mcJ$sp();
         }

         public long one() {
            return this.one$mcJ$sp();
         }

         public long meet(final long lhs, final long rhs) {
            return this.meet$mcJ$sp(lhs, rhs);
         }

         public long join(final long lhs, final long rhs) {
            return this.join$mcJ$sp(lhs, rhs);
         }

         public long and$mcJ$sp(final long a, final long b) {
            return this.h$3.and$mcJ$sp(a, b);
         }

         public long or$mcJ$sp(final long a, final long b) {
            return this.h$3.or$mcJ$sp(a, b);
         }

         public long not$mcJ$sp(final long a) {
            return this.h$3.complement$mcJ$sp(a);
         }

         public long zero$mcJ$sp() {
            return this.h$3.zero$mcJ$sp();
         }

         public long one$mcJ$sp() {
            return this.h$3.one$mcJ$sp();
         }

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return this.h$3.meet$mcJ$sp(lhs, rhs);
         }

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return this.h$3.join$mcJ$sp(lhs, rhs);
         }

         public {
            this.h$3 = h$3;
            JoinSemilattice.$init$(this);
            MeetSemilattice.$init$(this);
            Lattice.$init$(this);
            BoundedMeetSemilattice.$init$(this);
            BoundedJoinSemilattice.$init$(this);
            BoundedLattice.$init$(this);
            BoundedDistributiveLattice.$init$(this);
            Logic.$init$(this);
         }
      };
   }

   private Logic$() {
   }
}
