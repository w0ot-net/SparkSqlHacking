package org.apache.derby.iapi.store.raw;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Hashtable;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.iapi.util.Matchable;

public final class ContainerKey implements Matchable, Lockable {
   private final long segmentId;
   private final long containerId;

   public ContainerKey(long var1, long var3) {
      this.segmentId = var1;
      this.containerId = var3;
   }

   public long getContainerId() {
      return this.containerId;
   }

   public long getSegmentId() {
      return this.segmentId;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      CompressedNumber.writeLong((DataOutput)var1, this.segmentId);
      CompressedNumber.writeLong((DataOutput)var1, this.containerId);
   }

   public static ContainerKey read(ObjectInput var0) throws IOException {
      long var1 = CompressedNumber.readLong((DataInput)var0);
      long var3 = CompressedNumber.readLong((DataInput)var0);
      return new ContainerKey(var1, var3);
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ContainerKey)) {
         return false;
      } else {
         ContainerKey var2 = (ContainerKey)var1;
         return this.containerId == var2.containerId && this.segmentId == var2.segmentId;
      }
   }

   public int hashCode() {
      return (int)(this.segmentId ^ this.containerId);
   }

   public String toString() {
      return "Container(" + this.segmentId + ", " + this.containerId + ")";
   }

   public boolean match(Object var1) {
      if (this.equals(var1)) {
         return true;
      } else if (var1 instanceof PageKey) {
         return this.equals(((PageKey)var1).getContainerId());
      } else {
         return var1 instanceof RecordHandle ? this.equals(((RecordHandle)var1).getContainerId()) : false;
      }
   }

   public void lockEvent(Latch var1) {
   }

   public boolean requestCompatible(Object var1, Object var2) {
      ContainerLock var3 = (ContainerLock)var1;
      ContainerLock var4 = (ContainerLock)var2;
      return var3.isCompatible(var4);
   }

   public boolean lockerAlwaysCompatible() {
      return true;
   }

   public void unlockEvent(Latch var1) {
   }

   public boolean lockAttributes(int var1, Hashtable var2) {
      if ((var1 & 2) == 0) {
         return false;
      } else {
         var2.put("CONTAINERID", this.getContainerId());
         var2.put("LOCKNAME", "Tablelock");
         var2.put("TYPE", "TABLE");
         return true;
      }
   }
}
