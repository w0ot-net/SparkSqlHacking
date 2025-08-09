package org.apache.derby.iapi.types;

import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.types.BaseTypeIdImpl;
import org.apache.derby.catalog.types.DecimalTypeIdImpl;
import org.apache.derby.catalog.types.TypeDescriptorImpl;
import org.apache.derby.catalog.types.UserDefinedTypeIdImpl;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.shared.common.error.StandardException;

public final class TypeId {
   public static final int LONGINT_PRECISION = 19;
   public static final int LONGINT_SCALE = 0;
   public static final int LONGINT_MAXWIDTH = 8;
   public static final int INT_PRECISION = 10;
   public static final int INT_SCALE = 0;
   public static final int INT_MAXWIDTH = 4;
   public static final int SMALLINT_PRECISION = 5;
   public static final int SMALLINT_SCALE = 0;
   public static final int SMALLINT_MAXWIDTH = 2;
   public static final int TINYINT_PRECISION = 3;
   public static final int TINYINT_SCALE = 0;
   public static final int TINYINT_MAXWIDTH = 1;
   public static final int DOUBLE_PRECISION = 52;
   public static final int DOUBLE_PRECISION_IN_DIGITS = 15;
   public static final int DOUBLE_SCALE = 0;
   public static final int DOUBLE_MAXWIDTH = 8;
   public static final int REAL_PRECISION = 23;
   public static final int REAL_PRECISION_IN_DIGITS = 7;
   public static final int REAL_SCALE = 0;
   public static final int REAL_MAXWIDTH = 4;
   public static final int DECIMAL_PRECISION = 31;
   public static final int DECIMAL_SCALE = 31;
   public static final int DECIMAL_MAXWIDTH = 31;
   public static final int BOOLEAN_MAXWIDTH = 1;
   public static final int CHAR_MAXWIDTH = 254;
   public static final int VARCHAR_MAXWIDTH = 32672;
   public static final int LONGVARCHAR_MAXWIDTH = 32700;
   public static final int BIT_MAXWIDTH = 254;
   public static final int VARBIT_MAXWIDTH = 32672;
   public static final int LONGVARBIT_MAXWIDTH = 32700;
   public static final int BLOB_MAXWIDTH = Integer.MAX_VALUE;
   public static final int CLOB_MAXWIDTH = Integer.MAX_VALUE;
   public static final int XML_MAXWIDTH = Integer.MAX_VALUE;
   public static final int DATE_MAXWIDTH = 10;
   public static final int TIME_MAXWIDTH = 8;
   public static final int TIMESTAMP_MAXWIDTH = 29;
   public static final int TIME_SCALE = 0;
   public static final int TIMESTAMP_SCALE = 9;
   public static final String BIT_NAME = "CHAR () FOR BIT DATA";
   public static final String VARBIT_NAME = "VARCHAR () FOR BIT DATA";
   public static final String LONGVARBIT_NAME = "LONG VARCHAR FOR BIT DATA";
   public static final String TINYINT_NAME = "TINYINT";
   public static final String SMALLINT_NAME = "SMALLINT";
   public static final String INTEGER_NAME = "INTEGER";
   public static final String BIGINT_NAME = "BIGINT";
   public static final String FLOAT_NAME = "FLOAT";
   public static final String REAL_NAME = "REAL";
   public static final String DOUBLE_NAME = "DOUBLE";
   public static final String NUMERIC_NAME = "NUMERIC";
   public static final String DECIMAL_NAME = "DECIMAL";
   public static final String CHAR_NAME = "CHAR";
   public static final String VARCHAR_NAME = "VARCHAR";
   public static final String LONGVARCHAR_NAME = "LONG VARCHAR";
   public static final String DATE_NAME = "DATE";
   public static final String TIME_NAME = "TIME";
   public static final String TIMESTAMP_NAME = "TIMESTAMP";
   public static final String BINARY_NAME = "BINARY";
   public static final String VARBINARY_NAME = "VARBINARY";
   public static final String LONGVARBINARY_NAME = "LONGVARBINARY";
   public static final String BOOLEAN_NAME = "BOOLEAN";
   public static final String REF_NAME = "REF";
   public static final String REF_CURSOR = "REF CURSOR";
   public static final String NATIONAL_CHAR_NAME = "NATIONAL CHAR";
   public static final String NATIONAL_VARCHAR_NAME = "NATIONAL CHAR VARYING";
   public static final String NATIONAL_LONGVARCHAR_NAME = "LONG NVARCHAR";
   public static final String BLOB_NAME = "BLOB";
   public static final String CLOB_NAME = "CLOB";
   public static final String NCLOB_NAME = "NCLOB";
   public static final String XML_NAME = "XML";
   public static final String ARRAY_NAME = "ARRAY";
   public static final String STRUCT_NAME = "STRUCT";
   public static final String DATALINK_NAME = "DATALINK";
   public static final String ROWID_NAME = "ROWID";
   public static final String SQLXML_NAME = "SQLXML";
   public static final int USER_PRECEDENCE = 1000;
   public static final int XML_PRECEDENCE = 180;
   public static final int BLOB_PRECEDENCE = 170;
   public static final int LONGVARBIT_PRECEDENCE = 160;
   public static final int VARBIT_PRECEDENCE = 150;
   public static final int BIT_PRECEDENCE = 140;
   public static final int BOOLEAN_PRECEDENCE = 130;
   public static final int TIME_PRECEDENCE = 120;
   public static final int TIMESTAMP_PRECEDENCE = 110;
   public static final int DATE_PRECEDENCE = 100;
   public static final int DOUBLE_PRECEDENCE = 90;
   public static final int REAL_PRECEDENCE = 80;
   public static final int DECIMAL_PRECEDENCE = 70;
   public static final int NUMERIC_PRECEDENCE = 69;
   public static final int LONGINT_PRECEDENCE = 60;
   public static final int INT_PRECEDENCE = 50;
   public static final int SMALLINT_PRECEDENCE = 40;
   public static final int TINYINT_PRECEDENCE = 30;
   public static final int REF_PRECEDENCE = 25;
   public static final int CLOB_PRECEDENCE = 14;
   public static final int LONGVARCHAR_PRECEDENCE = 12;
   public static final int VARCHAR_PRECEDENCE = 10;
   public static final int CHAR_PRECEDENCE = 0;
   public static final TypeId BOOLEAN_ID = create(4, 16);
   public static final TypeId SMALLINT_ID = create(10, 22);
   public static final TypeId INTEGER_ID = create(7, 19);
   public static final TypeId CHAR_ID = create(5, 17);
   private static final TypeId TINYINT_ID = create(195, 196);
   public static final TypeId BIGINT_ID = create(11, 23);
   private static final TypeId REAL_ID = create(8, 20);
   public static final TypeId DOUBLE_ID = create(6, 18);
   private static final TypeId DECIMAL_ID = new TypeId(197, new DecimalTypeIdImpl(false));
   private static final TypeId NUMERIC_ID = new TypeId(197, new DecimalTypeIdImpl(true));
   private static final TypeId VARCHAR_ID = create(13, 25);
   private static final TypeId DATE_ID = create(40, 32);
   private static final TypeId TIME_ID = create(35, 33);
   private static final TypeId TIMESTAMP_ID = create(36, 34);
   private static final TypeId BIT_ID = create(27, 28);
   private static final TypeId VARBIT_ID = create(29, 30);
   private static final TypeId REF_ID = create(9, 21);
   private static final TypeId LONGVARCHAR_ID = create(230, 231);
   private static final TypeId LONGVARBIT_ID = create(232, 233);
   private static final TypeId BLOB_ID = create(440, 442);
   private static final TypeId CLOB_ID = create(444, 446);
   private static final TypeId XML_ID = create(456, 457);
   private static final TypeId[] ALL_BUILTIN_TYPE_IDS;
   private BaseTypeIdImpl baseTypeId;
   private int formatId;
   private boolean isBitTypeId;
   private boolean isLOBTypeId;
   private boolean isBooleanTypeId;
   private boolean isConcatableTypeId;
   private boolean isDecimalTypeId;
   private boolean isLongConcatableTypeId;
   private boolean isNumericTypeId;
   private boolean isRefTypeId;
   private boolean isStringTypeId;
   private boolean isFloatingPointTypeId;
   private boolean isRealTypeId;
   private boolean isDateTimeTimeStampTypeId;
   private boolean isUserDefinedTypeId;
   private int maxPrecision;
   private int maxScale;
   private int typePrecedence;
   private String javaTypeName;
   private int maxMaxWidth;

