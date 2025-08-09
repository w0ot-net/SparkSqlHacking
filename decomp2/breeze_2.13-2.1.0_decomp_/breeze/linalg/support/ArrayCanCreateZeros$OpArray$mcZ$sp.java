package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcZ$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcZ$sp;
   private final ClassTag evidence$1;

   public boolean[] apply(final int d) {
      return this.apply$mcZ$sp(d);
   }

   public boolean[] apply$mcZ$sp(final int d) {
      return (boolean[]).MODULE$.fill(d, (JFunction0.mcZ.sp)() -> BoxesRunTime.unboxToBoolean(((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcZ$sp)).zero()), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcZ$sp(final ClassTag evidence$1, final Semiring evidence$2$mcZ$sp) {
      super(evidence$1, evidence$2$mcZ$sp);
      this.evidence$2$mcZ$sp = evidence$2$mcZ$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
