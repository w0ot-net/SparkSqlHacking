package spire.random;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRing;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.LeftModule;

@ScalaSignature(
   bytes = "\u0006\u0005]2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\bESN$\u0018J\\:uC:\u001cWm\u001d\u001c\u000b\u0005\u00151\u0011A\u0002:b]\u0012|WNC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011\u0011CE\u0007\u0002\t%\u00111\u0003\u0002\u0002\u000f\t&\u001cH/\u00138ti\u0006t7-Z:6\u0003\u0019!\u0013N\\5uIQ\ta\u0003\u0005\u0002\f/%\u0011\u0001\u0004\u0004\u0002\u0005+:LG/\u0001\u0004n_\u0012,H.Z\u000b\u00047\u001d\u0012DC\u0001\u000f5!\u0011i\u0002E\t\u0019\u000e\u0003yQ!a\b\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011E\b\u0002\b\u00076{G-\u001e7f!\r\t2%J\u0005\u0003I\u0011\u0011A\u0001R5tiB\u0011ae\n\u0007\u0001\t\u0015A#A1\u0001*\u0005\u00051\u0016C\u0001\u0016.!\tY1&\u0003\u0002-\u0019\t9aj\u001c;iS:<\u0007CA\u0006/\u0013\tyCBA\u0002B]f\u00042!E\u00122!\t1#\u0007B\u00034\u0005\t\u0007\u0011FA\u0001L\u0011\u0015)$\u0001q\u00017\u0003\r)gO\r\t\u0005;\u0001*\u0013\u0007"
)
public interface DistInstances6 extends DistInstances5 {
   // $FF: synthetic method
   static CModule module$(final DistInstances6 $this, final CModule ev2) {
      return $this.module(ev2);
   }

   default CModule module(final CModule ev2) {
      return new DistCModule(ev2) {
         private final CModule ev2$4;

         public CommutativeRing scalar() {
            return DistCModule.scalar$(this);
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

         public CommutativeRing scalar$mcD$sp() {
            return CModule.scalar$mcD$sp$(this);
         }

         public CommutativeRing scalar$mcF$sp() {
            return CModule.scalar$mcF$sp$(this);
         }

         public CommutativeRing scalar$mcI$sp() {
            return CModule.scalar$mcI$sp$(this);
         }

         public CommutativeRing scalar$mcJ$sp() {
            return CModule.scalar$mcJ$sp$(this);
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

         public CModule alg() {
            return this.ev2$4;
         }

         public {
            this.ev2$4 = ev2$4;
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            CModule.$init$(this);
            DistCModule.$init$(this);
         }
      };
   }

   static void $init$(final DistInstances6 $this) {
   }
}
