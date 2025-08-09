package org.apache.spark.sql.catalyst.encoders;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.errors.DataTypeErrorsBase;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.BinaryType$;
import org.apache.spark.sql.types.BooleanType$;
import org.apache.spark.sql.types.ByteType$;
import org.apache.spark.sql.types.CalendarIntervalType$;
import org.apache.spark.sql.types.CharType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DateType$;
import org.apache.spark.sql.types.DayTimeIntervalType;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.DoubleType$;
import org.apache.spark.sql.types.FloatType$;
import org.apache.spark.sql.types.IntegerType$;
import org.apache.spark.sql.types.LongType$;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.NullType$;
import org.apache.spark.sql.types.PythonUserDefinedType;
import org.apache.spark.sql.types.SQLUserDefinedType;
import org.apache.spark.sql.types.ShortType$;
import org.apache.spark.sql.types.StringHelper$;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.TimestampNTZType$;
import org.apache.spark.sql.types.TimestampType$;
import org.apache.spark.sql.types.UDTRegistration$;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.sql.types.VarcharType;
import org.apache.spark.sql.types.VariantType;
import org.apache.spark.sql.types.YearMonthIntervalType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Tuple2;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.reflect.package.;

public final class RowEncoder$ implements DataTypeErrorsBase {
   public static final RowEncoder$ MODULE$ = new RowEncoder$();

   static {
      DataTypeErrorsBase.$init$(MODULE$);
   }

   public String toSQLId(final String parts) {
      return DataTypeErrorsBase.toSQLId$(this, (String)parts);
   }

   public String toSQLId(final Seq parts) {
      return DataTypeErrorsBase.toSQLId$(this, (Seq)parts);
   }

   public String toSQLStmt(final String text) {
      return DataTypeErrorsBase.toSQLStmt$(this, text);
   }

   public String toSQLConf(final String conf) {
      return DataTypeErrorsBase.toSQLConf$(this, conf);
   }

   public String toSQLType(final String text) {
      return DataTypeErrorsBase.toSQLType$(this, (String)text);
   }

   public String toSQLType(final AbstractDataType t) {
      return DataTypeErrorsBase.toSQLType$(this, (AbstractDataType)t);
   }

   public String toSQLValue(final String value) {
      return DataTypeErrorsBase.toSQLValue$(this, (String)value);
   }

   public String toSQLValue(final UTF8String value) {
      return DataTypeErrorsBase.toSQLValue$(this, (UTF8String)value);
   }

   public String toSQLValue(final short value) {
      return DataTypeErrorsBase.toSQLValue$(this, (short)value);
   }

   public String toSQLValue(final int value) {
      return DataTypeErrorsBase.toSQLValue$(this, (int)value);
   }

   public String toSQLValue(final long value) {
      return DataTypeErrorsBase.toSQLValue$(this, value);
   }

   public String toSQLValue(final float value) {
      return DataTypeErrorsBase.toSQLValue$(this, value);
   }

   public String toSQLValue(final double value) {
      return DataTypeErrorsBase.toSQLValue$(this, value);
   }

   public String quoteByDefault(final String elem) {
      return DataTypeErrorsBase.quoteByDefault$(this, elem);
   }

   public String getSummary(final QueryContext sqlContext) {
      return DataTypeErrorsBase.getSummary$(this, sqlContext);
   }

   public QueryContext[] getQueryContext(final QueryContext context) {
      return DataTypeErrorsBase.getQueryContext$(this, context);
   }

   public String toDSOption(final String option) {
      return DataTypeErrorsBase.toDSOption$(this, option);
   }

   public AgnosticEncoder encoderFor(final StructType schema) {
      return this.encoderFor(schema, false);
   }

   public AgnosticEncoder encoderFor(final StructType schema, final boolean lenient) {
      return this.encoderForDataType(schema, lenient);
   }

