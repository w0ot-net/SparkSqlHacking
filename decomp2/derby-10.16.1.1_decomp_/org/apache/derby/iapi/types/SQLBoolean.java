package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.sql.execute.DMLWriteResultSet;
import org.apache.derby.shared.common.error.StandardException;

public final class SQLBoolean extends DataType implements BooleanDataValue {
   static final int BOOLEAN_LENGTH = 1;
   private static final SQLBoolean BOOLEAN_TRUE = new SQLBoolean(true);
   private static final SQLBoolean BOOLEAN_FALSE = new SQLBoolean(false);
   static final SQLBoolean UNKNOWN = new SQLBoolean();
   private static final int BASE_MEMORY_USAGE;
   private boolean value;
   private boolean isnull;
   private boolean immutable;

   public boolean isNull() {
      return this.isnull;
   }

   public boolean getBoolean() {
      return this.value;
   }

   private static int makeInt(boolean var0) {
      return var0 ? 1 : 0;
   }

   public byte getByte() {
      return (byte)makeInt(this.value);
   }

   public short getShort() {
      return (short)makeInt(this.value);
   }

   public int getInt() {
      return makeInt(this.value);
   }

   public long getLong() {
      return (long)makeInt(this.value);
   }

   public float getFloat() {
      return (float)makeInt(this.value);
   }

   public double getDouble() {
      return (double)makeInt(this.value);
   }

   public int typeToBigDecimal() {
      return -5;
   }

   public String getString() {
      if (this.isNull()) {
         return null;
      } else {
         return this.value ? "true" : "false";
      }
   }

   public Object getObject() {
      return this.isNull() ? null : this.value;
   }

   public int getLength() {
      return 1;
   }

   public String getTypeName() {
      return "BOOLEAN";
   }

   public DataValueDescriptor recycle() {
      return (DataValueDescriptor)(this.immutable ? new SQLBoolean() : super.recycle());
   }

   public int getTypeFormatId() {
      return 77;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeBoolean(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.value = var1.readBoolean();
      this.isnull = false;
   }

   public void restoreToNull() {
      this.value = false;
      this.isnull = true;
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      if (this.typePrecedence() < var1.typePrecedence()) {
         return -var1.compare(this);
      } else {
         boolean var2 = this.isNull();
         boolean var3 = var1.isNull();
         if (!var2 && !var3) {
            boolean var5 = false;
            boolean var4 = this.getBoolean();
            var5 = var1.getBoolean();
            if (var4 == var5) {
               return 0;
            } else {
               return var4 && !var5 ? 1 : -1;
            }
         } else if (!var2) {
            return -1;
         } else {
            return !var3 ? 1 : 0;
         }
      }
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      return var3 || !this.isNull() && !var2.isNull() ? super.compare(var1, var2, var3, var4) : var4;
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new SQLBoolean(this.value, this.isnull);
   }

   public DataValueDescriptor getNewNull() {
      return new SQLBoolean();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      this.value = var1.getBoolean(var2);
      this.isnull = var3 && var1.wasNull();
   }

   public final void setInto(PreparedStatement var1, int var2) throws SQLException {
      if (this.isNull()) {
         var1.setNull(var2, -7);
      } else {
         var1.setBoolean(var2, this.value);
      }
   }

   public SQLBoolean() {
      this.isnull = true;
   }

   public SQLBoolean(boolean var1) {
      this.value = var1;
   }

   public SQLBoolean(Boolean var1) {
      if (!(this.isnull = var1 == null)) {
         this.value = var1;
      }

   }

   private SQLBoolean(boolean var1, boolean var2) {
      this.value = var1;
      this.isnull = var2;
   }

   public void setValue(boolean var1) {
      this.value = var1;
      this.isnull = false;
   }

   public void setValue(Boolean var1) {
      if (var1 == null) {
         this.value = false;
         this.isnull = true;
      } else {
         this.value = var1;
         this.isnull = false;
      }

   }

   public void setValue(byte var1) {
      this.value = var1 != 0;
      this.isnull = false;
   }

   public void setValue(short var1) {
      this.value = var1 != 0;
      this.isnull = false;
   }

   public void setValue(int var1) {
      this.value = var1 != 0;
      this.isnull = false;
   }

   public void setValue(long var1) {
      this.value = var1 != 0L;
      this.isnull = false;
   }

   public void setValue(float var1) {
      this.value = var1 != 0.0F;
      this.isnull = false;
   }

   public void setValue(double var1) {
      this.value = var1 != (double)0.0F;
      this.isnull = false;
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      if (var1 == null) {
         this.value = false;
         this.isnull = true;
      } else {
         this.value = BigDecimal.ZERO.compareTo(var1) != 0;
         this.isnull = false;
      }

   }

   public void setValue(String var1) throws StandardException {
      if (var1 == null) {
         this.value = false;
         this.isnull = true;
      } else {
         String var2 = StringUtil.SQLToUpperCase(var1.trim());
         if (var2.equals("TRUE")) {
            this.value = true;
            this.isnull = false;
         } else if (var2.equals("FALSE")) {
            this.value = false;
            this.isnull = false;
         } else {
            if (!var2.equals("UNKNOWN")) {
               throw this.invalidFormat();
            }

            this.value = false;
            this.isnull = true;
         }
      }

   }

   void setObject(Object var1) {
      this.setValue((Boolean)var1);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1 instanceof SQLChar) {
         this.setValue(var1.getString());
      } else {
         if (!(var1 instanceof SQLBoolean)) {
            throw StandardException.newException("XCL12.S", new Object[]{var1.getTypeName(), this.getTypeName()});
         }

         this.setValue(var1.getBoolean());
      }

   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return truthValue(var1, var2, var1.getBoolean() == var2.getBoolean());
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return truthValue(var1, var2, var1.getBoolean() != var2.getBoolean());
   }

