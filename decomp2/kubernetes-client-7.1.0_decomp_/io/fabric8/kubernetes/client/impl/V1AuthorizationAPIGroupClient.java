package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.authorization.v1.LocalSubjectAccessReview;
import io.fabric8.kubernetes.api.model.authorization.v1.SelfSubjectAccessReview;
import io.fabric8.kubernetes.api.model.authorization.v1.SelfSubjectRulesReview;
import io.fabric8.kubernetes.api.model.authorization.v1.SubjectAccessReview;
import io.fabric8.kubernetes.client.V1AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.InOutCreateable;
import io.fabric8.kubernetes.client.dsl.NamespacedInOutCreateable;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1AuthorizationAPIGroupClient extends ClientAdapter implements V1AuthorizationAPIGroupDSL {
   public static final String AUTHORIZATION_APIGROUP = "authorization.k8s.io";
   public static final String AUTHORIZATION_APIVERSION = "v1";

   public InOutCreateable selfSubjectAccessReview() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().getNonListingOperation(SelfSubjectAccessReview.class, this);
   }

   public InOutCreateable subjectAccessReview() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().getNonListingOperation(SubjectAccessReview.class, this);
   }

   public NamespacedInOutCreateable localSubjectAccessReview() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().getNamespacedHasMetadataCreateOnlyOperation(LocalSubjectAccessReview.class, this);
   }

   public InOutCreateable selfSubjectRulesReview() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().getNonListingOperation(SelfSubjectRulesReview.class, this);
   }

   public V1AuthorizationAPIGroupClient newInstance() {
      return new V1AuthorizationAPIGroupClient();
   }
}
