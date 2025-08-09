package spire.math;

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
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\rq\u0003C\u0003T\u0001\u0011\rAK\u0001\u000bQ_2Lhn\\7jC2Len\u001d;b]\u000e,7\u000f\r\u0006\u0003\r\u001d\tA!\\1uQ*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Y\u0001C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002'A\u0011A\u0002F\u0005\u0003+5\u0011A!\u00168ji\u0006aqN^3s'\u0016l\u0017N]5oOV\u0011\u0001d\b\u000b\u00053Y\u0002e\nE\u0002\u001b7ui\u0011!B\u0005\u00039\u0015\u0011a\u0003U8ms:|W.[1m\u001fZ,'oU3nSJLgn\u001a\t\u0003=}a\u0001\u0001B\u0005!\u0005\u0001\u0006\t\u0011!b\u0001C\t\t1)\u0005\u0002#KA\u0011AbI\u0005\u0003I5\u0011qAT8uQ&tw\r\u0005\u0002\rM%\u0011q%\u0004\u0002\u0004\u0003:L\bfA\u0010*YA\u0011ABK\u0005\u0003W5\u00111b\u001d9fG&\fG.\u001b>fIF*1%\f\u00181_9\u0011ABL\u0005\u0003_5\ta\u0001R8vE2,\u0017\u0007\u0002\u00132k9q!AM\u001b\u000e\u0003MR!\u0001N\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0001bB\u001c\u0003\u0003\u0003\u0005\u001d\u0001O\u0001\fKZLG-\u001a8dK\u00122T\u0007E\u0002:{uq!AO\u001e\u000e\u0003\u001dI!\u0001P\u0004\u0002\u000fA\f7m[1hK&\u0011ah\u0010\u0002\t\u00072\f7o\u001d+bO*\u0011Ah\u0002\u0005\b\u0003\n\t\t\u0011q\u0001C\u0003-)g/\u001b3f]\u000e,GE\u000e\u001c\u0011\u0007\r[UD\u0004\u0002E\u0013:\u0011Qi\u0012\b\u0003e\u0019K\u0011\u0001C\u0005\u0003\u0011\u001e\tq!\u00197hK\n\u0014\u0018-\u0003\u0002=\u0015*\u0011\u0001jB\u0005\u0003\u00196\u0013\u0001bU3nSJLgn\u001a\u0006\u0003y)Cqa\u0014\u0002\u0002\u0002\u0003\u000f\u0001+A\u0006fm&$WM\\2fIY:\u0004cA\"R;%\u0011!+\u0014\u0002\u0003\u000bF\f!!Z9\u0016\u0005USF\u0003\u0002,_C\u0012\u00042AG,Z\u0013\tAVA\u0001\u0007Q_2Lhn\\7jC2,\u0015\u000f\u0005\u0002\u001f5\u0012I\u0001e\u0001Q\u0001\u0002\u0003\u0015\r!\t\u0015\u00045&b\u0016'B\u0012.]u{\u0013\u0007\u0002\u00132k9AqaX\u0002\u0002\u0002\u0003\u000f\u0001-A\u0006fm&$WM\\2fIYB\u0004cA\u001d>3\"9!mAA\u0001\u0002\b\u0019\u0017aC3wS\u0012,gnY3%me\u00022aQ&Z\u0011\u001d)7!!AA\u0004\u0019\f1\"\u001a<jI\u0016t7-\u001a\u00138aA\u00191)U-"
)
public interface PolynomialInstances0 {
   // $FF: synthetic method
   static PolynomialOverSemiring overSemiring$(final PolynomialInstances0 $this, final ClassTag evidence$65, final Semiring evidence$66, final Eq evidence$67) {
      return $this.overSemiring(evidence$65, evidence$66, evidence$67);
   }

