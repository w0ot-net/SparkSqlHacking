package algebra.instances;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.BoolRng;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2A!\u0002\u0004\u0001\u0017!)q\u0006\u0001C\u0001a!)1\u0007\u0001C\u0001i!)Q\u0007\u0001C\u0001m!)1\b\u0001C\u0001y\tQ1+\u001a;C_>d'K\\4\u000b\u0005\u001dA\u0011!C5ogR\fgnY3t\u0015\u0005I\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\taaeE\u0002\u0001\u001bM\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0007c\u0001\u000b\u001835\tQC\u0003\u0002\u0017\u0011\u0005!!/\u001b8h\u0013\tARCA\u0004C_>d'K\\4\u0011\u0007i\tCE\u0004\u0002\u001c?A\u0011AdD\u0007\u0002;)\u0011aDC\u0001\u0007yI|w\u000e\u001e \n\u0005\u0001z\u0011A\u0002)sK\u0012,g-\u0003\u0002#G\t\u00191+\u001a;\u000b\u0005\u0001z\u0001CA\u0013'\u0019\u0001!Qa\n\u0001C\u0002!\u0012\u0011!Q\t\u0003S1\u0002\"A\u0004\u0016\n\u0005-z!a\u0002(pi\"Lgn\u001a\t\u0003\u001d5J!AL\b\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0002cA\u0019!\u0007\u0001\u0013\u000e\u0003\u0019\tAA_3s_V\t\u0011$\u0001\u0003qYV\u001cHcA\r8s!)\u0001h\u0001a\u00013\u0005\t\u0001\u0010C\u0003;\u0007\u0001\u0007\u0011$A\u0001z\u0003\u0015!\u0018.\\3t)\rIRH\u0010\u0005\u0006q\u0011\u0001\r!\u0007\u0005\u0006u\u0011\u0001\r!\u0007"
)
public class SetBoolRng implements BoolRng {
   public final Object negate(final Object x) {
      return BoolRng.negate$(this, x);
   }

   public CommutativeSemigroup multiplicative() {
      return MultiplicativeCommutativeSemigroup.multiplicative$(this);
   }

   public CommutativeSemigroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcD$sp$(this);
   }

   public CommutativeSemigroup multiplicative$mcF$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcF$sp$(this);
   }

   public CommutativeSemigroup multiplicative$mcI$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcI$sp$(this);
   }

   public CommutativeSemigroup multiplicative$mcJ$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcJ$sp$(this);
   }

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup.additive$(this);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
   }

   public CommutativeGroup additive$mcF$sp() {
      return AdditiveCommutativeGroup.additive$mcF$sp$(this);
   }

   public CommutativeGroup additive$mcI$sp() {
      return AdditiveCommutativeGroup.additive$mcI$sp$(this);
   }

   public CommutativeGroup additive$mcJ$sp() {
      return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
   }

   public double negate$mcD$sp(final double x) {
      return AdditiveGroup.negate$mcD$sp$(this, x);
   }

   public float negate$mcF$sp(final float x) {
      return AdditiveGroup.negate$mcF$sp$(this, x);
   }

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public Object minus(final Object x, final Object y) {
      return AdditiveGroup.minus$(this, x, y);
   }

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
   }

   public float minus$mcF$sp(final float x, final float y) {
      return AdditiveGroup.minus$mcF$sp$(this, x, y);
   }

   public int minus$mcI$sp(final int x, final int y) {
      return AdditiveGroup.minus$mcI$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public Object sumN(final Object a, final int n) {
      return AdditiveGroup.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveGroup.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
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
      return (Set)x.$minus$minus(y).$bar((scala.collection.Set)y.$minus$minus(x));
   }

   public Set times(final Set x, final Set y) {
      return (Set)x.$amp(y);
   }

   public SetBoolRng() {
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      MultiplicativeCommutativeSemigroup.$init$(this);
      BoolRng.$init$(this);
   }
}
