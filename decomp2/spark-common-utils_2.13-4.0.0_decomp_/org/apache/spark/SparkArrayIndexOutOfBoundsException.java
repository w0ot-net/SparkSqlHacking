package org.apache.spark;

import scala.Option;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3Qa\u0003\u0007\u0001\u0019IA\u0001B\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\t_\u0001\u0011\t\u0011)A\u0005a!AA\u0007\u0001B\u0001B\u0003%Q\u0007\u0003\u00059\u0001\t\u0005\t\u0015!\u0003:\u0011\u0015y\u0004\u0001\"\u0003A\u0011\u0015y\u0004\u0001\"\u0001G\u0011\u0015y\u0004\u0001\"\u0001M\u0011\u0015\u0001\u0006\u0001\"\u0011R\u0011\u0015I\u0006\u0001\"\u0011[\u0011\u0015Y\u0006\u0001\"\u0011]\u0005\r\u001a\u0006/\u0019:l\u0003J\u0014\u0018-_%oI\u0016Dx*\u001e;PM\n{WO\u001c3t\u000bb\u001cW\r\u001d;j_:T!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'oZ\n\u0004\u0001M\u0011\u0003C\u0001\u000b \u001d\t)BD\u0004\u0002\u001755\tqC\u0003\u0002\u00193\u00051AH]8piz\u001a\u0001!C\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\tib$A\u0004qC\u000e\\\u0017mZ3\u000b\u0003mI!\u0001I\u0011\u0003=\u0005\u0013(/Y=J]\u0012,\u0007pT;u\u001f\u001a\u0014u.\u001e8eg\u0016C8-\u001a9uS>t'BA\u000f\u001f!\t\u0019C%D\u0001\r\u0013\t)CB\u0001\bTa\u0006\u00148\u000e\u00165s_^\f'\r\\3\u0002\u000f5,7o]1hKB\u0011\u0001\u0006\f\b\u0003S)\u0002\"A\u0006\u0010\n\u0005-r\u0012A\u0002)sK\u0012,g-\u0003\u0002.]\t11\u000b\u001e:j]\u001eT!a\u000b\u0010\u0002\u0015\u0015\u0014(o\u001c:DY\u0006\u001c8\u000fE\u00022e\u001dj\u0011AH\u0005\u0003gy\u0011aa\u00149uS>t\u0017!E7fgN\fw-\u001a)be\u0006lW\r^3sgB!\u0001FN\u0014(\u0013\t9dFA\u0002NCB\fqaY8oi\u0016DH\u000fE\u00022uqJ!a\u000f\u0010\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\rj\u0014B\u0001 \r\u00051\tV/\u001a:z\u0007>tG/\u001a=u\u0003\u0019a\u0014N\\5u}Q)\u0011IQ\"E\u000bB\u00111\u0005\u0001\u0005\u0006M\u0015\u0001\ra\n\u0005\u0006_\u0015\u0001\r\u0001\r\u0005\u0006i\u0015\u0001\r!\u000e\u0005\u0006q\u0015\u0001\r!\u000f\u000b\u0006\u0003\u001eC\u0015J\u0013\u0005\u0006_\u0019\u0001\ra\n\u0005\u0006i\u0019\u0001\r!\u000e\u0005\u0006q\u0019\u0001\r!\u000f\u0005\u0006\u0017\u001a\u0001\raJ\u0001\bgVlW.\u0019:z)\u0011\tUJT(\t\u000b=:\u0001\u0019A\u0014\t\u000bQ:\u0001\u0019A\u001b\t\u000ba:\u0001\u0019A\u001d\u0002)\u001d,G/T3tg\u0006<W\rU1sC6,G/\u001a:t)\u0005\u0011\u0006\u0003B*YO\u001dj\u0011\u0001\u0016\u0006\u0003+Z\u000bA!\u001e;jY*\tq+\u0001\u0003kCZ\f\u0017BA\u001cU\u000319W\r^\"p]\u0012LG/[8o)\u00059\u0013aD4fiF+XM]=D_:$X\r\u001f;\u0015\u0003e\u0002"
)
public class SparkArrayIndexOutOfBoundsException extends ArrayIndexOutOfBoundsException implements SparkThrowable {
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

   private SparkArrayIndexOutOfBoundsException(final String message, final Option errorClass, final Map messageParameters, final QueryContext[] context) {
      super(message);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
      this.context = context;
   }

   public SparkArrayIndexOutOfBoundsException(final String errorClass, final Map messageParameters, final QueryContext[] context, final String summary) {
      this(SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, summary), scala.Option..MODULE$.apply(errorClass), messageParameters, context);
   }

   public SparkArrayIndexOutOfBoundsException(final String errorClass, final Map messageParameters, final QueryContext[] context) {
      this(errorClass, messageParameters, context, "");
   }
}
