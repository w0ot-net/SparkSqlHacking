package org.apache.derby.impl.services.monitor;

import java.util.Properties;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.io.WritableStorageFactory;
import org.apache.derby.shared.common.error.PassThroughException;
import org.apache.derby.shared.common.error.StandardException;

public class UpdateServiceProperties extends Properties {
   private PersistentService serviceType;
   private String serviceName;
   private volatile WritableStorageFactory storageFactory;
   private boolean serviceBooted;

   public UpdateServiceProperties(PersistentService var1, String var2, Properties var3, boolean var4) {
      super(var3);
      this.serviceType = var1;
      this.serviceName = var2;
      this.serviceBooted = var4;
   }

   public void setServiceBooted() {
      this.serviceBooted = true;
   }

   public void setStorageFactory(WritableStorageFactory var1) {
      this.storageFactory = var1;
   }

   public WritableStorageFactory getStorageFactory() {
      return this.storageFactory;
   }

   public Object put(Object var1, Object var2) {
      Object var3 = this.defaults.put(var1, var2);
      if (!((String)var1).startsWith("derby.__rt.")) {
         this.update();
      }

      return var3;
   }

   public Object remove(Object var1) {
      Object var2 = this.defaults.remove(var1);
      if (var2 != null && !((String)var1).startsWith("derby.__rt.")) {
         this.update();
      }

      return var2;
   }

   public void saveServiceProperties() {
      try {
         this.serviceType.saveServiceProperties(this.serviceName, this.storageFactory, BaseMonitor.removeRuntimeProperties(this.defaults), false);
      } catch (StandardException var2) {
         throw new PassThroughException(var2);
      }
   }

   private void update() {
      try {
         if (this.serviceBooted) {
            this.serviceType.saveServiceProperties(this.serviceName, this.storageFactory, BaseMonitor.removeRuntimeProperties(this.defaults), true);
         }

      } catch (StandardException var2) {
         throw new PassThroughException(var2);
      }
   }
}
