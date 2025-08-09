package algebra.lattice;

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
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000553AAB\u0004\u0001\u0019!A\u0001\b\u0001B\u0001B\u0003%\u0011\bC\u0003>\u0001\u0011\u0005a\bC\u0003B\u0001\u0011\u0005!\tC\u0003D\u0001\u0011\u0005A\tC\u0003J\u0001\u0011\u0005!J\u0001\nC_>d'K\\4Ge>lw)\u001a8C_>d'B\u0001\u0005\n\u0003\u001da\u0017\r\u001e;jG\u0016T\u0011AC\u0001\bC2<WM\u0019:b\u0007\u0001)\"!\u0004\u000f\u0014\u0007\u0001qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0004+aQR\"\u0001\f\u000b\u0005]I\u0011\u0001\u0002:j]\u001eL!!\u0007\f\u0003\u000f\t{w\u000e\u001c*oOB\u00111\u0004\b\u0007\u0001\t%i\u0002\u0001)A\u0001\u0002\u000b\u0007aDA\u0001B#\ty\"\u0005\u0005\u0002\u0010A%\u0011\u0011\u0005\u0005\u0002\b\u001d>$\b.\u001b8h!\ty1%\u0003\u0002%!\t\u0019\u0011I\\=)\tq1\u0013f\r\t\u0003\u001f\u001dJ!\u0001\u000b\t\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G)ZS\u0006\f\b\u0003\u001f-J!\u0001\f\t\u0002\u0007%sG/\r\u0003%]I\nbBA\u00183\u001b\u0005\u0001$BA\u0019\f\u0003\u0019a$o\\8u}%\t\u0011#M\u0003$iU:dG\u0004\u0002\u0010k%\u0011a\u0007E\u0001\u0005\u0019>tw-\r\u0003%]I\n\u0012\u0001B8sS\u001e\u00042AO\u001e\u001b\u001b\u00059\u0011B\u0001\u001f\b\u0005\u001d9UM\u001c\"p_2\fa\u0001P5oSRtDCA A!\rQ\u0004A\u0007\u0005\u0006q\t\u0001\r!O\u0001\u0005u\u0016\u0014x.F\u0001\u001b\u0003\u0011\u0001H.^:\u0015\u0007i)u\tC\u0003G\t\u0001\u0007!$A\u0001y\u0011\u0015AE\u00011\u0001\u001b\u0003\u0005I\u0018!\u0002;j[\u0016\u001cHc\u0001\u000eL\u0019\")a)\u0002a\u00015!)\u0001*\u0002a\u00015\u0001"
)
public class BoolRngFromGenBool implements BoolRng {
   public final GenBool orig;

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

   public Object zero() {
      return this.orig.zero();
   }

   public Object plus(final Object x, final Object y) {
      return this.orig.xor(x, y);
   }

   public Object times(final Object x, final Object y) {
      return this.orig.and(x, y);
   }

   public int zero$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.zero());
   }

   public long zero$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.zero());
   }

   public int plus$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.plus(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.plus(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   public int times$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.times(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   public long times$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.times(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   public BoolRngFromGenBool(final GenBool orig) {
      this.orig = orig;
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
