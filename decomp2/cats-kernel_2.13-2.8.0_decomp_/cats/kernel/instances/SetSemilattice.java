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
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2A\u0001B\u0003\u0001\u0019!)a\u0006\u0001C\u0001_!)!\u0007\u0001C\u0001g!)A\u0007\u0001C\u0001k\tq1+\u001a;TK6LG.\u0019;uS\u000e,'B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u00055)3c\u0001\u0001\u000f)A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u00042!\u0006\f\u0019\u001b\u00059\u0011BA\f\b\u0005I\u0011u.\u001e8eK\u0012\u001cV-\\5mCR$\u0018nY3\u0011\u0007e\u00013E\u0004\u0002\u001b=A\u00111\u0004E\u0007\u00029)\u0011QdC\u0001\u0007yI|w\u000e\u001e \n\u0005}\u0001\u0012A\u0002)sK\u0012,g-\u0003\u0002\"E\t\u00191+\u001a;\u000b\u0005}\u0001\u0002C\u0001\u0013&\u0019\u0001!QA\n\u0001C\u0002\u001d\u0012\u0011!Q\t\u0003Q-\u0002\"aD\u0015\n\u0005)\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001f1J!!\f\t\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0002aA\u0019\u0011\u0007A\u0012\u000e\u0003\u0015\tQ!Z7qif,\u0012\u0001G\u0001\bG>l'-\u001b8f)\rAb\u0007\u000f\u0005\u0006o\r\u0001\r\u0001G\u0001\u0002q\")\u0011h\u0001a\u00011\u0005\t\u0011\u0010"
)
public class SetSemilattice implements BoundedSemilattice {
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

   public Set empty() {
      return .MODULE$.Set().empty();
   }

   public Set combine(final Set x, final Set y) {
      return (Set)x.$bar(y);
   }

   public SetSemilattice() {
      Semigroup.$init$(this);
      Band.$init$(this);
      CommutativeSemigroup.$init$(this);
      Semilattice.$init$(this);
      Monoid.$init$(this);
      CommutativeMonoid.$init$(this);
      BoundedSemilattice.$init$(this);
   }
}
