package org.apache.hadoop.hive.serde2.thrift;

import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hive.service.rpc.thrift.TTypeId;

public enum Type {
   NULL_TYPE("VOID", 0, TTypeId.NULL_TYPE),
   BOOLEAN_TYPE("BOOLEAN", 16, TTypeId.BOOLEAN_TYPE),
   TINYINT_TYPE("TINYINT", -6, TTypeId.TINYINT_TYPE),
   SMALLINT_TYPE("SMALLINT", 5, TTypeId.SMALLINT_TYPE),
   INT_TYPE("INT", 4, TTypeId.INT_TYPE),
   BIGINT_TYPE("BIGINT", -5, TTypeId.BIGINT_TYPE),
   FLOAT_TYPE("FLOAT", 6, TTypeId.FLOAT_TYPE),
   DOUBLE_TYPE("DOUBLE", 8, TTypeId.DOUBLE_TYPE),
   STRING_TYPE("STRING", 12, TTypeId.STRING_TYPE),
   CHAR_TYPE("CHAR", 1, TTypeId.CHAR_TYPE, true, false, false),
   VARCHAR_TYPE("VARCHAR", 12, TTypeId.VARCHAR_TYPE, true, false, false),
   DATE_TYPE("DATE", 91, TTypeId.DATE_TYPE),
   TIMESTAMP_TYPE("TIMESTAMP", 93, TTypeId.TIMESTAMP_TYPE),
   INTERVAL_YEAR_MONTH_TYPE("INTERVAL_YEAR_MONTH", 1111, TTypeId.INTERVAL_YEAR_MONTH_TYPE),
   INTERVAL_DAY_TIME_TYPE("INTERVAL_DAY_TIME", 1111, TTypeId.INTERVAL_DAY_TIME_TYPE),
   BINARY_TYPE("BINARY", -2, TTypeId.BINARY_TYPE),
   DECIMAL_TYPE("DECIMAL", 3, TTypeId.DECIMAL_TYPE, true, false, false),
   ARRAY_TYPE("ARRAY", 2003, TTypeId.ARRAY_TYPE, true, true),
   MAP_TYPE("MAP", 2000, TTypeId.MAP_TYPE, true, true),
   STRUCT_TYPE("STRUCT", 2002, TTypeId.STRUCT_TYPE, true, false),
   UNION_TYPE("UNIONTYPE", 1111, TTypeId.UNION_TYPE, true, false),
   USER_DEFINED_TYPE("USER_DEFINED", 1111, TTypeId.USER_DEFINED_TYPE, true, false);

   private final String name;
   private final TTypeId tType;
   private final int javaSQLType;
   private final boolean isQualified;
   private final boolean isComplex;
   private final boolean isCollection;

   private Type(String name, int javaSQLType, TTypeId tType, boolean isQualified, boolean isComplex, boolean isCollection) {
      this.name = name;
      this.javaSQLType = javaSQLType;
      this.tType = tType;
      this.isQualified = isQualified;
      this.isComplex = isComplex;
      this.isCollection = isCollection;
   }

   private Type(String name, int javaSQLType, TTypeId tType, boolean isComplex, boolean isCollection) {
      this(name, javaSQLType, tType, false, isComplex, isCollection);
   }

   private Type(String name, int javaSqlType, TTypeId tType) {
      this(name, javaSqlType, tType, false, false, false);
   }

   public boolean isPrimitiveType() {
      return !this.isComplex;
   }

   public boolean isQualifiedType() {
      return this.isQualified;
   }

   public boolean isComplexType() {
      return this.isComplex;
   }

   public boolean isCollectionType() {
      return this.isCollection;
   }

   public static Type getType(TTypeId tType) {
      for(Type type : values()) {
         if (tType.equals(type.tType)) {
            return type;
         }
      }

      throw new IllegalArgumentException("Unrecognized Thrift TTypeId value: " + tType);
   }

   public static Type getType(String name) {
      if (name == null) {
         throw new IllegalArgumentException("Invalid type name: null");
      } else {
         for(Type type : values()) {
            if (name.equalsIgnoreCase(type.name)) {
               return type;
            }

            if ((type.isQualifiedType() || type.isComplexType()) && name.toUpperCase().startsWith(type.name)) {
               return type;
            }
         }

         throw new IllegalArgumentException("Unrecognized type name: " + name);
      }
   }

