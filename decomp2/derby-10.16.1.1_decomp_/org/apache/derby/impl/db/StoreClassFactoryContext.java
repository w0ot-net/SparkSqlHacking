package org.apache.derby.impl.db;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassFactoryContext;
import org.apache.derby.iapi.services.loader.JarReader;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.shared.common.error.StandardException;

final class StoreClassFactoryContext extends ClassFactoryContext {
   private final AccessFactory store;
   private final JarReader jarReader;

   StoreClassFactoryContext(ContextManager var1, ClassFactory var2, AccessFactory var3, JarReader var4) {
      super(var1, var2);
      this.store = var3;
      this.jarReader = var4;
   }

   public CompatibilitySpace getLockSpace() throws StandardException {
      return this.store == null ? null : this.store.getTransaction(this.getContextManager()).getLockSpace();
   }

   public PersistentSet getPersistentSet() throws StandardException {
      return this.store == null ? null : this.store.getTransaction(this.getContextManager());
   }

   public JarReader getJarReader() {
      return this.jarReader;
   }
}
