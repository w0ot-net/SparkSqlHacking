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

public class CompressSpacePageOperation extends PhysicalPageOperation {
   protected int newHighestPage;
   protected int num_pages_truncated;

   CompressSpacePageOperation(AllocPage var1, int var2, int var3) throws StandardException {
      super(var1);
      this.newHighestPage = var2;
      this.num_pages_truncated = var3;
   }

   public CompressSpacePageOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      if (!(this instanceof CompressSpacePageOperation10_2)) {
         var1.writeInt(this.newHighestPage);
         CompressedNumber.writeInt((DataOutput)var1, this.num_pages_truncated);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      if (!(this instanceof CompressSpacePageOperation10_2)) {
         this.newHighestPage = var1.readInt();
         this.num_pages_truncated = CompressedNumber.readInt((DataInput)var1);
      }

   }

   public int getTypeFormatId() {
      return 465;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      ((AllocPage)this.page).compressSpace(var2, this.newHighestPage, this.num_pages_truncated);
   }

   public void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException {
      ((AllocPage)var2).undoCompressSpace(var3, this.newHighestPage, this.num_pages_truncated);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) {
   }

   public String toString() {
      return null;
   }
}
