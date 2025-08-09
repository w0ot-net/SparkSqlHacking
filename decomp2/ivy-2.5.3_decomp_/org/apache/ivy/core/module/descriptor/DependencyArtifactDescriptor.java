package org.apache.ivy.core.module.descriptor;

import java.net.URL;
import org.apache.ivy.util.extendable.ExtendableItem;

public interface DependencyArtifactDescriptor extends ExtendableItem {
   DependencyDescriptor getDependencyDescriptor();

   String getName();

   String getType();

   String getExt();

   URL getUrl();

   String[] getConfigurations();
}
