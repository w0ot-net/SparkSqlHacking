package org.apache.derby.impl.store.access.btree.index;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.LogicalUndoable;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.ControlRow;
import org.apache.derby.impl.store.access.btree.OpenBTree;
import org.apache.derby.impl.store.access.btree.SearchParameters;
import org.apache.derby.shared.common.error.StandardException;

public class B2IUndo implements LogicalUndo, Formatable {
   public Page findUndo(Transaction var1, LogicalUndoable var2, LimitObjectInput var3) throws StandardException, IOException {
      ControlRow var4 = null;
      ControlRow var5 = null;
      Object var6 = null;
      Object var7 = null;
      Page var8 = null;
      ContainerHandle var9 = var2.getContainer();
      RecordHandle var10 = var2.getRecordHandle();
      boolean var11 = false;
      int var12 = 1;
      B2I var13 = null;

      try {
         var4 = ControlRow.get(var9, 1L);
         var13 = (B2I)var4.getConglom(470);
         var25 = var13.createTemplate(var1);
         var26 = var13.createTemplate(var1);
      } finally {
         if (var4 != null) {
            var4.release();
         }

      }

      var2.restoreLoggedRow(var25, var3);
      var11 = false;

      try {
         OpenBTree var14 = new OpenBTree();
         var14.init((TransactionManager)null, (TransactionManager)null, var2.getContainer(), var1, false, 4, 5, (BTreeLockingPolicy)null, var13, (LogicalUndo)null, (DynamicCompiledOpenConglomInfo)null);
         var5 = ControlRow.get(var14, var10.getPageNumber());
         var12 = 1;
         if (var5.getPage().recordExists(var10, true)) {
            RecordHandle var15 = var5.getPage().fetchFromSlot((RecordHandle)null, var5.getPage().getSlotNumber(var10), var26, (FetchDescriptor)null, true);
            var12 = ControlRow.compareIndexRowToKey(var26, var25, var25.length, 1, var14.getColumnSortOrderInfo());
         }

         if (var12 == 0) {
            var8 = var5.getPage();
         } else {
            SearchParameters var31 = new SearchParameters(var25, 1, var26, var14, false);
            var5.release();
            Object var24 = null;
            var5 = ControlRow.get(var14, 1L).search(var31);
            if (!var31.resultExact) {
               throw StandardException.newException("XSCB5.S", new Object[0]);
            }

            RecordHandle var16 = var5.getPage().fetchFromSlot((RecordHandle)null, var31.resultSlot, new DataValueDescriptor[0], (FetchDescriptor)null, true);
            var2.resetRecordHandle(var16);
            var8 = var5.getPage();
         }

         var11 = true;
      } finally {
         if (!var11 && var5 != null) {
            var5.release();
         }

      }

      return var8;
   }

   public int getTypeFormatId() {
      return 95;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
   }
}
