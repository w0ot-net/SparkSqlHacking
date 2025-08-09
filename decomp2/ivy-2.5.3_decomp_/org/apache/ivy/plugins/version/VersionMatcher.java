package org.apache.ivy.plugins.version;

import java.util.Comparator;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public interface VersionMatcher {
   boolean isDynamic(ModuleRevisionId var1);

   boolean accept(ModuleRevisionId var1, ModuleRevisionId var2);

   boolean needModuleDescriptor(ModuleRevisionId var1, ModuleRevisionId var2);

   boolean accept(ModuleRevisionId var1, ModuleDescriptor var2);

   int compare(ModuleRevisionId var1, ModuleRevisionId var2, Comparator var3);

   String getName();
}
