package cats.kernel.instances;

import cats.kernel.CommutativeGroup$mcD$sp;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeMonoid$mcD$sp;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.CommutativeSemigroup$mcD$sp;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Group$mcD$sp;
import cats.kernel.Monoid;
import cats.kernel.Monoid$mcD$sp;
import cats.kernel.Semigroup;
import cats.kernel.Semigroup$mcD$sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2AAB\u0004\u0001\u001d!)A\u0004\u0001C\u0001;!)\u0001\u0005\u0001C\u0001C!)a\u0005\u0001C\u0001O!)\u0001\u0006\u0001C\u0001S!)1\u0006\u0001C!Y\tYAi\\;cY\u0016<%o\\;q\u0015\tA\u0011\"A\u0005j]N$\u0018M\\2fg*\u0011!bC\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u00031\tAaY1ug\u000e\u00011c\u0001\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u00042AF\f\u001a\u001b\u0005I\u0011B\u0001\r\n\u0005A\u0019u.\\7vi\u0006$\u0018N^3He>,\b\u000f\u0005\u0002\u00115%\u00111$\u0005\u0002\u0007\t>,(\r\\3\u0002\rqJg.\u001b;?)\u0005q\u0002CA\u0010\u0001\u001b\u00059\u0011aB2p[\nLg.\u001a\u000b\u00043\t\"\u0003\"B\u0012\u0003\u0001\u0004I\u0012!\u0001=\t\u000b\u0015\u0012\u0001\u0019A\r\u0002\u0003e\fQ!Z7qif,\u0012!G\u0001\bS:4XM]:f)\tI\"\u0006C\u0003$\t\u0001\u0007\u0011$\u0001\u0004sK6|g/\u001a\u000b\u000435r\u0003\"B\u0012\u0006\u0001\u0004I\u0002\"B\u0013\u0006\u0001\u0004I\u0002"
)
public class DoubleGroup implements CommutativeGroup$mcD$sp {
   public double combineN(final double a, final int n) {
      return Group$mcD$sp.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Group$mcD$sp.combineN$mcD$sp$(this, a, n);
   }

   public CommutativeMonoid reverse() {
      return CommutativeMonoid$mcD$sp.reverse$(this);
   }

   public CommutativeMonoid reverse$mcD$sp() {
      return CommutativeMonoid$mcD$sp.reverse$mcD$sp$(this);
   }

   public boolean isEmpty(final double a, final Eq ev) {
      return Monoid$mcD$sp.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid$mcD$sp.isEmpty$mcD$sp$(this, a, ev);
   }

   public double combineAll(final IterableOnce as) {
      return Monoid$mcD$sp.combineAll$(this, as);
   }

   public double combineAll$mcD$sp(final IterableOnce as) {
      return Monoid$mcD$sp.combineAll$mcD$sp$(this, as);
   }

   public CommutativeSemigroup intercalate(final double middle) {
      return CommutativeSemigroup$mcD$sp.intercalate$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return CommutativeSemigroup$mcD$sp.intercalate$mcD$sp$(this, middle);
   }

   public double repeatedCombineN(final double a, final int n) {
      return Semigroup$mcD$sp.repeatedCombineN$(this, a, n);
   }

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup$mcD$sp.repeatedCombineN$mcD$sp$(this, a, n);
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

   public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
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

   public float remove$mcF$sp(final float a, final float b) {
      return Group.remove$mcF$sp$(this, a, b);
   }

   public int remove$mcI$sp(final int a, final int b) {
      return Group.remove$mcI$sp$(this, a, b);
   }

   public long remove$mcJ$sp(final long a, final long b) {
      return Group.remove$mcJ$sp$(this, a, b);
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

   public float empty$mcF$sp() {
      return Monoid.empty$mcF$sp$(this);
   }

   public int empty$mcI$sp() {
      return Monoid.empty$mcI$sp$(this);
   }

   public long empty$mcJ$sp() {
      return Monoid.empty$mcJ$sp$(this);
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

   public float combine$mcF$sp(final float x, final float y) {
      return Semigroup.combine$mcF$sp$(this, x, y);
   }

   public int combine$mcI$sp(final int x, final int y) {
      return Semigroup.combine$mcI$sp$(this, x, y);
   }

   public long combine$mcJ$sp(final long x, final long y) {
      return Semigroup.combine$mcJ$sp$(this, x, y);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public double combine(final double x, final double y) {
      return this.combine$mcD$sp(x, y);
   }

   public double empty() {
      return this.empty$mcD$sp();
   }

   public double inverse(final double x) {
      return this.inverse$mcD$sp(x);
   }

   public double remove(final double x, final double y) {
      return this.remove$mcD$sp(x, y);
   }

   public double combine$mcD$sp(final double x, final double y) {
      return x + y;
   }

   public double empty$mcD$sp() {
      return (double)0.0F;
   }

   public double inverse$mcD$sp(final double x) {
      return -x;
   }

   public double remove$mcD$sp(final double x, final double y) {
      return x - y;
   }

   public DoubleGroup() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
      Group.$init$(this);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
   }
}
