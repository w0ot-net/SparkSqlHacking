package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public class EncryptContainerUndoOperation implements Compensation {
   private transient EncryptContainerOperation undoOp;

   public EncryptContainerUndoOperation(EncryptContainerOperation var1) {
      this.undoOp = var1;
   }

   public EncryptContainerUndoOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
   }

   public int getTypeFormatId() {
      return 460;
   }

   public void setUndoOp(Undoable var1) {
      this.undoOp = (EncryptContainerOperation)var1;
   }

   public boolean needsRedo(Transaction var1) throws StandardException {
      return true;
   }

   public ByteArray getPreparedLog() {
      return (ByteArray)null;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      this.undoOp.undoMe(var1);
      this.releaseResource(var1);
   }

   public void releaseResource(Transaction var1) {
      if (this.undoOp != null) {
         this.undoOp.releaseResource(var1);
      }

   }

   public int group() {
      return 260;
   }

   public String toString() {
      return null;
   }
}