   private static TypeId create(int var0, int var1) {
      return new TypeId(var0, new BaseTypeIdImpl(var1));
   }

   public static TypeId[] getAllBuiltinTypeIds() {
      int var0 = ALL_BUILTIN_TYPE_IDS.length;
      TypeId[] var1 = new TypeId[var0];

      for(int var2 = 0; var2 < var0; ++var2) {
         var1[var2] = ALL_BUILTIN_TYPE_IDS[var2];
      }

      return var1;
   }

   public static TypeId getBuiltInTypeId(int var0) {
      switch (var0) {
         case -7:
         case 16:
            return BOOLEAN_ID;
         case -6:
            return TINYINT_ID;
         case -5:
            return BIGINT_ID;
         case -4:
            return LONGVARBIT_ID;
         case -3:
            return VARBIT_ID;
         case -2:
            return BIT_ID;
         case -1:
            return LONGVARCHAR_ID;
         case 1:
            return CHAR_ID;
         case 2:
            return NUMERIC_ID;
         case 3:
            return DECIMAL_ID;
         case 4:
            return INTEGER_ID;
         case 5:
            return SMALLINT_ID;
         case 6:
         case 8:
            return DOUBLE_ID;
         case 7:
            return REAL_ID;
         case 12:
            return VARCHAR_ID;
         case 91:
            return DATE_ID;
         case 92:
            return TIME_ID;
         case 93:
            return TIMESTAMP_ID;
         case 2004:
            return BLOB_ID;
         case 2005:
            return CLOB_ID;
         case 2009:
            return XML_ID;
         default:
            return null;
      }
   }

