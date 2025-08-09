package org.apache.hive.service.cli;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.thrift.Type;
import org.apache.hive.service.rpc.thrift.TBoolValue;
import org.apache.hive.service.rpc.thrift.TByteValue;
import org.apache.hive.service.rpc.thrift.TColumnValue;
import org.apache.hive.service.rpc.thrift.TDoubleValue;
import org.apache.hive.service.rpc.thrift.TI16Value;
import org.apache.hive.service.rpc.thrift.TI32Value;
import org.apache.hive.service.rpc.thrift.TI64Value;
import org.apache.hive.service.rpc.thrift.TStringValue;
import org.apache.spark.unsafe.types.UTF8String;

public class ColumnValue {
   private static TColumnValue booleanValue(Boolean value) {
      TBoolValue tBoolValue = new TBoolValue();
      if (value != null) {
         tBoolValue.setValue(value);
      }

      return TColumnValue.boolVal(tBoolValue);
   }

   private static TColumnValue byteValue(Byte value) {
      TByteValue tByteValue = new TByteValue();
      if (value != null) {
         tByteValue.setValue(value);
      }

      return TColumnValue.byteVal(tByteValue);
   }

   private static TColumnValue shortValue(Short value) {
      TI16Value tI16Value = new TI16Value();
      if (value != null) {
         tI16Value.setValue(value);
      }

      return TColumnValue.i16Val(tI16Value);
   }

   private static TColumnValue intValue(Integer value) {
      TI32Value tI32Value = new TI32Value();
      if (value != null) {
         tI32Value.setValue(value);
      }

      return TColumnValue.i32Val(tI32Value);
   }

   private static TColumnValue longValue(Long value) {
      TI64Value tI64Value = new TI64Value();
      if (value != null) {
         tI64Value.setValue(value);
      }

      return TColumnValue.i64Val(tI64Value);
   }

   private static TColumnValue floatValue(Float value) {
      TDoubleValue tDoubleValue = new TDoubleValue();
      if (value != null) {
         tDoubleValue.setValue((double)value);
      }

      return TColumnValue.doubleVal(tDoubleValue);
   }

   private static TColumnValue doubleValue(Double value) {
      TDoubleValue tDoubleValue = new TDoubleValue();
      if (value != null) {
         tDoubleValue.setValue(value);
      }

      return TColumnValue.doubleVal(tDoubleValue);
   }

   private static TColumnValue stringValue(String value) {
      TStringValue tStringValue = new TStringValue();
      if (value != null) {
         tStringValue.setValue(value);
      }

      return TColumnValue.stringVal(tStringValue);
   }

   private static TColumnValue stringValue(HiveChar value) {
      TStringValue tStringValue = new TStringValue();
      if (value != null) {
         tStringValue.setValue(value.toString());
      }

      return TColumnValue.stringVal(tStringValue);
   }

   private static TColumnValue stringValue(HiveVarchar value) {
      TStringValue tStringValue = new TStringValue();
      if (value != null) {
         tStringValue.setValue(value.toString());
      }

      return TColumnValue.stringVal(tStringValue);
   }

   private static TColumnValue stringValue(HiveIntervalYearMonth value) {
      TStringValue tStrValue = new TStringValue();
      if (value != null) {
         tStrValue.setValue(value.toString());
      }

      return TColumnValue.stringVal(tStrValue);
   }

   private static TColumnValue stringValue(HiveIntervalDayTime value) {
      TStringValue tStrValue = new TStringValue();
      if (value != null) {
         tStrValue.setValue(value.toString());
      }

      return TColumnValue.stringVal(tStrValue);
   }

