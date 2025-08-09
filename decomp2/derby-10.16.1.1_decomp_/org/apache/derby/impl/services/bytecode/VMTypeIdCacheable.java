package org.apache.derby.impl.services.bytecode;

import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.classfile.ClassHolder;

class VMTypeIdCacheable implements Cacheable {
   private Object descriptor;
   private Object key;

   public void clearIdentity() {
   }

   public Object getIdentity() {
      return this.key;
   }

   public Cacheable createIdentity(Object var1, Object var2) {
      return this;
   }

   public Cacheable setIdentity(Object var1) {
      this.key = var1;
      if (var1 instanceof String var2) {
         String var3 = ClassHolder.convertToInternalDescriptor(var2);
         this.descriptor = new Type(var2, var3);
      } else {
         this.descriptor = ((BCMethodDescriptor)var1).buildMethodDescriptor();
      }

      return this;
   }

   public void clean(boolean var1) {
   }

   public boolean isDirty() {
      return false;
   }

   Object descriptor() {
      return this.descriptor;
   }
}
