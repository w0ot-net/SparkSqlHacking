package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.SQLException;
import java.text.RuleBasedCollator;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.types.RowMultiSetImpl;
import org.apache.derby.catalog.types.TypeDescriptorImpl;
import org.apache.derby.catalog.types.UserDefinedTypeIdImpl;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.shared.common.error.StandardException;

public final class DataTypeDescriptor implements Formatable {
   public static final DataTypeDescriptor INTEGER;
   public static final DataTypeDescriptor INTEGER_NOT_NULL;
   public static final DataTypeDescriptor SMALLINT;
   public static final DataTypeDescriptor SMALLINT_NOT_NULL;
   public static final DataTypeDescriptor DOUBLE;
   public static final int MIN_VALUE_IDX = 0;
   public static final int MAX_VALUE_IDX = 1;
   public static final int MAX_MIN_ARRAY_SIZE = 2;
   private TypeDescriptorImpl typeDescriptor;
   private TypeId typeId;
   private int collationDerivation = 1;

   public static DataTypeDescriptor getBuiltInDataTypeDescriptor(int var0) {
      return getBuiltInDataTypeDescriptor(var0, true);
   }

   public static DataTypeDescriptor getBuiltInDataTypeDescriptor(int var0, int var1) {
      return getBuiltInDataTypeDescriptor(var0, true, var1);
   }

   public static DataTypeDescriptor getType(TypeDescriptor var0) {
      TypeDescriptorImpl var1 = (TypeDescriptorImpl)var0;
      TypeId var2 = TypeId.getTypeId(var0);
      DataTypeDescriptor var3 = new DataTypeDescriptor(var1, var2);
      var3.collationDerivation = 1;
      return var3;
   }

   public static TypeDescriptor getCatalogType(int var0, int var1) {
      return getBuiltInDataTypeDescriptor(var0, var1).getCatalogType();
   }

   public static TypeDescriptor getCatalogType(int var0) {
      return getBuiltInDataTypeDescriptor(var0).getCatalogType();
   }

   public static TypeDescriptor getCatalogType(TypeDescriptor var0, int var1) {
      if (var0.isRowMultiSet()) {
         return getRowMultiSetCollation(var0, var1);
      } else {
         return var0.getCollationType() == var1 ? var0 : getType(var0).getCollatedType(var1, 1).getCatalogType();
      }
   }

   public static DataTypeDescriptor getBuiltInDataTypeDescriptor(int var0, boolean var1) {
      switch (var0) {
         case 4:
            return var1 ? INTEGER : INTEGER_NOT_NULL;
         case 5:
            return var1 ? SMALLINT : SMALLINT_NOT_NULL;
         default:
            TypeId var2 = TypeId.getBuiltInTypeId(var0);
            return var2 == null ? null : new DataTypeDescriptor(var2, var1);
      }
   }

   public static DataTypeDescriptor getBuiltInDataTypeDescriptor(int var0, boolean var1, int var2) {
      TypeId var3 = TypeId.getBuiltInTypeId(var0);
      return var3 == null ? null : new DataTypeDescriptor(var3, var1, var2);
   }

   public static DataTypeDescriptor getBuiltInDataTypeDescriptor(String var0) {
      return new DataTypeDescriptor(TypeId.getBuiltInTypeId(var0), true);
   }

   public static DataTypeDescriptor getBuiltInDataTypeDescriptor(String var0, int var1) {
      return new DataTypeDescriptor(TypeId.getBuiltInTypeId(var0), true, var1);
   }

   public static DataTypeDescriptor getSQLDataTypeDescriptor(String var0) throws StandardException {
      return getSQLDataTypeDescriptor(var0, true);
   }

   public static DataTypeDescriptor getSQLDataTypeDescriptor(String var0, boolean var1) throws StandardException {
      TypeId var2 = TypeId.getSQLTypeForJavaType(var0);
      return var2 == null ? null : new DataTypeDescriptor(var2, var1);
   }

   public static DataTypeDescriptor getSQLDataTypeDescriptor(String var0, int var1, int var2, boolean var3, int var4) throws StandardException {
      TypeId var5 = TypeId.getSQLTypeForJavaType(var0);
      return var5 == null ? null : new DataTypeDescriptor(var5, var1, var2, var3, var4);
   }

