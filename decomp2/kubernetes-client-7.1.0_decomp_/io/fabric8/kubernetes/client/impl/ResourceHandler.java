package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;

public interface ResourceHandler {
   VisitableBuilder edit(HasMetadata var1);

   HasMetadataOperation operation(Client var1, Class var2);

   boolean hasOperation();
}
