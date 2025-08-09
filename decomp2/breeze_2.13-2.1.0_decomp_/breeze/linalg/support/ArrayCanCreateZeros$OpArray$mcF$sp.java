package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcF$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcF$sp;
   private final ClassTag evidence$1;

   public float[] apply(final int d) {
      return this.apply$mcF$sp(d);
   }

   public float[] apply$mcF$sp(final int d) {
      return (float[]).MODULE$.fill(d, (JFunction0.mcF.sp)() -> ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcF$sp)).zero$mcF$sp(), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcF$sp(final ClassTag evidence$1, final Semiring evidence$2$mcF$sp) {
      super(evidence$1, evidence$2$mcF$sp);
      this.evidence$2$mcF$sp = evidence$2$mcF$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
