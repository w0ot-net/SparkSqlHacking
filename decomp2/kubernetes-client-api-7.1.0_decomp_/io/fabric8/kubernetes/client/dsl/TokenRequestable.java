package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.authentication.TokenRequest;
import io.fabric8.kubernetes.api.model.authentication.TokenRequestBuilder;
import io.fabric8.kubernetes.api.model.authentication.TokenRequestFluent;

public interface TokenRequestable {
   default TokenRequest tokenRequest() {
      return this.tokenRequest(((TokenRequestBuilder)((TokenRequestFluent.SpecNested)(new TokenRequestBuilder()).withNewSpec().withExpirationSeconds(3600L)).endSpec()).build());
   }

   TokenRequest tokenRequest(TokenRequest var1);
}
