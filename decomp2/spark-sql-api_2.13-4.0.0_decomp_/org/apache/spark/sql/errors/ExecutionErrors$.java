package org.apache.spark.sql.errors;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkDateTimeException;
import org.apache.spark.SparkRuntimeException;
import org.apache.spark.SparkUnsupportedOperationException;
import org.apache.spark.SparkUpgradeException;
import org.apache.spark.sql.catalyst.WalkedTypePath;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.collection.immutable.Seq;

public final class ExecutionErrors$ implements ExecutionErrors {
   public static final ExecutionErrors$ MODULE$ = new ExecutionErrors$();

   static {
      DataTypeErrorsBase.$init$(MODULE$);
      ExecutionErrors.$init$(MODULE$);
   }

   public SparkDateTimeException fieldDiffersFromDerivedLocalDateError(final ChronoField field, final int actual, final int expected, final LocalDate candidate) {
      return ExecutionErrors.fieldDiffersFromDerivedLocalDateError$(this, field, actual, expected, candidate);
   }

   public SparkUpgradeException failToParseDateTimeInNewParserError(final String s, final Throwable e) {
      return ExecutionErrors.failToParseDateTimeInNewParserError$(this, s, e);
   }

   public SparkRuntimeException stateStoreHandleNotInitialized() {
      return ExecutionErrors.stateStoreHandleNotInitialized$(this);
   }

   public SparkUpgradeException failToRecognizePatternAfterUpgradeError(final String pattern, final Throwable e) {
      return ExecutionErrors.failToRecognizePatternAfterUpgradeError$(this, pattern, e);
   }

   public SparkRuntimeException failToRecognizePatternError(final String pattern, final Throwable e) {
      return ExecutionErrors.failToRecognizePatternError$(this, pattern, e);
   }

   public SparkRuntimeException unreachableError(final String err) {
      return ExecutionErrors.unreachableError$(this, err);
   }

   public String unreachableError$default$1() {
      return ExecutionErrors.unreachableError$default$1$(this);
   }

   public SparkDateTimeException invalidInputInCastToDatetimeError(final UTF8String value, final DataType to, final QueryContext context) {
      return ExecutionErrors.invalidInputInCastToDatetimeError$(this, value, to, context);
   }

   public SparkDateTimeException invalidInputInCastToDatetimeError(final double value, final DataType to, final QueryContext context) {
      return ExecutionErrors.invalidInputInCastToDatetimeError$(this, value, to, context);
   }

   public SparkDateTimeException invalidInputInCastToDatetimeErrorInternal(final String sqlValue, final DataType from, final DataType to, final QueryContext context) {
      return ExecutionErrors.invalidInputInCastToDatetimeErrorInternal$(this, sqlValue, from, to, context);
   }

   public ArithmeticException arithmeticOverflowError(final String message, final String suggestedFunc, final QueryContext context) {
      return ExecutionErrors.arithmeticOverflowError$(this, message, suggestedFunc, context);
   }

   public String arithmeticOverflowError$default$2() {
      return ExecutionErrors.arithmeticOverflowError$default$2$(this);
   }

   public QueryContext arithmeticOverflowError$default$3() {
      return ExecutionErrors.arithmeticOverflowError$default$3$(this);
   }

   public Throwable cannotParseStringAsDataTypeError(final String pattern, final String value, final DataType dataType) {
      return ExecutionErrors.cannotParseStringAsDataTypeError$(this, pattern, value, dataType);
   }

   public SparkUnsupportedOperationException unsupportedArrowTypeError(final ArrowType typeName) {
      return ExecutionErrors.unsupportedArrowTypeError$(this, typeName);
   }

   public SparkUnsupportedOperationException duplicatedFieldNameInArrowStructError(final Seq fieldNames) {
      return ExecutionErrors.duplicatedFieldNameInArrowStructError$(this, fieldNames);
   }

   public SparkUnsupportedOperationException unsupportedDataTypeError(final DataType typeName) {
      return ExecutionErrors.unsupportedDataTypeError$(this, typeName);
   }

   public Throwable userDefinedTypeNotAnnotatedAndRegisteredError(final UserDefinedType udt) {
      return ExecutionErrors.userDefinedTypeNotAnnotatedAndRegisteredError$(this, udt);
   }

   public SparkUnsupportedOperationException cannotFindEncoderForTypeError(final String typeName) {
      return ExecutionErrors.cannotFindEncoderForTypeError$(this, typeName);
   }

   public SparkUnsupportedOperationException cannotHaveCircularReferencesInBeanClassError(final Class clazz) {
      return ExecutionErrors.cannotHaveCircularReferencesInBeanClassError$(this, clazz);
   }

   public SparkUnsupportedOperationException cannotFindConstructorForTypeError(final String tpe) {
      return ExecutionErrors.cannotFindConstructorForTypeError$(this, tpe);
   }

   public SparkUnsupportedOperationException cannotHaveCircularReferencesInClassError(final String t) {
      return ExecutionErrors.cannotHaveCircularReferencesInClassError$(this, t);
   }

   public SparkUnsupportedOperationException cannotUseInvalidJavaIdentifierAsFieldNameError(final String fieldName, final WalkedTypePath walkedTypePath) {
      return ExecutionErrors.cannotUseInvalidJavaIdentifierAsFieldNameError$(this, fieldName, walkedTypePath);
   }

   public SparkRuntimeException primaryConstructorNotFoundError(final Class cls) {
      return ExecutionErrors.primaryConstructorNotFoundError$(this, cls);
   }

   public SparkRuntimeException cannotGetOuterPointerForInnerClassError(final Class innerCls) {
      return ExecutionErrors.cannotGetOuterPointerForInnerClassError$(this, innerCls);
   }

   public SparkRuntimeException cannotUseKryoSerialization() {
      return ExecutionErrors.cannotUseKryoSerialization$(this);
   }

   public SparkUnsupportedOperationException notPublicClassError(final String name) {
      return ExecutionErrors.notPublicClassError$(this, name);
   }

   public SparkUnsupportedOperationException primitiveTypesNotSupportedError() {
      return ExecutionErrors.primitiveTypesNotSupportedError$(this);
   }

   public SparkUnsupportedOperationException elementsOfTupleExceedLimitError() {
      return ExecutionErrors.elementsOfTupleExceedLimitError$(this);
   }

   public SparkUnsupportedOperationException emptyTupleNotSupportedError() {
      return ExecutionErrors.emptyTupleNotSupportedError$(this);
   }

   public Throwable invalidAgnosticEncoderError(final Object encoder) {
      return ExecutionErrors.invalidAgnosticEncoderError$(this, encoder);
   }

   public SparkDateTimeException zoneOffsetError(final String timeZone, final DateTimeException e) {
      return ExecutionErrors.zoneOffsetError$(this, timeZone, e);
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

   private ExecutionErrors$() {
   }
}
