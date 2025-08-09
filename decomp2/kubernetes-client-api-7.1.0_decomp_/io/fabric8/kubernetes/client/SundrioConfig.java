package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.NamedContext;
import io.fabric8.kubernetes.client.http.TlsVersion;
import java.util.List;
import java.util.Map;

class SundrioConfig extends Config {
   public SundrioConfig(String masterUrl, String apiVersion, String namespace, Boolean trustCerts, Boolean disableHostnameVerification, String caCertFile, String caCertData, String clientCertFile, String clientCertData, String clientKeyFile, String clientKeyData, String clientKeyAlgo, String clientKeyPassphrase, String username, String password, String oauthToken, String autoOAuthToken, Integer watchReconnectInterval, Integer watchReconnectLimit, Integer connectionTimeout, Integer requestTimeout, Long scaleTimeout, Integer loggingInterval, Integer maxConcurrentRequests, Integer maxConcurrentRequestsPerHost, Boolean http2Disable, String httpProxy, String httpsProxy, String[] noProxy, String userAgent, TlsVersion[] tlsVersions, Long websocketPingInterval, String proxyUsername, String proxyPassword, String trustStoreFile, String trustStorePassphrase, String keyStoreFile, String keyStorePassphrase, String impersonateUsername, String[] impersonateGroups, Map impersonateExtras, OAuthTokenProvider oauthTokenProvider, Map customHeaders, Integer requestRetryBackoffLimit, Integer requestRetryBackoffInterval, Integer uploadRequestTimeout, Boolean onlyHttpWatches, NamedContext currentContext, List contexts, Boolean autoConfigure) {
      super(masterUrl, apiVersion, namespace, trustCerts, disableHostnameVerification, caCertFile, caCertData, clientCertFile, clientCertData, clientKeyFile, clientKeyData, clientKeyAlgo, clientKeyPassphrase, username, password, oauthToken, autoOAuthToken, watchReconnectInterval, watchReconnectLimit, connectionTimeout, requestTimeout, scaleTimeout, loggingInterval, maxConcurrentRequests, maxConcurrentRequestsPerHost, http2Disable, httpProxy, httpsProxy, noProxy, userAgent, tlsVersions, websocketPingInterval, proxyUsername, proxyPassword, trustStoreFile, trustStorePassphrase, keyStoreFile, keyStorePassphrase, impersonateUsername, impersonateGroups, impersonateExtras, oauthTokenProvider, customHeaders, requestRetryBackoffLimit, requestRetryBackoffInterval, uploadRequestTimeout, onlyHttpWatches, currentContext, contexts, autoConfigure, true);
   }
}
