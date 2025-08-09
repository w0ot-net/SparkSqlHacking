package org.apache.derby.iapi.services.loader;

import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.shared.common.error.StandardException;

public abstract class ClassFactoryContext extends ContextImpl {
   public static final String CONTEXT_ID = "ClassFactoryContext";
   private final ClassFactory cf;

   protected ClassFactoryContext(ContextManager var1, ClassFactory var2) {
      super(var1, "ClassFactoryContext");
      this.cf = var2;
   }

   public final ClassFactory getClassFactory() {
      return this.cf;
   }

   public abstract CompatibilitySpace getLockSpace() throws StandardException;

   public abstract PersistentSet getPersistentSet() throws StandardException;

   public abstract JarReader getJarReader();

   public final void cleanupOnError(Throwable var1) {
      if (var1 instanceof StandardException var2) {
         if (var2.getSeverity() >= 40000) {
            this.popMe();
         }
      }

   }
}
