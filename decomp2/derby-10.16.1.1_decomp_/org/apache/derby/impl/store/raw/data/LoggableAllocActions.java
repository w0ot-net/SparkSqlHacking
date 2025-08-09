package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class LoggableAllocActions implements AllocationActions {
   public void actionAllocatePage(RawTransaction var1, BasePage var2, long var3, int var5, int var6) throws StandardException {
      AllocPageOperation var7 = new AllocPageOperation((AllocPage)var2, var3, var5, var6);
      var2.preDirty();
      var1.logAndDo(var7);
   }

   public void actionChainAllocPage(RawTransaction var1, BasePage var2, long var3, long var5) throws StandardException {
      ChainAllocPageOperation var7 = new ChainAllocPageOperation((AllocPage)var2, var3, var5);
      var2.preDirty();
      var1.logAndDo(var7);
   }

   public void actionCompressSpaceOperation(RawTransaction var1, BasePage var2, int var3, int var4) throws StandardException {
      Object var5 = null;
      if (var1.getLogFactory().checkVersion(10, 3, (String)null)) {
         var5 = new CompressSpacePageOperation((AllocPage)var2, var3, var4);
      } else {
         var5 = new CompressSpacePageOperation10_2((AllocPage)var2, var3, var4);
      }

      var2.preDirty();
      var1.logAndDo((Loggable)var5);
   }
}
