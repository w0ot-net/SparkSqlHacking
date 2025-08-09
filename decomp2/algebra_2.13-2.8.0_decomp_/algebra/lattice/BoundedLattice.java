package algebra.lattice;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\raa\u0002\u0005\n!\u0003\r\tA\u0004\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u0002!\teS\u0004\u0006\u001b&A\tA\u0014\u0004\u0006\u0011%A\ta\u0014\u0005\u0006E\u0012!\ta\u0019\u0005\u0006I\u0012!)!\u001a\u0005\bs\u0012\t\t\u0011\"\u0003{\u00059\u0011u.\u001e8eK\u0012d\u0015\r\u001e;jG\u0016T!AC\u0006\u0002\u000f1\fG\u000f^5dK*\tA\"A\u0004bY\u001e,'M]1\u0004\u0001U\u0011q\u0002H\n\u0006\u0001A1rH\u0011\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0004\u0003:L\bcA\f\u001955\t\u0011\"\u0003\u0002\u001a\u0013\t9A*\u0019;uS\u000e,\u0007CA\u000e\u001d\u0019\u0001!\u0011\"\b\u0001!\u0002\u0003\u0005)\u0019\u0001\u0010\u0003\u0003\u0005\u000b\"a\b\t\u0011\u0005E\u0001\u0013BA\u0011\u0013\u0005\u001dqu\u000e\u001e5j]\u001eDc\u0001H\u0012'aUR\u0004CA\t%\u0013\t)#CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012(Q)JcBA\t)\u0013\tI##A\u0002J]R\fD\u0001J\u00160'9\u0011AfL\u0007\u0002[)\u0011a&D\u0001\u0007yI|w\u000e\u001e \n\u0003M\tTaI\u00193iMr!!\u0005\u001a\n\u0005M\u0012\u0012\u0001\u0002'p]\u001e\fD\u0001J\u00160'E*1EN\u001c:q9\u0011\u0011cN\u0005\u0003qI\tQA\u00127pCR\fD\u0001J\u00160'E*1e\u000f\u001f?{9\u0011\u0011\u0003P\u0005\u0003{I\ta\u0001R8vE2,\u0017\u0007\u0002\u0013,_M\u00012a\u0006!\u001b\u0013\t\t\u0015B\u0001\fC_VtG-\u001a3NK\u0016$8+Z7jY\u0006$H/[2f!\r92IG\u0005\u0003\t&\u0011aCQ8v]\u0012,GMS8j]N+W.\u001b7biRL7-Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u001d\u0003\"!\u0005%\n\u0005%\u0013\"\u0001B+oSR\fA\u0001Z;bYV\tA\nE\u0002\u0018\u0001i\taBQ8v]\u0012,G\rT1ui&\u001cW\r\u0005\u0002\u0018\tM)A\u0001U*X5B\u0011\u0011#U\u0005\u0003%J\u0011a!\u00118z%\u00164\u0007cA\fU-&\u0011Q+\u0003\u0002 \u0005>,h\u000eZ3e\u001b\u0016,GoU3nS2\fG\u000f^5dK\u001a+hn\u0019;j_:\u001c\bCA\f\u0001!\r9\u0002LV\u0005\u00033&\u0011qDQ8v]\u0012,GMS8j]N+W.\u001b7biRL7-\u001a$v]\u000e$\u0018n\u001c8t!\tY\u0006-D\u0001]\u0015\tif,\u0001\u0002j_*\tq,\u0001\u0003kCZ\f\u0017BA1]\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\ta*A\u0003baBd\u00170\u0006\u0002gSR\u0011qm\u001d\t\u0004/\u0001A\u0007CA\u000ej\t%ib\u0001)A\u0001\u0002\u000b\u0007a\u0004\u000b\u0004jG-lw.]\u0019\u0006G\u001dBC.K\u0019\u0005I-z3#M\u0003$cIr7'\r\u0003%W=\u001a\u0012'B\u00127oAD\u0014\u0007\u0002\u0013,_M\tTaI\u001e=ev\nD\u0001J\u00160'!)AO\u0002a\u0002O\u0006\u0011QM\u001e\u0015\u0003\rY\u0004\"!E<\n\u0005a\u0014\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001|!\tax0D\u0001~\u0015\tqh,\u0001\u0003mC:<\u0017bAA\u0001{\n1qJ\u00196fGR\u0004"
)
public interface BoundedLattice extends Lattice, BoundedMeetSemilattice, BoundedJoinSemilattice {
   static BoundedLattice apply(final BoundedLattice ev) {
      return BoundedLattice$.MODULE$.apply(ev);
   }

   // $FF: synthetic method
   static BoundedLattice dual$(final BoundedLattice $this) {
      return $this.dual();
   }

   default BoundedLattice dual() {
      return new BoundedLattice() {
         // $FF: synthetic field
         private final BoundedLattice $outer;

         public BoundedLattice dual$mcD$sp() {
            return BoundedLattice.super.dual$mcD$sp();
         }

         public BoundedLattice dual$mcF$sp() {
            return BoundedLattice.super.dual$mcF$sp();
         }

         public BoundedLattice dual$mcI$sp() {
            return BoundedLattice.super.dual$mcI$sp();
         }

         public BoundedLattice dual$mcJ$sp() {
            return BoundedLattice.super.dual$mcJ$sp();
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

         public BoundedLattice dual() {
            return this.$outer;
         }

         public {
            if (BoundedLattice.this == null) {
               throw null;
            } else {
               this.$outer = BoundedLattice.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
               BoundedMeetSemilattice.$init$(this);
               BoundedJoinSemilattice.$init$(this);
               BoundedLattice.$init$(this);
            }
         }
      };
   }

   default BoundedLattice dual$mcD$sp() {
      return this.dual();
   }

   default BoundedLattice dual$mcF$sp() {
      return this.dual();
   }

   default BoundedLattice dual$mcI$sp() {
      return this.dual();
   }

   default BoundedLattice dual$mcJ$sp() {
      return this.dual();
   }

   static void $init$(final BoundedLattice $this) {
   }
}
