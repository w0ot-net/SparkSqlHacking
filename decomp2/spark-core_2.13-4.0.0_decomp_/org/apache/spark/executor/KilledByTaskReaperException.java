package org.apache.spark.executor;

import org.apache.spark.SparkException;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152Aa\u0001\u0003\u0001\u001b!A!\u0003\u0001B\u0001B\u0003%1\u0003C\u0003!\u0001\u0011\u0005\u0011EA\u000eLS2dW\r\u001a\"z)\u0006\u001c8NU3ba\u0016\u0014X\t_2faRLwN\u001c\u0006\u0003\u000b\u0019\t\u0001\"\u001a=fGV$xN\u001d\u0006\u0003\u000f!\tQa\u001d9be.T!!\u0003\u0006\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0011aA8sO\u000e\u00011C\u0001\u0001\u000f!\ty\u0001#D\u0001\u0007\u0013\t\tbA\u0001\bTa\u0006\u00148.\u0012=dKB$\u0018n\u001c8\u0002\u000f5,7o]1hKB\u0011A#\b\b\u0003+m\u0001\"AF\r\u000e\u0003]Q!\u0001\u0007\u0007\u0002\rq\u0012xn\u001c;?\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0003\u0019\u0001&/\u001a3fM&\u0011ad\b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005qI\u0012A\u0002\u001fj]&$h\b\u0006\u0002#IA\u00111\u0005A\u0007\u0002\t!)!C\u0001a\u0001'\u0001"
)
public class KilledByTaskReaperException extends SparkException {
   public KilledByTaskReaperException(final String message) {
      super(message);
   }
}
