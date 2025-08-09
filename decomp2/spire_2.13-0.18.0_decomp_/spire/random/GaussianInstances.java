package spire.random;

import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import java.math.MathContext;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;
import spire.std.package;

@ScalaSignature(
   bytes = "\u0006\u000513qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f\u0005\u0002!\u0019!C\u0002E!)q\u0005\u0001C\u0002Q!9\u0001\tAI\u0001\n\u0003\t%!E$bkN\u001c\u0018.\u00198J]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007e\u0006tGm\\7\u000b\u0003)\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\u000b\u0011\u000591\u0012BA\f\u0010\u0005\u0011)f.\u001b;\u0002\u000b\u0019dw.\u0019;\u0016\u0003i\u00012a\u0007\u000f\u001f\u001b\u00059\u0011BA\u000f\b\u0005!9\u0015-^:tS\u0006t\u0007C\u0001\b \u0013\t\u0001sBA\u0003GY>\fG/\u0001\u0004e_V\u0014G.Z\u000b\u0002GA\u00191\u0004\b\u0013\u0011\u00059)\u0013B\u0001\u0014\u0010\u0005\u0019!u.\u001e2mK\u0006Q!-[4EK\u000eLW.\u00197\u0015\u0005%2\u0004cA\u000e\u001dUA\u00111f\r\b\u0003YEr!!\f\u0019\u000e\u00039R!aL\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012B\u0001\u001a\u0010\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001N\u001b\u0003\u0015\tKw\rR3dS6\fGN\u0003\u00023\u001f!9q\u0007\u0002I\u0001\u0002\bA\u0014AA7d!\tId(D\u0001;\u0015\tYD(\u0001\u0003nCRD'\"A\u001f\u0002\t)\fg/Y\u0005\u0003\u007fi\u00121\"T1uQ\u000e{g\u000e^3yi\u0006!\"-[4EK\u000eLW.\u00197%I\u00164\u0017-\u001e7uIE*\u0012A\u0011\u0016\u0003q\r[\u0013\u0001\u0012\t\u0003\u000b*k\u0011A\u0012\u0006\u0003\u000f\"\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005%{\u0011AC1o]>$\u0018\r^5p]&\u00111J\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public interface GaussianInstances {
   void spire$random$GaussianInstances$_setter_$float_$eq(final Gaussian x$1);

   void spire$random$GaussianInstances$_setter_$double_$eq(final Gaussian x$1);

   Gaussian float();

   Gaussian double();

   // $FF: synthetic method
   static Gaussian bigDecimal$(final GaussianInstances $this, final MathContext mc) {
      return $this.bigDecimal(mc);
   }

   default Gaussian bigDecimal(final MathContext mc) {
      return new MarsagliaGaussian(package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (NRoot)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), package.bigDecimal$.MODULE$.BigDecimalIsTrig(mc), (Order)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), Uniform$.MODULE$.UniformBigDecimal());
   }

   // $FF: synthetic method
   static MathContext bigDecimal$default$1$(final GaussianInstances $this) {
      return $this.bigDecimal$default$1();
   }

   default MathContext bigDecimal$default$1() {
      return .MODULE$.BigDecimal().defaultMathContext();
   }

   static void $init$(final GaussianInstances $this) {
      $this.spire$random$GaussianInstances$_setter_$float_$eq(new Gaussian$mcF$sp() {
         public Dist apply$mcD$sp(final double mean, final double stdDev) {
            return Gaussian.apply$mcD$sp$(this, mean, stdDev);
         }

         public Dist apply(final float mean, final float stdDev) {
            return this.apply$mcF$sp(mean, stdDev);
         }

         public Dist apply$mcF$sp(final float mean, final float stdDev) {
            return new DistFromGen$mcF$sp((g) -> BoxesRunTime.boxToFloat($anonfun$apply$1(stdDev, mean, g)));
         }

         // $FF: synthetic method
         public static final float $anonfun$apply$1(final float stdDev$1, final float mean$1, final Generator g) {
            return (float)(Ziggurat$.MODULE$.rnor(g) * (double)stdDev$1 + (double)mean$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.spire$random$GaussianInstances$_setter_$double_$eq(new Gaussian$mcD$sp() {
         public Dist apply$mcF$sp(final float mean, final float stdDev) {
            return Gaussian.apply$mcF$sp$(this, mean, stdDev);
         }

         public Dist apply(final double mean, final double stdDev) {
            return this.apply$mcD$sp(mean, stdDev);
         }

         public Dist apply$mcD$sp(final double mean, final double stdDev) {
            return new DistFromGen$mcD$sp((g) -> BoxesRunTime.boxToDouble($anonfun$apply$2(stdDev, mean, g)));
         }

         // $FF: synthetic method
         public static final double $anonfun$apply$2(final double stdDev$2, final double mean$2, final Generator g) {
            return Ziggurat$.MODULE$.rnor(g) * stdDev$2 + mean$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }
}