   default PolynomialOverSemiring overSemiring(final ClassTag evidence$65, final Semiring evidence$66, final Eq evidence$67) {
      return new PolynomialOverSemiring(evidence$66, evidence$67, evidence$65) {
         private final Semiring scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Semiring scalar$mcD$sp() {
            return PolynomialOverSemiring.scalar$mcD$sp$(this);
         }

         public Eq eq$mcD$sp() {
            return PolynomialOverSemiring.eq$mcD$sp$(this);
         }

         public Polynomial zero() {
            return PolynomialOverSemiring.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$mcD$sp$(this, x, y);
         }

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

         public Semiring scalar() {
            return this.scalar;
         }

         public Eq eq() {
            return this.eq;
         }

         public ClassTag ct() {
            return this.ct;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Semiring().apply(evidence$66$1);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$67$1);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$65$1);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialEq eq$(final PolynomialInstances0 $this, final ClassTag evidence$68, final Semiring evidence$69, final Eq evidence$70) {
      return $this.eq(evidence$68, evidence$69, evidence$70);
   }

   default PolynomialEq eq(final ClassTag evidence$68, final Semiring evidence$69, final Eq evidence$70) {
      return new PolynomialEq(evidence$69, evidence$70, evidence$68) {
         private final Semiring scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Semiring scalar$mcD$sp() {
            return PolynomialEq.scalar$mcD$sp$(this);
         }

         public Eq eq$mcD$sp() {
            return PolynomialEq.eq$mcD$sp$(this);
         }

         public boolean eqv(final Polynomial x, final Polynomial y) {
            return PolynomialEq.eqv$(this, x, y);
         }

         public boolean eqv$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialEq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public Semiring scalar() {
            return this.scalar;
         }

         public Eq eq() {
            return this.eq;
         }

         public ClassTag ct() {
            return this.ct;
         }

         public {
            Eq.$init$(this);
            PolynomialEq.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Semiring().apply(evidence$69$1);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$70$1);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$68$1);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialOverSemiring overSemiring$mDc$sp$(final PolynomialInstances0 $this, final ClassTag evidence$65, final Semiring evidence$66, final Eq evidence$67) {
      return $this.overSemiring$mDc$sp(evidence$65, evidence$66, evidence$67);
   }

   default PolynomialOverSemiring overSemiring$mDc$sp(final ClassTag evidence$65, final Semiring evidence$66, final Eq evidence$67) {
      return new PolynomialOverSemiring$mcD$sp(evidence$66, evidence$67, evidence$65) {
         private final Semiring scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial zero() {
            return PolynomialOverSemiring$mcD$sp.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring$mcD$sp.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$mcD$sp$(this, x, y);
         }

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

         public Semiring scalar() {
            return this.scalar$mcD$sp();
         }

         public Eq eq() {
            return this.eq$mcD$sp();
         }

         public ClassTag ct() {
            return this.ct;
         }

         public Semiring scalar$mcD$sp() {
            return this.scalar;
         }

         public Eq eq$mcD$sp() {
            return this.eq;
         }

         public boolean specInstance$() {
            return true;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Semiring().apply(evidence$66$2);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$67$2);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$65$2);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialEq eq$mDc$sp$(final PolynomialInstances0 $this, final ClassTag evidence$68, final Semiring evidence$69, final Eq evidence$70) {
      return $this.eq$mDc$sp(evidence$68, evidence$69, evidence$70);
   }

   default PolynomialEq eq$mDc$sp(final ClassTag evidence$68, final Semiring evidence$69, final Eq evidence$70) {
      return new PolynomialEq$mcD$sp(evidence$69, evidence$70, evidence$68) {
         private final Semiring scalar;
         private final Eq eq;
         private final ClassTag ct;

         public boolean eqv(final Polynomial x, final Polynomial y) {
            return PolynomialEq$mcD$sp.eqv$(this, x, y);
         }

         public boolean eqv$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialEq$mcD$sp.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public Semiring scalar() {
            return this.scalar$mcD$sp();
         }

         public Eq eq() {
            return this.eq$mcD$sp();
         }

         public ClassTag ct() {
            return this.ct;
         }

         public Semiring scalar$mcD$sp() {
            return this.scalar;
         }

         public Eq eq$mcD$sp() {
            return this.eq;
         }

         public boolean specInstance$() {
            return true;
         }

         public {
            Eq.$init$(this);
            PolynomialEq.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Semiring().apply(evidence$69$2);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$70$2);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$68$2);
         }
      };
   }

   static void $init$(final PolynomialInstances0 $this) {
   }
}
