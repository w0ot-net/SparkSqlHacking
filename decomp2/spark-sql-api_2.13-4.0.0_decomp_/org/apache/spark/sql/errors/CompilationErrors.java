package org.apache.spark.sql.errors;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.internal.SqlApiConf$;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b\u0001\u0003\f\u0018!\u0003\r\t!G\u0011\t\u000b1\u0002A\u0011\u0001\u0018\t\u000bI\u0002A\u0011A\u001a\t\u000bM\u0003A\u0011\u0001+\t\u000b]\u0003A\u0011\u0001-\t\u000by\u0003A\u0011A0\t\u000b\t\u0004A\u0011A2\t\u000b\u0011\u0004A\u0011A3\t\u000b%\u0004A\u0011\u00016\t\u000b-\u0004A\u0011\u00017\t\u000bI\u0004A\u0011A:\t\u000bY\u0004A\u0011A<\t\u000bi\u0004A\u0011\u00016\t\u000bm\u0004A\u0011\u0001?\t\r}\u0004A\u0011AA\u0001\u0011\u001d\t)\u0001\u0001C\u0001\u0003\u000fAq!a\u0003\u0001\t\u0003\ti\u0001\u0003\u0004\u0002\u0012\u0001!\tA\u001b\u0005\u0007\u0003'\u0001A\u0011\u00016\b\u0011\u0005Uq\u0003#\u0001\u001a\u0003/1qAF\f\t\u0002e\tI\u0002C\u0004\u0002\u001eQ!\t!a\b\u0003#\r{W\u000e]5mCRLwN\\#se>\u00148O\u0003\u0002\u00193\u00051QM\u001d:peNT!AG\u000e\u0002\u0007M\fHN\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h'\r\u0001!\u0005\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%RS\"A\f\n\u0005-:\"A\u0005#bi\u0006$\u0016\u0010]3FeJ|'o\u001d\"bg\u0016\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002_A\u00111\u0005M\u0005\u0003c\u0011\u0012A!\u00168ji\u0006Y\u0012-\u001c2jOV|Wo]\"pYVlgn\u0014:GS\u0016dG-\u0012:s_J$2\u0001\u000e\u001dO!\t)d'D\u0001\u001a\u0013\t9\u0014DA\tB]\u0006d\u0017p]5t\u000bb\u001cW\r\u001d;j_:DQ!\u000f\u0002A\u0002i\nAA\\1nKB\u00191h\u0011$\u000f\u0005q\neBA\u001fA\u001b\u0005q$BA .\u0003\u0019a$o\\8u}%\tQ%\u0003\u0002CI\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u0005\r\u0019V-\u001d\u0006\u0003\u0005\u0012\u0002\"aR&\u000f\u0005!K\u0005CA\u001f%\u0013\tQE%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&%\u0011\u0015y%\u00011\u0001Q\u0003)qW/\\'bi\u000eDWm\u001d\t\u0003GEK!A\u0015\u0013\u0003\u0007%sG/A\nd_2,XN\u001c(pi\u001a{WO\u001c3FeJ|'\u000f\u0006\u00025+\")ak\u0001a\u0001\r\u000691m\u001c7OC6,\u0017\u0001\u00063fg\u000e\u0014\u0018\u000e\u001d;peB\u000b'o]3FeJ|'\u000f\u0006\u000253\")!\f\u0002a\u00017\u0006)1-Y;tKB\u00111\bX\u0005\u0003;\u0016\u0013\u0011\u0002\u00165s_^\f'\r\\3\u00029\u0011,7o\u0019:jE\u0016T5o\u001c8O_R,\u0005\u0010^3oI\u0016$WI\u001d:peR\u0011A\u0007\u0019\u0005\u0006C\u0016\u0001\rAR\u0001\ni\u0006\u0014G.\u001a(b[\u0016\fq\u0004Z3tGJL'-Z\"pY*\u001bxN\\+ogV\u0004\bo\u001c:uK\u0012,%O]8s)\u0005!\u0014!H2b]:|GOR5oI\u0012+7o\u0019:jaR|'OR5mK\u0016\u0013(o\u001c:\u0015\u0007Q2\u0007\u000eC\u0003h\u000f\u0001\u0007a)\u0001\u0005gS2,\u0007+\u0019;i\u0011\u0015Qv\u00011\u0001\\\u0003e)8/\u001b8h+:$\u0018\u0010]3e'\u000e\fG.Y+E\r\u0016\u0013(o\u001c:\u0015\u0003m\u000b\u0011$\u001b8wC2LGMQ8v]\u0012\f'/_*uCJ$XI\u001d:peR\u00111,\u001c\u0005\u0006]&\u0001\ra\\\u0001\u0006gR\f'\u000f\u001e\t\u0003GAL!!\u001d\u0013\u0003\t1{gnZ\u0001\u0018S:4\u0018\r\\5e\u0005>,h\u000eZ1ss\u0016sG-\u0012:s_J$\"a\u0017;\t\u000bUT\u0001\u0019A8\u0002\u0007\u0015tG-\u0001\u000bj]Z\fG.\u001b3TCZ,Wj\u001c3f\u000bJ\u0014xN\u001d\u000b\u00037bDQ!_\u0006A\u0002\u0019\u000b\u0001b]1wK6{G-Z\u0001\u001cg>\u0014HOQ=XSRDw.\u001e;Ck\u000e\\W\r^5oO\u0016\u0013(o\u001c:\u0002G\t,8m[3u\u0005f,fn];qa>\u0014H/\u001a3Cs>\u0003XM]1uS>tWI\u001d:peR\u00111, \u0005\u0006}6\u0001\rAR\u0001\n_B,'/\u0019;j_:\fAFY;dW\u0016$()_!oIN{'\u000f\u001e\"z+:\u001cX\u000f\u001d9peR,GMQ=Pa\u0016\u0014\u0018\r^5p]\u0016\u0013(o\u001c:\u0015\u0007m\u000b\u0019\u0001C\u0003\u007f\u001d\u0001\u0007a)\u0001\u0013pa\u0016\u0014\u0018\r^5p]:{GoU;qa>\u0014H\u000fU1si&$\u0018n\u001c8j]\u001e,%O]8s)\rY\u0016\u0011\u0002\u0005\u0006}>\u0001\rAR\u0001#_B,'/\u0019;j_:tu\u000e^*vaB|'\u000f^\"mkN$XM]5oO\u0016\u0013(o\u001c:\u0015\u0007m\u000by\u0001C\u0003\u007f!\u0001\u0007a)\u0001\u000edYV\u001cH/\u001a:Cs^KG\u000f\u001b)beRLG/[8oK\u0012\u0014\u00150\u0001\fdYV\u001cH/\u001a:Cs^KG\u000f\u001b\"vG.,G/\u001b8h\u0003E\u0019u.\u001c9jY\u0006$\u0018n\u001c8FeJ|'o\u001d\t\u0003SQ\u0019B\u0001\u0006\u0012\u0002\u001cA\u0011\u0011\u0006A\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005]\u0001"
)
public interface CompilationErrors extends DataTypeErrorsBase {
   // $FF: synthetic method
   static AnalysisException ambiguousColumnOrFieldError$(final CompilationErrors $this, final Seq name, final int numMatches) {
      return $this.ambiguousColumnOrFieldError(name, numMatches);
   }

