package org.apache.ivy.core.event.download;

import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public class StartArtifactDownloadEvent extends DownloadEvent {
   public static final String NAME = "pre-download-artifact";
   private DependencyResolver resolver;
   private ArtifactOrigin origin;

   public StartArtifactDownloadEvent(DependencyResolver resolver, Artifact artifact, ArtifactOrigin origin) {
      super("pre-download-artifact", artifact);
      this.resolver = resolver;
      this.origin = origin;
      this.addAttribute("resolver", this.resolver.getName());
      this.addAttribute("origin", origin.getLocation());
      this.addAttribute("local", String.valueOf(origin.isLocal()));
   }

   public DependencyResolver getResolver() {
      return this.resolver;
   }

   public ArtifactOrigin getOrigin() {
      return this.origin;
   }
}
