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
   bytes = "\u0006\u0005\u0005}aa\u0002\u0006\f!\u0003\r\t\u0001\u0005\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u00021\ta\u0012\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u00065\u0002!\teW\u0004\u0006?.A\t\u0001\u0019\u0004\u0006\u0015-A\t!\u0019\u0005\u0006c\u001a!\tA\u001d\u0005\u0006g\u001a!)\u0001\u001e\u0005\n\u0003\u001f1\u0011\u0011!C\u0005\u0003#\u0011aCQ8v]\u0012,G-T3fiN+W.\u001b7biRL7-\u001a\u0006\u0003\u00195\tq\u0001\\1ui&\u001cWMC\u0001\u000f\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u0012=M\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\u0007\u0005s\u0017\u0010E\u0002\u001a5qi\u0011aC\u0005\u00037-\u0011q\"T3fiN+W.\u001b7biRL7-\u001a\t\u0003;ya\u0001\u0001B\u0005 \u0001\u0001\u0006\t\u0011!b\u0001A\t\t\u0011)\u0005\u0002\"%A\u00111CI\u0005\u0003GQ\u0011qAT8uQ&tw\r\u000b\u0004\u001fK!\u0012t\u0007\u0010\t\u0003'\u0019J!a\n\u000b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G%RCf\u000b\b\u0003')J!a\u000b\u000b\u0002\u0007%sG/\r\u0003%[E*bB\u0001\u00182\u001b\u0005y#B\u0001\u0019\u0010\u0003\u0019a$o\\8u}%\tQ#M\u0003$gQ2TG\u0004\u0002\u0014i%\u0011Q\u0007F\u0001\u0005\u0019>tw-\r\u0003%[E*\u0012'B\u00129smRdBA\n:\u0013\tQD#A\u0003GY>\fG/\r\u0003%[E*\u0012'B\u0012>}\u0001{dBA\n?\u0013\tyD#\u0001\u0004E_V\u0014G.Z\u0019\u0005I5\nT#\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0007B\u00111\u0003R\u0005\u0003\u000bR\u0011A!\u00168ji\u0006\u0019qN\\3\u0016\u0003q\tQ![:P]\u0016$\"A\u0013-\u0015\u0005-s\u0005CA\nM\u0013\tiECA\u0004C_>dW-\u00198\t\u000b=\u001b\u00019\u0001)\u0002\u0005\u00154\bcA)V99\u0011!kU\u0007\u0002\u001b%\u0011A+D\u0001\ba\u0006\u001c7.Y4f\u0013\t1vK\u0001\u0002Fc*\u0011A+\u0004\u0005\u00063\u000e\u0001\r\u0001H\u0001\u0002C\u0006yQ.Z3u'\u0016l\u0017\u000e\\1ui&\u001cW-F\u0001]!\r\tV\fH\u0005\u0003=^\u0013!CQ8v]\u0012,GmU3nS2\fG\u000f^5dK\u00061\"i\\;oI\u0016$W*Z3u'\u0016l\u0017\u000e\\1ui&\u001cW\r\u0005\u0002\u001a\rM!aAY3j!\t\u00192-\u0003\u0002e)\t1\u0011I\\=SK\u001a\u00042!\u00074i\u0013\t97BA\u0010C_VtG-\u001a3NK\u0016$8+Z7jY\u0006$H/[2f\rVt7\r^5p]N\u0004\"!\u0007\u0001\u0011\u0005)|W\"A6\u000b\u00051l\u0017AA5p\u0015\u0005q\u0017\u0001\u00026bm\u0006L!\u0001]6\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005\u0001\u0017!B1qa2LXCA;y)\r1\u0018Q\u0001\t\u00043\u00019\bCA\u000fy\t%y\u0002\u0002)A\u0001\u0002\u000b\u0007\u0001\u0005K\u0004yKidh0!\u00012\u000b\rJ#f_\u00162\t\u0011j\u0013'F\u0019\u0006GM\"T0N\u0019\u0005I5\nT#M\u0003$qez((\r\u0003%[E*\u0012GB\u0012>}\u0005\rq(\r\u0003%[E*\u0002\"B(\t\u0001\b1\bf\u0001\u0005\u0002\nA\u00191#a\u0003\n\u0007\u00055AC\u0001\u0004j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003'\u0001B!!\u0006\u0002\u001c5\u0011\u0011q\u0003\u0006\u0004\u00033i\u0017\u0001\u00027b]\u001eLA!!\b\u0002\u0018\t1qJ\u00196fGR\u0004"
)
public interface BoundedMeetSemilattice extends MeetSemilattice {
   static BoundedMeetSemilattice apply(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilattice$.MODULE$.apply(ev);
   }

