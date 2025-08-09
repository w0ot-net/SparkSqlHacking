package org.apache.derby.impl.store.raw.data;

import java.util.Hashtable;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.RowLock;

public final class RecordId implements RecordHandle {
   private final PageKey pageId;
   private final int recordId;
   private transient int slotNumberHint;

   public RecordId(ContainerKey var1, long var2, int var4) {
      this.pageId = new PageKey(var1, var2);
      this.recordId = var4;
   }

   public RecordId(PageKey var1, int var2) {
      this.pageId = var1;
      this.recordId = var2;
   }

   public RecordId(PageKey var1, int var2, int var3) {
      this.pageId = var1;
      this.recordId = var2;
      this.slotNumberHint = var3;
   }

   public int getId() {
      return this.recordId;
   }

   public long getPageNumber() {
      return this.pageId.getPageNumber();
   }

   public Object getPageId() {
      return this.pageId;
   }

   public ContainerKey getContainerId() {
      return this.pageId.getContainerId();
   }

   public int getSlotNumberHint() {
      return this.slotNumberHint;
   }

   public void lockEvent(Latch var1) {
   }

   public boolean requestCompatible(Object var1, Object var2) {
      RowLock var3 = (RowLock)var1;
      RowLock var4 = (RowLock)var2;
      return var3.isCompatible(var4);
   }

   public boolean lockerAlwaysCompatible() {
      return true;
   }

   public void unlockEvent(Latch var1) {
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof RecordId var2)) {
         return false;
      } else {
         return this.recordId == var2.recordId && this.pageId.equals(var2.pageId);
      }
   }

   public int hashCode() {
      int var1 = 7;
      var1 = 89 * var1 + this.pageId.hashCode();
      var1 = 89 * var1 + this.recordId;
      return var1;
   }

   public String toString() {
      return null;
   }

   public boolean lockAttributes(int var1, Hashtable var2) {
      if ((var1 & 2) == 0) {
         return false;
      } else {
         var2.put("CONTAINERID", this.pageId.getContainerId().getContainerId());
         long var10002 = this.pageId.getPageNumber();
         var2.put("LOCKNAME", "(" + var10002 + "," + this.recordId + ")");
         var2.put("TYPE", "ROW");
         return true;
      }
   }
}
