package org.apache.spark.mllib.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4QAC\u0006\u0002\u0002YA\u0001\"\u000b\u0001\u0003\u0006\u0004%\tA\u000b\u0005\tu\u0001\u0011\t\u0011)A\u0005W!AA\b\u0001BC\u0002\u0013\u0005Q\b\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003?\u0011\u00151\u0005\u0001\"\u0001H\u0011\u0015y\u0005A\"\u0005Q\u0011\u00151\u0006\u0001\"\u0001X\u0011\u00151\u0006\u0001\"\u0001c\u0011\u0015)\u0007\u0001\"\u0011g\u0005Y9UM\\3sC2L'0\u001a3MS:,\u0017M]'pI\u0016d'B\u0001\u0007\u000e\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003\u001d=\tQ!\u001c7mS\nT!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u0002\u0001'\r\u0001q#\b\t\u00031mi\u0011!\u0007\u0006\u00025\u0005)1oY1mC&\u0011A$\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005y1cBA\u0010%\u001d\t\u00013%D\u0001\"\u0015\t\u0011S#\u0001\u0004=e>|GOP\u0005\u00025%\u0011Q%G\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0003F\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002&3\u00059q/Z5hQR\u001cX#A\u0016\u0011\u00051zS\"A\u0017\u000b\u00059j\u0011A\u00027j]\u0006dw-\u0003\u00021[\t1a+Z2u_JD3!\u0001\u001a9!\t\u0019d'D\u00015\u0015\t)t\"\u0001\u0006b]:|G/\u0019;j_:L!a\u000e\u001b\u0003\u000bMKgnY3\"\u0003e\nQ!\r\u00181]A\n\u0001b^3jO\"$8\u000f\t\u0015\u0004\u0005IB\u0014!C5oi\u0016\u00148-\u001a9u+\u0005q\u0004C\u0001\r@\u0013\t\u0001\u0015D\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0007I\u0012\u0015%A\"\u0002\u000bAr\u0003H\f\u0019\u0002\u0015%tG/\u001a:dKB$\b\u0005K\u0002\u0005e\t\u000ba\u0001P5oSRtDc\u0001%K\u0019B\u0011\u0011\nA\u0007\u0002\u0017!)\u0011&\u0002a\u0001W!\u001a!J\r\u001d\t\u000bq*\u0001\u0019\u0001 )\u00071\u0013$\tK\u0002\u0006ea\nA\u0002\u001d:fI&\u001cG\u000fU8j]R$BAP)T+\")!K\u0002a\u0001W\u0005QA-\u0019;b\u001b\u0006$(/\u001b=\t\u000bQ3\u0001\u0019A\u0016\u0002\u0019],\u0017n\u001a5u\u001b\u0006$(/\u001b=\t\u000bq2\u0001\u0019\u0001 \u0002\u000fA\u0014X\rZ5diR\u0011\u0001L\u0018\t\u00043rsT\"\u0001.\u000b\u0005m{\u0011a\u0001:eI&\u0011QL\u0017\u0002\u0004%\u0012#\u0005\"B0\b\u0001\u0004\u0001\u0017\u0001\u0003;fgR$\u0015\r^1\u0011\u0007ec6\u0006K\u0002\bea\"\"AP2\t\u000b}C\u0001\u0019A\u0016)\u0007!\u0011\u0004(\u0001\u0005u_N#(/\u001b8h)\u00059\u0007C\u00015m\u001d\tI'\u000e\u0005\u0002!3%\u00111.G\u0001\u0007!J,G-\u001a4\n\u00055t'AB*ue&twM\u0003\u0002l3!\u001a\u0001A\r\""
)
public abstract class GeneralizedLinearModel implements Serializable {
   private final Vector weights;
   private final double intercept;

   public Vector weights() {
      return this.weights;
   }

   public double intercept() {
      return this.intercept;
   }

   public abstract double predictPoint(final Vector dataMatrix, final Vector weightMatrix, final double intercept);

   public RDD predict(final RDD testData) {
      Vector localWeights = this.weights();
      Broadcast bcWeights = testData.context().broadcast(localWeights, .MODULE$.apply(Vector.class));
      double localIntercept = this.intercept();
      return testData.mapPartitions((iter) -> {
         Vector w = (Vector)bcWeights.value();
         return iter.map((v) -> BoxesRunTime.boxToDouble($anonfun$predict$2(this, w, localIntercept, v)));
      }, testData.mapPartitions$default$2(), .MODULE$.Double());
   }

   public double predict(final Vector testData) {
      return this.predictPoint(testData, this.weights(), this.intercept());
   }

   public String toString() {
      String var10000 = this.getClass().getName();
      return var10000 + ": intercept = " + this.intercept() + ", numFeatures = " + this.weights().size();
   }

   // $FF: synthetic method
   public static final double $anonfun$predict$2(final GeneralizedLinearModel $this, final Vector w$1, final double localIntercept$1, final Vector v) {
      return $this.predictPoint(v, w$1, localIntercept$1);
   }

   public GeneralizedLinearModel(final Vector weights, final double intercept) {
      this.weights = weights;
      this.intercept = intercept;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
