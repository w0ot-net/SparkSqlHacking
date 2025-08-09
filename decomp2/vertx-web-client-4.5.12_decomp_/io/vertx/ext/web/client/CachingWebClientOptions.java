package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TrustOptions;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class CachingWebClientOptions extends WebClientOptions {
   public static final Set DEFAULT_CACHED_STATUS_CODES = buildDefaultStatusCodes();
   public static final Set DEFAULT_CACHED_METHODS = buildDefaultMethods();
   private boolean enableVaryCaching = false;
   private Set cachedStatusCodes;
   private Set cachedMethods;

   public CachingWebClientOptions() {
      this.cachedStatusCodes = DEFAULT_CACHED_STATUS_CODES;
      this.cachedMethods = DEFAULT_CACHED_METHODS;
   }

   public CachingWebClientOptions(boolean enableVaryCaching) {
      this.cachedStatusCodes = DEFAULT_CACHED_STATUS_CODES;
      this.cachedMethods = DEFAULT_CACHED_METHODS;
      this.enableVaryCaching = enableVaryCaching;
   }

   public CachingWebClientOptions(WebClientOptions other) {
      super(other);
      this.cachedStatusCodes = DEFAULT_CACHED_STATUS_CODES;
      this.cachedMethods = DEFAULT_CACHED_METHODS;
   }

   public CachingWebClientOptions(JsonObject json) {
      super(json);
      this.cachedStatusCodes = DEFAULT_CACHED_STATUS_CODES;
      this.cachedMethods = DEFAULT_CACHED_METHODS;
      CachingWebClientOptionsConverter.fromJson(json, this);
   }

   void init(CachingWebClientOptions other) {
      super.init(other);
      this.enableVaryCaching = other.enableVaryCaching;
      this.cachedStatusCodes = other.cachedStatusCodes;
      this.cachedMethods = other.cachedMethods;
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      CachingWebClientOptionsConverter.toJson(this, json);
      return json;
   }

   public CachingWebClientOptions setEnableVaryCaching(boolean enabled) {
      this.enableVaryCaching = enabled;
      return this;
   }

   public Set getCachedStatusCodes() {
      return this.cachedStatusCodes;
   }

   public CachingWebClientOptions setCachedStatusCodes(Set codes) {
      this.cachedStatusCodes = codes;
      return this;
   }

   public CachingWebClientOptions addCachedStatusCode(Integer code) {
      this.cachedStatusCodes.add(code);
      return this;
   }

   public CachingWebClientOptions removeCachedStatusCode(Integer code) {
      this.cachedStatusCodes.remove(code);
      return this;
   }

   public Set getCachedMethods() {
      return this.cachedMethods;
   }

   public CachingWebClientOptions setCachedMethods(Set methods) {
      this.cachedMethods = methods;
      return this;
   }

   public CachingWebClientOptions addCachedMethod(HttpMethod method) {
      this.cachedMethods.add(method);
      return this;
   }

   public CachingWebClientOptions removeCachedMethod(HttpMethod method) {
      this.cachedMethods.remove(method);
      return this;
   }

   public boolean isVaryCachingEnabled() {
      return this.enableVaryCaching;
   }

   public CachingWebClientOptions setUserAgentEnabled(boolean userAgentEnabled) {
      return (CachingWebClientOptions)super.setUserAgentEnabled(userAgentEnabled);
   }

   public CachingWebClientOptions setUserAgent(String userAgent) {
      return (CachingWebClientOptions)super.setUserAgent(userAgent);
   }

   public CachingWebClientOptions setFollowRedirects(boolean followRedirects) {
      return (CachingWebClientOptions)super.setFollowRedirects(followRedirects);
   }

   public CachingWebClientOptions setMaxRedirects(int maxRedirects) {
      return (CachingWebClientOptions)super.setMaxRedirects(maxRedirects);
   }

   public CachingWebClientOptions setSendBufferSize(int sendBufferSize) {
      return (CachingWebClientOptions)super.setSendBufferSize(sendBufferSize);
   }

   public CachingWebClientOptions setReceiveBufferSize(int receiveBufferSize) {
      return (CachingWebClientOptions)super.setReceiveBufferSize(receiveBufferSize);
   }

   public CachingWebClientOptions setReuseAddress(boolean reuseAddress) {
      return (CachingWebClientOptions)super.setReuseAddress(reuseAddress);
   }

   public CachingWebClientOptions setTrafficClass(int trafficClass) {
      return (CachingWebClientOptions)super.setTrafficClass(trafficClass);
   }

   public CachingWebClientOptions setTcpNoDelay(boolean tcpNoDelay) {
      return (CachingWebClientOptions)super.setTcpNoDelay(tcpNoDelay);
   }

   public CachingWebClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      return (CachingWebClientOptions)super.setTcpKeepAlive(tcpKeepAlive);
   }

   public CachingWebClientOptions setSoLinger(int soLinger) {
      return (CachingWebClientOptions)super.setSoLinger(soLinger);
   }

   public CachingWebClientOptions setIdleTimeout(int idleTimeout) {
      return (CachingWebClientOptions)super.setIdleTimeout(idleTimeout);
   }

   public CachingWebClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      return (CachingWebClientOptions)super.setIdleTimeoutUnit(idleTimeoutUnit);
   }

   public CachingWebClientOptions setSsl(boolean ssl) {
      return (CachingWebClientOptions)super.setSsl(ssl);
   }

   public CachingWebClientOptions setKeyCertOptions(KeyCertOptions options) {
      return (CachingWebClientOptions)super.setKeyCertOptions(options);
   }

   public CachingWebClientOptions setKeyStoreOptions(JksOptions options) {
      return (CachingWebClientOptions)super.setKeyStoreOptions(options);
   }

   public CachingWebClientOptions setPfxKeyCertOptions(PfxOptions options) {
      return (CachingWebClientOptions)super.setPfxKeyCertOptions(options);
   }

   public CachingWebClientOptions setTrustOptions(TrustOptions options) {
      return (CachingWebClientOptions)super.setTrustOptions(options);
   }

   public CachingWebClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return (CachingWebClientOptions)super.setPemKeyCertOptions(options);
   }

   public CachingWebClientOptions setTrustStoreOptions(JksOptions options) {
      return (CachingWebClientOptions)super.setTrustStoreOptions(options);
   }

   public CachingWebClientOptions setPfxTrustOptions(PfxOptions options) {
      return (CachingWebClientOptions)super.setPfxTrustOptions(options);
   }

   public CachingWebClientOptions setPemTrustOptions(PemTrustOptions options) {
      return (CachingWebClientOptions)super.setPemTrustOptions(options);
   }

   public CachingWebClientOptions addEnabledCipherSuite(String suite) {
      return (CachingWebClientOptions)super.addEnabledCipherSuite(suite);
   }

   public CachingWebClientOptions addCrlPath(String crlPath) throws NullPointerException {
      return (CachingWebClientOptions)super.addCrlPath(crlPath);
   }

   public CachingWebClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      return (CachingWebClientOptions)super.addCrlValue(crlValue);
   }

   public CachingWebClientOptions setConnectTimeout(int connectTimeout) {
      return (CachingWebClientOptions)super.setConnectTimeout(connectTimeout);
   }

   public CachingWebClientOptions setTrustAll(boolean trustAll) {
      return (CachingWebClientOptions)super.setTrustAll(trustAll);
   }

   public CachingWebClientOptions setMaxPoolSize(int maxPoolSize) {
      return (CachingWebClientOptions)super.setMaxPoolSize(maxPoolSize);
   }

   public CachingWebClientOptions setHttp2MultiplexingLimit(int limit) {
      return (CachingWebClientOptions)super.setHttp2MultiplexingLimit(limit);
   }

   public CachingWebClientOptions setHttp2MaxPoolSize(int max) {
      return (CachingWebClientOptions)super.setHttp2MaxPoolSize(max);
   }

   public CachingWebClientOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize) {
      return (CachingWebClientOptions)super.setHttp2ConnectionWindowSize(http2ConnectionWindowSize);
   }

   public CachingWebClientOptions setKeepAlive(boolean keepAlive) {
      return (CachingWebClientOptions)super.setKeepAlive(keepAlive);
   }

   public CachingWebClientOptions setPipelining(boolean pipelining) {
      return (CachingWebClientOptions)super.setPipelining(pipelining);
   }

   public CachingWebClientOptions setPipeliningLimit(int limit) {
      return (CachingWebClientOptions)super.setPipeliningLimit(limit);
   }

   public CachingWebClientOptions setVerifyHost(boolean verifyHost) {
      return (CachingWebClientOptions)super.setVerifyHost(verifyHost);
   }

   /** @deprecated */
   @Deprecated
   public CachingWebClientOptions setTryUseCompression(boolean tryUseCompression) {
      return (CachingWebClientOptions)super.setTryUseCompression(tryUseCompression);
   }

   public CachingWebClientOptions setDecompressionSupported(boolean tryUseCompression) {
      return (CachingWebClientOptions)super.setDecompressionSupported(tryUseCompression);
   }

   public CachingWebClientOptions setSendUnmaskedFrames(boolean sendUnmaskedFrames) {
      return (CachingWebClientOptions)super.setSendUnmaskedFrames(sendUnmaskedFrames);
   }

   public CachingWebClientOptions setMaxWebSocketFrameSize(int maxWebsocketFrameSize) {
      return (CachingWebClientOptions)super.setMaxWebSocketFrameSize(maxWebsocketFrameSize);
   }

   public CachingWebClientOptions setDefaultHost(String defaultHost) {
      return (CachingWebClientOptions)super.setDefaultHost(defaultHost);
   }

   public CachingWebClientOptions setDefaultPort(int defaultPort) {
      return (CachingWebClientOptions)super.setDefaultPort(defaultPort);
   }

   public CachingWebClientOptions setMaxChunkSize(int maxChunkSize) {
      return (CachingWebClientOptions)super.setMaxChunkSize(maxChunkSize);
   }

   public CachingWebClientOptions setProtocolVersion(HttpVersion protocolVersion) {
      return (CachingWebClientOptions)super.setProtocolVersion(protocolVersion);
   }

   public CachingWebClientOptions setMaxHeaderSize(int maxHeaderSize) {
      return (CachingWebClientOptions)super.setMaxHeaderSize(maxHeaderSize);
   }

   public CachingWebClientOptions setMaxWaitQueueSize(int maxWaitQueueSize) {
      return (CachingWebClientOptions)super.setMaxWaitQueueSize(maxWaitQueueSize);
   }

   public CachingWebClientOptions setUseAlpn(boolean useAlpn) {
      return (CachingWebClientOptions)super.setUseAlpn(useAlpn);
   }

   public CachingWebClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      return (CachingWebClientOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   public CachingWebClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (CachingWebClientOptions)super.setJdkSslEngineOptions(sslEngineOptions);
   }

   public CachingWebClientOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return (CachingWebClientOptions)super.setOpenSslEngineOptions(sslEngineOptions);
   }

   public CachingWebClientOptions setHttp2ClearTextUpgrade(boolean value) {
      return (CachingWebClientOptions)super.setHttp2ClearTextUpgrade(value);
   }

   public CachingWebClientOptions setAlpnVersions(List alpnVersions) {
      return (CachingWebClientOptions)super.setAlpnVersions(alpnVersions);
   }

   public CachingWebClientOptions setMetricsName(String metricsName) {
      return (CachingWebClientOptions)super.setMetricsName(metricsName);
   }

   public CachingWebClientOptions setProxyOptions(ProxyOptions proxyOptions) {
      return (CachingWebClientOptions)super.setProxyOptions(proxyOptions);
   }

   public CachingWebClientOptions setLocalAddress(String localAddress) {
      return (CachingWebClientOptions)super.setLocalAddress(localAddress);
   }

   public CachingWebClientOptions setLogActivity(boolean logEnabled) {
      return (CachingWebClientOptions)super.setLogActivity(logEnabled);
   }

   public CachingWebClientOptions addEnabledSecureTransportProtocol(String protocol) {
      return (CachingWebClientOptions)super.addEnabledSecureTransportProtocol(protocol);
   }

   public CachingWebClientOptions removeEnabledSecureTransportProtocol(String protocol) {
      return (CachingWebClientOptions)super.removeEnabledSecureTransportProtocol(protocol);
   }

   public CachingWebClientOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      return (CachingWebClientOptions)super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
   }

   public CachingWebClientOptions setReusePort(boolean reusePort) {
      return (CachingWebClientOptions)super.setReusePort(reusePort);
   }

   public CachingWebClientOptions setTcpFastOpen(boolean tcpFastOpen) {
      return (CachingWebClientOptions)super.setTcpFastOpen(tcpFastOpen);
   }

   public CachingWebClientOptions setTcpCork(boolean tcpCork) {
      return (CachingWebClientOptions)super.setTcpCork(tcpCork);
   }

   public CachingWebClientOptions setTcpQuickAck(boolean tcpQuickAck) {
      return (CachingWebClientOptions)super.setTcpQuickAck(tcpQuickAck);
   }

   public CachingWebClientOptions setHttp2KeepAliveTimeout(int keepAliveTimeout) {
      return (CachingWebClientOptions)super.setHttp2KeepAliveTimeout(keepAliveTimeout);
   }

   public CachingWebClientOptions setForceSni(boolean forceSni) {
      return (CachingWebClientOptions)super.setForceSni(forceSni);
   }

   public CachingWebClientOptions setDecoderInitialBufferSize(int decoderInitialBufferSize) {
      return (CachingWebClientOptions)super.setDecoderInitialBufferSize(decoderInitialBufferSize);
   }

   public CachingWebClientOptions setPoolCleanerPeriod(int poolCleanerPeriod) {
      return (CachingWebClientOptions)super.setPoolCleanerPeriod(poolCleanerPeriod);
   }

   public CachingWebClientOptions setKeepAliveTimeout(int keepAliveTimeout) {
      return (CachingWebClientOptions)super.setKeepAliveTimeout(keepAliveTimeout);
   }

   public CachingWebClientOptions setMaxWebSocketMessageSize(int maxWebsocketMessageSize) {
      return (CachingWebClientOptions)super.setMaxWebSocketMessageSize(maxWebsocketMessageSize);
   }

   public CachingWebClientOptions setMaxInitialLineLength(int maxInitialLineLength) {
      return (CachingWebClientOptions)super.setMaxInitialLineLength(maxInitialLineLength);
   }

   public CachingWebClientOptions setInitialSettings(Http2Settings settings) {
      return (CachingWebClientOptions)super.setInitialSettings(settings);
   }

   public CachingWebClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      return (CachingWebClientOptions)super.setSslHandshakeTimeout(sslHandshakeTimeout);
   }

   public CachingWebClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      return (CachingWebClientOptions)super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
   }

   public CachingWebClientOptions setTryUsePerFrameWebSocketCompression(boolean offer) {
      return (CachingWebClientOptions)super.setTryUsePerFrameWebSocketCompression(offer);
   }

   public CachingWebClientOptions setTryUsePerMessageWebSocketCompression(boolean offer) {
      return (CachingWebClientOptions)super.setTryUsePerMessageWebSocketCompression(offer);
   }

   public CachingWebClientOptions setWebSocketCompressionLevel(int compressionLevel) {
      return (CachingWebClientOptions)super.setWebSocketCompressionLevel(compressionLevel);
   }

   public CachingWebClientOptions setWebSocketCompressionAllowClientNoContext(boolean offer) {
      return (CachingWebClientOptions)super.setWebSocketCompressionAllowClientNoContext(offer);
   }

   public CachingWebClientOptions setWebSocketCompressionRequestServerNoContext(boolean offer) {
      return (CachingWebClientOptions)super.setWebSocketCompressionRequestServerNoContext(offer);
   }

   private static Set buildDefaultStatusCodes() {
      Set<Integer> codes = new HashSet(3);
      Collections.addAll(codes, new Integer[]{200, 301, 404});
      return codes;
   }

   private static Set buildDefaultMethods() {
      Set<HttpMethod> methods = new HashSet(1);
      methods.add(HttpMethod.GET);
      return methods;
   }
}
