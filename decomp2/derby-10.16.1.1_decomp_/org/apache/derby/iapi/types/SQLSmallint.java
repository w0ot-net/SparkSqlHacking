package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.shared.common.error.StandardException;

public final class SQLSmallint extends NumberDataType {
   static final int SMALLINT_LENGTH = 2;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLSmallint.class);
   private short value;
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

   public short getShort() {
      return this.value;
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
      return this.isNull() ? null : Short.toString(this.value);
   }

   public int getLength() {
      return 2;
   }

   public Object getObject() {
      return this.isNull() ? null : Integer.valueOf(this.value);
   }

   public String getTypeName() {
      return "SMALLINT";
   }

   public int getTypeFormatId() {
      return 83;
   }

   public boolean isNull() {
      return this.isnull;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeShort(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.value = var1.readShort();
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
      return new SQLSmallint(this.value, this.isnull);
   }

   public DataValueDescriptor getNewNull() {
      return new SQLSmallint();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      try {
         this.value = var1.getShort(var2);
         this.isnull = var3 && var1.wasNull();
      } catch (SQLException var6) {
         int var5 = var1.getInt(var2);
         this.value = (short)var5;
         this.isnull = false;
      }

   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, 5);
      } else {
         var1.setShort(var2, this.value);
      }
   }

   public final void setInto(ResultSet var1, int var2) throws SQLException, StandardException {
      var1.updateShort(var2, this.value);
   }

   public SQLSmallint() {
      this.isnull = true;
   }

   public SQLSmallint(short var1) {
      this.value = var1;
   }

   private SQLSmallint(short var1, boolean var2) {
      this.value = var1;
      this.isnull = var2;
   }

   public SQLSmallint(Short var1) {
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
            this.value = Short.valueOf(var1.trim());
         } catch (NumberFormatException var3) {
            throw this.invalidFormat();
         }

         this.isnull = false;
      }

   }

   public void setValue(short var1) {
      this.value = var1;
      this.isnull = false;
   }

   public void setValue(byte var1) {
      this.value = (short)var1;
      this.isnull = false;
   }

   public void setValue(int var1) throws StandardException {
      if (var1 <= 32767 && var1 >= -32768) {
         this.value = (short)var1;
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public void setValue(long var1) throws StandardException {
      if (var1 <= 32767L && var1 >= -32768L) {
         this.value = (short)((int)var1);
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public void setValue(float var1) throws StandardException {
      var1 = NumberDataType.normalizeREAL(var1);
      if (!(var1 > 32767.0F) && !(var1 < -32768.0F)) {
         float var2 = (float)Math.floor((double)var1);
         this.value = (short)((int)var2);
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public void setValue(double var1) throws StandardException {
      var1 = NumberDataType.normalizeDOUBLE(var1);
      if (!(var1 > (double)32767.0F) && !(var1 < (double)-32768.0F)) {
         double var3 = Math.floor(var1);
         this.value = (short)((int)var3);
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public void setValue(boolean var1) {
      this.value = (short)(var1 ? 1 : 0);
      this.isnull = false;
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      this.setValue(var1.getShort());
   }

   public int typePrecedence() {
      return 40;
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getShort() == var2.getShort());
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getShort() != var2.getShort());
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getShort() < var2.getShort());
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getShort() > var2.getShort());
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getShort() <= var2.getShort());
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getShort() >= var2.getShort());
   }

   public NumberDataValue times(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLSmallint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         int var4 = var1.getShort() * var2.getShort();
         ((NumberDataValue)var3).setValue(var4);
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue mod(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLSmallint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         short var4 = var2.getShort();
         if (var4 == 0) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            ((NumberDataValue)var3).setValue(var1.getShort() % var4);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLSmallint();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         short var2 = this.getShort();
         ((NumberDataValue)var1).setValue(-var2);
         return (NumberDataValue)var1;
      }
   }

   protected boolean isNegative() {
      return !this.isNull() && this.value < 0;
   }

   public String toString() {
      return this.isNull() ? "NULL" : Short.toString(this.value);
   }

   public int hashCode() {
      return this.value;
   }

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }
}
