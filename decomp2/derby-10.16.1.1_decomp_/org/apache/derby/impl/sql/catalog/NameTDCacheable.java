package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class NameTDCacheable extends TDCacheable {
   private TableKey identity;

   NameTDCacheable(DataDictionaryImpl var1) {
      super(var1);
   }

   public void clearIdentity() {
      this.identity = null;
      this.td = null;
   }

   public Object getIdentity() {
      return this.identity;
   }

   public Cacheable createIdentity(Object var1, Object var2) {
      this.identity = (TableKey)var1;
      this.td = (TableDescriptor)var2;
      return this.td != null ? this : null;
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      this.td = this.dd.getUncachedTableDescriptor(this.identity = (TableKey)var1);
      if (this.td != null) {
         this.dd.addTableDescriptorToOtherCache(this.td, this);
         return this;
      } else {
         return null;
      }
   }
}
