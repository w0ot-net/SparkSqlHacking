package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class DirectAllocActions implements AllocationActions {
   public void actionAllocatePage(RawTransaction var1, BasePage var2, long var3, int var5, int var6) throws StandardException {
      ((AllocPage)var2).setPageStatus((LogInstant)null, var3, var5);
   }

   public void actionChainAllocPage(RawTransaction var1, BasePage var2, long var3, long var5) throws StandardException {
      ((AllocPage)var2).chainNextAllocPage((LogInstant)null, var3, var5);
   }

   public void actionCompressSpaceOperation(RawTransaction var1, BasePage var2, int var3, int var4) throws StandardException {
      ((AllocPage)var2).compressSpace((LogInstant)null, var3, var4);
   }
}
