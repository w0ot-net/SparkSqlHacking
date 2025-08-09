package org.apache.spark;

import java.util.ConcurrentModificationException;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4Q\u0001D\u0007\u0001\u001bMA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\t_\u0001\u0011\t\u0011)A\u0005a!A1\u0007\u0001B\u0001B\u0003%A\u0007C\u0003>\u0001\u0011\u0005a\bC\u0003D\u0001\u0011\u0005C\tC\u0003H\u0001\u0011\u0005\u0003j\u0002\u0005J\u001b\u0005\u0005\t\u0012A\u0007K\r!aQ\"!A\t\u00025Y\u0005\"B\u001f\t\t\u00031\u0006bB,\t#\u0003%\t\u0001\u0017\u0005\bG\"\t\t\u0011\"\u0003e\u0005\u0011\u001a\u0006/\u0019:l\u0007>t7-\u001e:sK:$Xj\u001c3jM&\u001c\u0017\r^5p]\u0016C8-\u001a9uS>t'B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0014\u0007\u0001!B\u0004\u0005\u0002\u001655\taC\u0003\u0002\u00181\u0005!Q\u000f^5m\u0015\u0005I\u0012\u0001\u00026bm\u0006L!a\u0007\f\u0003?\r{gnY;se\u0016tG/T8eS\u001aL7-\u0019;j_:,\u0005pY3qi&|g\u000e\u0005\u0002\u001e=5\tQ\"\u0003\u0002 \u001b\tq1\u000b]1sWRC'o\\<bE2,\u0017AC3se>\u00148\t\\1tg\u000e\u0001\u0001CA\u0012-\u001d\t!#\u0006\u0005\u0002&Q5\taE\u0003\u0002(C\u00051AH]8pizR\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\na\u0001\u0015:fI\u00164\u0017BA\u0017/\u0005\u0019\u0019FO]5oO*\u00111\u0006K\u0001\u0012[\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001c\b\u0003B\u00122E\tJ!A\r\u0018\u0003\u00075\u000b\u0007/A\u0003dCV\u001cX\r\u0005\u00026u9\u0011a\u0007\u000f\b\u0003K]J\u0011!K\u0005\u0003s!\nq\u0001]1dW\u0006<W-\u0003\u0002<y\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0003s!\na\u0001P5oSRtD\u0003B A\u0003\n\u0003\"!\b\u0001\t\u000b\u0001\"\u0001\u0019\u0001\u0012\t\u000b=\"\u0001\u0019\u0001\u0019\t\u000fM\"\u0001\u0013!a\u0001i\u0005!r-\u001a;NKN\u001c\u0018mZ3QCJ\fW.\u001a;feN$\u0012!\u0012\t\u0005+\u0019\u0013#%\u0003\u00023-\u0005aq-\u001a;D_:$\u0017\u000e^5p]R\t!%\u0001\u0013Ta\u0006\u00148nQ8oGV\u0014(/\u001a8u\u001b>$\u0017NZ5dCRLwN\\#yG\u0016\u0004H/[8o!\ti\u0002bE\u0002\t\u0019B\u0003\"!\u0014(\u000e\u0003!J!a\u0014\u0015\u0003\r\u0005s\u0017PU3g!\t\tF+D\u0001S\u0015\t\u0019\u0006$\u0001\u0002j_&\u0011QK\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0002\u0015\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\u0012!\u0017\u0016\u0003ii[\u0013a\u0017\t\u00039\u0006l\u0011!\u0018\u0006\u0003=~\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0001D\u0013AC1o]>$\u0018\r^5p]&\u0011!-\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#A3\u0011\u0005\u0019LW\"A4\u000b\u0005!D\u0012\u0001\u00027b]\u001eL!A[4\u0003\r=\u0013'.Z2u\u0001"
)
public class SparkConcurrentModificationException extends ConcurrentModificationException implements SparkThrowable {
   private final String errorClass;
   private final Map messageParameters;

   public static Throwable $lessinit$greater$default$3() {
      return SparkConcurrentModificationException$.MODULE$.$lessinit$greater$default$3();
   }

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

   public SparkConcurrentModificationException(final String errorClass, final Map messageParameters, final Throwable cause) {
      super(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters), cause);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }
}
