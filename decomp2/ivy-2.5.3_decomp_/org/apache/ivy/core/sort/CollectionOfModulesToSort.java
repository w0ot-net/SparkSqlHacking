package org.apache.ivy.core.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.plugins.version.VersionMatcher;

class CollectionOfModulesToSort implements Iterable {
   private final List moduleDescriptors;
   private final VersionMatcher versionMatcher;
   private final Map modulesByModuleId;
   private final NonMatchingVersionReporter nonMatchingVersionReporter;

   public CollectionOfModulesToSort(Collection modulesToSort, VersionMatcher matcher, NonMatchingVersionReporter nonMatchingVersionReporter) {
      this.versionMatcher = matcher;
      this.nonMatchingVersionReporter = nonMatchingVersionReporter;
      this.modulesByModuleId = new HashMap();
      this.moduleDescriptors = new ArrayList(modulesToSort.size());

      for(ModuleDescriptor md : modulesToSort) {
         ModuleInSort mdInSort = new ModuleInSort(md);
         this.moduleDescriptors.add(mdInSort);
         this.addToModulesByModuleId(md, mdInSort);
      }

   }

   private void addToModulesByModuleId(ModuleDescriptor md, ModuleInSort mdInSort) {
      ModuleId mdId = md.getModuleRevisionId().getModuleId();
      List<ModuleInSort> mdInSortAsList = new LinkedList();
      mdInSortAsList.add(mdInSort);
      Collection<ModuleInSort> previousList = (Collection)this.modulesByModuleId.put(mdId, mdInSortAsList);
      if (previousList != null) {
         mdInSortAsList.addAll(previousList);
      }

   }

   public Iterator iterator() {
      return this.moduleDescriptors.iterator();
   }

   public int size() {
      return this.moduleDescriptors.size();
   }

   public ModuleInSort getModuleDescriptorDependency(DependencyDescriptor descriptor) {
      Collection<ModuleInSort> modulesOfSameId = (Collection)this.modulesByModuleId.get(descriptor.getDependencyId());
      if (modulesOfSameId == null) {
         return null;
      } else {
         for(ModuleInSort mdInSort : modulesOfSameId) {
            if (mdInSort.match(descriptor, this.versionMatcher)) {
               return mdInSort;
            }

            this.nonMatchingVersionReporter.reportNonMatchingVersion(descriptor, mdInSort.getSortedModuleDescriptor());
         }

         return null;
      }
   }
}
