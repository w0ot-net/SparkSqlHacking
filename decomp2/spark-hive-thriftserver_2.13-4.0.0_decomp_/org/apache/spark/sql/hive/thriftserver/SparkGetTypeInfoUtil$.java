package org.apache.spark.sql.hive.thriftserver;

import org.apache.hadoop.hive.serde2.thrift.Type;
import scala.collection.immutable.Seq;
import scala.package.;

public final class SparkGetTypeInfoUtil$ {
   public static final SparkGetTypeInfoUtil$ MODULE$ = new SparkGetTypeInfoUtil$();
   private static final Seq supportedType;

   static {
      supportedType = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Type[]{Type.NULL_TYPE, Type.BOOLEAN_TYPE, Type.STRING_TYPE, Type.BINARY_TYPE, Type.TINYINT_TYPE, Type.SMALLINT_TYPE, Type.INT_TYPE, Type.BIGINT_TYPE, Type.FLOAT_TYPE, Type.DOUBLE_TYPE, Type.DECIMAL_TYPE, Type.DATE_TYPE, Type.TIMESTAMP_TYPE, Type.ARRAY_TYPE, Type.MAP_TYPE, Type.STRUCT_TYPE, Type.CHAR_TYPE, Type.VARCHAR_TYPE, Type.INTERVAL_YEAR_MONTH_TYPE, Type.INTERVAL_DAY_TIME_TYPE})));
   }

   public Seq supportedType() {
      return supportedType;
   }

   private SparkGetTypeInfoUtil$() {
   }
}
