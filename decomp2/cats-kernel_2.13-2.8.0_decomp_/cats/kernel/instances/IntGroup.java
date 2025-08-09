package cats.kernel.instances;

import cats.kernel.CommutativeGroup$mcI$sp;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeMonoid$mcI$sp;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.CommutativeSemigroup$mcI$sp;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Group$mcI$sp;
import cats.kernel.Monoid;
import cats.kernel.Monoid$mcI$sp;
import cats.kernel.Semigroup;
import cats.kernel.Semigroup$mcI$sp;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2AAB\u0004\u0001\u001d!)A\u0004\u0001C\u0001;!)\u0001\u0005\u0001C\u0001C!)a\u0005\u0001C\u0001O!)\u0001\u0006\u0001C\u0001S!)1\u0006\u0001C!Y\tA\u0011J\u001c;He>,\bO\u0003\u0002\t\u0013\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0015-\taa[3s]\u0016d'\"\u0001\u0007\u0002\t\r\fGo]\u0002\u0001'\r\u0001q\"\u0006\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Y9\u0012$D\u0001\n\u0013\tA\u0012B\u0001\tD_6lW\u000f^1uSZ,wI]8vaB\u0011\u0001CG\u0005\u00037E\u00111!\u00138u\u0003\u0019a\u0014N\\5u}Q\ta\u0004\u0005\u0002 \u00015\tq!A\u0004d_6\u0014\u0017N\\3\u0015\u0007e\u0011C\u0005C\u0003$\u0005\u0001\u0007\u0011$A\u0001y\u0011\u0015)#\u00011\u0001\u001a\u0003\u0005I\u0018!B3naRLX#A\r\u0002\u000f%tg/\u001a:tKR\u0011\u0011D\u000b\u0005\u0006G\u0011\u0001\r!G\u0001\u0007e\u0016lwN^3\u0015\u0007eic\u0006C\u0003$\u000b\u0001\u0007\u0011\u0004C\u0003&\u000b\u0001\u0007\u0011\u0004"
)
public class IntGroup implements CommutativeGroup$mcI$sp {
   public int combineN(final int a, final int n) {
      return Group$mcI$sp.combineN$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Group$mcI$sp.combineN$mcI$sp$(this, a, n);
   }

   public CommutativeMonoid reverse() {
      return CommutativeMonoid$mcI$sp.reverse$(this);
   }

   public CommutativeMonoid reverse$mcI$sp() {
      return CommutativeMonoid$mcI$sp.reverse$mcI$sp$(this);
   }

   public boolean isEmpty(final int a, final Eq ev) {
      return Monoid$mcI$sp.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return Monoid$mcI$sp.isEmpty$mcI$sp$(this, a, ev);
   }

   public int combineAll(final IterableOnce as) {
      return Monoid$mcI$sp.combineAll$(this, as);
   }

   public int combineAll$mcI$sp(final IterableOnce as) {
      return Monoid$mcI$sp.combineAll$mcI$sp$(this, as);
   }

   public CommutativeSemigroup intercalate(final int middle) {
      return CommutativeSemigroup$mcI$sp.intercalate$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return CommutativeSemigroup$mcI$sp.intercalate$mcI$sp$(this, middle);
   }

   public int repeatedCombineN(final int a, final int n) {
      return Semigroup$mcI$sp.repeatedCombineN$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup$mcI$sp.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public CommutativeMonoid reverse$mcD$sp() {
      return CommutativeMonoid.reverse$mcD$sp$(this);
   }

   public CommutativeMonoid reverse$mcF$sp() {
      return CommutativeMonoid.reverse$mcF$sp$(this);
   }

   public CommutativeMonoid reverse$mcJ$sp() {
      return CommutativeMonoid.reverse$mcJ$sp$(this);
   }

   public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
   }

   public double inverse$mcD$sp(final double a) {
      return Group.inverse$mcD$sp$(this, a);
   }

   public float inverse$mcF$sp(final float a) {
      return Group.inverse$mcF$sp$(this, a);
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

   public long remove$mcJ$sp(final long a, final long b) {
      return Group.remove$mcJ$sp$(this, a, b);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Group.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Group.combineN$mcF$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Group.combineN$mcJ$sp$(this, a, n);
   }

   public double empty$mcD$sp() {
      return Monoid.empty$mcD$sp$(this);
   }

   public float empty$mcF$sp() {
      return Monoid.empty$mcF$sp$(this);
   }

   public long empty$mcJ$sp() {
      return Monoid.empty$mcJ$sp$(this);
   }

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid.isEmpty$mcD$sp$(this, a, ev);
   }

   public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return Monoid.isEmpty$mcF$sp$(this, a, ev);
   }

   public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return Monoid.isEmpty$mcJ$sp$(this, a, ev);
   }

   public double combineAll$mcD$sp(final IterableOnce as) {
      return Monoid.combineAll$mcD$sp$(this, as);
   }

   public float combineAll$mcF$sp(final IterableOnce as) {
      return Monoid.combineAll$mcF$sp$(this, as);
   }

   public long combineAll$mcJ$sp(final IterableOnce as) {
      return Monoid.combineAll$mcJ$sp$(this, as);
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

   public long combine$mcJ$sp(final long x, final long y) {
      return Semigroup.combine$mcJ$sp$(this, x, y);
   }

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public int combine(final int x, final int y) {
      return this.combine$mcI$sp(x, y);
   }

   public int empty() {
      return this.empty$mcI$sp();
   }

   public int inverse(final int x) {
      return this.inverse$mcI$sp(x);
   }

   public int remove(final int x, final int y) {
      return this.remove$mcI$sp(x, y);
   }

   public int combine$mcI$sp(final int x, final int y) {
      return x + y;
   }

   public int empty$mcI$sp() {
      return 0;
   }

   public int inverse$mcI$sp(final int x) {
      return -x;
   }

   public int remove$mcI$sp(final int x, final int y) {
      return x - y;
   }

   public IntGroup() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
      Group.$init$(this);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
   }
}
