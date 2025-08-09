package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3Qa\u0003\u0007\u0001\u0019IA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\t]\u0001\u0011\t\u0011)A\u0005_!A1\u0007\u0001B\u0001B\u0003%A\u0007\u0003\u00058\u0001\t\u0005\t\u0015!\u00039\u0011\u0015q\u0004\u0001\"\u0003@\u0011\u0015q\u0004\u0001\"\u0001F\u0011\u0015q\u0004\u0001\"\u0001L\u0011\u0015y\u0005\u0001\"\u0011Q\u0011\u00151\u0006\u0001\"\u0011X\u0011\u0015A\u0006\u0001\"\u0011Z\u0005a\u0019\u0006/\u0019:l\u0003JLG\u000f[7fi&\u001cW\t_2faRLwN\u001c\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sON\u0019\u0001aE\u000e\u0011\u0005QIR\"A\u000b\u000b\u0005Y9\u0012\u0001\u00027b]\u001eT\u0011\u0001G\u0001\u0005U\u00064\u0018-\u0003\u0002\u001b+\t\u0019\u0012I]5uQ6,G/[2Fq\u000e,\u0007\u000f^5p]B\u0011A$H\u0007\u0002\u0019%\u0011a\u0004\u0004\u0002\u000f'B\f'o\u001b+ie><\u0018M\u00197f\u0003\u001diWm]:bO\u0016\u001c\u0001\u0001\u0005\u0002#W9\u00111%\u000b\t\u0003I\u001dj\u0011!\n\u0006\u0003M\u0001\na\u0001\u0010:p_Rt$\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):\u0013A\u0002)sK\u0012,g-\u0003\u0002-[\t11\u000b\u001e:j]\u001eT!AK\u0014\u0002\u0015\u0015\u0014(o\u001c:DY\u0006\u001c8\u000fE\u00021c\u0005j\u0011aJ\u0005\u0003e\u001d\u0012aa\u00149uS>t\u0017!E7fgN\fw-\u001a)be\u0006lW\r^3sgB!!%N\u0011\"\u0013\t1TFA\u0002NCB\fqaY8oi\u0016DH\u000fE\u00021smJ!AO\u0014\u0003\u000b\u0005\u0013(/Y=\u0011\u0005qa\u0014BA\u001f\r\u00051\tV/\u001a:z\u0007>tG/\u001a=u\u0003\u0019a\u0014N\\5u}Q)\u0001)\u0011\"D\tB\u0011A\u0004\u0001\u0005\u0006?\u0015\u0001\r!\t\u0005\u0006]\u0015\u0001\ra\f\u0005\u0006g\u0015\u0001\r\u0001\u000e\u0005\u0006o\u0015\u0001\r\u0001\u000f\u000b\u0006\u0001\u001a;\u0005*\u0013\u0005\u0006]\u0019\u0001\r!\t\u0005\u0006g\u0019\u0001\r\u0001\u000e\u0005\u0006o\u0019\u0001\r\u0001\u000f\u0005\u0006\u0015\u001a\u0001\r!I\u0001\bgVlW.\u0019:z)\u0011\u0001E*\u0014(\t\u000b9:\u0001\u0019A\u0011\t\u000bM:\u0001\u0019\u0001\u001b\t\u000b]:\u0001\u0019\u0001\u001d\u0002)\u001d,G/T3tg\u0006<W\rU1sC6,G/\u001a:t)\u0005\t\u0006\u0003\u0002*VC\u0005j\u0011a\u0015\u0006\u0003)^\tA!\u001e;jY&\u0011agU\u0001\rO\u0016$8i\u001c8eSRLwN\u001c\u000b\u0002C\u0005yq-\u001a;Rk\u0016\u0014\u0018pQ8oi\u0016DH\u000fF\u00019\u0001"
)
public class SparkArithmeticException extends ArithmeticException implements SparkThrowable {
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

   private SparkArithmeticException(final String message, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkArithmeticException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), scala.Option..MODULE$.apply(errorClass), messageParameters, context);
   }

   public SparkArithmeticException(final String errorClass, final Map messageParameters, final QueryContext[] context) {
      this(errorClass, messageParameters, context, "");
   }
}
