package org.apache.spark.mllib.optimization;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.util.MLUtils$;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005U2A!\u0002\u0004\u0001#!Aa\u0003\u0001B\u0001B\u0003%q\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003\u001e\u0001\u0011\u0005\u0011\u0005C\u0003#\u0001\u0011\u00053E\u0001\tM_\u001eL7\u000f^5d\u000fJ\fG-[3oi*\u0011q\u0001C\u0001\r_B$\u0018.\\5{CRLwN\u001c\u0006\u0003\u0013)\tQ!\u001c7mS\nT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\u0002\u0001'\t\u0001!\u0003\u0005\u0002\u0014)5\ta!\u0003\u0002\u0016\r\tAqI]1eS\u0016tG/\u0001\u0006ok6\u001cE.Y:tKN\u0004\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u00111!\u00138u\u0003\u0019a\u0014N\\5u}Q\u0011q\u0004\t\t\u0003'\u0001AQA\u0006\u0002A\u0002]!\u0012aH\u0001\bG>l\u0007/\u001e;f)\u0015!seL\u00194!\tAR%\u0003\u0002'3\t1Ai\\;cY\u0016DQ\u0001\u000b\u0003A\u0002%\nA\u0001Z1uCB\u0011!&L\u0007\u0002W)\u0011A\u0006C\u0001\u0007Y&t\u0017\r\\4\n\u00059Z#A\u0002,fGR|'\u000fC\u00031\t\u0001\u0007A%A\u0003mC\n,G\u000eC\u00033\t\u0001\u0007\u0011&A\u0004xK&<\u0007\u000e^:\t\u000bQ\"\u0001\u0019A\u0015\u0002\u0017\r,Xn\u0012:bI&,g\u000e\u001e"
)
public class LogisticGradient extends Gradient {
   private final int numClasses;

   public double compute(final Vector data, final double label, final Vector weights, final Vector cumGradient) {
      int dataSize = data.size();
      .MODULE$.require(weights.size() % dataSize == 0 && this.numClasses == weights.size() / dataSize + 1);
      int var9 = this.numClasses;
      switch (var9) {
         case 2:
            double margin = (double)-1.0F * BLAS$.MODULE$.dot(data, weights);
            double multiplier = (double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp(margin)) - label;
            BLAS$.MODULE$.axpy(multiplier, data, cumGradient);
            if (label > (double)0) {
               return MLUtils$.MODULE$.log1pExp(margin);
            }

            return MLUtils$.MODULE$.log1pExp(margin) - margin;
         default:
            if (weights instanceof DenseVector var16) {
               double[] weightsArray = var16.values();
               if (cumGradient instanceof DenseVector var19) {
                  double[] cumGradientArray = var19.values();
                  DoubleRef marginY = DoubleRef.create((double)0.0F);
                  DoubleRef maxMargin = DoubleRef.create(Double.NEGATIVE_INFINITY);
                  IntRef maxMarginIndex = IntRef.create(0);
                  double[] margins = (double[])scala.Array..MODULE$.tabulate(this.numClasses - 1, (JFunction1.mcDI.sp)(i) -> {
                     DoubleRef margin = DoubleRef.create((double)0.0F);
                     data.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> margin.elem += value * weightsArray[i * dataSize + index]);
                     if (i == (int)label - 1) {
                        marginY.elem = margin.elem;
                     }

                     if (margin.elem > maxMargin.elem) {
                        maxMargin.elem = margin.elem;
                        maxMarginIndex.elem = i;
                     }

                     return margin.elem;
                  }, scala.reflect.ClassTag..MODULE$.Double());
                  DoubleRef temp = DoubleRef.create((double)0.0F);
                  if (maxMargin.elem > (double)0) {
                     scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.numClasses - 1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
                        margins[i] -= maxMargin.elem;
                        if (i == maxMarginIndex.elem) {
                           temp.elem += scala.math.package..MODULE$.exp(-maxMargin.elem);
                        } else {
                           temp.elem += scala.math.package..MODULE$.exp(margins[i]);
                        }
                     });
                  } else {
                     scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.numClasses - 1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> temp.elem += scala.math.package..MODULE$.exp(margins[i]));
                  }

                  double sum = temp.elem;
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.numClasses - 1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
                     double multiplier = scala.math.package..MODULE$.exp(margins[i]) / (sum + (double)1.0F) - (label != (double)0.0F && label == (double)(i + 1) ? (double)1.0F : (double)0.0F);
                     data.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> {
                        int var8 = i * dataSize + index;
                        cumGradientArray[var8] += multiplier * value;
                     });
                  });
                  double loss = label > (double)0.0F ? scala.math.package..MODULE$.log1p(sum) - marginY.elem : scala.math.package..MODULE$.log1p(sum);
                  return maxMargin.elem > (double)0 ? loss + maxMargin.elem : loss;
               } else {
                  throw new IllegalArgumentException("cumGradient only supports dense vector but got type " + cumGradient.getClass() + ".");
               }
            } else {
               throw new IllegalArgumentException("weights only supports dense vector but got type " + weights.getClass() + ".");
            }
      }
   }

   public LogisticGradient(final int numClasses) {
      this.numClasses = numClasses;
   }

   public LogisticGradient() {
      this(2);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
