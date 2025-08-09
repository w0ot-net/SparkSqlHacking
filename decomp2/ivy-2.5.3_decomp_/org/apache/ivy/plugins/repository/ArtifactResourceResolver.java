package org.apache.ivy.plugins.repository;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;

public interface ArtifactResourceResolver {
   ResolvedResource resolve(Artifact var1);
}
