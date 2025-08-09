package org.apache.spark.sql.streaming;

import java.io.Serializable;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkThrowable;
import org.apache.spark.SparkThrowableHelper$;
import org.apache.spark.annotation.Evolving;
import scala.Predef;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da\u0001\u0002\f\u0018\u0001\tB\u0001\"\u000e\u0001\u0003\u0006\u0004%IA\u000e\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005o!A\u0001\t\u0001BC\u0002\u0013\u0005a\u0007\u0003\u0005B\u0001\t\u0005\t\u0015!\u00038\u0011!\u0011\u0005A!b\u0001\n\u0003\u0019\u0005\u0002C$\u0001\u0005\u0003\u0005\u000b\u0011\u0002#\t\u0011!\u0003!Q1A\u0005\u0002YB\u0001\"\u0013\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\t\u0015\u0002\u0011)\u0019!C\u0001m!A1\n\u0001B\u0001B\u0003%q\u0007\u0003\u0005M\u0001\t\u0005\t\u0015!\u00038\u0011!i\u0005A!A!\u0002\u0013q\u0005BB)\u0001\t\u0003I\"\u000b\u0003\u0004R\u0001\u0011\u00051\u0004\u0018\u0005\u0006#\u0002!\t!\u0019\u0005\bQ\u0002\u0011\r\u0011\"\u0001j\u0011\u0019q\u0007\u0001)A\u0005U\")q\u000e\u0001C!a\")\u0011\u000f\u0001C!a\")!\u000f\u0001C!a\")1\u000f\u0001C!i\n92\u000b\u001e:fC6LgnZ)vKJLX\t_2faRLwN\u001c\u0006\u00031e\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005iY\u0012aA:rY*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0019\u0013\u0007\u0005\u0002%]9\u0011Qe\u000b\b\u0003M%j\u0011a\n\u0006\u0003Q\u0005\na\u0001\u0010:p_Rt\u0014\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051j\u0013a\u00029bG.\fw-\u001a\u0006\u0002U%\u0011q\u0006\r\u0002\n\u000bb\u001cW\r\u001d;j_:T!\u0001L\u0017\u0011\u0005I\u001aT\"A\u000e\n\u0005QZ\"AD*qCJ\\G\u000b\u001b:po\u0006\u0014G.Z\u0001\u0011cV,'/\u001f#fEV<7\u000b\u001e:j]\u001e,\u0012a\u000e\t\u0003qqr!!\u000f\u001e\u0011\u0005\u0019j\u0013BA\u001e.\u0003\u0019\u0001&/\u001a3fM&\u0011QH\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005mj\u0013!E9vKJLH)\u001a2vON#(/\u001b8hA\u00059Q.Z:tC\u001e,\u0017\u0001C7fgN\fw-\u001a\u0011\u0002\u000b\r\fWo]3\u0016\u0003\u0011\u0003\"\u0001J#\n\u0005\u0019\u0003$!\u0003+ie><\u0018M\u00197f\u0003\u0019\u0019\u0017-^:fA\u0005Y1\u000f^1si>3gm]3u\u00031\u0019H/\u0019:u\u001f\u001a47/\u001a;!\u0003%)g\u000eZ(gMN,G/\u0001\u0006f]\u0012|eMZ:fi\u0002\n!\"\u001a:s_J\u001cE.Y:t\u0003EiWm]:bO\u0016\u0004\u0016M]1nKR,'o\u001d\t\u0005q=;t'\u0003\u0002Q}\t\u0019Q*\u00199\u0002\rqJg.\u001b;?)!\u0019VKV,Y3j[\u0006C\u0001+\u0001\u001b\u00059\u0002\"B\u001b\u000e\u0001\u00049\u0004\"\u0002!\u000e\u0001\u00049\u0004\"\u0002\"\u000e\u0001\u0004!\u0005\"\u0002%\u000e\u0001\u00049\u0004\"\u0002&\u000e\u0001\u00049\u0004\"\u0002'\u000e\u0001\u00049\u0004\"B'\u000e\u0001\u0004qE#B*^=~\u0003\u0007\"\u0002!\u000f\u0001\u00049\u0004\"\u0002\"\u000f\u0001\u0004!\u0005\"\u0002'\u000f\u0001\u00049\u0004\"B'\u000f\u0001\u0004qEcB*cG\u0012,gm\u001a\u0005\u0006k=\u0001\ra\u000e\u0005\u0006\u0005>\u0001\r\u0001\u0012\u0005\u0006\u0011>\u0001\ra\u000e\u0005\u0006\u0015>\u0001\ra\u000e\u0005\u0006\u0019>\u0001\ra\u000e\u0005\u0006\u001b>\u0001\rAT\u0001\u0005i&lW-F\u0001k!\tYG.D\u0001.\u0013\tiWF\u0001\u0003M_:<\u0017!\u0002;j[\u0016\u0004\u0013AC4fi6+7o]1hKR\tq'\u0001\u0005u_N#(/\u001b8h\u000319W\r^\"p]\u0012LG/[8o\u0003Q9W\r^'fgN\fw-\u001a)be\u0006lW\r^3sgR\tQ\u000f\u0005\u0003ww^:T\"A<\u000b\u0005aL\u0018\u0001B;uS2T\u0011A_\u0001\u0005U\u00064\u0018-\u0003\u0002Qo\"\u0012\u0001! \t\u0004}\u0006\rQ\"A@\u000b\u0007\u0005\u00051$\u0001\u0006b]:|G/\u0019;j_:L1!!\u0002\u0000\u0005!)eo\u001c7wS:<\u0007"
)
public class StreamingQueryException extends Exception implements SparkThrowable {
   private final String queryDebugString;
   private final String message;
   private final Throwable cause;
   private final String startOffset;
   private final String endOffset;
   private final String errorClass;
   private final Map messageParameters;
   private final long time;

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

