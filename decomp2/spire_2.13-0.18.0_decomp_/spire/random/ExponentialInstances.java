package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007I1A\f\t\u000f}\u0001!\u0019!C\u0002A\t!R\t\u001f9p]\u0016tG/[1m\u0013:\u001cH/\u00198dKNT!AB\u0004\u0002\rI\fg\u000eZ8n\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0003\u0001-\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0014!\taA#\u0003\u0002\u0016\u001b\t!QK\\5u\u0003\u00151Gn\\1u+\u0005A\u0002cA\r\u001b95\tQ!\u0003\u0002\u001c\u000b\tYQ\t\u001f9p]\u0016tG/[1m!\taQ$\u0003\u0002\u001f\u001b\t)a\t\\8bi\u00061Am\\;cY\u0016,\u0012!\t\t\u00043i\u0011\u0003C\u0001\u0007$\u0013\t!SB\u0001\u0004E_V\u0014G.\u001a"
)
public interface ExponentialInstances {
   void spire$random$ExponentialInstances$_setter_$float_$eq(final Exponential x$1);

   void spire$random$ExponentialInstances$_setter_$double_$eq(final Exponential x$1);

   Exponential float();

   Exponential double();

   static void $init$(final ExponentialInstances $this) {
      $this.spire$random$ExponentialInstances$_setter_$float_$eq(new Exponential$mcF$sp() {
         public Dist apply$mcD$sp(final double rate) {
            return Exponential.apply$mcD$sp$(this, rate);
         }

         public Dist apply(final float rate) {
            return this.apply$mcF$sp(rate);
         }

         public Dist apply$mcF$sp(final float rate) {
            return new DistFromGen$mcF$sp((g) -> BoxesRunTime.boxToFloat($anonfun$apply$1(rate, g)));
         }

         // $FF: synthetic method
         public static final float $anonfun$apply$1(final float rate$1, final Generator g) {
            return (float)(Ziggurat$.MODULE$.rexp(g) / (double)rate$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.spire$random$ExponentialInstances$_setter_$double_$eq(new Exponential$mcD$sp() {
         public Dist apply$mcF$sp(final float rate) {
            return Exponential.apply$mcF$sp$(this, rate);
         }

         public Dist apply(final double rate) {
            return this.apply$mcD$sp(rate);
         }

         public Dist apply$mcD$sp(final double rate) {
            return new DistFromGen$mcD$sp((g) -> BoxesRunTime.boxToDouble($anonfun$apply$2(rate, g)));
         }

         // $FF: synthetic method
         public static final double $anonfun$apply$2(final double rate$2, final Generator g) {
            return Ziggurat$.MODULE$.rexp(g) / rate$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }
}
