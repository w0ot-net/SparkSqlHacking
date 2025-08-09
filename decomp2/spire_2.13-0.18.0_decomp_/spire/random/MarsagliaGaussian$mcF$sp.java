package spire.random;

import algebra.ring.Field;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.package$;
import spire.syntax.LiteralIntMultiplicativeSemigroupOps$;
import spire.syntax.package;

public final class MarsagliaGaussian$mcF$sp extends MarsagliaGaussian implements Gaussian$mcF$sp {
   public final Field evidence$1$mcF$sp;
   public final NRoot evidence$2$mcF$sp;
   public final Trig evidence$3$mcF$sp;
   public final Order evidence$4$mcF$sp;
   public final Uniform evidence$5$mcF$sp;
   public final Dist u$mcF$sp;

   public final Dist u$mcF$sp() {
      return this.u$mcF$sp;
   }

   public final Dist u() {
      return this.u$mcF$sp();
   }

   public Dist apply(final float mean, final float stdDev) {
      return this.apply$mcF$sp(mean, stdDev);
   }

   public Dist apply$mcF$sp(final float mean, final float stdDev) {
      return new DistFromGen$mcF$sp((gen) -> BoxesRunTime.boxToFloat($anonfun$apply$5(this, stdDev, mean, gen)));
   }

   public boolean specInstance$() {
      return true;
   }

   private final float loop$3(final Generator gen$3, final float stdDev$5, final float mean$5) {
      float x;
      float s;
      do {
         x = this.u().apply$mcF$sp(gen$3);
         float y = this.u().apply$mcF$sp(gen$3);
         s = this.evidence$1$mcF$sp.plus$mcF$sp(this.evidence$1$mcF$sp.times$mcF$sp(x, x), this.evidence$1$mcF$sp.times$mcF$sp(y, y));
      } while(this.evidence$4$mcF$sp.gteqv$mcF$sp(s, package$.MODULE$.Field().apply(this.evidence$1$mcF$sp).one$mcF$sp()) || this.evidence$4$mcF$sp.eqv$mcF$sp(s, package$.MODULE$.Field().apply(this.evidence$1$mcF$sp).zero$mcF$sp()));

      float scale = this.evidence$1$mcF$sp.times$mcF$sp(stdDev$5, this.evidence$2$mcF$sp.sqrt$mcF$sp(this.evidence$1$mcF$sp.div$mcF$sp(BoxesRunTime.unboxToFloat(LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(package.field$.MODULE$.literalIntMultiplicativeSemigroupOps(-2), BoxesRunTime.boxToFloat(this.evidence$3$mcF$sp.log$mcF$sp(s)), this.evidence$1$mcF$sp)), s)));
      return this.evidence$1$mcF$sp.plus$mcF$sp(this.evidence$1$mcF$sp.times$mcF$sp(x, scale), mean$5);
   }

   // $FF: synthetic method
   public static final float $anonfun$apply$5(final MarsagliaGaussian$mcF$sp $this, final float stdDev$5, final float mean$5, final Generator gen) {
      return $this.loop$3(gen, stdDev$5, mean$5);
   }

   public MarsagliaGaussian$mcF$sp(final Field evidence$1$mcF$sp, final NRoot evidence$2$mcF$sp, final Trig evidence$3$mcF$sp, final Order evidence$4$mcF$sp, final Uniform evidence$5$mcF$sp) {
      super(evidence$1$mcF$sp, evidence$2$mcF$sp, evidence$3$mcF$sp, evidence$4$mcF$sp, evidence$5$mcF$sp);
      this.evidence$1$mcF$sp = evidence$1$mcF$sp;
      this.evidence$2$mcF$sp = evidence$2$mcF$sp;
      this.evidence$3$mcF$sp = evidence$3$mcF$sp;
      this.evidence$4$mcF$sp = evidence$4$mcF$sp;
      this.evidence$5$mcF$sp = evidence$5$mcF$sp;
      this.u$mcF$sp = Dist$.MODULE$.uniform(BoxesRunTime.boxToFloat(evidence$1$mcF$sp.negate$mcF$sp(package$.MODULE$.Field().apply(evidence$1$mcF$sp).one$mcF$sp())), BoxesRunTime.boxToFloat(package$.MODULE$.Field().apply(evidence$1$mcF$sp).one$mcF$sp()), evidence$5$mcF$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
