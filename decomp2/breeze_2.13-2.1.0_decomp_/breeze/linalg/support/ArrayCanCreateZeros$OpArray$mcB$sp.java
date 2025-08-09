package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcB$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcB$sp;
   private final ClassTag evidence$1;

   public byte[] apply(final int d) {
      return this.apply$mcB$sp(d);
   }

   public byte[] apply$mcB$sp(final int d) {
      return (byte[]).MODULE$.fill(d, (JFunction0.mcB.sp)() -> BoxesRunTime.unboxToByte(((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcB$sp)).zero()), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcB$sp(final ClassTag evidence$1, final Semiring evidence$2$mcB$sp) {
      super(evidence$1, evidence$2$mcB$sp);
      this.evidence$2$mcB$sp = evidence$2$mcB$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
