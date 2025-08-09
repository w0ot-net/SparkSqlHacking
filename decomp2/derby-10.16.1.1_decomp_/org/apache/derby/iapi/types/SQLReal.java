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

public final class SQLReal extends NumberDataType {
   static final int REAL_LENGTH = 16;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLReal.class);
   private float value;
   private boolean isnull;

   public int getInt() throws StandardException {
      if (!((double)this.value > (double)(float)Integer.MAX_VALUE) && !((double)this.value < -2.147483649E9)) {
         return (int)this.value;
      } else {
         throw StandardException.newException("22003", new Object[]{"INTEGER"});
      }
   }

   public byte getByte() throws StandardException {
      if (!((double)this.value > (double)128.0F) && !((double)this.value < (double)-129.0F)) {
         return (byte)((int)this.value);
      } else {
         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public short getShort() throws StandardException {
      if (!((double)this.value > (double)32768.0F) && !((double)this.value < (double)-32769.0F)) {
         return (short)((int)this.value);
      } else {
         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public long getLong() throws StandardException {
      if (!((double)this.value > (double)Long.MAX_VALUE) && !((double)this.value < (double)Long.MIN_VALUE)) {
         return (long)this.value;
      } else {
         throw StandardException.newException("22003", new Object[]{"BIGINT"});
      }
   }

   public float getFloat() {
      return this.value;
   }

   public double getDouble() {
      return (double)this.value;
   }

   public int typeToBigDecimal() {
      return 1;
   }

   public boolean getBoolean() {
      return this.value != 0.0F;
   }

   public String getString() {
      return this.isNull() ? null : Float.toString(this.value);
   }

   public int getLength() {
      return 16;
   }

   public Object getObject() {
      return this.isNull() ? null : this.value;
   }

   public String getTypeName() {
      return "REAL";
   }

   public int getTypeFormatId() {
      return 81;
   }

   public boolean isNull() {
      return this.isnull;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeFloat(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.value = var1.readFloat();
      this.isnull = false;
   }

   public void restoreToNull() {
      this.value = 0.0F;
      this.isnull = true;
   }

   protected int typeCompare(DataValueDescriptor var1) throws StandardException {
      float var2 = this.getFloat();
      float var3 = NumberDataType.normalizeREAL(var1.getFloat());
      if (var2 == var3) {
         return 0;
      } else {
         return var2 > var3 ? 1 : -1;
      }
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      SQLReal var2 = new SQLReal();
      var2.value = this.value;
      var2.isnull = this.isnull;
      return var2;
   }

   public DataValueDescriptor getNewNull() {
      return new SQLReal();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws StandardException, SQLException {
      float var4 = var1.getFloat(var2);
      if (var3 && var1.wasNull()) {
         this.restoreToNull();
      } else {
         this.setValue(var4);
      }

   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, 7);
      } else {
         var1.setFloat(var2, this.value);
      }
   }

   public final void setInto(ResultSet var1, int var2) throws SQLException, StandardException {
      var1.updateFloat(var2, this.value);
   }

   public SQLReal() {
      this.isnull = true;
   }

   public SQLReal(float var1) throws StandardException {
      this.value = NumberDataType.normalizeREAL(var1);
   }

   public SQLReal(Float var1) throws StandardException {
      if (!(this.isnull = var1 == null)) {
         this.value = NumberDataType.normalizeREAL(var1);
      }

   }

   public void setValue(String var1) throws StandardException {
      if (var1 == null) {
         this.value = 0.0F;
         this.isnull = true;
      } else {
         try {
            this.setValue(Double.parseDouble(var1.trim()));
         } catch (NumberFormatException var3) {
            throw this.invalidFormat();
         }
      }

   }

   public void setValue(Number var1) throws StandardException {
      if (!this.objectNull(var1)) {
         this.setValue(var1.floatValue());
      }
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      if (!this.objectNull(var1)) {
         float var2 = var1.floatValue();
         if (var2 == 0.0F) {
            boolean var3 = var1.compareTo(BigDecimal.ZERO) == 0;
            if (!var3) {
               throw StandardException.newException("22003", new Object[]{"REAL"});
            }
         }

         this.setValue(var2);
      }
   }

   public void setValue(float var1) throws StandardException {
      this.value = NumberDataType.normalizeREAL(var1);
      this.isnull = false;
   }

   public void setValue(int var1) {
      this.value = (float)var1;
      this.isnull = false;
   }

   public void setValue(long var1) {
      this.value = (float)var1;
      this.isnull = false;
   }

   public void setValue(double var1) throws StandardException {
      float var3 = (float)var1;
      if (var3 == 0.0F && var1 != (double)0.0F) {
         throw StandardException.newException("22003", new Object[]{"REAL"});
      } else {
         this.setValue(var3);
      }
   }

   public void setValue(boolean var1) {
      this.value = var1 ? 1.0F : 0.0F;
      this.isnull = false;
   }

   void setObject(Object var1) throws StandardException {
      this.setValue((Float)var1);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1 instanceof StringDataValue) {
         this.setValue(var1.getString());
      } else if (var1 instanceof SQLDouble) {
         this.setValue(var1.getDouble());
      } else {
         this.setValue(var1.getFloat());
      }

   }

   public int typePrecedence() {
      return 80;
   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getFloat() == var2.getFloat());
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getFloat() != var2.getFloat());
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getFloat() < var2.getFloat());
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getFloat() > var2.getFloat());
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getFloat() <= var2.getFloat());
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.getFloat() >= var2.getFloat());
   }

   public NumberDataValue plus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLReal();
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
         var3 = new SQLReal();
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
         var3 = new SQLReal();
      }

      if (!var1.isNull() && !var2.isNull()) {
         double var4 = var1.getDouble();
         double var6 = var2.getDouble();
         double var8 = var4 * var6;
         if (var8 == (double)0.0F && var4 != (double)0.0F && var6 != (double)0.0F) {
            throw StandardException.newException("22003", new Object[]{"REAL"});
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
         var3 = new SQLReal();
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
               throw StandardException.newException("22003", new Object[]{"REAL"});
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
         var1 = new SQLReal();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         float var2 = -this.getFloat();
         ((NumberDataValue)var1).setValue(var2);
         return (NumberDataValue)var1;
      }
   }

   protected boolean isNegative() {
      return !this.isNull() && this.value < 0.0F;
   }

   public String toString() {
      return this.isNull() ? "NULL" : Float.toString(this.value);
   }

   public int hashCode() {
      long var1 = (long)this.value;
      if ((float)var1 != this.value) {
         var1 = Double.doubleToLongBits((double)this.value);
      }

      return (int)(var1 ^ var1 >> 32);
   }

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }
}
