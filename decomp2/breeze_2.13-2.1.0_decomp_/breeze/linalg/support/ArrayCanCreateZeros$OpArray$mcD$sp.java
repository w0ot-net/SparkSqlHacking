package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcD$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcD$sp;
   private final ClassTag evidence$1;

   public double[] apply(final int d) {
      return this.apply$mcD$sp(d);
   }

   public double[] apply$mcD$sp(final int d) {
      return (double[]).MODULE$.fill(d, (JFunction0.mcD.sp)() -> ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcD$sp)).zero$mcD$sp(), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcD$sp(final ClassTag evidence$1, final Semiring evidence$2$mcD$sp) {
      super(evidence$1, evidence$2$mcD$sp);
      this.evidence$2$mcD$sp = evidence$2$mcD$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
