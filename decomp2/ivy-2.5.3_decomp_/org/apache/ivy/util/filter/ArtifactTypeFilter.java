package org.apache.ivy.util.filter;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.ivy.core.module.descriptor.Artifact;

public class ArtifactTypeFilter implements Filter {
   private Collection acceptedTypes;

   public ArtifactTypeFilter(Collection acceptedTypes) {
      this.acceptedTypes = new ArrayList(acceptedTypes);
   }

   public boolean accept(Artifact art) {
      return this.acceptedTypes.contains(art.getType());
   }
}
