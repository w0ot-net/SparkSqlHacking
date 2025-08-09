package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class LoggableActions implements PageActions {
   public void actionDelete(RawTransaction var1, BasePage var2, int var3, int var4, boolean var5, LogicalUndo var6) throws StandardException {
      DeleteOperation var7 = new DeleteOperation(var1, var2, var3, var4, var5, var6);
      this.doAction(var1, var2, var7);
   }

   public int actionUpdate(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, int var7, DynamicByteArrayOutputStream var8, int var9, RecordHandle var10) throws StandardException {
      UpdateOperation var11 = new UpdateOperation(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
      this.doAction(var1, var2, var11);
      return var11.getNextStartColumn();
   }

   public void actionPurge(RawTransaction var1, BasePage var2, int var3, int var4, int[] var5, boolean var6) throws StandardException {
      PurgeOperation var7 = new PurgeOperation(var1, var2, var3, var4, var5, var6);
      this.doAction(var1, var2, var7);
   }

   public void actionUpdateField(RawTransaction var1, BasePage var2, int var3, int var4, int var5, Object var6, LogicalUndo var7) throws StandardException {
      UpdateFieldOperation var8 = new UpdateFieldOperation(var1, var2, var3, var4, var5, var6, var7);
      this.doAction(var1, var2, var8);
   }

   public int actionInsert(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, LogicalUndo var7, byte var8, int var9, boolean var10, int var11, DynamicByteArrayOutputStream var12, int var13, int var14) throws StandardException {
      InsertOperation var15 = new InsertOperation(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14);
      this.doAction(var1, var2, var15);
      return var15.getNextStartColumn();
   }

   public void actionCopyRows(RawTransaction var1, BasePage var2, BasePage var3, int var4, int var5, int var6, int[] var7) throws StandardException {
      CopyRowsOperation var8 = new CopyRowsOperation(var1, var2, var3, var4, var5, var6, var7);
      this.doAction(var1, var2, var8);
   }

   public void actionInvalidatePage(RawTransaction var1, BasePage var2) throws StandardException {
      InvalidatePageOperation var3 = new InvalidatePageOperation(var2);
      this.doAction(var1, var2, var3);
   }

   public void actionInitPage(RawTransaction var1, BasePage var2, int var3, int var4, long var5) throws StandardException {
      InitPageOperation var7 = new InitPageOperation(var2, var3, var4, var5);
      this.doAction(var1, var2, var7);
   }

   public void actionShrinkReservedSpace(RawTransaction var1, BasePage var2, int var3, int var4, int var5, int var6) throws StandardException {
      SetReservedSpaceOperation var7 = new SetReservedSpaceOperation(var2, var3, var4, var5, var6);
      this.doAction(var1, var2, var7);
   }

   private void doAction(RawTransaction var1, BasePage var2, Loggable var3) throws StandardException {
      long var4 = 0L;
      Object var6 = null;
      var2.preDirty();
      var1.logAndDo(var3);
   }
}
