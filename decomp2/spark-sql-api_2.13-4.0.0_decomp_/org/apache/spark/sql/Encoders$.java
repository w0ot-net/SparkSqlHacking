package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Modifier;
import org.apache.spark.sql.catalyst.JavaTypeInference$;
import org.apache.spark.sql.catalyst.ScalaReflection$;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders$;
import org.apache.spark.sql.catalyst.encoders.JavaSerializationCodec$;
import org.apache.spark.sql.catalyst.encoders.KryoSerializationCodec$;
import org.apache.spark.sql.catalyst.encoders.RowEncoder$;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.StructType;
import scala.Function0;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.reflect.api.TypeTags;

public final class Encoders$ {
   public static final Encoders$ MODULE$ = new Encoders$();

   public Encoder BOOLEAN() {
      return AgnosticEncoders.BoxedBooleanEncoder$.MODULE$;
   }

   public Encoder BYTE() {
      return AgnosticEncoders.BoxedByteEncoder$.MODULE$;
   }

   public Encoder SHORT() {
      return AgnosticEncoders.BoxedShortEncoder$.MODULE$;
   }

   public Encoder INT() {
      return AgnosticEncoders.BoxedIntEncoder$.MODULE$;
   }

   public Encoder LONG() {
      return AgnosticEncoders.BoxedLongEncoder$.MODULE$;
   }

   public Encoder FLOAT() {
      return AgnosticEncoders.BoxedFloatEncoder$.MODULE$;
   }

   public Encoder DOUBLE() {
      return AgnosticEncoders.BoxedDoubleEncoder$.MODULE$;
   }

   public Encoder CHAR(final int length) {
      return new AgnosticEncoders.CharEncoder(length);
   }

   public Encoder VARCHAR(final int length) {
      return new AgnosticEncoders.VarcharEncoder(length);
   }

   public Encoder STRING() {
      return AgnosticEncoders.StringEncoder$.MODULE$;
   }

   public Encoder DECIMAL() {
      return AgnosticEncoders$.MODULE$.DEFAULT_JAVA_DECIMAL_ENCODER();
   }

   public Encoder DATE() {
      return AgnosticEncoders$.MODULE$.STRICT_DATE_ENCODER();
   }

   public Encoder LOCALDATE() {
      return AgnosticEncoders$.MODULE$.STRICT_LOCAL_DATE_ENCODER();
   }

   public Encoder LOCALDATETIME() {
      return AgnosticEncoders.LocalDateTimeEncoder$.MODULE$;
   }

   public Encoder TIMESTAMP() {
      return AgnosticEncoders$.MODULE$.STRICT_TIMESTAMP_ENCODER();
   }

   public Encoder INSTANT() {
      return AgnosticEncoders$.MODULE$.STRICT_INSTANT_ENCODER();
   }

   public Encoder BINARY() {
      return AgnosticEncoders.BinaryEncoder$.MODULE$;
   }

   public Encoder DURATION() {
      return AgnosticEncoders.DayTimeIntervalEncoder$.MODULE$;
   }

   public Encoder PERIOD() {
      return AgnosticEncoders.YearMonthIntervalEncoder$.MODULE$;
   }

   public Encoder bean(final Class beanClass) {
      return JavaTypeInference$.MODULE$.encoderFor(beanClass);
   }

   public Encoder row(final StructType schema) {
      return RowEncoder$.MODULE$.encoderFor(schema);
   }

   public Encoder kryo(final ClassTag evidence$1) {
      return this.genericSerializer(KryoSerializationCodec$.MODULE$, evidence$1);
   }

   public Encoder kryo(final Class clazz) {
      return this.kryo(.MODULE$.apply(clazz));
   }

   public Encoder javaSerialization(final ClassTag evidence$2) {
      return this.genericSerializer(JavaSerializationCodec$.MODULE$, evidence$2);
   }

   public Encoder javaSerialization(final Class clazz) {
      return this.javaSerialization(.MODULE$.apply(clazz));
   }

   private void validatePublicClass(final ClassTag evidence$3) {
      if (!Modifier.isPublic(scala.reflect.package..MODULE$.classTag(evidence$3).runtimeClass().getModifiers())) {
         throw ExecutionErrors$.MODULE$.notPublicClassError(scala.reflect.package..MODULE$.classTag(evidence$3).runtimeClass().getName());
      }
   }

   private Encoder genericSerializer(final Function0 provider, final ClassTag evidence$4) {
      if (scala.reflect.package..MODULE$.classTag(evidence$4).runtimeClass().isPrimitive()) {
         throw ExecutionErrors$.MODULE$.primitiveTypesNotSupportedError();
      } else {
         this.validatePublicClass(evidence$4);
         return new AgnosticEncoders.TransformingEncoder(scala.reflect.package..MODULE$.classTag(evidence$4), AgnosticEncoders.BinaryEncoder$.MODULE$, provider, AgnosticEncoders.TransformingEncoder$.MODULE$.apply$default$4());
      }
   }

   public Encoder tupleEncoder(final Seq encoders) {
      return AgnosticEncoders.ProductEncoder$.MODULE$.tuple((Seq)encoders.map((x$1) -> AgnosticEncoders$.MODULE$.agnosticEncoderFor(x$1)), AgnosticEncoders.ProductEncoder$.MODULE$.tuple$default$2());
   }

   public Encoder tuple(final Encoder e1) {
      return this.tupleEncoder(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Encoder[]{e1}));
   }

   public Encoder tuple(final Encoder e1, final Encoder e2) {
      return this.tupleEncoder(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Encoder[]{e1, e2}));
   }

   public Encoder tuple(final Encoder e1, final Encoder e2, final Encoder e3) {
      return this.tupleEncoder(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Encoder[]{e1, e2, e3}));
   }

   public Encoder tuple(final Encoder e1, final Encoder e2, final Encoder e3, final Encoder e4) {
      return this.tupleEncoder(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Encoder[]{e1, e2, e3, e4}));
   }

   public Encoder tuple(final Encoder e1, final Encoder e2, final Encoder e3, final Encoder e4, final Encoder e5) {
      return this.tupleEncoder(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Encoder[]{e1, e2, e3, e4, e5}));
   }

   public Encoder product(final TypeTags.TypeTag evidence$5) {
      return ScalaReflection$.MODULE$.encoderFor(evidence$5);
   }

   public Encoder scalaInt() {
      return AgnosticEncoders.PrimitiveIntEncoder$.MODULE$;
   }

   public Encoder scalaLong() {
      return AgnosticEncoders.PrimitiveLongEncoder$.MODULE$;
   }

   public Encoder scalaDouble() {
      return AgnosticEncoders.PrimitiveDoubleEncoder$.MODULE$;
   }

   public Encoder scalaFloat() {
      return AgnosticEncoders.PrimitiveFloatEncoder$.MODULE$;
   }

   public Encoder scalaByte() {
      return AgnosticEncoders.PrimitiveByteEncoder$.MODULE$;
   }

   public Encoder scalaShort() {
      return AgnosticEncoders.PrimitiveShortEncoder$.MODULE$;
   }

   public Encoder scalaBoolean() {
      return AgnosticEncoders.PrimitiveBooleanEncoder$.MODULE$;
   }

   private Encoders$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
