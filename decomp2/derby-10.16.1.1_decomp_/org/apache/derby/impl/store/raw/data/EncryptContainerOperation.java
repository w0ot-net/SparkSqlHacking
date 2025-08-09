package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public class EncryptContainerOperation implements Undoable {
   private ContainerKey containerId;

   protected EncryptContainerOperation(RawContainerHandle var1) throws StandardException {
      this.containerId = var1.getId();
   }

   public EncryptContainerOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.containerId.writeExternal(var1);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.containerId = ContainerKey.read(var1);
   }

   public ByteArray getPreparedLog() {
      return (ByteArray)null;
   }

   public void releaseResource(Transaction var1) {
   }

   public int group() {
      return 256;
   }

   public boolean needsRedo(Transaction var1) throws StandardException {
      return false;
   }

   public int getTypeFormatId() {
      return 459;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      this.releaseResource(var1);
   }

   public void undoMe(Transaction var1) throws StandardException {
      BaseDataFileFactory var2 = (BaseDataFileFactory)((RawTransaction)var1).getDataFactory();
      EncryptOrDecryptData var3 = new EncryptOrDecryptData(var2);
      var3.restoreContainer(this.containerId);
      this.releaseResource(var1);
   }

   public Compensation generateUndo(Transaction var1, LimitObjectInput var2) throws StandardException {
      return new EncryptContainerUndoOperation(this);
   }

   public String toString() {
      return null;
   }
}
