package org.apache.derby.impl.store.access;

import org.apache.derby.iapi.store.access.conglomerate.ConglomerateFactory;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.UndoHandler;
import org.apache.derby.shared.common.error.StandardException;

public class RAMAccessUndoHandler implements UndoHandler {
   private RAMAccessManager access_factory;

   public RAMAccessUndoHandler(RAMAccessManager var1) {
      this.access_factory = var1;
   }

   public void insertUndoNotify(Transaction var1, PageKey var2) throws StandardException {
      long var3 = var2.getContainerId().getContainerId();
      ConglomerateFactory var5 = this.access_factory.getFactoryFromConglomId(var3);

      try {
         var5.insertUndoNotify(this.access_factory, var1, var2);
      } catch (StandardException var7) {
      }

   }
}