   public static TypeId getUserDefinedTypeId(String var0) throws StandardException {
      return new TypeId(267, new UserDefinedTypeIdImpl(var0));
   }

   public static TypeId getUserDefinedTypeId(String var0, String var1, String var2) throws StandardException {
      return new TypeId(267, new UserDefinedTypeIdImpl(var0, var1, var2));
   }

   public static TypeId getSQLTypeForJavaType(String var0) throws StandardException {
      if (!var0.equals("java.lang.Boolean") && !var0.equals("boolean")) {
         if (var0.equals("byte[]")) {
            return VARBIT_ID;
         } else if (var0.equals("java.lang.String")) {
            return VARCHAR_ID;
         } else if (!var0.equals("java.lang.Integer") && !var0.equals("int")) {
            if (var0.equals("byte")) {
               return TINYINT_ID;
            } else if (var0.equals("short")) {
               return SMALLINT_ID;
            } else if (!var0.equals("java.lang.Long") && !var0.equals("long")) {
               if (!var0.equals("java.lang.Float") && !var0.equals("float")) {
                  if (!var0.equals("java.lang.Double") && !var0.equals("double")) {
                     if (var0.equals("java.math.BigDecimal")) {
                        return DECIMAL_ID;
                     } else if (var0.equals("java.sql.Date")) {
                        return DATE_ID;
                     } else if (var0.equals("java.sql.Time")) {
                        return TIME_ID;
                     } else if (var0.equals("java.sql.Timestamp")) {
                        return TIMESTAMP_ID;
                     } else if (var0.equals("java.sql.Blob")) {
                        return BLOB_ID;
                     } else if (var0.equals("java.sql.Clob")) {
                        return CLOB_ID;
                     } else if (var0.equals("org.apache.derby.iapi.types.XML")) {
                        return XML_ID;
                     } else {
                        return var0.equals("char") ? null : getUserDefinedTypeId(var0);
                     }
                  } else {
                     return DOUBLE_ID;
                  }
               } else {
                  return REAL_ID;
               }
            } else {
               return BIGINT_ID;
            }
         } else {
            return INTEGER_ID;
         }
      } else {
         return BOOLEAN_ID;
      }
   }