   public AgnosticEncoder encoderForDataType(final DataType dataType, final boolean lenient) {
      while(true) {
         boolean var5 = false;
         Object var6 = null;
         boolean var7 = false;
         Object var8 = null;
         if (NullType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.NullEncoder$.MODULE$;
         }

         if (BooleanType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BoxedBooleanEncoder$.MODULE$;
         }

         if (ByteType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BoxedByteEncoder$.MODULE$;
         }

         if (ShortType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BoxedShortEncoder$.MODULE$;
         }

         if (IntegerType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BoxedIntEncoder$.MODULE$;
         }

         if (LongType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BoxedLongEncoder$.MODULE$;
         }

         if (FloatType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BoxedFloatEncoder$.MODULE$;
         }

         if (DoubleType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BoxedDoubleEncoder$.MODULE$;
         }

         if (dataType instanceof DecimalType var10) {
            return new AgnosticEncoders.JavaDecimalEncoder(var10, true);
         }

         if (BinaryType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.BinaryEncoder$.MODULE$;
         }

         if (dataType instanceof CharType var11) {
            int length = var11.length();
            if (SqlApiConf$.MODULE$.get().preserveCharVarcharTypeInfo()) {
               return new AgnosticEncoders.CharEncoder(length);
            }
         }

         if (dataType instanceof VarcharType var13) {
            int length = var13.length();
            if (SqlApiConf$.MODULE$.get().preserveCharVarcharTypeInfo()) {
               return new AgnosticEncoders.VarcharEncoder(length);
            }
         }

         if (dataType instanceof StringType var15) {
            if (StringHelper$.MODULE$.isPlainString(var15)) {
               return AgnosticEncoders.StringEncoder$.MODULE$;
            }
         }

         if (TimestampType$.MODULE$.equals(dataType)) {
            var5 = true;
            if (SqlApiConf$.MODULE$.get().datetimeJava8ApiEnabled()) {
               return new AgnosticEncoders.InstantEncoder(lenient);
            }
         }

         if (var5) {
            return new AgnosticEncoders.TimestampEncoder(lenient);
         }

         if (TimestampNTZType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.LocalDateTimeEncoder$.MODULE$;
         }

         if (DateType$.MODULE$.equals(dataType)) {
            var7 = true;
            if (SqlApiConf$.MODULE$.get().datetimeJava8ApiEnabled()) {
               return new AgnosticEncoders.LocalDateEncoder(lenient);
            }
         }

         if (var7) {
            return new AgnosticEncoders.DateEncoder(lenient);
         }

         if (CalendarIntervalType$.MODULE$.equals(dataType)) {
            return AgnosticEncoders.CalendarIntervalEncoder$.MODULE$;
         }

         if (dataType instanceof DayTimeIntervalType) {
            return AgnosticEncoders.DayTimeIntervalEncoder$.MODULE$;
         }

         if (dataType instanceof YearMonthIntervalType) {
            return AgnosticEncoders.YearMonthIntervalEncoder$.MODULE$;
         }

         if (dataType instanceof VariantType) {
            return AgnosticEncoders.VariantEncoder$.MODULE$;
         }

         if (!(dataType instanceof PythonUserDefinedType)) {
            if (dataType instanceof UserDefinedType) {
               UserDefinedType var17 = (UserDefinedType)dataType;
               SQLUserDefinedType annotation = (SQLUserDefinedType)var17.userClass().getAnnotation(SQLUserDefinedType.class);
               Class udtClass = annotation != null ? annotation.udt() : (Class)UDTRegistration$.MODULE$.getUDTFor(var17.userClass().getName()).getOrElse(() -> {
                  throw ExecutionErrors$.MODULE$.userDefinedTypeNotAnnotatedAndRegisteredError(var17);
               });
               return new AgnosticEncoders.UDTEncoder(var17, udtClass);
            }

            if (dataType instanceof ArrayType) {
               ArrayType var20 = (ArrayType)dataType;
               DataType elementType = var20.elementType();
               boolean containsNull = var20.containsNull();
               return new AgnosticEncoders.IterableEncoder(.MODULE$.classTag(scala.reflect.ClassTag..MODULE$.apply(ArraySeq.class)), this.encoderForDataType(elementType, lenient), containsNull, true);
            }

            if (dataType instanceof MapType) {
               MapType var23 = (MapType)dataType;
               DataType keyType = var23.keyType();
               DataType valueType = var23.valueType();
               boolean valueContainsNull = var23.valueContainsNull();
               return new AgnosticEncoders.MapEncoder(.MODULE$.classTag(scala.reflect.ClassTag..MODULE$.apply(Map.class)), this.encoderForDataType(keyType, lenient), this.encoderForDataType(valueType, lenient), valueContainsNull);
            }

            if (dataType instanceof StructType) {
               StructType var27 = (StructType)dataType;
               StructField[] fields = var27.fields();
               return new AgnosticEncoders.RowEncoder(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(fields), (field) -> new AgnosticEncoders.EncoderField(field.name(), MODULE$.encoderForDataType(field.dataType(), lenient), field.nullable(), field.metadata(), AgnosticEncoders.EncoderField$.MODULE$.apply$default$5(), AgnosticEncoders.EncoderField$.MODULE$.apply$default$6()), scala.reflect.ClassTag..MODULE$.apply(AgnosticEncoders.EncoderField.class))).toImmutableArraySeq());
            }

            throw new AnalysisException("UNSUPPORTED_DATA_TYPE_FOR_ENCODER", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("dataType"), this.toSQLType((AbstractDataType)dataType))}))));
         }

         PythonUserDefinedType var16 = (PythonUserDefinedType)dataType;
         DataType var10000 = var16.sqlType();
         lenient = lenient;
         dataType = var10000;
      }
   }

   private RowEncoder$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
