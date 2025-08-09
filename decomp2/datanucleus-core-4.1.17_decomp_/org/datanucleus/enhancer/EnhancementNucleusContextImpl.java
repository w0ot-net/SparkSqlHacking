package org.datanucleus.enhancer;

import java.util.Map;
import org.datanucleus.AbstractNucleusContext;
import org.datanucleus.plugin.PluginManager;

public class EnhancementNucleusContextImpl extends AbstractNucleusContext {
   public EnhancementNucleusContextImpl(String apiName, Map startupProps) {
      this(apiName, startupProps, (PluginManager)null);
   }

   public EnhancementNucleusContextImpl(String apiName, Map startupProps, PluginManager pluginMgr) {
      super(apiName, startupProps, pluginMgr);
   }

   public void close() {
      if (this.metaDataManager != null) {
         this.metaDataManager.close();
         this.metaDataManager = null;
      }

      if (this.classLoaderResolverMap != null) {
         this.classLoaderResolverMap.clear();
         this.classLoaderResolverMap = null;
      }

      if (this.typeManager != null) {
         this.typeManager = null;
      }

   }

   protected void logConfigurationDetails() {
   }
}
