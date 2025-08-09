package cats.kernel.instances;

import cats.kernel.CommutativeGroup$mcJ$sp;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeMonoid$mcJ$sp;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.CommutativeSemigroup$mcJ$sp;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Group$mcJ$sp;
import cats.kernel.Monoid;
import cats.kernel.Monoid$mcJ$sp;
import cats.kernel.Semigroup;
import cats.kernel.Semigroup$mcJ$sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2AAB\u0004\u0001\u001d!)A\u0004\u0001C\u0001;!)\u0001\u0005\u0001C\u0001C!)a\u0005\u0001C\u0001O!)\u0001\u0006\u0001C\u0001S!)1\u0006\u0001C!Y\tIAj\u001c8h\u000fJ|W\u000f\u001d\u0006\u0003\u0011%\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005)Y\u0011AB6fe:,GNC\u0001\r\u0003\u0011\u0019\u0017\r^:\u0004\u0001M\u0019\u0001aD\u000b\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\r\u0005s\u0017PU3g!\r1r#G\u0007\u0002\u0013%\u0011\u0001$\u0003\u0002\u0011\u0007>lW.\u001e;bi&4Xm\u0012:pkB\u0004\"\u0001\u0005\u000e\n\u0005m\t\"\u0001\u0002'p]\u001e\fa\u0001P5oSRtD#\u0001\u0010\u0011\u0005}\u0001Q\"A\u0004\u0002\u000f\r|WNY5oKR\u0019\u0011D\t\u0013\t\u000b\r\u0012\u0001\u0019A\r\u0002\u0003aDQ!\n\u0002A\u0002e\t\u0011!_\u0001\u0006K6\u0004H/_\u000b\u00023\u00059\u0011N\u001c<feN,GCA\r+\u0011\u0015\u0019C\u00011\u0001\u001a\u0003\u0019\u0011X-\\8wKR\u0019\u0011$\f\u0018\t\u000b\r*\u0001\u0019A\r\t\u000b\u0015*\u0001\u0019A\r"
)
public class LongGroup implements CommutativeGroup$mcJ$sp {
   public long combineN(final long a, final int n) {
      return Group$mcJ$sp.combineN$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Group$mcJ$sp.combineN$mcJ$sp$(this, a, n);
   }

   public CommutativeMonoid reverse() {
      return CommutativeMonoid$mcJ$sp.reverse$(this);
   }

   public CommutativeMonoid reverse$mcJ$sp() {
      return CommutativeMonoid$mcJ$sp.reverse$mcJ$sp$(this);
   }

   public boolean isEmpty(final long a, final Eq ev) {
      return Monoid$mcJ$sp.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return Monoid$mcJ$sp.isEmpty$mcJ$sp$(this, a, ev);
   }

   public long combineAll(final IterableOnce as) {
      return Monoid$mcJ$sp.combineAll$(this, as);
   }

   public long combineAll$mcJ$sp(final IterableOnce as) {
      return Monoid$mcJ$sp.combineAll$mcJ$sp$(this, as);
   }

   public CommutativeSemigroup intercalate(final long middle) {
      return CommutativeSemigroup$mcJ$sp.intercalate$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return CommutativeSemigroup$mcJ$sp.intercalate$mcJ$sp$(this, middle);
   }

   public long repeatedCombineN(final long a, final int n) {
      return Semigroup$mcJ$sp.repeatedCombineN$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup$mcJ$sp.repeatedCombineN$mcJ$sp$(this, a, n);
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

   public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
   }

   public double inverse$mcD$sp(final double a) {
      return Group.inverse$mcD$sp$(this, a);
   }

   public float inverse$mcF$sp(final float a) {
      return Group.inverse$mcF$sp$(this, a);
   }

   public int inverse$mcI$sp(final int a) {
      return Group.inverse$mcI$sp$(this, a);
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

   public double combineN$mcD$sp(final double a, final int n) {
      return Group.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Group.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Group.combineN$mcI$sp$(this, a, n);
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

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid.isEmpty$mcD$sp$(this, a, ev);
   }

   public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return Monoid.isEmpty$mcF$sp$(this, a, ev);
   }

   public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return Monoid.isEmpty$mcI$sp$(this, a, ev);
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

   public Option combineAllOption(final IterableOnce as) {
      return Monoid.combineAllOption$(this, as);
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

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public long combine(final long x, final long y) {
      return this.combine$mcJ$sp(x, y);
   }

   public long empty() {
      return this.empty$mcJ$sp();
   }

   public long inverse(final long x) {
      return this.inverse$mcJ$sp(x);
   }

   public long remove(final long x, final long y) {
      return this.remove$mcJ$sp(x, y);
   }

   public long combine$mcJ$sp(final long x, final long y) {
      return x + y;
   }

   public long empty$mcJ$sp() {
      return 0L;
   }

   public long inverse$mcJ$sp(final long x) {
      return -x;
   }

   public long remove$mcJ$sp(final long x, final long y) {
      return x - y;
   }

   public LongGroup() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
      Group.$init$(this);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
   }
}
