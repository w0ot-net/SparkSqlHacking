package io.fabric8.kubernetes.client.osgi;

import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.NamespacedKubernetesClient;
import io.fabric8.kubernetes.client.NamespacedKubernetesClientAdapter;
import io.fabric8.kubernetes.client.OAuthTokenProvider;
import io.fabric8.kubernetes.client.impl.KubernetesClientImpl;
import java.util.Map;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
   configurationPid = {"io.fabric8.kubernetes.client"},
   name = "io.fabric8.kubernetes.client.osgi.ManagedKubernetesClient",
   scope = ServiceScope.SINGLETON,
   service = {KubernetesClient.class, NamespacedKubernetesClient.class},
   reference = {@Reference(
   name = "oAuthTokenProvider",
   service = OAuthTokenProvider.class,
   cardinality = ReferenceCardinality.OPTIONAL,
   policyOption = ReferencePolicyOption.GREEDY,
   bind = "bindOAuthTokenProvider",
   unbind = "unbindOAuthTokenProvider"
)},
   configurationPolicy = ConfigurationPolicy.REQUIRE
)
public class ManagedKubernetesClient extends NamespacedKubernetesClientAdapter {
   private OAuthTokenProvider provider;

   public ManagedKubernetesClient() {
      super(KubernetesClientImpl.class);
   }

   @Activate
   public void activate(Map properties) {
      ConfigBuilder builder = new ConfigBuilder();
      if (properties.containsKey("kubernetes.master")) {
         builder.withMasterUrl((String)properties.get("kubernetes.master"));
      }

      if (properties.containsKey("kubernetes.api.version")) {
         builder.withApiVersion((String)properties.get("kubernetes.api.version"));
      }

      if (properties.containsKey("kubernetes.namespace")) {
         builder.withNamespace((String)properties.get("kubernetes.namespace"));
      }

      if (properties.containsKey("kubernetes.certs.ca.file")) {
         builder.withCaCertFile((String)properties.get("kubernetes.certs.ca.file"));
      }

      if (properties.containsKey("kubernetes.certs.ca.data")) {
         builder.withCaCertData((String)properties.get("kubernetes.certs.ca.data"));
      }

      if (properties.containsKey("kubernetes.certs.client.file")) {
         builder.withClientCertFile((String)properties.get("kubernetes.certs.client.file"));
      }

      if (properties.containsKey("kubernetes.certs.client.data")) {
         builder.withClientCertData((String)properties.get("kubernetes.certs.client.data"));
      }

      if (properties.containsKey("kubernetes.certs.client.key.file")) {
         builder.withClientKeyFile((String)properties.get("kubernetes.certs.client.key.file"));
      }

      if (properties.containsKey("kubernetes.certs.client.key.data")) {
         builder.withClientKeyData((String)properties.get("kubernetes.certs.client.key.data"));
      }

      if (properties.containsKey("kubernetes.certs.client.key.algo")) {
         builder.withClientKeyAlgo((String)properties.get("kubernetes.certs.client.key.algo"));
      }

      if (properties.containsKey("kubernetes.certs.client.key.passphrase")) {
         builder.withClientKeyPassphrase((String)properties.get("kubernetes.certs.client.key.passphrase"));
      }

      if (properties.containsKey("kubernetes.auth.basic.username")) {
         builder.withUsername((String)properties.get("kubernetes.auth.basic.username"));
      }

      if (properties.containsKey("kubernetes.auth.basic.password")) {
         builder.withPassword((String)properties.get("kubernetes.auth.basic.password"));
      }

      if (properties.containsKey("kubernetes.auth.token")) {
         builder.withOauthToken((String)properties.get("kubernetes.auth.token"));
      }

      if (properties.containsKey("kubernetes.watch.reconnectInterval")) {
         builder.withWatchReconnectInterval(Integer.parseInt((String)properties.get("kubernetes.watch.reconnectInterval")));
      }

      if (properties.containsKey("kubernetes.watch.reconnectLimit")) {
         builder.withWatchReconnectLimit(Integer.parseInt((String)properties.get("kubernetes.watch.reconnectLimit")));
      }

      if (properties.containsKey("kubernetes.request.timeout")) {
         builder.withRequestTimeout(Integer.parseInt((String)properties.get("kubernetes.request.timeout")));
      }

      if (properties.containsKey("http.proxy")) {
         builder.withHttpProxy((String)properties.get("http.proxy"));
      }

      if (properties.containsKey("https.proxy")) {
         builder.withHttpsProxy((String)properties.get("https.proxy"));
      }

      if (properties.containsKey("no.proxy")) {
         String noProxyProperty = (String)properties.get("no.proxy");
         builder.withNoProxy(noProxyProperty.split(","));
      }

      if (properties.containsKey("kubernetes.websocket.ping.interval")) {
         builder.withWebsocketPingInterval(Long.parseLong((String)properties.get("kubernetes.websocket.ping.interval")));
      }

      if (properties.containsKey("kubernetes.truststore.file")) {
         builder.withTrustStoreFile((String)properties.get("kubernetes.truststore.file"));
      }

      if (properties.containsKey("kubernetes.truststore.passphrase")) {
         builder.withTrustStorePassphrase((String)properties.get("kubernetes.truststore.passphrase"));
      }

      if (properties.containsKey("kubernetes.keystore.file")) {
         builder.withKeyStoreFile((String)properties.get("kubernetes.keystore.file"));
      }

      if (properties.containsKey("kubernetes.keystore.passphrase")) {
         builder.withKeyStorePassphrase((String)properties.get("kubernetes.keystore.passphrase"));
      }

      if (this.provider != null) {
         builder.withOauthTokenProvider(this.provider);
      }

      this.init((new KubernetesClientBuilder()).withConfig(builder.build()).build());
   }

   @Deactivate
   public void deactivate() {
      this.close();
   }

   public void bindOAuthTokenProvider(OAuthTokenProvider provider) {
      this.provider = provider;
   }

   public void unbindOAuthTokenProvider(OAuthTokenProvider provider) {
      if (this.provider == provider) {
         this.provider = null;
      }

   }
}
