package cats.kernel.instances;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.SortedSet.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2A!\u0002\u0004\u0001\u001b!AA\u0006\u0001B\u0002B\u0003-Q\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00037\u0001\u0011\u0005q\u0007C\u00039\u0001\u0011\u0005\u0011H\u0001\u000bT_J$X\rZ*fiN+W.\u001b7biRL7-\u001a\u0006\u0003\u000f!\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005%Q\u0011AB6fe:,GNC\u0001\f\u0003\u0011\u0019\u0017\r^:\u0004\u0001U\u0011abI\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u0017/ei\u0011\u0001C\u0005\u00031!\u0011!CQ8v]\u0012,GmU3nS2\fG\u000f^5dKB\u0019!dH\u0011\u000e\u0003mQ!\u0001H\u000f\u0002\u0013%lW.\u001e;bE2,'B\u0001\u0010\u0012\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Am\u0011\u0011bU8si\u0016$7+\u001a;\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\r!\n\u0002\u0002\u0003F\u0011a%\u000b\t\u0003!\u001dJ!\u0001K\t\u0003\u000f9{G\u000f[5oOB\u0011\u0001CK\u0005\u0003WE\u00111!\u00118z\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0004-9\n\u0013BA\u0018\t\u0005\u0015y%\u000fZ3s\u0003\u0019a\u0014N\\5u}Q\t!\u0007\u0006\u00024kA\u0019A\u0007A\u0011\u000e\u0003\u0019AQ\u0001\f\u0002A\u00045\nQ!Z7qif,\u0012!G\u0001\bG>l'-\u001b8f)\rI\"\b\u0010\u0005\u0006w\u0011\u0001\r!G\u0001\u0002q\")Q\b\u0002a\u00013\u0005\t\u0011\u0010"
)
public class SortedSetSemilattice implements BoundedSemilattice {
   private final Order evidence$7;

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

   public SortedSet empty() {
      return (SortedSet).MODULE$.empty(((Order)scala.Predef..MODULE$.implicitly(this.evidence$7)).toOrdering());
   }

   public SortedSet combine(final SortedSet x, final SortedSet y) {
      return (SortedSet)x.$bar(y);
   }

   public SortedSetSemilattice(final Order evidence$7) {
      this.evidence$7 = evidence$7;
      Semigroup.$init$(this);
      Band.$init$(this);
      CommutativeSemigroup.$init$(this);
      Semilattice.$init$(this);
      Monoid.$init$(this);
      CommutativeMonoid.$init$(this);
      BoundedSemilattice.$init$(this);
   }
}
