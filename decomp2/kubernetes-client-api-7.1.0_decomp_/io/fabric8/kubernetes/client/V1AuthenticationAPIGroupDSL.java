package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.InOutCreateable;

public interface V1AuthenticationAPIGroupDSL extends Client {
   InOutCreateable tokenReviews();

   InOutCreateable selfSubjectReviews();
}
