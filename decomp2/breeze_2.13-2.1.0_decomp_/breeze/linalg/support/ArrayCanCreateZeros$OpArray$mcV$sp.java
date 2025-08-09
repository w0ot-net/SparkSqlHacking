package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcV$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcV$sp;
   private final ClassTag evidence$1;

   public BoxedUnit[] apply(final int d) {
      return this.apply$mcV$sp(d);
   }

   public BoxedUnit[] apply$mcV$sp(final int d) {
      return (BoxedUnit[]).MODULE$.fill(d, (JFunction0.mcV.sp)() -> ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcV$sp)).zero(), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcV$sp(final ClassTag evidence$1, final Semiring evidence$2$mcV$sp) {
      super(evidence$1, evidence$2$mcV$sp);
      this.evidence$2$mcV$sp = evidence$2$mcV$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
