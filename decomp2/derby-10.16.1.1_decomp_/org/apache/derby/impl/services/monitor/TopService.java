package org.apache.derby.impl.services.monitor;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;

final class TopService {
   ProtocolKey key;
   ModuleInstance topModule;
   Hashtable protocolTable;
   Vector moduleInstances;
   BaseMonitor monitor;
   boolean inShutdown;
   PersistentService serviceType;
   Locale serviceLocale;

   TopService(BaseMonitor var1) {
      this.monitor = var1;
      this.protocolTable = new Hashtable();
      this.moduleInstances = new Vector(0, 5);
   }

   TopService(BaseMonitor var1, ProtocolKey var2, PersistentService var3, Locale var4) {
      this(var1);
      this.key = var2;
      this.serviceType = var3;
      this.serviceLocale = var4;
   }

   void setTopModule(Object var1) {
      synchronized(this) {
         ModuleInstance var3 = this.findModuleInstance(var1);
         if (var3 != null) {
            this.topModule = var3;
            this.notifyAll();
         }

         if (this.getServiceType() != null) {
            ProtocolKey var4 = new ProtocolKey(this.key.getFactoryInterface(), this.monitor.getServiceName(var1));
            this.addToProtocol(var4, this.topModule);
         }

      }
   }

   Object getService() {
      return this.topModule.getInstance();
   }

   boolean isPotentialService(ProtocolKey var1) {
      String var2;
      if (this.serviceType == null) {
         var2 = var1.getIdentifier();
      } else {
         try {
            var2 = this.serviceType.getCanonicalServiceName(var1.getIdentifier());
         } catch (StandardException var4) {
            return false;
         }

         if (var2 == null) {
            return false;
         }
      }

      if (this.topModule != null) {
         return this.topModule.isTypeAndName(this.serviceType, this.key.getFactoryInterface(), var2);
      } else {
         return !var1.getFactoryInterface().isAssignableFrom(this.key.getFactoryInterface()) ? false : this.serviceType.isSameService(this.key.getIdentifier(), var2);
      }
   }

   boolean isActiveService() {
      synchronized(this) {
         return this.topModule != null;
      }
   }

   boolean isActiveService(ProtocolKey var1) {
      synchronized(this) {
         if (this.inShutdown) {
            return false;
         } else if (!this.isPotentialService(var1)) {
            return false;
         } else if (this.topModule != null) {
            return true;
         } else {
            while(!this.inShutdown && this.topModule == null) {
               try {
                  this.wait();
               } catch (InterruptedException var5) {
                  InterruptStatus.setInterrupted();
               }
            }

            return !this.inShutdown;
         }
      }
   }

   synchronized Object findModule(ProtocolKey var1, boolean var2, Properties var3) {
      ModuleInstance var4 = (ModuleInstance)this.protocolTable.get(var1);
      if (var4 == null) {
         return null;
      } else {
         Object var5 = var4.getInstance();
         return !var2 && !BaseMonitor.canSupport(var5, var3) ? null : var5;
      }
   }

   private ModuleInstance findModuleInstance(Object var1) {
      synchronized(this.moduleInstances) {
         for(int var3 = 0; var3 < this.moduleInstances.size(); ++var3) {
            ModuleInstance var4 = (ModuleInstance)this.moduleInstances.get(var3);
            if (var4.getInstance() == var1) {
               return var4;
            }
         }

         return null;
      }
   }

   Object bootModule(boolean var1, Object var2, ProtocolKey var3, Properties var4) throws StandardException {
      synchronized(this) {
         if (this.inShutdown) {
            throw StandardException.newException("08006.D", new Object[]{this.getKey().getIdentifier()});
         }
      }

      Object var5 = this.findModule(var3, false, var4);
      if (var5 != null) {
         return var5;
      } else {
         if (this.monitor.reportOn) {
            BaseMonitor var10000 = this.monitor;
            String var10001 = var3.toString();
            var10000.report("Booting Module   " + var10001 + " create = " + var1);
         }

         synchronized(this) {
            int var7 = 0;

            while(true) {
               ModuleInstance var8;
               synchronized(this.moduleInstances) {
                  if (var7 >= this.moduleInstances.size()) {
                     break;
                  }

                  var8 = (ModuleInstance)this.moduleInstances.get(var7);
               }

               if (var8.isBooted() && var8.isTypeAndName((PersistentService)null, var3.getFactoryInterface(), var3.getIdentifier())) {
                  var5 = var8.getInstance();
                  if (BaseMonitor.canSupport(var5, var4) && this.addToProtocol(var3, var8)) {
                     if (this.monitor.reportOn) {
                        this.monitor.report("Started Module   " + var3.toString());
                        this.monitor.report("  Implementation " + var5.getClass().getName());
                     }

                     return var5;
                  }
               }

               ++var7;
            }
         }

         var5 = this.monitor.loadInstance(var3.getFactoryInterface(), var4);
         if (var5 == null) {
            throw Monitor.missingImplementation(var3.getFactoryInterface().getName());
         } else {
            ModuleInstance var6 = new ModuleInstance(var5, var3.getIdentifier(), var2, this.topModule == null ? null : this.topModule.getInstance());
            this.moduleInstances.add(var6);

            try {
               BaseMonitor.boot(var5, var1, var4);
            } catch (StandardException var13) {
               this.moduleInstances.remove(var6);
               throw var13;
            }

            var6.setBooted();
            synchronized(this) {
               if (this.addToProtocol(var3, var6)) {
                  if (this.monitor.reportOn) {
                     this.monitor.report("Started Module   " + var3.toString());
                     this.monitor.report("  Implementation " + var6.getInstance().getClass().getName());
                  }

                  return var6.getInstance();
               }
            }

            stop(var5);
            this.moduleInstances.remove(var6);
            return this.findModule(var3, true, var4);
         }
      }
   }

   boolean shutdown() {
      synchronized(this) {
         if (this.inShutdown) {
            return false;
         }

         this.inShutdown = true;
         this.notifyAll();
      }

      while(true) {
         ModuleInstance var1;
         synchronized(this) {
            if (this.moduleInstances.isEmpty()) {
               return true;
            }

            var1 = (ModuleInstance)this.moduleInstances.get(0);
         }

         Object var2 = var1.getInstance();
         stop(var2);
         synchronized(this) {
            this.moduleInstances.remove(0);
         }
      }
   }

   private boolean addToProtocol(ProtocolKey var1, ModuleInstance var2) {
      String var3 = var2.getIdentifier();
      synchronized(this) {
         Object var5 = this.protocolTable.get(var1);
         if (var5 == null) {
            this.protocolTable.put(var1, var2);
            return true;
         } else {
            return var5 == var2;
         }
      }
   }

   boolean inService(Object var1) {
      return this.findModuleInstance(var1) != null;
   }

   public ProtocolKey getKey() {
      return this.key;
   }

   PersistentService getServiceType() {
      return this.serviceType;
   }

   private static void stop(Object var0) {
      if (var0 instanceof ModuleControl) {
         ((ModuleControl)var0).stop();
      }

   }
}
