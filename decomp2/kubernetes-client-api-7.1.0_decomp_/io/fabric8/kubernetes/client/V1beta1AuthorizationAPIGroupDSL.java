package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.InOutCreateable;
import io.fabric8.kubernetes.client.dsl.NamespacedInOutCreateable;

public interface V1beta1AuthorizationAPIGroupDSL extends Client {
   InOutCreateable selfSubjectAccessReview();

   InOutCreateable subjectAccessReview();

   NamespacedInOutCreateable localSubjectAccessReview();

   InOutCreateable selfSubjectRulesReview();
}
