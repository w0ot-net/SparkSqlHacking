package org.apache.spark;

import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4Q\u0001D\u0007\u0001\u001bMA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\t_\u0001\u0011\t\u0011)A\u0005a!A1\u0007\u0001B\u0001B\u0003%A\u0007C\u0003>\u0001\u0011\u0005a\bC\u0003D\u0001\u0011\u0005C\tC\u0003K\u0001\u0011\u00053j\u0002\u0005M\u001b\u0005\u0005\t\u0012A\u0007N\r!aQ\"!A\t\u00025q\u0005\"B\u001f\t\t\u0003I\u0006b\u0002.\t#\u0003%\ta\u0017\u0005\bM\"\t\t\u0011\"\u0003h\u0005m\u0019\u0006/\u0019:l\u00072\f7o\u001d(pi\u001a{WO\u001c3Fq\u000e,\u0007\u000f^5p]*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xmE\u0002\u0001)q\u0001\"!\u0006\u000e\u000e\u0003YQ!a\u0006\r\u0002\t1\fgn\u001a\u0006\u00023\u0005!!.\u0019<b\u0013\tYbC\u0001\fDY\u0006\u001c8OT8u\r>,h\u000eZ#yG\u0016\u0004H/[8o!\tib$D\u0001\u000e\u0013\tyRB\u0001\bTa\u0006\u00148\u000e\u00165s_^\f'\r\\3\u0002\u0015\u0015\u0014(o\u001c:DY\u0006\u001c8o\u0001\u0001\u0011\u0005\rbcB\u0001\u0013+!\t)\u0003&D\u0001'\u0015\t9\u0013%\u0001\u0004=e>|GO\u0010\u0006\u0002S\u0005)1oY1mC&\u00111\u0006K\u0001\u0007!J,G-\u001a4\n\u00055r#AB*ue&twM\u0003\u0002,Q\u0005\tR.Z:tC\u001e,\u0007+\u0019:b[\u0016$XM]:\u0011\t\r\n$EI\u0005\u0003e9\u00121!T1q\u0003\u0015\u0019\u0017-^:f!\t)$H\u0004\u00027q9\u0011QeN\u0005\u0002S%\u0011\u0011\bK\u0001\ba\u0006\u001c7.Y4f\u0013\tYDHA\u0005UQJ|w/\u00192mK*\u0011\u0011\bK\u0001\u0007y%t\u0017\u000e\u001e \u0015\t}\u0002\u0015I\u0011\t\u0003;\u0001AQ\u0001\t\u0003A\u0002\tBQa\f\u0003A\u0002ABqa\r\u0003\u0011\u0002\u0003\u0007A'\u0001\u000bhKRlUm]:bO\u0016\u0004\u0016M]1nKR,'o\u001d\u000b\u0002\u000bB!a)\u0013\u0012#\u001b\u00059%B\u0001%\u0019\u0003\u0011)H/\u001b7\n\u0005I:\u0015\u0001D4fi\u000e{g\u000eZ5uS>tG#\u0001\u0012\u00027M\u0003\u0018M]6DY\u0006\u001c8OT8u\r>,h\u000eZ#yG\u0016\u0004H/[8o!\ti\u0002bE\u0002\t\u001fN\u0003\"\u0001U)\u000e\u0003!J!A\u0015\u0015\u0003\r\u0005s\u0017PU3g!\t!v+D\u0001V\u0015\t1\u0006$\u0001\u0002j_&\u0011\u0001,\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0002\u001b\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\u0012\u0001\u0018\u0016\u0003iu[\u0013A\u0018\t\u0003?\u0012l\u0011\u0001\u0019\u0006\u0003C\n\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\rD\u0013AC1o]>$\u0018\r^5p]&\u0011Q\r\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#\u00015\u0011\u0005UI\u0017B\u00016\u0017\u0005\u0019y%M[3di\u0002"
)
public class SparkClassNotFoundException extends ClassNotFoundException implements SparkThrowable {
   private final String errorClass;
   private final Map messageParameters;

   public static Throwable $lessinit$greater$default$3() {
      return SparkClassNotFoundException$.MODULE$.$lessinit$greater$default$3();
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

   public SparkClassNotFoundException(final String errorClass, final Map messageParameters, final Throwable cause) {
      super(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters), cause);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }
}
