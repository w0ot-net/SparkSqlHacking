package org.apache.ivy.core.module.descriptor;

import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.util.extendable.ExtendableItem;

public interface DependencyDescriptor extends ExtendableItem, InheritableItem {
   ModuleId getDependencyId();

   default ModuleDescriptor getModuleDescriptor() {
      return null;
   }

   boolean isForce();

   boolean isChanging();

   boolean isTransitive();

   ModuleRevisionId getParentRevisionId();

   ModuleRevisionId getDependencyRevisionId();

   ModuleRevisionId getDynamicConstraintDependencyRevisionId();

   String[] getModuleConfigurations();

   String[] getDependencyConfigurations(String var1, String var2);

   String[] getDependencyConfigurations(String var1);

   String[] getDependencyConfigurations(String[] var1);

   Namespace getNamespace();

   DependencyArtifactDescriptor[] getAllDependencyArtifacts();

   DependencyArtifactDescriptor[] getDependencyArtifacts(String var1);

   DependencyArtifactDescriptor[] getDependencyArtifacts(String[] var1);

   IncludeRule[] getAllIncludeRules();

   IncludeRule[] getIncludeRules(String var1);

   IncludeRule[] getIncludeRules(String[] var1);

   ExcludeRule[] getAllExcludeRules();

   ExcludeRule[] getExcludeRules(String var1);

   ExcludeRule[] getExcludeRules(String[] var1);

   boolean doesExclude(String[] var1, ArtifactId var2);

   boolean canExclude();

   DependencyDescriptor asSystem();

   DependencyDescriptor clone(ModuleRevisionId var1);
}
