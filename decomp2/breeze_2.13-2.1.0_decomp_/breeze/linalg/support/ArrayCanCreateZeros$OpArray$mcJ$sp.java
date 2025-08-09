package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcJ$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcJ$sp;
   private final ClassTag evidence$1;

   public long[] apply(final int d) {
      return this.apply$mcJ$sp(d);
   }

   public long[] apply$mcJ$sp(final int d) {
      return (long[]).MODULE$.fill(d, (JFunction0.mcJ.sp)() -> ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcJ$sp)).zero$mcJ$sp(), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcJ$sp(final ClassTag evidence$1, final Semiring evidence$2$mcJ$sp) {
      super(evidence$1, evidence$2$mcJ$sp);
      this.evidence$2$mcJ$sp = evidence$2$mcJ$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
