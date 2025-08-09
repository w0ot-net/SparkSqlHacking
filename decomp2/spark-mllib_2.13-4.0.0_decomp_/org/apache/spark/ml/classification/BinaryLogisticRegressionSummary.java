package org.apache.spark.ml.classification;

import org.apache.spark.SparkException;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2qa\u0001\u0003\u0011\u0002\u0007\u0005r\u0002C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0011\u00053EA\u0010CS:\f'/\u001f'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:\u001cV/\\7befT!!\u0002\u0004\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u0011q\u0001C\u0001\u0003[2T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0001C\u0006\u000e\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\t9\u0002$D\u0001\u0005\u0013\tIBAA\rM_\u001eL7\u000f^5d%\u0016<'/Z:tS>t7+^7nCJL\bCA\f\u001c\u0013\taBAA\u000eCS:\f'/_\"mCN\u001c\u0018NZ5dCRLwN\\*v[6\f'/_\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"!\u0005\u0011\n\u0005\u0005\u0012\"\u0001B+oSR\f\u0001b]2pe\u0016\u001cu\u000e\\\u000b\u0002IA\u0011Q\u0005\f\b\u0003M)\u0002\"a\n\n\u000e\u0003!R!!\u000b\b\u0002\rq\u0012xn\u001c;?\u0013\tY##\u0001\u0004Qe\u0016$WMZ\u0005\u0003[9\u0012aa\u0015;sS:<'BA\u0016\u0013S\r\u0001\u0001GM\u0005\u0003c\u0011\u00111EQ5oCJLHj\\4jgRL7MU3he\u0016\u001c8/[8o'VlW.\u0019:z\u00136\u0004H.\u0003\u00024\t\t9#)\u001b8befdunZ5ti&\u001c'+Z4sKN\u001c\u0018n\u001c8Ue\u0006Lg.\u001b8h'VlW.\u0019:z\u0001"
)
public interface BinaryLogisticRegressionSummary extends LogisticRegressionSummary, BinaryClassificationSummary {
   // $FF: synthetic method
   static String scoreCol$(final BinaryLogisticRegressionSummary $this) {
      return $this.scoreCol();
   }

   default String scoreCol() {
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(this.probabilityCol()))) {
         return this.probabilityCol();
      } else {
         throw new SparkException("probabilityCol is required for BinaryLogisticRegressionSummary.");
      }
   }

   static void $init$(final BinaryLogisticRegressionSummary $this) {
   }
}
