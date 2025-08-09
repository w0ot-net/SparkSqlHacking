package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.shared.common.error.StandardException;

public abstract class DataType implements DataValueDescriptor, Comparable {
   public boolean getBoolean() throws StandardException {
      throw this.dataTypeConversion("boolean");
   }

   public byte getByte() throws StandardException {
      throw this.dataTypeConversion("byte");
   }

   public short getShort() throws StandardException {
      throw this.dataTypeConversion("short");
   }

   public int getInt() throws StandardException {
      throw this.dataTypeConversion("int");
   }

   public long getLong() throws StandardException {
      throw this.dataTypeConversion("long");
   }

   public float getFloat() throws StandardException {
      throw this.dataTypeConversion("float");
   }

   public double getDouble() throws StandardException {
      throw this.dataTypeConversion("double");
   }

   public int typeToBigDecimal() throws StandardException {
      throw this.dataTypeConversion("java.math.BigDecimal");
   }

   public byte[] getBytes() throws StandardException {
      throw this.dataTypeConversion("byte[]");
   }

   public Date getDate(Calendar var1) throws StandardException {
      throw this.dataTypeConversion("java.sql.Date");
   }

   public Time getTime(Calendar var1) throws StandardException {
      throw this.dataTypeConversion("java.sql.Time");
   }

   public Timestamp getTimestamp(Calendar var1) throws StandardException {
      throw this.dataTypeConversion("java.sql.Timestamp");
   }

   public InputStream getStream() throws StandardException {
      throw this.dataTypeConversion("InputStream");
   }

   public boolean hasStream() {
      return false;
   }

   public String getTraceString() throws StandardException {
      return this.getString();
   }

   public DataValueDescriptor recycle() {
      this.restoreToNull();
      return this;
   }

   public void readExternalFromArray(ArrayInputStream var1) throws IOException, ClassNotFoundException {
      this.readExternal(var1);
   }

   public final BooleanDataValue isNullOp() {
      return SQLBoolean.truthValue(this.isNull());
   }

   public final BooleanDataValue isNotNull() {
      return SQLBoolean.truthValue(!this.isNull());
   }

   public void setValue(Time var1) throws StandardException {
      this.setValue(var1, (Calendar)null);
   }

   public void setValue(Time var1, Calendar var2) throws StandardException {
      this.throwLangSetMismatch("java.sql.Time");
   }

   public void setValue(Timestamp var1) throws StandardException {
      this.setValue(var1, (Calendar)null);
   }

   public void setValue(Timestamp var1, Calendar var2) throws StandardException {
      this.throwLangSetMismatch("java.sql.Timestamp");
   }

   public void setValue(Date var1) throws StandardException {
      this.setValue(var1, (Calendar)null);
   }

   public void setValue(Date var1, Calendar var2) throws StandardException {
      this.throwLangSetMismatch("java.sql.Date");
   }

   public void setValue(Object var1) throws StandardException {
      this.throwLangSetMismatch("java.lang.Object");
   }

   public void setValue(String var1) throws StandardException {
      this.throwLangSetMismatch("java.lang.String");
   }

   public void setValue(Blob var1) throws StandardException {
      this.throwLangSetMismatch("java.sql.Blob");
   }

   public void setValue(Clob var1) throws StandardException {
      this.throwLangSetMismatch("java.sql.Clob");
   }

   public void setValue(int var1) throws StandardException {
      this.throwLangSetMismatch("int");
   }

   public void setValue(double var1) throws StandardException {
      this.throwLangSetMismatch("double");
   }

   public void setValue(float var1) throws StandardException {
      this.throwLangSetMismatch("float");
   }

   public void setValue(short var1) throws StandardException {
      this.throwLangSetMismatch("short");
   }

   public void setValue(long var1) throws StandardException {
      this.throwLangSetMismatch("long");
   }

   public void setValue(byte var1) throws StandardException {
      this.throwLangSetMismatch("byte");
   }

   public void setValue(boolean var1) throws StandardException {
      this.throwLangSetMismatch("boolean");
   }

   public void setValue(byte[] var1) throws StandardException {
      this.throwLangSetMismatch("byte[]");
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      this.throwLangSetMismatch("java.math.BigDecimal");
   }

   public final void setValue(DataValueDescriptor var1) throws StandardException {
      if (var1.isNull()) {
         this.setToNull();
      } else {
         try {
            this.setFrom(var1);
         } catch (StandardException var4) {
            String var3 = var4.getMessageId();
            if ("22003".equals(var3)) {
               throw this.outOfRange();
            } else if ("22018".equals(var3)) {
               throw this.invalidFormat();
            } else {
               throw var4;
            }
         }
      }
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      throw StandardException.newException("0A000.S", new Object[0]);
   }

   public void setToNull() {
      this.restoreToNull();
   }

   public void setObjectForCast(Object var1, boolean var2, String var3) throws StandardException {
      if (var1 == null) {
         this.setToNull();
      } else if (!var2) {
         throw StandardException.newException("XCL12.S", new Object[]{var1.getClass().getName(), this.getTypeName(var3)});
      } else {
         this.setObject(var1);
      }
   }

   void setObject(Object var1) throws StandardException {
      this.genericSetObject(var1);
   }

   String getTypeName(String var1) {
      return this.getTypeName();
   }

   public Object getObject() throws StandardException {
      throw this.dataTypeConversion("java.lang.Object");
   }

   void genericSetObject(Object var1) throws StandardException {
      this.throwLangSetMismatch(var1);
   }

