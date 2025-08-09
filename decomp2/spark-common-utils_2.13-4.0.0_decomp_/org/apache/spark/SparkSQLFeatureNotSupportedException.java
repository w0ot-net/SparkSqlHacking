package org.apache.spark;

import java.sql.SQLFeatureNotSupportedException;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2QAB\u0004\u0001\u000f5A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C!g!)\u0011\b\u0001C!u\t!3\u000b]1sWN\u000bFJR3biV\u0014XMT8u'V\u0004\bo\u001c:uK\u0012,\u0005pY3qi&|gN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h'\r\u0001aB\u0006\t\u0003\u001fQi\u0011\u0001\u0005\u0006\u0003#I\t1a]9m\u0015\u0005\u0019\u0012\u0001\u00026bm\u0006L!!\u0006\t\u0003?M\u000bFJR3biV\u0014XMT8u'V\u0004\bo\u001c:uK\u0012,\u0005pY3qi&|g\u000e\u0005\u0002\u001815\tq!\u0003\u0002\u001a\u000f\tq1\u000b]1sWRC'o\\<bE2,\u0017AC3se>\u00148\t\\1tg\u000e\u0001\u0001CA\u000f'\u001d\tqB\u0005\u0005\u0002 E5\t\u0001E\u0003\u0002\"7\u00051AH]8pizR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\na\u0001\u0015:fI\u00164\u0017BA\u0014)\u0005\u0019\u0019FO]5oO*\u0011QEI\u0001\u0012[\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001c\b\u0003B\u000f,9qI!\u0001\f\u0015\u0003\u00075\u000b\u0007/\u0001\u0004=S:LGO\u0010\u000b\u0004_A\n\u0004CA\f\u0001\u0011\u0015Q2\u00011\u0001\u001d\u0011\u0015I3\u00011\u0001+\u0003Q9W\r^'fgN\fw-\u001a)be\u0006lW\r^3sgR\tA\u0007\u0005\u00036qqaR\"\u0001\u001c\u000b\u0005]\u0012\u0012\u0001B;uS2L!\u0001\f\u001c\u0002\u0019\u001d,GoQ8oI&$\u0018n\u001c8\u0015\u0003q\u0001"
)
public class SparkSQLFeatureNotSupportedException extends SQLFeatureNotSupportedException implements SparkThrowable {
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

   public SparkSQLFeatureNotSupportedException(final String errorClass, final Map messageParameters) {
      super(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }
}
