package org.apache.spark.sql.catalyst.encoders;

import org.apache.spark.QueryContext;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M;QAB\u0004\t\u0002Q1QAF\u0004\t\u0002]AQ\u0001J\u0001\u0005\u0002\u0015BQAJ\u0001\u0005\u0002\u001dBQAJ\u0001\u0005\u0002]BaAP\u0001\u0005\u0002-y\u0014A\u0003*po\u0016s7m\u001c3fe*\u0011\u0001\"C\u0001\tK:\u001cw\u000eZ3sg*\u0011!bC\u0001\tG\u0006$\u0018\r\\=ti*\u0011A\"D\u0001\u0004gFd'B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0004\u0001A\u0011Q#A\u0007\u0002\u000f\tQ!k\\<F]\u000e|G-\u001a:\u0014\u0007\u0005Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0003?\tj\u0011\u0001\t\u0006\u0003C-\ta!\u001a:s_J\u001c\u0018BA\u0012!\u0005I!\u0015\r^1UsB,WI\u001d:peN\u0014\u0015m]3\u0002\rqJg.\u001b;?)\u0005!\u0012AC3oG>$WM\u001d$peR\u0011\u0001f\f\t\u0004+%Z\u0013B\u0001\u0016\b\u0005=\tuM\\8ti&\u001cWI\\2pI\u0016\u0014\bC\u0001\u0017.\u001b\u0005Y\u0011B\u0001\u0018\f\u0005\r\u0011vn\u001e\u0005\u0006a\r\u0001\r!M\u0001\u0007g\u000eDW-\\1\u0011\u0005I*T\"A\u001a\u000b\u0005QZ\u0011!\u0002;za\u0016\u001c\u0018B\u0001\u001c4\u0005)\u0019FO];diRK\b/\u001a\u000b\u0004QaJ\u0004\"\u0002\u0019\u0005\u0001\u0004\t\u0004\"\u0002\u001e\u0005\u0001\u0004Y\u0014a\u00027f]&,g\u000e\u001e\t\u00033qJ!!\u0010\u000e\u0003\u000f\t{w\u000e\\3b]\u0006\u0011RM\\2pI\u0016\u0014hi\u001c:ECR\fG+\u001f9f)\r\u0001UJ\u0015\u0019\u0003\u0003\u0012\u00032!F\u0015C!\t\u0019E\t\u0004\u0001\u0005\u0013\u0015+\u0011\u0011!A\u0001\u0006\u00031%aA0%cE\u0011qI\u0013\t\u00033!K!!\u0013\u000e\u0003\u000f9{G\u000f[5oOB\u0011\u0011dS\u0005\u0003\u0019j\u00111!\u00118z\u0011\u0015qU\u00011\u0001P\u0003!!\u0017\r^1UsB,\u0007C\u0001\u001aQ\u0013\t\t6G\u0001\u0005ECR\fG+\u001f9f\u0011\u0015QT\u00011\u0001<\u0001"
)
public final class RowEncoder {
   public static AgnosticEncoder encoderFor(final StructType schema, final boolean lenient) {
      return RowEncoder$.MODULE$.encoderFor(schema, lenient);
   }

   public static AgnosticEncoder encoderFor(final StructType schema) {
      return RowEncoder$.MODULE$.encoderFor(schema);
   }

   public static String toDSOption(final String option) {
      return RowEncoder$.MODULE$.toDSOption(option);
   }

   public static QueryContext[] getQueryContext(final QueryContext context) {
      return RowEncoder$.MODULE$.getQueryContext(context);
   }

   public static String getSummary(final QueryContext sqlContext) {
      return RowEncoder$.MODULE$.getSummary(sqlContext);
   }

   public static String toSQLValue(final double value) {
      return RowEncoder$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final float value) {
      return RowEncoder$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final long value) {
      return RowEncoder$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final int value) {
      return RowEncoder$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final short value) {
      return RowEncoder$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final UTF8String value) {
      return RowEncoder$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final String value) {
      return RowEncoder$.MODULE$.toSQLValue(value);
   }

   public static String toSQLType(final AbstractDataType t) {
      return RowEncoder$.MODULE$.toSQLType(t);
   }

   public static String toSQLType(final String text) {
      return RowEncoder$.MODULE$.toSQLType(text);
   }

   public static String toSQLConf(final String conf) {
      return RowEncoder$.MODULE$.toSQLConf(conf);
   }

   public static String toSQLStmt(final String text) {
      return RowEncoder$.MODULE$.toSQLStmt(text);
   }

   public static String toSQLId(final Seq parts) {
      return RowEncoder$.MODULE$.toSQLId(parts);
   }

   public static String toSQLId(final String parts) {
      return RowEncoder$.MODULE$.toSQLId(parts);
   }
}
