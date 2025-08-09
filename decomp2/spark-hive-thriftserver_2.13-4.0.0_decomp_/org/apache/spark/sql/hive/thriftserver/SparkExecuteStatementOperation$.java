package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.Collections;
import java.util.Map;
import org.apache.hive.service.rpc.thrift.TColumnDesc;
import org.apache.hive.service.rpc.thrift.TPrimitiveTypeEntry;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.hive.service.rpc.thrift.TTypeDesc;
import org.apache.hive.service.rpc.thrift.TTypeEntry;
import org.apache.hive.service.rpc.thrift.TTypeId;
import org.apache.hive.service.rpc.thrift.TTypeQualifierValue;
import org.apache.hive.service.rpc.thrift.TTypeQualifiers;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.CharType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DayTimeIntervalType;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.VarcharType;
import org.apache.spark.sql.types.YearMonthIntervalType;
import org.apache.spark.sql.types.NullType.;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.runtime.BoxedUnit;

public final class SparkExecuteStatementOperation$ {
   public static final SparkExecuteStatementOperation$ MODULE$ = new SparkExecuteStatementOperation$();

   public boolean $lessinit$greater$default$5() {
      return true;
   }

   public TTypeId toTTypeId(final DataType typ) {
      if (.MODULE$.equals(typ)) {
         return TTypeId.NULL_TYPE;
      } else if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(typ)) {
         return TTypeId.BOOLEAN_TYPE;
      } else if (org.apache.spark.sql.types.ByteType..MODULE$.equals(typ)) {
         return TTypeId.TINYINT_TYPE;
      } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(typ)) {
         return TTypeId.SMALLINT_TYPE;
      } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(typ)) {
         return TTypeId.INT_TYPE;
      } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(typ)) {
         return TTypeId.BIGINT_TYPE;
      } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(typ)) {
         return TTypeId.FLOAT_TYPE;
      } else if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(typ)) {
         return TTypeId.DOUBLE_TYPE;
      } else if (typ instanceof CharType) {
         return TTypeId.CHAR_TYPE;
      } else if (typ instanceof VarcharType) {
         return TTypeId.VARCHAR_TYPE;
      } else if (typ instanceof StringType) {
         return TTypeId.STRING_TYPE;
      } else if (typ instanceof DecimalType) {
         return TTypeId.DECIMAL_TYPE;
      } else if (org.apache.spark.sql.types.DateType..MODULE$.equals(typ)) {
         return TTypeId.DATE_TYPE;
      } else if (org.apache.spark.sql.types.TimestampType..MODULE$.equals(typ)) {
         return TTypeId.TIMESTAMP_TYPE;
      } else if (org.apache.spark.sql.types.TimestampNTZType..MODULE$.equals(typ)) {
         return TTypeId.TIMESTAMP_TYPE;
      } else if (org.apache.spark.sql.types.BinaryType..MODULE$.equals(typ)) {
         return TTypeId.BINARY_TYPE;
      } else if (org.apache.spark.sql.types.CalendarIntervalType..MODULE$.equals(typ)) {
         return TTypeId.STRING_TYPE;
      } else if (typ instanceof DayTimeIntervalType) {
         return TTypeId.INTERVAL_DAY_TIME_TYPE;
      } else if (typ instanceof YearMonthIntervalType) {
         return TTypeId.INTERVAL_YEAR_MONTH_TYPE;
      } else if (typ instanceof ArrayType) {
         return TTypeId.ARRAY_TYPE;
      } else if (typ instanceof MapType) {
         return TTypeId.MAP_TYPE;
      } else if (typ instanceof StructType) {
         return TTypeId.STRUCT_TYPE;
      } else {
         throw new IllegalArgumentException("Unrecognized type name: " + typ.catalogString());
      }
   }

   private TTypeQualifiers toTTypeQualifiers(final DataType typ) {
      TTypeQualifiers ret = new TTypeQualifiers();
      Map var10000;
      if (typ instanceof DecimalType var7) {
         var10000 = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("precision"), TTypeQualifierValue.i32Value(var7.precision())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("scale"), TTypeQualifierValue.i32Value(var7.scale()))})))).asJava();
      } else {
         var10000 = (typ instanceof VarcharType ? true : typ instanceof CharType) ? scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("characterMaximumLength"), TTypeQualifierValue.i32Value(typ.defaultSize()))})))).asJava() : Collections.emptyMap();
      }

      Map qualifiers = var10000;
      ret.setQualifiers(qualifiers);
      return ret;
   }

   private TTypeDesc toTTypeDesc(final DataType typ) {
      TPrimitiveTypeEntry typeEntry = new TPrimitiveTypeEntry(this.toTTypeId(typ));
      typeEntry.setTypeQualifiers(this.toTTypeQualifiers(typ));
      TTypeDesc tTypeDesc = new TTypeDesc();
      tTypeDesc.addToTypes(TTypeEntry.primitiveEntry(typeEntry));
      return tTypeDesc;
   }

   private TColumnDesc toTColumnDesc(final StructField field, final int pos) {
      TColumnDesc tColumnDesc = new TColumnDesc();
      tColumnDesc.setColumnName(field.name());
      tColumnDesc.setTypeDesc(this.toTTypeDesc(field.dataType()));
      tColumnDesc.setComment((String)field.getComment().getOrElse(() -> ""));
      tColumnDesc.setPosition(pos);
      return tColumnDesc;
   }

   public TTableSchema toTTableSchema(final StructType schema) {
      TTableSchema tTableSchema = new TTableSchema();
      ((IterableOnceOps)org.apache.spark.sql.catalyst.util.CharVarcharUtils..MODULE$.getRawSchema(schema).zipWithIndex()).foreach((x0$1) -> {
         $anonfun$toTTableSchema$1(tTableSchema, x0$1);
         return BoxedUnit.UNIT;
      });
      return tTableSchema;
   }

   // $FF: synthetic method
   public static final void $anonfun$toTTableSchema$1(final TTableSchema tTableSchema$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         StructField f = (StructField)x0$1._1();
         int i = x0$1._2$mcI$sp();
         tTableSchema$1.addToColumns(MODULE$.toTColumnDesc(f, i));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private SparkExecuteStatementOperation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
