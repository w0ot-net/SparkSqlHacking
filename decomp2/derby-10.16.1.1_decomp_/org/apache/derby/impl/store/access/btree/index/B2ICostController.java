package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.impl.store.access.btree.BTreeCostController;
import org.apache.derby.shared.common.error.StandardException;

public class B2ICostController extends BTreeCostController {
   B2ICostController() {
   }

   void init(TransactionManager var1, B2I var2, Transaction var3) throws StandardException {
      super.init(var1, var2, var3);
   }
}
