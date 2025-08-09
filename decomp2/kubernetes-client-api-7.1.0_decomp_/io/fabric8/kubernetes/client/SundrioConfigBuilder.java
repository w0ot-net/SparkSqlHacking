package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SundrioConfigBuilder extends SundrioConfigFluent implements VisitableBuilder {
   SundrioConfigFluent fluent;

   public SundrioConfigBuilder() {
      this.fluent = this;
   }

   public SundrioConfigBuilder(SundrioConfigFluent fluent) {
      this.fluent = fluent;
   }

   public SundrioConfigBuilder(SundrioConfigFluent fluent, SundrioConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SundrioConfigBuilder(SundrioConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SundrioConfig build() {
      SundrioConfig buildable = new SundrioConfig(this.fluent.getMasterUrl(), this.fluent.getApiVersion(), this.fluent.getNamespace(), this.fluent.getTrustCerts(), this.fluent.getDisableHostnameVerification(), this.fluent.getCaCertFile(), this.fluent.getCaCertData(), this.fluent.getClientCertFile(), this.fluent.getClientCertData(), this.fluent.getClientKeyFile(), this.fluent.getClientKeyData(), this.fluent.getClientKeyAlgo(), this.fluent.getClientKeyPassphrase(), this.fluent.getUsername(), this.fluent.getPassword(), this.fluent.getOauthToken(), this.fluent.getAutoOAuthToken(), this.fluent.getWatchReconnectInterval(), this.fluent.getWatchReconnectLimit(), this.fluent.getConnectionTimeout(), this.fluent.getRequestTimeout(), this.fluent.getScaleTimeout(), this.fluent.getLoggingInterval(), this.fluent.getMaxConcurrentRequests(), this.fluent.getMaxConcurrentRequestsPerHost(), this.fluent.getHttp2Disable(), this.fluent.getHttpProxy(), this.fluent.getHttpsProxy(), this.fluent.getNoProxy(), this.fluent.getUserAgent(), this.fluent.getTlsVersions(), this.fluent.getWebsocketPingInterval(), this.fluent.getProxyUsername(), this.fluent.getProxyPassword(), this.fluent.getTrustStoreFile(), this.fluent.getTrustStorePassphrase(), this.fluent.getKeyStoreFile(), this.fluent.getKeyStorePassphrase(), this.fluent.getImpersonateUsername(), this.fluent.getImpersonateGroups(), this.fluent.getImpersonateExtras(), this.fluent.getOauthTokenProvider(), this.fluent.getCustomHeaders(), this.fluent.getRequestRetryBackoffLimit(), this.fluent.getRequestRetryBackoffInterval(), this.fluent.getUploadRequestTimeout(), this.fluent.getOnlyHttpWatches(), this.fluent.getCurrentContext(), this.fluent.getContexts(), this.fluent.getAutoConfigure());
      buildable.setAuthProvider(this.fluent.getAuthProvider());
      return buildable;
   }
}
