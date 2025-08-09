package breeze.linalg.support;

import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public class ArrayCanCreateZeros$OpArray$mcC$sp extends ArrayCanCreateZeros.OpArray {
   public final Semiring evidence$2$mcC$sp;
   private final ClassTag evidence$1;

   public char[] apply(final int d) {
      return this.apply$mcC$sp(d);
   }

   public char[] apply$mcC$sp(final int d) {
      return (char[]).MODULE$.fill(d, (JFunction0.mcC.sp)() -> BoxesRunTime.unboxToChar(((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2$mcC$sp)).zero()), this.breeze$linalg$support$ArrayCanCreateZeros$OpArray$$evidence$1);
   }

   public ArrayCanCreateZeros$OpArray$mcC$sp(final ClassTag evidence$1, final Semiring evidence$2$mcC$sp) {
      super(evidence$1, evidence$2$mcC$sp);
      this.evidence$2$mcC$sp = evidence$2$mcC$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
