package org.apache.ivy.core.cache;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public interface ResolutionCacheManager {
   File getResolutionCacheRoot();

   File getResolvedIvyFileInCache(ModuleRevisionId var1);

   File getResolvedIvyPropertiesInCache(ModuleRevisionId var1);

   File getConfigurationResolveReportInCache(String var1, String var2);

   File[] getConfigurationResolveReportsInCache(String var1);

   ModuleDescriptor getResolvedModuleDescriptor(ModuleRevisionId var1) throws ParseException, IOException;

   void saveResolvedModuleDescriptor(ModuleDescriptor var1) throws ParseException, IOException;

   void clean();
}
