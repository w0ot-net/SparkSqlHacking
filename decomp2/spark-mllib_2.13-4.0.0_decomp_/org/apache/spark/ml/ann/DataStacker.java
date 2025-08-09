package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

@ScalaSignature(
   bytes = "\u0006\u0005a3Qa\u0002\u0005\u0001\u0011IA\u0001B\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\tU\u0001\u0011\t\u0011)A\u0005O!A1\u0006\u0001B\u0001B\u0003%q\u0005C\u0003-\u0001\u0011\u0005Q\u0006C\u00034\u0001\u0011\u0005A\u0007C\u0003L\u0001\u0011\u0005AJA\u0006ECR\f7\u000b^1dW\u0016\u0014(BA\u0005\u000b\u0003\r\tgN\u001c\u0006\u0003\u00171\t!!\u001c7\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c2\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!d\t\b\u00037\u0005r!\u0001\b\u0011\u000e\u0003uQ!AH\u0010\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AF\u0005\u0003EU\tq\u0001]1dW\u0006<W-\u0003\u0002%K\ta1+\u001a:jC2L'0\u00192mK*\u0011!%F\u0001\ngR\f7m[*ju\u0016\u0004\"\u0001\u0006\u0015\n\u0005%*\"aA%oi\u0006I\u0011N\u001c9viNK'0Z\u0001\u000b_V$\b/\u001e;TSj,\u0017A\u0002\u001fj]&$h\b\u0006\u0003/aE\u0012\u0004CA\u0018\u0001\u001b\u0005A\u0001\"\u0002\u0014\u0005\u0001\u00049\u0003\"\u0002\u0016\u0005\u0001\u00049\u0003\"B\u0016\u0005\u0001\u00049\u0013!B:uC\u000e\\GCA\u001bH!\r1\u0014hO\u0007\u0002o)\u0011\u0001\bD\u0001\u0004e\u0012$\u0017B\u0001\u001e8\u0005\r\u0011F\t\u0012\t\u0005)qr\u0014)\u0003\u0002>+\t1A+\u001e9mKJ\u0002\"\u0001F \n\u0005\u0001+\"A\u0002#pk\ndW\r\u0005\u0002C\u000b6\t1I\u0003\u0002E\u0015\u00051A.\u001b8bY\u001eL!AR\"\u0003\rY+7\r^8s\u0011\u0015AU\u00011\u0001J\u0003\u0011!\u0017\r^1\u0011\u0007YJ$\n\u0005\u0003\u0015y\u0005\u000b\u0015aB;ogR\f7m\u001b\u000b\u0003\u001b^\u0003R\u0001\u0006(Q!\u001eJ!aT\u000b\u0003\rQ+\b\u000f\\34!\r\tVKP\u0007\u0002%*\u0011Ai\u0015\u0006\u0002)\u00061!M]3fu\u0016L!A\u0016*\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\u0005\u0006\u0011\u001a\u0001\r!\u0011"
)
public class DataStacker implements Serializable {
   private final int stackSize;
   private final int inputSize;
   private final int outputSize;

   public RDD stack(final RDD data) {
      RDD stackedData = this.stackSize == 1 ? data.map((v) -> new Tuple2(BoxesRunTime.boxToDouble((double)0.0F), .MODULE$.fromBreeze(breeze.linalg.DenseVector..MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new DenseVector[]{((Vector)v._1()).asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), ((Vector)v._2()).asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())})), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)) : data.mapPartitions((it) -> it.grouped(this.stackSize).map((seq) -> {
            int size = seq.size();
            double[] bigVector = new double[this.inputSize * size + this.outputSize * size];
            IntRef i = IntRef.create(0);
            seq.foreach((x0$1) -> {
               $anonfun$stack$4(this, bigVector, i, size, x0$1);
               return BoxedUnit.UNIT;
            });
            return new Tuple2(BoxesRunTime.boxToDouble((double)0.0F), .MODULE$.dense(bigVector));
         }), data.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return stackedData;
   }

   public Tuple3 unstack(final Vector data) {
      double[] arrData = data.toArray();
      int realStackSize = arrData.length / (this.inputSize + this.outputSize);
      DenseMatrix input = new DenseMatrix.mcD.sp(this.inputSize, realStackSize, arrData);
      DenseMatrix target = new DenseMatrix.mcD.sp(this.outputSize, realStackSize, arrData, this.inputSize * realStackSize);
      return new Tuple3(input, target, BoxesRunTime.boxToInteger(realStackSize));
   }

   // $FF: synthetic method
   public static final void $anonfun$stack$4(final DataStacker $this, final double[] bigVector$1, final IntRef i$1, final int size$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Vector in = (Vector)x0$1._1();
         Vector out = (Vector)x0$1._2();
         System.arraycopy(in.toArray(), 0, bigVector$1, i$1.elem * $this.inputSize, $this.inputSize);
         System.arraycopy(out.toArray(), 0, bigVector$1, $this.inputSize * size$1 + i$1.elem * $this.outputSize, $this.outputSize);
         ++i$1.elem;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public DataStacker(final int stackSize, final int inputSize, final int outputSize) {
      this.stackSize = stackSize;
      this.inputSize = inputSize;
      this.outputSize = outputSize;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
