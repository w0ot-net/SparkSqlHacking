package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4Qa\u0004\t\u0001!YA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tg\u0001\u0011\t\u0011)A\u0005i!A1\b\u0001B\u0001B\u0003%A\b\u0003\u0005>\u0001\t\u0005\t\u0015!\u0003?\u0011!\t\u0005A!A!\u0002\u0013\u0011\u0005\"\u0002%\u0001\t\u0013I\u0005\"\u0002%\u0001\t\u0003\u0001\u0006\"\u0002%\u0001\t\u00039\u0006\"\u0002%\u0001\t\u0003Y\u0006\"\u0002%\u0001\t\u0003q\u0006\"\u0002%\u0001\t\u0003A\u0007\"\u00026\u0001\t\u0003Z\u0007\"\u00027\u0001\t\u0003j\u0007\"\u00028\u0001\t\u0003z'!H*qCJ\\\u0017\n\u001c7fO\u0006d\u0017I]4v[\u0016tG/\u0012=dKB$\u0018n\u001c8\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c2\u0001A\f'!\tA2E\u0004\u0002\u001aA9\u0011!DH\u0007\u00027)\u0011A$H\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"E\u00059\u0001/Y2lC\u001e,'\"A\u0010\n\u0005\u0011*#\u0001G%mY\u0016<\u0017\r\\!sOVlWM\u001c;Fq\u000e,\u0007\u000f^5p]*\u0011\u0011E\t\t\u0003O!j\u0011\u0001E\u0005\u0003SA\u0011ab\u00159be.$\u0006N]8xC\ndW-A\u0004nKN\u001c\u0018mZ3\u0011\u00051\u0002dBA\u0017/!\tQ\"%\u0003\u00020E\u00051\u0001K]3eK\u001aL!!\r\u001a\u0003\rM#(/\u001b8h\u0015\ty#%A\u0003dCV\u001cX\rE\u00026maj\u0011AI\u0005\u0003o\t\u0012aa\u00149uS>t\u0007C\u0001\r:\u0013\tQTEA\u0005UQJ|w/\u00192mK\u0006QQM\u001d:pe\u000ec\u0017m]:\u0011\u0007U24&A\tnKN\u001c\u0018mZ3QCJ\fW.\u001a;feN\u0004B\u0001L ,W%\u0011\u0001I\r\u0002\u0004\u001b\u0006\u0004\u0018aB2p]R,\u0007\u0010\u001e\t\u0004k\r+\u0015B\u0001##\u0005\u0015\t%O]1z!\t9c)\u0003\u0002H!\ta\u0011+^3ss\u000e{g\u000e^3yi\u00061A(\u001b8jiz\"bAS&M\u001b:{\u0005CA\u0014\u0001\u0011\u0015Qc\u00011\u0001,\u0011\u0015\u0019d\u00011\u00015\u0011\u0015Yd\u00011\u0001=\u0011\u0015id\u00011\u0001?\u0011\u0015\te\u00011\u0001C)\u0019Q\u0015KU*U-\")1h\u0002a\u0001W!)Qh\u0002a\u0001}!)\u0011i\u0002a\u0001\u0005\")Qk\u0002a\u0001W\u000591/^7nCJL\b\"B\u001a\b\u0001\u0004AD\u0003\u0002&Y3jCQa\u000f\u0005A\u0002-BQ!\u0010\u0005A\u0002yBQa\r\u0005A\u0002a\"2A\u0013/^\u0011\u0015Y\u0014\u00021\u0001,\u0011\u0015i\u0014\u00021\u0001?)\rQu\f\u0019\u0005\u0006w)\u0001\ra\u000b\u0005\u0006{)\u0001\r!\u0019\t\u0005E\u001e\\3&D\u0001d\u0015\t!W-\u0001\u0003vi&d'\"\u00014\u0002\t)\fg/Y\u0005\u0003\u0001\u000e$\"AS5\t\u000bmZ\u0001\u0019A\u0016\u0002)\u001d,G/T3tg\u0006<W\rU1sC6,G/\u001a:t)\u0005\t\u0017\u0001D4fi\u000e{g\u000eZ5uS>tG#A\u0016\u0002\u001f\u001d,G/U;fef\u001cuN\u001c;fqR$\u0012A\u0011"
)
public class SparkIllegalArgumentException extends IllegalArgumentException implements SparkThrowable {
   private final Option errorClass;
   private final Map messageParameters;
   private final QueryContext[] context;

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

   public java.util.Map getMessageParameters() {
      return .MODULE$.MapHasAsJava(this.messageParameters).asJava();
   }

   public String getCondition() {
      return (String)this.errorClass.orNull(scala..less.colon.less..MODULE$.refl());
   }

   public QueryContext[] getQueryContext() {
      return this.context;
   }

   private SparkIllegalArgumentException(final String message, final Option cause, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message, (Throwable)cause.orNull(scala..less.colon.less..MODULE$.refl()));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkIllegalArgumentException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary, final Throwable cause) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), scala.Option..MODULE$.apply(cause), scala.Option..MODULE$.apply(errorClass), messageParameters, context);
   }

   public SparkIllegalArgumentException(final String errorClass, final Map messageParameters, final Throwable cause) {
      this(errorClass, messageParameters, (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "", cause);
   }

   public SparkIllegalArgumentException(final String errorClass, final Map messageParameters) {
      this(errorClass, messageParameters, (Throwable)null);
   }

   public SparkIllegalArgumentException(final String errorClass, final java.util.Map messageParameters) {
      this(errorClass, .MODULE$.MapHasAsScala(messageParameters).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public SparkIllegalArgumentException(final String errorClass) {
      this(errorClass, scala.Predef..MODULE$.Map().empty(), (Throwable)null);
   }
}
