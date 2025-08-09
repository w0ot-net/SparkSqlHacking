package algebra.instances;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Semiring;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.Semigroup;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2A!\u0002\u0004\u0001\u0017!)q\u0006\u0001C\u0001a!)1\u0007\u0001C\u0001i!)Q\u0007\u0001C\u0001m!)1\b\u0001C\u0001y\tY1+\u001a;TK6L'/\u001b8h\u0015\t9\u0001\"A\u0005j]N$\u0018M\\2fg*\t\u0011\"A\u0004bY\u001e,'M]1\u0004\u0001U\u0011ABJ\n\u0004\u00015\u0019\u0002C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\rE\u0002\u0015/ei\u0011!\u0006\u0006\u0003-!\tAA]5oO&\u0011\u0001$\u0006\u0002\t'\u0016l\u0017N]5oOB\u0019!$\t\u0013\u000f\u0005my\u0002C\u0001\u000f\u0010\u001b\u0005i\"B\u0001\u0010\u000b\u0003\u0019a$o\\8u}%\u0011\u0001eD\u0001\u0007!J,G-\u001a4\n\u0005\t\u001a#aA*fi*\u0011\u0001e\u0004\t\u0003K\u0019b\u0001\u0001B\u0003(\u0001\t\u0007\u0001FA\u0001B#\tIC\u0006\u0005\u0002\u000fU%\u00111f\u0004\u0002\b\u001d>$\b.\u001b8h!\tqQ&\u0003\u0002/\u001f\t\u0019\u0011I\\=\u0002\rqJg.\u001b;?)\u0005\t\u0004c\u0001\u001a\u0001I5\ta!\u0001\u0003{KJ|W#A\r\u0002\tAdWo\u001d\u000b\u00043]J\u0004\"\u0002\u001d\u0004\u0001\u0004I\u0012!\u0001=\t\u000bi\u001a\u0001\u0019A\r\u0002\u0003e\fQ\u0001^5nKN$2!G\u001f?\u0011\u0015AD\u00011\u0001\u001a\u0011\u0015QD\u00011\u0001\u001a\u0001"
)
public class SetSemiring implements Semiring {
   public Semigroup multiplicative() {
      return MultiplicativeSemigroup.multiplicative$(this);
   }

   public Semigroup multiplicative$mcD$sp() {
      return MultiplicativeSemigroup.multiplicative$mcD$sp$(this);
   }

   public Semigroup multiplicative$mcF$sp() {
      return MultiplicativeSemigroup.multiplicative$mcF$sp$(this);
   }

   public Semigroup multiplicative$mcI$sp() {
      return MultiplicativeSemigroup.multiplicative$mcI$sp$(this);
   }

   public Semigroup multiplicative$mcJ$sp() {
      return MultiplicativeSemigroup.multiplicative$mcJ$sp$(this);
   }

   public double times$mcD$sp(final double x, final double y) {
      return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
   }

   public float times$mcF$sp(final float x, final float y) {
      return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public Object pow(final Object a, final int n) {
      return MultiplicativeSemigroup.pow$(this, a, n);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
   }

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
   }

   public Object positivePow(final Object a, final int n) {
      return MultiplicativeSemigroup.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
   }

   public int positivePow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
   }

   public long positivePow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
   }

   public Option tryProduct(final IterableOnce as) {
      return MultiplicativeSemigroup.tryProduct$(this, as);
   }

   public CommutativeMonoid additive() {
      return AdditiveCommutativeMonoid.additive$(this);
   }

   public CommutativeMonoid additive$mcD$sp() {
      return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
   }

   public CommutativeMonoid additive$mcF$sp() {
      return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
   }

   public CommutativeMonoid additive$mcI$sp() {
      return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
   }

   public CommutativeMonoid additive$mcJ$sp() {
      return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
   }

   public double zero$mcD$sp() {
      return AdditiveMonoid.zero$mcD$sp$(this);
   }

   public float zero$mcF$sp() {
      return AdditiveMonoid.zero$mcF$sp$(this);
   }

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero(final Object a, final Eq ev) {
      return AdditiveMonoid.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public Object sumN(final Object a, final int n) {
      return AdditiveMonoid.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
   }

   public Object sum(final IterableOnce as) {
      return AdditiveMonoid.sum$(this, as);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcF$sp$(this, as);
   }

   public int sum$mcI$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcI$sp$(this, as);
   }

   public long sum$mcJ$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcJ$sp$(this, as);
   }

   public Option trySum(final IterableOnce as) {
      return AdditiveMonoid.trySum$(this, as);
   }

   public double plus$mcD$sp(final double x, final double y) {
      return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
   }

   public float plus$mcF$sp(final float x, final float y) {
      return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public Object positiveSumN(final Object a, final int n) {
      return AdditiveSemigroup.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public Set zero() {
      return .MODULE$.Set().empty();
   }

   public Set plus(final Set x, final Set y) {
      return (Set)x.$bar(y);
   }

   public Set times(final Set x, final Set y) {
      return (Set)x.$amp(y);
   }

   public SetSemiring() {
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
   }
}
