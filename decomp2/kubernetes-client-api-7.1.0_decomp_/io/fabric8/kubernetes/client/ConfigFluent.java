package io.fabric8.kubernetes.client;

public class ConfigFluent extends SundrioConfigFluent {
   public ConfigFluent() {
   }

   public ConfigFluent(Config instance) {
      this.copyInstance(instance);
   }

   public void copyInstance(Config instance) {
      if (instance != null) {
         this.withMasterUrl(instance.getMasterUrl());
         this.withApiVersion(instance.getApiVersion());
         this.withNamespace(instance.getNamespace());
         this.withTrustCerts(instance.isTrustCerts());
         this.withDisableHostnameVerification(instance.isDisableHostnameVerification());
         this.withCaCertFile(instance.getCaCertFile());
         this.withCaCertData(instance.getCaCertData());
         this.withClientCertFile(instance.getClientCertFile());
         this.withClientCertData(instance.getClientCertData());
         this.withClientKeyFile(instance.getClientKeyFile());
         this.withClientKeyData(instance.getClientKeyData());
         this.withClientKeyAlgo(instance.getClientKeyAlgo());
         this.withClientKeyPassphrase(instance.getClientKeyPassphrase());
         this.withUsername(instance.getUsername());
         this.withPassword(instance.getPassword());
         this.withOauthToken(instance.getOauthToken());
         this.withAutoOAuthToken(instance.getAutoOAuthToken());
         this.withWatchReconnectInterval(instance.getWatchReconnectInterval());
         this.withWatchReconnectLimit(instance.getWatchReconnectLimit());
         this.withConnectionTimeout(instance.getConnectionTimeout());
         this.withRequestTimeout(instance.getRequestTimeout());
         this.withScaleTimeout(instance.getScaleTimeout());
         this.withLoggingInterval(instance.getLoggingInterval());
         this.withMaxConcurrentRequests(instance.getMaxConcurrentRequests());
         this.withMaxConcurrentRequestsPerHost(instance.getMaxConcurrentRequestsPerHost());
         this.withHttp2Disable(instance.isHttp2Disable());
         this.withHttpProxy(instance.getHttpProxy());
         this.withHttpsProxy(instance.getHttpsProxy());
         this.withNoProxy(instance.getNoProxy());
         this.withUserAgent(instance.getUserAgent());
         this.withTlsVersions(instance.getTlsVersions());
         this.withWebsocketPingInterval(instance.getWebsocketPingInterval());
         this.withProxyUsername(instance.getProxyUsername());
         this.withProxyPassword(instance.getProxyPassword());
         this.withTrustStoreFile(instance.getTrustStoreFile());
         this.withTrustStorePassphrase(instance.getTrustStorePassphrase());
         this.withKeyStoreFile(instance.getKeyStoreFile());
         this.withKeyStorePassphrase(instance.getKeyStorePassphrase());
         this.withImpersonateUsername(instance.getImpersonateUsername());
         this.withImpersonateGroups(instance.getImpersonateGroups());
         this.withImpersonateExtras(instance.getImpersonateExtras());
         this.withOauthTokenProvider(instance.getOauthTokenProvider());
         this.withCustomHeaders(instance.getCustomHeaders());
         this.withRequestRetryBackoffLimit(instance.getRequestRetryBackoffLimit());
         this.withRequestRetryBackoffInterval(instance.getRequestRetryBackoffInterval());
         this.withUploadRequestTimeout(instance.getUploadRequestTimeout());
         this.withOnlyHttpWatches(instance.isOnlyHttpWatches());
         this.withCurrentContext(instance.getCurrentContext());
         this.withContexts(instance.getContexts());
         this.withAutoConfigure(instance.getAutoConfigure());
         this.withAuthProvider(instance.getAuthProvider());
      }

   }

   public ConfigFluent withTrustCerts(boolean trustCerts) {
      return (ConfigFluent)this.withTrustCerts(trustCerts);
   }

   public ConfigFluent withDisableHostnameVerification(boolean disableHostnameVerification) {
      return (ConfigFluent)this.withDisableHostnameVerification(disableHostnameVerification);
   }

   public ConfigFluent withWebsocketPingInterval(long websocketPingInterval) {
      return (ConfigFluent)this.withWebsocketPingInterval(websocketPingInterval);
   }

   public ConfigFluent withConnectionTimeout(int connectionTimeout) {
      return (ConfigFluent)this.withConnectionTimeout(connectionTimeout);
   }

   public ConfigFluent withMaxConcurrentRequests(int maxConcurrentRequests) {
      return (ConfigFluent)this.withMaxConcurrentRequests(maxConcurrentRequests);
   }

   public ConfigFluent withWatchReconnectInterval(int watchReconnectInterval) {
      return (ConfigFluent)this.withWatchReconnectInterval(watchReconnectInterval);
   }

   public ConfigFluent withWatchReconnectLimit(int watchReconnectLimit) {
      return (ConfigFluent)this.withWatchReconnectLimit(watchReconnectLimit);
   }

   public ConfigFluent withUploadRequestTimeout(int uploadRequestTimeout) {
      return (ConfigFluent)this.withUploadRequestTimeout(uploadRequestTimeout);
   }

   public ConfigFluent withRequestRetryBackoffLimit(int requestRetryBackoffLimit) {
      return (ConfigFluent)this.withRequestRetryBackoffLimit(requestRetryBackoffLimit);
   }

   public ConfigFluent withRequestRetryBackoffInterval(int requestRetryBackoffInterval) {
      return (ConfigFluent)this.withRequestRetryBackoffInterval(requestRetryBackoffInterval);
   }

   public ConfigFluent withRequestTimeout(int requestTimeout) {
      return (ConfigFluent)this.withRequestTimeout(requestTimeout);
   }

   public ConfigFluent withScaleTimeout(long scaleTimeout) {
      return (ConfigFluent)this.withScaleTimeout(scaleTimeout);
   }

   public ConfigFluent withLoggingInterval(int loggingInterval) {
      return (ConfigFluent)this.withLoggingInterval(loggingInterval);
   }

   public ConfigFluent withHttp2Disable(boolean http2Disable) {
      return (ConfigFluent)this.withHttp2Disable(http2Disable);
   }

   public ConfigFluent withOnlyHttpWatches(boolean onlyHttpWatches) {
      return (ConfigFluent)this.withOnlyHttpWatches(onlyHttpWatches);
   }

   public ConfigFluent withAutoConfigure(boolean autoConfigure) {
      return (ConfigFluent)this.withAutoConfigure(autoConfigure);
   }
}
