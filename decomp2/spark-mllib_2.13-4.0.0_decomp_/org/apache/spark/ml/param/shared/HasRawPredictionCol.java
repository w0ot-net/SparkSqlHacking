package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000bI\u0002AQA\u001a\u0003'!\u000b7OU1x!J,G-[2uS>t7i\u001c7\u000b\u0005\u00199\u0011AB:iCJ,GM\u0003\u0002\t\u0013\u0005)\u0001/\u0019:b[*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0004\n\u0005q9!A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011A\u0003I\u0005\u0003CU\u0011A!\u00168ji\u0006\u0001\"/Y<Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\\\u000b\u0002IA\u0019!$J\u0014\n\u0005\u0019:!!\u0002)be\u0006l\u0007C\u0001\u00150\u001d\tIS\u0006\u0005\u0002++5\t1F\u0003\u0002-#\u00051AH]8pizJ!AL\u000b\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]U\t1cZ3u%\u0006<\bK]3eS\u000e$\u0018n\u001c8D_2,\u0012a\n"
)
public interface HasRawPredictionCol extends Params {
   void org$apache$spark$ml$param$shared$HasRawPredictionCol$_setter_$rawPredictionCol_$eq(final Param x$1);

   Param rawPredictionCol();

   // $FF: synthetic method
   static String getRawPredictionCol$(final HasRawPredictionCol $this) {
      return $this.getRawPredictionCol();
   }

   default String getRawPredictionCol() {
      return (String)this.$(this.rawPredictionCol());
   }

   static void $init$(final HasRawPredictionCol $this) {
      $this.org$apache$spark$ml$param$shared$HasRawPredictionCol$_setter_$rawPredictionCol_$eq(new Param($this, "rawPredictionCol", "raw prediction (a.k.a. confidence) column name", .MODULE$.apply(String.class)));
      $this.setDefault($this.rawPredictionCol(), "rawPrediction");
   }
}
