package io.fabric8.kubernetes.client.dsl.internal.core.v1;

import io.fabric8.kubernetes.api.model.ServiceAccount;
import io.fabric8.kubernetes.api.model.ServiceAccountList;
import io.fabric8.kubernetes.api.model.authentication.TokenRequest;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.ServiceAccountResource;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.utils.URLUtils;
import java.io.IOException;
import java.net.URL;

public class ServiceAccountOperationsImpl extends HasMetadataOperation implements ServiceAccountResource {
   public ServiceAccountOperationsImpl(Client client) {
      this(HasMetadataOperationsImpl.defaultContext(client));
   }

   private ServiceAccountOperationsImpl(OperationContext context) {
      super(context.withPlural("serviceaccounts"), ServiceAccount.class, ServiceAccountList.class);
   }

   public ServiceAccountOperationsImpl newInstance(OperationContext context) {
      return new ServiceAccountOperationsImpl(context);
   }

   public TokenRequest tokenRequest(TokenRequest tokenRequest) {
      return this.handleTokenRequest(tokenRequest);
   }

   private TokenRequest handleTokenRequest(TokenRequest tokenRequest) {
      try {
         URL requestUrl = new URL(URLUtils.join(new String[]{this.getResourceUrl().toString(), "token"}));
         HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().post("application/json", this.getKubernetesSerialization().asJson(tokenRequest)).url(requestUrl);
         return (TokenRequest)this.handleResponse(requestBuilder, TokenRequest.class);
      } catch (IOException exception) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("token request"), exception);
      }
   }
}
