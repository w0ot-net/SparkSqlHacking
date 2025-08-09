package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Aa\u0002\u0005\u0001\u001f!)a\u0005\u0001C\u0001O!9!\u0006\u0001b\u0001\n\u0003Y\u0003B\u0002\u0017\u0001A\u0003%!\u0004C\u0003.\u0001\u0011\u0005a\u0006C\u00034\u0001\u0011\u0005A\u0007C\u00037\u0001\u0011\u0005sGA\u0006CS\u001eLe\u000e^$s_V\u0004(BA\u0005\u000b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\f\u0019\u000511.\u001a:oK2T\u0011!D\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0007\u0001\u0001b\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0004/aQR\"\u0001\u0006\n\u0005eQ!\u0001E\"p[6,H/\u0019;jm\u0016<%o\\;q!\tY2E\u0004\u0002\u001dC9\u0011Q\u0004I\u0007\u0002=)\u0011qDD\u0001\u0007yI|w\u000e\u001e \n\u0003MI!A\t\n\u0002\u000fA\f7m[1hK&\u0011A%\n\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005\t\u0012\u0012A\u0002\u001fj]&$h\bF\u0001)!\tI\u0003!D\u0001\t\u0003\u0015)W\u000e\u001d;z+\u0005Q\u0012AB3naRL\b%A\u0004d_6\u0014\u0017N\\3\u0015\u0007iy\u0013\u0007C\u00031\t\u0001\u0007!$A\u0001y\u0011\u0015\u0011D\u00011\u0001\u001b\u0003\u0005I\u0018aB5om\u0016\u00148/\u001a\u000b\u00035UBQ\u0001M\u0003A\u0002i\taA]3n_Z,Gc\u0001\u000e9s!)\u0001G\u0002a\u00015!)!G\u0002a\u00015\u0001"
)
public class BigIntGroup implements CommutativeGroup {
   private final BigInt empty;

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

   public Object repeatedCombineN(final Object a, final int n) {
      return Semigroup.repeatedCombineN$(this, a, n);
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

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public BigInt empty() {
      return this.empty;
   }

   public BigInt combine(final BigInt x, final BigInt y) {
      return x.$plus(y);
   }

   public BigInt inverse(final BigInt x) {
      return x.unary_$minus();
   }

   public BigInt remove(final BigInt x, final BigInt y) {
      return x.$minus(y);
   }

   public BigIntGroup() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
      Group.$init$(this);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
      this.empty = .MODULE$.BigInt().apply(0);
   }
}
