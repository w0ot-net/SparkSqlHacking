package org.apache.derby.iapi.sql.dictionary;

import java.util.ArrayList;
import org.apache.derby.catalog.UUID;

public class GenericDescriptorList extends ArrayList {
   private boolean scanned;

   public void setScanned(boolean var1) {
      this.scanned = var1;
   }

   public boolean getScanned() {
      return this.scanned;
   }

   public UniqueTupleDescriptor getUniqueTupleDescriptor(UUID var1) {
      for(UniqueTupleDescriptor var3 : this) {
         if (var3.getUUID().equals(var1)) {
            return var3;
         }
      }

      return null;
   }
}
