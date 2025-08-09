package org.apache.spark.ml.ann;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.optimization.Updater;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i2Qa\u0001\u0003\u0001\t9AQa\u0006\u0001\u0005\u0002eAQ\u0001\b\u0001\u0005Bu\u0011!\"\u0011(O+B$\u0017\r^3s\u0015\t)a!A\u0002b]:T!a\u0002\u0005\u0002\u00055d'BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0014\u0005\u0001y\u0001C\u0001\t\u0016\u001b\u0005\t\"B\u0001\n\u0014\u00031y\u0007\u000f^5nSj\fG/[8o\u0015\t!\u0002\"A\u0003nY2L'-\u0003\u0002\u0017#\t9Q\u000b\u001d3bi\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003i\u0001\"a\u0007\u0001\u000e\u0003\u0011\tqaY8naV$X\r\u0006\u0004\u001f[=\n4\u0007\u000f\t\u0005?\t\"#&D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019!V\u000f\u001d7feA\u0011Q\u0005K\u0007\u0002M)\u0011qeE\u0001\u0007Y&t\u0017\r\\4\n\u0005%2#A\u0002,fGR|'\u000f\u0005\u0002 W%\u0011A\u0006\t\u0002\u0007\t>,(\r\\3\t\u000b9\u0012\u0001\u0019\u0001\u0013\u0002\u0015],\u0017n\u001a5ug>cG\rC\u00031\u0005\u0001\u0007A%\u0001\u0005he\u0006$\u0017.\u001a8u\u0011\u0015\u0011$\u00011\u0001+\u0003!\u0019H/\u001a9TSj,\u0007\"\u0002\u001b\u0003\u0001\u0004)\u0014\u0001B5uKJ\u0004\"a\b\u001c\n\u0005]\u0002#aA%oi\")\u0011H\u0001a\u0001U\u0005A!/Z4QCJ\fW\u000e"
)
public class ANNUpdater extends Updater {
   public Tuple2 compute(final Vector weightsOld, final Vector gradient, final double stepSize, final int iter, final double regParam) {
      breeze.linalg.Vector brzWeights = weightsOld.asBreeze().toDenseVector$mcD$sp(.MODULE$.Double());
      breeze.linalg.package..MODULE$.axpy(BoxesRunTime.boxToDouble(-stepSize), gradient.asBreeze(), brzWeights, breeze.linalg.operators.HasOps..MODULE$.impl_scaleAdd_InPlace_V_S_V_Double());
      return new Tuple2(Vectors$.MODULE$.fromBreeze(brzWeights), BoxesRunTime.boxToDouble((double)0.0F));
   }
}
