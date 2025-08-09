package org.apache.ivy.core.event.resolve;

import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public class ResolveDependencyEvent extends IvyEvent {
   private DependencyResolver resolver;
   private DependencyDescriptor dd;

   protected ResolveDependencyEvent(String name, DependencyResolver resolver, DependencyDescriptor dd, ModuleRevisionId requestedRevisionId) {
      super(name);
      this.resolver = resolver;
      this.dd = dd;
      this.addAttribute("resolver", this.resolver.getName());
      this.addMridAttributes(this.dd.getDependencyRevisionId());
      this.addAttributes(this.dd.getQualifiedExtraAttributes());
      this.addAttributes(this.dd.getExtraAttributes());
      this.addAttribute("req-revision", requestedRevisionId.getRevision());
      this.addAttribute("req-revision-default", dd.getDependencyRevisionId().getRevision());
      this.addAttribute("req-revision-dynamic", dd.getDynamicConstraintDependencyRevisionId().getRevision());
      this.addAttribute("req-branch", requestedRevisionId.getBranch());
      this.addAttribute("req-branch-default", dd.getDependencyRevisionId().getBranch());
   }

   public DependencyDescriptor getDependencyDescriptor() {
      return this.dd;
   }

   public DependencyResolver getResolver() {
      return this.resolver;
   }
}
