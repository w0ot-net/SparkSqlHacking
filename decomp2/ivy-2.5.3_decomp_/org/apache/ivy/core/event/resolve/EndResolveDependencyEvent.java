package org.apache.ivy.core.event.resolve;

import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public class EndResolveDependencyEvent extends ResolveDependencyEvent {
   public static final String NAME = "post-resolve-dependency";
   private ResolvedModuleRevision module;
   private long duration;

   public EndResolveDependencyEvent(DependencyResolver resolver, DependencyDescriptor dd, ModuleRevisionId requestedRevisionId, ResolvedModuleRevision module, long duration) {
      super("post-resolve-dependency", resolver, dd, requestedRevisionId);
      this.module = module;
      this.duration = duration;
      this.addAttribute("duration", String.valueOf(duration));
      if (this.module != null) {
         this.addAttribute("revision", this.module.getDescriptor().getResolvedModuleRevisionId().getRevision());
         this.addAttributes(this.module.getDescriptor().getResolvedModuleRevisionId().getQualifiedExtraAttributes());
         this.addAttributes(this.module.getDescriptor().getResolvedModuleRevisionId().getExtraAttributes());
         this.addAttribute("resolved", "true");
      } else {
         this.addAttribute("resolved", "false");
      }

   }

   public ResolvedModuleRevision getModule() {
      return this.module;
   }

   public long getDuration() {
      return this.duration;
   }
}
