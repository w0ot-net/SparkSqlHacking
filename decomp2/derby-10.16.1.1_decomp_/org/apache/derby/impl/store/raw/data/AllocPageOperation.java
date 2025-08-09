package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.shared.common.error.StandardException;

public final class AllocPageOperation extends PhysicalPageOperation {
   protected long newPageNumber;
   protected int doStatus;
   protected int undoStatus;

   AllocPageOperation(AllocPage var1, long var2, int var4, int var5) throws StandardException {
      super(var1);
      this.newPageNumber = var2;
      this.doStatus = var4;
      this.undoStatus = var5;
   }

   public AllocPageOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeLong((DataOutput)var1, this.newPageNumber);
      CompressedNumber.writeInt((DataOutput)var1, this.doStatus);
      CompressedNumber.writeInt((DataOutput)var1, this.undoStatus);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.newPageNumber = CompressedNumber.readLong((DataInput)var1);
      this.doStatus = CompressedNumber.readInt((DataInput)var1);
      this.undoStatus = CompressedNumber.readInt((DataInput)var1);
   }

   public int getTypeFormatId() {
      return 111;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      ((AllocPage)this.page).setPageStatus(var2, this.newPageNumber, this.doStatus);
   }

   public void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException {
      ((AllocPage)var2).setPageStatus(var3, this.newPageNumber, this.undoStatus);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) {
   }

   public String toString() {
      return null;
   }
}
