package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public interface AllocationActions {
   void actionAllocatePage(RawTransaction var1, BasePage var2, long var3, int var5, int var6) throws StandardException;

   void actionChainAllocPage(RawTransaction var1, BasePage var2, long var3, long var5) throws StandardException;

   void actionCompressSpaceOperation(RawTransaction var1, BasePage var2, int var3, int var4) throws StandardException;
}
