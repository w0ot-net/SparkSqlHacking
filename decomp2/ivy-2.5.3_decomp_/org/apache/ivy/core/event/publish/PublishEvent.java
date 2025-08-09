package org.apache.ivy.core.event.publish;

import java.io.File;
import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public abstract class PublishEvent extends IvyEvent {
   private final DependencyResolver resolver;
   private final Artifact artifact;
   private final File data;
   private final boolean overwrite;

   protected PublishEvent(String name, DependencyResolver resolver, Artifact artifact, File data, boolean overwrite) {
      super(name);
      this.resolver = resolver;
      this.artifact = artifact;
      this.data = data;
      this.overwrite = overwrite;
      this.addMridAttributes(artifact.getModuleRevisionId());
      this.addAttributes(artifact.getAttributes());
      this.addAttribute("resolver", resolver.getName());
      this.addAttribute("file", data.getAbsolutePath());
      this.addAttribute("overwrite", String.valueOf(overwrite));
   }

   public DependencyResolver getResolver() {
      return this.resolver;
   }

   public File getData() {
      return this.data;
   }

   public Artifact getArtifact() {
      return this.artifact;
   }

   public boolean isOverwrite() {
      return this.overwrite;
   }
}
