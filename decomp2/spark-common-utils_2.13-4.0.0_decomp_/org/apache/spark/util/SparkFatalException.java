package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2Q\u0001B\u0003\u0003\u000f5A\u0001\"\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\t\u00192\u000b]1sW\u001a\u000bG/\u00197Fq\u000e,\u0007\u000f^5p]*\u0011aaB\u0001\u0005kRLGN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h'\t\u0001a\u0002\u0005\u0002\u001059\u0011\u0001c\u0006\b\u0003#Ui\u0011A\u0005\u0006\u0003'Q\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002-\u0005)1oY1mC&\u0011\u0001$G\u0001\ba\u0006\u001c7.Y4f\u0015\u00051\u0012BA\u000e\u001d\u0005%)\u0005pY3qi&|gN\u0003\u0002\u00193\u0005IA\u000f\u001b:po\u0006\u0014G.Z\u000b\u0002?A\u0011q\u0002I\u0005\u0003Cq\u0011\u0011\u0002\u00165s_^\f'\r\\3\u0002\u0015QD'o\\<bE2,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003K\u001d\u0002\"A\n\u0001\u000e\u0003\u0015AQ!H\u0002A\u0002}\u0001"
)
public final class SparkFatalException extends Exception {
   private final Throwable throwable;

   public Throwable throwable() {
      return this.throwable;
   }

   public SparkFatalException(final Throwable throwable) {
      super(throwable);
      this.throwable = throwable;
   }
}
