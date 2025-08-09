package io.fabric8.kubernetes.client;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.fabric8.kubernetes.api.model.AuthProviderConfig;
import io.fabric8.kubernetes.api.model.NamedContext;
import io.fabric8.kubernetes.client.http.TlsVersion;
import io.fabric8.kubernetes.client.internal.CertUtils;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import io.fabric8.kubernetes.client.internal.SSLUtils;
import io.fabric8.kubernetes.client.readiness.Readiness;
import io.fabric8.kubernetes.client.utils.IOHelpers;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(
   allowGetters = true,
   allowSetters = true
)
public class Config {
   private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
   public static final String KUBERNETES_DISABLE_AUTO_CONFIG_SYSTEM_PROPERTY = "kubernetes.disable.autoConfig";
   public static final String KUBERNETES_MASTER_SYSTEM_PROPERTY = "kubernetes.master";
   public static final String KUBERNETES_API_VERSION_SYSTEM_PROPERTY = "kubernetes.api.version";
   public static final String KUBERNETES_TRUST_CERT_SYSTEM_PROPERTY = "kubernetes.trust.certificates";
   public static final String KUBERNETES_DISABLE_HOSTNAME_VERIFICATION_SYSTEM_PROPERTY = "kubernetes.disable.hostname.verification";
   public static final String KUBERNETES_CA_CERTIFICATE_FILE_SYSTEM_PROPERTY = "kubernetes.certs.ca.file";
   public static final String KUBERNETES_CA_CERTIFICATE_DATA_SYSTEM_PROPERTY = "kubernetes.certs.ca.data";
   public static final String KUBERNETES_CLIENT_CERTIFICATE_FILE_SYSTEM_PROPERTY = "kubernetes.certs.client.file";
   public static final String KUBERNETES_CLIENT_CERTIFICATE_DATA_SYSTEM_PROPERTY = "kubernetes.certs.client.data";
   public static final String KUBERNETES_CLIENT_KEY_FILE_SYSTEM_PROPERTY = "kubernetes.certs.client.key.file";
   public static final String KUBERNETES_CLIENT_KEY_DATA_SYSTEM_PROPERTY = "kubernetes.certs.client.key.data";
   public static final String KUBERNETES_CLIENT_KEY_ALGO_SYSTEM_PROPERTY = "kubernetes.certs.client.key.algo";
   public static final String KUBERNETES_CLIENT_KEY_PASSPHRASE_SYSTEM_PROPERTY = "kubernetes.certs.client.key.passphrase";
   public static final String KUBERNETES_AUTH_BASIC_USERNAME_SYSTEM_PROPERTY = "kubernetes.auth.basic.username";
   public static final String KUBERNETES_AUTH_BASIC_PASSWORD_SYSTEM_PROPERTY = "kubernetes.auth.basic.password";
   public static final String KUBERNETES_AUTH_TRYKUBECONFIG_SYSTEM_PROPERTY = "kubernetes.auth.tryKubeConfig";
   public static final String KUBERNETES_AUTH_TRYSERVICEACCOUNT_SYSTEM_PROPERTY = "kubernetes.auth.tryServiceAccount";
   public static final String KUBERNETES_AUTH_SERVICEACCOUNT_TOKEN_FILE_SYSTEM_PROPERTY = "kubernetes.auth.serviceAccount.token";
   public static final String KUBERNETES_OAUTH_TOKEN_SYSTEM_PROPERTY = "kubernetes.auth.token";
   public static final String KUBERNETES_WATCH_RECONNECT_INTERVAL_SYSTEM_PROPERTY = "kubernetes.watch.reconnectInterval";
   public static final String KUBERNETES_WATCH_RECONNECT_LIMIT_SYSTEM_PROPERTY = "kubernetes.watch.reconnectLimit";
   public static final String KUBERNETES_CONNECTION_TIMEOUT_SYSTEM_PROPERTY = "kubernetes.connection.timeout";
   public static final String KUBERNETES_UPLOAD_REQUEST_TIMEOUT_SYSTEM_PROPERTY = "kubernetes.upload.request.timeout";
   public static final String KUBERNETES_REQUEST_TIMEOUT_SYSTEM_PROPERTY = "kubernetes.request.timeout";
   public static final String KUBERNETES_REQUEST_RETRY_BACKOFFLIMIT_SYSTEM_PROPERTY = "kubernetes.request.retry.backoffLimit";
   public static final String KUBERNETES_REQUEST_RETRY_BACKOFFINTERVAL_SYSTEM_PROPERTY = "kubernetes.request.retry.backoffInterval";
   public static final String KUBERNETES_LOGGING_INTERVAL_SYSTEM_PROPERTY = "kubernetes.logging.interval";
   public static final String KUBERNETES_SCALE_TIMEOUT_SYSTEM_PROPERTY = "kubernetes.scale.timeout";
   public static final String KUBERNETES_WEBSOCKET_PING_INTERVAL_SYSTEM_PROPERTY = "kubernetes.websocket.ping.interval";
   public static final String KUBERNETES_MAX_CONCURRENT_REQUESTS = "kubernetes.max.concurrent.requests";
   public static final String KUBERNETES_MAX_CONCURRENT_REQUESTS_PER_HOST = "kubernetes.max.concurrent.requests.per.host";
   public static final String KUBERNETES_IMPERSONATE_USERNAME = "kubernetes.impersonate.username";
   public static final String KUBERNETES_IMPERSONATE_GROUP = "kubernetes.impersonate.group";
   public static final String KUBERNETES_TRUSTSTORE_PASSPHRASE_PROPERTY = "kubernetes.truststore.passphrase";
   public static final String KUBERNETES_TRUSTSTORE_FILE_PROPERTY = "kubernetes.truststore.file";
   public static final String KUBERNETES_KEYSTORE_PASSPHRASE_PROPERTY = "kubernetes.keystore.passphrase";
   public static final String KUBERNETES_KEYSTORE_FILE_PROPERTY = "kubernetes.keystore.file";
   public static final String KUBERNETES_TLS_VERSIONS = "kubernetes.tls.versions";
   public static final String KUBERNETES_TRYNAMESPACE_PATH_SYSTEM_PROPERTY = "kubernetes.tryNamespacePath";
   public static final String KUBERNETES_NAMESPACE_PATH = "/var/run/secrets/kubernetes.io/serviceaccount/namespace";
   public static final String KUBERNETES_NAMESPACE_FILE = "kubenamespace";
   public static final String KUBERNETES_NAMESPACE_SYSTEM_PROPERTY = "kubernetes.namespace";
   public static final String KUBERNETES_KUBECONFIG_FILE = "kubeconfig";
   public static final String KUBERNETES_SERVICE_HOST_PROPERTY = "KUBERNETES_SERVICE_HOST";
   public static final String KUBERNETES_SERVICE_PORT_PROPERTY = "KUBERNETES_SERVICE_PORT";
   public static final String KUBERNETES_SERVICE_ACCOUNT_TOKEN_PATH = "/var/run/secrets/kubernetes.io/serviceaccount/token";
   public static final String KUBERNETES_SERVICE_ACCOUNT_CA_CRT_PATH = "/var/run/secrets/kubernetes.io/serviceaccount/ca.crt";
   public static final String KUBERNETES_HTTP2_DISABLE = "http2.disable";
   public static final String KUBERNETES_HTTP_PROXY = "http.proxy";
   public static final String KUBERNETES_HTTPS_PROXY = "https.proxy";
   public static final String KUBERNETES_ALL_PROXY = "all.proxy";
   public static final String KUBERNETES_NO_PROXY = "no.proxy";
   public static final String KUBERNETES_PROXY_USERNAME = "proxy.username";
   public static final String KUBERNETES_PROXY_PASSWORD = "proxy.password";
   public static final String KUBERNETES_USER_AGENT = "kubernetes.user.agent";
   public static final String DEFAULT_MASTER_URL = "https://kubernetes.default.svc";
   public static final Long DEFAULT_SCALE_TIMEOUT = 600000L;
   public static final int DEFAULT_REQUEST_TIMEOUT = 10000;
   public static final int DEFAULT_LOGGING_INTERVAL = 20000;
   public static final Long DEFAULT_WEBSOCKET_PING_INTERVAL = 30000L;
   public static final Integer DEFAULT_MAX_CONCURRENT_REQUESTS = 64;
   public static final Integer DEFAULT_MAX_CONCURRENT_REQUESTS_PER_HOST = 5;
   public static final Integer DEFAULT_REQUEST_RETRY_BACKOFFLIMIT = 10;
   public static final Integer DEFAULT_REQUEST_RETRY_BACKOFFINTERVAL = 100;
   public static final int DEFAULT_UPLOAD_REQUEST_TIMEOUT = 120000;
   public static final String HTTP_PROTOCOL_PREFIX = "http://";
   public static final String HTTPS_PROTOCOL_PREFIX = "https://";
   public static final String SOCKS5_PROTOCOL_PREFIX = "socks5://";
   private static final int DEFAULT_WATCH_RECONNECT_INTERVAL = 1000;
   private static final int DEFAULT_CONNECTION_TIMEOUT = 10000;
   private static final String DEFAULT_CLIENT_KEY_PASSPHRASE = "changeit";
   private Boolean trustCerts;
   private Boolean disableHostnameVerification;
   private String masterUrl;
   private String apiVersion;
   private String namespace;
   private Boolean defaultNamespace;
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
   private volatile String oauthToken;
   @JsonIgnore
   private volatile String autoOAuthToken;
   private OAuthTokenProvider oauthTokenProvider;
   private Long websocketPingInterval;
   private Integer connectionTimeout;
   private Integer maxConcurrentRequests;
   private Integer maxConcurrentRequestsPerHost;
   private final RequestConfig requestConfig;
   private List contexts;
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
   private String[] impersonateGroups;
   private Map impersonateExtras;
   private Boolean http2Disable;
   private String httpProxy;
   private String httpsProxy;
   private String proxyUsername;
   private String proxyPassword;
   private String[] noProxy;
   private String userAgent;
   private TlsVersion[] tlsVersions;
   private Boolean onlyHttpWatches;
   private Map customHeaders;
   private Boolean autoConfigure;
   @JsonIgnore
   protected Map additionalProperties;