   public static TypeDescriptor getRowMultiSet(String[] var0, TypeDescriptor[] var1) {
      RowMultiSetImpl var2 = new RowMultiSetImpl(var0, var1);
      return new TypeDescriptorImpl(var2, true, -1);
   }

   public DataTypeDescriptor() {
   }

   public DataTypeDescriptor(TypeId var1, int var2, int var3, boolean var4, int var5) {
      this.typeId = var1;
      this.typeDescriptor = new TypeDescriptorImpl(var1.getBaseTypeId(), var2, var3, var4, var5);
   }

   public DataTypeDescriptor(TypeId var1, int var2, int var3, boolean var4, int var5, int var6, int var7) {
      this.typeId = var1;
      this.typeDescriptor = new TypeDescriptorImpl(var1.getBaseTypeId(), var2, var3, var4, var5, var6);
      this.collationDerivation = var7;
   }

   public DataTypeDescriptor(TypeId var1, boolean var2, int var3) {
      this.typeId = var1;
      this.typeDescriptor = new TypeDescriptorImpl(var1.getBaseTypeId(), var2, var3);
   }

   public DataTypeDescriptor(TypeId var1, boolean var2) {
      this.typeId = var1;
      this.typeDescriptor = new TypeDescriptorImpl(var1.getBaseTypeId(), var1.getMaximumPrecision(), var1.getMaximumScale(), var2, var1.getMaximumMaximumWidth());
   }

   private DataTypeDescriptor(DataTypeDescriptor var1, boolean var2) {
      this.typeId = var1.typeId;
      this.typeDescriptor = new TypeDescriptorImpl(var1.typeDescriptor, var1.getPrecision(), var1.getScale(), var2, var1.getMaximumWidth(), var1.getCollationType());
      this.collationDerivation = var1.getCollationDerivation();
   }

   private DataTypeDescriptor(DataTypeDescriptor var1, int var2, int var3) {
      this.typeId = var1.typeId;
      this.typeDescriptor = new TypeDescriptorImpl(var1.typeDescriptor, var1.getPrecision(), var1.getScale(), var1.isNullable(), var1.getMaximumWidth(), var2);
      this.collationDerivation = var3;
   }

   public DataTypeDescriptor(DataTypeDescriptor var1, int var2, int var3, boolean var4, int var5) {
      this.typeId = var1.typeId;
      this.typeDescriptor = new TypeDescriptorImpl(var1.typeDescriptor, var2, var3, var4, var5, var1.getCollationType());
      this.collationDerivation = var1.getCollationDerivation();
   }

   public DataTypeDescriptor(DataTypeDescriptor var1, boolean var2, int var3) {
      this.typeId = var1.typeId;
      this.typeDescriptor = new TypeDescriptorImpl(var1.typeDescriptor, var1.getPrecision(), var1.getScale(), var2, var3, var1.getCollationType());
      this.collationDerivation = var1.getCollationDerivation();
   }

   private DataTypeDescriptor(TypeDescriptorImpl var1, TypeId var2) {
      this.typeDescriptor = var1;
      this.typeId = var2;
   }

   public DataValueDescriptor normalize(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (var1.isNull()) {
         if (!this.isNullable()) {
            throw StandardException.newException("23502", new Object[]{""});
         }

         var2.setToNull();
      } else {
         int var3 = this.getJDBCTypeId();
         var2.normalize(this, var1);
         if ((var3 == -1 || var3 == -4) && var1.getClass() == var2.getClass()) {
            return var1;
         }
      }

      return var2;
   }

