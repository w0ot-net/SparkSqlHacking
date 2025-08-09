package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.authentication.SelfSubjectReview;
import io.fabric8.kubernetes.api.model.authentication.TokenReview;
import io.fabric8.kubernetes.client.V1AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.InOutCreateable;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1AuthenticationAPIGroupClient extends ClientAdapter implements V1AuthenticationAPIGroupDSL {
   public InOutCreateable tokenReviews() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().getNonListingOperation(TokenReview.class, this);
   }

   public InOutCreateable selfSubjectReviews() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().getNonListingOperation(SelfSubjectReview.class, this);
   }

   public V1AuthenticationAPIGroupClient newInstance() {
      return new V1AuthenticationAPIGroupClient();
   }
}
