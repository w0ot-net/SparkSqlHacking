package org.apache.ivy.core.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.plugins.circular.CircularDependencyException;
import org.apache.ivy.plugins.circular.CircularDependencyStrategy;
import org.apache.ivy.plugins.circular.IgnoreCircularDependencyStrategy;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Checks;

public class SortEngine {
   private SortEngineSettings settings;

   public SortEngine(SortEngineSettings settings) {
      if (settings == null) {
         throw new NullPointerException("SortEngine.settings can not be null");
      } else {
         this.settings = settings;
      }
   }

   public List sortNodes(Collection nodes, SortOptions options) {
      Map<ModuleDescriptor, List<IvyNode>> dependenciesMap = new LinkedHashMap();
      List<IvyNode> nulls = new ArrayList();

      for(IvyNode node : nodes) {
         if (node.getDescriptor() == null) {
            nulls.add(node);
         } else {
            List<IvyNode> n = (List)dependenciesMap.get(node.getDescriptor());
            if (n == null) {
               n = new ArrayList();
               dependenciesMap.put(node.getDescriptor(), n);
            }

            n.add(node);
         }
      }

      List<ModuleDescriptor> list = this.sortModuleDescriptors(dependenciesMap.keySet(), options);
      double adjustFactor = 1.3;
      List<IvyNode> ret = new ArrayList((int)((double)list.size() * 1.3 + (double)nulls.size()));

      for(ModuleDescriptor md : list) {
         List<IvyNode> n = (List)dependenciesMap.get(md);
         ret.addAll(n);
      }

      ret.addAll(0, nulls);
      return ret;
   }

   public List sortModuleDescriptors(Collection moduleDescriptors, SortOptions options) throws CircularDependencyException {
      Checks.checkNotNull(options, "options");
      ModuleDescriptorSorter sorter = new ModuleDescriptorSorter(moduleDescriptors, this.getVersionMatcher(), options.getNonMatchingVersionReporter(), options.isUseCircularDependencyStrategy() ? this.getCircularStrategy() : IgnoreCircularDependencyStrategy.getInstance());
      return sorter.sortModuleDescriptors();
   }

   protected CircularDependencyStrategy getCircularStrategy() {
      return this.settings.getCircularDependencyStrategy();
   }

   protected VersionMatcher getVersionMatcher() {
      return this.settings.getVersionMatcher();
   }
}
