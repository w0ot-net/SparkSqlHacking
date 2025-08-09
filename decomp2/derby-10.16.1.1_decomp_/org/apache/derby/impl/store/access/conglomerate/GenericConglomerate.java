package org.apache.derby.impl.store.access.conglomerate;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.types.DataType;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public abstract class GenericConglomerate extends DataType implements Conglomerate {
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

   public static boolean hasCollatedColumns(int[] var0) {
      for(int var1 = 0; var1 < var0.length; ++var1) {
         if (var0[var1] != 0) {
            return true;
         }
      }

      return false;
   }
}
