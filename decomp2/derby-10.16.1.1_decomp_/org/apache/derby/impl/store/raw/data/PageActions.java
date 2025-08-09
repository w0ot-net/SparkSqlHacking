package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public interface PageActions {
   void actionDelete(RawTransaction var1, BasePage var2, int var3, int var4, boolean var5, LogicalUndo var6) throws StandardException;

   int actionUpdate(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, int var7, DynamicByteArrayOutputStream var8, int var9, RecordHandle var10) throws StandardException;

   void actionPurge(RawTransaction var1, BasePage var2, int var3, int var4, int[] var5, boolean var6) throws StandardException;

   void actionUpdateField(RawTransaction var1, BasePage var2, int var3, int var4, int var5, Object var6, LogicalUndo var7) throws StandardException;

   int actionInsert(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, LogicalUndo var7, byte var8, int var9, boolean var10, int var11, DynamicByteArrayOutputStream var12, int var13, int var14) throws StandardException;

   void actionCopyRows(RawTransaction var1, BasePage var2, BasePage var3, int var4, int var5, int var6, int[] var7) throws StandardException;

   void actionInvalidatePage(RawTransaction var1, BasePage var2) throws StandardException;

   void actionInitPage(RawTransaction var1, BasePage var2, int var3, int var4, long var5) throws StandardException;

   void actionShrinkReservedSpace(RawTransaction var1, BasePage var2, int var3, int var4, int var5, int var6) throws StandardException;
}