   public DataTypeDescriptor getDominantType(DataTypeDescriptor var1, ClassFactory var2) {
      DataTypeDescriptor var7 = null;
      int var9 = this.getPrecision();
      int var10 = this.getScale();
      TypeId var4 = this.getTypeId();
      TypeId var5 = var1.getTypeId();
      boolean var3 = this.isNullable() || var1.isNullable();
      int var8 = this.getMaximumWidth() > var1.getMaximumWidth() ? this.getMaximumWidth() : var1.getMaximumWidth();
      DataTypeDescriptor var6;
      if (!var4.userType() && !var5.userType()) {
         TypeId var12;
         TypeId var17;
         if (var4.typePrecedence() > var5.typePrecedence()) {
            var6 = this;
            var7 = var1;
            var17 = var4;
            var12 = var5;
         } else {
            var6 = var1;
            var7 = this;
            var17 = var5;
            var12 = var4;
         }

         if (var17.isRealTypeId() && !var12.isRealTypeId() && var12.isNumericTypeId()) {
            var6 = getBuiltInDataTypeDescriptor(8);
            var17 = TypeId.getBuiltInTypeId(8);
         }

         if (var17.isDecimalTypeId() && !var12.isStringTypeId()) {
            var9 = var17.getPrecision(this, var1);
            if (var9 > 31) {
               var9 = 31;
            }

            var10 = var17.getScale(this, var1);
            var8 = var10 > 0 ? var9 + 3 : var9 + 1;
         } else if (var4.typePrecedence() != var5.typePrecedence()) {
            var9 = var6.getPrecision();
            var10 = var6.getScale();
            if (var12.isStringTypeId() && var17.isBitTypeId() && !var17.isLongConcatableTypeId()) {
               if (var12.isLongConcatableTypeId()) {
                  if (var8 > 134217727) {
                     var8 = Integer.MAX_VALUE;
                  } else {
                     var8 *= 16;
                  }
               } else {
                  int var14 = var7.getMaximumWidth();
                  int var13;
                  if (var14 > 134217727) {
                     var13 = Integer.MAX_VALUE;
                  } else {
                     var13 = 16 * var14;
                  }

                  var8 = var8 >= var13 ? var8 : var13;
               }
            }

            if (var12.isStringTypeId() && !var12.isLongConcatableTypeId() && var17.isDecimalTypeId()) {
               int var18 = var7.getMaximumWidth();
               int var19;
               if (var18 > 1073741822) {
                  var19 = 2147483644;
               } else {
                  var19 = var18 * 2;
               }

               if (var9 < var19) {
                  var9 = var19;
               }

               if (var10 < var18) {
                  var10 = var18;
               }

               var8 = var9 + 3;
            }
         }
      } else {
         ClassInspector var11 = var2.getClassInspector();
         if (var11.assignableTo(var4.getCorrespondingJavaTypeName(), var5.getCorrespondingJavaTypeName())) {
            var6 = var1;
         } else {
            var6 = this;
         }

         var9 = var6.getPrecision();
         var10 = var6.getScale();
      }

      var6 = new DataTypeDescriptor(var6, var9, var10, var3, var8);
      if (var6.getTypeId().isStringTypeId()) {
         if (this.getCollationDerivation() != var1.getCollationDerivation()) {
            if (this.getCollationDerivation() == 0) {
               var6 = var6.getCollatedType(var1.getCollationType(), var1.getCollationDerivation());
            } else if (var1.getCollationDerivation() == 0) {
               var6 = var6.getCollatedType(this.getCollationType(), this.getCollationDerivation());
            } else {
               var6 = var6.getCollatedType(0, 0);
            }
         } else if (this.getCollationType() != var1.getCollationType()) {
            var6 = var6.getCollatedType(0, 0);
         } else {
            var6 = var6.getCollatedType(this.getCollationType(), this.getCollationDerivation());
         }
      }

      return var6;
   }

   public boolean isExactTypeAndLengthMatch(DataTypeDescriptor var1) {
      if (this.getMaximumWidth() != var1.getMaximumWidth()) {
         return false;
      } else if (this.getScale() != var1.getScale()) {
         return false;
      } else if (this.getPrecision() != var1.getPrecision()) {
         return false;
      } else {
         TypeId var2 = this.getTypeId();
         TypeId var3 = var1.getTypeId();
         return var2.equals(var3);
      }
   }

   public int getMaximumWidth() {
      return this.typeDescriptor.getMaximumWidth();
   }

   public TypeId getTypeId() {
      return this.typeId;
   }

