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

public final class MarsagliaGaussian$mcD$sp extends MarsagliaGaussian implements Gaussian$mcD$sp {
   public final Field evidence$1$mcD$sp;
   public final NRoot evidence$2$mcD$sp;
   public final Trig evidence$3$mcD$sp;
   public final Order evidence$4$mcD$sp;
   public final Uniform evidence$5$mcD$sp;
   public final Dist u$mcD$sp;

   public final Dist u$mcD$sp() {
      return this.u$mcD$sp;
   }

   public final Dist u() {
      return this.u$mcD$sp();
   }

   public Dist apply(final double mean, final double stdDev) {
      return this.apply$mcD$sp(mean, stdDev);
   }

   public Dist apply$mcD$sp(final double mean, final double stdDev) {
      return new DistFromGen$mcD$sp((gen) -> BoxesRunTime.boxToDouble($anonfun$apply$4(this, stdDev, mean, gen)));
   }

   public boolean specInstance$() {
      return true;
   }

   private final double loop$2(final Generator gen$2, final double stdDev$4, final double mean$4) {
      double x;
      double s;
      do {
         x = this.u().apply$mcD$sp(gen$2);
         double y = this.u().apply$mcD$sp(gen$2);
         s = this.evidence$1$mcD$sp.plus$mcD$sp(this.evidence$1$mcD$sp.times$mcD$sp(x, x), this.evidence$1$mcD$sp.times$mcD$sp(y, y));
      } while(this.evidence$4$mcD$sp.gteqv$mcD$sp(s, package$.MODULE$.Field().apply(this.evidence$1$mcD$sp).one$mcD$sp()) || this.evidence$4$mcD$sp.eqv$mcD$sp(s, package$.MODULE$.Field().apply(this.evidence$1$mcD$sp).zero$mcD$sp()));

      double scale = this.evidence$1$mcD$sp.times$mcD$sp(stdDev$4, this.evidence$2$mcD$sp.sqrt$mcD$sp(this.evidence$1$mcD$sp.div$mcD$sp(BoxesRunTime.unboxToDouble(LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(package.field$.MODULE$.literalIntMultiplicativeSemigroupOps(-2), BoxesRunTime.boxToDouble(this.evidence$3$mcD$sp.log$mcD$sp(s)), this.evidence$1$mcD$sp)), s)));
      return this.evidence$1$mcD$sp.plus$mcD$sp(this.evidence$1$mcD$sp.times$mcD$sp(x, scale), mean$4);
   }

   // $FF: synthetic method
   public static final double $anonfun$apply$4(final MarsagliaGaussian$mcD$sp $this, final double stdDev$4, final double mean$4, final Generator gen) {
      return $this.loop$2(gen, stdDev$4, mean$4);
   }

   public MarsagliaGaussian$mcD$sp(final Field evidence$1$mcD$sp, final NRoot evidence$2$mcD$sp, final Trig evidence$3$mcD$sp, final Order evidence$4$mcD$sp, final Uniform evidence$5$mcD$sp) {
      super(evidence$1$mcD$sp, evidence$2$mcD$sp, evidence$3$mcD$sp, evidence$4$mcD$sp, evidence$5$mcD$sp);
      this.evidence$1$mcD$sp = evidence$1$mcD$sp;
      this.evidence$2$mcD$sp = evidence$2$mcD$sp;
      this.evidence$3$mcD$sp = evidence$3$mcD$sp;
      this.evidence$4$mcD$sp = evidence$4$mcD$sp;
      this.evidence$5$mcD$sp = evidence$5$mcD$sp;
      this.u$mcD$sp = Dist$.MODULE$.uniform(BoxesRunTime.boxToDouble(evidence$1$mcD$sp.negate$mcD$sp(package$.MODULE$.Field().apply(evidence$1$mcD$sp).one$mcD$sp())), BoxesRunTime.boxToDouble(package$.MODULE$.Field().apply(evidence$1$mcD$sp).one$mcD$sp()), evidence$5$mcD$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
