package org.apache.parquet.format;

public class LogicalTypes {
   public static final LogicalType UTF8 = LogicalType.STRING(new StringType());
   public static final LogicalType MAP = LogicalType.MAP(new MapType());
   public static final LogicalType LIST = LogicalType.LIST(new ListType());
   public static final LogicalType ENUM = LogicalType.ENUM(new EnumType());
   public static final LogicalType DATE = LogicalType.DATE(new DateType());
   public static final LogicalType TIME_MILLIS;
   public static final LogicalType TIME_MICROS;
   public static final LogicalType TIMESTAMP_MILLIS;
   public static final LogicalType TIMESTAMP_MICROS;
   public static final LogicalType INT_8;
   public static final LogicalType INT_16;
   public static final LogicalType INT_32;
   public static final LogicalType INT_64;
   public static final LogicalType UINT_8;
   public static final LogicalType UINT_16;
   public static final LogicalType UINT_32;
   public static final LogicalType UINT_64;
   public static final LogicalType UNKNOWN;
   public static final LogicalType JSON;
   public static final LogicalType BSON;
   public static final LogicalType FLOAT16;

   public static LogicalType DECIMAL(int scale, int precision) {
      return LogicalType.DECIMAL(new DecimalType(scale, precision));
   }

   static {
      TIME_MILLIS = LogicalType.TIME(new TimeType(true, LogicalTypes.TimeUnits.MILLIS));
      TIME_MICROS = LogicalType.TIME(new TimeType(true, LogicalTypes.TimeUnits.MICROS));
      TIMESTAMP_MILLIS = LogicalType.TIMESTAMP(new TimestampType(true, LogicalTypes.TimeUnits.MILLIS));
      TIMESTAMP_MICROS = LogicalType.TIMESTAMP(new TimestampType(true, LogicalTypes.TimeUnits.MICROS));
      INT_8 = LogicalType.INTEGER(new IntType((byte)8, true));
      INT_16 = LogicalType.INTEGER(new IntType((byte)16, true));
      INT_32 = LogicalType.INTEGER(new IntType((byte)32, true));
      INT_64 = LogicalType.INTEGER(new IntType((byte)64, true));
      UINT_8 = LogicalType.INTEGER(new IntType((byte)8, false));
      UINT_16 = LogicalType.INTEGER(new IntType((byte)16, false));
      UINT_32 = LogicalType.INTEGER(new IntType((byte)32, false));
      UINT_64 = LogicalType.INTEGER(new IntType((byte)64, false));
      UNKNOWN = LogicalType.UNKNOWN(new NullType());
      JSON = LogicalType.JSON(new JsonType());
      BSON = LogicalType.BSON(new BsonType());
      FLOAT16 = LogicalType.FLOAT16(new Float16Type());
   }

   public static class TimeUnits {
      public static final TimeUnit MILLIS = TimeUnit.MILLIS(new MilliSeconds());
      public static final TimeUnit MICROS = TimeUnit.MICROS(new MicroSeconds());
   }
}
