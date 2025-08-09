package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.shared.common.error.StandardException;

public final class SQLLongint extends NumberDataType {
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLLongint.class);
   private long value;
   private boolean isnull;

   public int getInt() throws StandardException {
      if (this.value <= 2147483647L && this.value >= -2147483648L) {
         return (int)this.value;
      } else {
         throw StandardException.newException("22003", new Object[]{"INTEGER"});
      }
   }

   public byte getByte() throws StandardException {
      if (this.value <= 127L && this.value >= -128L) {
         return (byte)((int)this.value);
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public short getShort() throws StandardException {
      if (this.value <= 32767L && this.value >= -32768L) {
         return (short)((int)this.value);
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public long getLong() {
      return this.value;
   }

   public float getFloat() {
      return (float)this.value;
   }

   public double getDouble() {
      return (double)this.value;
   }

   public boolean getBoolean() {
      return this.value != 0L;
   }

   public String getString() {
      return this.isNull() ? null : Long.toString(this.value);
   }

   public Object getObject() {
      return this.isNull() ? null : this.value;
   }

   public int getLength() {
      return 8;
   }

   public String getTypeName() {
      return "BIGINT";
   }

   public int getTypeFormatId() {
      return 84;
   }

   public boolean isNull() {
      return this.isnull;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeLong(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.value = var1.readLong();
      this.isnull = false;
   }

   public void restoreToNull() {
      this.value = 0L;
      this.isnull = true;
   }

   protected int typeCompare(DataValueDescriptor var1) throws StandardException {
      long var2 = this.getLong();
      long var4 = var1.getLong();
      if (var2 == var4) {
         return 0;
      } else {
         return var2 > var4 ? 1 : -1;
      }
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new SQLLongint(this.value, this.isnull);
   }

   public DataValueDescriptor getNewNull() {
      return new SQLLongint();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      if ((this.value = var1.getLong(var2)) == 0L) {
         this.isnull = var3 && var1.wasNull();
      } else {
         this.isnull = false;
      }

   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, -5);
      } else {
         var1.setLong(var2, this.value);
      }
   }

   public final void setInto(ResultSet var1, int var2) throws SQLException {
      var1.updateLong(var2, this.value);
   }

   public SQLLongint() {
      this.isnull = true;
   }

   public SQLLongint(long var1) {
      this.value = var1;
   }

   private SQLLongint(long var1, boolean var3) {
      this.value = var1;
      this.isnull = var3;
   }

   public SQLLongint(Long var1) {
      if (!(this.isnull = var1 == null)) {
         this.value = var1;
      }

   }

   public void setValue(String var1) throws StandardException {
      if (var1 == null) {
         this.value = 0L;
         this.isnull = true;
      } else {
         try {
            this.value = Long.valueOf(var1.trim());
         } catch (NumberFormatException var3) {
            throw this.invalidFormat();
         }

         this.isnull = false;
      }

   }

   public final void setValue(Number var1) {
      if (!this.objectNull(var1)) {
         this.setValue(var1.longValue());
      }
   }

   public void setValue(long var1) {
      this.value = var1;
      this.isnull = false;
   }

   public void setValue(int var1) {
      this.value = (long)var1;
      this.isnull = false;
   }

   public void setValue(float var1) throws StandardException {
      var1 = NumberDataType.normalizeREAL(var1);
      if (!(var1 > (float)Long.MAX_VALUE) && !(var1 < (float)Long.MIN_VALUE)) {
         float var2 = (float)Math.floor((double)var1);
         this.value = (long)var2;
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"BIGINT"});
      }
   }

   public void setValue(double var1) throws StandardException {
      var1 = NumberDataType.normalizeDOUBLE(var1);
      if (!(var1 > (double)Long.MAX_VALUE) && !(var1 < (double)Long.MIN_VALUE)) {
         double var3 = Math.floor(var1);
         this.value = (long)var3;
         this.isnull = false;
      } else {
         throw StandardException.newException("22003", new Object[]{"BIGINT"});
      }
   }

   public void setValue(boolean var1) {
      this.value = var1 ? 1L : 0L;
      this.isnull = false;
   }

   void setObject(Object var1) {
      this.setValue((Long)var1);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      this.setValue(var1.getLong());
   }

   public int typePrecedence() {
      return 60;
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getLong() == var2.getLong());
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getLong() != var2.getLong());
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getLong() < var2.getLong());
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getLong() > var2.getLong());
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getLong() <= var2.getLong());
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getLong() >= var2.getLong());
   }

   public NumberDataValue plus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLLongint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         long var4 = var1.getLong();
         long var6 = var2.getLong();
         long var8 = var4 + var6;
         if (var4 < 0L == var6 < 0L && var4 < 0L != var8 < 0L) {
            throw StandardException.newException("22003", new Object[]{"BIGINT"});
         } else {
            ((NumberDataValue)var3).setValue(var8);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLLongint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         long var4 = var1.getLong() - var2.getLong();
         if (var1.getLong() < 0L != var2.getLong() < 0L && var1.getLong() < 0L != var4 < 0L) {
            throw StandardException.newException("22003", new Object[]{"BIGINT"});
         } else {
            ((NumberDataValue)var3).setValue(var4);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue times(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLLongint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         long var4 = var1.getLong() * var2.getLong();
         if (var2.getLong() != 0L && var1.getLong() != var4 / var2.getLong()) {
            throw StandardException.newException("22003", new Object[]{"BIGINT"});
         } else {
            ((NumberDataValue)var3).setValue(var4);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLLongint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         long var4 = var2.getLong();
         if (var4 == 0L) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            ((NumberDataValue)var3).setValue(var1.getLong() / var4);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue mod(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLLongint();
      }

      if (!var1.isNull() && !var2.isNull()) {
         long var4 = var2.getLong();
         if (var4 == 0L) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            ((NumberDataValue)var3).setValue(var1.getLong() % var4);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLLongint();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         long var2 = this.getLong();
         if (var2 == Long.MIN_VALUE) {
            throw StandardException.newException("22003", new Object[]{"BIGINT"});
         } else {
            ((NumberDataValue)var1).setValue(-var2);
            return (NumberDataValue)var1;
         }
      }
   }

   protected boolean isNegative() {
      return !this.isNull() && this.value < 0L;
   }

   public String toString() {
      return this.isNull() ? "NULL" : Long.toString(this.value);
   }

   public int hashCode() {
      return (int)(this.value ^ this.value >> 32);
   }

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }
}