   public static TColumnValue toTColumnValue(TypeDescriptor typeDescriptor, Object value) {
      Type type = typeDescriptor.getType();
      switch (type) {
         case BOOLEAN_TYPE:
            return booleanValue((Boolean)value);
         case TINYINT_TYPE:
            return byteValue((Byte)value);
         case SMALLINT_TYPE:
            return shortValue((Short)value);
         case INT_TYPE:
            return intValue((Integer)value);
         case BIGINT_TYPE:
            return longValue((Long)value);
         case FLOAT_TYPE:
            return floatValue((Float)value);
         case DOUBLE_TYPE:
            return doubleValue((Double)value);
         case STRING_TYPE:
            return stringValue((String)value);
         case CHAR_TYPE:
            return stringValue((HiveChar)value);
         case VARCHAR_TYPE:
            return stringValue((HiveVarchar)value);
         case DATE_TYPE:
         case TIMESTAMP_TYPE:
            return stringValue((String)value);
         case DECIMAL_TYPE:
            String plainStr = value == null ? null : ((BigDecimal)value).toPlainString();
            return stringValue(plainStr);
         case BINARY_TYPE:
            String strVal = value == null ? null : UTF8String.fromBytes((byte[])value).toString();
            return stringValue(strVal);
         case ARRAY_TYPE:
         case MAP_TYPE:
         case STRUCT_TYPE:
         case UNION_TYPE:
         case USER_DEFINED_TYPE:
         case INTERVAL_YEAR_MONTH_TYPE:
         case INTERVAL_DAY_TIME_TYPE:
            return stringValue((String)value);
         case NULL_TYPE:
            return stringValue((String)value);
         default:
            return null;
      }
   }

   private static Boolean getBooleanValue(TBoolValue tBoolValue) {
      return tBoolValue.isSetValue() ? tBoolValue.isValue() : null;
   }

   private static Byte getByteValue(TByteValue tByteValue) {
      return tByteValue.isSetValue() ? tByteValue.getValue() : null;
   }

   private static Short getShortValue(TI16Value tI16Value) {
      return tI16Value.isSetValue() ? tI16Value.getValue() : null;
   }

   private static Integer getIntegerValue(TI32Value tI32Value) {
      return tI32Value.isSetValue() ? tI32Value.getValue() : null;
   }

   private static Long getLongValue(TI64Value tI64Value) {
      return tI64Value.isSetValue() ? tI64Value.getValue() : null;
   }

   private static Double getDoubleValue(TDoubleValue tDoubleValue) {
      return tDoubleValue.isSetValue() ? tDoubleValue.getValue() : null;
   }

   private static String getStringValue(TStringValue tStringValue) {
      return tStringValue.isSetValue() ? tStringValue.getValue() : null;
   }

   private static Date getDateValue(TStringValue tStringValue) {
      return tStringValue.isSetValue() ? Date.valueOf(tStringValue.getValue()) : null;
   }

   private static Timestamp getTimestampValue(TStringValue tStringValue) {
      return tStringValue.isSetValue() ? Timestamp.valueOf(tStringValue.getValue()) : null;
   }

   private static byte[] getBinaryValue(TStringValue tString) {
      return tString.isSetValue() ? tString.getValue().getBytes() : null;
   }

   private static BigDecimal getBigDecimalValue(TStringValue tStringValue) {
      return tStringValue.isSetValue() ? new BigDecimal(tStringValue.getValue()) : null;
   }

   public static Object toColumnValue(TColumnValue value) {
      TColumnValue._Fields field = (TColumnValue._Fields)value.getSetField();
      switch (field) {
         case BOOL_VAL -> {
            return getBooleanValue(value.getBoolVal());
         }
         case BYTE_VAL -> {
            return getByteValue(value.getByteVal());
         }
         case I16_VAL -> {
            return getShortValue(value.getI16Val());
         }
         case I32_VAL -> {
            return getIntegerValue(value.getI32Val());
         }
         case I64_VAL -> {
            return getLongValue(value.getI64Val());
         }
         case DOUBLE_VAL -> {
            return getDoubleValue(value.getDoubleVal());
         }
         case STRING_VAL -> {
            return getStringValue(value.getStringVal());
         }
         default -> throw new IllegalArgumentException("never");
      }
   }
}
