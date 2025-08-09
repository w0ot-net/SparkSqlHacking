package org.apache.ivy.core.sort;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.plugins.circular.CircularDependencyException;
import org.apache.ivy.plugins.circular.CircularDependencyStrategy;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Message;

public class ModuleDescriptorSorter {
   private final CollectionOfModulesToSort moduleDescriptors;
   private final List sorted = new LinkedList();
   private final CircularDependencyStrategy circularDepStrategy;

   public ModuleDescriptorSorter(Collection modulesDescriptorsToSort, VersionMatcher matcher, NonMatchingVersionReporter nonMatchingVersionReporter, CircularDependencyStrategy circularDepStrategy) {
      this.circularDepStrategy = circularDepStrategy;
      this.moduleDescriptors = new CollectionOfModulesToSort(modulesDescriptorsToSort, matcher, nonMatchingVersionReporter);
   }

   public List sortModuleDescriptors() throws CircularDependencyException {
      Message.debug("Nbr of module to sort : " + this.moduleDescriptors.size());

      for(ModuleInSort m : this.moduleDescriptors) {
         this.sortModuleDescriptorsHelp(m, m);
      }

      return this.sorted;
   }

   private void sortModuleDescriptorsHelp(ModuleInSort current, ModuleInSort caller) throws CircularDependencyException {
      if (!current.isProcessed()) {
         if (!current.checkLoop(caller, this.circularDepStrategy)) {
            DependencyDescriptor[] descriptors = current.getDependencies();
            Message.debug("Sort dependencies of : " + current.toString() + " / Number of dependencies = " + descriptors.length);
            current.setCaller(caller);

            for(DependencyDescriptor descriptor : descriptors) {
               ModuleInSort child = this.moduleDescriptors.getModuleDescriptorDependency(descriptor);
               if (child != null) {
                  this.sortModuleDescriptorsHelp(child, current);
               }
            }

            current.endOfCall();
            Message.debug("Sort done for : " + current.toString());
            current.addToSortedListIfRequired(this.sorted);
         }
      }
   }
}
