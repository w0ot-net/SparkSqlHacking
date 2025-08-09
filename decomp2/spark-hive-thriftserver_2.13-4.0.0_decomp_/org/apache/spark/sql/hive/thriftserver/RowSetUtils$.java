package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.hive.service.rpc.thrift.TBinaryColumn;
import org.apache.hive.service.rpc.thrift.TBoolColumn;
import org.apache.hive.service.rpc.thrift.TBoolValue;
import org.apache.hive.service.rpc.thrift.TByteColumn;
import org.apache.hive.service.rpc.thrift.TByteValue;
import org.apache.hive.service.rpc.thrift.TColumn;
import org.apache.hive.service.rpc.thrift.TColumnValue;
import org.apache.hive.service.rpc.thrift.TDoubleColumn;
import org.apache.hive.service.rpc.thrift.TDoubleValue;
import org.apache.hive.service.rpc.thrift.TI16Column;
import org.apache.hive.service.rpc.thrift.TI16Value;
import org.apache.hive.service.rpc.thrift.TI32Column;
import org.apache.hive.service.rpc.thrift.TI32Value;
import org.apache.hive.service.rpc.thrift.TI64Column;
import org.apache.hive.service.rpc.thrift.TI64Value;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRow;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TStringColumn;
import org.apache.hive.service.rpc.thrift.TStringValue;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.execution.HiveResult;
import org.apache.spark.sql.execution.HiveResult.;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;

public final class RowSetUtils$ {
   public static final RowSetUtils$ MODULE$ = new RowSetUtils$();

   public ByteBuffer bitSetToBuffer(final BitSet bitSet) {
      return ByteBuffer.wrap(bitSet.toByteArray());
   }

   public TRowSet toTRowSet(final long startRowOffSet, final Seq rows, final DataType[] schema, final TProtocolVersion protocolVersion) {
      return protocolVersion.getValue() < TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V6.getValue() ? this.toRowBasedSet(startRowOffSet, rows, schema, .MODULE$.getTimeFormatters(), .MODULE$.getBinaryFormatter()) : this.toColumnBasedSet(startRowOffSet, rows, schema, .MODULE$.getTimeFormatters(), .MODULE$.getBinaryFormatter());
   }

   private TRowSet toRowBasedSet(final long startRowOffSet, final Seq rows, final DataType[] schema, final HiveResult.TimeFormatters timeFormatters, final Function1 binaryFormatter) {
      List tRows = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((scala.collection.Seq)rows.map((row) -> {
         TRow tRow = new TRow();
         int j = 0;

         for(int columnSize = row.length(); j < columnSize; ++j) {
            TColumnValue columnValue = MODULE$.toTColumnValue(j, row, schema[j], timeFormatters, binaryFormatter);
            tRow.addToColVals(columnValue);
         }

         return tRow;
      })).asJava();
      return new TRowSet(startRowOffSet, tRows);
   }

   private TRowSet toColumnBasedSet(final long startRowOffSet, final Seq rows, final DataType[] schema, final HiveResult.TimeFormatters timeFormatters, final Function1 binaryFormatter) {
      int rowSize = rows.length();
      TRowSet tRowSet = new TRowSet(startRowOffSet, new ArrayList(rowSize));
      int i = 0;
      int columnSize = schema.length;

      ArrayList columns;
      for(columns = new ArrayList(columnSize); i < columnSize; ++i) {
         columns.add(i, this.toTColumn(rows, i, schema[i], timeFormatters, binaryFormatter));
      }

      tRowSet.setColumns(columns);
      return tRowSet;
   }

