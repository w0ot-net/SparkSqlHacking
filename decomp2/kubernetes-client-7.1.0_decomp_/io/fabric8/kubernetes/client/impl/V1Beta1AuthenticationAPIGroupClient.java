package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.authentication.v1beta1.SelfSubjectReview;
import io.fabric8.kubernetes.client.dsl.InOutCreateable;
import io.fabric8.kubernetes.client.dsl.V1Beta1AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1Beta1AuthenticationAPIGroupClient extends ClientAdapter implements V1Beta1AuthenticationAPIGroupDSL {
   public InOutCreateable selfSubjectReview() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().getNonListingOperation(SelfSubjectReview.class, this);
   }

   public V1Beta1AuthenticationAPIGroupClient newInstance() {
      return new V1Beta1AuthenticationAPIGroupClient();
   }
}
