package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.shared.common.error.StandardException;

abstract class PhysicalPageOperation extends PageBasicOperation implements Undoable {
   PhysicalPageOperation(BasePage var1) {
      super(var1);
   }

   public PhysicalPageOperation() {
   }

   public Compensation generateUndo(Transaction var1, LimitObjectInput var2) throws StandardException {
      BasePage var3 = this.findpage(var1);
      var3.preDirty();
      return new PhysicalUndoOperation(var3, this);
   }

   public abstract void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException;
}
