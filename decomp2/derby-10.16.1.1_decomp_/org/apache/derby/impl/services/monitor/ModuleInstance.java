package org.apache.derby.impl.services.monitor;

import org.apache.derby.iapi.services.monitor.PersistentService;

class ModuleInstance {
   protected Object instance;
   protected String identifier;
   protected Object topLevelService;
   protected Object service;
   private boolean booted;

   protected ModuleInstance(Object var1, String var2, Object var3, Object var4) {
      this.instance = var1;
      this.identifier = var2;
      this.topLevelService = var4;
      this.service = var3;
   }

   protected ModuleInstance(Object var1) {
      this(var1, (String)null, (Object)null, (Object)null);
   }

   protected boolean isTypeAndName(PersistentService var1, Class var2, String var3) {
      if (!var2.isInstance(this.instance)) {
         return false;
      } else if (var1 != null && var3 != null) {
         return var1.isSameService(this.identifier, var3);
      } else {
         if (var3 != null) {
            if (this.identifier == null) {
               return false;
            }

            if (!var3.equals(this.identifier)) {
               return false;
            }
         } else if (this.identifier != null) {
            return false;
         }

         return true;
      }
   }

   protected String getIdentifier() {
      return this.identifier;
   }

   protected Object getTopLevelService() {
      return this.topLevelService;
   }

   protected Object getInstance() {
      return this.instance;
   }

   synchronized void setBooted() {
      this.booted = true;
   }

   synchronized boolean isBooted() {
      return this.booted;
   }
}
