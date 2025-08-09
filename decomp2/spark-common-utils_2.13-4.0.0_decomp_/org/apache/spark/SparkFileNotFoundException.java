package org.apache.spark;

import java.io.FileNotFoundException;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2QAB\u0004\u0001\u000f5A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C!g!)\u0011\b\u0001C!u\tQ2\u000b]1sW\u001aKG.\u001a(pi\u001a{WO\u001c3Fq\u000e,\u0007\u000f^5p]*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014xmE\u0002\u0001\u001dY\u0001\"a\u0004\u000b\u000e\u0003AQ!!\u0005\n\u0002\u0005%|'\"A\n\u0002\t)\fg/Y\u0005\u0003+A\u0011QCR5mK:{GOR8v]\u0012,\u0005pY3qi&|g\u000e\u0005\u0002\u001815\tq!\u0003\u0002\u001a\u000f\tq1\u000b]1sWRC'o\\<bE2,\u0017AC3se>\u00148\t\\1tg\u000e\u0001\u0001CA\u000f'\u001d\tqB\u0005\u0005\u0002 E5\t\u0001E\u0003\u0002\"7\u00051AH]8pizR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\na\u0001\u0015:fI\u00164\u0017BA\u0014)\u0005\u0019\u0019FO]5oO*\u0011QEI\u0001\u0012[\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001c\b\u0003B\u000f,9qI!\u0001\f\u0015\u0003\u00075\u000b\u0007/\u0001\u0004=S:LGO\u0010\u000b\u0004_A\n\u0004CA\f\u0001\u0011\u0015Q2\u00011\u0001\u001d\u0011\u0015I3\u00011\u0001+\u0003Q9W\r^'fgN\fw-\u001a)be\u0006lW\r^3sgR\tA\u0007\u0005\u00036qqaR\"\u0001\u001c\u000b\u0005]\u0012\u0012\u0001B;uS2L!\u0001\f\u001c\u0002\u0019\u001d,GoQ8oI&$\u0018n\u001c8\u0015\u0003q\u0001"
)
public class SparkFileNotFoundException extends FileNotFoundException implements SparkThrowable {
   private final String errorClass;
   private final Map messageParameters;

   /** @deprecated */
   @Deprecated
   public String getErrorClass() {
      return SparkThrowable.super.getErrorClass();
   }

   public String getSqlState() {
      return SparkThrowable.super.getSqlState();
   }

   public boolean isInternalError() {
      return SparkThrowable.super.isInternalError();
   }

   public QueryContext[] getQueryContext() {
      return SparkThrowable.super.getQueryContext();
   }

   public java.util.Map getMessageParameters() {
      return .MODULE$.MapHasAsJava(this.messageParameters).asJava();
   }

   public String getCondition() {
      return this.errorClass;
   }

   public SparkFileNotFoundException(final String errorClass, final Map messageParameters) {
      super(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }
}
