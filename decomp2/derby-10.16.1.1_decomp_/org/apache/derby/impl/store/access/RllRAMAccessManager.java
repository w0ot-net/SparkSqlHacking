package org.apache.derby.impl.store.access;

import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class RllRAMAccessManager extends RAMAccessManager {
   private int system_lock_level = 6;

   protected int getSystemLockLevel() {
      return this.system_lock_level;
   }

   protected void bootLookupSystemLockLevel(TransactionController var1) throws StandardException {
      if (this.isReadOnly() || !PropertyUtil.getServiceBoolean(var1, "derby.storage.rowLocking", true)) {
         this.system_lock_level = 7;
      }

   }
}
