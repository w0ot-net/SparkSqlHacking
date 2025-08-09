package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollback;

public interface RollableScalableResource extends ScalableResource, ImageUpdateable {
   TimeoutImageEditReplacePatchable rolling();

   Status rollback(DeploymentRollback var1);
}
