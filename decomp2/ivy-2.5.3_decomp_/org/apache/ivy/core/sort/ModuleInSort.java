package org.apache.ivy.core.sort;

import java.util.LinkedList;
import java.util.List;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.circular.CircularDependencyHelper;
import org.apache.ivy.plugins.circular.CircularDependencyStrategy;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Message;

class ModuleInSort {
   private final ModuleDescriptor module;
   private boolean isSorted = false;
   private List loopElements = new LinkedList();
   private boolean isLoopIntermediateElement = false;
   private ModuleInSort caller;

   public ModuleInSort(ModuleDescriptor moduleToSort) {
      this.module = moduleToSort;
   }

   public boolean isInLoop() {
      return this.isLoopIntermediateElement;
   }

   public boolean isSorted() {
      if (this.isSorted) {
         Message.debug("Module descriptor already sorted : " + this.module.getModuleRevisionId().toString());
         return true;
      } else {
         return false;
      }
   }

   public boolean isProcessed() {
      if (!this.isSorted && !this.isLoopIntermediateElement) {
         return false;
      } else {
         Message.debug("Module descriptor is processed : " + this.module.getModuleRevisionId().toString());
         return true;
      }
   }

   public void setCaller(ModuleInSort caller) {
      this.caller = caller;
   }

   public void endOfCall() {
      this.caller = null;
   }

   public boolean checkLoop(ModuleInSort futurCaller, CircularDependencyStrategy depStrategy) {
      if (this.caller == null) {
         return false;
      } else {
         List<ModuleRevisionId> elemOfLoop = new LinkedList();
         elemOfLoop.add(this.module.getModuleRevisionId());

         for(ModuleInSort stackEl = futurCaller; stackEl != this; stackEl = stackEl.caller) {
            elemOfLoop.add(stackEl.module.getModuleRevisionId());
            stackEl.isLoopIntermediateElement = true;
            this.loopElements.add(stackEl);
         }

         elemOfLoop.add(this.module.getModuleRevisionId());
         ModuleRevisionId[] mrids = (ModuleRevisionId[])elemOfLoop.toArray(new ModuleRevisionId[elemOfLoop.size()]);
         depStrategy.handleCircularDependency(mrids);
         return true;
      }
   }

   public void addToSortedListIfRequired(List sorted) {
      if (!this.isLoopIntermediateElement) {
         this.addToSortList(sorted);
      }

   }

   private void addToSortList(List sortedList) {
      for(ModuleInSort moduleInLoop : this.loopElements) {
         moduleInLoop.addToSortList(sortedList);
      }

      if (!this.isSorted()) {
         sortedList.add(this.module);
         this.isSorted = true;
      }

   }

   public String toString() {
      return this.module.getModuleRevisionId().toString();
   }

   public DependencyDescriptor[] getDependencies() {
      return this.module.getDependencies();
   }

   public static void logLoopWarning(List loopElement) {
      Message.warn("circular dependency detected during sort: " + CircularDependencyHelper.formatMessageFromDescriptors(loopElement));
   }

   public boolean match(DependencyDescriptor descriptor, VersionMatcher versionMatcher) {
      ModuleDescriptor md = this.module;
      return md.getResolvedModuleRevisionId().getRevision() == null || md.getResolvedModuleRevisionId().getRevision().equals(Ivy.getWorkingRevision()) || versionMatcher.accept(descriptor.getDependencyRevisionId(), md);
   }

   public ModuleDescriptor getSortedModuleDescriptor() {
      return this.module;
   }
}