   public static TypeId getBuiltInTypeId(String var0) {
      if (var0.equals("BOOLEAN")) {
         return BOOLEAN_ID;
      } else if (var0.equals("CHAR")) {
         return CHAR_ID;
      } else if (var0.equals("DATE")) {
         return DATE_ID;
      } else if (var0.equals("DOUBLE")) {
         return DOUBLE_ID;
      } else if (var0.equals("FLOAT")) {
         return DOUBLE_ID;
      } else if (var0.equals("INTEGER")) {
         return INTEGER_ID;
      } else if (var0.equals("BIGINT")) {
         return BIGINT_ID;
      } else if (var0.equals("REAL")) {
         return REAL_ID;
      } else if (var0.equals("SMALLINT")) {
         return SMALLINT_ID;
      } else if (var0.equals("TIME")) {
         return TIME_ID;
      } else if (var0.equals("TIMESTAMP")) {
         return TIMESTAMP_ID;
      } else if (var0.equals("VARCHAR")) {
         return VARCHAR_ID;
      } else if (var0.equals("CHAR () FOR BIT DATA")) {
         return BIT_ID;
      } else if (var0.equals("VARCHAR () FOR BIT DATA")) {
         return VARBIT_ID;
      } else if (var0.equals("TINYINT")) {
         return TINYINT_ID;
      } else if (var0.equals("DECIMAL")) {
         return DECIMAL_ID;
      } else if (var0.equals("NUMERIC")) {
         return NUMERIC_ID;
      } else if (var0.equals("LONG VARCHAR")) {
         return LONGVARCHAR_ID;
      } else if (var0.equals("LONG VARCHAR FOR BIT DATA")) {
         return LONGVARBIT_ID;
      } else if (var0.equals("BLOB")) {
         return BLOB_ID;
      } else if (var0.equals("CLOB")) {
         return CLOB_ID;
      } else if (var0.equals("XML")) {
         return XML_ID;
      } else {
         return var0.equals("REF") ? REF_ID : null;
      }
   }

   public static TypeId getTypeId(TypeDescriptor var0) {
      TypeDescriptorImpl var1 = (TypeDescriptorImpl)var0;
      int var2 = var0.getJDBCTypeId();
      TypeId var3 = getBuiltInTypeId(var2);
      if (var3 != null) {
         return var3;
      } else if (var2 == 2000) {
         return new TypeId(267, var1.getTypeId());
      } else {
         return var1.isRowMultiSet() ? new TypeId(469, var1.getTypeId()) : null;
      }
   }

   public TypeId(int var1, BaseTypeIdImpl var2) {
      this.formatId = var1;
      this.baseTypeId = var2;
      this.setTypeIdSpecificInstanceVariables();
   }

   public boolean equals(Object var1) {
      return var1 instanceof TypeId ? this.getSQLTypeName().equals(((TypeId)var1).getSQLTypeName()) : false;
   }

   public int hashCode() {
      return this.getSQLTypeName().hashCode();
   }

