package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.policy.v1.Eviction;
import java.util.Map;

public interface PodResource extends Resource, Loggable, Containerable, ContainerResource, EphemeralContainersResource, PortForwardable {
   boolean evict();

   boolean evict(Eviction var1);

   EphemeralContainersResource ephemeralContainers();

   Pod patchReadinessGateStatus(Map var1);
}
