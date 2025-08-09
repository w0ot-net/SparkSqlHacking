package org.apache.derby.impl.store.access.heap;

import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.conglomerate.OpenConglomerate;
import org.apache.derby.shared.common.error.StandardException;

class OpenHeap extends OpenConglomerate {
   public int[] getFormatIds() {
      return ((Heap)this.getConglomerate()).format_ids;
   }

   public RowLocation newRowLocationTemplate() throws StandardException {
      if (this.getContainer() == null) {
         throw StandardException.newException("XSCH6.S", new Object[]{this.getConglomerate().getId()});
      } else {
         return new HeapRowLocation();
      }
   }
}
