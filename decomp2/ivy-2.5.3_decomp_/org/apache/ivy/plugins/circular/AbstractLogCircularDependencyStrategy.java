package org.apache.ivy.plugins.circular;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolveData;

public abstract class AbstractLogCircularDependencyStrategy extends AbstractCircularDependencyStrategy {
   private final Collection circularDependencies = new HashSet();

   protected AbstractLogCircularDependencyStrategy(String name) {
      super(name);
   }

   public void handleCircularDependency(ModuleRevisionId[] mrids) {
      String circularDependencyId = this.getCircularDependencyId(mrids);
      if (!this.circularDependencies.contains(circularDependencyId)) {
         this.circularDependencies.add(circularDependencyId);
         this.logCircularDependency(mrids);
      }

   }

   protected abstract void logCircularDependency(ModuleRevisionId[] var1);

   protected String getCircularDependencyId(ModuleRevisionId[] mrids) {
      String contextPrefix = "";
      ResolveData data = IvyContext.getContext().getResolveData();
      if (data != null) {
         contextPrefix = data.getOptions().getResolveId() + " ";
      }

      return contextPrefix + Arrays.asList(mrids);
   }
}