   private void setTypeIdSpecificInstanceVariables() {
      switch (this.formatId) {
         case 4:
            this.maxPrecision = 1;
            this.typePrecedence = 130;
            this.javaTypeName = "java.lang.Boolean";
            this.maxMaxWidth = 1;
            this.isBooleanTypeId = true;
            break;
         case 5:
            this.typePrecedence = 0;
            this.javaTypeName = "java.lang.String";
            this.maxMaxWidth = 254;
            this.isStringTypeId = true;
            this.isConcatableTypeId = true;
            break;
         case 6:
            this.maxPrecision = 52;
            this.maxScale = 0;
            this.typePrecedence = 90;
            this.javaTypeName = "java.lang.Double";
            this.maxMaxWidth = 8;
            this.isNumericTypeId = true;
            this.isFloatingPointTypeId = true;
            break;
         case 7:
            this.maxPrecision = 10;
            this.maxScale = 0;
            this.typePrecedence = 50;
            this.javaTypeName = "java.lang.Integer";
            this.maxMaxWidth = 4;
            this.isNumericTypeId = true;
            break;
         case 8:
            this.maxPrecision = 23;
            this.maxScale = 0;
            this.typePrecedence = 80;
            this.javaTypeName = "java.lang.Float";
            this.maxMaxWidth = 4;
            this.isNumericTypeId = true;
            this.isRealTypeId = true;
            this.isFloatingPointTypeId = true;
            break;
         case 9:
            this.typePrecedence = 25;
            this.javaTypeName = "java.sql.Ref";
            this.isRefTypeId = true;
            break;
         case 10:
            this.maxPrecision = 5;
            this.maxScale = 0;
            this.typePrecedence = 40;
            this.javaTypeName = "java.lang.Integer";
            this.maxMaxWidth = 2;
            this.isNumericTypeId = true;
            break;
         case 11:
            this.maxPrecision = 19;
            this.maxScale = 0;
            this.typePrecedence = 60;
            this.javaTypeName = "java.lang.Long";
            this.maxMaxWidth = 8;
            this.isNumericTypeId = true;
            break;
         case 13:
            this.typePrecedence = 10;
            this.javaTypeName = "java.lang.String";
            this.maxMaxWidth = 32672;
            this.isStringTypeId = true;
            this.isConcatableTypeId = true;
            break;
         case 27:
            this.typePrecedence = 140;
            this.javaTypeName = "byte[]";
            this.maxMaxWidth = 254;
            this.isBitTypeId = true;
            this.isConcatableTypeId = true;
            break;
         case 29:
            this.typePrecedence = 150;
            this.javaTypeName = "byte[]";
            this.maxMaxWidth = 32672;
            this.isBitTypeId = true;
            this.isConcatableTypeId = true;
            break;
         case 35:
            this.typePrecedence = 120;
            this.javaTypeName = "java.sql.Time";
            this.maxScale = 0;
            this.maxMaxWidth = 8;
            this.maxPrecision = 8;
            this.isDateTimeTimeStampTypeId = true;
            break;
         case 36:
            this.typePrecedence = 110;
            this.javaTypeName = "java.sql.Timestamp";
            this.maxScale = 9;
            this.maxMaxWidth = 29;
            this.maxPrecision = 29;
            this.isDateTimeTimeStampTypeId = true;
            break;
         case 40:
            this.typePrecedence = 100;
            this.javaTypeName = "java.sql.Date";
            this.maxMaxWidth = 10;
            this.maxPrecision = 10;
            this.isDateTimeTimeStampTypeId = true;
            break;
         case 195:
            this.maxPrecision = 3;
            this.maxScale = 0;
            this.typePrecedence = 30;
            this.javaTypeName = "java.lang.Integer";
            this.maxMaxWidth = 1;
            this.isNumericTypeId = true;
            break;
         case 197:
            this.maxPrecision = 31;
            this.maxScale = 31;
            this.typePrecedence = 70;
            this.javaTypeName = "java.math.BigDecimal";
            this.maxMaxWidth = 31;
            this.isDecimalTypeId = true;
            this.isNumericTypeId = true;
            break;
         case 230:
            this.typePrecedence = 12;
            this.javaTypeName = "java.lang.String";
            this.maxMaxWidth = 32700;
            this.isStringTypeId = true;
            this.isConcatableTypeId = true;
            this.isLongConcatableTypeId = true;
            break;
         case 232:
            this.typePrecedence = 160;
            this.javaTypeName = "byte[]";
            this.maxMaxWidth = 32700;
            this.isBitTypeId = true;
            this.isConcatableTypeId = true;
            this.isLongConcatableTypeId = true;
            break;
         case 267:
            if (this.baseTypeId != null) {
               this.setUserTypeIdInfo();
            } else {
               this.typePrecedence = 1000;
            }

            this.maxMaxWidth = -1;
            this.isUserDefinedTypeId = true;
            break;
         case 440:
            this.typePrecedence = 170;
            this.javaTypeName = "java.sql.Blob";
            this.maxMaxWidth = Integer.MAX_VALUE;
            this.isBitTypeId = true;
            this.isConcatableTypeId = true;
            this.isLongConcatableTypeId = true;
            this.isLOBTypeId = true;
            break;
         case 444:
            this.typePrecedence = 14;
            this.javaTypeName = "java.sql.Clob";
            this.maxMaxWidth = Integer.MAX_VALUE;
            this.isStringTypeId = true;
            this.isConcatableTypeId = true;
            this.isLongConcatableTypeId = true;
            this.isLOBTypeId = true;
            break;
         case 456:
            this.typePrecedence = 180;
            this.javaTypeName = "org.apache.derby.iapi.types.XML";
            this.maxMaxWidth = Integer.MAX_VALUE;
            this.isLongConcatableTypeId = true;
      }

   }

