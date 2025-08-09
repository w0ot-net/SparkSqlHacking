package org.apache.spark;

import java.sql.SQLException;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2QAB\u0004\u0001\u000f5A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C!g!)\u0011\b\u0001C!u\t\t2\u000b]1sWN\u000bF*\u0012=dKB$\u0018n\u001c8\u000b\u0005!I\u0011!B:qCJ\\'B\u0001\u0006\f\u0003\u0019\t\u0007/Y2iK*\tA\"A\u0002pe\u001e\u001c2\u0001\u0001\b\u0017!\tyA#D\u0001\u0011\u0015\t\t\"#A\u0002tc2T\u0011aE\u0001\u0005U\u00064\u0018-\u0003\u0002\u0016!\ta1+\u0015'Fq\u000e,\u0007\u000f^5p]B\u0011q\u0003G\u0007\u0002\u000f%\u0011\u0011d\u0002\u0002\u000f'B\f'o\u001b+ie><\u0018M\u00197f\u0003))'O]8s\u00072\f7o]\u0002\u0001!\tibE\u0004\u0002\u001fIA\u0011qDI\u0007\u0002A)\u0011\u0011eG\u0001\u0007yI|w\u000e\u001e \u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0002\rA\u0013X\rZ3g\u0013\t9\u0003F\u0001\u0004TiJLgn\u001a\u0006\u0003K\t\n\u0011#\\3tg\u0006<W\rU1sC6,G/\u001a:t!\u0011i2\u0006\b\u000f\n\u00051B#aA'ba\u00061A(\u001b8jiz\"2a\f\u00192!\t9\u0002\u0001C\u0003\u001b\u0007\u0001\u0007A\u0004C\u0003*\u0007\u0001\u0007!&\u0001\u000bhKRlUm]:bO\u0016\u0004\u0016M]1nKR,'o\u001d\u000b\u0002iA!Q\u0007\u000f\u000f\u001d\u001b\u00051$BA\u001c\u0013\u0003\u0011)H/\u001b7\n\u000512\u0014\u0001D4fi\u000e{g\u000eZ5uS>tG#\u0001\u000f"
)
public class SparkSQLException extends SQLException implements SparkThrowable {
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

   public SparkSQLException(final String errorClass, final Map messageParameters) {
      super(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }
}
