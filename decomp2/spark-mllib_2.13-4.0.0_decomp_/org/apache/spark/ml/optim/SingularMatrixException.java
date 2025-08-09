package org.apache.spark.ml.optim;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2Q!\u0002\u0004\u0001\u0015AA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)Q\u0006\u0001C\u0001]!)Q\u0006\u0001C\u0001g\t92+\u001b8hk2\f'/T1ue&DX\t_2faRLwN\u001c\u0006\u0003\u000f!\tQa\u001c9uS6T!!\u0003\u0006\u0002\u00055d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0014\u0005\u0001\t\u0002C\u0001\n\u001e\u001d\t\u0019\"D\u0004\u0002\u001515\tQC\u0003\u0002\u0017/\u00051AH]8piz\u001a\u0001!C\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tYB$A\u0004qC\u000e\\\u0017mZ3\u000b\u0003eI!AH\u0010\u00031%cG.Z4bY\u0006\u0013x-^7f]R,\u0005pY3qi&|gN\u0003\u0002\u001c9\u00059Q.Z:tC\u001e,\u0007C\u0001\u0012'\u001d\t\u0019C\u0005\u0005\u0002\u00159%\u0011Q\u0005H\u0001\u0007!J,G-\u001a4\n\u0005\u001dB#AB*ue&twM\u0003\u0002&9\u0005)1-Y;tKB\u0011!cK\u0005\u0003Y}\u0011\u0011\u0002\u00165s_^\f'\r\\3\u0002\rqJg.\u001b;?)\ry\u0013G\r\t\u0003a\u0001i\u0011A\u0002\u0005\u0006A\r\u0001\r!\t\u0005\u0006S\r\u0001\rA\u000b\u000b\u0003_QBQ\u0001\t\u0003A\u0002\u0005\u0002"
)
public class SingularMatrixException extends IllegalArgumentException {
   public SingularMatrixException(final String message, final Throwable cause) {
      super(message, cause);
   }

   public SingularMatrixException(final String message) {
      this(message, (Throwable)null);
   }
}
