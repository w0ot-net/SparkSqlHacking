package org.apache.ivy.plugins.conflict;

import java.util.Collection;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.resolve.IvyNode;

public interface ConflictManager {
   Collection resolveConflicts(IvyNode var1, Collection var2);

   String getName();

   void handleAllBlacklistedRevisions(DependencyDescriptor var1, Collection var2);
}
