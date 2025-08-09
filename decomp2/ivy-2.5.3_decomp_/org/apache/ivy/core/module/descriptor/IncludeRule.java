package org.apache.ivy.core.module.descriptor;

import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.util.extendable.ExtendableItem;

public interface IncludeRule extends ExtendableItem {
   ArtifactId getId();

   String[] getConfigurations();

   PatternMatcher getMatcher();
}