   public static Type getType(TypeInfo typeInfo) {
      switch (typeInfo.getCategory()) {
         case PRIMITIVE:
            PrimitiveTypeInfo pTypeInfo = (PrimitiveTypeInfo)typeInfo;
            switch (pTypeInfo.getPrimitiveCategory()) {
               case VOID:
                  return NULL_TYPE;
               case BOOLEAN:
                  return BOOLEAN_TYPE;
               case BYTE:
                  return BINARY_TYPE;
               case SHORT:
                  return SMALLINT_TYPE;
               case INT:
                  return INT_TYPE;
               case LONG:
                  return BIGINT_TYPE;
               case FLOAT:
                  return FLOAT_TYPE;
               case DOUBLE:
                  return DOUBLE_TYPE;
               case STRING:
                  return STRING_TYPE;
               case CHAR:
                  return CHAR_TYPE;
               case VARCHAR:
                  return VARCHAR_TYPE;
               case BINARY:
                  return BINARY_TYPE;
               case DATE:
                  return DATE_TYPE;
               case TIMESTAMP:
                  return TIMESTAMP_TYPE;
               case INTERVAL_YEAR_MONTH:
                  return INTERVAL_YEAR_MONTH_TYPE;
               case INTERVAL_DAY_TIME:
                  return INTERVAL_DAY_TIME_TYPE;
               case DECIMAL:
                  return DECIMAL_TYPE;
               default:
                  throw new RuntimeException("Unrecognized type: " + pTypeInfo.getPrimitiveCategory());
            }
         case LIST:
            return STRING_TYPE;
         case MAP:
            return MAP_TYPE;
         case STRUCT:
            return STRUCT_TYPE;
         case UNION:
            return UNION_TYPE;
         default:
            throw new RuntimeException("Unrecognized type: " + typeInfo.getCategory());
      }
   }

   public Integer getNumPrecRadix() {
      return this.isNumericType() ? 10 : null;
   }

   public Integer getMaxPrecision() {
      switch (this) {
         case TINYINT_TYPE:
            return 3;
         case SMALLINT_TYPE:
            return 5;
         case INT_TYPE:
            return 10;
         case BIGINT_TYPE:
            return 19;
         case FLOAT_TYPE:
            return 7;
         case DOUBLE_TYPE:
            return 15;
         case DECIMAL_TYPE:
            return 38;
         default:
            return null;
      }
   }

   public boolean isNumericType() {
      switch (this) {
         case TINYINT_TYPE:
         case SMALLINT_TYPE:
         case INT_TYPE:
         case BIGINT_TYPE:
         case FLOAT_TYPE:
         case DOUBLE_TYPE:
         case DECIMAL_TYPE:
            return true;
         default:
            return false;
      }
   }

   public String getLiteralPrefix() {
      return null;
   }

   public String getLiteralSuffix() {
      return null;
   }

   public Short getNullable() {
      return Short.valueOf((short)1);
   }

   public Boolean isCaseSensitive() {
      switch (this) {
         case STRING_TYPE:
            return true;
         default:
            return false;
      }
   }

   public String getCreateParams() {
      return null;
   }

   public Short getSearchable() {
      return this.isPrimitiveType() ? Short.valueOf((short)3) : Short.valueOf((short)0);
   }

   public Boolean isUnsignedAttribute() {
      return this.isNumericType() ? false : true;
   }

   public Boolean isFixedPrecScale() {
      return false;
   }

   public Boolean isAutoIncrement() {
      return false;
   }

   public String getLocalizedName() {
      return null;
   }

   public Short getMinimumScale() {
      return Short.valueOf((short)0);
   }

   public Short getMaximumScale() {
      return Short.valueOf((short)0);
   }

   public TTypeId toTType() {
      return this.tType;
   }

   public int toJavaSQLType() {
      return this.javaSQLType;
   }

   public String getName() {
      return this.name;
   }
}