   public DataValueDescriptor cloneHolder() {
      return this.cloneValue(false);
   }

   public void throwLangSetMismatch(Object var1) throws StandardException {
      this.throwLangSetMismatch(var1.getClass().getName());
   }

   void throwLangSetMismatch(String var1) throws StandardException {
      throw StandardException.newException("XCL12.S", new Object[]{var1, this.getTypeName()});
   }

   public void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException {
      var1.setObject(var2, this.getObject());
   }

   public void setInto(ResultSet var1, int var2) throws SQLException, StandardException {
      var1.updateObject(var2, this.getObject());
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      this.setValue(var2);
   }

   public int typePrecedence() {
      return -1;
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.compare(var2) == 0);
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.compare(var2) != 0);
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.compare(var2) < 0);
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.compare(var2) > 0);
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.compare(var2) <= 0);
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.compare(var2) >= 0);
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      if (this.typePrecedence() < var2.typePrecedence()) {
         return var2.compare(flip(var1), this, var3, var4);
      } else {
         int var5 = this.compare(var2);
         switch (var1) {
            case 1 -> {
               return var5 < 0;
            }
            case 2 -> {
               return var5 == 0;
            }
            case 3 -> {
               return var5 <= 0;
            }
            case 4 -> {
               return var5 > 0;
            }
            case 5 -> {
               return var5 >= 0;
            }
            default -> {
               return false;
            }
         }
      }
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4, boolean var5) throws StandardException {
      if (this.typePrecedence() < var2.typePrecedence()) {
         return var2.compare(flip(var1), this, var3, var4, var5);
      } else {
         int var6 = this.compare(var2, var4);
         switch (var1) {
            case 1 -> {
               return var6 < 0;
            }
            case 2 -> {
               return var6 == 0;
            }
            case 3 -> {
               return var6 <= 0;
            }
            case 4 -> {
               return var6 > 0;
            }
            case 5 -> {
               return var6 >= 0;
            }
            default -> {
               return false;
            }
         }
      }
   }

   public int compare(DataValueDescriptor var1, boolean var2) throws StandardException {
      if (!this.isNull() && !var1.isNull()) {
         return this.compare(var1);
      } else if (!this.isNull()) {
         return var2 ? 1 : -1;
      } else if (!var1.isNull()) {
         return var2 ? -1 : 1;
      } else {
         return 0;
      }
   }

   public int compareTo(Object var1) {
      DataValueDescriptor var2 = (DataValueDescriptor)var1;

      try {
         return this.typePrecedence() < var2.typePrecedence() ? -1 * var2.compare(this) : this.compare(var2);
      } catch (StandardException var4) {
         return 0;
      }
   }

   protected static int flip(int var0) {
      switch (var0) {
         case 1 -> {
            return 4;
         }
         case 2 -> {
            return 2;
         }
         case 3 -> {
            return 5;
         }
         default -> {
            return var0;
         }
      }
   }

   public DataValueDescriptor coalesce(DataValueDescriptor[] var1, DataValueDescriptor var2) throws StandardException {
      for(int var3 = 0; var3 < var1.length; ++var3) {
         if (!var1[var3].isNull()) {
            var2.setValue(var1[var3]);
            return var2;
         }
      }

      var2.setToNull();
      return var2;
   }

   public BooleanDataValue in(DataValueDescriptor var1, DataValueDescriptor[] var2, boolean var3) throws StandardException {
      BooleanDataValue var4 = null;
      if (var1.isNull()) {
         return SQLBoolean.truthValue(var1, var2[0], false);
      } else {
         int var5 = 0;
         int var6 = var2.length;
         int var7 = var1.typePrecedence();
         DataValueDescriptor var8 = null;
         if (var3) {
            while(var6 - var5 > 2) {
               int var9 = (var6 - var5) / 2 + var5;
               var8 = var7 < var2[var9].typePrecedence() ? var2[var9] : var1;
               var4 = var8.equals(var1, var2[var9]);
               if (var4.equals(true)) {
                  return var4;
               }

               BooleanDataValue var10 = var8.greaterThan(var2[var9], var1);
               if (var10.equals(true)) {
                  var6 = var9;
               } else {
                  var5 = var9;
               }
            }
         }

         for(int var13 = var5; var13 < var6; ++var13) {
            var8 = var7 < var2[var13].typePrecedence() ? var2[var13] : var1;
            var4 = var8.equals(var1, var2[var13]);
            if (var4.equals(true)) {
               break;
            }

            if (var3) {
               BooleanDataValue var14 = var8.greaterThan(var2[var13], var1);
               if (var14.equals(true)) {
                  break;
               }
            }
         }

         return var4;
      }
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof DataValueDescriptor)) {
         return false;
      } else {
         try {
            return this.compare(2, (DataValueDescriptor)var1, true, false);
         } catch (StandardException var3) {
            return false;
         }
      }
   }

   public void setValue(InputStream var1, int var2) throws StandardException {
      this.throwLangSetMismatch("java.io.InputStream");
   }

   public void checkHostVariable(int var1) throws StandardException {
   }

   protected final StandardException dataTypeConversion(String var1) {
      return StandardException.newException("22005", new Object[]{var1, this.getTypeName()});
   }

   protected final StandardException outOfRange() {
      return StandardException.newException("22003", new Object[]{this.getTypeName()});
   }

   protected final StandardException invalidFormat() {
      return StandardException.newException("22018", new Object[]{this.getTypeName()});
   }
}