   public final int getJDBCTypeId() {
      return this.baseTypeId.getJDBCTypeId();
   }

   public String getSQLTypeName() {
      return this.baseTypeId.getSQLTypeName();
   }

   public final boolean userType() {
      return this.baseTypeId.userType();
   }

   public int getMaximumPrecision() {
      return this.maxPrecision;
   }

   public int getMaximumScale() {
      return this.maxScale;
   }

   private void setUserTypeIdInfo() {
      UserDefinedTypeIdImpl var1 = (UserDefinedTypeIdImpl)this.baseTypeId;
      this.typePrecedence = 1000;
      this.javaTypeName = var1.getClassName();
   }

   public boolean isStringTypeId() {
      return this.isStringTypeId;
   }

   public boolean isDateTimeTimeStampTypeId() {
      return this.isDateTimeTimeStampTypeId;
   }

   public boolean isRealTypeId() {
      return this.isRealTypeId;
   }

   public boolean isFloatingPointTypeId() {
      return this.isFloatingPointTypeId;
   }

   public boolean isDoubleTypeId() {
      return this.isFloatingPointTypeId && !this.isRealTypeId;
   }

   public boolean isFixedStringTypeId() {
      return this.formatId == 5;
   }

   public boolean isClobTypeId() {
      return this.formatId == 444;
   }

   public boolean isBlobTypeId() {
      return this.formatId == 440;
   }

   public boolean isLongVarcharTypeId() {
      return this.formatId == 230;
   }

   public boolean isLongVarbinaryTypeId() {
      return this.formatId == 232;
   }

   public boolean isDateTimeTimeStampTypeID() {
      return this.formatId == 40 || this.formatId == 35 || this.formatId == 36;
   }

   public boolean isTimestampId() {
      return this.formatId == 36;
   }

   public boolean isXMLTypeId() {
      return this.formatId == 456;
   }

   public boolean orderable(ClassFactory var1) {
      switch (this.formatId) {
         case 230:
         case 232:
         case 440:
         case 444:
         case 456:
            return false;
         case 267:
            return false;
         default:
            boolean var2 = true;
            return var2;
      }
   }

   public int typePrecedence() {
      return this.typePrecedence;
   }

   public String getCorrespondingJavaTypeName() {
      return this.javaTypeName;
   }

   public String getResultSetMetaDataTypeName() {
      if (BLOB_ID != null && BLOB_ID.equals(this)) {
         return "java.sql.Blob";
      } else {
         return CLOB_ID != null && CLOB_ID.equals(this) ? "java.sql.Clob" : this.getCorrespondingJavaTypeName();
      }
   }

   public int getMaximumMaximumWidth() {
      return this.maxMaxWidth;
   }

   public String toParsableString(DataTypeDescriptor var1) {
      return this.baseTypeId.toParsableString(var1.getCatalogType());
   }

   public boolean isNumericTypeId() {
      return this.isNumericTypeId;
   }

   public boolean isDecimalTypeId() {
      return this.isDecimalTypeId;
   }

   public boolean isBooleanTypeId() {
      return this.isBooleanTypeId;
   }

   public boolean isRefTypeId() {
      return this.isRefTypeId;
   }

   public boolean isConcatableTypeId() {
      return this.isConcatableTypeId;
   }

   public boolean isBitTypeId() {
      return this.isBitTypeId;
   }

   public boolean isLOBTypeId() {
      return this.isLOBTypeId;
   }

   public boolean isLongConcatableTypeId() {
      return this.isLongConcatableTypeId;
   }

   public boolean isUserDefinedTypeId() {
      return this.isUserDefinedTypeId;
   }

   public int getTypeFormatId() {
      return this.formatId;
   }

