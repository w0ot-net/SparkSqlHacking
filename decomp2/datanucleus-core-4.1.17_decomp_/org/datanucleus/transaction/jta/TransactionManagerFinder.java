package org.datanucleus.transaction.jta;

import javax.transaction.TransactionManager;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.plugin.PluginManager;

public class TransactionManagerFinder {
   NucleusContext nucleusContext;

   public TransactionManagerFinder(NucleusContext ctx) {
      this.nucleusContext = ctx;
   }

   public TransactionManager getTransactionManager(ClassLoaderResolver clr) {
      String jtaLocatorName = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.jtaLocator");
      PluginManager pluginMgr = this.nucleusContext.getPluginManager();
      if (jtaLocatorName != null) {
         try {
            TransactionManagerLocator locator = (TransactionManagerLocator)pluginMgr.createExecutableExtension("org.datanucleus.jta_locator", "name", jtaLocatorName, "class-name", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this.nucleusContext});
            return locator.getTransactionManager(clr);
         } catch (Exception var9) {
         }
      } else {
         String[] locatorNames = pluginMgr.getAttributeValuesForExtension("org.datanucleus.jta_locator", (String)null, (String)null, "name");
         if (locatorNames != null) {
            for(int i = 0; i < locatorNames.length; ++i) {
               try {
                  TransactionManagerLocator locator = (TransactionManagerLocator)pluginMgr.createExecutableExtension("org.datanucleus.jta_locator", "name", locatorNames[i], "class-name", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this.nucleusContext});
                  if (locator != null) {
                     TransactionManager tm = locator.getTransactionManager(clr);
                     if (tm != null) {
                        return tm;
                     }
                  }
               } catch (Exception var8) {
               }
            }
         }
      }

      return null;
   }
}
