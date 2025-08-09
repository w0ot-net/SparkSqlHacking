package org.apache.spark.ml.ann;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u000b=AQA\u0007\u0001\u0005\u0002qAQA\b\u0001\u0005B}AQA\n\u0001\u0005B}\u0011qbU5h[>LGMR;oGRLwN\u001c\u0006\u0003\r\u001d\t1!\u00198o\u0015\tA\u0011\"\u0001\u0002nY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xmE\u0002\u0001!Y\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007CA\f\u0019\u001b\u0005)\u0011BA\r\u0006\u0005I\t5\r^5wCRLwN\u001c$v]\u000e$\u0018n\u001c8\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!\b\t\u0003/\u0001\tA!\u001a<bYV\t\u0001\u0005\u0005\u0003\u0012C\r\u001a\u0013B\u0001\u0012\u0013\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u0012I%\u0011QE\u0005\u0002\u0007\t>,(\r\\3\u0002\u0015\u0011,'/\u001b<bi&4X\r"
)
public class SigmoidFunction implements ActivationFunction {
   public Function1 eval() {
      return (JFunction1.mcDD.sp)(x) -> (double)1.0F / ((double)1 + .MODULE$.exp(-x));
   }

   public Function1 derivative() {
      return (JFunction1.mcDD.sp)(z) -> ((double)1 - z) * z;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
