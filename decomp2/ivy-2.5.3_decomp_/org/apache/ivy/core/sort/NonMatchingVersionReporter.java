package org.apache.ivy.core.sort;

import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;

public interface NonMatchingVersionReporter {
   void reportNonMatchingVersion(DependencyDescriptor var1, ModuleDescriptor var2);
}
