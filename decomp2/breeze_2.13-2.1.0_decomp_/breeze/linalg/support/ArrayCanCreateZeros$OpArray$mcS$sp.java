package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcS$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcS$sp;
   private final ClassTag evidence$1;

   public short[] apply(final int d) {
      return this.apply$mcS$sp(d);
   }

   public short[] apply$mcS$sp(final int d) {
      return (short[]).MODULE$.fill(d, (JFunction0.mcS.sp)() -> ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcS$sp)).zero$mcS$sp(), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcS$sp(final ClassTag evidence$1, final Semiring evidence$2$mcS$sp) {
      super(evidence$1, evidence$2$mcS$sp);
      this.evidence$2$mcS$sp = evidence$2$mcS$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
