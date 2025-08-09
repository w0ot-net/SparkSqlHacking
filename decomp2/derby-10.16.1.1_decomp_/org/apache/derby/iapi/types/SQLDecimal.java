package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.shared.common.error.StandardException;

public final class SQLDecimal extends NumberDataType implements VariableSizeDataValue {
   private BigDecimal value;
   private byte[] rawData;
   private int rawScale;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLDecimal.class);
   private static final int BIG_DECIMAL_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(BigDecimal.class);

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE;
      if (null != this.value) {
         var1 += BIG_DECIMAL_MEMORY_USAGE + (this.value.unscaledValue().bitLength() + 8) / 8;
      }

      if (null != this.rawData) {
         var1 += this.rawData.length;
      }

      return var1;
   }

   public SQLDecimal() {
   }

   public SQLDecimal(BigDecimal var1) {
      this.value = var1;
   }

   public SQLDecimal(BigDecimal var1, int var2, int var3) throws StandardException {
      this.value = var1;
      if (this.value != null && var3 >= 0) {
         this.value = this.value.setScale(var3, RoundingMode.DOWN);
      }

   }

   public SQLDecimal(String var1) {
      this.value = new BigDecimal(var1);
   }

   public int getInt() throws StandardException {
      if (this.isNull()) {
         return 0;
      } else {
         try {
            long var1 = this.getLong();
            if (var1 >= -2147483648L && var1 <= 2147483647L) {
               return (int)var1;
            }
         } catch (StandardException var3) {
         }

         throw StandardException.newException("22003", new Object[]{"INTEGER"});
      }
   }

   public byte getByte() throws StandardException {
      if (this.isNull()) {
         return 0;
      } else {
         try {
            long var1 = this.getLong();
            if (var1 >= -128L && var1 <= 127L) {
               return (byte)((int)var1);
            }
         } catch (StandardException var3) {
         }

         throw StandardException.newException("22003", new Object[]{"TINYINT"});
      }
   }

   public short getShort() throws StandardException {
      if (this.isNull()) {
         return 0;
      } else {
         try {
            long var1 = this.getLong();
            if (var1 >= -32768L && var1 <= 32767L) {
               return (short)((int)var1);
            }
         } catch (StandardException var3) {
         }

         throw StandardException.newException("22003", new Object[]{"SMALLINT"});
      }
   }

   public long getLong() throws StandardException {
      BigDecimal var1 = this.getBigDecimal();
      if (var1 == null) {
         return 0L;
      } else if (var1.compareTo(MINLONG_MINUS_ONE) == 1 && var1.compareTo(MAXLONG_PLUS_ONE) == -1) {
         return var1.longValue();
      } else {
         throw StandardException.newException("22003", new Object[]{"BIGINT"});
      }
   }

   public float getFloat() throws StandardException {
      BigDecimal var1 = this.getBigDecimal();
      if (var1 == null) {
         return 0.0F;
      } else {
         float var2 = NumberDataType.normalizeREAL(var1.floatValue());
         return var2;
      }
   }

   public double getDouble() throws StandardException {
      BigDecimal var1 = this.getBigDecimal();
      if (var1 == null) {
         return (double)0.0F;
      } else {
         double var2 = NumberDataType.normalizeDOUBLE(var1.doubleValue());
         return var2;
      }
   }

   private BigDecimal getBigDecimal() {
      if (this.value == null && this.rawData != null) {
         this.value = new BigDecimal(new BigInteger(this.rawData), this.rawScale);
      }

      return this.value;
   }

   public int typeToBigDecimal() {
      return 3;
   }

   public boolean getBoolean() {
      BigDecimal var1 = this.getBigDecimal();
      if (var1 == null) {
         return false;
      } else {
         return var1.compareTo(BigDecimal.ZERO) != 0;
      }
   }

   public String getString() {
      BigDecimal var1 = this.getBigDecimal();
      return var1 == null ? null : var1.toPlainString();
   }

   public Object getObject() {
      return this.getBigDecimal();
   }

   void setObject(Object var1) throws StandardException {
      this.setValue((Number)((BigDecimal)var1));
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      this.setCoreValue(getBigDecimal(var1));
   }

   public int getLength() {
      return this.getDecimalValuePrecision();
   }

   public String getTypeName() {
      return "DECIMAL";
   }

   public int getTypeFormatId() {
      return 200;
   }

   public boolean isNull() {
      return this.value == null && this.rawData == null;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      int var2;
      byte[] var3;
      if (this.value != null) {
         var2 = this.value.scale();
         if (var2 < 0) {
            var2 = 0;
            this.value = this.value.setScale(0);
         }

         BigInteger var4 = this.value.unscaledValue();
         var3 = var4.toByteArray();
      } else {
         var2 = this.rawScale;
         var3 = this.rawData;
      }

      var1.writeByte(var2);
      var1.writeByte(var3.length);
      var1.write(var3);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.value = null;
      this.rawScale = var1.readUnsignedByte();
      int var2 = var1.readUnsignedByte();
      if (this.rawData == null || var2 != this.rawData.length) {
         this.rawData = new byte[var2];
      }

      var1.readFully(this.rawData);
   }

   public void restoreToNull() {
      this.value = null;
      this.rawData = null;
   }

   protected int typeCompare(DataValueDescriptor var1) throws StandardException {
      BigDecimal var2 = getBigDecimal(var1);
      return this.getBigDecimal().compareTo(var2);
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new SQLDecimal(this.getBigDecimal());
   }

   public DataValueDescriptor getNewNull() {
      return new SQLDecimal();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      this.value = var1.getBigDecimal(var2);
      this.rawData = null;
   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, 3);
      } else {
         var1.setBigDecimal(var2, this.getBigDecimal());
      }
   }

   public void setValue(String var1) throws StandardException {
      this.rawData = null;
      if (var1 == null) {
         this.value = null;
      } else {
         try {
            var1 = var1.trim();
            this.value = new BigDecimal(var1);
            this.rawData = null;
         } catch (NumberFormatException var3) {
            throw this.invalidFormat();
         }
      }

   }

   public void setValue(double var1) throws StandardException {
      this.setCoreValue(NumberDataType.normalizeDOUBLE(var1));
   }

   public void setValue(float var1) throws StandardException {
      this.setCoreValue((double)NumberDataType.normalizeREAL(var1));
   }

   public void setValue(long var1) {
      this.value = BigDecimal.valueOf(var1);
      this.rawData = null;
   }

   public void setValue(int var1) {
      this.setValue((long)var1);
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      this.setCoreValue(var1);
   }

   public void setValue(Number var1) throws StandardException {
      if (!(var1 instanceof BigDecimal) && var1 != null) {
         this.setValue(var1.longValue());
      } else {
         this.setCoreValue((BigDecimal)var1);
      }

   }

   public void setValue(boolean var1) {
      this.setCoreValue(var1 ? BigDecimal.ONE : BigDecimal.ZERO);
   }

   public int typePrecedence() {
      return 70;
   }

   private void setCoreValue(BigDecimal var1) {
      this.value = var1;
      this.rawData = null;
   }

   private void setCoreValue(double var1) {
      this.value = new BigDecimal(Double.toString(var1));
      this.rawData = null;
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      int var3 = var1.getScale();
      int var4 = var1.getPrecision();
      this.setFrom(var2);
      this.setWidth(var4, var3, true);
   }

   public NumberDataValue plus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDecimal();
      }

      if (!var1.isNull() && !var2.isNull()) {
         ((NumberDataValue)var3).setBigDecimal(getBigDecimal(var1).add(getBigDecimal(var2)));
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDecimal();
      }

      if (!var1.isNull() && !var2.isNull()) {
         ((NumberDataValue)var3).setBigDecimal(getBigDecimal(var1).subtract(getBigDecimal(var2)));
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue times(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDecimal();
      }

      if (!var1.isNull() && !var2.isNull()) {
         ((NumberDataValue)var3).setBigDecimal(getBigDecimal(var1).multiply(getBigDecimal(var2)));
         return (NumberDataValue)var3;
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      return this.divide(var1, var2, var3, -1);
   }

   public NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3, int var4) throws StandardException {
      if (var3 == null) {
         var3 = new SQLDecimal();
      }

      if (!var1.isNull() && !var2.isNull()) {
         BigDecimal var5 = getBigDecimal(var2);
         if (var5.compareTo(BigDecimal.ZERO) == 0) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            BigDecimal var6 = getBigDecimal(var1);
            ((NumberDataValue)var3).setBigDecimal(var6.divide(var5, var4 > -1 ? var4 : Math.max(var6.scale() + getWholeDigits(var5) + 1, 4), RoundingMode.DOWN));
            return (NumberDataValue)var3;
         }
      } else {
         ((NumberDataValue)var3).setToNull();
         return (NumberDataValue)var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLDecimal();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         ((NumberDataValue)var1).setBigDecimal(this.getBigDecimal().negate());
         return (NumberDataValue)var1;
      }
   }

   protected boolean isNegative() {
      return !this.isNull() && this.getBigDecimal().compareTo(BigDecimal.ZERO) == -1;
   }

   public String toString() {
      return this.isNull() ? "NULL" : this.getString();
   }

   public int hashCode() {
      BigDecimal var3 = this.getBigDecimal();
      double var4 = var3 != null ? var3.doubleValue() : (double)0.0F;
      long var1;
      if (Double.isInfinite(var4)) {
         var1 = var3.longValue();
      } else {
         var1 = (long)var4;
         if ((double)var1 != var4) {
            var1 = Double.doubleToLongBits(var4);
         }
      }

      return (int)(var1 ^ var1 >> 32);
   }

   public void setWidth(int var1, int var2, boolean var3) throws StandardException {
      if (!this.isNull()) {
         if (var1 != -1 && var1 - var2 < getWholeDigits(this.getBigDecimal())) {
            throw StandardException.newException("22003", new Object[]{"DECIMAL/NUMERIC(" + var1 + "," + var2 + ")"});
         } else {
            this.value = this.value.setScale(var2, RoundingMode.DOWN);
            this.rawData = null;
         }
      }
   }

   public int getDecimalValuePrecision() {
      if (this.isNull()) {
         return 0;
      } else {
         BigDecimal var1 = this.getBigDecimal();
         return getWholeDigits(var1) + this.getDecimalValueScale();
      }
   }

   public int getDecimalValueScale() {
      if (this.isNull()) {
         return 0;
      } else if (this.value == null) {
         return this.rawScale;
      } else {
         int var1 = this.value.scale();
         return var1 >= 0 ? var1 : 0;
      }
   }

   public static BigDecimal getBigDecimal(DataValueDescriptor var0) throws StandardException {
      switch (var0.typeToBigDecimal()) {
         case -5:
            return BigDecimal.valueOf(var0.getLong());
         case 1:
            try {
               return new BigDecimal(var0.getString().trim());
            } catch (NumberFormatException var2) {
               throw StandardException.newException("22018", new Object[]{"java.math.BigDecimal"});
            }
         case 3:
            return (BigDecimal)var0.getObject();
         default:
            return null;
      }
   }

   private static int getWholeDigits(BigDecimal var0) {
      var0 = var0.abs();
      return BigDecimal.ONE.compareTo(var0) == 1 ? 0 : var0.precision() - var0.scale();
   }
}
