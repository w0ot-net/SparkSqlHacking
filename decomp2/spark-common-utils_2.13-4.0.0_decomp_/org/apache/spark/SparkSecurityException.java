package org.apache.spark;

import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2QAB\u0004\u0001\u000f5A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C!g!)\u0011\b\u0001C!u\t12\u000b]1sWN+7-\u001e:jif,\u0005pY3qi&|gN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h'\r\u0001aB\u0006\t\u0003\u001fQi\u0011\u0001\u0005\u0006\u0003#I\tA\u0001\\1oO*\t1#\u0001\u0003kCZ\f\u0017BA\u000b\u0011\u0005E\u0019VmY;sSRLX\t_2faRLwN\u001c\t\u0003/ai\u0011aB\u0005\u00033\u001d\u0011ab\u00159be.$\u0006N]8xC\ndW-\u0001\u0006feJ|'o\u00117bgN\u001c\u0001\u0001\u0005\u0002\u001eM9\u0011a\u0004\n\t\u0003?\tj\u0011\u0001\t\u0006\u0003Cm\ta\u0001\u0010:p_Rt$\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012\u0013A\u0002)sK\u0012,g-\u0003\u0002(Q\t11\u000b\u001e:j]\u001eT!!\n\u0012\u0002#5,7o]1hKB\u000b'/Y7fi\u0016\u00148\u000f\u0005\u0003\u001eWqa\u0012B\u0001\u0017)\u0005\ri\u0015\r]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007=\u0002\u0014\u0007\u0005\u0002\u0018\u0001!)!d\u0001a\u00019!)\u0011f\u0001a\u0001U\u0005!r-\u001a;NKN\u001c\u0018mZ3QCJ\fW.\u001a;feN$\u0012\u0001\u000e\t\u0005kabB$D\u00017\u0015\t9$#\u0001\u0003vi&d\u0017B\u0001\u00177\u000319W\r^\"p]\u0012LG/[8o)\u0005a\u0002"
)
public class SparkSecurityException extends SecurityException implements SparkThrowable {
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

   public SparkSecurityException(final String errorClass, final Map messageParameters) {
      super(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }
}
