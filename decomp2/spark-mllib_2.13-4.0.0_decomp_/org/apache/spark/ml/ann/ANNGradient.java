package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.mllib.optimization.Gradient;
import scala.MatchError;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005y2Q!\u0002\u0004\u0001\rAA\u0001\"\u0007\u0001\u0003\u0002\u0003\u0006Ia\u0007\u0005\t?\u0001\u0011\t\u0011)A\u0005A!)1\u0005\u0001C\u0001I!)\u0001\u0006\u0001C!S\tY\u0011I\u0014(He\u0006$\u0017.\u001a8u\u0015\t9\u0001\"A\u0002b]:T!!\u0003\u0006\u0002\u00055d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0014\u0005\u0001\t\u0002C\u0001\n\u0018\u001b\u0005\u0019\"B\u0001\u000b\u0016\u00031y\u0007\u000f^5nSj\fG/[8o\u0015\t1\"\"A\u0003nY2L'-\u0003\u0002\u0019'\tAqI]1eS\u0016tG/\u0001\u0005u_B|Gn\\4z\u0007\u0001\u0001\"\u0001H\u000f\u000e\u0003\u0019I!A\b\u0004\u0003\u0011Q{\u0007o\u001c7pOf\f1\u0002Z1uCN#\u0018mY6feB\u0011A$I\u0005\u0003E\u0019\u00111\u0002R1uCN#\u0018mY6fe\u00061A(\u001b8jiz\"2!\n\u0014(!\ta\u0002\u0001C\u0003\u001a\u0007\u0001\u00071\u0004C\u0003 \u0007\u0001\u0007\u0001%A\u0004d_6\u0004X\u000f^3\u0015\u000b)\u0002\u0004H\u000f\u001f\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\r\u0011{WO\u00197f\u0011\u0015\tD\u00011\u00013\u0003\u0011!\u0017\r^1\u0011\u0005M2T\"\u0001\u001b\u000b\u0005U*\u0012A\u00027j]\u0006dw-\u0003\u00028i\t1a+Z2u_JDQ!\u000f\u0003A\u0002)\nQ\u0001\\1cK2DQa\u000f\u0003A\u0002I\nqa^3jO\"$8\u000fC\u0003>\t\u0001\u0007!'A\u0006dk6<%/\u00193jK:$\b"
)
public class ANNGradient extends Gradient {
   private final Topology topology;
   private final DataStacker dataStacker;

   public double compute(final Vector data, final double label, final Vector weights, final Vector cumGradient) {
      Tuple3 var8 = this.dataStacker.unstack(VectorImplicits$.MODULE$.mllibVectorToMLVector(data));
      if (var8 != null) {
         DenseMatrix input = (DenseMatrix)var8._1();
         DenseMatrix target = (DenseMatrix)var8._2();
         int realBatchSize = BoxesRunTime.unboxToInt(var8._3());
         Tuple3 var7 = new Tuple3(input, target, BoxesRunTime.boxToInteger(realBatchSize));
         DenseMatrix input = (DenseMatrix)var7._1();
         DenseMatrix target = (DenseMatrix)var7._2();
         int realBatchSize = BoxesRunTime.unboxToInt(var7._3());
         TopologyModel model = this.topology.model(VectorImplicits$.MODULE$.mllibVectorToMLVector(weights));
         return model.computeGradient(input, target, VectorImplicits$.MODULE$.mllibVectorToMLVector(cumGradient), realBatchSize);
      } else {
         throw new MatchError(var8);
      }
   }

   public ANNGradient(final Topology topology, final DataStacker dataStacker) {
      this.topology = topology;
      this.dataStacker = dataStacker;
   }
}
