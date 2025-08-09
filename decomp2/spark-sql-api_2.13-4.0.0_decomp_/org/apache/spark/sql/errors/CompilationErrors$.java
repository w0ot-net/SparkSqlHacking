package org.apache.spark.sql.errors;

import org.apache.spark.QueryContext;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.collection.immutable.Seq;

public final class CompilationErrors$ implements CompilationErrors {
   public static final CompilationErrors$ MODULE$ = new CompilationErrors$();

   static {
      DataTypeErrorsBase.$init$(MODULE$);
      CompilationErrors.$init$(MODULE$);
   }

   public AnalysisException ambiguousColumnOrFieldError(final Seq name, final int numMatches) {
      return CompilationErrors.ambiguousColumnOrFieldError$(this, name, numMatches);
   }

   public AnalysisException columnNotFoundError(final String colName) {
      return CompilationErrors.columnNotFoundError$(this, colName);
   }

   public AnalysisException descriptorParseError(final Throwable cause) {
      return CompilationErrors.descriptorParseError$(this, cause);
   }

   public AnalysisException describeJsonNotExtendedError(final String tableName) {
      return CompilationErrors.describeJsonNotExtendedError$(this, tableName);
   }

   public AnalysisException describeColJsonUnsupportedError() {
      return CompilationErrors.describeColJsonUnsupportedError$(this);
   }

   public AnalysisException cannotFindDescriptorFileError(final String filePath, final Throwable cause) {
      return CompilationErrors.cannotFindDescriptorFileError$(this, filePath, cause);
   }

   public Throwable usingUntypedScalaUDFError() {
      return CompilationErrors.usingUntypedScalaUDFError$(this);
   }

   public Throwable invalidBoundaryStartError(final long start) {
      return CompilationErrors.invalidBoundaryStartError$(this, start);
   }

   public Throwable invalidBoundaryEndError(final long end) {
      return CompilationErrors.invalidBoundaryEndError$(this, end);
   }

   public Throwable invalidSaveModeError(final String saveMode) {
      return CompilationErrors.invalidSaveModeError$(this, saveMode);
   }

   public Throwable sortByWithoutBucketingError() {
      return CompilationErrors.sortByWithoutBucketingError$(this);
   }

   public Throwable bucketByUnsupportedByOperationError(final String operation) {
      return CompilationErrors.bucketByUnsupportedByOperationError$(this, operation);
   }

   public Throwable bucketByAndSortByUnsupportedByOperationError(final String operation) {
      return CompilationErrors.bucketByAndSortByUnsupportedByOperationError$(this, operation);
   }

   public Throwable operationNotSupportPartitioningError(final String operation) {
      return CompilationErrors.operationNotSupportPartitioningError$(this, operation);
   }

   public Throwable operationNotSupportClusteringError(final String operation) {
      return CompilationErrors.operationNotSupportClusteringError$(this, operation);
   }

   public Throwable clusterByWithPartitionedBy() {
      return CompilationErrors.clusterByWithPartitionedBy$(this);
   }

   public Throwable clusterByWithBucketing() {
      return CompilationErrors.clusterByWithBucketing$(this);
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

   private CompilationErrors$() {
   }
}
