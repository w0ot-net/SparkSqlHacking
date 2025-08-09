package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public class ContainerOperation extends ContainerBasicOperation implements Undoable {
   protected byte operation;
   protected transient boolean hasCreateByteArray = true;
   protected ByteArray createByteArray;
   protected static final byte CREATE = 1;
   protected static final byte DROP = 2;
   protected static final byte REMOVE = 4;

   protected ContainerOperation(RawContainerHandle var1, byte var2) throws StandardException {
      super(var1);
      this.operation = var2;
   }

   public ContainerOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      var1.writeByte(this.operation);
      if (this.operation == 1) {
         try {
            this.createByteArray = this.containerHdl.logCreateContainerInfo();
         } catch (StandardException var3) {
            throw new IOException(var3.toString());
         }

         this.createByteArray.writeExternal(var1);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.operation = var1.readByte();
      if (this.operation == 1 && this.hasCreateByteArray) {
         this.createByteArray = new ByteArray();
         this.createByteArray.readExternal(var1);
      }

   }

   public int getTypeFormatId() {
      return 242;
   }

   protected RawContainerHandle findContainerForRedoRecovery(RawTransaction var1) throws StandardException {
      long var2 = this.containerId.getSegmentId();
      long var4 = this.containerId.getContainerId();
      var1.reCreateContainerForRedoRecovery(var2, var4, this.createByteArray);
      return var1.openDroppedContainer(this.containerId, (LockingPolicy)null);
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      switch (this.operation) {
         case 1:
         case 3:
         default:
            break;
         case 2:
            this.containerHdl.dropContainer(var2, true);
            break;
         case 4:
            this.containerHdl.removeContainer(var2);
      }

      this.releaseResource(var1);
   }

   public void undoMe(Transaction var1, RawContainerHandle var2, LogInstant var3, LimitObjectInput var4) throws StandardException {
      switch (this.operation) {
         case 1:
            var2.removeContainer(var3);
            break;
         case 2:
            var2.dropContainer(var3, false);
         case 3:
         case 4:
      }

      this.releaseResource(var1);
   }

   public Compensation generateUndo(Transaction var1, LimitObjectInput var2) throws StandardException {
      if (this.operation == 4) {
         return null;
      } else {
         RawContainerHandle var3 = this.findContainer(var1);
         return new ContainerUndoOperation(var3, this);
      }
   }

   public String toString() {
      return null;
   }
}