   private String queryDebugString() {
      return this.queryDebugString;
   }

   public String message() {
      return this.message;
   }

   public Throwable cause() {
      return this.cause;
   }

   public String startOffset() {
      return this.startOffset;
   }

   public String endOffset() {
      return this.endOffset;
   }

   public long time() {
      return this.time;
   }

   public String getMessage() {
      if (this.queryDebugString().isEmpty()) {
         return this.message();
      } else {
         String var10000 = this.message();
         return var10000 + "\n" + this.queryDebugString();
      }
   }

   public String toString() {
      StringOps var10000 = .MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      String var10002 = StreamingQueryException.class.getName();
      return var10000.stripMargin$extension(var10001.augmentString(var10002 + ": " + this.cause().getMessage() + "\n       |" + this.queryDebugString()));
   }

   public String getCondition() {
      return this.errorClass;
   }

   public java.util.Map getMessageParameters() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.messageParameters).asJava();
   }

   public StreamingQueryException(final String queryDebugString, final String message, final Throwable cause, final String startOffset, final String endOffset, final String errorClass, final Map messageParameters) {
      super(message, cause);
      this.queryDebugString = queryDebugString;
      this.message = message;
      this.cause = cause;
      this.startOffset = startOffset;
      this.endOffset = endOffset;
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.time = System.currentTimeMillis();
   }

   public StreamingQueryException(final String message, final Throwable cause, final String errorClass, final Map messageParameters) {
      this((String)messageParameters.getOrElse("queryDebugString", new Serializable() {
         private static final long serialVersionUID = 0L;

         public final String apply() {
            return "";
         }
      }), message, cause, (String)messageParameters.getOrElse("startOffset", new Serializable() {
         private static final long serialVersionUID = 0L;

         public final String apply() {
            return "";
         }
      }), (String)messageParameters.getOrElse("endOffset", new Serializable() {
         private static final long serialVersionUID = 0L;

         public final String apply() {
            return "";
         }
      }), errorClass, messageParameters);
   }

   public StreamingQueryException(final String queryDebugString, final Throwable cause, final String startOffset, final String endOffset, final String errorClass, final Map messageParameters) {
      this(queryDebugString, SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters), cause, startOffset, endOffset, errorClass, messageParameters);
   }
}
