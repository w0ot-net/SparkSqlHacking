package spire.random;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.Field;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.LeftModule;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\bESN$\u0018J\\:uC:\u001cWm]\u001c\u000b\u0005\u00151\u0011A\u0002:b]\u0012|WNC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011\u0011CE\u0007\u0002\t%\u00111\u0003\u0002\u0002\u000f\t&\u001cH/\u00138ti\u0006t7-Z:7\u0003\u0019!\u0013N\\5uIQ\ta\u0003\u0005\u0002\f/%\u0011\u0001\u0004\u0004\u0002\u0005+:LG/A\u0006wK\u000e$xN]*qC\u000e,WcA\u000e(eQ\u0019A\u0004\u000e#\u0011\tu\u0001#\u0005M\u0007\u0002=)\u0011qDB\u0001\bC2<WM\u0019:b\u0013\t\tcDA\u0006WK\u000e$xN]*qC\u000e,\u0007cA\t$K%\u0011A\u0005\u0002\u0002\u0005\t&\u001cH\u000f\u0005\u0002'O1\u0001A!\u0002\u0015\u0003\u0005\u0004I#!\u0001,\u0012\u0005)j\u0003CA\u0006,\u0013\taCBA\u0004O_RD\u0017N\\4\u0011\u0005-q\u0013BA\u0018\r\u0005\r\te.\u001f\t\u0004#\r\n\u0004C\u0001\u00143\t\u0015\u0019$A1\u0001*\u0005\u0005Y\u0005\"B\u001b\u0003\u0001\b1\u0014aA3wcA\u0019q'Q\u0019\u000f\u0005azdBA\u001d?\u001d\tQT(D\u0001<\u0015\ta\u0004\"\u0001\u0004=e>|GOP\u0005\u0002\u000f%\u0011qDB\u0005\u0003\u0001z\tq\u0001]1dW\u0006<W-\u0003\u0002C\u0007\n\u0011Q)\u001d\u0006\u0003\u0001zAQ!\u0012\u0002A\u0004\u0019\u000b1!\u001a<3!\u0011i\u0002%J\u0019"
)
public interface DistInstances7 extends DistInstances6 {
   // $FF: synthetic method
   static VectorSpace vectorSpace$(final DistInstances7 $this, final Eq ev1, final VectorSpace ev2) {
      return $this.vectorSpace(ev1, ev2);
   }

   default VectorSpace vectorSpace(final Eq ev1, final VectorSpace ev2) {
      return new DistVectorSpace(ev2, ev1) {
         private final VectorSpace ev2$5;
         private final Eq ev1$4;

         public Field scalar() {
            return DistVectorSpace.scalar$(this);
         }

         public Dist divr(final Dist v, final Dist k) {
            return DistVectorSpace.divr$(this, v, k);
         }

         public Field scalar$mcD$sp() {
            return VectorSpace.scalar$mcD$sp$(this);
         }

         public Field scalar$mcF$sp() {
            return VectorSpace.scalar$mcF$sp$(this);
         }

         public Field scalar$mcI$sp() {
            return VectorSpace.scalar$mcI$sp$(this);
         }

         public Field scalar$mcJ$sp() {
            return VectorSpace.scalar$mcJ$sp$(this);
         }

         public Object divr$mcD$sp(final Object v, final double f) {
            return VectorSpace.divr$mcD$sp$(this, v, f);
         }

         public Object divr$mcF$sp(final Object v, final float f) {
            return VectorSpace.divr$mcF$sp$(this, v, f);
         }

         public Object divr$mcI$sp(final Object v, final int f) {
            return VectorSpace.divr$mcI$sp$(this, v, f);
         }

         public Object divr$mcJ$sp(final Object v, final long f) {
            return VectorSpace.divr$mcJ$sp$(this, v, f);
         }

         public Dist zero() {
            return DistCModule.zero$(this);
         }

         public Dist plus(final Dist x, final Dist y) {
            return DistCModule.plus$(this, x, y);
         }

         public Dist negate(final Dist x) {
            return DistCModule.negate$(this, x);
         }

         public Dist minus(final Dist x, final Dist y) {
            return DistCModule.minus$(this, x, y);
         }

         public Dist timesl(final Dist k, final Dist v) {
            return DistCModule.timesl$(this, k, v);
         }

         public Dist timesr(final Dist v, final Dist k) {
            return DistCModule.timesr$(this, v, k);
         }

         public Object timesr$mcD$sp(final Object v, final double r) {
            return CModule.timesr$mcD$sp$(this, v, r);
         }

         public Object timesr$mcF$sp(final Object v, final float r) {
            return CModule.timesr$mcF$sp$(this, v, r);
         }

         public Object timesr$mcI$sp(final Object v, final int r) {
            return CModule.timesr$mcI$sp$(this, v, r);
         }

         public Object timesr$mcJ$sp(final Object v, final long r) {
            return CModule.timesr$mcJ$sp$(this, v, r);
         }

         public Object timesl$mcD$sp(final double r, final Object v) {
            return LeftModule.timesl$mcD$sp$(this, r, v);
         }

         public Object timesl$mcF$sp(final float r, final Object v) {
            return LeftModule.timesl$mcF$sp$(this, r, v);
         }

         public Object timesl$mcI$sp(final int r, final Object v) {
            return LeftModule.timesl$mcI$sp$(this, r, v);
         }

         public Object timesl$mcJ$sp(final long r, final Object v) {
            return LeftModule.timesl$mcJ$sp$(this, r, v);
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

         public VectorSpace alg() {
            return this.ev2$5;
         }

         public Eq eqK() {
            return this.ev1$4;
         }

         public {
            this.ev2$5 = ev2$5;
            this.ev1$4 = ev1$4;
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            CModule.$init$(this);
            DistCModule.$init$(this);
            VectorSpace.$init$(this);
            DistVectorSpace.$init$(this);
         }
      };
   }

   static void $init$(final DistInstances7 $this) {
   }
}
