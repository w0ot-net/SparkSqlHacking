package org.apache.ivy.core.deliver;

import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public interface PublishingDependencyRevisionResolver {
   String resolve(ModuleDescriptor var1, String var2, ModuleRevisionId var3, String var4);
}
