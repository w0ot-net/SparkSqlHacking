package org.apache.ivy.core.module.descriptor;

import org.apache.ivy.core.module.id.ModuleRevisionId;

public interface InheritableItem {
   ModuleRevisionId getSourceModule();
}
