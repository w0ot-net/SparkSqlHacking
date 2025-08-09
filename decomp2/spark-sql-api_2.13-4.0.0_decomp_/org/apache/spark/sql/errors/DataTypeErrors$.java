package org.apache.spark.sql.errors;

import org.apache.spark.QueryContext;
import org.apache.spark.SparkArithmeticException;
import org.apache.spark.SparkException;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.SparkNumberFormatException;
import org.apache.spark.SparkRuntimeException;
import org.apache.spark.SparkUnsupportedOperationException;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Decimal;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;

public final class DataTypeErrors$ implements DataTypeErrorsBase {
   public static final DataTypeErrors$ MODULE$ = new DataTypeErrors$();

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

   public SparkUnsupportedOperationException unsupportedOperationExceptionError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_2225");
   }

   public SparkArithmeticException decimalPrecisionExceedsMaxPrecisionError(final int precision, final int maxPrecision) {
      return new SparkArithmeticException("DECIMAL_PRECISION_EXCEEDS_MAX_PRECISION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("precision"), Integer.toString(precision)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("maxPrecision"), Integer.toString(maxPrecision))}))), (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "");
   }

   public SparkException unsupportedRoundingMode(final Enumeration.Value roundMode) {
      return org.apache.spark.SparkException..MODULE$.internalError("Not supported rounding mode: " + roundMode.toString() + ".");
   }

   public SparkArithmeticException outOfDecimalTypeRangeError(final UTF8String str) {
      return new SparkArithmeticException("NUMERIC_OUT_OF_SUPPORTED_RANGE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("value"), str.toString())}))), (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "");
   }

   public SparkRuntimeException unsupportedJavaTypeError(final Class clazz) {
      return new SparkRuntimeException("_LEGACY_ERROR_TEMP_2121", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("clazz"), clazz.toString())}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   public SparkUnsupportedOperationException nullLiteralsCannotBeCastedError(final String name) {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_2226", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), name)}))));
   }

   public Throwable notUserDefinedTypeError(final String name, final String userClass) {
      return new SparkException("_LEGACY_ERROR_TEMP_2227", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), name), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("userClass"), userClass)}))), (Throwable)null);
   }

   public Throwable cannotLoadUserDefinedTypeError(final String name, final String userClass) {
      return new SparkException("_LEGACY_ERROR_TEMP_2228", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), name), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("userClass"), userClass)}))), (Throwable)null);
   }

   public SparkRuntimeException unsupportedArrayTypeError(final Class clazz) {
      return new SparkRuntimeException("_LEGACY_ERROR_TEMP_2120", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("clazz"), clazz.toString())}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   public Throwable schemaFailToParseError(final String schema, final Throwable e) {
      return new AnalysisException("INVALID_SCHEMA.PARSE_ERROR", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("inputSchema"), QuotingUtils$.MODULE$.toSQLSchema(schema)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("reason"), e.getMessage())}))), new Some(e));
   }

   public Throwable invalidDayTimeIntervalType(final String startFieldName, final String endFieldName) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1224", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("startFieldName"), startFieldName), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("endFieldName"), endFieldName)}))));
   }

   public Throwable invalidDayTimeField(final byte field, final Seq supportedIds) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1223", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("field"), Byte.toString(field)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("supportedIds"), supportedIds.mkString(", "))}))));
   }

   public Throwable invalidYearMonthField(final byte field, final Seq supportedIds) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1225", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("field"), Byte.toString(field)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("supportedIds"), supportedIds.mkString(", "))}))));
   }

   public Throwable decimalCannotGreaterThanPrecisionError(final int scale, final int precision) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1228", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("scale"), Integer.toString(scale)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("precision"), Integer.toString(precision))}))));
   }

   public Throwable negativeScaleNotAllowedError(final int scale) {
      String sqlConf = QuotingUtils$.MODULE$.toSQLConf("spark.sql.legacy.allowNegativeScaleOfDecimal");
      return new AnalysisException("NEGATIVE_SCALE_DISALLOWED", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("scale"), this.toSQLValue(scale)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("sqlConf"), sqlConf)}))));
   }

   public Throwable attributeNameSyntaxError(final String name) {
      return new AnalysisException("INVALID_ATTRIBUTE_NAME_SYNTAX", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), name)}))));
   }

   public Throwable cannotMergeIncompatibleDataTypesError(final DataType left, final DataType right) {
      return new SparkException("CANNOT_MERGE_INCOMPATIBLE_DATA_TYPE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("left"), this.toSQLType((AbstractDataType)left)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("right"), this.toSQLType((AbstractDataType)right))}))), (Throwable)null);
   }

   public Throwable cannotMergeDecimalTypesWithIncompatibleScaleError(final int leftScale, final int rightScale) {
      return new SparkException("_LEGACY_ERROR_TEMP_2124", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("leftScale"), Integer.toString(leftScale)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("rightScale"), Integer.toString(rightScale))}))), (Throwable)null);
   }

   public Throwable dataTypeUnsupportedError(final String dataType, final String failure) {
      return new SparkIllegalArgumentException("UNSUPPORTED_DATATYPE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("typeName"), dataType + failure)}))));
   }

   public Throwable invalidFieldName(final Seq fieldName, final Seq path, final Origin context) {
      return new AnalysisException("INVALID_FIELD_NAME", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("fieldName"), this.toSQLId(fieldName)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("path"), this.toSQLId(path))}))), context);
   }

   public ArithmeticException unscaledValueTooLargeForPrecisionError(final Decimal value, final int decimalPrecision, final int decimalScale, final QueryContext context) {
      return this.numericValueOutOfRange(value, decimalPrecision, decimalScale, context);
   }

   public QueryContext unscaledValueTooLargeForPrecisionError$default$4() {
      return null;
   }

   public ArithmeticException cannotChangeDecimalPrecisionError(final Decimal value, final int decimalPrecision, final int decimalScale, final QueryContext context) {
      return this.numericValueOutOfRange(value, decimalPrecision, decimalScale, context);
   }

   public QueryContext cannotChangeDecimalPrecisionError$default$4() {
      return null;
   }

   private ArithmeticException numericValueOutOfRange(final Decimal value, final int decimalPrecision, final int decimalScale, final QueryContext context) {
      return new SparkArithmeticException("NUMERIC_VALUE_OUT_OF_RANGE.WITH_SUGGESTION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("value"), value.toPlainString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("precision"), Integer.toString(decimalPrecision)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("scale"), Integer.toString(decimalScale)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("config"), this.toSQLConf("spark.sql.ansi.enabled"))}))), this.getQueryContext(context), this.getSummary(context));
   }

   public SparkNumberFormatException invalidInputInCastToNumberError(final DataType to, final UTF8String s, final QueryContext context) {
      String var10000 = s.toString().replace("\\", "\\\\");
      String convertedValueStr = "'" + var10000.replace("'", "\\'") + "'";
      return new SparkNumberFormatException("CAST_INVALID_INPUT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("expression"), convertedValueStr), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("sourceType"), this.toSQLType((AbstractDataType)StringType$.MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("targetType"), this.toSQLType((AbstractDataType)to)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("ansiConfig"), this.toSQLConf("spark.sql.ansi.enabled"))}))), this.getQueryContext(context), this.getSummary(context));
   }

   public Throwable ambiguousColumnOrFieldError(final Seq name, final int numMatches, final Origin context) {
      return new AnalysisException("AMBIGUOUS_COLUMN_OR_FIELD", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), this.toSQLId(name)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("n"), Integer.toString(numMatches))}))), context);
   }

   public ArithmeticException castingCauseOverflowError(final String t, final DataType from, final DataType to) {
      return new SparkArithmeticException("CAST_OVERFLOW", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("value"), t), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("sourceType"), this.toSQLType((AbstractDataType)from)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("targetType"), this.toSQLType((AbstractDataType)to)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("ansiConfig"), this.toSQLConf("spark.sql.ansi.enabled"))}))), (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "");
   }

   public SparkRuntimeException failedParsingStructTypeError(final String raw) {
      return new SparkRuntimeException("FAILED_PARSE_STRUCT_TYPE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("raw"), "'" + raw + "'")}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   public SparkUnsupportedOperationException fieldIndexOnRowWithoutSchemaError(final String fieldName) {
      return new SparkUnsupportedOperationException("UNSUPPORTED_CALL.FIELD_INDEX", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("methodName"), "fieldIndex"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("className"), "Row"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("fieldName"), this.toSQLId(fieldName))}))));
   }

   public Throwable valueIsNullError(final int index) {
      return new SparkRuntimeException("ROW_VALUE_IS_NULL", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("index"), Integer.toString(index))}))), (Throwable)null, org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   public Throwable charOrVarcharTypeAsStringUnsupportedError() {
      return new AnalysisException("UNSUPPORTED_CHAR_OR_VARCHAR_AS_STRING", .MODULE$.Map().empty());
   }

   public Throwable userSpecifiedSchemaUnsupportedError(final String operation) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1189", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("operation"), operation)}))));
   }

   private DataTypeErrors$() {
   }
}