   protected static boolean disableAutoConfig() {
      return Utils.getSystemPropertyOrEnvVar("kubernetes.disable.autoConfig", false);
   }

   public static Config empty() {
      return new Config(false);
   }

   public static Config autoConfigure(String context) {
      Config config = new Config(false);
      autoConfigure(config, context);
      return config;
   }

   private static void autoConfigure(Config config, String context) {
      Collection<File> kubeConfigFiles = findKubeConfigFiles();
      if (!kubeConfigFiles.isEmpty()) {
         io.fabric8.kubernetes.api.model.Config[] kubeconfigs = (io.fabric8.kubernetes.api.model.Config[])kubeConfigFiles.stream().map(KubeConfigUtils::parseConfig).toArray((x$0) -> new io.fabric8.kubernetes.api.model.Config[x$0]);
         KubeConfigUtils.merge(config, context, kubeconfigs);
      } else {
         tryServiceAccount(config);
         tryNamespaceFromPath(config);
      }

      postAutoConfigure(config);
      config.autoConfigure = true;
   }

   private static void postAutoConfigure(Config config) {
      configFromSysPropsOrEnvVars(config);
      config.masterUrl = ensureHttps(config.masterUrl, config);
      config.masterUrl = ensureEndsWithSlash(config.masterUrl);
   }

   private static String ensureEndsWithSlash(String masterUrl) {
      if (!masterUrl.endsWith("/")) {
         masterUrl = masterUrl + "/";
      }

      return masterUrl;
   }

   private static String ensureHttps(String masterUrl, Config config) {
      if (!masterUrl.toLowerCase(Locale.ROOT).startsWith("http://") && !masterUrl.toLowerCase(Locale.ROOT).startsWith("https://")) {
         String var10000 = SSLUtils.isHttpsAvailable(config) ? "https://" : "http://";
         masterUrl = var10000 + masterUrl;
      }

      return masterUrl;
   }