   private TColumn toTColumn(final Seq rows, final int ordinal, final DataType typ, final HiveResult.TimeFormatters timeFormatters, final Function1 binaryFormatter) {
      BitSet nulls = new BitSet();
      if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(typ)) {
         List values = this.getOrSetAsNull(rows, ordinal, nulls, scala.Predef..MODULE$.boolean2Boolean(true));
         return TColumn.boolVal(new TBoolColumn(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.ByteType..MODULE$.equals(typ)) {
         List values = this.getOrSetAsNull(rows, ordinal, nulls, scala.Predef..MODULE$.byte2Byte((byte)0));
         return TColumn.byteVal(new TByteColumn(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(typ)) {
         List values = this.getOrSetAsNull(rows, ordinal, nulls, scala.Predef..MODULE$.short2Short((short)0));
         return TColumn.i16Val(new TI16Column(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(typ)) {
         List values = this.getOrSetAsNull(rows, ordinal, nulls, scala.Predef..MODULE$.int2Integer(0));
         return TColumn.i32Val(new TI32Column(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(typ)) {
         List values = this.getOrSetAsNull(rows, ordinal, nulls, scala.Predef..MODULE$.long2Long(0L));
         return TColumn.i64Val(new TI64Column(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(typ)) {
         List values = scala.jdk.CollectionConverters..MODULE$.BufferHasAsJava((Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.getOrSetAsNull(rows, ordinal, nulls, scala.Predef..MODULE$.float2Float(0.0F))).asScala().map((n) -> Double.valueOf(n.toString()))).asJava();
         return TColumn.doubleVal(new TDoubleColumn(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(typ)) {
         List values = this.getOrSetAsNull(rows, ordinal, nulls, scala.Predef..MODULE$.double2Double((double)0.0F));
         return TColumn.doubleVal(new TDoubleColumn(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.StringType..MODULE$.equals(typ)) {
         List values = this.getOrSetAsNull(rows, ordinal, nulls, "");
         return TColumn.stringVal(new TStringColumn(values, this.bitSetToBuffer(nulls)));
      } else if (org.apache.spark.sql.types.BinaryType..MODULE$.equals(typ)) {
         List values = scala.jdk.CollectionConverters..MODULE$.BufferHasAsJava((Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.getOrSetAsNull(rows, ordinal, nulls, scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Byte()))).asScala().map((x$1) -> ByteBuffer.wrap(x$1))).asJava();
         return TColumn.binaryVal(new TBinaryColumn(values, this.bitSetToBuffer(nulls)));
      } else {
         IntRef i = IntRef.create(0);
         int rowSize = rows.length();
         ArrayList values = new ArrayList(rowSize);
         rows.foreach((row) -> {
            $anonfun$toTColumn$3(nulls, i, ordinal, typ, timeFormatters, binaryFormatter, values, row);
            return BoxedUnit.UNIT;
         });
         return TColumn.stringVal(new TStringColumn(values, this.bitSetToBuffer(nulls)));
      }
   }

   private List getOrSetAsNull(final Seq rows, final int ordinal, final BitSet nulls, final Object defaultVal) {
      int size = rows.length();
      ArrayList ret = new ArrayList(size);
      IntRef idx = IntRef.create(0);
      rows.foreach((row) -> {
         $anonfun$getOrSetAsNull$1(ordinal, nulls, idx, ret, defaultVal, row);
         return BoxedUnit.UNIT;
      });
      return ret;
   }

   private TColumnValue toTColumnValue(final int ordinal, final Row row, final DataType dataType, final HiveResult.TimeFormatters timeFormatters, final Function1 binaryFormatter) {
      if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(dataType)) {
         TBoolValue boolValue = new TBoolValue();
         if (!row.isNullAt(ordinal)) {
            boolValue.setValue(row.getBoolean(ordinal));
         }

         return TColumnValue.boolVal(boolValue);
      } else if (org.apache.spark.sql.types.ByteType..MODULE$.equals(dataType)) {
         TByteValue byteValue = new TByteValue();
         if (!row.isNullAt(ordinal)) {
            byteValue.setValue(row.getByte(ordinal));
         }

         return TColumnValue.byteVal(byteValue);
      } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(dataType)) {
         TI16Value tI16Value = new TI16Value();
         if (!row.isNullAt(ordinal)) {
            tI16Value.setValue(row.getShort(ordinal));
         }

         return TColumnValue.i16Val(tI16Value);
      } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(dataType)) {
         TI32Value tI32Value = new TI32Value();
         if (!row.isNullAt(ordinal)) {
            tI32Value.setValue(row.getInt(ordinal));
         }

         return TColumnValue.i32Val(tI32Value);
      } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(dataType)) {
         TI64Value tI64Value = new TI64Value();
         if (!row.isNullAt(ordinal)) {
            tI64Value.setValue(row.getLong(ordinal));
         }

         return TColumnValue.i64Val(tI64Value);
      } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(dataType)) {
         TDoubleValue tDoubleValue = new TDoubleValue();
         if (!row.isNullAt(ordinal)) {
            Double doubleValue = Double.valueOf(Float.toString(row.getFloat(ordinal)));
            tDoubleValue.setValue(scala.Predef..MODULE$.Double2double(doubleValue));
         }

         return TColumnValue.doubleVal(tDoubleValue);
      } else if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(dataType)) {
         TDoubleValue tDoubleValue = new TDoubleValue();
         if (!row.isNullAt(ordinal)) {
            tDoubleValue.setValue(row.getDouble(ordinal));
         }

         return TColumnValue.doubleVal(tDoubleValue);
      } else if (org.apache.spark.sql.types.StringType..MODULE$.equals(dataType)) {
         TStringValue tStringValue = new TStringValue();
         if (!row.isNullAt(ordinal)) {
            tStringValue.setValue(row.getString(ordinal));
         }

         return TColumnValue.stringVal(tStringValue);
      } else {
         TStringValue tStrValue = new TStringValue();
         if (!row.isNullAt(ordinal)) {
            String value = .MODULE$.toHiveString(new Tuple2(row.get(ordinal), dataType), false, timeFormatters, binaryFormatter);
            tStrValue.setValue(value);
         }

         return TColumnValue.stringVal(tStrValue);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$toTColumn$3(final BitSet nulls$1, final IntRef i$1, final int ordinal$1, final DataType typ$1, final HiveResult.TimeFormatters timeFormatters$2, final Function1 binaryFormatter$2, final ArrayList values$1, final Row row) {
      nulls$1.set(i$1.elem, row.isNullAt(ordinal$1));
      String value = row.isNullAt(ordinal$1) ? "" : .MODULE$.toHiveString(new Tuple2(row.get(ordinal$1), typ$1), true, timeFormatters$2, binaryFormatter$2);
      values$1.add(value);
      ++i$1.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$getOrSetAsNull$1(final int ordinal$2, final BitSet nulls$2, final IntRef idx$1, final ArrayList ret$1, final Object defaultVal$1, final Row row) {
      if (row.isNullAt(ordinal$2)) {
         nulls$2.set(idx$1.elem, true);
         ret$1.add(idx$1.elem, defaultVal$1);
      } else {
         ret$1.add(idx$1.elem, row.getAs(ordinal$2));
      }

      ++idx$1.elem;
   }

   private RowSetUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
