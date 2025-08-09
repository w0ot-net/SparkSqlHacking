package org.apache.spark.sql.errors;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkException.;
import org.apache.spark.sql.catalyst.parser.ParseException;
import org.apache.spark.sql.catalyst.parser.SqlBaseParser;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.util.matching.Regex;

public final class QueryParsingErrors$ implements DataTypeErrorsBase {
   public static final QueryParsingErrors$ MODULE$ = new QueryParsingErrors$();

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

   public Throwable invalidInsertIntoError(final SqlBaseParser.InsertIntoContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0001", ctx);
   }

   public Throwable parserStackOverflow(final ParserRuleContext parserRuleContext) {
      throw new ParseException("FAILED_TO_PARSE_TOO_COMPLEX", parserRuleContext);
   }

   public Throwable insertOverwriteDirectoryUnsupportedError() {
      return .MODULE$.internalError("INSERT OVERWRITE DIRECTORY is not supported.");
   }

   public Throwable columnAliasInOperationNotAllowedError(final String op, final SqlBaseParser.TableAliasContext ctx) {
      return new ParseException("COLUMN_ALIASES_NOT_ALLOWED", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("op"), this.toSQLStmt(op))}))), ctx.identifierList());
   }

   public Throwable emptySourceForMergeError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0004", ctx.source);
   }

   public Throwable insertedValueNumberNotMatchFieldNumberError(final SqlBaseParser.NotMatchedClauseContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0006", ctx.notMatchedAction());
   }

   public Throwable mergeStatementWithoutWhenClauseError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return new ParseException("MERGE_WITHOUT_WHEN", ctx);
   }

   public Throwable nonLastMatchedClauseOmitConditionError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return new ParseException("NON_LAST_MATCHED_CLAUSE_OMIT_CONDITION", ctx);
   }

   public Throwable nonLastNotMatchedClauseOmitConditionError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return new ParseException("NON_LAST_NOT_MATCHED_BY_TARGET_CLAUSE_OMIT_CONDITION", ctx);
   }

   public Throwable nonLastNotMatchedBySourceClauseOmitConditionError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return new ParseException("NON_LAST_NOT_MATCHED_BY_SOURCE_CLAUSE_OMIT_CONDITION", ctx);
   }

   public Throwable emptyPartitionKeyError(final String key, final SqlBaseParser.PartitionSpecContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.EMPTY_PARTITION_VALUE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("partKey"), this.toSQLId(key))}))), ctx);
   }

   public Throwable clausesWithPipeOperatorsUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx, final String clauses) {
      return new ParseException("UNSUPPORTED_FEATURE.CLAUSE_WITH_PIPE_OPERATORS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("clauses"), clauses)}))), ctx);
   }

   public Throwable multipleQueryResultClausesWithPipeOperatorsUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx, final String clause1, final String clause2) {
      return new ParseException("MULTIPLE_QUERY_RESULT_CLAUSES_WITH_PIPE_OPERATORS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("clause1"), clause1), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("clause2"), clause2)}))), ctx);
   }

   public Throwable combinationQueryResultClausesUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.COMBINATION_QUERY_RESULT_CLAUSES", ctx);
   }

   public Throwable pipeOperatorAggregateUnsupportedCaseError(final String caseArgument, final ParserRuleContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.PIPE_OPERATOR_AGGREGATE_UNSUPPORTED_CASE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("case"), caseArgument)}))), ctx);
   }

   public Throwable windowClauseInPipeOperatorWhereClauseNotAllowedError(final ParserRuleContext ctx) {
      return new ParseException("NOT_ALLOWED_IN_PIPE_OPERATOR_WHERE.WINDOW_CLAUSE", ctx);
   }

   public Throwable distributeByUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0012", ctx);
   }

   public Throwable transformNotSupportQuantifierError(final ParserRuleContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.TRANSFORM_DISTINCT_ALL", scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public Throwable transformWithSerdeUnsupportedError(final ParserRuleContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.TRANSFORM_NON_HIVE", scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public Throwable unpivotWithPivotInFromClauseNotAllowedError(final ParserRuleContext ctx) {
      return new ParseException("NOT_ALLOWED_IN_FROM.UNPIVOT_WITH_PIVOT", ctx);
   }

   public Throwable lateralWithPivotInFromClauseNotAllowedError(final ParserRuleContext ctx) {
      return new ParseException("NOT_ALLOWED_IN_FROM.LATERAL_WITH_PIVOT", ctx);
   }

   public Throwable lateralWithUnpivotInFromClauseNotAllowedError(final ParserRuleContext ctx) {
      return new ParseException("NOT_ALLOWED_IN_FROM.LATERAL_WITH_UNPIVOT", ctx);
   }

   public Throwable lateralJoinWithUsingJoinUnsupportedError(final ParserRuleContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.LATERAL_JOIN_USING", scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public Throwable unsupportedLateralJoinTypeError(final ParserRuleContext ctx, final String joinType) {
      return new ParseException("INVALID_LATERAL_JOIN_TYPE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("joinType"), this.toSQLStmt(joinType))}))), ctx);
   }

   public Throwable invalidLateralJoinRelationError(final SqlBaseParser.RelationPrimaryContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.LATERAL_WITHOUT_SUBQUERY_OR_TABLE_VALUED_FUNC", ctx);
   }

   public Throwable repetitiveWindowDefinitionError(final String name, final SqlBaseParser.WindowClauseContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.REPETITIVE_WINDOW_DEFINITION", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("windowName"), this.toSQLId(name))}))), ctx);
   }

   public Throwable invalidWindowReferenceError(final String name, final SqlBaseParser.WindowClauseContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.INVALID_WINDOW_REFERENCE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("windowName"), this.toSQLId(name))}))), ctx);
   }

   public Throwable cannotResolveWindowReferenceError(final String name, final SqlBaseParser.WindowClauseContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.UNRESOLVED_WINDOW_REFERENCE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("windowName"), this.toSQLId(name))}))), ctx);
   }

   public Throwable incompatibleJoinTypesError(final String joinType1, final String joinType2, final ParserRuleContext ctx) {
      return new ParseException("INCOMPATIBLE_JOIN_TYPES", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("joinType1"), joinType1.toUpperCase(Locale.ROOT)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("joinType2"), joinType2.toUpperCase(Locale.ROOT))}))), ctx);
   }

   public Throwable emptyInputForTableSampleError(final ParserRuleContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0014", ctx);
   }

   public Throwable tableSampleByBytesUnsupportedError(final String msg, final SqlBaseParser.SampleMethodContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0015", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("msg"), msg)}))), ctx);
   }

   public Throwable invalidByteLengthLiteralError(final String bytesStr, final SqlBaseParser.SampleByBytesContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0016", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("bytesStr"), bytesStr)}))), ctx);
   }

   public Throwable invalidEscapeStringError(final String invalidEscape, final SqlBaseParser.PredicateContext ctx) {
      return new ParseException("INVALID_ESC", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("invalidEscape"), this.toSQLValue(invalidEscape))}))), ctx);
   }

   public Throwable trimOptionUnsupportedError(final int trimOption, final SqlBaseParser.TrimContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0018", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("trimOption"), Integer.toString(trimOption))}))), ctx);
   }

   public Throwable functionNameUnsupportedError(final String functionName, final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.UNSUPPORTED_FUNC_NAME", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("funcName"), this.toSQLId(functionName))}))), ctx);
   }

   public Throwable cannotParseValueTypeError(final String valueType, final String value, final SqlBaseParser.TypeConstructorContext ctx) {
      return new ParseException("INVALID_TYPED_LITERAL", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("valueType"), this.toSQLType(valueType)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), this.toSQLValue(value))}))), ctx);
   }

   public Throwable literalValueTypeUnsupportedError(final String unsupportedType, final Seq supportedTypes, final SqlBaseParser.TypeConstructorContext ctx) {
      return new ParseException("UNSUPPORTED_TYPED_LITERAL", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("unsupportedType"), this.toSQLType(unsupportedType)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("supportedTypes"), ((IterableOnceOps)supportedTypes.map((text) -> MODULE$.toSQLType(text))).mkString(", "))}))), ctx);
   }

   public Throwable invalidNumericLiteralRangeError(final String rawStrippedQualifier, final BigDecimal minValue, final BigDecimal maxValue, final String typeName, final SqlBaseParser.NumberContext ctx) {
      return new ParseException("INVALID_NUMERIC_LITERAL_RANGE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rawStrippedQualifier"), rawStrippedQualifier), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("minValue"), minValue.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("maxValue"), maxValue.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("typeName"), typeName)}))), ctx);
   }

   public Throwable moreThanOneFromToUnitInIntervalLiteralError(final ParserRuleContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0024", ctx);
   }

   public Throwable invalidIntervalFormError(final String value, final SqlBaseParser.MultiUnitsIntervalContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0026", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), value)}))), ctx);
   }

   public Throwable invalidFromToUnitValueError(final SqlBaseParser.IntervalValueContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0027", ctx);
   }

   public Throwable fromToIntervalUnsupportedError(final String from, final String to, final ParserRuleContext ctx) {
      String intervalInput = ctx.getText();
      Regex pattern = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("'([^']*)'"));
      Option var8 = pattern.findFirstMatchIn(intervalInput);
      String var10000;
      if (var8 instanceof Some var9) {
         Regex.Match m = (Regex.Match)var9.value();
         var10000 = m.group(1);
      } else {
         if (!scala.None..MODULE$.equals(var8)) {
            throw new MatchError(var8);
         }

         var10000 = "";
      }

      String input = var10000;
      return new ParseException("INVALID_INTERVAL_FORMAT.UNSUPPORTED_FROM_TO_EXPRESSION", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("input"), input), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("from"), from), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("to"), to)}))), ctx);
   }

   public Throwable mixedIntervalUnitsError(final String literal, final ParserRuleContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0029", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("literal"), literal)}))), ctx);
   }

   public Throwable dataTypeUnsupportedError(final String dataType, final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      return new ParseException("UNSUPPORTED_DATATYPE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("typeName"), this.toSQLType(dataType))}))), ctx);
   }

   public Throwable charTypeMissingLengthError(final String dataType, final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      return new ParseException("DATATYPE_MISSING_SIZE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), this.toSQLType(dataType))}))), ctx);
   }

   public Throwable nestedTypeMissingElementTypeError(final String dataType, final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      String var4 = dataType.toUpperCase(Locale.ROOT);
      switch (var4 == null ? 0 : var4.hashCode()) {
         case -1838645291:
            if ("STRUCT".equals(var4)) {
               return new ParseException("INCOMPLETE_TYPE_DEFINITION.STRUCT", scala.Predef..MODULE$.Map().empty(), ctx);
            }
            break;
         case 76092:
            if ("MAP".equals(var4)) {
               return new ParseException("INCOMPLETE_TYPE_DEFINITION.MAP", scala.Predef..MODULE$.Map().empty(), ctx);
            }
            break;
         case 62552633:
            if ("ARRAY".equals(var4)) {
               return new ParseException("INCOMPLETE_TYPE_DEFINITION.ARRAY", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("elementType"), "<INT>")}))), ctx);
            }
      }

      throw new MatchError(var4);
   }

   public Throwable partitionTransformNotExpectedError(final String name, final String expr, final SqlBaseParser.ApplyTransformContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.INVALID_COLUMN_REFERENCE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("transform"), this.toSQLId(name)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("expr"), expr)}))), ctx);
   }

   public Throwable wrongNumberArgumentsForTransformError(final String name, final int actualNum, final SqlBaseParser.ApplyTransformContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.TRANSFORM_WRONG_NUM_ARGS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("transform"), this.toSQLId(name)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("expectedNum"), "1"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("actualNum"), Integer.toString(actualNum))}))), ctx);
   }

   public Throwable invalidBucketsNumberError(final String describe, final SqlBaseParser.ApplyTransformContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0031", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("describe"), describe)}))), ctx);
   }

   public Throwable cannotCleanReservedNamespacePropertyError(final String property, final ParserRuleContext ctx, final String msg) {
      return new ParseException("UNSUPPORTED_FEATURE.SET_NAMESPACE_PROPERTY", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("property"), property), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("msg"), msg)}))), ctx);
   }

   public Throwable propertiesAndDbPropertiesBothSpecifiedError(final SqlBaseParser.CreateNamespaceContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.SET_PROPERTIES_AND_DBPROPERTIES", scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public Throwable cannotCleanReservedTablePropertyError(final String property, final ParserRuleContext ctx, final String msg) {
      return new ParseException("UNSUPPORTED_FEATURE.SET_TABLE_PROPERTY", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("property"), property), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("msg"), msg)}))), ctx);
   }

   public Throwable duplicatedTablePathsFoundError(final String pathOne, final String pathTwo, final ParserRuleContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0032", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pathOne"), pathOne), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pathTwo"), pathTwo)}))), ctx);
   }

   public Throwable storedAsAndStoredByBothSpecifiedError(final SqlBaseParser.CreateFileFormatContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0033", ctx);
   }

   public Throwable operationInHiveStyleCommandUnsupportedError(final String operation, final String command, final SqlBaseParser.StatementContext ctx, final Option msgOpt) {
      return new ParseException("_LEGACY_ERROR_TEMP_0034", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("operation"), operation), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("command"), command), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("msg"), msgOpt.map((m) -> ", " + m).getOrElse(() -> ""))}))), ctx);
   }

   public Option operationInHiveStyleCommandUnsupportedError$default$4() {
      return scala.None..MODULE$;
   }

   public Throwable operationNotAllowedError(final String message, final ParserRuleContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0035", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("message"), message)}))), ctx);
   }

   public Throwable invalidStatementError(final String operation, final ParserRuleContext ctx) {
      return new ParseException("INVALID_STATEMENT_OR_CLAUSE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("operation"), this.toSQLStmt(operation))}))), ctx);
   }

   public Throwable descColumnForPartitionUnsupportedError(final SqlBaseParser.DescribeRelationContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.DESC_TABLE_COLUMN_PARTITION", scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public Throwable computeStatisticsNotExpectedError(final SqlBaseParser.IdentifierContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.ANALYZE_TABLE_UNEXPECTED_NOSCAN", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("ctx"), this.toSQLStmt(ctx.getText()))}))), ctx);
   }

   public Throwable addCatalogInCacheTableAsSelectNotAllowedError(final String quoted, final SqlBaseParser.CacheTableContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0037", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("quoted"), quoted)}))), ctx);
   }

   public Throwable showFunctionsUnsupportedError(final String identifier, final SqlBaseParser.IdentifierContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.SHOW_FUNCTIONS_INVALID_SCOPE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("scope"), this.toSQLId(identifier))}))), ctx);
   }

   public Throwable showFunctionsInvalidPatternError(final String pattern, final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.SHOW_FUNCTIONS_INVALID_PATTERN", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pattern"), this.toSQLId(pattern))}))), ctx);
   }

   public Throwable duplicateCteDefinitionNamesError(final String duplicateNames, final SqlBaseParser.CtesContext ctx) {
      return new ParseException("DUPLICATED_CTE_NAMES", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("duplicateNames"), duplicateNames)}))), ctx);
   }

   public Throwable sqlStatementUnsupportedError(final String sqlText, final Origin position) {
      return new ParseException(scala.Option..MODULE$.apply(sqlText), position, position, "INVALID_SQL_SYNTAX.UNSUPPORTED_SQL_STATEMENT", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sqlText"), sqlText)}))));
   }

   public Throwable invalidIdentifierError(final String ident, final ParserRuleContext ctx) {
      return new ParseException("INVALID_IDENTIFIER", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("ident"), ident)}))), ctx);
   }

   public Throwable duplicateClausesError(final String clauseName, final ParserRuleContext ctx) {
      return new ParseException("DUPLICATE_CLAUSES", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("clauseName"), clauseName)}))), ctx);
   }

   public Throwable duplicateKeysError(final String key, final ParserRuleContext ctx) {
      return new ParseException("DUPLICATE_KEY", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("keyColumn"), this.toSQLId(key))}))), ctx);
   }

   public Throwable unexpectedFormatForSetConfigurationError(final ParserRuleContext ctx) {
      return new ParseException("INVALID_SET_SYNTAX", ctx);
   }

   public Throwable invalidPropertyKeyForSetQuotedConfigurationError(final String keyCandidate, final String valueStr, final ParserRuleContext ctx) {
      return new ParseException("INVALID_PROPERTY_KEY", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("key"), this.toSQLConf(keyCandidate)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), this.toSQLConf(valueStr))}))), ctx);
   }

   public Throwable invalidPropertyValueForSetQuotedConfigurationError(final String valueCandidate, final String keyStr, final ParserRuleContext ctx) {
      return new ParseException("INVALID_PROPERTY_VALUE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), this.toSQLConf(valueCandidate)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("key"), this.toSQLConf(keyStr))}))), ctx);
   }

   public Throwable unexpectedFormatForResetConfigurationError(final SqlBaseParser.ResetConfigurationContext ctx) {
      return new ParseException("INVALID_RESET_COMMAND_FORMAT", ctx);
   }

   public Throwable intervalValueOutOfRangeError(final String input, final SqlBaseParser.IntervalContext ctx) {
      return new ParseException("INVALID_INTERVAL_FORMAT.TIMEZONE_INTERVAL_OUT_OF_RANGE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("input"), input)}))), ctx);
   }

   public Throwable invalidTimeZoneDisplacementValueError(final SqlBaseParser.SetTimeZoneContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0045", ctx);
   }

   public Throwable createTempTableNotSpecifyProviderError(final SqlBaseParser.CreateTableContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0046", ctx);
   }

   public Throwable rowFormatNotUsedWithStoredAsError(final SqlBaseParser.CreateTableLikeContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0047", ctx);
   }

   public Throwable useDefinedRecordReaderOrWriterClassesError(final ParserRuleContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0048", ctx);
   }

   public Throwable directoryPathAndOptionsPathBothSpecifiedError(final SqlBaseParser.InsertOverwriteDirContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0049", ctx);
   }

   public Throwable unsupportedLocalFileSchemeError(final SqlBaseParser.InsertOverwriteDirContext ctx, final String actualSchema) {
      return new ParseException("LOCAL_MUST_WITH_SCHEMA_FILE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("actualSchema"), actualSchema)}))), ctx);
   }

   public Throwable invalidGroupingSetError(final String element, final SqlBaseParser.GroupingAnalyticsContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0051", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("element"), element)}))), ctx);
   }

   public Throwable identityColumnUnsupportedDataType(final SqlBaseParser.IdentityColumnContext ctx, final String dataType) {
      return new ParseException("IDENTITY_COLUMNS_UNSUPPORTED_DATA_TYPE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("dataType"), dataType)}))), ctx);
   }

   public Throwable identityColumnIllegalStep(final SqlBaseParser.IdentityColSpecContext ctx) {
      return new ParseException("IDENTITY_COLUMNS_ILLEGAL_STEP", scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public Throwable identityColumnDuplicatedSequenceGeneratorOption(final SqlBaseParser.IdentityColSpecContext ctx, final String sequenceGeneratorOption) {
      return new ParseException("IDENTITY_COLUMNS_DUPLICATED_SEQUENCE_GENERATOR_OPTION", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sequenceGeneratorOption"), sequenceGeneratorOption)}))), ctx);
   }

   public Throwable createViewWithBothIfNotExistsAndReplaceError(final SqlBaseParser.CreateViewContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0052", ctx);
   }

   public Throwable temporaryViewWithSchemaBindingMode(final SqlBaseParser.StatementContext ctx) {
      return new ParseException("UNSUPPORTED_FEATURE.TEMPORARY_VIEW_WITH_SCHEMA_BINDING_MODE", scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public Throwable parameterMarkerNotAllowed(final String statement, final Origin origin) {
      return new ParseException(origin.sqlText(), origin, origin, "UNSUPPORTED_FEATURE.PARAMETER_MARKER_IN_UNEXPECTED_STATEMENT", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("statement"), statement)}))));
   }

   public Throwable defineTempViewWithIfNotExistsError(final SqlBaseParser.CreateViewContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0053", ctx);
   }

   public Throwable notAllowedToAddDBPrefixForTempViewError(final Seq nameParts, final SqlBaseParser.CreateViewContext ctx) {
      return new ParseException("TEMP_VIEW_NAME_TOO_MANY_NAME_PARTS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("actualName"), this.toSQLId(nameParts))}))), ctx);
   }

   public Throwable createFuncWithBothIfNotExistsAndReplaceError(final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.CREATE_ROUTINE_WITH_IF_NOT_EXISTS_AND_REPLACE", ctx);
   }

   public Throwable createFuncWithGeneratedColumnsError(final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.CREATE_FUNC_WITH_GENERATED_COLUMNS_AS_PARAMETERS", ctx);
   }

   public Throwable createFuncWithConstraintError(final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.CREATE_FUNC_WITH_COLUMN_CONSTRAINTS", ctx);
   }

   public Throwable defineTempFuncWithIfNotExistsError(final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.CREATE_TEMP_FUNC_WITH_IF_NOT_EXISTS", ctx);
   }

   public Throwable unsupportedFunctionNameError(final Seq funcName, final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.MULTI_PART_NAME", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("statement"), this.toSQLStmt("CREATE TEMPORARY FUNCTION")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), this.toSQLId(funcName))}))), ctx);
   }

   public Throwable specifyingDBInCreateTempFuncError(final String databaseName, final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.CREATE_TEMP_FUNC_WITH_DATABASE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("database"), this.toSQLId(databaseName))}))), ctx);
   }

   public Throwable invalidTableValuedFunctionNameError(final Seq name, final SqlBaseParser.TableValuedFunctionContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.INVALID_TABLE_VALUED_FUNC_NAME", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("funcName"), this.toSQLId(name))}))), ctx);
   }

   public Throwable unclosedBracketedCommentError(final String command, final Origin start, final Origin stop) {
      return new ParseException(new Some(command), start, stop, "UNCLOSED_BRACKETED_COMMENT", scala.Predef..MODULE$.Map().empty());
   }

   public Throwable invalidTimeTravelSpec(final String reason, final ParserRuleContext ctx) {
      return new ParseException("_LEGACY_ERROR_TEMP_0056", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("reason"), reason)}))), ctx);
   }

   public Throwable invalidNameForDropTempFunc(final Seq name, final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.MULTI_PART_NAME", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("statement"), this.toSQLStmt("DROP TEMPORARY FUNCTION")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), this.toSQLId(name))}))), ctx);
   }

   public Throwable invalidNameForSetCatalog(final Seq name, final ParserRuleContext ctx) {
      return new ParseException("INVALID_SQL_SYNTAX.MULTI_PART_NAME", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("statement"), this.toSQLStmt("SET CATALOG")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), this.toSQLId(name))}))), ctx);
   }

   public Throwable defaultColumnNotImplementedYetError(final ParserRuleContext ctx) {
      return new ParseException("UNSUPPORTED_DEFAULT_VALUE.WITHOUT_SUGGESTION", ctx);
   }

   public Throwable defaultColumnNotEnabledError(final ParserRuleContext ctx) {
      return new ParseException("UNSUPPORTED_DEFAULT_VALUE.WITH_SUGGESTION", ctx);
   }

   public Throwable defaultColumnReferencesNotAllowedInPartitionSpec(final ParserRuleContext ctx) {
      return new ParseException("REF_DEFAULT_VALUE_IS_NOT_ALLOWED_IN_PARTITION", ctx);
   }

   public Throwable duplicateArgumentNamesError(final Seq arguments, final ParserRuleContext ctx) {
      return new ParseException("EXEC_IMMEDIATE_DUPLICATE_ARGUMENT_ALIASES", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("aliases"), ((IterableOnceOps)arguments.map((parts) -> MODULE$.toSQLId(parts))).mkString(", "))}))), ctx);
   }

   public Throwable duplicateTableColumnDescriptor(final ParserRuleContext ctx, final String columnName, final String optionName, final boolean isCreate, final String alterType) {
      String errorClass = isCreate ? "CREATE_TABLE_COLUMN_DESCRIPTOR_DUPLICATE" : "ALTER_TABLE_COLUMN_DESCRIPTOR_DUPLICATE";
      Map alterTypeMap = isCreate ? scala.Predef..MODULE$.Map().empty() : (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), alterType)})));
      return new ParseException(errorClass, (Map)alterTypeMap.$plus$plus((IterableOnce)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("columnName"), columnName), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("optionName"), optionName)})))), ctx);
   }

   public boolean duplicateTableColumnDescriptor$default$4() {
      return true;
   }

   public String duplicateTableColumnDescriptor$default$5() {
      return "ADD";
   }

   public Throwable invalidDatetimeUnitError(final ParserRuleContext ctx, final String functionName, final String invalidValue) {
      return new ParseException("INVALID_PARAMETER_VALUE.DATETIME_UNIT", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("functionName"), this.toSQLId(functionName)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("parameter"), this.toSQLId("unit")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("invalidValue"), invalidValue)}))), ctx);
   }

   public Throwable invalidTableFunctionIdentifierArgumentMissingParentheses(final ParserRuleContext ctx, final String argumentName) {
      return new ParseException("INVALID_SQL_SYNTAX.INVALID_TABLE_FUNCTION_IDENTIFIER_ARGUMENT_MISSING_PARENTHESES", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("argumentName"), this.toSQLId(argumentName))}))), ctx);
   }

   public Throwable clusterByWithPartitionedBy(final ParserRuleContext ctx) {
      return new ParseException("SPECIFY_CLUSTER_BY_WITH_PARTITIONED_BY_IS_NOT_ALLOWED", ctx);
   }

   public Throwable clusterByWithBucketing(final ParserRuleContext ctx) {
      return new ParseException("SPECIFY_CLUSTER_BY_WITH_BUCKETING_IS_NOT_ALLOWED", ctx);
   }

   private QueryParsingErrors$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
