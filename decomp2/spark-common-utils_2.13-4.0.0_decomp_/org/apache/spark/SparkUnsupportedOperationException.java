package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514Qa\u0004\t\u0001!YA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tg\u0001\u0011\t\u0011)A\u0005i!A\u0001\b\u0001B\u0001B\u0003%\u0011\bC\u0003=\u0001\u0011%Q\bC\u0003=\u0001\u0011\u0005!\tC\u0003=\u0001\u0011\u0005Q\tC\u0003=\u0001\u0011\u0005q\nC\u0003R\u0001\u0011\u0005#\u000bC\u0003T\u0001\u0011\u0005Ck\u0002\u0004V!!\u0005\u0001C\u0016\u0004\u0007\u001fAA\t\u0001E,\t\u000bqZA\u0011A1\t\u000b\t\\A\u0011A2\t\u000f\u0011\\\u0011\u0011!C\u0005K\n\u00113\u000b]1sWVs7/\u001e9q_J$X\rZ(qKJ\fG/[8o\u000bb\u001cW\r\u001d;j_:T!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\n\u0004\u0001]1\u0003C\u0001\r$\u001d\tI\u0002E\u0004\u0002\u001b=5\t1D\u0003\u0002\u001d;\u00051AH]8piz\u001a\u0001!C\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\t#%A\u0004qC\u000e\\\u0017mZ3\u000b\u0003}I!\u0001J\u0013\u0003;Us7/\u001e9q_J$X\rZ(qKJ\fG/[8o\u000bb\u001cW\r\u001d;j_:T!!\t\u0012\u0011\u0005\u001dBS\"\u0001\t\n\u0005%\u0002\"AD*qCJ\\G\u000b\u001b:po\u0006\u0014G.Z\u0001\b[\u0016\u001c8/Y4f!\ta\u0003G\u0004\u0002.]A\u0011!DI\u0005\u0003_\t\na\u0001\u0015:fI\u00164\u0017BA\u00193\u0005\u0019\u0019FO]5oO*\u0011qFI\u0001\u000bKJ\u0014xN]\"mCN\u001c\bcA\u001b7W5\t!%\u0003\u00028E\t1q\n\u001d;j_:\f\u0011#\\3tg\u0006<W\rU1sC6,G/\u001a:t!\u0011a#hK\u0016\n\u0005m\u0012$aA'ba\u00061A(\u001b8jiz\"BAP A\u0003B\u0011q\u0005\u0001\u0005\u0006U\u0011\u0001\ra\u000b\u0005\u0006g\u0011\u0001\r\u0001\u000e\u0005\u0006q\u0011\u0001\r!\u000f\u000b\u0004}\r#\u0005\"B\u001a\u0006\u0001\u0004Y\u0003\"\u0002\u001d\u0006\u0001\u0004IDc\u0001 G\u000f\")1G\u0002a\u0001W!)\u0001H\u0002a\u0001\u0011B!\u0011JT\u0016,\u001b\u0005Q%BA&M\u0003\u0011)H/\u001b7\u000b\u00035\u000bAA[1wC&\u00111H\u0013\u000b\u0003}ACQaM\u0004A\u0002-\nAcZ3u\u001b\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001cH#\u0001%\u0002\u0019\u001d,GoQ8oI&$\u0018n\u001c8\u0015\u0003-\n!e\u00159be.,fn];qa>\u0014H/\u001a3Pa\u0016\u0014\u0018\r^5p]\u0016C8-\u001a9uS>t\u0007CA\u0014\f'\rY\u0001l\u0017\t\u0003keK!A\u0017\u0012\u0003\r\u0005s\u0017PU3g!\tav,D\u0001^\u0015\tqF*\u0001\u0002j_&\u0011\u0001-\u0018\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0002-\u0006)\u0011\r\u001d9msR\ta(\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001g!\t9'.D\u0001i\u0015\tIG*\u0001\u0003mC:<\u0017BA6i\u0005\u0019y%M[3di\u0002"
)
public class SparkUnsupportedOperationException extends UnsupportedOperationException implements SparkThrowable {
   private final Option errorClass;
   private final Map messageParameters;

   public static SparkUnsupportedOperationException apply() {
      return SparkUnsupportedOperationException$.MODULE$.apply();
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
      return (String)this.errorClass.orNull(scala..less.colon.less..MODULE$.refl());
   }

   private SparkUnsupportedOperationException(final String message, final Option errorClass, final Map messageParameters) {
      super(message);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }

   public SparkUnsupportedOperationException(final String errorClass, final Map messageParameters) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters), scala.Option..MODULE$.apply(errorClass), messageParameters);
   }

   public SparkUnsupportedOperationException(final String errorClass, final java.util.Map messageParameters) {
      this(errorClass, .MODULE$.MapHasAsScala(messageParameters).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public SparkUnsupportedOperationException(final String errorClass) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, scala.Predef..MODULE$.Map().empty()), scala.Option..MODULE$.apply(errorClass), scala.Predef..MODULE$.Map().empty());
   }
}
