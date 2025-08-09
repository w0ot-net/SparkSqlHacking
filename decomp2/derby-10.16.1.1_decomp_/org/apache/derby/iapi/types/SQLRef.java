package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.shared.common.error.StandardException;

public class SQLRef extends DataType implements RefDataValue {
   protected RowLocation value;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLRef.class);

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE;
      if (null != this.value) {
         var1 += this.value.estimateMemoryUsage();
      }

      return var1;
   }

   public String getString() {
      return this.value != null ? this.value.toString() : null;
   }

   public Object getObject() {
      return this.value;
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1.isNull()) {
         this.setToNull();
      } else {
         this.value = (RowLocation)var1.getObject();
      }

   }

   public int getLength() {
      return -1;
   }

   public String getTypeName() {
      return "REF";
   }

   public int getTypeFormatId() {
      return 82;
   }

   public boolean isNull() {
      return this.value == null;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.value = (RowLocation)var1.readObject();
   }

   public void restoreToNull() {
      this.value = null;
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      return this.value.compare(var1, ((SQLRef)var2).value, var3, var4);
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      return this.value.compare(((SQLRef)var1).value);
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return this.value == null ? new SQLRef() : new SQLRef((RowLocation)this.value.cloneValue(false));
   }

   public DataValueDescriptor getNewNull() {
      return new SQLRef();
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) {
   }

   public void setInto(PreparedStatement var1, int var2) {
   }

   public SQLRef() {
   }

   public SQLRef(RowLocation var1) {
      this.value = var1;
   }

   public void setValue(RowLocation var1) {
      this.value = var1;
   }

   public String toString() {
      return this.value == null ? "NULL" : this.value.toString();
   }

   public int hashCode() {
      return this.value == null ? 0 : this.value.hashCode();
   }
}
