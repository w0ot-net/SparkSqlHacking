package org.apache.spark.ml.classification;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005\u0011b\u0004\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u00011\t!\b\u0005\u0006[\u0001!\tA\f\u0002\u0010)J\f\u0017N\\5oON+X.\\1ss*\u0011aaB\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tA\u0011\"\u0001\u0002nY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0005\u0002\u0001!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u00023A\u0011\u0011CG\u0005\u00037I\u0011A!\u00168ji\u0006\u0001rN\u00196fGRLg/\u001a%jgR|'/_\u000b\u0002=A\u0019\u0011cH\u0011\n\u0005\u0001\u0012\"!B!se\u0006L\bCA\t#\u0013\t\u0019#C\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0005\u0015Z\u0003C\u0001\u0014*\u001b\u00059#B\u0001\u0015\n\u0003)\tgN\\8uCRLwN\\\u0005\u0003U\u001d\u0012QaU5oG\u0016\f\u0013\u0001L\u0001\u0006g9\nd\u0006M\u0001\u0010i>$\u0018\r\\%uKJ\fG/[8ogV\tq\u0006\u0005\u0002\u0012a%\u0011\u0011G\u0005\u0002\u0004\u0013:$\bfA\u0002&W\u0001"
)
public interface TrainingSummary {
   double[] objectiveHistory();

   // $FF: synthetic method
   static int totalIterations$(final TrainingSummary $this) {
      return $this.totalIterations();
   }

   default int totalIterations() {
      .MODULE$.assert(this.objectiveHistory().length > 0, () -> "objectiveHistory length should be greater than 0.");
      return this.objectiveHistory().length - 1;
   }

   static void $init$(final TrainingSummary $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
