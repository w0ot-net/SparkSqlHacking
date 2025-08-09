package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000bI\u0002AQA\u001a\u0003!!\u000b7\u000f\u0015:fI&\u001cG/[8o\u0007>d'B\u0001\u0004\b\u0003\u0019\u0019\b.\u0019:fI*\u0011\u0001\"C\u0001\u0006a\u0006\u0014\u0018-\u001c\u0006\u0003\u0015-\t!!\u001c7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001'e\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001c\u001b\u00059\u0011B\u0001\u000f\b\u0005\u0019\u0001\u0016M]1ng\u00061A%\u001b8ji\u0012\"\u0012a\b\t\u0003)\u0001J!!I\u000b\u0003\tUs\u0017\u000e^\u0001\u000eaJ,G-[2uS>t7i\u001c7\u0016\u0003\u0011\u00022AG\u0013(\u0013\t1sAA\u0003QCJ\fW\u000e\u0005\u0002)_9\u0011\u0011&\f\t\u0003UUi\u0011a\u000b\u0006\u0003YE\ta\u0001\u0010:p_Rt\u0014B\u0001\u0018\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001'\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059*\u0012\u0001E4fiB\u0013X\rZ5di&|gnQ8m+\u00059\u0003"
)
public interface HasPredictionCol extends Params {
   void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1);

   Param predictionCol();

   // $FF: synthetic method
   static String getPredictionCol$(final HasPredictionCol $this) {
      return $this.getPredictionCol();
   }

   default String getPredictionCol() {
      return (String)this.$(this.predictionCol());
   }

   static void $init$(final HasPredictionCol $this) {
      $this.org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(new Param($this, "predictionCol", "prediction column name", .MODULE$.apply(String.class)));
      $this.setDefault($this.predictionCol(), "prediction");
   }
}
