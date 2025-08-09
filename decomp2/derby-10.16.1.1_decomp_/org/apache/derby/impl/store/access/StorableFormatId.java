package org.apache.derby.impl.store.access;

import java.io.DataInput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.types.DataType;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class StorableFormatId extends DataType {
   private int format_id;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(StorableFormatId.class);

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }

   public StorableFormatId() {
   }

   public StorableFormatId(int var1) {
      this.format_id = var1;
   }

   public int getValue() {
      return this.format_id;
   }

   public void setValue(int var1) {
      this.format_id = var1;
   }

   public int getTypeFormatId() {
      return 93;
   }

   public boolean isNull() {
      return false;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatIdUtil.writeFormatIdInteger(var1, this.format_id);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.format_id = FormatIdUtil.readFormatIdInteger((DataInput)var1);
   }

   public void restoreToNull() {
      this.format_id = 0;
   }

   public int getLength() throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   public String getString() throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   public Object getObject() throws StandardException {
      return this;
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return null;
   }

   public DataValueDescriptor getNewNull() {
      return null;
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws StandardException, SQLException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   public String getTypeName() {
      return null;
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }
}
