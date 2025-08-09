package org.apache.ivy.plugins.namespace;

import org.apache.ivy.core.module.id.ModuleRevisionId;

public interface NamespaceTransformer {
   ModuleRevisionId transform(ModuleRevisionId var1);

   boolean isIdentity();
}