   public DataValueDescriptor getNull() throws StandardException {
      DataValueDescriptor var1 = this.typeId.getNull();
      if (this.typeDescriptor.getCollationType() == 0) {
         return var1;
      } else if (var1 instanceof StringDataValue) {
         try {
            RuleBasedCollator var2 = ConnectionUtil.getCurrentLCC().getDataValueFactory().getCharacterCollator(this.typeDescriptor.getCollationType());
            return ((StringDataValue)var1).getValue(var2);
         } catch (SQLException var3) {
            throw StandardException.plainWrapException(var3);
         }
      } else {
         return var1;
      }
   }

   public String getTypeName() {
      return this.typeDescriptor.getTypeName();
   }

   public int getJDBCTypeId() {
      return this.typeDescriptor.getJDBCTypeId();
   }

   public int getPrecision() {
      return this.typeDescriptor.getPrecision();
   }

   public int getScale() {
      return this.typeDescriptor.getScale();
   }

   public int getCollationType() {
      return this.typeDescriptor.getCollationType();
   }

   public static int getCollationType(String var0) {
      if (var0.equalsIgnoreCase("UCS_BASIC")) {
         return 0;
      } else if (var0.equalsIgnoreCase("TERRITORY_BASED")) {
         return 1;
      } else if (var0.equalsIgnoreCase("TERRITORY_BASED:PRIMARY")) {
         return 2;
      } else if (var0.equalsIgnoreCase("TERRITORY_BASED:SECONDARY")) {
         return 3;
      } else if (var0.equalsIgnoreCase("TERRITORY_BASED:TERTIARY")) {
         return 4;
      } else {
         return var0.equalsIgnoreCase("TERRITORY_BASED:IDENTICAL") ? 5 : -1;
      }
   }

   public String getCollationName() {
      return this.getCollationDerivation() == 0 ? "NONE" : getCollationName(this.getCollationType());
   }

   public static String getCollationName(int var0) {
      switch (var0) {
         case 1 -> {
            return "TERRITORY_BASED";
         }
         case 2 -> {
            return "TERRITORY_BASED:PRIMARY";
         }
         case 3 -> {
            return "TERRITORY_BASED:SECONDARY";
         }
         case 4 -> {
            return "TERRITORY_BASED:TERTIARY";
         }
         case 5 -> {
            return "TERRITORY_BASED:IDENTICAL";
         }
         default -> {
            return "UCS_BASIC";
         }
      }
   }

   public int getCollationDerivation() {
      return this.collationDerivation;
   }

   public boolean isNullable() {
      return this.typeDescriptor.isNullable();
   }

   public DataTypeDescriptor getNullabilityType(boolean var1) {
      return this.isNullable() == var1 ? this : new DataTypeDescriptor(this, var1);
   }

   public DataTypeDescriptor getCollatedType(int var1, int var2) {
      if (!this.typeDescriptor.isStringType()) {
         return this;
      } else {
         return this.getCollationType() == var1 && this.getCollationDerivation() == var2 ? this : new DataTypeDescriptor(this, var1, var2);
      }
   }