   Object one();

   // $FF: synthetic method
   static boolean isOne$(final BoundedMeetSemilattice $this, final Object a, final Eq ev) {
      return $this.isOne(a, ev);
   }

   default boolean isOne(final Object a, final Eq ev) {
      return ev.eqv(a, this.one());
   }

   // $FF: synthetic method
   static BoundedSemilattice meetSemilattice$(final BoundedMeetSemilattice $this) {
      return $this.meetSemilattice();
   }

   default BoundedSemilattice meetSemilattice() {
      return new BoundedSemilattice() {
         // $FF: synthetic field
         private final BoundedMeetSemilattice $outer;

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
            return this.$outer.one();
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.meet(x, y);
         }

         public {
            if (BoundedMeetSemilattice.this == null) {
               throw null;
            } else {
               this.$outer = BoundedMeetSemilattice.this;
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
   static double one$mcD$sp$(final BoundedMeetSemilattice $this) {
      return $this.one$mcD$sp();
   }

   default double one$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.one());
   }

   // $FF: synthetic method
   static float one$mcF$sp$(final BoundedMeetSemilattice $this) {
      return $this.one$mcF$sp();
   }

   default float one$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.one());
   }

   // $FF: synthetic method
   static int one$mcI$sp$(final BoundedMeetSemilattice $this) {
      return $this.one$mcI$sp();
   }

   default int one$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.one());
   }

   // $FF: synthetic method
   static long one$mcJ$sp$(final BoundedMeetSemilattice $this) {
      return $this.one$mcJ$sp();
   }

   default long one$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.one());
   }

   // $FF: synthetic method
   static boolean isOne$mcD$sp$(final BoundedMeetSemilattice $this, final double a, final Eq ev) {
      return $this.isOne$mcD$sp(a, ev);
   }

   default boolean isOne$mcD$sp(final double a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToDouble(a), ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcF$sp$(final BoundedMeetSemilattice $this, final float a, final Eq ev) {
      return $this.isOne$mcF$sp(a, ev);
   }

   default boolean isOne$mcF$sp(final float a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToFloat(a), ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcI$sp$(final BoundedMeetSemilattice $this, final int a, final Eq ev) {
      return $this.isOne$mcI$sp(a, ev);
   }

   default boolean isOne$mcI$sp(final int a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToInteger(a), ev);
   }

   // $FF: synthetic method
   static boolean isOne$mcJ$sp$(final BoundedMeetSemilattice $this, final long a, final Eq ev) {
      return $this.isOne$mcJ$sp(a, ev);
   }

   default boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return this.isOne(BoxesRunTime.boxToLong(a), ev);
   }

   // $FF: synthetic method
   static BoundedSemilattice meetSemilattice$mcD$sp$(final BoundedMeetSemilattice $this) {
      return $this.meetSemilattice$mcD$sp();
   }

   default BoundedSemilattice meetSemilattice$mcD$sp() {
      return this.meetSemilattice();
   }

   // $FF: synthetic method
   static BoundedSemilattice meetSemilattice$mcF$sp$(final BoundedMeetSemilattice $this) {
      return $this.meetSemilattice$mcF$sp();
   }

   default BoundedSemilattice meetSemilattice$mcF$sp() {
      return this.meetSemilattice();
   }

   // $FF: synthetic method
   static BoundedSemilattice meetSemilattice$mcI$sp$(final BoundedMeetSemilattice $this) {
      return $this.meetSemilattice$mcI$sp();
   }

   default BoundedSemilattice meetSemilattice$mcI$sp() {
      return this.meetSemilattice();
   }

   // $FF: synthetic method
   static BoundedSemilattice meetSemilattice$mcJ$sp$(final BoundedMeetSemilattice $this) {
      return $this.meetSemilattice$mcJ$sp();
   }

   default BoundedSemilattice meetSemilattice$mcJ$sp() {
      return this.meetSemilattice();
   }

   static void $init$(final BoundedMeetSemilattice $this) {
   }
}
