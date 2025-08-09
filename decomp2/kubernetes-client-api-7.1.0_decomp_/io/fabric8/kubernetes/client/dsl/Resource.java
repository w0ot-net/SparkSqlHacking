package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.client.FromServerGettable;
import io.fabric8.kubernetes.client.GracePeriodConfigurable;
import io.fabric8.kubernetes.client.ResourceNotFoundException;

public interface Resource extends Scalable, FromServerGettable, WatchAndWaitable, WritableOperation, DryRunable, Informable {
   /** @deprecated */
   @Deprecated
   default GracePeriodConfigurable cascading(boolean enabled) {
      return (GracePeriodConfigurable)(!enabled ? (GracePeriodConfigurable)this.withPropagationPolicy(DeletionPropagation.ORPHAN) : this);
   }

   boolean isReady();

   Object require() throws ResourceNotFoundException;

   ReplaceDeletable lockResourceVersion();

   ReplaceDeletable lockResourceVersion(String var1);

   Object item();
}
