package org.apache.spark.mllib.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.SparseMatrix;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A3AAB\u0004\u0001%!A\u0011\u0004\u0001BC\u0002\u0013\u0005!\u0004\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003\u001c\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u0015\u0001\u0004\u0001\"\u0001E\u0005\r\u00016)\u0011\u0006\u0003\u0011%\tqAZ3biV\u0014XM\u0003\u0002\u000b\u0017\u0005)Q\u000e\u001c7jE*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g-A\u0001l+\u0005Y\u0002C\u0001\u000b\u001d\u0013\tiRCA\u0002J]RD3!A\u0010&!\t\u00013%D\u0001\"\u0015\t\u00113\"\u0001\u0006b]:|G/\u0019;j_:L!\u0001J\u0011\u0003\u000bMKgnY3\"\u0003\u0019\nQ!\r\u00185]A\n!a\u001b\u0011)\u0007\tyR%\u0001\u0004=S:LGO\u0010\u000b\u0003W5\u0002\"\u0001\f\u0001\u000e\u0003\u001dAQ!G\u0002A\u0002mA3!L\u0010&Q\r\u0019q$J\u0001\u0004M&$HC\u0001\u001a6!\ta3'\u0003\u00025\u000f\tA\u0001kQ!N_\u0012,G\u000eC\u00037\t\u0001\u0007q'A\u0004t_V\u00148-Z:\u0011\u0007aZT(D\u0001:\u0015\tQ4\"A\u0002sI\u0012L!\u0001P\u001d\u0003\u0007I#E\t\u0005\u0002?\u00036\tqH\u0003\u0002A\u0013\u00051A.\u001b8bY\u001eL!AQ \u0003\rY+7\r^8sQ\r!q$\n\u000b\u0003e\u0015CQAN\u0003A\u0002\u0019\u00032a\u0012'>\u001b\u0005A%BA%K\u0003\u0011Q\u0017M^1\u000b\u0005-[\u0011aA1qS&\u0011Q\n\u0013\u0002\b\u0015\u00064\u0018M\u0015#EQ\r)q$\n\u0015\u0004\u0001})\u0003"
)
public class PCA {
   private final int k;

   public int k() {
      return this.k;
   }

   public PCAModel fit(final RDD sources) {
      int numFeatures = ((Vector)sources.first()).size();
      .MODULE$.require(this.k() <= numFeatures, () -> "source vector size " + numFeatures + " must be no less than k=" + this.k());
      RowMatrix var10000;
      if (numFeatures > 65535) {
         SummarizerBuffer summary = Statistics$.MODULE$.colStats(sources.map((x$1) -> new Tuple2(x$1, BoxesRunTime.boxToDouble((double)1.0F)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), new scala.collection.immutable..colon.colon("mean", scala.collection.immutable.Nil..MODULE$));
         Vector mean = Vectors$.MODULE$.fromML(summary.mean());
         RDD meanCenteredRdd = sources.map((row) -> {
            BLAS$.MODULE$.axpy((double)-1.0F, mean, row);
            return row;
         }, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         var10000 = new RowMatrix(meanCenteredRdd);
      } else {
         .MODULE$.require(PCAUtil$.MODULE$.memoryCost(this.k(), numFeatures) < 2147483647L, () -> "The param k and numFeatures is too large for SVD computation. Try reducing the parameter k for PCA, or reduce the input feature vector dimension to make this tractable.");
         var10000 = new RowMatrix(sources);
      }

      RowMatrix mat = var10000;
      Tuple2 var11 = mat.computePrincipalComponentsAndExplainedVariance(this.k());
      if (var11 != null) {
         Matrix pc = (Matrix)var11._1();
         Vector explainedVariance = (Vector)var11._2();
         Tuple2 var10 = new Tuple2(pc, explainedVariance);
         Matrix pc = (Matrix)var10._1();
         Vector explainedVariance = (Vector)var10._2();
         DenseMatrix var24;
         if (pc instanceof DenseMatrix) {
            DenseMatrix var18 = (DenseMatrix)pc;
            var24 = var18;
         } else {
            if (!(pc instanceof SparseMatrix)) {
               throw new IllegalArgumentException("Unsupported matrix format. Expected SparseMatrix or DenseMatrix. Instead got: " + pc.getClass());
            }

            SparseMatrix var19 = (SparseMatrix)pc;
            var24 = var19.toDense();
         }

         DenseMatrix densePC = var24;
         DenseVector var25;
         if (explainedVariance instanceof DenseVector) {
            DenseVector var22 = (DenseVector)explainedVariance;
            var25 = var22;
         } else {
            if (!(explainedVariance instanceof SparseVector)) {
               throw new MatchError(explainedVariance);
            }

            SparseVector var23 = (SparseVector)explainedVariance;
            var25 = var23.toDense();
         }

         DenseVector denseExplainedVariance = var25;
         return new PCAModel(this.k(), densePC, denseExplainedVariance);
      } else {
         throw new MatchError(var11);
      }
   }

   public PCAModel fit(final JavaRDD sources) {
      return this.fit(sources.rdd());
   }

   public PCA(final int k) {
      this.k = k;
      .MODULE$.require(k > 0, () -> "Number of principal components must be positive but got " + this.k());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