   public DataValueDescriptor getNull() {
      switch (this.formatId) {
         case 4 -> {
            return new SQLBoolean();
         }
         case 5 -> {
            return new SQLChar();
         }
         case 6 -> {
            return new SQLDouble();
         }
         case 7 -> {
            return new SQLInteger();
         }
         case 8 -> {
            return new SQLReal();
         }
         case 9 -> {
            return new SQLRef();
         }
         case 10 -> {
            return new SQLSmallint();
         }
         case 11 -> {
            return new SQLLongint();
         }
         case 13 -> {
            return new SQLVarchar();
         }
         case 27 -> {
            return new SQLBit();
         }
         case 29 -> {
            return new SQLVarbit();
         }
         case 35 -> {
            return new SQLTime();
         }
         case 36 -> {
            return new SQLTimestamp();
         }
         case 40 -> {
            return new SQLDate();
         }
         case 195 -> {
            return new SQLTinyint();
         }
         case 197 -> {
            return new SQLDecimal();
         }
         case 230 -> {
            return new SQLLongvarchar();
         }
         case 232 -> {
            return new SQLLongVarbit();
         }
         case 267 -> {
            return new UserType();
         }
         case 440 -> {
            return new SQLBlob();
         }
         case 444 -> {
            return new SQLClob();
         }
         case 456 -> {
            return new XML();
         }
         default -> {
            return null;
         }
      }
   }

   public boolean streamStorable() {
      return this.isStringTypeId() || this.isBitTypeId();
   }

   public int getApproximateLengthInBytes(DataTypeDescriptor var1) {
      switch (this.formatId) {
         case 5:
            return 2 * var1.getMaximumWidth() + 2;
         case 9:
            return 16;
         case 13:
         case 230:
            if (var1.getMaximumWidth() == Integer.MAX_VALUE) {
               return 200;
            }

            return var1.getMaximumWidth() * 2 + 2;
         case 27:
            return (int)Math.ceil((double)var1.getMaximumWidth() / (double)8.0F);
         case 29:
            if (var1.getMaximumWidth() == Integer.MAX_VALUE) {
               return 200;
            }

            return (int)Math.ceil((double)var1.getMaximumWidth() / (double)8.0F);
         case 35:
            return 16;
         case 36:
            return 29;
         case 40:
            return 18;
         case 197:
            if (var1.getPrecision() == Integer.MAX_VALUE) {
               return 200;
            }

            return 8 + (int)Math.ceil((double)var1.getPrecision() / (double)2.0F);
         case 232:
         case 440:
         case 444:
         case 456:
            return 10240;
         case 267:
            return 200;
         default:
            return var1.getMaximumWidth();
      }
   }

   public BaseTypeIdImpl getBaseTypeId() {
      return this.baseTypeId;
   }

   public int getPrecision(DataTypeDescriptor var1, DataTypeDescriptor var2) {
      long var3 = (long)var1.getScale();
      long var5 = (long)var2.getScale();
      long var7 = (long)var1.getPrecision();
      long var9 = (long)var2.getPrecision();
      long var11 = (long)this.getScale(var1, var2) + Math.max(var7 - var3, var9 - var5);
      if (var11 > 2147483647L) {
         var11 = 2147483647L;
      }

      return (int)var11;
   }

   public int getScale(DataTypeDescriptor var1, DataTypeDescriptor var2) {
      return Math.max(var1.getScale(), var2.getScale());
   }

   public boolean variableLength() {
      switch (this.formatId) {
         case 5:
         case 13:
         case 27:
         case 29:
         case 197:
         case 440:
         case 444:
            return true;
         default:
            return false;
      }
   }

   static {
      ALL_BUILTIN_TYPE_IDS = new TypeId[]{BOOLEAN_ID, SMALLINT_ID, INTEGER_ID, CHAR_ID, TINYINT_ID, BIGINT_ID, REAL_ID, DOUBLE_ID, DECIMAL_ID, NUMERIC_ID, VARCHAR_ID, DATE_ID, TIME_ID, TIMESTAMP_ID, BIT_ID, VARBIT_ID, REF_ID, LONGVARCHAR_ID, LONGVARBIT_ID, BLOB_ID, CLOB_ID, XML_ID};
   }
}
