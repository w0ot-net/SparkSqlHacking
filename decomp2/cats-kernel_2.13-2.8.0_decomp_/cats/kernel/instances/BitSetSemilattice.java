package cats.kernel.instances;

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
import scala.collection.immutable.BitSet;
import scala.collection.immutable.BitSet.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2A\u0001B\u0003\u0001\u0019!)q\u0004\u0001C\u0001A!)1\u0005\u0001C\u0001I!)Q\u0005\u0001C\u0001M\t\t\")\u001b;TKR\u001cV-\\5mCR$\u0018nY3\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001\u00192\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#F\f\u000e\u0003\u001dI!AF\u0004\u0003%\t{WO\u001c3fIN+W.\u001b7biRL7-\u001a\t\u00031ui\u0011!\u0007\u0006\u00035m\t\u0011\"[7nkR\f'\r\\3\u000b\u0005qy\u0011AC2pY2,7\r^5p]&\u0011a$\u0007\u0002\u0007\u0005&$8+\u001a;\u0002\rqJg.\u001b;?)\u0005\t\u0003C\u0001\u0012\u0001\u001b\u0005)\u0011!B3naRLX#A\f\u0002\u000f\r|WNY5oKR\u0019qcJ\u0015\t\u000b!\u001a\u0001\u0019A\f\u0002\u0003aDQAK\u0002A\u0002]\t\u0011!\u001f"
)
public class BitSetSemilattice implements BoundedSemilattice {
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

   public BitSet empty() {
      return .MODULE$.empty();
   }

   public BitSet combine(final BitSet x, final BitSet y) {
      return (BitSet)x.$bar(y);
   }

   public BitSetSemilattice() {
      Semigroup.$init$(this);
      Band.$init$(this);
      CommutativeSemigroup.$init$(this);
      Semilattice.$init$(this);
      Monoid.$init$(this);
      CommutativeMonoid.$init$(this);
      BoundedSemilattice.$init$(this);
   }
}
