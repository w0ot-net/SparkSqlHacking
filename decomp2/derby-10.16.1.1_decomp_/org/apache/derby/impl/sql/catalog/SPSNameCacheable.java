package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class SPSNameCacheable implements Cacheable {
   private TableKey identity;
   private SPSDescriptor spsd;
   private final DataDictionaryImpl dd;

   SPSNameCacheable(DataDictionaryImpl var1) {
      this.dd = var1;
   }

   public void clearIdentity() {
      if (this.spsd != null) {
         this.dd.spsCacheEntryRemoved(this.spsd);
         this.spsd = null;
         this.identity = null;
      }

   }

   public Object getIdentity() {
      return this.identity;
   }

   public Cacheable createIdentity(Object var1, Object var2) {
      this.identity = (TableKey)var1;
      this.spsd = (SPSDescriptor)var2;
      if (this.spsd != null) {
         this.dd.spsCacheEntryAdded(this.spsd);

         try {
            this.spsd.loadGeneratedClass();
         } catch (StandardException var4) {
         }

         return this;
      } else {
         return null;
      }
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      this.identity = (TableKey)var1;
      this.spsd = this.dd.getUncachedSPSDescriptor(this.identity);
      if (this.spsd != null) {
         this.dd.spsCacheEntryAdded(this.spsd);

         try {
            this.spsd.loadGeneratedClass();
         } catch (StandardException var3) {
         }

         return this;
      } else {
         return null;
      }
   }

   public void clean(boolean var1) {
   }

   public boolean isDirty() {
      return false;
   }

   public SPSDescriptor getSPSDescriptor() {
      return this.spsd;
   }
}
