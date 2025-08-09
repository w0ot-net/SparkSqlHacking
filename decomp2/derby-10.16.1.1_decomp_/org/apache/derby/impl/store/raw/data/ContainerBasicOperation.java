package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public abstract class ContainerBasicOperation implements Loggable {
   private long containerVersion;
   protected ContainerKey containerId;
   protected transient RawContainerHandle containerHdl = null;
   private transient boolean foundHere = false;

   protected ContainerBasicOperation(RawContainerHandle var1) throws StandardException {
      this.containerHdl = var1;
      this.containerId = var1.getId();
      this.containerVersion = var1.getContainerVersion();
   }

   public ContainerBasicOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.containerId.writeExternal(var1);
      CompressedNumber.writeLong((DataOutput)var1, this.containerVersion);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.containerId = ContainerKey.read(var1);
      this.containerVersion = CompressedNumber.readLong((DataInput)var1);
   }

   public ByteArray getPreparedLog() {
      return (ByteArray)null;
   }

   public void releaseResource(Transaction var1) {
      if (this.foundHere) {
         if (this.containerHdl != null) {
            this.containerHdl.close();
            this.containerHdl = null;
         }

         this.foundHere = false;
      }
   }

   public int group() {
      return 256;
   }

   protected RawContainerHandle findContainer(Transaction var1) throws StandardException {
      this.releaseResource(var1);
      RawTransaction var2 = (RawTransaction)var1;
      this.containerHdl = var2.openDroppedContainer(this.containerId, (LockingPolicy)null);
      if (var2.inRollForwardRecovery() && this.containerHdl == null) {
         this.containerHdl = this.findContainerForRedoRecovery(var2);
      }

      if (this.containerHdl == null) {
         throw StandardException.newException("40XD2", new Object[]{this.containerId});
      } else {
         this.foundHere = true;
         return this.containerHdl;
      }
   }

   protected RawContainerHandle findContainerForRedoRecovery(RawTransaction var1) throws StandardException {
      return null;
   }

   public boolean needsRedo(Transaction var1) throws StandardException {
      this.findContainer(var1);
      long var2 = this.containerHdl.getContainerVersion();
      if (var2 == this.containerVersion) {
         return true;
      } else {
         this.releaseResource(var1);
         return var2 > this.containerVersion ? false : false;
      }
   }

   public String toString() {
      return null;
   }
}
