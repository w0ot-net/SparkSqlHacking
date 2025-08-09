package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.shared.common.error.StandardException;

public final class SQLDouble extends NumberDataType {
   static final int DOUBLE_LENGTH = 32;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLDouble.class);
   private double value;
   private boolean isnull;

   public int getInt() throws StandardException {
      if (!(this.value > (double)(float)Integer.MAX_VALUE) && !(this.value < -2.147483649E9)) {
         return (int)this.value;
      } else {
         throw StandardException.newException("22003", new Object[]{"INTEGER"});
      }
   }

   public byte getByte() throws StandardException {
      if (!(this.value > (double)128.0F) && !(this.value < (double)-129.0F)) {
         return (byte)((int)this.value);
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public short getShort() throws StandardException {
      if (!(this.value > (double)32768.0F) && !(this.value < (double)-32769.0F)) {
         return (short)((int)this.value);
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public long getLong() throws StandardException {
      if (!(this.value > (double)Long.MAX_VALUE) && !(this.value < (double)Long.MIN_VALUE)) {
         return (long)this.value;
      } else {
         throw StandardException.newException("22003", new Object[]{"BIGINT"});
      }
   }

   public float getFloat() throws StandardException {
      if (Float.isInfinite((float)this.value)) {
         throw StandardException.newException("22003", new Object[]{"REAL"});
      } else {
         return (float)this.value;
      }
   }

   public double getDouble() {
      return this.value;
   }

   public int typeToBigDecimal() {
      return 1;
   }

   public boolean getBoolean() {
      return this.value != (double)0.0F;
   }

   public String getString() {
      return this.isNull() ? null : Double.toString(this.value);
   }

   public Object getObject() {
      return this.isNull() ? null : this.value;
   }

   void setObject(Object var1) throws StandardException {
      this.setValue((Double)var1);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      this.setValue(var1.getDouble());
   }

   public int getLength() {
      return 32;
   }

   public String getTypeName() {
      return "DOUBLE";
   }

   public int getTypeFormatId() {
      return 79;
   }

   public boolean isNull() {
      return this.isnull;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeDouble(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.value = var1.readDouble();
      this.isnull = false;
   }

   public void restoreToNull() {
      this.value = (double)0.0F;
      this.isnull = true;
   }

   protected int typeCompare(DataValueDescriptor var1) throws StandardException {
      double var2 = this.getDouble();
      double var4 = var1.getDouble();
      if (var2 == var4) {
         return 0;
      } else {
         return var2 > var4 ? 1 : -1;
      }
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      try {
         return new SQLDouble(this.value, this.isnull);
      } catch (StandardException var3) {
         return null;
      }
   }

   public DataValueDescriptor getNewNull() {
      return new SQLDouble();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws StandardException, SQLException {
      double var4 = var1.getDouble(var2);
      this.isnull = var3 && var1.wasNull();
      if (this.isnull) {
         this.value = (double)0.0F;
      } else {
         this.value = NumberDataType.normalizeDOUBLE(var4);
      }

   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, 8);
      } else {
         var1.setDouble(var2, this.value);
      }
   }

   public final void setInto(ResultSet var1, int var2) throws SQLException, StandardException {
      var1.updateDouble(var2, this.value);
   }

   public SQLDouble() {
      this.isnull = true;
   }

   public SQLDouble(double var1) throws StandardException {
      this.value = NumberDataType.normalizeDOUBLE(var1);
   }

   public SQLDouble(Double var1) throws StandardException {
      if (!(this.isnull = var1 == null)) {
         this.value = NumberDataType.normalizeDOUBLE(var1);
      }

   }

   private SQLDouble(double var1, boolean var3) throws StandardException {
      this.value = NumberDataType.normalizeDOUBLE(var1);
      this.isnull = var3;
   }

   public void setValue(String var1) throws StandardException {
      if (var1 == null) {
         this.value = (double)0.0F;
         this.isnull = true;
      } else {
         double var2 = (double)0.0F;

         try {
            var2 = Double.parseDouble(var1.trim());
         } catch (NumberFormatException var5) {
            throw this.invalidFormat();
         }

         this.value = NumberDataType.normalizeDOUBLE(var2);
         this.isnull = false;
      }

   }

   public void setValue(double var1) throws StandardException {
      this.value = NumberDataType.normalizeDOUBLE(var1);
      this.isnull = false;
   }

   public void setValue(float var1) throws StandardException {
      this.value = NumberDataType.normalizeDOUBLE((double)var1);
      this.isnull = false;
   }

   public void setValue(long var1) {
      this.value = (double)var1;
      this.isnull = false;
   }

   public void setValue(int var1) {
      this.value = (double)var1;
      this.isnull = false;
   }

   public void setValue(Number var1) throws StandardException {
      if (!this.objectNull(var1)) {
         this.setValue(var1.doubleValue());
      }
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      if (!this.objectNull(var1)) {
         double var2 = var1.doubleValue();
         if (var2 == (double)0.0F) {
            boolean var4 = var1.compareTo(BigDecimal.ZERO) == 0;
            if (!var4) {
               throw StandardException.newException("22003", new Object[]{"REAL"});
            }
         }

         this.setValue(var2);
      }
   }

   public void setValue(boolean var1) {
      this.value = var1 ? (double)1.0F : (double)0.0F;
      this.isnull = false;
   }

   public int typePrecedence() {
      return 90;
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getDouble() == var2.getDouble());
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getDouble() != var2.getDouble());
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getDouble() < var2.getDouble());
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getDouble() > var2.getDouble());
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getDouble() <= var2.getDouble());
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getDouble() >= var2.getDouble());
   }

   public NumberDataValue plus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDouble();
      }

      if (!var1.isNull() && !var2.isNull()) {
         double var4 = var1.getDouble() + var2.getDouble();
         ((NumberDataValue)var3).setValue(var4);
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDouble();
      }

      if (!var1.isNull() && !var2.isNull()) {
         double var4 = var1.getDouble() - var2.getDouble();
         ((NumberDataValue)var3).setValue(var4);
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue times(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDouble();
      }

      if (!var1.isNull() && !var2.isNull()) {
         double var4 = var1.getDouble();
         double var6 = var2.getDouble();
         double var8 = var4 * var6;
         if (var8 == (double)0.0F && var4 != (double)0.0F && var6 != (double)0.0F) {
            throw StandardException.newException("22003", new Object[]{"DOUBLE"});
         } else {
            ((NumberDataValue)var3).setValue(var8);
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDouble();
      }

      if (!var1.isNull() && !var2.isNull()) {
         double var4 = var2.getDouble();
         if (var4 == (double)0.0F) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            double var6 = var1.getDouble();
            double var8 = var6 / var4;
            if (Double.isNaN(var8)) {
               throw StandardException.newException("22012", new Object[0]);
            } else if (var8 == (double)0.0F && var6 != (double)0.0F) {
               throw StandardException.newException("22003", new Object[]{"DOUBLE"});
            } else {
               ((NumberDataValue)var3).setValue(var8);
               return (NumberDataValue)var3;
            }
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLDouble();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         double var2 = -this.getDouble();
         ((NumberDataValue)var1).setValue(var2);
         return (NumberDataValue)var1;
      }
   }

   protected boolean isNegative() {
      return !this.isNull() && this.value < (double)0.0F;
   }

   public String toString() {
      return this.isNull() ? "NULL" : Double.toString(this.value);
   }

   public int hashCode() {
      long var1 = (long)this.value;
      double var3 = (double)var1;
      if (var3 != this.value) {
         var1 = Double.doubleToLongBits(this.value);
      }

      return (int)(var1 ^ var1 >> 32);
   }

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }
}
