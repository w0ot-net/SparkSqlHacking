package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.StandardHttpClientBuilder;
import io.fabric8.kubernetes.client.utils.HttpClientUtils;

/** @deprecated */
@Deprecated
public class DefaultKubernetesClient extends NamespacedKubernetesClientAdapter {
   public static final String KUBERNETES_VERSION_ENDPOINT = "version";

   public DefaultKubernetesClient() {
      this((new ConfigBuilder()).build());
   }

   public DefaultKubernetesClient(String masterUrl) {
      this(((ConfigBuilder)(new ConfigBuilder()).withMasterUrl(masterUrl)).build());
   }

   public DefaultKubernetesClient(Config config) {
      this(HttpClientUtils.createHttpClient(config), config);
   }

   public DefaultKubernetesClient(HttpClient httpClient, Config config) {
      this(httpClient, config, (KubernetesClientBuilder.ExecutorSupplier)null);
   }

   public DefaultKubernetesClient(final HttpClient httpClient, Config config, KubernetesClientBuilder.ExecutorSupplier executorSupplier) {
      super(NamespacedKubernetesClient.class);
      KubernetesClientBuilder builder = (new KubernetesClientBuilder()).withConfig(config).withTaskExecutorSupplier(executorSupplier);
      if (httpClient != null) {
         builder.withHttpClientFactory(new HttpClient.Factory() {
            public HttpClient.Builder newBuilder() {
               throw new UnsupportedOperationException();
            }

            public HttpClient.Builder newBuilder(Config config) {
               return new StandardHttpClientBuilder((HttpClient.Factory)null) {
                  public HttpClient build() {
                     return httpClient;
                  }

                  protected StandardHttpClientBuilder newInstance(HttpClient.Factory clientFactory) {
                     return null;
                  }
               };
            }
         });
      }

      this.init(builder.build());
   }
}
