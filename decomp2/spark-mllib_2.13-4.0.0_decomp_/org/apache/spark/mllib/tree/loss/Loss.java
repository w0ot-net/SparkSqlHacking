package org.apache.spark.mllib.tree.loss;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.model.TreeEnsembleModel;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0003C\u0003'\u0001\u0011\u0005q\u0005C\u0003,\u0001\u0019\u0005A\u0006C\u0003>\u0001\u0011\u0005a\b\u0003\u0004>\u0001\u0019\u0005A\"\u0016\u0002\u0005\u0019>\u001c8O\u0003\u0002\b\u0011\u0005!An\\:t\u0015\tI!\"\u0001\u0003ue\u0016,'BA\u0006\r\u0003\u0015iG\u000e\\5c\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7\u0001A\n\u0004\u0001QQ\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\r\u0005\u0002\u001cG9\u0011A$\t\b\u0003;\u0001j\u0011A\b\u0006\u0003?I\ta\u0001\u0010:p_Rt\u0014\"A\f\n\u0005\t2\u0012a\u00029bG.\fw-Z\u0005\u0003I\u0015\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\t\f\u0002\r\u0011Jg.\u001b;%)\u0005A\u0003CA\u000b*\u0013\tQcC\u0001\u0003V]&$\u0018\u0001C4sC\u0012LWM\u001c;\u0015\u00075\u0002$\u0007\u0005\u0002\u0016]%\u0011qF\u0006\u0002\u0007\t>,(\r\\3\t\u000bE\u0012\u0001\u0019A\u0017\u0002\u0015A\u0014X\rZ5di&|g\u000eC\u00034\u0005\u0001\u0007Q&A\u0003mC\n,G\u000eK\u0002\u0003km\u0002\"AN\u001d\u000e\u0003]R!\u0001\u000f\u0007\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002;o\t)1+\u001b8dK\u0006\nA(A\u00032]Ir\u0003'\u0001\u0007d_6\u0004X\u000f^3FeJ|'\u000fF\u0002.\u007f\u0019CQ\u0001Q\u0002A\u0002\u0005\u000bQ!\\8eK2\u0004\"A\u0011#\u000e\u0003\rS!\u0001\u0011\u0005\n\u0005\u0015\u001b%!\u0005+sK\u0016,en]3nE2,Wj\u001c3fY\")qi\u0001a\u0001\u0011\u0006!A-\u0019;b!\rIEJT\u0007\u0002\u0015*\u00111\nD\u0001\u0004e\u0012$\u0017BA'K\u0005\r\u0011F\t\u0012\t\u0003\u001fJk\u0011\u0001\u0015\u0006\u0003#*\t!B]3he\u0016\u001c8/[8o\u0013\t\u0019\u0006K\u0001\u0007MC\n,G.\u001a3Q_&tG\u000fK\u0002\u0004km\"2!\f,X\u0011\u0015\tD\u00011\u0001.\u0011\u0015\u0019D\u00011\u0001.Q\r\u0001Qg\u000f"
)
public interface Loss extends Serializable {
   double gradient(final double prediction, final double label);

   // $FF: synthetic method
   static double computeError$(final Loss $this, final TreeEnsembleModel model, final RDD data) {
      return $this.computeError(model, data);
   }

   default double computeError(final TreeEnsembleModel model, final RDD data) {
      return .MODULE$.doubleRDDToDoubleRDDFunctions(data.map((point) -> BoxesRunTime.boxToDouble($anonfun$computeError$1(this, model, point)), scala.reflect.ClassTag..MODULE$.Double())).mean();
   }

   double computeError(final double prediction, final double label);

   // $FF: synthetic method
   static double $anonfun$computeError$1(final Loss $this, final TreeEnsembleModel model$1, final LabeledPoint point) {
      return $this.computeError(model$1.predict(point.features()), point.label());
   }

   static void $init$(final Loss $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