   default AnalysisException ambiguousColumnOrFieldError(final Seq name, final int numMatches) {
      return new AnalysisException("AMBIGUOUS_COLUMN_OR_FIELD", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), this.toSQLId(name)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("n"), Integer.toString(numMatches))}))));
   }

   // $FF: synthetic method
   static AnalysisException columnNotFoundError$(final CompilationErrors $this, final String colName) {
      return $this.columnNotFoundError(colName);
   }

   default AnalysisException columnNotFoundError(final String colName) {
      return new AnalysisException("COLUMN_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("colName"), this.toSQLId(colName)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("caseSensitiveConfig"), this.toSQLConf(SqlApiConf$.MODULE$.CASE_SENSITIVE_KEY()))}))));
   }

   // $FF: synthetic method
   static AnalysisException descriptorParseError$(final CompilationErrors $this, final Throwable cause) {
      return $this.descriptorParseError(cause);
   }

   default AnalysisException descriptorParseError(final Throwable cause) {
      return new AnalysisException("CANNOT_PARSE_PROTOBUF_DESCRIPTOR", .MODULE$.Map().empty(), scala.Option..MODULE$.apply(cause));
   }

   // $FF: synthetic method
   static AnalysisException describeJsonNotExtendedError$(final CompilationErrors $this, final String tableName) {
      return $this.describeJsonNotExtendedError(tableName);
   }

   default AnalysisException describeJsonNotExtendedError(final String tableName) {
      return new AnalysisException("DESCRIBE_JSON_NOT_EXTENDED", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("tableName"), tableName)}))));
   }

   // $FF: synthetic method
   static AnalysisException describeColJsonUnsupportedError$(final CompilationErrors $this) {
      return $this.describeColJsonUnsupportedError();
   }

   default AnalysisException describeColJsonUnsupportedError() {
      return new AnalysisException("UNSUPPORTED_FEATURE.DESC_TABLE_COLUMN_JSON", .MODULE$.Map().empty());
   }

   // $FF: synthetic method
   static AnalysisException cannotFindDescriptorFileError$(final CompilationErrors $this, final String filePath, final Throwable cause) {
      return $this.cannotFindDescriptorFileError(filePath, cause);
   }

   default AnalysisException cannotFindDescriptorFileError(final String filePath, final Throwable cause) {
      return new AnalysisException("PROTOBUF_DESCRIPTOR_FILE_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("filePath"), filePath)}))), scala.Option..MODULE$.apply(cause));
   }

   // $FF: synthetic method
   static Throwable usingUntypedScalaUDFError$(final CompilationErrors $this) {
      return $this.usingUntypedScalaUDFError();
   }

   default Throwable usingUntypedScalaUDFError() {
      return new AnalysisException("UNTYPED_SCALA_UDF", .MODULE$.Map().empty());
   }

   // $FF: synthetic method
   static Throwable invalidBoundaryStartError$(final CompilationErrors $this, final long start) {
      return $this.invalidBoundaryStartError(start);
   }

   default Throwable invalidBoundaryStartError(final long start) {
      return new AnalysisException("INVALID_BOUNDARY.START", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("boundary"), this.toSQLId("start")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("invalidValue"), this.toSQLValue(start)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("longMinValue"), this.toSQLValue(Long.MIN_VALUE)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("intMinValue"), this.toSQLValue(Integer.MIN_VALUE)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("intMaxValue"), this.toSQLValue(Integer.MAX_VALUE))}))));
   }

   // $FF: synthetic method
   static Throwable invalidBoundaryEndError$(final CompilationErrors $this, final long end) {
      return $this.invalidBoundaryEndError(end);
   }

   default Throwable invalidBoundaryEndError(final long end) {
      return new AnalysisException("INVALID_BOUNDARY.END", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("boundary"), this.toSQLId("end")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("invalidValue"), this.toSQLValue(end)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("longMaxValue"), this.toSQLValue(Long.MAX_VALUE)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("intMinValue"), this.toSQLValue(Integer.MIN_VALUE)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("intMaxValue"), this.toSQLValue(Integer.MAX_VALUE))}))));
   }

   // $FF: synthetic method
   static Throwable invalidSaveModeError$(final CompilationErrors $this, final String saveMode) {
      return $this.invalidSaveModeError(saveMode);
   }

   default Throwable invalidSaveModeError(final String saveMode) {
      return new AnalysisException("INVALID_SAVE_MODE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("mode"), this.toDSOption(saveMode))}))));
   }

   // $FF: synthetic method
   static Throwable sortByWithoutBucketingError$(final CompilationErrors $this) {
      return $this.sortByWithoutBucketingError();
   }

   default Throwable sortByWithoutBucketingError() {
      return new AnalysisException("SORT_BY_WITHOUT_BUCKETING", .MODULE$.Map().empty());
   }

   // $FF: synthetic method
   static Throwable bucketByUnsupportedByOperationError$(final CompilationErrors $this, final String operation) {
      return $this.bucketByUnsupportedByOperationError(operation);
   }

   default Throwable bucketByUnsupportedByOperationError(final String operation) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1312", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("operation"), operation)}))));
   }

   // $FF: synthetic method
   static Throwable bucketByAndSortByUnsupportedByOperationError$(final CompilationErrors $this, final String operation) {
      return $this.bucketByAndSortByUnsupportedByOperationError(operation);
   }

   default Throwable bucketByAndSortByUnsupportedByOperationError(final String operation) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1313", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("operation"), operation)}))));
   }

   // $FF: synthetic method
   static Throwable operationNotSupportPartitioningError$(final CompilationErrors $this, final String operation) {
      return $this.operationNotSupportPartitioningError(operation);
   }

   default Throwable operationNotSupportPartitioningError(final String operation) {
      return new AnalysisException("_LEGACY_ERROR_TEMP_1197", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("operation"), operation)}))));
   }

   // $FF: synthetic method
   static Throwable operationNotSupportClusteringError$(final CompilationErrors $this, final String operation) {
      return $this.operationNotSupportClusteringError(operation);
   }

   default Throwable operationNotSupportClusteringError(final String operation) {
      return new AnalysisException("CLUSTERING_NOT_SUPPORTED", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("operation"), operation)}))));
   }

   // $FF: synthetic method
   static Throwable clusterByWithPartitionedBy$(final CompilationErrors $this) {
      return $this.clusterByWithPartitionedBy();
   }

   default Throwable clusterByWithPartitionedBy() {
      return new AnalysisException("SPECIFY_CLUSTER_BY_WITH_PARTITIONED_BY_IS_NOT_ALLOWED", .MODULE$.Map().empty());
   }

   // $FF: synthetic method
   static Throwable clusterByWithBucketing$(final CompilationErrors $this) {
      return $this.clusterByWithBucketing();
   }

   default Throwable clusterByWithBucketing() {
      return new AnalysisException("SPECIFY_CLUSTER_BY_WITH_BUCKETING_IS_NOT_ALLOWED", .MODULE$.Map().empty());
   }

   static void $init$(final CompilationErrors $this) {
   }
}