   protected Config(boolean autoConfigure) {
      this((String)null, (String)null, (String)null, (Boolean)null, (Boolean)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Long)null, (Integer)null, (Integer)null, (Integer)null, (Boolean)null, (String)null, (String)null, (String[])null, (String)null, (TlsVersion[])null, (Long)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String[])null, (Map)null, (OAuthTokenProvider)null, (Map)null, (Integer)null, (Integer)null, (Integer)null, (Boolean)null, (NamedContext)null, (List)null, autoConfigure, true);
   }

   @JsonCreator
   public Config(@JsonProperty("masterUrl") String masterUrl, @JsonProperty("apiVersion") String apiVersion, @JsonProperty("namespace") String namespace, @JsonProperty("trustCerts") Boolean trustCerts, @JsonProperty("disableHostnameVerification") Boolean disableHostnameVerification, @JsonProperty("caCertFile") String caCertFile, @JsonProperty("caCertData") String caCertData, @JsonProperty("clientCertFile") String clientCertFile, @JsonProperty("clientCertData") String clientCertData, @JsonProperty("clientKeyFile") String clientKeyFile, @JsonProperty("clientKeyData") String clientKeyData, @JsonProperty("clientKeyAlgo") String clientKeyAlgo, @JsonProperty("clientKeyPassphrase") String clientKeyPassphrase, @JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("oauthToken") String oauthToken, @JsonProperty("autoOAuthToken") String autoOAuthToken, @JsonProperty("watchReconnectInterval") Integer watchReconnectInterval, @JsonProperty("watchReconnectLimit") Integer watchReconnectLimit, @JsonProperty("connectionTimeout") Integer connectionTimeout, @JsonProperty("requestTimeout") Integer requestTimeout, @JsonProperty("scaleTimeout") Long scaleTimeout, @JsonProperty("loggingInterval") Integer loggingInterval, @JsonProperty("maxConcurrentRequests") Integer maxConcurrentRequests, @JsonProperty("maxConcurrentRequestsPerHost") Integer maxConcurrentRequestsPerHost, @JsonProperty("http2Disable") Boolean http2Disable, @JsonProperty("httpProxy") String httpProxy, @JsonProperty("httpsProxy") String httpsProxy, @JsonProperty("noProxy") String[] noProxy, @JsonProperty("userAgent") String userAgent, @JsonProperty("tlsVersions") TlsVersion[] tlsVersions, @JsonProperty("websocketPingInterval") Long websocketPingInterval, @JsonProperty("proxyUsername") String proxyUsername, @JsonProperty("proxyPassword") String proxyPassword, @JsonProperty("trustStoreFile") String trustStoreFile, @JsonProperty("trustStorePassphrase") String trustStorePassphrase, @JsonProperty("keyStoreFile") String keyStoreFile, @JsonProperty("keyStorePassphrase") String keyStorePassphrase, @JsonProperty("impersonateUsername") String impersonateUsername, @JsonProperty("impersonateGroups") String[] impersonateGroups, @JsonProperty("impersonateExtras") Map impersonateExtras, @JsonProperty("oauthTokenProvider") OAuthTokenProvider oauthTokenProvider, @JsonProperty("customHeaders") Map customHeaders, @JsonProperty("requestRetryBackoffLimit") Integer requestRetryBackoffLimit, @JsonProperty("requestRetryBackoffInterval") Integer requestRetryBackoffInterval, @JsonProperty("uploadRequestTimeout") Integer uploadRequestTimeout, @JsonProperty("onlyHttpWatches") Boolean onlyHttpWatches, @JsonProperty("currentContext") NamedContext currentContext, @JsonProperty("contexts") List contexts, @JsonProperty("autoConfigure") Boolean autoConfigure) {
      this(masterUrl, apiVersion, namespace, trustCerts, disableHostnameVerification, caCertFile, caCertData, clientCertFile, clientCertData, clientKeyFile, clientKeyData, clientKeyAlgo, clientKeyPassphrase, username, password, oauthToken, autoOAuthToken, watchReconnectInterval, watchReconnectLimit, connectionTimeout, requestTimeout, scaleTimeout, loggingInterval, maxConcurrentRequests, maxConcurrentRequestsPerHost, http2Disable, httpProxy, httpsProxy, noProxy, userAgent, tlsVersions, websocketPingInterval, proxyUsername, proxyPassword, trustStoreFile, trustStorePassphrase, keyStoreFile, keyStorePassphrase, impersonateUsername, impersonateGroups, impersonateExtras, oauthTokenProvider, customHeaders, requestRetryBackoffLimit, requestRetryBackoffInterval, uploadRequestTimeout, onlyHttpWatches, currentContext, contexts, autoConfigure, true);
   }

   Config(String masterUrl, String apiVersion, String namespace, Boolean trustCerts, Boolean disableHostnameVerification, String caCertFile, String caCertData, String clientCertFile, String clientCertData, String clientKeyFile, String clientKeyData, String clientKeyAlgo, String clientKeyPassphrase, String username, String password, String oauthToken, String autoOAuthToken, Integer watchReconnectInterval, Integer watchReconnectLimit, Integer connectionTimeout, Integer requestTimeout, Long scaleTimeout, Integer loggingInterval, Integer maxConcurrentRequests, Integer maxConcurrentRequestsPerHost, Boolean http2Disable, String httpProxy, String httpsProxy, String[] noProxy, String userAgent, TlsVersion[] tlsVersions, Long websocketPingInterval, String proxyUsername, String proxyPassword, String trustStoreFile, String trustStorePassphrase, String keyStoreFile, String keyStorePassphrase, String impersonateUsername, String[] impersonateGroups, Map impersonateExtras, OAuthTokenProvider oauthTokenProvider, Map customHeaders, Integer requestRetryBackoffLimit, Integer requestRetryBackoffInterval, Integer uploadRequestTimeout, Boolean onlyHttpWatches, NamedContext currentContext, List contexts, Boolean autoConfigure, Boolean shouldSetDefaultValues) {
      this.currentContext = null;
      this.customHeaders = null;
      this.additionalProperties = new HashMap();
      if (Boolean.TRUE.equals(shouldSetDefaultValues)) {
         this.masterUrl = "https://kubernetes.default.svc";
         this.apiVersion = "v1";
         this.defaultNamespace = true;
         this.trustCerts = false;
         this.disableHostnameVerification = false;
         this.onlyHttpWatches = false;
         this.http2Disable = false;
         this.clientKeyAlgo = "RSA";
         this.clientKeyPassphrase = "changeit";
         this.websocketPingInterval = DEFAULT_WEBSOCKET_PING_INTERVAL;
         this.connectionTimeout = 10000;
         this.maxConcurrentRequests = DEFAULT_MAX_CONCURRENT_REQUESTS;
         this.maxConcurrentRequestsPerHost = DEFAULT_MAX_CONCURRENT_REQUESTS_PER_HOST;
         this.contexts = new ArrayList();
         this.userAgent = "fabric8-kubernetes-client/" + Version.clientVersion();
         this.tlsVersions = new TlsVersion[]{TlsVersion.TLS_1_3, TlsVersion.TLS_1_2};
         this.requestConfig = new RequestConfig(-1, 1000, 10000, DEFAULT_SCALE_TIMEOUT, 20000, DEFAULT_REQUEST_RETRY_BACKOFFLIMIT, DEFAULT_REQUEST_RETRY_BACKOFFINTERVAL, 120000);
      } else {
         this.requestConfig = new RequestConfig((Integer)null, (Integer)null, (Integer)null, (Long)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null);
      }

      if (Boolean.TRUE.equals(autoConfigure)) {
         autoConfigure(this, (String)null);
      }

      if (Utils.isNotNullOrEmpty(apiVersion)) {
         this.apiVersion = apiVersion;
      }

      if (Utils.isNotNullOrEmpty(masterUrl)) {
         this.masterUrl = masterUrl;
      }

      if (Utils.isNotNullOrEmpty(namespace)) {
         this.namespace = namespace;
      }

      if (Boolean.TRUE.equals(trustCerts)) {
         this.trustCerts = true;
      }

      if (Boolean.TRUE.equals(disableHostnameVerification)) {
         this.disableHostnameVerification = true;
      }

      if (Utils.isNotNullOrEmpty(caCertFile)) {
         this.caCertFile = caCertFile;
      }

      if (Utils.isNotNullOrEmpty(caCertData)) {
         this.caCertData = caCertData;
      }

      if (Utils.isNotNullOrEmpty(clientCertFile)) {
         this.clientCertFile = clientCertFile;
      }

      if (Utils.isNotNullOrEmpty(clientCertData)) {
         this.clientCertData = clientCertData;
      }

      if (Utils.isNotNullOrEmpty(clientKeyFile)) {
         this.clientKeyFile = clientKeyFile;
      }

      if (Utils.isNotNullOrEmpty(clientKeyData)) {
         this.clientKeyData = clientKeyData;
      }

      if (Utils.isNotNullOrEmpty(clientKeyAlgo)) {
         this.clientKeyAlgo = clientKeyAlgo;
      }

      if (Utils.isNotNullOrEmpty(clientKeyPassphrase)) {
         this.clientKeyPassphrase = clientKeyPassphrase;
      }

      if (Utils.isNotNullOrEmpty(username)) {
         this.username = username;
      }

      if (Utils.isNotNullOrEmpty(password)) {
         this.password = password;
      }

      if (Utils.isNotNullOrEmpty(oauthToken)) {
         this.oauthToken = oauthToken;
      }

      if (websocketPingInterval != null) {
         this.websocketPingInterval = websocketPingInterval;
      }

      if (connectionTimeout != null) {
         this.connectionTimeout = connectionTimeout;
      }

      if (watchReconnectLimit != null) {
         this.setWatchReconnectLimit(watchReconnectLimit);
      }

      if (watchReconnectInterval != null) {
         this.setWatchReconnectInterval(watchReconnectInterval);
      }

      if (requestTimeout != null) {
         this.setRequestTimeout(requestTimeout);
      }

      if (scaleTimeout != null) {
         this.setScaleTimeout(scaleTimeout);
      }

      if (loggingInterval != null) {
         this.setLoggingInterval(loggingInterval);
      }

      if (requestRetryBackoffLimit != null) {
         this.setRequestRetryBackoffLimit(requestRetryBackoffLimit);
      }

      if (requestRetryBackoffInterval != null) {
         this.setRequestRetryBackoffInterval(requestRetryBackoffInterval);
      }

      if (uploadRequestTimeout != null) {
         this.setUploadRequestTimeout(uploadRequestTimeout);
      }

      if (Utils.isNotNullOrEmpty(impersonateUsername)) {
         this.setImpersonateUsername(impersonateUsername);
      }

      if (Utils.isNotNullOrEmpty(impersonateGroups)) {
         this.setImpersonateGroups(impersonateGroups);
      }

      if (Utils.isNotNullOrEmpty(impersonateExtras)) {
         this.setImpersonateExtras(impersonateExtras);
      }

      if (http2Disable != null) {
         this.http2Disable = http2Disable;
      }

      if (Utils.isNotNullOrEmpty(httpProxy)) {
         this.httpProxy = httpProxy;
      }

      if (Utils.isNotNullOrEmpty(httpsProxy)) {
         this.httpsProxy = httpsProxy;
      }

      if (Utils.isNotNullOrEmpty(noProxy)) {
         this.noProxy = noProxy;
      }

      if (Utils.isNotNullOrEmpty(proxyUsername)) {
         this.proxyUsername = proxyUsername;
      }

      if (Utils.isNotNullOrEmpty(proxyPassword)) {
         this.proxyPassword = proxyPassword;
      }

      if (Utils.isNotNullOrEmpty(userAgent)) {
         this.userAgent = userAgent;
      }

      if (tlsVersions != null && tlsVersions.length > 0) {
         this.tlsVersions = tlsVersions;
      }

      if (Utils.isNotNullOrEmpty(trustStoreFile)) {
         this.trustStoreFile = trustStoreFile;
      }

      if (Utils.isNotNullOrEmpty(trustStorePassphrase)) {
         this.trustStorePassphrase = trustStorePassphrase;
      }

      if (Utils.isNotNullOrEmpty(keyStoreFile)) {
         this.keyStoreFile = keyStoreFile;
      }

      if (Utils.isNotNullOrEmpty(keyStorePassphrase)) {
         this.keyStorePassphrase = keyStorePassphrase;
      }

      if (maxConcurrentRequests != null) {
         this.maxConcurrentRequests = maxConcurrentRequests;
      }

      if (maxConcurrentRequestsPerHost != null) {
         this.maxConcurrentRequestsPerHost = maxConcurrentRequestsPerHost;
      }

      if (Utils.isNotNullOrEmpty(autoOAuthToken)) {
         this.autoOAuthToken = autoOAuthToken;
      }

      if (contexts != null && !contexts.isEmpty()) {
         this.contexts = contexts;
      }

      if (Utils.isNotNull(currentContext)) {
         this.currentContext = currentContext;
      }

      if (Utils.isNotNullOrEmpty(this.masterUrl)) {
         this.masterUrl = ensureEndsWithSlash(ensureHttps(this.masterUrl, this));
      }

      this.autoConfigure = autoConfigure;
      this.oauthTokenProvider = oauthTokenProvider;
      this.customHeaders = customHeaders;
      this.onlyHttpWatches = onlyHttpWatches;
   }

   public static void configFromSysPropsOrEnvVars(Config config) {
      config.setTrustCerts(Utils.getSystemPropertyOrEnvVar("kubernetes.trust.certificates", config.isTrustCerts()));
      config.setDisableHostnameVerification(Utils.getSystemPropertyOrEnvVar("kubernetes.disable.hostname.verification", config.isDisableHostnameVerification()));
      config.setMasterUrl(Utils.getSystemPropertyOrEnvVar("kubernetes.master", config.getMasterUrl()));
      config.setApiVersion(Utils.getSystemPropertyOrEnvVar("kubernetes.api.version", config.getApiVersion()));
      config.setNamespace(Utils.getSystemPropertyOrEnvVar("kubernetes.namespace", config.getNamespace()));
      config.setCaCertFile(Utils.getSystemPropertyOrEnvVar("kubernetes.certs.ca.file", config.getCaCertFile()));
      config.setCaCertData(Utils.getSystemPropertyOrEnvVar("kubernetes.certs.ca.data", config.getCaCertData()));
      config.setClientCertFile(Utils.getSystemPropertyOrEnvVar("kubernetes.certs.client.file", config.getClientCertFile()));
      config.setClientCertData(Utils.getSystemPropertyOrEnvVar("kubernetes.certs.client.data", config.getClientCertData()));
      config.setClientKeyFile(Utils.getSystemPropertyOrEnvVar("kubernetes.certs.client.key.file", config.getClientKeyFile()));
      config.setClientKeyData(Utils.getSystemPropertyOrEnvVar("kubernetes.certs.client.key.data", config.getClientKeyData()));
      config.setClientKeyAlgo(getKeyAlgorithm(config.getClientKeyFile(), config.getClientKeyData()));
      config.setClientKeyPassphrase(Utils.getSystemPropertyOrEnvVar("kubernetes.certs.client.key.passphrase", config.getClientKeyPassphrase()));
      config.setUserAgent(Utils.getSystemPropertyOrEnvVar("kubernetes.user.agent", config.getUserAgent()));
      config.setTrustStorePassphrase(Utils.getSystemPropertyOrEnvVar("kubernetes.truststore.passphrase", config.getTrustStorePassphrase()));
      config.setTrustStoreFile(Utils.getSystemPropertyOrEnvVar("kubernetes.truststore.file", config.getTrustStoreFile()));
      config.setKeyStorePassphrase(Utils.getSystemPropertyOrEnvVar("kubernetes.keystore.passphrase", config.getKeyStorePassphrase()));
      config.setKeyStoreFile(Utils.getSystemPropertyOrEnvVar("kubernetes.keystore.file", config.getKeyStoreFile()));
      config.setAutoOAuthToken(Utils.getSystemPropertyOrEnvVar("kubernetes.auth.token", config.getAutoOAuthToken()));
      config.setUsername(Utils.getSystemPropertyOrEnvVar("kubernetes.auth.basic.username", config.getUsername()));
      config.setPassword(Utils.getSystemPropertyOrEnvVar("kubernetes.auth.basic.password", config.getPassword()));
      config.setImpersonateUsername(Utils.getSystemPropertyOrEnvVar("kubernetes.impersonate.username", config.getImpersonateUsername()));
      String configuredImpersonateGroups = Utils.getSystemPropertyOrEnvVar("kubernetes.impersonate.group", (String)Arrays.stream((String[])Optional.ofNullable(config.getImpersonateGroups()).orElse(new String[0])).collect(Collectors.joining(",")));
      if (Utils.isNotNullOrEmpty(configuredImpersonateGroups)) {
         config.setImpersonateGroups(configuredImpersonateGroups.split(","));
      }

      String configuredWatchReconnectInterval = Utils.getSystemPropertyOrEnvVar("kubernetes.watch.reconnectInterval");
      if (configuredWatchReconnectInterval != null) {
         config.setWatchReconnectInterval(Integer.parseInt(configuredWatchReconnectInterval));
      }

      String configuredWatchReconnectLimit = Utils.getSystemPropertyOrEnvVar("kubernetes.watch.reconnectLimit");
      if (configuredWatchReconnectLimit != null) {
         config.setWatchReconnectLimit(Integer.parseInt(configuredWatchReconnectLimit));
      }

      String configuredScaleTimeout = Utils.getSystemPropertyOrEnvVar("kubernetes.scale.timeout", String.valueOf(DEFAULT_SCALE_TIMEOUT));
      if (configuredScaleTimeout != null) {
         config.setScaleTimeout(Long.parseLong(configuredScaleTimeout));
      }

      String configuredLoggingInterval = Utils.getSystemPropertyOrEnvVar("kubernetes.logging.interval", String.valueOf(20000));
      if (configuredLoggingInterval != null) {
         config.setLoggingInterval(Integer.parseInt(configuredLoggingInterval));
      }

      config.setConnectionTimeout(Utils.getSystemPropertyOrEnvVar("kubernetes.connection.timeout", config.getConnectionTimeout()));
      config.setUploadRequestTimeout(Utils.getSystemPropertyOrEnvVar("kubernetes.upload.request.timeout", config.getUploadRequestTimeout()));
      config.setRequestTimeout(Utils.getSystemPropertyOrEnvVar("kubernetes.request.timeout", config.getRequestTimeout()));
      config.setRequestRetryBackoffLimit(Utils.getSystemPropertyOrEnvVar("kubernetes.request.retry.backoffLimit", config.getRequestRetryBackoffLimit()));
      config.setRequestRetryBackoffInterval(Utils.getSystemPropertyOrEnvVar("kubernetes.request.retry.backoffInterval", config.getRequestRetryBackoffInterval()));
      String configuredWebsocketPingInterval = Utils.getSystemPropertyOrEnvVar("kubernetes.websocket.ping.interval", String.valueOf(config.getWebsocketPingInterval()));
      if (configuredWebsocketPingInterval != null) {
         config.setWebsocketPingInterval(Long.parseLong(configuredWebsocketPingInterval));
      }

      String configuredMaxConcurrentRequests = Utils.getSystemPropertyOrEnvVar("kubernetes.max.concurrent.requests", String.valueOf(config.getMaxConcurrentRequests()));
      if (configuredMaxConcurrentRequests != null) {
         config.setMaxConcurrentRequests(Integer.parseInt(configuredMaxConcurrentRequests));
      }

      String configuredMaxConcurrentReqeustsPerHost = Utils.getSystemPropertyOrEnvVar("kubernetes.max.concurrent.requests.per.host", String.valueOf(config.getMaxConcurrentRequestsPerHost()));
      if (configuredMaxConcurrentReqeustsPerHost != null) {
         config.setMaxConcurrentRequestsPerHost(Integer.parseInt(configuredMaxConcurrentReqeustsPerHost));
      }

      config.setHttp2Disable(Utils.getSystemPropertyOrEnvVar("http2.disable", config.isHttp2Disable()));
      if (Utils.isNullOrEmpty(config.getHttpProxy())) {
         config.setHttpProxy(Utils.getSystemPropertyOrEnvVar("all.proxy", config.getHttpProxy()));
         config.setHttpProxy(Utils.getSystemPropertyOrEnvVar("http.proxy", config.getHttpProxy()));
      }

      if (Utils.isNullOrEmpty(config.getHttpsProxy())) {
         config.setHttpsProxy(Utils.getSystemPropertyOrEnvVar("all.proxy", config.getHttpsProxy()));
         config.setHttpsProxy(Utils.getSystemPropertyOrEnvVar("https.proxy", config.getHttpsProxy()));
      }

      config.setProxyUsername(Utils.getSystemPropertyOrEnvVar("proxy.username", config.getProxyUsername()));
      config.setProxyPassword(Utils.getSystemPropertyOrEnvVar("proxy.password", config.getProxyPassword()));
      String noProxyVar = Utils.getSystemPropertyOrEnvVar("no.proxy");
      if (noProxyVar != null) {
         config.setNoProxy(noProxyVar.split(","));
      }

      String tlsVersionsVar = Utils.getSystemPropertyOrEnvVar("kubernetes.tls.versions");
      if (tlsVersionsVar != null && !tlsVersionsVar.isEmpty()) {
         String[] tlsVersionsSplit = tlsVersionsVar.split(",");
         TlsVersion[] tlsVersions = new TlsVersion[tlsVersionsSplit.length];

         for(int i = 0; i < tlsVersionsSplit.length; ++i) {
            tlsVersions[i] = TlsVersion.forJavaName(tlsVersionsSplit[i]);
         }

         config.setTlsVersions(tlsVersions);
      }

   }

   private static boolean tryServiceAccount(Config config) {
      LOGGER.debug("Trying to configure client from service account...");
      String masterHost = Utils.getSystemPropertyOrEnvVar("KUBERNETES_SERVICE_HOST", (String)null);
      String masterPort = Utils.getSystemPropertyOrEnvVar("KUBERNETES_SERVICE_PORT", (String)null);
      String caCertPath = Utils.getSystemPropertyOrEnvVar("kubernetes.certs.ca.file", "/var/run/secrets/kubernetes.io/serviceaccount/ca.crt");
      if (masterHost != null && masterPort != null) {
         String hostPort = joinHostPort(masterHost, masterPort);
         LOGGER.debug("Found service account host and port: {}", hostPort);
         config.setMasterUrl("https://" + hostPort);
      }

      if (Utils.getSystemPropertyOrEnvVar("kubernetes.auth.tryServiceAccount", true)) {
         boolean serviceAccountCaCertExists = Files.isRegularFile((new File(caCertPath)).toPath(), new LinkOption[0]);
         if (serviceAccountCaCertExists) {
            LOGGER.debug("Found service account ca cert at: [{}}].", caCertPath);
            config.setCaCertFile(caCertPath);
         } else {
            LOGGER.debug("Did not find service account ca cert at: [{}}].", caCertPath);
         }

         File saTokenPathFile = findServiceAccountTokenFile();
         if (saTokenPathFile != null) {
            String saTokenPathLocation = saTokenPathFile.getAbsolutePath();

            try {
               String serviceTokenCandidate = new String(Files.readAllBytes(saTokenPathFile.toPath()));
               LOGGER.debug("Found service account token at: [{}].", saTokenPathLocation);
               config.setAutoOAuthToken(serviceTokenCandidate);
               return true;
            } catch (IOException var8) {
               LOGGER.warn("Error reading service account token from: [{}]. Ignoring.", saTokenPathLocation);
            }
         }
      }

      return false;
   }

   private static File findServiceAccountTokenFile() {
      String saTokenPath = Utils.getSystemPropertyOrEnvVar("kubernetes.auth.serviceAccount.token");
      File saTokenPathFile;
      if (saTokenPath != null) {
         saTokenPathFile = new File(saTokenPath);
      } else {
         saTokenPathFile = new File("/var/run/secrets/kubernetes.io/serviceaccount/token");
         if (!saTokenPathFile.exists()) {
            saTokenPathFile = null;
            LOGGER.debug("Could not find the service account token at the default location: [{}]. Ignoring.", "/var/run/secrets/kubernetes.io/serviceaccount/token");
         }
      }

      return saTokenPathFile;
   }

   private static String joinHostPort(String host, String port) {
      return host.indexOf(58) >= 0 ? "[" + host + "]:" + port : host + ":" + port;
   }

   public static Config fromKubeconfig(String kubeconfigContents) {
      return fromKubeconfig((String)null, (io.fabric8.kubernetes.api.model.Config[])(KubeConfigUtils.parseConfigFromString(kubeconfigContents)));
   }

   public static Config fromKubeconfig(File kubeconfigFile) {
      return fromKubeconfig((String)null, (File)kubeconfigFile);
   }

   public static Config fromKubeconfig(String context, File kubeconfig) {
      return fromKubeconfig(context, KubeConfigUtils.parseConfig(kubeconfig));
   }

   private static Config fromKubeconfig(String context, io.fabric8.kubernetes.api.model.Config... kubeconfigs) {
      Config ret = empty();
      KubeConfigUtils.merge(ret, context, kubeconfigs);
      return ret;
   }

   /** @deprecated */
   @Deprecated(
      since = "7.0.0",
      forRemoval = true
   )
   public static Config fromKubeconfig(String context, String kubeconfigContents, String kubeconfigPath) {
      Config config = new Config(false);
      if (Utils.isNullOrEmpty(kubeconfigContents)) {
         throw new KubernetesClientException("Could not create Config from kubeconfig");
      } else {
         io.fabric8.kubernetes.api.model.Config kubeconfig;
         if (kubeconfigPath != null) {
            kubeconfig = KubeConfigUtils.parseConfig(new File(kubeconfigPath));
         } else {
            kubeconfig = KubeConfigUtils.parseConfigFromString(kubeconfigContents);
         }

         KubeConfigUtils.merge(config, context, kubeconfig);
         if (!disableAutoConfig()) {
            postAutoConfigure(config);
         }

         return config;
      }
   }

   public Config refresh() {
      String currentContextName = this.getCurrentContext() != null ? this.getCurrentContext().getName() : null;
      if (Utils.isNotNullOrEmpty(this.oauthToken)) {
         return this;
      } else if (this.autoConfigure) {
         return autoConfigure(currentContextName);
      } else if (this.getFile() != null) {
         if (loadKubeConfigContents(this.getFile()) == null) {
            return this;
         } else {
            Config refreshedConfig = fromKubeconfig(currentContextName, this.getFile());
            if (!disableAutoConfig()) {
               postAutoConfigure(refreshedConfig);
            }

            return refreshedConfig;
         }
      } else {
         return this;
      }
   }

   private static Collection findKubeConfigFiles() {
      LOGGER.debug("Trying to configure client from Kubernetes config...");
      return (Collection)(!Utils.getSystemPropertyOrEnvVar("kubernetes.auth.tryKubeConfig", true) ? Collections.emptyList() : (Collection)getKubeconfigFilenames().stream().map(File::new).filter((f) -> {
         if (!f.isFile()) {
            LOGGER.debug("Did not find Kubernetes config at: [{}]. Ignoring.", f.getPath());
            return false;
         } else {
            return true;
         }
      }).filter((f) -> Utils.isNotNullOrEmpty(loadKubeConfigContents(f))).collect(Collectors.toList()));
   }

   public static Collection getKubeconfigFilenames() {
      String valueOrDefault = Utils.getSystemPropertyOrEnvVar("kubeconfig", (new File(getHomeDir(), ".kube" + File.separator + "config")).toString());
      return Arrays.asList(valueOrDefault.split(File.pathSeparator));
   }

   private static String loadKubeConfigContents(File kubeConfigFile) {
      try {
         FileReader reader = new FileReader(kubeConfigFile);

         String var2;
         try {
            var2 = IOHelpers.readFully((Reader)reader);
         } catch (Throwable var5) {
            try {
               reader.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }

            throw var5;
         }

         reader.close();
         return var2;
      } catch (IOException e) {
         LOGGER.error("Could not load Kubernetes config file from {}", kubeConfigFile.getPath(), e);
         return null;
      }
   }

   private static boolean tryNamespaceFromPath(Config config) {
      LOGGER.debug("Trying to configure client namespace from Kubernetes service account namespace path...");
      if (Utils.getSystemPropertyOrEnvVar("kubernetes.tryNamespacePath", true)) {
         String serviceAccountNamespace = Utils.getSystemPropertyOrEnvVar("kubenamespace", "/var/run/secrets/kubernetes.io/serviceaccount/namespace");
         boolean serviceAccountNamespaceExists = Files.isRegularFile((new File(serviceAccountNamespace)).toPath(), new LinkOption[0]);
         if (serviceAccountNamespaceExists) {
            LOGGER.debug("Found service account namespace at: [{}].", serviceAccountNamespace);

            try {
               String namespace = new String(Files.readAllBytes((new File(serviceAccountNamespace)).toPath()));
               config.setNamespace(namespace.replace(System.lineSeparator(), ""));
               return true;
            } catch (IOException e) {
               LOGGER.error("Error reading service account namespace from: [" + serviceAccountNamespace + "].", e);
            }
         } else {
            LOGGER.debug("Did not find service account namespace at: [{}]. Ignoring.", serviceAccountNamespace);
         }
      }

      return false;
   }

   private static String getHomeDir() {
      return getHomeDir(Config::isDirectoryAndExists, Config::getSystemEnvVariable);
   }

   private static boolean isDirectoryAndExists(String filePath) {
      File f = new File(filePath);
      return f.exists() && f.isDirectory();
   }

   private static String getSystemEnvVariable(String envVariableName) {
      return System.getenv(envVariableName);
   }

   protected static String getHomeDir(Predicate directoryExists, UnaryOperator getEnvVar) {
      String home = (String)getEnvVar.apply("HOME");
      if (home != null && !home.isEmpty() && directoryExists.test(home)) {
         return home;
      } else {
         String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
         if (osName.startsWith("win")) {
            String homeDrive = (String)getEnvVar.apply("HOMEDRIVE");
            String homePath = (String)getEnvVar.apply("HOMEPATH");
            if (homeDrive != null && !homeDrive.isEmpty() && homePath != null && !homePath.isEmpty()) {
               String homeDir = homeDrive + homePath;
               if (directoryExists.test(homeDir)) {
                  return homeDir;
               }
            }

            String userProfile = (String)getEnvVar.apply("USERPROFILE");
            if (userProfile != null && !userProfile.isEmpty() && directoryExists.test(userProfile)) {
               return userProfile;
            }
         }

         return System.getProperty("user.home", ".");
      }
   }

   public static String getKeyAlgorithm(InputStream inputStream) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

      String var4;
      try {
         String algorithm = null;

         String line;
         while((line = bufferedReader.readLine()) != null) {
            if (line.contains("BEGIN EC PRIVATE KEY")) {
               algorithm = "EC";
            } else if (line.contains("BEGIN RSA PRIVATE KEY")) {
               algorithm = "RSA";
            }
         }

         var4 = algorithm;
      } catch (Throwable var6) {
         try {
            bufferedReader.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      bufferedReader.close();
      return var4;
   }

   public static String getKeyAlgorithm(String clientKeyFile, String clientKeyData) {
      if (Utils.getSystemPropertyOrEnvVar("kubernetes.certs.client.key.algo") != null) {
         return Utils.getSystemPropertyOrEnvVar("kubernetes.certs.client.key.algo");
      } else {
         try {
            if (clientKeyData != null || clientKeyFile != null) {
               ByteArrayInputStream keyInputStream = CertUtils.getInputStreamFromDataOrFile(clientKeyData, clientKeyFile);
               return getKeyAlgorithm(keyInputStream);
            }
         } catch (IOException exception) {
            LOGGER.debug("Failure in determining private key algorithm type, defaulting to RSA {}", exception.getMessage());
         }

         return null;
      }
   }

   @JsonProperty("oauthToken")
   public String getOauthToken() {
      return this.oauthToken;
   }

   public void setOauthToken(String oauthToken) {
      this.oauthToken = oauthToken;
   }

   @JsonProperty("password")
   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @JsonProperty("username")
   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   @JsonProperty("impersonateUsername")
   public String getImpersonateUsername() {
      return this.getRequestConfig().getImpersonateUsername();
   }

   public void setImpersonateUsername(String impersonateUsername) {
      this.requestConfig.setImpersonateUsername(impersonateUsername);
   }

   @JsonProperty("impersonateGroups")
   public String[] getImpersonateGroups() {
      return this.getRequestConfig().getImpersonateGroups();
   }

   public void setImpersonateGroups(String... impersonateGroup) {
      this.requestConfig.setImpersonateGroups(impersonateGroup);
   }

   @JsonProperty("impersonateExtras")
   public Map getImpersonateExtras() {
      return this.getRequestConfig().getImpersonateExtras();
   }

   public void setImpersonateExtras(Map impersonateExtras) {
      this.requestConfig.setImpersonateExtras(impersonateExtras);
   }

   @JsonProperty("clientKeyPassphrase")
   public String getClientKeyPassphrase() {
      return this.clientKeyPassphrase;
   }

   public void setClientKeyPassphrase(String clientKeyPassphrase) {
      this.clientKeyPassphrase = clientKeyPassphrase;
   }

   @JsonProperty("clientKeyAlgo")
   public String getClientKeyAlgo() {
      return this.clientKeyAlgo;
   }

   public void setClientKeyAlgo(String clientKeyAlgo) {
      this.clientKeyAlgo = clientKeyAlgo;
   }

   @JsonProperty("clientKeyData")
   public String getClientKeyData() {
      return this.clientKeyData;
   }

   public void setClientKeyData(String clientKeyData) {
      this.clientKeyData = clientKeyData;
   }

   @JsonProperty("clientKeyFile")
   public String getClientKeyFile() {
      return this.clientKeyFile;
   }

   public void setClientKeyFile(String clientKeyFile) {
      this.clientKeyFile = clientKeyFile;
   }

   @JsonProperty("clientCertData")
   public String getClientCertData() {
      return this.clientCertData;
   }

   public void setClientCertData(String clientCertData) {
      this.clientCertData = clientCertData;
   }

   @JsonProperty("clientCertFile")
   public String getClientCertFile() {
      return this.clientCertFile;
   }

   public void setClientCertFile(String clientCertFile) {
      this.clientCertFile = clientCertFile;
   }

   @JsonProperty("caCertData")
   public String getCaCertData() {
      return this.caCertData;
   }

   public void setCaCertData(String caCertData) {
      this.caCertData = caCertData;
   }

   @JsonProperty("caCertFile")
   public String getCaCertFile() {
      return this.caCertFile;
   }

   public void setCaCertFile(String caCertFile) {
      this.caCertFile = caCertFile;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("masterUrl")
   public String getMasterUrl() {
      return this.masterUrl;
   }

   public void setMasterUrl(String masterUrl) {
      this.masterUrl = masterUrl;
      this.masterUrl = ensureEndsWithSlash(ensureHttps(masterUrl, this));
   }

   @JsonProperty("trustCerts")
   public boolean isTrustCerts() {
      return (Boolean)Optional.ofNullable(this.trustCerts).orElse(false);
   }

   Boolean getTrustCerts() {
      return this.trustCerts;
   }

   public void setTrustCerts(boolean trustCerts) {
      this.trustCerts = trustCerts;
   }

   @JsonProperty("disableHostnameVerification")
   public boolean isDisableHostnameVerification() {
      return (Boolean)Optional.ofNullable(this.disableHostnameVerification).orElse(false);
   }

   Boolean getDisableHostnameVerification() {
      return this.disableHostnameVerification;
   }

   public void setDisableHostnameVerification(boolean disableHostnameVerification) {
      this.disableHostnameVerification = disableHostnameVerification;
   }

   @JsonProperty("watchReconnectInterval")
   public Integer getWatchReconnectInterval() {
      return this.requestConfig.getWatchReconnectInterval();
   }

   public void setWatchReconnectInterval(Integer watchReconnectInterval) {
      this.requestConfig.setWatchReconnectInterval(watchReconnectInterval);
   }

   @JsonProperty("watchReconnectLimit")
   public Integer getWatchReconnectLimit() {
      return this.getRequestConfig().getWatchReconnectLimit();
   }

   public void setWatchReconnectLimit(Integer watchReconnectLimit) {
      this.requestConfig.setWatchReconnectLimit(watchReconnectLimit);
   }

   public static io.fabric8.kubernetes.api.model.ConfigBuilder builder() {
      return new io.fabric8.kubernetes.api.model.ConfigBuilder();
   }

   @JsonProperty("connectionTimeout")
   public Integer getConnectionTimeout() {
      return this.connectionTimeout;
   }

   public void setConnectionTimeout(Integer connectionTimeout) {
      this.connectionTimeout = connectionTimeout;
   }

   @JsonProperty("uploadRequestTimeout")
   public Integer getUploadRequestTimeout() {
      return this.getRequestConfig().getUploadRequestTimeout();
   }

   public void setUploadRequestTimeout(Integer requestTimeout) {
      this.requestConfig.setUploadRequestTimeout(requestTimeout);
   }

   @JsonProperty("requestTimeout")
   public Integer getRequestTimeout() {
      return this.getRequestConfig().getRequestTimeout();
   }

   public void setRequestTimeout(Integer requestTimeout) {
      this.requestConfig.setRequestTimeout(requestTimeout);
   }

   @JsonProperty("requestRetryBackoffLimit")
   public Integer getRequestRetryBackoffLimit() {
      return this.getRequestConfig().getRequestRetryBackoffLimit();
   }

   public void setRequestRetryBackoffLimit(Integer requestRetryBackoffLimit) {
      this.requestConfig.setRequestRetryBackoffLimit(requestRetryBackoffLimit);
   }

   @JsonProperty("requestRetryBackoffInterval")
   public Integer getRequestRetryBackoffInterval() {
      return this.getRequestConfig().getRequestRetryBackoffInterval();
   }

   public void setRequestRetryBackoffInterval(Integer requestRetryBackoffInterval) {
      this.requestConfig.setRequestRetryBackoffInterval(requestRetryBackoffInterval);
   }

   @JsonProperty("scaleTimeout")
   public Long getScaleTimeout() {
      return this.getRequestConfig().getScaleTimeout();
   }

   public void setScaleTimeout(Long scaleTimeout) {
      this.requestConfig.setScaleTimeout(scaleTimeout);
   }

   @JsonProperty("loggingInterval")
   public Integer getLoggingInterval() {
      return this.getRequestConfig().getLoggingInterval();
   }

   public void setLoggingInterval(Integer loggingInterval) {
      this.requestConfig.setLoggingInterval(loggingInterval);
   }

   @JsonProperty("http2Disable")
   public boolean isHttp2Disable() {
      return (Boolean)Optional.ofNullable(this.http2Disable).orElse(false);
   }

   Boolean getHttp2Disable() {
      return this.http2Disable;
   }

   public void setHttp2Disable(Boolean http2Disable) {
      this.http2Disable = http2Disable;
   }

   public void setHttpProxy(String httpProxy) {
      this.httpProxy = httpProxy;
   }

   @JsonProperty("httpProxy")
   public String getHttpProxy() {
      return this.httpProxy;
   }

   public void setHttpsProxy(String httpsProxy) {
      this.httpsProxy = httpsProxy;
   }

   @JsonProperty("httpsProxy")
   public String getHttpsProxy() {
      return this.httpsProxy;
   }

   public void setNoProxy(String[] noProxy) {
      this.noProxy = noProxy;
   }

   @JsonProperty("noProxy")
   public String[] getNoProxy() {
      return this.noProxy;
   }

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("defaultNamespace")
   public boolean isDefaultNamespace() {
      return (Boolean)Optional.ofNullable(this.defaultNamespace).orElse(true);
   }

   public void setDefaultNamespace(boolean defaultNamespace) {
      this.defaultNamespace = defaultNamespace;
   }

   @JsonProperty("userAgent")
   public String getUserAgent() {
      return this.userAgent;
   }

   public void setUserAgent(String userAgent) {
      this.userAgent = userAgent;
   }

   @JsonProperty("tlsVersions")
   public TlsVersion[] getTlsVersions() {
      return this.tlsVersions;
   }

   public void setTlsVersions(TlsVersion[] tlsVersions) {
      this.tlsVersions = tlsVersions;
   }

   @JsonProperty("websocketPingInterval")
   public Long getWebsocketPingInterval() {
      return this.websocketPingInterval;
   }

   public void setWebsocketPingInterval(Long websocketPingInterval) {
      this.websocketPingInterval = websocketPingInterval;
   }

   public Integer getMaxConcurrentRequests() {
      return this.maxConcurrentRequests;
   }

   public void setMaxConcurrentRequests(Integer maxConcurrentRequests) {
      this.maxConcurrentRequests = maxConcurrentRequests;
   }

   public Integer getMaxConcurrentRequestsPerHost() {
      return this.maxConcurrentRequestsPerHost;
   }

   public void setMaxConcurrentRequestsPerHost(Integer maxConcurrentRequestsPerHost) {
      this.maxConcurrentRequestsPerHost = maxConcurrentRequestsPerHost;
   }

   @JsonProperty("proxyUsername")
   public String getProxyUsername() {
      return this.proxyUsername;
   }

   public void setProxyUsername(String proxyUsername) {
      this.proxyUsername = proxyUsername;
   }

   @JsonProperty("proxyPassword")
   public String getProxyPassword() {
      return this.proxyPassword;
   }

   public void setProxyPassword(String proxyPassword) {
      this.proxyPassword = proxyPassword;
   }

   public RequestConfig getRequestConfig() {
      return this.requestConfig;
   }

   public void setTrustStorePassphrase(String trustStorePassphrase) {
      this.trustStorePassphrase = trustStorePassphrase;
   }

   @JsonProperty("trustStorePassphrase")
   public String getTrustStorePassphrase() {
      return this.trustStorePassphrase;
   }

   public void setKeyStorePassphrase(String keyStorePassphrase) {
      this.keyStorePassphrase = keyStorePassphrase;
   }

   @JsonProperty("keyStorePassphrase")
   public String getKeyStorePassphrase() {
      return this.keyStorePassphrase;
   }

   public void setTrustStoreFile(String trustStoreFile) {
      this.trustStoreFile = trustStoreFile;
   }

   @JsonProperty("trustStoreFile")
   public String getTrustStoreFile() {
      return this.trustStoreFile;
   }

   public void setKeyStoreFile(String keyStoreFile) {
      this.keyStoreFile = keyStoreFile;
   }

   @JsonProperty("keyStoreFile")
   public String getKeyStoreFile() {
      return this.keyStoreFile;
   }

   @JsonIgnore
   public OAuthTokenProvider getOauthTokenProvider() {
      return this.oauthTokenProvider;
   }

   public void setOauthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
      this.oauthTokenProvider = oauthTokenProvider;
   }

   @JsonProperty("customHeaders")
   public Map getCustomHeaders() {
      return this.customHeaders;
   }

   public void setCustomHeaders(Map customHeaders) {
      this.customHeaders = customHeaders;
   }

   public Boolean getAutoConfigure() {
      return this.autoConfigure;
   }

   public List getContexts() {
      return this.contexts;
   }

   public void setContexts(List contexts) {
      this.contexts = contexts;
   }

   public NamedContext getCurrentContext() {
      return this.currentContext;
   }

   public void setCurrentContext(NamedContext context) {
      this.currentContext = context;
   }

   public File getFile() {
      return KubeConfigUtils.getFileWithNamedContext(this.getCurrentContext());
   }

   public File getFileWithCluster() {
      return KubeConfigUtils.getFileWithNamedCluster(this.getCurrentContext());
   }

   public File getFileWithAuthInfo() {
      return KubeConfigUtils.getFileWithNamedAuthInfo(this.getCurrentContext());
   }

   @JsonIgnore
   public Readiness getReadiness() {
      return Readiness.getInstance();
   }

   public void setAuthProvider(AuthProviderConfig authProvider) {
      this.authProvider = authProvider;
   }

   public AuthProviderConfig getAuthProvider() {
      return this.authProvider;
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public String getAutoOAuthToken() {
      return this.autoOAuthToken;
   }

   public void setAutoOAuthToken(String autoOAuthToken) {
      this.autoOAuthToken = autoOAuthToken;
   }

   public boolean isOnlyHttpWatches() {
      return (Boolean)Optional.ofNullable(this.onlyHttpWatches).orElse(false);
   }

   public void setOnlyHttpWatches(boolean onlyHttpWatches) {
      this.onlyHttpWatches = onlyHttpWatches;
   }

   @JsonIgnoreProperties(
      ignoreUnknown = true
   )
   public static final class ExecCredential {
      public String kind;
      public String apiVersion;
      public ExecCredentialSpec spec;
      public ExecCredentialStatus status;
   }

   @JsonIgnoreProperties(
      ignoreUnknown = true
   )
   public static final class ExecCredentialSpec {
   }

   @JsonIgnoreProperties(
      ignoreUnknown = true
   )
   public static final class ExecCredentialStatus {
      public String token;
      public String clientCertificateData;
      public String clientKeyData;
   }
}
