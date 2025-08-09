package org.apache.derby.impl.store.access;

import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.shared.common.error.StandardException;

class CacheableConglomerate implements Cacheable {
   private final RAMAccessManager accessManager;
   private Long conglomid;
   private Conglomerate conglom;

   CacheableConglomerate(RAMAccessManager var1) {
      this.accessManager = var1;
   }

   protected Conglomerate getConglom() {
      return this.conglom;
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      this.conglomid = (Long)var1;
      long var2 = this.conglomid;
      this.conglom = this.accessManager.getFactoryFromConglomId(var2).readConglomerate(this.accessManager.getCurrentTransactionContext().getTransaction(), new ContainerKey(0L, var2));
      return this;
   }

   public Cacheable createIdentity(Object var1, Object var2) throws StandardException {
      this.conglomid = (Long)var1;
      this.conglom = (Conglomerate)var2;
      return this;
   }

   public void clearIdentity() {
      this.conglomid = null;
      this.conglom = null;
   }

   public Object getIdentity() {
      return this.conglomid;
   }

   public boolean isDirty() {
      return false;
   }

   public void clean(boolean var1) throws StandardException {
   }
}
