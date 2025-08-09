package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

abstract class TDCacheable implements Cacheable {
   protected TableDescriptor td;
   protected final DataDictionaryImpl dd;

   TDCacheable(DataDictionaryImpl var1) {
      this.dd = var1;
   }

   public void clean(boolean var1) {
   }

   public boolean isDirty() {
      return false;
   }

   public TableDescriptor getTableDescriptor() {
      return this.td;
   }

   protected boolean checkConsistency(TableDescriptor var1, Object var2, HeaderPrintWriter var3) throws StandardException {
      boolean var4 = true;
      return var4;
   }
}
