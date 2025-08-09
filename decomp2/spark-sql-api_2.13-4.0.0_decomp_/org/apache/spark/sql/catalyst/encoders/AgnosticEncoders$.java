package org.apache.spark.sql.catalyst.encoders;

import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.DecimalType$;
import scala.Predef.;

public final class AgnosticEncoders$ {
   public static final AgnosticEncoders$ MODULE$ = new AgnosticEncoders$();
   private static final AgnosticEncoders.DateEncoder STRICT_DATE_ENCODER = new AgnosticEncoders.DateEncoder(false);
   private static final AgnosticEncoders.LocalDateEncoder STRICT_LOCAL_DATE_ENCODER = new AgnosticEncoders.LocalDateEncoder(false);
   private static final AgnosticEncoders.TimestampEncoder STRICT_TIMESTAMP_ENCODER = new AgnosticEncoders.TimestampEncoder(false);
   private static final AgnosticEncoders.InstantEncoder STRICT_INSTANT_ENCODER = new AgnosticEncoders.InstantEncoder(false);
   private static final AgnosticEncoders.DateEncoder LENIENT_DATE_ENCODER = new AgnosticEncoders.DateEncoder(true);
   private static final AgnosticEncoders.LocalDateEncoder LENIENT_LOCAL_DATE_ENCODER = new AgnosticEncoders.LocalDateEncoder(true);
   private static final AgnosticEncoders.TimestampEncoder LENIENT_TIMESTAMP_ENCODER = new AgnosticEncoders.TimestampEncoder(true);
   private static final AgnosticEncoders.InstantEncoder LENIENT_INSTANT_ENCODER = new AgnosticEncoders.InstantEncoder(true);
   private static final AgnosticEncoders.SparkDecimalEncoder DEFAULT_SPARK_DECIMAL_ENCODER;
   private static final AgnosticEncoders.ScalaDecimalEncoder DEFAULT_SCALA_DECIMAL_ENCODER;
   private static final AgnosticEncoders.JavaDecimalEncoder DEFAULT_JAVA_DECIMAL_ENCODER;

   static {
      DEFAULT_SPARK_DECIMAL_ENCODER = new AgnosticEncoders.SparkDecimalEncoder(DecimalType$.MODULE$.SYSTEM_DEFAULT());
      DEFAULT_SCALA_DECIMAL_ENCODER = new AgnosticEncoders.ScalaDecimalEncoder(DecimalType$.MODULE$.SYSTEM_DEFAULT());
      DEFAULT_JAVA_DECIMAL_ENCODER = new AgnosticEncoders.JavaDecimalEncoder(DecimalType$.MODULE$.SYSTEM_DEFAULT(), false);
   }

   public AgnosticEncoder agnosticEncoderFor(final Encoder evidence$1) {
      Encoder var3 = (Encoder).MODULE$.implicitly(evidence$1);
      if (var3 instanceof AgnosticEncoder var4) {
         return var4;
      } else if (var3 instanceof ToAgnosticEncoder) {
         return ((ToAgnosticEncoder)var3).encoder();
      } else {
         throw ExecutionErrors$.MODULE$.invalidAgnosticEncoderError(var3);
      }
   }

   public AgnosticEncoders.DateEncoder STRICT_DATE_ENCODER() {
      return STRICT_DATE_ENCODER;
   }

   public AgnosticEncoders.LocalDateEncoder STRICT_LOCAL_DATE_ENCODER() {
      return STRICT_LOCAL_DATE_ENCODER;
   }

   public AgnosticEncoders.TimestampEncoder STRICT_TIMESTAMP_ENCODER() {
      return STRICT_TIMESTAMP_ENCODER;
   }

   public AgnosticEncoders.InstantEncoder STRICT_INSTANT_ENCODER() {
      return STRICT_INSTANT_ENCODER;
   }

   public AgnosticEncoders.DateEncoder LENIENT_DATE_ENCODER() {
      return LENIENT_DATE_ENCODER;
   }

   public AgnosticEncoders.LocalDateEncoder LENIENT_LOCAL_DATE_ENCODER() {
      return LENIENT_LOCAL_DATE_ENCODER;
   }

   public AgnosticEncoders.TimestampEncoder LENIENT_TIMESTAMP_ENCODER() {
      return LENIENT_TIMESTAMP_ENCODER;
   }

   public AgnosticEncoders.InstantEncoder LENIENT_INSTANT_ENCODER() {
      return LENIENT_INSTANT_ENCODER;
   }

   public AgnosticEncoders.SparkDecimalEncoder DEFAULT_SPARK_DECIMAL_ENCODER() {
      return DEFAULT_SPARK_DECIMAL_ENCODER;
   }

   public AgnosticEncoders.ScalaDecimalEncoder DEFAULT_SCALA_DECIMAL_ENCODER() {
      return DEFAULT_SCALA_DECIMAL_ENCODER;
   }

   public AgnosticEncoders.JavaDecimalEncoder DEFAULT_JAVA_DECIMAL_ENCODER() {
      return DEFAULT_JAVA_DECIMAL_ENCODER;
   }

   private AgnosticEncoders$() {
   }
}
