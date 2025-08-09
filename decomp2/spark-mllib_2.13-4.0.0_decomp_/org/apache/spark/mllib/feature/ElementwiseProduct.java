package org.apache.spark.mllib.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3Aa\u0002\u0005\u0001'!Aa\u0004\u0001BC\u0002\u0013\u0005q\u0004\u0003\u00050\u0001\t\u0005\t\u0015!\u0003!\u0011\u0015\t\u0004\u0001\"\u00013\u0011\u00159\u0004\u0001\"\u00119\u0011\u0019a\u0004\u0001\"\u0001\r{!1a\t\u0001C\u0001\u0019\u001d\u0013!#\u00127f[\u0016tGo^5tKB\u0013x\u000eZ;di*\u0011\u0011BC\u0001\bM\u0016\fG/\u001e:f\u0015\tYA\"A\u0003nY2L'M\u0003\u0002\u000e\u001d\u0005)1\u000f]1sW*\u0011q\u0002E\u0001\u0007CB\f7\r[3\u000b\u0003E\t1a\u001c:h\u0007\u0001\u00192\u0001\u0001\u000b\u001b!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00111\u0004H\u0007\u0002\u0011%\u0011Q\u0004\u0003\u0002\u0012-\u0016\u001cGo\u001c:Ue\u0006t7OZ8s[\u0016\u0014\u0018AC:dC2Lgn\u001a,fGV\t\u0001\u0005\u0005\u0002\"I5\t!E\u0003\u0002$\u0015\u00051A.\u001b8bY\u001eL!!\n\u0012\u0003\rY+7\r^8sQ\r\tq%\f\t\u0003Q-j\u0011!\u000b\u0006\u0003U1\t!\"\u00198o_R\fG/[8o\u0013\ta\u0013FA\u0003TS:\u001cW-I\u0001/\u0003\u0015\td\u0006\u000e\u00181\u0003-\u00198-\u00197j]\u001e4Vm\u0019\u0011)\u0007\t9S&\u0001\u0004=S:LGO\u0010\u000b\u0003gQ\u0002\"a\u0007\u0001\t\u000by\u0019\u0001\u0019\u0001\u0011)\u0007Q:S\u0006K\u0002\u0004O5\n\u0011\u0002\u001e:b]N4wN]7\u0015\u0005\u0001J\u0004\"\u0002\u001e\u0005\u0001\u0004\u0001\u0013A\u0002<fGR|'\u000fK\u0002\u0005O5\na\u0002\u001e:b]N4wN]7EK:\u001cX\r\u0006\u0002?\tB\u0019QcP!\n\u0005\u00013\"!B!se\u0006L\bCA\u000bC\u0013\t\u0019eC\u0001\u0004E_V\u0014G.\u001a\u0005\u0006\u000b\u0016\u0001\rAP\u0001\u0007m\u0006dW/Z:\u0002\u001fQ\u0014\u0018M\\:g_Jl7\u000b]1sg\u0016$2\u0001S(R!\u0011)\u0012j\u0013 \n\u0005)3\"A\u0002+va2,'\u0007E\u0002\u0016\u007f1\u0003\"!F'\n\u000593\"aA%oi\")\u0001K\u0002a\u0001\u0017\u00069\u0011N\u001c3jG\u0016\u001c\b\"B#\u0007\u0001\u0004q\u0004f\u0001\u0001([\u0001"
)
public class ElementwiseProduct implements VectorTransformer {
   private final Vector scalingVec;

   public RDD transform(final RDD data) {
      return VectorTransformer.transform$(this, (RDD)data);
   }

   public JavaRDD transform(final JavaRDD data) {
      return VectorTransformer.transform$(this, (JavaRDD)data);
   }

   public Vector scalingVec() {
      return this.scalingVec;
   }

   public Vector transform(final Vector vector) {
      .MODULE$.require(vector.size() == this.scalingVec().size(), () -> {
         int var10000 = this.scalingVec().size();
         return "vector sizes do not match: Expected " + var10000 + " but found " + vector.size();
      });
      if (vector instanceof DenseVector var5) {
         Option var6 = DenseVector$.MODULE$.unapply(var5);
         if (!var6.isEmpty()) {
            double[] values = (double[])var6.get();
            double[] newValues = this.transformDense(values);
            return Vectors$.MODULE$.dense(newValues);
         }
      }

      if (vector instanceof SparseVector var9) {
         Option var10 = SparseVector$.MODULE$.unapply(var9);
         if (!var10.isEmpty()) {
            int size = BoxesRunTime.unboxToInt(((Tuple3)var10.get())._1());
            int[] indices = (int[])((Tuple3)var10.get())._2();
            double[] values = (double[])((Tuple3)var10.get())._3();
            Tuple2 var15 = this.transformSparse(indices, values);
            if (var15 != null) {
               int[] newIndices = (int[])var15._1();
               double[] newValues = (double[])var15._2();
               Tuple2 var14 = new Tuple2(newIndices, newValues);
               int[] newIndices = (int[])var14._1();
               double[] newValues = (double[])var14._2();
               return Vectors$.MODULE$.sparse(size, newIndices, newValues);
            }

            throw new MatchError(var15);
         }
      }

      throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + vector.getClass() + ".");
   }

   public double[] transformDense(final double[] values) {
      double[] newValues = (double[])(([D)values).clone();
      int dim = this.scalingVec().size();

      for(int i = 0; i < dim; ++i) {
         newValues[i] *= this.scalingVec().apply(i);
      }

      return newValues;
   }

   public Tuple2 transformSparse(final int[] indices, final double[] values) {
      double[] newValues = (double[])(([D)values).clone();
      int dim = newValues.length;

      for(int i = 0; i < dim; ++i) {
         newValues[i] *= this.scalingVec().apply(indices[i]);
      }

      return new Tuple2(indices, newValues);
   }

   public ElementwiseProduct(final Vector scalingVec) {
      this.scalingVec = scalingVec;
      VectorTransformer.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