   public BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3 = var1.getBoolean();
      boolean var4 = var2.getBoolean();
      return truthValue(var1, var2, !var3 && var4);
   }

   public BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3 = var1.getBoolean();
      boolean var4 = var2.getBoolean();
      return truthValue(var1, var2, var3 && !var4);
   }

   public BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3 = var1.getBoolean();
      boolean var4 = var2.getBoolean();
      return truthValue(var1, var2, !var3 || var4);
   }

   public BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3 = var1.getBoolean();
      boolean var4 = var2.getBoolean();
      return truthValue(var1, var2, var3 || !var4);
   }

   public BooleanDataValue and(BooleanDataValue var1) {
      return !this.equals(false) && !var1.equals(false) ? truthValue(this, var1, this.getBoolean() && var1.getBoolean()) : BOOLEAN_FALSE;
   }

   public BooleanDataValue or(BooleanDataValue var1) {
      return !this.equals(true) && !var1.equals(true) ? truthValue(this, var1, this.getBoolean() || var1.getBoolean()) : BOOLEAN_TRUE;
   }

   public BooleanDataValue is(BooleanDataValue var1) {
      if (this.equals(true) && var1.equals(true)) {
         return BOOLEAN_TRUE;
      } else if (this.equals(false) && var1.equals(false)) {
         return BOOLEAN_TRUE;
      } else {
         return this.isNull() && var1.isNull() ? BOOLEAN_TRUE : BOOLEAN_FALSE;
      }
   }

   public BooleanDataValue isNot(BooleanDataValue var1) {
      BooleanDataValue var2 = this.is(var1);
      return var2.equals(true) ? BOOLEAN_FALSE : BOOLEAN_TRUE;
   }

   public BooleanDataValue throwExceptionIfFalse(String var1, String var2, String var3) throws StandardException {
      if (!this.isNull() && !this.value) {
         throw StandardException.newException(var1, new Object[]{var2, var3});
      } else {
         return this;
      }
   }

   public BooleanDataValue throwExceptionIfImmediateAndFalse(String var1, String var2, String var3, Activation var4, int var5) throws StandardException {
      if (!this.isNull() && !this.value) {
         ExecPreparedStatement var6 = var4.getPreparedStatement();
         UUID var7 = (UUID)var6.getSavedObject(var5);
         LanguageConnectionContext var8 = var4.getLanguageConnectionContext();
         boolean var9 = var8.isEffectivelyDeferred(var8.getCurrentSQLSessionContext(var4), var7);
         if (!var9) {
            throw StandardException.newException(var1, new Object[]{var2, var3});
         }

         DMLWriteResultSet var10 = (DMLWriteResultSet)var4.getResultSet();
         var10.rememberConstraint(var7);
      }

      return this;
   }

   public int typePrecedence() {
      return 130;
   }

   public static SQLBoolean truthValue(DataValueDescriptor var0, DataValueDescriptor var1, boolean var2) {
      if (!var0.isNull() && !var1.isNull()) {
         return var2 ? BOOLEAN_TRUE : BOOLEAN_FALSE;
      } else {
         return unknownTruthValue();
      }
   }

   public static SQLBoolean truthValue(DataValueDescriptor var0, DataValueDescriptor var1, Boolean var2) {
      if (!var0.isNull() && !var1.isNull() && var2 != null) {
         return var2 == Boolean.TRUE ? BOOLEAN_TRUE : BOOLEAN_FALSE;
      } else {
         return unknownTruthValue();
      }
   }

   public static SQLBoolean truthValue(boolean var0) {
      return var0 ? BOOLEAN_TRUE : BOOLEAN_FALSE;
   }

   public static SQLBoolean unknownTruthValue() {
      return UNKNOWN;
   }

   public boolean equals(boolean var1) {
      if (this.isNull()) {
         return false;
      } else {
         return this.value == var1;
      }
   }

   public BooleanDataValue getImmutable() {
      if (this.isNull()) {
         return UNKNOWN;
      } else {
         return this.value ? BOOLEAN_TRUE : BOOLEAN_FALSE;
      }
   }

   public String toString() {
      if (this.isNull()) {
         return "NULL";
      } else {
         return this.value ? "true" : "false";
      }
   }

   public int hashCode() {
      if (this.isNull()) {
         return -1;
      } else {
         return this.value ? 1 : 0;
      }
   }

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }

   static {
      BOOLEAN_TRUE.immutable = true;
      BOOLEAN_FALSE.immutable = true;
      UNKNOWN.immutable = true;
      BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLBoolean.class);
   }
}
