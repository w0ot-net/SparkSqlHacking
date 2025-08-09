package org.apache.spark;

import java.io.Serializable;
import java.time.DateTimeException;
import scala.None;
import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000594Q!\u0004\b\u0001\u001dQA\u0001\"\t\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\ta\u0001\u0011\t\u0011)A\u0005c!AQ\u0007\u0001B\u0001B\u0003%a\u0007\u0003\u0005:\u0001\t\u0005\t\u0015!\u0003;\u0011!\u0001\u0005A!A!\u0002\u0013\t\u0005\"B&\u0001\t\u0013a\u0005\"B&\u0001\t\u0003\u0019\u0006\"B&\u0001\t\u0003I\u0006\"B&\u0001\t\u0003y\u0006\"B2\u0001\t\u0003\"\u0007\"\u00026\u0001\t\u0003Z\u0007\"\u00027\u0001\t\u0003j'AF*qCJ\\G)\u0019;f)&lW-\u0012=dKB$\u0018n\u001c8\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c2\u0001A\u000b\u001e!\t12$D\u0001\u0018\u0015\tA\u0012$\u0001\u0003uS6,'\"\u0001\u000e\u0002\t)\fg/Y\u0005\u00039]\u0011\u0011\u0003R1uKRKW.Z#yG\u0016\u0004H/[8o!\tqr$D\u0001\u000f\u0013\t\u0001cB\u0001\bTa\u0006\u00148\u000e\u00165s_^\f'\r\\3\u0002\u000f5,7o]1hK\u000e\u0001\u0001C\u0001\u0013.\u001d\t)3\u0006\u0005\u0002'S5\tqE\u0003\u0002)E\u00051AH]8pizR\u0011AK\u0001\u0006g\u000e\fG.Y\u0005\u0003Y%\na\u0001\u0015:fI\u00164\u0017B\u0001\u00180\u0005\u0019\u0019FO]5oO*\u0011A&K\u0001\u000bKJ\u0014xN]\"mCN\u001c\bc\u0001\u001a4G5\t\u0011&\u0003\u00025S\t1q\n\u001d;j_:\f\u0011#\\3tg\u0006<W\rU1sC6,G/\u001a:t!\u0011!sgI\u0012\n\u0005az#aA'ba\u000691m\u001c8uKb$\bc\u0001\u001a<{%\u0011A(\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003=yJ!a\u0010\b\u0003\u0019E+XM]=D_:$X\r\u001f;\u0002\u000b\r\fWo]3\u0011\u0007I\u001a$\t\u0005\u0002D\u0011:\u0011AI\u0012\b\u0003M\u0015K\u0011AK\u0005\u0003\u000f&\nq\u0001]1dW\u0006<W-\u0003\u0002J\u0015\nIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0003\u000f&\na\u0001P5oSRtDCB'O\u001fB\u000b&\u000b\u0005\u0002\u001f\u0001!)\u0011E\u0002a\u0001G!)\u0001G\u0002a\u0001c!)QG\u0002a\u0001m!)\u0011H\u0002a\u0001u!)\u0001I\u0002a\u0001\u0003R)Q\nV+W/\")\u0001g\u0002a\u0001G!)Qg\u0002a\u0001m!)\u0011h\u0002a\u0001u!)\u0001l\u0002a\u0001G\u000591/^7nCJLHCB'[7rkf\fC\u00031\u0011\u0001\u00071\u0005C\u00036\u0011\u0001\u0007a\u0007C\u0003:\u0011\u0001\u0007!\bC\u0003Y\u0011\u0001\u00071\u0005C\u0003A\u0011\u0001\u0007\u0011\t\u0006\u0003NA\u0006\u0014\u0007\"\u0002\u0019\n\u0001\u0004\u0019\u0003\"B\u001b\n\u0001\u00041\u0004\"B\u001d\n\u0001\u0004Q\u0014\u0001F4fi6+7o]1hKB\u000b'/Y7fi\u0016\u00148\u000fF\u0001f!\u00111\u0017nI\u0012\u000e\u0003\u001dT!\u0001[\r\u0002\tU$\u0018\u000e\\\u0005\u0003q\u001d\fAbZ3u\u0007>tG-\u001b;j_:$\u0012aI\u0001\u0010O\u0016$\u0018+^3ss\u000e{g\u000e^3yiR\t!\b"
)
public class SparkDateTimeException extends DateTimeException implements SparkThrowable {
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

   private SparkDateTimeException(final String message, final Option errorClass, final Map messageParameters, final QueryContext[] context, final Option cause) {
      super(message, (Throwable)cause.orNull(scala..less.colon.less..MODULE$.refl()));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkDateTimeException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), (Option)scala.Option..MODULE$.apply(errorClass), (Map)messageParameters, (QueryContext[])context, scala.None..MODULE$);
   }

   public SparkDateTimeException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary, final Option cause) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), scala.Option..MODULE$.apply(errorClass), messageParameters, context, cause.orElse(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final None apply() {
            return scala.None..MODULE$;
         }
      }));
   }

   public SparkDateTimeException(final String errorClass, final Map messageParameters, final QueryContext[] context) {
      this(errorClass, messageParameters, context, "");
   }
}
