package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.shared.common.error.StandardException;

public final class SQLInteger extends NumberDataType {
   static final int INTEGER_LENGTH = 4;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLInteger.class);
   private int value;
   private boolean isnull;

   public int getInt() {
      return this.value;
   }

   public byte getByte() throws StandardException {
      if (this.value <= 127 && this.value >= -128) {
         return (byte)this.value;
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public short getShort() throws StandardException {
      if (this.value <= 32767 && this.value >= -32768) {
         return (short)this.value;
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public long getLong() {
      return (long)this.value;
   }

   public float getFloat() {
      return (float)this.value;
   }

   public double getDouble() {
      return (double)this.value;
   }

   public boolean getBoolean() {
      return this.value != 0;
   }

   public String getString() {
      return this.isNull() ? null : Integer.toString(this.value);
   }

   public Object getObject() {
      return this.isNull() ? null : this.value;
   }

   public int getLength() {
      return 4;
   }

   public String getTypeName() {
      return "INTEGER";
   }

   public int getTypeFormatId() {
      return 80;
   }

   public boolean isNull() {
      return this.isnull;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.value);
   }

   public final void readExternal(ObjectInput var1) throws IOException {
      this.value = var1.readInt();
      this.isnull = false;
   }

   public void restoreToNull() {
      this.value = 0;
      this.isnull = true;
   }

   protected int typeCompare(DataValueDescriptor var1) throws StandardException {
      int var2 = this.getInt();
      int var3 = var1.getInt();
      if (var2 == var3) {
         return 0;
      } else {
         return var2 > var3 ? 1 : -1;
      }
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      SQLInteger var2 = new SQLInteger(this.value);
      var2.isnull = this.isnull;
      return var2;
   }

   public DataValueDescriptor getNewNull() {
      return new SQLInteger();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      if ((this.value = var1.getInt(var2)) == 0) {
         this.isnull = var3 && var1.wasNull();
      } else {
         this.isnull = false;
      }

   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, 4);
      } else {
         var1.setInt(var2, this.value);
      }
   }

   public final void setInto(ResultSet var1, int var2) throws SQLException {
      var1.updateInt(var2, this.value);
   }

   public SQLInteger() {
      this.isnull = true;
   }

   public SQLInteger(int var1) {
      this.value = var1;
   }

   public SQLInteger(char var1) {
      this.value = var1;
   }

   public SQLInteger(Integer var1) {
      if (!(this.isnull = var1 == null)) {
         this.value = var1;
      }

   }

   public void setValue(String var1) throws StandardException {
      if (var1 == null) {
         this.value = 0;
         this.isnull = true;
      } else {
         try {
            this.value = Integer.parseInt(var1.trim());
         } catch (NumberFormatException var3) {
            throw this.invalidFormat();
         }

         this.isnull = false;
      }

   }

   public void setValue(int var1) {
      this.value = var1;
      this.isnull = false;
   }

   public void setValue(long var1) throws StandardException {
      if (var1 <= 2147483647L && var1 >= -2147483648L) {
         this.value = (int)var1;
         this.isnull = false;
      } else {
         throw this.outOfRange();
      }
   }

   public void setValue(float var1) throws StandardException {
      var1 = NumberDataType.normalizeREAL(var1);
      if (!(var1 > (float)Integer.MAX_VALUE) && !(var1 < (float)Integer.MIN_VALUE)) {
         float var2 = (float)Math.floor((double)var1);
         this.value = (int)var2;
         this.isnull = false;
      } else {
         throw this.outOfRange();
      }
   }

   public void setValue(double var1) throws StandardException {
      var1 = NumberDataType.normalizeDOUBLE(var1);
      if (!(var1 > (double)Integer.MAX_VALUE) && !(var1 < (double)Integer.MIN_VALUE)) {
         double var3 = Math.floor(var1);
         this.value = (int)var3;
         this.isnull = false;
      } else {
         throw this.outOfRange();
      }
   }

   public void setValue(boolean var1) {
      this.value = var1 ? 1 : 0;
      this.isnull = false;
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      this.setValue(var1.getInt());
   }

   public int typePrecedence() {
      return 50;
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getInt() == var2.getInt());
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getInt() != var2.getInt());
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getInt() < var2.getInt());
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getInt() > var2.getInt());
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getInt() <= var2.getInt());
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getInt() >= var2.getInt());
   }

   public NumberDataValue times(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLInteger();
      }

      if (!var1.isNull() && !var2.isNull()) {
         long var4 = var1.getLong() * var2.getLong();
         ((NumberDataValue)var3).setValue(var4);
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue mod(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLInteger();
      }

      if (!var1.isNull() && !var2.isNull()) {
         int var4 = var2.getInt();
         if (var4 == 0) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            ((NumberDataValue)var3).setValue(var1.getInt() % var4);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLInteger();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         int var2 = this.getInt();
         if (var2 == Integer.MIN_VALUE) {
            throw this.outOfRange();
         } else {
            ((NumberDataValue)var1).setValue(-var2);
            return (NumberDataValue)var1;
         }
      }
   }

   protected boolean isNegative() {
      return !this.isNull() && this.value < 0;
   }

   public String toString() {
      return this.isNull() ? "NULL" : Integer.toString(this.value);
   }

   public int hashCode() {
      return this.value;
   }

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }
}
