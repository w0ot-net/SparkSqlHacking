package org.apache.derby.impl.store.access;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.UserType;

public class UTF extends UserType {
   public UTF() {
   }

   public UTF(String var1) {
      super(var1);
   }

   public int compare(DataValueDescriptor var1) {
      UTF var2 = (UTF)var1;
      return ((String)this.getObject()).compareTo((String)var2.getObject());
   }
}
