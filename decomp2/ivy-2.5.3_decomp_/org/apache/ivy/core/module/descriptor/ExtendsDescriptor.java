package org.apache.ivy.core.module.descriptor;

import org.apache.ivy.core.module.id.ModuleRevisionId;

public interface ExtendsDescriptor {
   ModuleRevisionId getParentRevisionId();

   ModuleRevisionId getResolvedParentRevisionId();

   ModuleDescriptor getParentMd();

   String getLocation();

   String[] getExtendsTypes();

   boolean isAllInherited();

   boolean isInfoInherited();

   boolean isDescriptionInherited();

   boolean areConfigurationsInherited();

   boolean areDependenciesInherited();

   boolean isLocal();
}
