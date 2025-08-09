package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface V1Beta1AuthenticationAPIGroupDSL extends Client {
   InOutCreateable selfSubjectReview();
}
