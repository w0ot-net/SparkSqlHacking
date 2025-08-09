package org.apache.hive.jdbc;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.thrift.Type;

public class JdbcColumn {
   private final String columnName;
   private final String tableName;
   private final String tableCatalog;
   private final String type;
   private final String comment;
   private final int ordinalPos;

   JdbcColumn(String columnName, String tableName, String tableCatalog, String type, String comment, int ordinalPos) {
      this.columnName = columnName;
      this.tableName = tableName;
      this.tableCatalog = tableCatalog;
      this.type = type;
      this.comment = comment;
      this.ordinalPos = ordinalPos;
   }

   public String getColumnName() {
      return this.columnName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public String getTableCatalog() {
      return this.tableCatalog;
   }

   public String getType() {
      return this.type;
   }

   static String columnClassName(Type hiveType, JdbcColumnAttributes columnAttributes) throws SQLException {
      int columnType = hiveTypeToSqlType(hiveType);
      switch (columnType) {
         case -6:
            return Byte.class.getName();
         case -5:
            return Long.class.getName();
         case -2:
            return byte[].class.getName();
         case 0:
            return "null";
         case 1:
         case 12:
            return String.class.getName();
         case 3:
            return BigInteger.class.getName();
         case 4:
            return Integer.class.getName();
         case 5:
            return Short.class.getName();
         case 6:
            return Float.class.getName();
         case 8:
            return Double.class.getName();
         case 16:
            return Boolean.class.getName();
         case 91:
            return Date.class.getName();
         case 93:
            return Timestamp.class.getName();
         case 1111:
         case 2000:
            switch (hiveType) {
               case INTERVAL_YEAR_MONTH_TYPE:
                  return HiveIntervalYearMonth.class.getName();
               case INTERVAL_DAY_TIME_TYPE:
                  return HiveIntervalDayTime.class.getName();
               default:
                  return String.class.getName();
            }
         case 2002:
         case 2003:
            return String.class.getName();
         default:
            throw new SQLException("Invalid column type: " + columnType);
      }
   }

   static Type typeStringToHiveType(String type) throws SQLException {
      if ("string".equalsIgnoreCase(type)) {
         return Type.STRING_TYPE;
      } else if ("varchar".equalsIgnoreCase(type)) {
         return Type.VARCHAR_TYPE;
      } else if ("char".equalsIgnoreCase(type)) {
         return Type.CHAR_TYPE;
      } else if ("float".equalsIgnoreCase(type)) {
         return Type.FLOAT_TYPE;
      } else if ("double".equalsIgnoreCase(type)) {
         return Type.DOUBLE_TYPE;
      } else if ("boolean".equalsIgnoreCase(type)) {
         return Type.BOOLEAN_TYPE;
      } else if ("tinyint".equalsIgnoreCase(type)) {
         return Type.TINYINT_TYPE;
      } else if ("smallint".equalsIgnoreCase(type)) {
         return Type.SMALLINT_TYPE;
      } else if ("int".equalsIgnoreCase(type)) {
         return Type.INT_TYPE;
      } else if ("bigint".equalsIgnoreCase(type)) {
         return Type.BIGINT_TYPE;
      } else if ("date".equalsIgnoreCase(type)) {
         return Type.DATE_TYPE;
      } else if ("timestamp".equalsIgnoreCase(type)) {
         return Type.TIMESTAMP_TYPE;
      } else if ("interval_year_month".equalsIgnoreCase(type)) {
         return Type.INTERVAL_YEAR_MONTH_TYPE;
      } else if ("interval_day_time".equalsIgnoreCase(type)) {
         return Type.INTERVAL_DAY_TIME_TYPE;
      } else if ("decimal".equalsIgnoreCase(type)) {
         return Type.DECIMAL_TYPE;
      } else if ("binary".equalsIgnoreCase(type)) {
         return Type.BINARY_TYPE;
      } else if ("map".equalsIgnoreCase(type)) {
         return Type.MAP_TYPE;
      } else if ("array".equalsIgnoreCase(type)) {
         return Type.ARRAY_TYPE;
      } else if ("struct".equalsIgnoreCase(type)) {
         return Type.STRUCT_TYPE;
      } else if (!"void".equalsIgnoreCase(type) && !"null".equalsIgnoreCase(type)) {
         throw new SQLException("Unrecognized column type: " + type);
      } else {
         return Type.NULL_TYPE;
      }
   }

   public static int hiveTypeToSqlType(Type hiveType) throws SQLException {
      return hiveType.toJavaSQLType();
   }

   public static int hiveTypeToSqlType(String type) throws SQLException {
      return hiveTypeToSqlType(typeStringToHiveType(type));
   }

   static String getColumnTypeName(String type) throws SQLException {
      if ("string".equalsIgnoreCase(type)) {
         return "string";
      } else if ("varchar".equalsIgnoreCase(type)) {
         return "varchar";
      } else if ("char".equalsIgnoreCase(type)) {
         return "char";
      } else if ("float".equalsIgnoreCase(type)) {
         return "float";
      } else if ("double".equalsIgnoreCase(type)) {
         return "double";
      } else if ("boolean".equalsIgnoreCase(type)) {
         return "boolean";
      } else if ("tinyint".equalsIgnoreCase(type)) {
         return "tinyint";
      } else if ("smallint".equalsIgnoreCase(type)) {
         return "smallint";
      } else if ("int".equalsIgnoreCase(type)) {
         return "int";
      } else if ("bigint".equalsIgnoreCase(type)) {
         return "bigint";
      } else if ("timestamp".equalsIgnoreCase(type)) {
         return "timestamp";
      } else if ("date".equalsIgnoreCase(type)) {
         return "date";
      } else if ("interval_year_month".equalsIgnoreCase(type)) {
         return "interval_year_month";
      } else if ("interval_day_time".equalsIgnoreCase(type)) {
         return "interval_day_time";
      } else if ("decimal".equalsIgnoreCase(type)) {
         return "decimal";
      } else if ("binary".equalsIgnoreCase(type)) {
         return "binary";
      } else if (!"void".equalsIgnoreCase(type) && !"null".equalsIgnoreCase(type)) {
         if (type.equalsIgnoreCase("map")) {
            return "map";
         } else if (type.equalsIgnoreCase("array")) {
            return "array";
         } else if (type.equalsIgnoreCase("struct")) {
            return "struct";
         } else {
            throw new SQLException("Unrecognized column type: " + type);
         }
      } else {
         return "void";
      }
   }

   static int columnDisplaySize(Type hiveType, JdbcColumnAttributes columnAttributes) throws SQLException {
      int columnType = hiveTypeToSqlType(hiveType);
      switch (columnType) {
         case -6:
         case -5:
         case 4:
         case 5:
            return columnPrecision(hiveType, columnAttributes) + 1;
         case -2:
            return Integer.MAX_VALUE;
         case 0:
            return 4;
         case 1:
         case 12:
            return columnPrecision(hiveType, columnAttributes);
         case 3:
            return columnPrecision(hiveType, columnAttributes) + 2;
         case 6:
            return 24;
         case 8:
            return 25;
         case 16:
            return columnPrecision(hiveType, columnAttributes);
         case 91:
            return 10;
         case 93:
            return columnPrecision(hiveType, columnAttributes);
         case 1111:
         case 2000:
            return columnPrecision(hiveType, columnAttributes);
         case 2002:
         case 2003:
            return Integer.MAX_VALUE;
         default:
            throw new SQLException("Invalid column type: " + columnType);
      }
   }

   static int columnPrecision(Type hiveType, JdbcColumnAttributes columnAttributes) throws SQLException {
      int columnType = hiveTypeToSqlType(hiveType);
      switch (columnType) {
         case -6:
            return 3;
         case -5:
            return 19;
         case -2:
            return Integer.MAX_VALUE;
         case 0:
            return 0;
         case 1:
         case 12:
            if (columnAttributes != null) {
               return columnAttributes.precision;
            }

            return Integer.MAX_VALUE;
         case 3:
            return columnAttributes.precision;
         case 4:
            return 10;
         case 5:
            return 5;
         case 6:
            return 7;
         case 8:
            return 15;
         case 16:
            return 1;
         case 91:
            return 10;
         case 93:
            return 29;
         case 1111:
         case 2000:
            switch (hiveType) {
               case INTERVAL_YEAR_MONTH_TYPE:
                  return 11;
               case INTERVAL_DAY_TIME_TYPE:
                  return 29;
               default:
                  return Integer.MAX_VALUE;
            }
         case 2002:
         case 2003:
            return Integer.MAX_VALUE;
         default:
            throw new SQLException("Invalid column type: " + columnType);
      }
   }

   static int columnScale(Type hiveType, JdbcColumnAttributes columnAttributes) throws SQLException {
      int columnType = hiveTypeToSqlType(hiveType);
      switch (columnType) {
         case -6:
         case -5:
         case -2:
         case 0:
         case 1:
         case 4:
         case 5:
         case 12:
         case 16:
         case 91:
            return 0;
         case 3:
            return columnAttributes.scale;
         case 6:
            return 7;
         case 8:
            return 15;
         case 93:
            return 9;
         case 1111:
         case 2000:
         case 2002:
         case 2003:
            return 0;
         default:
            throw new SQLException("Invalid column type: " + columnType);
      }
   }

   public Integer getNumPrecRadix() {
      if (this.type.equalsIgnoreCase("tinyint")) {
         return 10;
      } else if (this.type.equalsIgnoreCase("smallint")) {
         return 10;
      } else if (this.type.equalsIgnoreCase("int")) {
         return 10;
      } else if (this.type.equalsIgnoreCase("bigint")) {
         return 10;
      } else if (this.type.equalsIgnoreCase("float")) {
         return 10;
      } else if (this.type.equalsIgnoreCase("double")) {
         return 10;
      } else {
         return this.type.equalsIgnoreCase("decimal") ? 10 : null;
      }
   }

   public String getComment() {
      return this.comment;
   }

   public int getOrdinalPos() {
      return this.ordinalPos;
   }
}
