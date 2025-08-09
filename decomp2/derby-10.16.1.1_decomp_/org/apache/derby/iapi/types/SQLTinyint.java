package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.shared.common.error.StandardException;

public final class SQLTinyint extends NumberDataType {
   static final int TINYINT_LENGTH = 1;
   private byte value;
   private boolean isnull;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLTinyint.class);

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }

   public SQLTinyint() {
      this.isnull = true;
   }

   public SQLTinyint(byte var1) {
      this.value = var1;
   }

   private SQLTinyint(byte var1, boolean var2) {
      this.value = var1;
      this.isnull = var2;
   }

   public SQLTinyint(Byte var1) {
      if (!(this.isnull = var1 == null)) {
         this.value = var1;
      }

   }

   public int getInt() {
      return this.value;
   }

   public byte getByte() {
      return this.value;
   }

   public short getShort() {
      return (short)this.value;
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
      return this.isNull() ? null : Byte.toString(this.value);
   }

   public int getLength() {
      return 1;
   }

   public Object getObject() {
      return this.isNull() ? null : this.value;
   }

   public String getTypeName() {
      return "TINYINT";
   }

   public int getTypeFormatId() {
      return 199;
   }

   public boolean isNull() {
      return this.isnull;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeByte(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.value = var1.readByte();
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
      return new SQLTinyint(this.value, this.isnull);
   }

   public DataValueDescriptor getNewNull() {
      return new SQLTinyint();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      this.value = var1.getByte(var2);
      this.isnull = var3 && var1.wasNull();
   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, -6);
      } else {
         var1.setByte(var2, this.value);
      }
   }

   public final void setInto(ResultSet var1, int var2) throws SQLException, StandardException {
      var1.updateByte(var2, this.value);
   }

   public void setValue(String var1) throws StandardException {
      if (var1 == null) {
         this.value = 0;
         this.isnull = true;
      } else {
         try {
            this.value = Byte.valueOf(var1.trim());
         } catch (NumberFormatException var3) {
            throw this.invalidFormat();
         }

         this.isnull = false;
      }

   }

   public void setValue(byte var1) {
      this.value = var1;
      this.isnull = false;
   }

   public void setValue(short var1) throws StandardException {
      if (var1 <= 127 && var1 >= -128) {
         this.value = (byte)var1;
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public void setValue(int var1) throws StandardException {
      if (var1 <= 127 && var1 >= -128) {
         this.value = (byte)var1;
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public void setValue(long var1) throws StandardException {
      if (var1 <= 127L && var1 >= -128L) {
         this.value = (byte)((int)var1);
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public void setValue(float var1) throws StandardException {
      var1 = NumberDataType.normalizeREAL(var1);
      if (!(var1 > 127.0F) && !(var1 < -128.0F)) {
         float var2 = (float)Math.floor((double)var1);
         this.value = (byte)((int)var2);
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public void setValue(double var1) throws StandardException {
      var1 = NumberDataType.normalizeDOUBLE(var1);
      if (!(var1 > (double)127.0F) && !(var1 < (double)-128.0F)) {
         double var3 = Math.floor(var1);
         this.value = (byte)((int)var3);
         this.isnull = false;
      } else {
         throw this.outOfRange();
      }
   }

   public void setValue(boolean var1) {
      this.value = (byte)(var1 ? 1 : 0);
      this.isnull = false;
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      this.setValue(var1.getByte());
   }

   public int typePrecedence() {
      return 30;
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getByte() == var2.getByte());
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getByte() != var2.getByte());
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getByte() < var2.getByte());
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getByte() > var2.getByte());
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getByte() <= var2.getByte());
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getByte() >= var2.getByte());
   }

   public NumberDataValue times(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLTinyint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         int var4 = var1.getByte() * var2.getByte();
         ((NumberDataValue)var3).setValue(var4);
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue mod(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLTinyint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         byte var4 = var2.getByte();
         if (var4 == 0) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            ((NumberDataValue)var3).setValue(var1.getByte() % var4);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLTinyint();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         byte var2 = this.getByte();
         ((NumberDataValue)var1).setValue(-var2);
         return (NumberDataValue)var1;
      }
   }

   protected boolean isNegative() {
      return !this.isNull() && this.value < 0;
   }

   public String toString() {
      return this.isNull() ? "NULL" : Byte.toString(this.value);
   }

   public int hashCode() {
      return this.value;
   }
}
