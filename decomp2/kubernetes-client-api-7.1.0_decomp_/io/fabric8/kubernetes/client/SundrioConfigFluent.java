package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.AuthProviderConfig;
import io.fabric8.kubernetes.api.model.NamedContext;
import io.fabric8.kubernetes.client.http.TlsVersion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class SundrioConfigFluent extends BaseFluent {
   private Boolean trustCerts;
   private Boolean disableHostnameVerification;
   private String masterUrl;
   private String apiVersion;
   private String namespace;
   private String caCertFile;
   private String caCertData;
   private String clientCertFile;
   private String clientCertData;
   private String clientKeyFile;
   private String clientKeyData;
   private String clientKeyAlgo;
   private String clientKeyPassphrase;
   private String trustStoreFile;
   private String trustStorePassphrase;
   private String keyStoreFile;
   private String keyStorePassphrase;
   private AuthProviderConfig authProvider;
   private String username;
   private String password;
   private String oauthToken;
   private String autoOAuthToken;
   private OAuthTokenProvider oauthTokenProvider;
   private Long websocketPingInterval;
   private Integer connectionTimeout;
   private Integer maxConcurrentRequests;
   private Integer maxConcurrentRequestsPerHost;
   private List contexts = new ArrayList();
   private NamedContext currentContext;
   private Integer watchReconnectInterval;
   private Integer watchReconnectLimit;
   private Integer uploadRequestTimeout;
   private Integer requestRetryBackoffLimit;
   private Integer requestRetryBackoffInterval;
   private Integer requestTimeout;
   private Long scaleTimeout;
   private Integer loggingInterval;
   private String impersonateUsername;
   private List impersonateGroups = new ArrayList();
   private Map impersonateExtras = new LinkedHashMap();
   private Boolean http2Disable;
   private String httpProxy;
   private String httpsProxy;
   private String proxyUsername;
   private String proxyPassword;
   private List noProxy = new ArrayList();
   private String userAgent;
   private List tlsVersions = new ArrayList();
   private Boolean onlyHttpWatches;
   private Map customHeaders = new LinkedHashMap();
   private Boolean autoConfigure;

   public SundrioConfigFluent() {
   }

   public SundrioConfigFluent(SundrioConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SundrioConfig instance) {
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

   public Boolean getTrustCerts() {
      return this.trustCerts;
   }

   public SundrioConfigFluent withTrustCerts(Boolean trustCerts) {
      this.trustCerts = trustCerts;
      return this;
   }

   public boolean hasTrustCerts() {
      return this.trustCerts != null;
   }

   public Boolean getDisableHostnameVerification() {
      return this.disableHostnameVerification;
   }

   public SundrioConfigFluent withDisableHostnameVerification(Boolean disableHostnameVerification) {
      this.disableHostnameVerification = disableHostnameVerification;
      return this;
   }

   public boolean hasDisableHostnameVerification() {
      return this.disableHostnameVerification != null;
   }

   public String getMasterUrl() {
      return this.masterUrl;
   }

   public SundrioConfigFluent withMasterUrl(String masterUrl) {
      this.masterUrl = masterUrl;
      return this;
   }

   public boolean hasMasterUrl() {
      return this.masterUrl != null;
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public SundrioConfigFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public SundrioConfigFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public String getCaCertFile() {
      return this.caCertFile;
   }

   public SundrioConfigFluent withCaCertFile(String caCertFile) {
      this.caCertFile = caCertFile;
      return this;
   }

   public boolean hasCaCertFile() {
      return this.caCertFile != null;
   }

   public String getCaCertData() {
      return this.caCertData;
   }

   public SundrioConfigFluent withCaCertData(String caCertData) {
      this.caCertData = caCertData;
      return this;
   }

   public boolean hasCaCertData() {
      return this.caCertData != null;
   }

   public String getClientCertFile() {
      return this.clientCertFile;
   }

   public SundrioConfigFluent withClientCertFile(String clientCertFile) {
      this.clientCertFile = clientCertFile;
      return this;
   }

   public boolean hasClientCertFile() {
      return this.clientCertFile != null;
   }

   public String getClientCertData() {
      return this.clientCertData;
   }

   public SundrioConfigFluent withClientCertData(String clientCertData) {
      this.clientCertData = clientCertData;
      return this;
   }

   public boolean hasClientCertData() {
      return this.clientCertData != null;
   }

   public String getClientKeyFile() {
      return this.clientKeyFile;
   }

   public SundrioConfigFluent withClientKeyFile(String clientKeyFile) {
      this.clientKeyFile = clientKeyFile;
      return this;
   }

   public boolean hasClientKeyFile() {
      return this.clientKeyFile != null;
   }

   public String getClientKeyData() {
      return this.clientKeyData;
   }

   public SundrioConfigFluent withClientKeyData(String clientKeyData) {
      this.clientKeyData = clientKeyData;
      return this;
   }

   public boolean hasClientKeyData() {
      return this.clientKeyData != null;
   }

   public String getClientKeyAlgo() {
      return this.clientKeyAlgo;
   }

   public SundrioConfigFluent withClientKeyAlgo(String clientKeyAlgo) {
      this.clientKeyAlgo = clientKeyAlgo;
      return this;
   }

   public boolean hasClientKeyAlgo() {
      return this.clientKeyAlgo != null;
   }

   public String getClientKeyPassphrase() {
      return this.clientKeyPassphrase;
   }

   public SundrioConfigFluent withClientKeyPassphrase(String clientKeyPassphrase) {
      this.clientKeyPassphrase = clientKeyPassphrase;
      return this;
   }

   public boolean hasClientKeyPassphrase() {
      return this.clientKeyPassphrase != null;
   }

   public String getTrustStoreFile() {
      return this.trustStoreFile;
   }

   public SundrioConfigFluent withTrustStoreFile(String trustStoreFile) {
      this.trustStoreFile = trustStoreFile;
      return this;
   }

   public boolean hasTrustStoreFile() {
      return this.trustStoreFile != null;
   }

   public String getTrustStorePassphrase() {
      return this.trustStorePassphrase;
   }

   public SundrioConfigFluent withTrustStorePassphrase(String trustStorePassphrase) {
      this.trustStorePassphrase = trustStorePassphrase;
      return this;
   }

   public boolean hasTrustStorePassphrase() {
      return this.trustStorePassphrase != null;
   }

   public String getKeyStoreFile() {
      return this.keyStoreFile;
   }

   public SundrioConfigFluent withKeyStoreFile(String keyStoreFile) {
      this.keyStoreFile = keyStoreFile;
      return this;
   }

   public boolean hasKeyStoreFile() {
      return this.keyStoreFile != null;
   }

   public String getKeyStorePassphrase() {
      return this.keyStorePassphrase;
   }

   public SundrioConfigFluent withKeyStorePassphrase(String keyStorePassphrase) {
      this.keyStorePassphrase = keyStorePassphrase;
      return this;
   }

   public boolean hasKeyStorePassphrase() {
      return this.keyStorePassphrase != null;
   }

   public AuthProviderConfig getAuthProvider() {
      return this.authProvider;
   }

   public SundrioConfigFluent withAuthProvider(AuthProviderConfig authProvider) {
      this.authProvider = authProvider;
      return this;
   }

   public boolean hasAuthProvider() {
      return this.authProvider != null;
   }

   public String getUsername() {
      return this.username;
   }

   public SundrioConfigFluent withUsername(String username) {
      this.username = username;
      return this;
   }

   public boolean hasUsername() {
      return this.username != null;
   }

   public String getPassword() {
      return this.password;
   }

   public SundrioConfigFluent withPassword(String password) {
      this.password = password;
      return this;
   }

   public boolean hasPassword() {
      return this.password != null;
   }

   public String getOauthToken() {
      return this.oauthToken;
   }

   public SundrioConfigFluent withOauthToken(String oauthToken) {
      this.oauthToken = oauthToken;
      return this;
   }

   public boolean hasOauthToken() {
      return this.oauthToken != null;
   }

   public String getAutoOAuthToken() {
      return this.autoOAuthToken;
   }

   public SundrioConfigFluent withAutoOAuthToken(String autoOAuthToken) {
      this.autoOAuthToken = autoOAuthToken;
      return this;
   }

   public boolean hasAutoOAuthToken() {
      return this.autoOAuthToken != null;
   }

   public OAuthTokenProvider getOauthTokenProvider() {
      return this.oauthTokenProvider;
   }

   public SundrioConfigFluent withOauthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
      this.oauthTokenProvider = oauthTokenProvider;
      return this;
   }

   public boolean hasOauthTokenProvider() {
      return this.oauthTokenProvider != null;
   }

   public Long getWebsocketPingInterval() {
      return this.websocketPingInterval;
   }

   public SundrioConfigFluent withWebsocketPingInterval(Long websocketPingInterval) {
      this.websocketPingInterval = websocketPingInterval;
      return this;
   }

   public boolean hasWebsocketPingInterval() {
      return this.websocketPingInterval != null;
   }

   public Integer getConnectionTimeout() {
      return this.connectionTimeout;
   }

   public SundrioConfigFluent withConnectionTimeout(Integer connectionTimeout) {
      this.connectionTimeout = connectionTimeout;
      return this;
   }

   public boolean hasConnectionTimeout() {
      return this.connectionTimeout != null;
   }

   public Integer getMaxConcurrentRequests() {
      return this.maxConcurrentRequests;
   }

   public SundrioConfigFluent withMaxConcurrentRequests(Integer maxConcurrentRequests) {
      this.maxConcurrentRequests = maxConcurrentRequests;
      return this;
   }

   public boolean hasMaxConcurrentRequests() {
      return this.maxConcurrentRequests != null;
   }

   public Integer getMaxConcurrentRequestsPerHost() {
      return this.maxConcurrentRequestsPerHost;
   }

   public SundrioConfigFluent withMaxConcurrentRequestsPerHost(Integer maxConcurrentRequestsPerHost) {
      this.maxConcurrentRequestsPerHost = maxConcurrentRequestsPerHost;
      return this;
   }

   public boolean hasMaxConcurrentRequestsPerHost() {
      return this.maxConcurrentRequestsPerHost != null;
   }

   public SundrioConfigFluent addToContexts(int index, NamedContext item) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      this.contexts.add(index, item);
      return this;
   }

   public SundrioConfigFluent setToContexts(int index, NamedContext item) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      this.contexts.set(index, item);
      return this;
   }

   public SundrioConfigFluent addToContexts(NamedContext... items) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      for(NamedContext item : items) {
         this.contexts.add(item);
      }

      return this;
   }

   public SundrioConfigFluent addAllToContexts(Collection items) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      for(NamedContext item : items) {
         this.contexts.add(item);
      }

      return this;
   }

   public SundrioConfigFluent removeFromContexts(NamedContext... items) {
      if (this.contexts == null) {
         return this;
      } else {
         for(NamedContext item : items) {
            this.contexts.remove(item);
         }

         return this;
      }
   }

   public SundrioConfigFluent removeAllFromContexts(Collection items) {
      if (this.contexts == null) {
         return this;
      } else {
         for(NamedContext item : items) {
            this.contexts.remove(item);
         }

         return this;
      }
   }

   public List getContexts() {
      return this.contexts;
   }

   public NamedContext getContext(int index) {
      return (NamedContext)this.contexts.get(index);
   }

   public NamedContext getFirstContext() {
      return (NamedContext)this.contexts.get(0);
   }

   public NamedContext getLastContext() {
      return (NamedContext)this.contexts.get(this.contexts.size() - 1);
   }

   public NamedContext getMatchingContext(Predicate predicate) {
      for(NamedContext item : this.contexts) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingContext(Predicate predicate) {
      for(NamedContext item : this.contexts) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public SundrioConfigFluent withContexts(List contexts) {
      if (contexts != null) {
         this.contexts = new ArrayList();

         for(NamedContext item : contexts) {
            this.addToContexts(item);
         }
      } else {
         this.contexts = null;
      }

      return this;
   }

   public SundrioConfigFluent withContexts(NamedContext... contexts) {
      if (this.contexts != null) {
         this.contexts.clear();
         this._visitables.remove("contexts");
      }

      if (contexts != null) {
         for(NamedContext item : contexts) {
            this.addToContexts(item);
         }
      }

      return this;
   }

   public boolean hasContexts() {
      return this.contexts != null && !this.contexts.isEmpty();
   }

   public NamedContext getCurrentContext() {
      return this.currentContext;
   }

   public SundrioConfigFluent withCurrentContext(NamedContext currentContext) {
      this.currentContext = currentContext;
      return this;
   }

   public boolean hasCurrentContext() {
      return this.currentContext != null;
   }

   public Integer getWatchReconnectInterval() {
      return this.watchReconnectInterval;
   }

   public SundrioConfigFluent withWatchReconnectInterval(Integer watchReconnectInterval) {
      this.watchReconnectInterval = watchReconnectInterval;
      return this;
   }

   public boolean hasWatchReconnectInterval() {
      return this.watchReconnectInterval != null;
   }

   public Integer getWatchReconnectLimit() {
      return this.watchReconnectLimit;
   }

   public SundrioConfigFluent withWatchReconnectLimit(Integer watchReconnectLimit) {
      this.watchReconnectLimit = watchReconnectLimit;
      return this;
   }

   public boolean hasWatchReconnectLimit() {
      return this.watchReconnectLimit != null;
   }

   public Integer getUploadRequestTimeout() {
      return this.uploadRequestTimeout;
   }

   public SundrioConfigFluent withUploadRequestTimeout(Integer uploadRequestTimeout) {
      this.uploadRequestTimeout = uploadRequestTimeout;
      return this;
   }

   public boolean hasUploadRequestTimeout() {
      return this.uploadRequestTimeout != null;
   }

   public Integer getRequestRetryBackoffLimit() {
      return this.requestRetryBackoffLimit;
   }

   public SundrioConfigFluent withRequestRetryBackoffLimit(Integer requestRetryBackoffLimit) {
      this.requestRetryBackoffLimit = requestRetryBackoffLimit;
      return this;
   }

   public boolean hasRequestRetryBackoffLimit() {
      return this.requestRetryBackoffLimit != null;
   }

   public Integer getRequestRetryBackoffInterval() {
      return this.requestRetryBackoffInterval;
   }

   public SundrioConfigFluent withRequestRetryBackoffInterval(Integer requestRetryBackoffInterval) {
      this.requestRetryBackoffInterval = requestRetryBackoffInterval;
      return this;
   }

   public boolean hasRequestRetryBackoffInterval() {
      return this.requestRetryBackoffInterval != null;
   }

   public Integer getRequestTimeout() {
      return this.requestTimeout;
   }

   public SundrioConfigFluent withRequestTimeout(Integer requestTimeout) {
      this.requestTimeout = requestTimeout;
      return this;
   }

   public boolean hasRequestTimeout() {
      return this.requestTimeout != null;
   }

   public Long getScaleTimeout() {
      return this.scaleTimeout;
   }

   public SundrioConfigFluent withScaleTimeout(Long scaleTimeout) {
      this.scaleTimeout = scaleTimeout;
      return this;
   }

   public boolean hasScaleTimeout() {
      return this.scaleTimeout != null;
   }

   public Integer getLoggingInterval() {
      return this.loggingInterval;
   }

   public SundrioConfigFluent withLoggingInterval(Integer loggingInterval) {
      this.loggingInterval = loggingInterval;
      return this;
   }

   public boolean hasLoggingInterval() {
      return this.loggingInterval != null;
   }

   public String getImpersonateUsername() {
      return this.impersonateUsername;
   }

   public SundrioConfigFluent withImpersonateUsername(String impersonateUsername) {
      this.impersonateUsername = impersonateUsername;
      return this;
   }

   public boolean hasImpersonateUsername() {
      return this.impersonateUsername != null;
   }

   public SundrioConfigFluent withImpersonateGroups(String... impersonateGroups) {
      if (this.impersonateGroups != null) {
         this.impersonateGroups.clear();
         this._visitables.remove("impersonateGroups");
      }

      if (impersonateGroups != null) {
         for(String item : impersonateGroups) {
            this.addToImpersonateGroups(item);
         }
      }

      return this;
   }

   public String[] getImpersonateGroups() {
      int size = this.impersonateGroups != null ? this.impersonateGroups.size() : 0;
      String[] result = new String[size];
      if (size == 0) {
         return result;
      } else {
         int index = 0;

         for(String item : this.impersonateGroups) {
            result[index++] = item;
         }

         return result;
      }
   }

   public SundrioConfigFluent addToImpersonateGroups(int index, String item) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      this.impersonateGroups.add(index, item);
      return this;
   }

   public SundrioConfigFluent setToImpersonateGroups(int index, String item) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      this.impersonateGroups.set(index, item);
      return this;
   }

   public SundrioConfigFluent addToImpersonateGroups(String... items) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      for(String item : items) {
         this.impersonateGroups.add(item);
      }

      return this;
   }

   public SundrioConfigFluent addAllToImpersonateGroups(Collection items) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      for(String item : items) {
         this.impersonateGroups.add(item);
      }

      return this;
   }

   public SundrioConfigFluent removeFromImpersonateGroups(String... items) {
      if (this.impersonateGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.impersonateGroups.remove(item);
         }

         return this;
      }
   }

   public SundrioConfigFluent removeAllFromImpersonateGroups(Collection items) {
      if (this.impersonateGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.impersonateGroups.remove(item);
         }

         return this;
      }
   }

   public boolean hasImpersonateGroups() {
      return this.impersonateGroups != null && !this.impersonateGroups.isEmpty();
   }

   public SundrioConfigFluent addToImpersonateExtras(String key, List value) {
      if (this.impersonateExtras == null && key != null && value != null) {
         this.impersonateExtras = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.impersonateExtras.put(key, value);
      }

      return this;
   }

   public SundrioConfigFluent addToImpersonateExtras(Map map) {
      if (this.impersonateExtras == null && map != null) {
         this.impersonateExtras = new LinkedHashMap();
      }

      if (map != null) {
         this.impersonateExtras.putAll(map);
      }

      return this;
   }

   public SundrioConfigFluent removeFromImpersonateExtras(String key) {
      if (this.impersonateExtras == null) {
         return this;
      } else {
         if (key != null && this.impersonateExtras != null) {
            this.impersonateExtras.remove(key);
         }

         return this;
      }
   }

   public SundrioConfigFluent removeFromImpersonateExtras(Map map) {
      if (this.impersonateExtras == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.impersonateExtras != null) {
                  this.impersonateExtras.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getImpersonateExtras() {
      return this.impersonateExtras;
   }

   public SundrioConfigFluent withImpersonateExtras(Map impersonateExtras) {
      if (impersonateExtras == null) {
         this.impersonateExtras = null;
      } else {
         this.impersonateExtras = new LinkedHashMap(impersonateExtras);
      }

      return this;
   }

   public boolean hasImpersonateExtras() {
      return this.impersonateExtras != null;
   }

   public Boolean getHttp2Disable() {
      return this.http2Disable;
   }

   public SundrioConfigFluent withHttp2Disable(Boolean http2Disable) {
      this.http2Disable = http2Disable;
      return this;
   }

   public boolean hasHttp2Disable() {
      return this.http2Disable != null;
   }

   public String getHttpProxy() {
      return this.httpProxy;
   }

   public SundrioConfigFluent withHttpProxy(String httpProxy) {
      this.httpProxy = httpProxy;
      return this;
   }

   public boolean hasHttpProxy() {
      return this.httpProxy != null;
   }

   public String getHttpsProxy() {
      return this.httpsProxy;
   }

   public SundrioConfigFluent withHttpsProxy(String httpsProxy) {
      this.httpsProxy = httpsProxy;
      return this;
   }

   public boolean hasHttpsProxy() {
      return this.httpsProxy != null;
   }

   public String getProxyUsername() {
      return this.proxyUsername;
   }

   public SundrioConfigFluent withProxyUsername(String proxyUsername) {
      this.proxyUsername = proxyUsername;
      return this;
   }

   public boolean hasProxyUsername() {
      return this.proxyUsername != null;
   }

   public String getProxyPassword() {
      return this.proxyPassword;
   }

   public SundrioConfigFluent withProxyPassword(String proxyPassword) {
      this.proxyPassword = proxyPassword;
      return this;
   }

   public boolean hasProxyPassword() {
      return this.proxyPassword != null;
   }

   public SundrioConfigFluent withNoProxy(String... noProxy) {
      if (this.noProxy != null) {
         this.noProxy.clear();
         this._visitables.remove("noProxy");
      }

      if (noProxy != null) {
         for(String item : noProxy) {
            this.addToNoProxy(item);
         }
      }

      return this;
   }

   public String[] getNoProxy() {
      int size = this.noProxy != null ? this.noProxy.size() : 0;
      String[] result = new String[size];
      if (size == 0) {
         return result;
      } else {
         int index = 0;

         for(String item : this.noProxy) {
            result[index++] = item;
         }

         return result;
      }
   }

   public SundrioConfigFluent addToNoProxy(int index, String item) {
      if (this.noProxy == null) {
         this.noProxy = new ArrayList();
      }

      this.noProxy.add(index, item);
      return this;
   }

   public SundrioConfigFluent setToNoProxy(int index, String item) {
      if (this.noProxy == null) {
         this.noProxy = new ArrayList();
      }

      this.noProxy.set(index, item);
      return this;
   }

   public SundrioConfigFluent addToNoProxy(String... items) {
      if (this.noProxy == null) {
         this.noProxy = new ArrayList();
      }

      for(String item : items) {
         this.noProxy.add(item);
      }

      return this;
   }

   public SundrioConfigFluent addAllToNoProxy(Collection items) {
      if (this.noProxy == null) {
         this.noProxy = new ArrayList();
      }

      for(String item : items) {
         this.noProxy.add(item);
      }

      return this;
   }

   public SundrioConfigFluent removeFromNoProxy(String... items) {
      if (this.noProxy == null) {
         return this;
      } else {
         for(String item : items) {
            this.noProxy.remove(item);
         }

         return this;
      }
   }

   public SundrioConfigFluent removeAllFromNoProxy(Collection items) {
      if (this.noProxy == null) {
         return this;
      } else {
         for(String item : items) {
            this.noProxy.remove(item);
         }

         return this;
      }
   }

   public boolean hasNoProxy() {
      return this.noProxy != null && !this.noProxy.isEmpty();
   }

   public String getUserAgent() {
      return this.userAgent;
   }

   public SundrioConfigFluent withUserAgent(String userAgent) {
      this.userAgent = userAgent;
      return this;
   }

   public boolean hasUserAgent() {
      return this.userAgent != null;
   }

   public SundrioConfigFluent withTlsVersions(TlsVersion... tlsVersions) {
      if (this.tlsVersions != null) {
         this.tlsVersions.clear();
         this._visitables.remove("tlsVersions");
      }

      if (tlsVersions != null) {
         for(TlsVersion item : tlsVersions) {
            this.addToTlsVersions(item);
         }
      }

      return this;
   }

   public TlsVersion[] getTlsVersions() {
      int size = this.tlsVersions != null ? this.tlsVersions.size() : 0;
      TlsVersion[] result = new TlsVersion[size];
      if (size == 0) {
         return result;
      } else {
         int index = 0;

         for(TlsVersion item : this.tlsVersions) {
            result[index++] = item;
         }

         return result;
      }
   }

   public SundrioConfigFluent addToTlsVersions(int index, TlsVersion item) {
      if (this.tlsVersions == null) {
         this.tlsVersions = new ArrayList();
      }

      this.tlsVersions.add(index, item);
      return this;
   }

   public SundrioConfigFluent setToTlsVersions(int index, TlsVersion item) {
      if (this.tlsVersions == null) {
         this.tlsVersions = new ArrayList();
      }

      this.tlsVersions.set(index, item);
      return this;
   }

   public SundrioConfigFluent addToTlsVersions(TlsVersion... items) {
      if (this.tlsVersions == null) {
         this.tlsVersions = new ArrayList();
      }

      for(TlsVersion item : items) {
         this.tlsVersions.add(item);
      }

      return this;
   }

   public SundrioConfigFluent addAllToTlsVersions(Collection items) {
      if (this.tlsVersions == null) {
         this.tlsVersions = new ArrayList();
      }

      for(TlsVersion item : items) {
         this.tlsVersions.add(item);
      }

      return this;
   }

   public SundrioConfigFluent removeFromTlsVersions(TlsVersion... items) {
      if (this.tlsVersions == null) {
         return this;
      } else {
         for(TlsVersion item : items) {
            this.tlsVersions.remove(item);
         }

         return this;
      }
   }

   public SundrioConfigFluent removeAllFromTlsVersions(Collection items) {
      if (this.tlsVersions == null) {
         return this;
      } else {
         for(TlsVersion item : items) {
            this.tlsVersions.remove(item);
         }

         return this;
      }
   }

   public boolean hasTlsVersions() {
      return this.tlsVersions != null && !this.tlsVersions.isEmpty();
   }

   public Boolean getOnlyHttpWatches() {
      return this.onlyHttpWatches;
   }

   public SundrioConfigFluent withOnlyHttpWatches(Boolean onlyHttpWatches) {
      this.onlyHttpWatches = onlyHttpWatches;
      return this;
   }

   public boolean hasOnlyHttpWatches() {
      return this.onlyHttpWatches != null;
   }

   public SundrioConfigFluent addToCustomHeaders(String key, String value) {
      if (this.customHeaders == null && key != null && value != null) {
         this.customHeaders = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.customHeaders.put(key, value);
      }

      return this;
   }

   public SundrioConfigFluent addToCustomHeaders(Map map) {
      if (this.customHeaders == null && map != null) {
         this.customHeaders = new LinkedHashMap();
      }

      if (map != null) {
         this.customHeaders.putAll(map);
      }

      return this;
   }

   public SundrioConfigFluent removeFromCustomHeaders(String key) {
      if (this.customHeaders == null) {
         return this;
      } else {
         if (key != null && this.customHeaders != null) {
            this.customHeaders.remove(key);
         }

         return this;
      }
   }

   public SundrioConfigFluent removeFromCustomHeaders(Map map) {
      if (this.customHeaders == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.customHeaders != null) {
                  this.customHeaders.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getCustomHeaders() {
      return this.customHeaders;
   }

   public SundrioConfigFluent withCustomHeaders(Map customHeaders) {
      if (customHeaders == null) {
         this.customHeaders = null;
      } else {
         this.customHeaders = new LinkedHashMap(customHeaders);
      }

      return this;
   }

   public boolean hasCustomHeaders() {
      return this.customHeaders != null;
   }

   public Boolean getAutoConfigure() {
      return this.autoConfigure;
   }

   public SundrioConfigFluent withAutoConfigure(Boolean autoConfigure) {
      this.autoConfigure = autoConfigure;
      return this;
   }

   public boolean hasAutoConfigure() {
      return this.autoConfigure != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            SundrioConfigFluent that = (SundrioConfigFluent)o;
            if (!Objects.equals(this.trustCerts, that.trustCerts)) {
               return false;
            } else if (!Objects.equals(this.disableHostnameVerification, that.disableHostnameVerification)) {
               return false;
            } else if (!Objects.equals(this.masterUrl, that.masterUrl)) {
               return false;
            } else if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.caCertFile, that.caCertFile)) {
               return false;
            } else if (!Objects.equals(this.caCertData, that.caCertData)) {
               return false;
            } else if (!Objects.equals(this.clientCertFile, that.clientCertFile)) {
               return false;
            } else if (!Objects.equals(this.clientCertData, that.clientCertData)) {
               return false;
            } else if (!Objects.equals(this.clientKeyFile, that.clientKeyFile)) {
               return false;
            } else if (!Objects.equals(this.clientKeyData, that.clientKeyData)) {
               return false;
            } else if (!Objects.equals(this.clientKeyAlgo, that.clientKeyAlgo)) {
               return false;
            } else if (!Objects.equals(this.clientKeyPassphrase, that.clientKeyPassphrase)) {
               return false;
            } else if (!Objects.equals(this.trustStoreFile, that.trustStoreFile)) {
               return false;
            } else if (!Objects.equals(this.trustStorePassphrase, that.trustStorePassphrase)) {
               return false;
            } else if (!Objects.equals(this.keyStoreFile, that.keyStoreFile)) {
               return false;
            } else if (!Objects.equals(this.keyStorePassphrase, that.keyStorePassphrase)) {
               return false;
            } else if (!Objects.equals(this.authProvider, that.authProvider)) {
               return false;
            } else if (!Objects.equals(this.username, that.username)) {
               return false;
            } else if (!Objects.equals(this.password, that.password)) {
               return false;
            } else if (!Objects.equals(this.oauthToken, that.oauthToken)) {
               return false;
            } else if (!Objects.equals(this.autoOAuthToken, that.autoOAuthToken)) {
               return false;
            } else if (!Objects.equals(this.oauthTokenProvider, that.oauthTokenProvider)) {
               return false;
            } else if (!Objects.equals(this.websocketPingInterval, that.websocketPingInterval)) {
               return false;
            } else if (!Objects.equals(this.connectionTimeout, that.connectionTimeout)) {
               return false;
            } else if (!Objects.equals(this.maxConcurrentRequests, that.maxConcurrentRequests)) {
               return false;
            } else if (!Objects.equals(this.maxConcurrentRequestsPerHost, that.maxConcurrentRequestsPerHost)) {
               return false;
            } else if (!Objects.equals(this.contexts, that.contexts)) {
               return false;
            } else if (!Objects.equals(this.currentContext, that.currentContext)) {
               return false;
            } else if (!Objects.equals(this.watchReconnectInterval, that.watchReconnectInterval)) {
               return false;
            } else if (!Objects.equals(this.watchReconnectLimit, that.watchReconnectLimit)) {
               return false;
            } else if (!Objects.equals(this.uploadRequestTimeout, that.uploadRequestTimeout)) {
               return false;
            } else if (!Objects.equals(this.requestRetryBackoffLimit, that.requestRetryBackoffLimit)) {
               return false;
            } else if (!Objects.equals(this.requestRetryBackoffInterval, that.requestRetryBackoffInterval)) {
               return false;
            } else if (!Objects.equals(this.requestTimeout, that.requestTimeout)) {
               return false;
            } else if (!Objects.equals(this.scaleTimeout, that.scaleTimeout)) {
               return false;
            } else if (!Objects.equals(this.loggingInterval, that.loggingInterval)) {
               return false;
            } else if (!Objects.equals(this.impersonateUsername, that.impersonateUsername)) {
               return false;
            } else if (!Objects.equals(this.impersonateGroups, that.impersonateGroups)) {
               return false;
            } else if (!Objects.equals(this.impersonateExtras, that.impersonateExtras)) {
               return false;
            } else if (!Objects.equals(this.http2Disable, that.http2Disable)) {
               return false;
            } else if (!Objects.equals(this.httpProxy, that.httpProxy)) {
               return false;
            } else if (!Objects.equals(this.httpsProxy, that.httpsProxy)) {
               return false;
            } else if (!Objects.equals(this.proxyUsername, that.proxyUsername)) {
               return false;
            } else if (!Objects.equals(this.proxyPassword, that.proxyPassword)) {
               return false;
            } else if (!Objects.equals(this.noProxy, that.noProxy)) {
               return false;
            } else if (!Objects.equals(this.userAgent, that.userAgent)) {
               return false;
            } else if (!Objects.equals(this.tlsVersions, that.tlsVersions)) {
               return false;
            } else if (!Objects.equals(this.onlyHttpWatches, that.onlyHttpWatches)) {
               return false;
            } else if (!Objects.equals(this.customHeaders, that.customHeaders)) {
               return false;
            } else {
               return Objects.equals(this.autoConfigure, that.autoConfigure);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.trustCerts, this.disableHostnameVerification, this.masterUrl, this.apiVersion, this.namespace, this.caCertFile, this.caCertData, this.clientCertFile, this.clientCertData, this.clientKeyFile, this.clientKeyData, this.clientKeyAlgo, this.clientKeyPassphrase, this.trustStoreFile, this.trustStorePassphrase, this.keyStoreFile, this.keyStorePassphrase, this.authProvider, this.username, this.password, this.oauthToken, this.autoOAuthToken, this.oauthTokenProvider, this.websocketPingInterval, this.connectionTimeout, this.maxConcurrentRequests, this.maxConcurrentRequestsPerHost, this.contexts, this.currentContext, this.watchReconnectInterval, this.watchReconnectLimit, this.uploadRequestTimeout, this.requestRetryBackoffLimit, this.requestRetryBackoffInterval, this.requestTimeout, this.scaleTimeout, this.loggingInterval, this.impersonateUsername, this.impersonateGroups, this.impersonateExtras, this.http2Disable, this.httpProxy, this.httpsProxy, this.proxyUsername, this.proxyPassword, this.noProxy, this.userAgent, this.tlsVersions, this.onlyHttpWatches, this.customHeaders, this.autoConfigure, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.trustCerts != null) {
         sb.append("trustCerts:");
         sb.append(this.trustCerts + ",");
      }

      if (this.disableHostnameVerification != null) {
         sb.append("disableHostnameVerification:");
         sb.append(this.disableHostnameVerification + ",");
      }

      if (this.masterUrl != null) {
         sb.append("masterUrl:");
         sb.append(this.masterUrl + ",");
      }

      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.caCertFile != null) {
         sb.append("caCertFile:");
         sb.append(this.caCertFile + ",");
      }

      if (this.caCertData != null) {
         sb.append("caCertData:");
         sb.append(this.caCertData + ",");
      }

      if (this.clientCertFile != null) {
         sb.append("clientCertFile:");
         sb.append(this.clientCertFile + ",");
      }

      if (this.clientCertData != null) {
         sb.append("clientCertData:");
         sb.append(this.clientCertData + ",");
      }

      if (this.clientKeyFile != null) {
         sb.append("clientKeyFile:");
         sb.append(this.clientKeyFile + ",");
      }

      if (this.clientKeyData != null) {
         sb.append("clientKeyData:");
         sb.append(this.clientKeyData + ",");
      }

      if (this.clientKeyAlgo != null) {
         sb.append("clientKeyAlgo:");
         sb.append(this.clientKeyAlgo + ",");
      }

      if (this.clientKeyPassphrase != null) {
         sb.append("clientKeyPassphrase:");
         sb.append(this.clientKeyPassphrase + ",");
      }

      if (this.trustStoreFile != null) {
         sb.append("trustStoreFile:");
         sb.append(this.trustStoreFile + ",");
      }

      if (this.trustStorePassphrase != null) {
         sb.append("trustStorePassphrase:");
         sb.append(this.trustStorePassphrase + ",");
      }

      if (this.keyStoreFile != null) {
         sb.append("keyStoreFile:");
         sb.append(this.keyStoreFile + ",");
      }

      if (this.keyStorePassphrase != null) {
         sb.append("keyStorePassphrase:");
         sb.append(this.keyStorePassphrase + ",");
      }

      if (this.authProvider != null) {
         sb.append("authProvider:");
         sb.append(this.authProvider + ",");
      }

      if (this.username != null) {
         sb.append("username:");
         sb.append(this.username + ",");
      }

      if (this.password != null) {
         sb.append("password:");
         sb.append(this.password + ",");
      }

      if (this.oauthToken != null) {
         sb.append("oauthToken:");
         sb.append(this.oauthToken + ",");
      }

      if (this.autoOAuthToken != null) {
         sb.append("autoOAuthToken:");
         sb.append(this.autoOAuthToken + ",");
      }

      if (this.oauthTokenProvider != null) {
         sb.append("oauthTokenProvider:");
         sb.append(this.oauthTokenProvider + ",");
      }

      if (this.websocketPingInterval != null) {
         sb.append("websocketPingInterval:");
         sb.append(this.websocketPingInterval + ",");
      }

      if (this.connectionTimeout != null) {
         sb.append("connectionTimeout:");
         sb.append(this.connectionTimeout + ",");
      }

      if (this.maxConcurrentRequests != null) {
         sb.append("maxConcurrentRequests:");
         sb.append(this.maxConcurrentRequests + ",");
      }

      if (this.maxConcurrentRequestsPerHost != null) {
         sb.append("maxConcurrentRequestsPerHost:");
         sb.append(this.maxConcurrentRequestsPerHost + ",");
      }

      if (this.contexts != null && !this.contexts.isEmpty()) {
         sb.append("contexts:");
         sb.append(this.contexts + ",");
      }

      if (this.currentContext != null) {
         sb.append("currentContext:");
         sb.append(this.currentContext + ",");
      }

      if (this.watchReconnectInterval != null) {
         sb.append("watchReconnectInterval:");
         sb.append(this.watchReconnectInterval + ",");
      }

      if (this.watchReconnectLimit != null) {
         sb.append("watchReconnectLimit:");
         sb.append(this.watchReconnectLimit + ",");
      }

      if (this.uploadRequestTimeout != null) {
         sb.append("uploadRequestTimeout:");
         sb.append(this.uploadRequestTimeout + ",");
      }

      if (this.requestRetryBackoffLimit != null) {
         sb.append("requestRetryBackoffLimit:");
         sb.append(this.requestRetryBackoffLimit + ",");
      }

      if (this.requestRetryBackoffInterval != null) {
         sb.append("requestRetryBackoffInterval:");
         sb.append(this.requestRetryBackoffInterval + ",");
      }

      if (this.requestTimeout != null) {
         sb.append("requestTimeout:");
         sb.append(this.requestTimeout + ",");
      }

      if (this.scaleTimeout != null) {
         sb.append("scaleTimeout:");
         sb.append(this.scaleTimeout + ",");
      }

      if (this.loggingInterval != null) {
         sb.append("loggingInterval:");
         sb.append(this.loggingInterval + ",");
      }

      if (this.impersonateUsername != null) {
         sb.append("impersonateUsername:");
         sb.append(this.impersonateUsername + ",");
      }

      if (this.impersonateGroups != null && !this.impersonateGroups.isEmpty()) {
         sb.append("impersonateGroups:");
         sb.append(this.impersonateGroups + ",");
      }

      if (this.impersonateExtras != null && !this.impersonateExtras.isEmpty()) {
         sb.append("impersonateExtras:");
         sb.append(this.impersonateExtras + ",");
      }

      if (this.http2Disable != null) {
         sb.append("http2Disable:");
         sb.append(this.http2Disable + ",");
      }

      if (this.httpProxy != null) {
         sb.append("httpProxy:");
         sb.append(this.httpProxy + ",");
      }

      if (this.httpsProxy != null) {
         sb.append("httpsProxy:");
         sb.append(this.httpsProxy + ",");
      }

      if (this.proxyUsername != null) {
         sb.append("proxyUsername:");
         sb.append(this.proxyUsername + ",");
      }

      if (this.proxyPassword != null) {
         sb.append("proxyPassword:");
         sb.append(this.proxyPassword + ",");
      }

      if (this.noProxy != null && !this.noProxy.isEmpty()) {
         sb.append("noProxy:");
         sb.append(this.noProxy + ",");
      }

      if (this.userAgent != null) {
         sb.append("userAgent:");
         sb.append(this.userAgent + ",");
      }

      if (this.tlsVersions != null && !this.tlsVersions.isEmpty()) {
         sb.append("tlsVersions:");
         sb.append(this.tlsVersions + ",");
      }

      if (this.onlyHttpWatches != null) {
         sb.append("onlyHttpWatches:");
         sb.append(this.onlyHttpWatches + ",");
      }

      if (this.customHeaders != null && !this.customHeaders.isEmpty()) {
         sb.append("customHeaders:");
         sb.append(this.customHeaders + ",");
      }

      if (this.autoConfigure != null) {
         sb.append("autoConfigure:");
         sb.append(this.autoConfigure);
      }

      sb.append("}");
      return sb.toString();
   }

   public SundrioConfigFluent withTrustCerts() {
      return this.withTrustCerts(true);
   }

   public SundrioConfigFluent withDisableHostnameVerification() {
      return this.withDisableHostnameVerification(true);
   }

   public SundrioConfigFluent withHttp2Disable() {
      return this.withHttp2Disable(true);
   }

   public SundrioConfigFluent withOnlyHttpWatches() {
      return this.withOnlyHttpWatches(true);
   }

   public SundrioConfigFluent withAutoConfigure() {
      return this.withAutoConfigure(true);
   }
}
