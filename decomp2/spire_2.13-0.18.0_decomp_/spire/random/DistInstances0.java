package spire.random;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeSemiring;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\bESN$\u0018J\\:uC:\u001cWm\u001d\u0019\u000b\u0005\u00151\u0011A\u0002:b]\u0012|WNC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0003\u0005\u0002\f'%\u0011A\u0003\u0004\u0002\u0005+:LG/A\u0005d'\u0016l\u0017N]5oOV\u0011qC\f\u000b\u00031]\u00022!G\u0013)\u001d\tQ\"E\u0004\u0002\u001cA9\u0011AdH\u0007\u0002;)\u0011a\u0004C\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dI!!\t\u0004\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0005J\u0001\ba\u0006\u001c7.Y4f\u0015\t\tc!\u0003\u0002'O\tI1iU3nSJLgn\u001a\u0006\u0003G\u0011\u00022!\u000b\u0016-\u001b\u0005!\u0011BA\u0016\u0005\u0005\u0011!\u0015n\u001d;\u0011\u00055rC\u0002\u0001\u0003\u0006_\t\u0011\r\u0001\r\u0002\u0002\u0003F\u0011\u0011\u0007\u000e\t\u0003\u0017IJ!a\r\u0007\u0003\u000f9{G\u000f[5oOB\u00111\"N\u0005\u0003m1\u00111!\u00118z\u0011\u0015A$\u0001q\u0001:\u0003\t)g\u000fE\u0002\u001aK1\u0002"
)
public interface DistInstances0 {
   // $FF: synthetic method
   static CommutativeSemiring cSemiring$(final DistInstances0 $this, final CommutativeSemiring ev) {
      return $this.cSemiring(ev);
   }

   default CommutativeSemiring cSemiring(final CommutativeSemiring ev) {
      return new DistCSemiring(ev) {
         private final CommutativeSemiring ev$1;

         public Dist zero() {
            return DistCSemiring.zero$(this);
         }

         public Dist plus(final Dist x, final Dist y) {
            return DistCSemiring.plus$(this, x, y);
         }

         public Dist times(final Dist x, final Dist y) {
            return DistCSemiring.times$(this, x, y);
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

         public CommutativeSemiring alg() {
            return this.ev$1;
         }

         public {
            this.ev$1 = ev$1;
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            MultiplicativeCommutativeSemigroup.$init$(this);
            DistCSemiring.$init$(this);
         }
      };
   }

   static void $init$(final DistInstances0 $this) {
   }
}
