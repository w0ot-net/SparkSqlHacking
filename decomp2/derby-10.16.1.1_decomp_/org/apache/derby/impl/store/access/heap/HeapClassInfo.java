package org.apache.derby.impl.store.access.heap;

import org.apache.derby.iapi.services.io.FormatableInstanceGetter;

public class HeapClassInfo extends FormatableInstanceGetter {
   public Object getNewInstance() {
      return new HeapRowLocation();
   }
}
