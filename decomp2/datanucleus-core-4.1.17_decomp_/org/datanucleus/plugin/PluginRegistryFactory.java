package org.datanucleus.plugin;

import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class PluginRegistryFactory {
   public static PluginRegistry newPluginRegistry(String registryClassName, String registryBundleCheck, boolean allowUserBundles, ClassLoaderResolver clr) {
      PluginRegistry registry = null;
      if (registryClassName != null) {
         registry = newInstance(registryClassName, registryClassName, clr);
         if (registry != null) {
            if (NucleusLogger.GENERAL.isDebugEnabled()) {
               NucleusLogger.GENERAL.debug("Using PluginRegistry " + registry.getClass().getName());
            }

            return registry;
         }
      }

      if (NucleusLogger.GENERAL.isDebugEnabled()) {
         NucleusLogger.GENERAL.debug("Using PluginRegistry " + NonManagedPluginRegistry.class.getName());
      }

      return new NonManagedPluginRegistry(clr, registryBundleCheck, allowUserBundles);
   }

   private static PluginRegistry newInstance(String testClass, String registryClassName, ClassLoaderResolver clr) {
      try {
         if (clr.classForName(testClass, ClassConstants.NUCLEUS_CONTEXT_LOADER) == null && NucleusLogger.GENERAL.isDebugEnabled()) {
            NucleusLogger.GENERAL.debug(Localiser.msg("024005", registryClassName));
         }

         return (PluginRegistry)clr.classForName(registryClassName, ClassConstants.NUCLEUS_CONTEXT_LOADER).getConstructor(ClassConstants.CLASS_LOADER_RESOLVER).newInstance(clr);
      } catch (Exception var4) {
         return null;
      }
   }
}
