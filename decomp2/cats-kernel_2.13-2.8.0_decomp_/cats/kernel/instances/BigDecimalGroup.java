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
import scala.math.BigDecimal;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Aa\u0002\u0005\u0001\u001f!)a\u0005\u0001C\u0001O!9!\u0006\u0001b\u0001\n\u0003Y\u0003B\u0002\u0017\u0001A\u0003%!\u0004C\u0003.\u0001\u0011\u0005a\u0006C\u00034\u0001\u0011\u0005A\u0007C\u00037\u0001\u0011\u0005sGA\bCS\u001e$UmY5nC2<%o\\;q\u0015\tI!\"A\u0005j]N$\u0018M\\2fg*\u00111\u0002D\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u00035\tAaY1ug\u000e\u00011c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u00042a\u0006\r\u001b\u001b\u0005Q\u0011BA\r\u000b\u0005A\u0019u.\\7vi\u0006$\u0018N^3He>,\b\u000f\u0005\u0002\u001cG9\u0011A$\t\b\u0003;\u0001j\u0011A\b\u0006\u0003?9\ta\u0001\u0010:p_Rt\u0014\"A\n\n\u0005\t\u0012\u0012a\u00029bG.\fw-Z\u0005\u0003I\u0015\u0012!BQ5h\t\u0016\u001c\u0017.\\1m\u0015\t\u0011##\u0001\u0004=S:LGO\u0010\u000b\u0002QA\u0011\u0011\u0006A\u0007\u0002\u0011\u0005)Q-\u001c9usV\t!$\u0001\u0004f[B$\u0018\u0010I\u0001\bG>l'-\u001b8f)\rQr&\r\u0005\u0006a\u0011\u0001\rAG\u0001\u0002q\")!\u0007\u0002a\u00015\u0005\t\u00110A\u0004j]Z,'o]3\u0015\u0005i)\u0004\"\u0002\u0019\u0006\u0001\u0004Q\u0012A\u0002:f[>4X\rF\u0002\u001bqeBQ\u0001\r\u0004A\u0002iAQA\r\u0004A\u0002i\u0001"
)
public class BigDecimalGroup implements CommutativeGroup {
   private final BigDecimal empty;

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

   public BigDecimal empty() {
      return this.empty;
   }

   public BigDecimal combine(final BigDecimal x, final BigDecimal y) {
      return new BigDecimal(x.bigDecimal().add(y.bigDecimal()), x.mc());
   }

   public BigDecimal inverse(final BigDecimal x) {
      return new BigDecimal(x.bigDecimal().negate(), x.mc());
   }

   public BigDecimal remove(final BigDecimal x, final BigDecimal y) {
      return new BigDecimal(x.bigDecimal().subtract(y.bigDecimal()), x.mc());
   }

   public BigDecimalGroup() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
      Group.$init$(this);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
      this.empty = .MODULE$.BigDecimal().apply(0);
   }
}
