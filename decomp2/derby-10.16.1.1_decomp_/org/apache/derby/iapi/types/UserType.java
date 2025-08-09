package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.shared.common.error.StandardException;

public class UserType extends DataType implements UserDataValue {
   private Object value;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(UserType.class);

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE;
      if (null != this.value) {
         var1 += ClassSize.estimateAndCatalogBase(this.value.getClass());
      }

      return var1;
   }

   public String getString() {
      return !this.isNull() ? this.value.toString() : null;
   }

   public boolean getBoolean() throws StandardException {
      return !this.isNull() && this.value instanceof Boolean ? (Boolean)this.value : super.getBoolean();
   }

   public byte getByte() throws StandardException {
      return !this.isNull() && this.value instanceof Number ? ((Number)this.value).byteValue() : super.getByte();
   }

   public short getShort() throws StandardException {
      return !this.isNull() && this.value instanceof Number ? ((Number)this.value).shortValue() : super.getShort();
   }

   public int getInt() throws StandardException {
      return !this.isNull() && this.value instanceof Number ? ((Number)this.value).intValue() : super.getInt();
   }

   public long getLong() throws StandardException {
      return !this.isNull() && this.value instanceof Number ? ((Number)this.value).longValue() : super.getLong();
   }

   public float getFloat() throws StandardException {
      return !this.isNull() && this.value instanceof Number ? ((Number)this.value).floatValue() : super.getFloat();
   }

   public double getDouble() throws StandardException {
      return !this.isNull() && this.value instanceof Number ? ((Number)this.value).doubleValue() : super.getDouble();
   }

   public byte[] getBytes() throws StandardException {
      return !this.isNull() && this.value instanceof byte[] ? (byte[])this.value : super.getBytes();
   }

   public Date getDate(Calendar var1) throws StandardException {
      if (!this.isNull()) {
         if (this.value instanceof Date) {
            return (Date)this.value;
         }

         if (this.value instanceof Timestamp) {
            return (new SQLTimestamp((Timestamp)this.value)).getDate(var1);
         }
      }

      return super.getDate(var1);
   }

   public Time getTime(Calendar var1) throws StandardException {
      if (!this.isNull()) {
         if (this.value instanceof Time) {
            return (Time)this.value;
         }

         if (this.value instanceof Timestamp) {
            return (new SQLTimestamp((Timestamp)this.value)).getTime(var1);
         }
      }

      return super.getTime(var1);
   }

   public Timestamp getTimestamp(Calendar var1) throws StandardException {
      if (!this.isNull()) {
         if (this.value instanceof Timestamp) {
            return (Timestamp)this.value;
         }

         if (this.value instanceof Date) {
            return (new SQLDate((Date)this.value)).getTimestamp(var1);
         }

         if (this.value instanceof Time) {
            return (new SQLTime((Time)this.value)).getTimestamp(var1);
         }
      }

      return super.getTimestamp(var1);
   }

   void setObject(Object var1) {
      this.setValue(var1);
   }

   public Object getObject() {
      return this.value;
   }

   public int getLength() {
      return -1;
   }

   public String getTypeName() {
      return this.isNull() ? "JAVA_OBJECT" : ClassInspector.readableClassName(this.value.getClass());
   }

   String getTypeName(String var1) {
      return var1;
   }

   public int getTypeFormatId() {
      return 266;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.value = var1.readObject();
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new UserType(this.value);
   }

   public DataValueDescriptor getNewNull() {
      return new UserType();
   }

   public void restoreToNull() {
      this.value = null;
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      this.value = var1.getObject(var2);
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      if (this.typePrecedence() < var1.typePrecedence()) {
         return -var1.compare(this);
      } else {
         boolean var2 = this.isNull();
         boolean var3 = var1.isNull();
         if (!var2 && !var3) {
            int var4;
            try {
               var4 = ((Comparable)this.value).compareTo(var1.getObject());
            } catch (ClassCastException var6) {
               throw StandardException.newException("XCL15.S", new Object[]{this.getTypeName(), ClassInspector.readableClassName(var1.getObject().getClass())});
            }

            if (var4 < 0) {
               var4 = -1;
            } else if (var4 > 0) {
               var4 = 1;
            }

            return var4;
         } else if (!var2) {
            return -1;
         } else {
            return !var3 ? 1 : 0;
         }
      }
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      if (var3 || !this.isNull() && !var2.isNull()) {
         if (var1 == 2 && !this.isNull() && !var2.isNull()) {
            Object var5 = this.getObject();
            if (!(var5 instanceof Comparable)) {
               return var5.equals(var2.getObject());
            }
         }

         return super.compare(var1, var2, var3, var4);
      } else {
         return var4;
      }
   }

   public UserType() {
   }

   public UserType(Object var1) {
      this.value = var1;
   }

   public void setValue(Object var1) {
      this.value = var1;
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      this.setValue(var1.getObject());
   }

   public void setBigDecimal(BigDecimal var1) {
      this.setValue((Object)var1);
   }

   public void setValue(String var1) {
      if (var1 == null) {
         this.value = null;
      } else {
         this.value = var1;
      }

   }

   public BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, var1.compare(2, var2, true, false));
   }

   public BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return SQLBoolean.truthValue(var1, var2, !var1.compare(2, var2, true, false));
   }

   public String toString() {
      return this.isNull() ? "NULL" : this.value.toString();
   }

   public int hashCode() {
      return this.isNull() ? 0 : this.value.hashCode();
   }

   public int typePrecedence() {
      return 1000;
   }

   public final boolean isNull() {
      return this.value == null;
   }
}
