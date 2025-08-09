package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class DirectActions implements PageActions {
   protected DynamicByteArrayOutputStream outBytes = new DynamicByteArrayOutputStream();
   protected ArrayInputStream limitIn = new ArrayInputStream();

   public void actionDelete(RawTransaction var1, BasePage var2, int var3, int var4, boolean var5, LogicalUndo var6) throws StandardException {
      try {
         var2.setDeleteStatus((LogInstant)null, var3, var5);
      } catch (IOException var8) {
         throw StandardException.newException("XSDA4.S", var8, new Object[0]);
      }
   }

   public int actionUpdate(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, int var7, DynamicByteArrayOutputStream var8, int var9, RecordHandle var10) throws StandardException {
      if (var8 == null) {
         this.outBytes.reset();
      } else {
         this.outBytes = var8;
      }

      try {
         int var11 = var2.logRow(var3, false, var4, var5, var6, this.outBytes, 0, (byte)8, var7, var9, 100);
         this.limitIn.setData(this.outBytes.getByteArray());
         this.limitIn.setPosition(this.outBytes.getBeginPosition());
         this.limitIn.setLimit(this.outBytes.getPosition() - this.outBytes.getBeginPosition());
         var2.storeRecord((LogInstant)null, var3, false, this.limitIn);
         return var11;
      } catch (IOException var12) {
         throw StandardException.newException("XSDA4.S", var12, new Object[0]);
      }
   }

   public void actionPurge(RawTransaction var1, BasePage var2, int var3, int var4, int[] var5, boolean var6) throws StandardException {
      try {
         for(int var7 = var4 - 1; var7 >= 0; --var7) {
            var2.purgeRecord((LogInstant)null, var3 + var7, var5[var7]);
         }

      } catch (IOException var8) {
         throw StandardException.newException("XSDA4.S", var8, new Object[0]);
      }
   }

   public void actionUpdateField(RawTransaction var1, BasePage var2, int var3, int var4, int var5, Object var6, LogicalUndo var7) throws StandardException {
      this.outBytes.reset();

      try {
         var2.logColumn(var3, var5, var6, this.outBytes, 100);
         this.limitIn.setData(this.outBytes.getByteArray());
         this.limitIn.setPosition(this.outBytes.getBeginPosition());
         this.limitIn.setLimit(this.outBytes.getPosition() - this.outBytes.getBeginPosition());
         var2.storeField((LogInstant)null, var3, var5, this.limitIn);
      } catch (IOException var9) {
         throw StandardException.newException("XSDA4.S", var9, new Object[0]);
      }
   }

   public int actionInsert(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, LogicalUndo var7, byte var8, int var9, boolean var10, int var11, DynamicByteArrayOutputStream var12, int var13, int var14) throws StandardException {
      if (var12 == null) {
         this.outBytes.reset();
      } else {
         this.outBytes = var12;
      }

      try {
         if (var10) {
            var9 = var2.logLongColumn(var3, var4, var5[0], this.outBytes);
         } else {
            var9 = var2.logRow(var3, true, var4, var5, var6, this.outBytes, var9, var8, var11, var13, var14);
         }

         this.limitIn.setData(this.outBytes.getByteArray());
         this.limitIn.setPosition(this.outBytes.getBeginPosition());
         this.limitIn.setLimit(this.outBytes.getPosition() - this.outBytes.getBeginPosition());
         var2.storeRecord((LogInstant)null, var3, true, this.limitIn);
         return var9;
      } catch (IOException var16) {
         throw StandardException.newException("XSDA4.S", var16, new Object[0]);
      }
   }

   public void actionCopyRows(RawTransaction var1, BasePage var2, BasePage var3, int var4, int var5, int var6, int[] var7) throws StandardException {
      try {
         int[] var8 = new int[var5];

         for(int var9 = 0; var9 < var5; ++var9) {
            this.outBytes.reset();
            var3.logRecord(var6 + var9, 0, var7[var9], (FormatableBitSet)null, this.outBytes, (RecordHandle)null);
            var8[var9] = this.outBytes.getUsed();
         }

         if (!var2.spaceForCopy(var5, var8)) {
            throw StandardException.newException("XSDA3.S", new Object[0]);
         } else {
            for(int var11 = 0; var11 < var5; ++var11) {
               this.outBytes.reset();
               var3.logRecord(var6 + var11, 0, var7[var11], (FormatableBitSet)null, this.outBytes, (RecordHandle)null);
               this.limitIn.setData(this.outBytes.getByteArray());
               this.limitIn.setPosition(this.outBytes.getBeginPosition());
               this.limitIn.setLimit(this.outBytes.getPosition() - this.outBytes.getBeginPosition());
               var2.storeRecord((LogInstant)null, var4 + var11, true, this.limitIn);
            }

         }
      } catch (IOException var10) {
         throw StandardException.newException("XSDA4.S", var10, new Object[0]);
      }
   }

   public void actionInvalidatePage(RawTransaction var1, BasePage var2) throws StandardException {
      var2.setPageStatus((LogInstant)null, (byte)2);
   }

   public void actionInitPage(RawTransaction var1, BasePage var2, int var3, int var4, long var5) throws StandardException {
      boolean var7 = (var3 & 2) != 0;
      boolean var8 = (var3 & 1) != 0;
      int var9 = (var3 & 4) == 0 ? var2.newRecordId() : 6;
      var2.initPage((LogInstant)null, (byte)1, var9, var7, var8);
   }

   public void actionShrinkReservedSpace(RawTransaction var1, BasePage var2, int var3, int var4, int var5, int var6) throws StandardException {
      try {
         var2.setReservedSpace((LogInstant)null, var3, var5);
      } catch (IOException var8) {
         throw StandardException.newException("XSDA4.S", var8, new Object[0]);
      }
   }
}
