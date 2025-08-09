package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.shared.common.error.StandardException;

public class ContainerUndoOperation extends ContainerBasicOperation implements Compensation {
   private transient ContainerOperation undoOp;

   public ContainerUndoOperation(RawContainerHandle var1, ContainerOperation var2) throws StandardException {
      super(var1);
      this.undoOp = var2;
   }

   public ContainerUndoOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
   }

   public int getTypeFormatId() {
      return 107;
   }

   public void setUndoOp(Undoable var1) {
      this.undoOp = (ContainerOperation)var1;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      this.undoOp.undoMe(var1, this.containerHdl, var2, var3);
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
}
