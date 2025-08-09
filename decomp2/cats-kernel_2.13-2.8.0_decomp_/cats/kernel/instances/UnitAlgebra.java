package cats.kernel.instances;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005A3A\u0001C\u0005\u0001!!)\u0011\u0005\u0001C\u0001E!)Q\u0005\u0001C\u0001M!)q\u0005\u0001C\u0001Q!)Q\u0006\u0001C!]!)\u0011\u0007\u0001C\u0001e!1A\u0007\u0001Q\u0005RUBQ!\u0010\u0001\u0005By\u00121\"\u00168ji\u0006cw-\u001a2sC*\u0011!bC\u0001\nS:\u001cH/\u00198dKNT!\u0001D\u0007\u0002\r-,'O\\3m\u0015\u0005q\u0011\u0001B2biN\u001c\u0001a\u0005\u0003\u0001#]q\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\rE\u0002\u00193mi\u0011aC\u0005\u00035-\u0011!CQ8v]\u0012,GmU3nS2\fG\u000f^5dKB\u0011!\u0003H\u0005\u0003;M\u0011A!\u00168jiB\u0019\u0001dH\u000e\n\u0005\u0001Z!\u0001E\"p[6,H/\u0019;jm\u0016<%o\\;q\u0003\u0019a\u0014N\\5u}Q\t1\u0005\u0005\u0002%\u00015\t\u0011\"A\u0003f[B$\u00180F\u0001\u001c\u0003\u001d\u0019w.\u001c2j]\u0016$2aG\u0015,\u0011\u0015Q3\u00011\u0001\u001c\u0003\u0005A\b\"\u0002\u0017\u0004\u0001\u0004Y\u0012!A=\u0002\rI,Wn\u001c<f)\rYr\u0006\r\u0005\u0006U\u0011\u0001\ra\u0007\u0005\u0006Y\u0011\u0001\raG\u0001\bS:4XM]:f)\tY2\u0007C\u0003+\u000b\u0001\u00071$\u0001\tsKB,\u0017\r^3e\u0007>l'-\u001b8f\u001dR\u00191D\u000e\u001d\t\u000b]2\u0001\u0019A\u000e\u0002\u0003\u0005DQ!\u000f\u0004A\u0002i\n\u0011A\u001c\t\u0003%mJ!\u0001P\n\u0003\u0007%sG/\u0001\td_6\u0014\u0017N\\3BY2|\u0005\u000f^5p]R\u0011qH\u0011\t\u0004%\u0001[\u0012BA!\u0014\u0005\u0019y\u0005\u000f^5p]\")1i\u0002a\u0001\t\u0006\u0011\u0011m\u001d\t\u0004\u000b6[bB\u0001$L\u001d\t9%*D\u0001I\u0015\tIu\"\u0001\u0004=e>|GOP\u0005\u0002)%\u0011AjE\u0001\ba\u0006\u001c7.Y4f\u0013\tquJ\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cWM\u0003\u0002M'\u0001"
)
public class UnitAlgebra implements BoundedSemilattice, CommutativeGroup {
   public double inverse$mcD$sp(final double a) {
      return Group.inverse$mcD$sp$(this, a);
   }

   public float inverse$mcF$sp(final float a) {
      return Group.inverse$mcF$sp$(this, a);
   }

   public int inverse$mcI$sp(final int a) {
      return Group.inverse$mcI$sp$(this, a);
   }

   public long inverse$mcJ$sp(final long a) {
      return Group.inverse$mcJ$sp$(this, a);
   }

   public double remove$mcD$sp(final double a, final double b) {
      return Group.remove$mcD$sp$(this, a, b);
   }

   public float remove$mcF$sp(final float a, final float b) {
      return Group.remove$mcF$sp$(this, a, b);
   }

   public int remove$mcI$sp(final int a, final int b) {
      return Group.remove$mcI$sp$(this, a, b);
   }

   public long remove$mcJ$sp(final long a, final long b) {
      return Group.remove$mcJ$sp$(this, a, b);
   }

   public Object combineN(final Object a, final int n) {
      return Group.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Group.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Group.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Group.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Group.combineN$mcJ$sp$(this, a, n);
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

   public void empty() {
   }

   public void combine(final BoxedUnit x, final BoxedUnit y) {
   }

   public void remove(final BoxedUnit x, final BoxedUnit y) {
   }

   public void inverse(final BoxedUnit x) {
   }

   public void repeatedCombineN(final BoxedUnit a, final int n) {
   }

   public Option combineAllOption(final IterableOnce as) {
      return (Option)(as.iterator().isEmpty() ? .MODULE$ : new Some(BoxedUnit.UNIT));
   }

   public UnitAlgebra() {
      Semigroup.$init$(this);
      Band.$init$(this);
      CommutativeSemigroup.$init$(this);
      Semilattice.$init$(this);
      Monoid.$init$(this);
      CommutativeMonoid.$init$(this);
      BoundedSemilattice.$init$(this);
      Group.$init$(this);
   }
}
