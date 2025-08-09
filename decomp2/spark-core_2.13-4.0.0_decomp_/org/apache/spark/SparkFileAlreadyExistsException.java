package org.apache.spark;

import org.apache.hadoop.fs.FileAlreadyExistsException;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2QAB\u0004\u0001\u000f5A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C!g!)1\b\u0001C!y\ty2\u000b]1sW\u001aKG.Z!me\u0016\fG-_#ySN$8/\u0012=dKB$\u0018n\u001c8\u000b\u0005!I\u0011!B:qCJ\\'B\u0001\u0006\f\u0003\u0019\t\u0007/Y2iK*\tA\"A\u0002pe\u001e\u001c2\u0001\u0001\b\u0017!\tyA#D\u0001\u0011\u0015\t\t\"#\u0001\u0002gg*\u00111#C\u0001\u0007Q\u0006$wn\u001c9\n\u0005U\u0001\"A\u0007$jY\u0016\fEN]3bIf,\u00050[:ug\u0016C8-\u001a9uS>t\u0007CA\f\u0019\u001b\u00059\u0011BA\r\b\u00059\u0019\u0006/\u0019:l)\"\u0014xn^1cY\u0016\f!\"\u001a:s_J\u001cE.Y:t\u0007\u0001\u0001\"!\b\u0014\u000f\u0005y!\u0003CA\u0010#\u001b\u0005\u0001#BA\u0011\u001c\u0003\u0019a$o\\8u})\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\u00051\u0001K]3eK\u001aL!a\n\u0015\u0003\rM#(/\u001b8h\u0015\t)#%A\tnKN\u001c\u0018mZ3QCJ\fW.\u001a;feN\u0004B!H\u0016\u001d9%\u0011A\u0006\u000b\u0002\u0004\u001b\u0006\u0004\u0018A\u0002\u001fj]&$h\bF\u00020aE\u0002\"a\u0006\u0001\t\u000bi\u0019\u0001\u0019\u0001\u000f\t\u000b%\u001a\u0001\u0019\u0001\u0016\u0002)\u001d,G/T3tg\u0006<W\rU1sC6,G/\u001a:t)\u0005!\u0004\u0003B\u001b;9qi\u0011A\u000e\u0006\u0003oa\nA!\u001e;jY*\t\u0011(\u0001\u0003kCZ\f\u0017B\u0001\u00177\u000319W\r^\"p]\u0012LG/[8o)\u0005a\u0002"
)
public class SparkFileAlreadyExistsException extends FileAlreadyExistsException implements SparkThrowable {
   private final String errorClass;
   private final Map messageParameters;

   /** @deprecated */
   @Deprecated
   public String getErrorClass() {
      return super.getErrorClass();
   }

   public String getSqlState() {
      return super.getSqlState();
   }

   public boolean isInternalError() {
      return super.isInternalError();
   }

   public QueryContext[] getQueryContext() {
      return super.getQueryContext();
   }

   public java.util.Map getMessageParameters() {
      return .MODULE$.MapHasAsJava(this.messageParameters).asJava();
   }

   public String getCondition() {
      return this.errorClass;
   }

   public SparkFileAlreadyExistsException(final String errorClass, final Map messageParameters) {
      super(org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }
}
