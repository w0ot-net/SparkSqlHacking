package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public final class LogicalUndoOperation extends PageBasicOperation implements Compensation {
   protected int recordId;
   private transient LogicalPageOperation undoOp = null;

   LogicalUndoOperation(BasePage var1) {
      super(var1);
   }

   LogicalUndoOperation(BasePage var1, int var2, LogicalPageOperation var3) {
      super(var1);
      this.undoOp = var3;
      this.recordId = var2;
   }

   public LogicalUndoOperation() {
   }

   public int getTypeFormatId() {
      return 104;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.recordId);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.recordId = CompressedNumber.readInt((DataInput)var1);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) {
   }

   public void setUndoOp(Undoable var1) {
      this.undoOp = (LogicalPageOperation)var1;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      long var4 = 0L;
      Object var6 = null;
      this.undoOp.undoMe(var1, this.page, this.recordId, var2, var3);
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

   public String toString() {
      return null;
   }
}
