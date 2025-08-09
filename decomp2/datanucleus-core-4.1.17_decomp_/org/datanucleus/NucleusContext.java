package org.datanucleus;

import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.store.types.TypeManager;

public interface NucleusContext {
   void applyDefaultProperties(Configuration var1);

   void initialise();

   void close();

   ApiAdapter getApiAdapter();

   String getApiName();

   Configuration getConfiguration();

   PluginManager getPluginManager();

   MetaDataManager getMetaDataManager();

   TypeManager getTypeManager();

   ClassLoaderResolver getClassLoaderResolver(ClassLoader var1);

   boolean supportsORMMetaData();
}
