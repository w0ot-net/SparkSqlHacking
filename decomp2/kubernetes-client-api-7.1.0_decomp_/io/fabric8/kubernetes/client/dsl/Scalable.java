package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.autoscaling.v1.Scale;

public interface Scalable {
   Object scale(int var1);

   /** @deprecated */
   @Deprecated
   Object scale(int var1, boolean var2);

   default Scale scale() {
      return this.scale((Scale)null);
   }

   Scale scale(Scale var1);
}
