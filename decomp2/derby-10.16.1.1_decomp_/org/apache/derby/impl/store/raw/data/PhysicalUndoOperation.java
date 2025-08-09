package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public final class PhysicalUndoOperation extends PageBasicOperation implements Compensation {
   private transient PhysicalPageOperation undoOp;

   PhysicalUndoOperation(BasePage var1) {
      super(var1);
   }

   PhysicalUndoOperation(BasePage var1, PhysicalPageOperation var2) {
      super(var1);
      this.undoOp = var2;
   }

   public PhysicalUndoOperation() {
   }

   public int getTypeFormatId() {
      return 105;
   }

   public void setUndoOp(Undoable var1) {
      this.undoOp = (PhysicalPageOperation)var1;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      long var4 = 0L;
      Object var6 = null;
      this.undoOp.undoMe(var1, this.page, var2, var3);
      this.releaseResource(var1);
   }

   public void releaseResource(Transaction var1) {
      if (this.undoOp != null) {
         this.undoOp.releaseResource(var1);
      }

      super.releaseResource(var1);
   }

   public int group() {
      return super.group() | 4 | 256;
   }

   public final ByteArray getPreparedLog() {
      return (ByteArray)null;
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) {
   }

   public String toString() {
      return null;
   }
}
