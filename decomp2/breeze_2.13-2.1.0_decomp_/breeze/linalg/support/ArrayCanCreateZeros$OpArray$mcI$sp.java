package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcI$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcI$sp;
   private final ClassTag evidence$1;

   public int[] apply(final int d) {
      return this.apply$mcI$sp(d);
   }

   public int[] apply$mcI$sp(final int d) {
      return (int[]).MODULE$.fill(d, (JFunction0.mcI.sp)() -> ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcI$sp)).zero$mcI$sp(), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcI$sp(final ClassTag evidence$1, final Semiring evidence$2$mcI$sp) {
      super(evidence$1, evidence$2$mcI$sp);
      this.evidence$2$mcI$sp = evidence$2$mcI$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
