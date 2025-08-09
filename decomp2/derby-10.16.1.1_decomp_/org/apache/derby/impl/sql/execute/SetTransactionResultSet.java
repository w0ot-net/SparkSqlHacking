package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;

class SetTransactionResultSet extends MiscResultSet {
   SetTransactionResultSet(Activation var1) {
      super(var1);
   }

   public boolean doesCommit() {
      return true;
   }
}