   private static TypeDescriptor getRowMultiSetCollation(TypeDescriptor var0, int var1) {
      TypeDescriptor[] var2 = var0.getRowTypes();
      TypeDescriptor[] var3 = null;

      for(int var4 = 0; var4 < var2.length; ++var4) {
         TypeDescriptor var5 = getCatalogType(var2[var4], var1);
         if (var5 != var2[var4]) {
            if (var3 == null) {
               var3 = new TypeDescriptor[var2.length];
               System.arraycopy(var2, 0, var3, 0, var2.length);
            }

            var3[var4] = var5;
         }
      }

      if (var3 == null) {
         return var0;
      } else {
         return getRowMultiSet(var0.getRowColumnNames(), var3);
      }
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof DataTypeDescriptor var2)) {
         return false;
      } else {
         return this.typeDescriptor.equals(var2.typeDescriptor) && this.collationDerivation == var2.collationDerivation;
      }
   }

   public boolean comparable(DataTypeDescriptor var1, boolean var2, ClassFactory var3) {
      TypeId var4 = var1.getTypeId();
      int var5 = var4.getJDBCTypeId();
      if (!var4.isLongConcatableTypeId() && !this.typeId.isLongConcatableTypeId()) {
         if (!this.typeId.isRefTypeId() && !var4.isRefTypeId()) {
            if (!this.typeId.isUserDefinedTypeId() && var4.isUserDefinedTypeId()) {
               return var1.comparable(this, var2, var3);
            } else if (this.typeId.isNumericTypeId()) {
               return var4.isNumericTypeId();
            } else if (this.typeId.isStringTypeId()) {
               if (!var4.isDateTimeTimeStampTypeID() && !var4.isBooleanTypeId()) {
                  return var4.isStringTypeId() && this.typeId.isStringTypeId() ? this.compareCollationInfo(var1) : false;
               } else {
                  return true;
               }
            } else if (this.typeId.isBitTypeId()) {
               return var4.isBitTypeId();
            } else if (!this.typeId.isBooleanTypeId()) {
               if (this.typeId.getJDBCTypeId() == 91) {
                  return var5 == 91 || var4.isStringTypeId();
               } else if (this.typeId.getJDBCTypeId() == 92) {
                  return var5 == 92 || var4.isStringTypeId();
               } else if (this.typeId.getJDBCTypeId() == 93) {
                  return var5 == 93 || var4.isStringTypeId();
               } else {
                  return !this.typeId.isUserDefinedTypeId() && this.typeId.getJDBCTypeId() != 1111 ? false : false;
               }
            } else {
               return var4.getSQLTypeName().equals(this.typeId.getSQLTypeName()) || var4.isStringTypeId();
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean compareCollationInfo(DataTypeDescriptor var1) {
      if (this.getCollationDerivation() == var1.getCollationDerivation() && this.getCollationDerivation() == 0) {
         return false;
      } else {
         return this.getCollationDerivation() == var1.getCollationDerivation() && this.getCollationType() == var1.getCollationType();
      }
   }

   public String getSQLstring() {
      return this.typeId.toParsableString(this);
   }

   public TypeDescriptor getCatalogType() {
      return this.typeDescriptor;
   }

   public double estimatedMemoryUsage() {
      switch (this.typeId.getTypeFormatId()) {
         case 4:
            return (double)4.0F;
         case 5:
         case 13:
            return (double)2.0F * (double)this.getMaximumWidth();
         case 6:
            return (double)8.0F;
         case 7:
            return (double)4.0F;
         case 8:
            return (double)4.0F;
         case 9:
            return (double)12.0F;
         case 10:
            return (double)2.0F;
         case 11:
            return (double)8.0F;
         case 27:
            return (double)((float)this.getMaximumWidth()) / (double)8.0F + (double)0.5F;
         case 195:
            return (double)1.0F;
         case 197:
            return (double)this.getPrecision() * 0.415 + (double)1.5F;
         case 230:
         case 444:
            return (double)10000.0F;
         case 232:
         case 440:
            return (double)10000.0F;
         case 267:
            if (this.typeId.userType()) {
               return (double)256.0F;
            }
         case 35:
         case 36:
         case 40:
            return (double)12.0F;
         default:
            return (double)0.0F;
      }
   }

   public static boolean isJDBCTypeEquivalent(int var0, int var1) {
      if (var0 == var1) {
         return true;
      } else if (isNumericType(var0)) {
         if (isNumericType(var1)) {
            return true;
         } else {
            return isCharacterType(var1);
         }
      } else if (isCharacterType(var0)) {
         if (isCharacterType(var1)) {
            return true;
         } else if (isNumericType(var1)) {
            return true;
         } else {
            switch (var1) {
               case 91:
               case 92:
               case 93:
                  return true;
               default:
                  return false;
            }
         }
      } else if (isBinaryType(var0)) {
         return isBinaryType(var1);
      } else if (var0 != 91 && var0 != 92) {
         if (var0 == 93) {
            if (isCharacterType(var1)) {
               return true;
            } else {
               return var1 == 91;
            }
         } else {
            return var0 == 2005 && isCharacterType(var1);
         }
      } else if (isCharacterType(var1)) {
         return true;
      } else {
         return var1 == 93;
      }
   }

   public static boolean isNumericType(int var0) {
      switch (var0) {
         case -7:
         case -6:
         case -5:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 16:
            return true;
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 1:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         default:
            return false;
      }
   }

   private static boolean isCharacterType(int var0) {
      switch (var0) {
         case -1:
         case 1:
         case 12:
            return true;
         default:
            return false;
      }
   }

   private static boolean isBinaryType(int var0) {
      switch (var0) {
         case -4:
         case -3:
         case -2:
            return true;
         default:
            return false;
      }
   }

   public static boolean isAsciiStreamAssignable(int var0) {
      return var0 == 2005 || isCharacterType(var0);
   }

   public static boolean isBinaryStreamAssignable(int var0) {
      return var0 == 2004 || isBinaryType(var0);
   }

   public static boolean isCharacterStreamAssignable(int var0) {
      return isAsciiStreamAssignable(var0);
   }

   public String toString() {
      return this.typeDescriptor.toString();
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.typeDescriptor = (TypeDescriptorImpl)var1.readObject();
      if (this.typeDescriptor.isUserDefinedType()) {
         try {
            this.typeId = TypeId.getUserDefinedTypeId(((UserDefinedTypeIdImpl)this.typeDescriptor.getTypeId()).getClassName());
         } catch (StandardException var3) {
            throw (IOException)(new IOException(var3.getMessage())).initCause(var3);
         }
      } else {
         this.typeId = TypeId.getBuiltInTypeId(this.getTypeName());
      }

      this.collationDerivation = var1.readInt();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.typeDescriptor);
      var1.writeInt(this.getCollationDerivation());
   }

   public int getTypeFormatId() {
      return 240;
   }

   public boolean isUserCreatableType() throws StandardException {
      switch (this.typeId.getJDBCTypeId()) {
         case 3 -> {
            return this.getPrecision() <= this.typeId.getMaximumPrecision() && this.getScale() <= this.typeId.getMaximumScale();
         }
         case 2000 -> {
            return this.getTypeId().getBaseTypeId().isAnsiUDT();
         }
         default -> {
            return true;
         }
      }
   }

   public String getFullSQLTypeName() {
      StringBuffer var1 = new StringBuffer(this.typeId.getSQLTypeName());
      if (!this.typeId.isDecimalTypeId() && !this.typeId.isNumericTypeId()) {
         if (this.typeId.variableLength()) {
            var1.append("(");
            var1.append(this.getMaximumWidth());
            var1.append(")");
         }
      } else {
         var1.append("(");
         var1.append(this.getPrecision());
         var1.append(", ");
         var1.append(this.getScale());
         var1.append(")");
      }

      return var1.toString();
   }

   public String getSQLTypeNameWithCollation() {
      String var1 = this.typeId.getSQLTypeName();
      if (this.typeId.isStringTypeId()) {
         var1 = var1 + " (" + this.getCollationName() + ")";
      }

      return var1;
   }

   public long[] getNumericBounds() throws StandardException {
      long[] var1 = new long[2];
      if (this.getTypeId().equals(TypeId.SMALLINT_ID)) {
         var1[0] = Long.valueOf(-32768L);
         var1[1] = Long.valueOf(32767L);
      } else if (this.getTypeId().equals(TypeId.INTEGER_ID)) {
         var1[0] = Long.valueOf(-2147483648L);
         var1[1] = Long.valueOf(2147483647L);
      } else {
         if (!this.getTypeId().equals(TypeId.BIGINT_ID)) {
            throw StandardException.newException("XSCB3.S", new Object[0]);
         }

         var1[0] = Long.MIN_VALUE;
         var1[1] = Long.MAX_VALUE;
      }

      return var1;
   }

   static {
      INTEGER = new DataTypeDescriptor(TypeId.INTEGER_ID, true);
      INTEGER_NOT_NULL = INTEGER.getNullabilityType(false);
      SMALLINT = new DataTypeDescriptor(TypeId.SMALLINT_ID, true);
      SMALLINT_NOT_NULL = SMALLINT.getNullabilityType(false);
      DOUBLE = new DataTypeDescriptor(TypeId.DOUBLE_ID, true);
   }
}
