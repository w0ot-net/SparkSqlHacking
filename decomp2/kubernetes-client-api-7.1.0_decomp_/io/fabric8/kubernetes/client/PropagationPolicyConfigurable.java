package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.client.dsl.Deletable;

public interface PropagationPolicyConfigurable extends Deletable {
   Object withPropagationPolicy(DeletionPropagation var1);
}
