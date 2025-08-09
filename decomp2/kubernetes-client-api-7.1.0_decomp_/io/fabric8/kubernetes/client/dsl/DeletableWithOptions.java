package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.GracePeriodConfigurable;
import io.fabric8.kubernetes.client.PropagationPolicyConfigurable;

public interface DeletableWithOptions extends GracePeriodConfigurable, PropagationPolicyConfigurable {
}
