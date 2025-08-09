package org.apache.ivy.core.event.publish;

import java.io.File;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public class StartArtifactPublishEvent extends PublishEvent {
   public static final String NAME = "pre-publish-artifact";

   public StartArtifactPublishEvent(DependencyResolver resolver, Artifact artifact, File data, boolean overwrite) {
      super("pre-publish-artifact", resolver, artifact, data, overwrite);
   }
}
