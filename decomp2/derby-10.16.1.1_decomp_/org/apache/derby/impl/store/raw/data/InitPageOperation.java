package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.AuxObject;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.shared.common.error.StandardException;

public final class InitPageOperation extends PhysicalPageOperation {
   protected int nextRecordId;
   protected int initFlag;
   protected int pageFormatId;
   protected long pageOffset;
   protected boolean reuse;
   protected boolean overflowPage;

   InitPageOperation(BasePage var1, int var2, int var3, long var4) throws StandardException {
      super(var1);
      this.initFlag = var2;
      this.pageFormatId = var3;
      this.pageOffset = var4;
      if ((this.initFlag & 4) == 0) {
         this.nextRecordId = var1.newRecordId();
      } else {
         this.nextRecordId = 6;
      }

   }

   public InitPageOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.nextRecordId);
      CompressedNumber.writeInt((DataOutput)var1, this.initFlag);
      CompressedNumber.writeLong((DataOutput)var1, this.pageOffset);
      var1.writeInt(this.pageFormatId);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.nextRecordId = CompressedNumber.readInt((DataInput)var1);
      this.initFlag = CompressedNumber.readInt((DataInput)var1);
      this.pageOffset = CompressedNumber.readLong((DataInput)var1);
      this.pageFormatId = var1.readInt();
   }

   public int getTypeFormatId() {
      return 241;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      boolean var4 = (this.initFlag & 2) != 0;
      boolean var5 = (this.initFlag & 1) != 0;
      this.page.initPage(var2, (byte)1, this.nextRecordId, var4, var5);
   }

   protected BasePage getPageForRedoRecovery(Transaction var1) throws StandardException {
      BasePage var2 = super.getPageForRedoRecovery(var1);
      if (var2 != null) {
         return var2;
      } else {
         var2 = (BasePage)this.containerHdl.reCreatePageForRedoRecovery(this.pageFormatId, this.getPageId().getPageNumber(), this.pageOffset);
         return var2;
      }
   }

   public void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      var2.setPageStatus(var3, (byte)2);
      var2.setAuxObject((AuxObject)null);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      this.undoMe(var1, var2, var3, var4);
   }

   public String toString() {
      return null;
   }
}
