package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.impl.launcher.commands.VersionCommand;
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
import io.vertx.core.tracing.TracingPolicy;
import io.vertx.uritemplate.ExpandOptions;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class WebClientOptions extends HttpClientOptions {
   public static final boolean DEFAULT_USER_AGENT_ENABLED = true;
   public static final String DEFAULT_USER_AGENT = loadUserAgent();
   public static final boolean DEFAULT_FOLLOW_REDIRECTS = true;
   public static final ExpandOptions DEFAULT_EXPAND_OPTIONS = null;
   private boolean userAgentEnabled = true;
   private String userAgent;
   private boolean followRedirects;
   private ExpandOptions templateExpandOptions;

   public WebClientOptions() {
      this.userAgent = DEFAULT_USER_AGENT;
      this.followRedirects = true;
      this.templateExpandOptions = DEFAULT_EXPAND_OPTIONS;
   }

   public WebClientOptions(WebClientOptions other) {
      super(other);
      this.userAgent = DEFAULT_USER_AGENT;
      this.followRedirects = true;
      this.templateExpandOptions = DEFAULT_EXPAND_OPTIONS;
      this.init(other);
   }

   public WebClientOptions(HttpClientOptions other) {
      super(other);
      this.userAgent = DEFAULT_USER_AGENT;
      this.followRedirects = true;
      this.templateExpandOptions = DEFAULT_EXPAND_OPTIONS;
   }

   public WebClientOptions(JsonObject json) {
      super(json);
      this.userAgent = DEFAULT_USER_AGENT;
      this.followRedirects = true;
      this.templateExpandOptions = DEFAULT_EXPAND_OPTIONS;
      WebClientOptionsConverter.fromJson(json, this);
   }

   void init(WebClientOptions other) {
      this.userAgentEnabled = other.userAgentEnabled;
      this.userAgent = other.userAgent;
      this.followRedirects = other.followRedirects;
      this.templateExpandOptions = other.templateExpandOptions != null ? new ExpandOptions(other.templateExpandOptions) : null;
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      WebClientOptionsConverter.toJson(this, json);
      return json;
   }

   public boolean isUserAgentEnabled() {
      return this.userAgentEnabled;
   }

   public WebClientOptions setUserAgentEnabled(boolean userAgentEnabled) {
      this.userAgentEnabled = userAgentEnabled;
      return this;
   }

   public String getUserAgent() {
      return this.userAgent;
   }

   public WebClientOptions setUserAgent(String userAgent) {
      this.userAgent = userAgent;
      return this;
   }

   public boolean isFollowRedirects() {
      return this.followRedirects;
   }

   public WebClientOptions setFollowRedirects(boolean followRedirects) {
      this.followRedirects = followRedirects;
      return this;
   }

   public ExpandOptions getTemplateExpandOptions() {
      return this.templateExpandOptions;
   }

   public WebClientOptions setTemplateExpandOptions(ExpandOptions templateExpandOptions) {
      this.templateExpandOptions = templateExpandOptions;
      return this;
   }

   public WebClientOptions setMaxRedirects(int maxRedirects) {
      return (WebClientOptions)super.setMaxRedirects(maxRedirects);
   }

   public WebClientOptions setSendBufferSize(int sendBufferSize) {
      return (WebClientOptions)super.setSendBufferSize(sendBufferSize);
   }

   public WebClientOptions setReceiveBufferSize(int receiveBufferSize) {
      return (WebClientOptions)super.setReceiveBufferSize(receiveBufferSize);
   }

   public WebClientOptions setReuseAddress(boolean reuseAddress) {
      return (WebClientOptions)super.setReuseAddress(reuseAddress);
   }

   public WebClientOptions setTrafficClass(int trafficClass) {
      return (WebClientOptions)super.setTrafficClass(trafficClass);
   }

   public WebClientOptions setTcpNoDelay(boolean tcpNoDelay) {
      return (WebClientOptions)super.setTcpNoDelay(tcpNoDelay);
   }

   public WebClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      return (WebClientOptions)super.setTcpKeepAlive(tcpKeepAlive);
   }

   public WebClientOptions setSoLinger(int soLinger) {
      return (WebClientOptions)super.setSoLinger(soLinger);
   }

   public WebClientOptions setIdleTimeout(int idleTimeout) {
      return (WebClientOptions)super.setIdleTimeout(idleTimeout);
   }

   public WebClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      return (WebClientOptions)super.setIdleTimeoutUnit(idleTimeoutUnit);
   }

   public WebClientOptions setSsl(boolean ssl) {
      return (WebClientOptions)super.setSsl(ssl);
   }

   public WebClientOptions setKeyCertOptions(KeyCertOptions options) {
      return (WebClientOptions)super.setKeyCertOptions(options);
   }

   public WebClientOptions setKeyStoreOptions(JksOptions options) {
      return (WebClientOptions)super.setKeyStoreOptions(options);
   }

   public WebClientOptions setPfxKeyCertOptions(PfxOptions options) {
      return (WebClientOptions)super.setPfxKeyCertOptions(options);
   }

   public WebClientOptions setTrustOptions(TrustOptions options) {
      return (WebClientOptions)super.setTrustOptions(options);
   }

   public WebClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return (WebClientOptions)super.setPemKeyCertOptions(options);
   }

   public WebClientOptions setTrustStoreOptions(JksOptions options) {
      return (WebClientOptions)super.setTrustStoreOptions(options);
   }

   public WebClientOptions setPfxTrustOptions(PfxOptions options) {
      return (WebClientOptions)super.setPfxTrustOptions(options);
   }

   public WebClientOptions setPemTrustOptions(PemTrustOptions options) {
      return (WebClientOptions)super.setPemTrustOptions(options);
   }

   public WebClientOptions addEnabledCipherSuite(String suite) {
      return (WebClientOptions)super.addEnabledCipherSuite(suite);
   }

   public WebClientOptions addCrlPath(String crlPath) throws NullPointerException {
      return (WebClientOptions)super.addCrlPath(crlPath);
   }

   public WebClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      return (WebClientOptions)super.addCrlValue(crlValue);
   }

   public WebClientOptions setConnectTimeout(int connectTimeout) {
      return (WebClientOptions)super.setConnectTimeout(connectTimeout);
   }

   public WebClientOptions setTrustAll(boolean trustAll) {
      return (WebClientOptions)super.setTrustAll(trustAll);
   }

   public WebClientOptions setMaxPoolSize(int maxPoolSize) {
      return (WebClientOptions)super.setMaxPoolSize(maxPoolSize);
   }

   public WebClientOptions setHttp2MultiplexingLimit(int limit) {
      return (WebClientOptions)super.setHttp2MultiplexingLimit(limit);
   }

   public WebClientOptions setHttp2MaxPoolSize(int max) {
      return (WebClientOptions)super.setHttp2MaxPoolSize(max);
   }

   public WebClientOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize) {
      return (WebClientOptions)super.setHttp2ConnectionWindowSize(http2ConnectionWindowSize);
   }

   public WebClientOptions setKeepAlive(boolean keepAlive) {
      return (WebClientOptions)super.setKeepAlive(keepAlive);
   }

   public WebClientOptions setPipelining(boolean pipelining) {
      return (WebClientOptions)super.setPipelining(pipelining);
   }

   public WebClientOptions setPipeliningLimit(int limit) {
      return (WebClientOptions)super.setPipeliningLimit(limit);
   }

   public WebClientOptions setVerifyHost(boolean verifyHost) {
      return (WebClientOptions)super.setVerifyHost(verifyHost);
   }

   /** @deprecated */
   @Deprecated
   public WebClientOptions setTryUseCompression(boolean tryUseCompression) {
      return (WebClientOptions)super.setTryUseCompression(tryUseCompression);
   }

   public WebClientOptions setDecompressionSupported(boolean tryUseCompression) {
      return (WebClientOptions)super.setDecompressionSupported(tryUseCompression);
   }

   public WebClientOptions setSendUnmaskedFrames(boolean sendUnmaskedFrames) {
      return (WebClientOptions)super.setSendUnmaskedFrames(sendUnmaskedFrames);
   }

   public WebClientOptions setMaxWebSocketFrameSize(int maxWebsocketFrameSize) {
      return (WebClientOptions)super.setMaxWebSocketFrameSize(maxWebsocketFrameSize);
   }

   public WebClientOptions setDefaultHost(String defaultHost) {
      return (WebClientOptions)super.setDefaultHost(defaultHost);
   }

   public WebClientOptions setDefaultPort(int defaultPort) {
      return (WebClientOptions)super.setDefaultPort(defaultPort);
   }

   public WebClientOptions setMaxChunkSize(int maxChunkSize) {
      return (WebClientOptions)super.setMaxChunkSize(maxChunkSize);
   }

   public WebClientOptions setProtocolVersion(HttpVersion protocolVersion) {
      return (WebClientOptions)super.setProtocolVersion(protocolVersion);
   }

   public WebClientOptions setMaxHeaderSize(int maxHeaderSize) {
      return (WebClientOptions)super.setMaxHeaderSize(maxHeaderSize);
   }

   public WebClientOptions setMaxWaitQueueSize(int maxWaitQueueSize) {
      return (WebClientOptions)super.setMaxWaitQueueSize(maxWaitQueueSize);
   }

   public WebClientOptions setUseAlpn(boolean useAlpn) {
      return (WebClientOptions)super.setUseAlpn(useAlpn);
   }

   public WebClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      return (WebClientOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   public WebClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (WebClientOptions)super.setJdkSslEngineOptions(sslEngineOptions);
   }

   public WebClientOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return (WebClientOptions)super.setOpenSslEngineOptions(sslEngineOptions);
   }

   public WebClientOptions setHttp2ClearTextUpgrade(boolean value) {
      return (WebClientOptions)super.setHttp2ClearTextUpgrade(value);
   }

   public WebClientOptions setAlpnVersions(List alpnVersions) {
      return (WebClientOptions)super.setAlpnVersions(alpnVersions);
   }

   public WebClientOptions setMetricsName(String metricsName) {
      return (WebClientOptions)super.setMetricsName(metricsName);
   }

   public WebClientOptions setProxyOptions(ProxyOptions proxyOptions) {
      return (WebClientOptions)super.setProxyOptions(proxyOptions);
   }

   public WebClientOptions setLocalAddress(String localAddress) {
      return (WebClientOptions)super.setLocalAddress(localAddress);
   }

   public WebClientOptions setLogActivity(boolean logEnabled) {
      return (WebClientOptions)super.setLogActivity(logEnabled);
   }

   public WebClientOptions addEnabledSecureTransportProtocol(String protocol) {
      return (WebClientOptions)super.addEnabledSecureTransportProtocol(protocol);
   }

   public WebClientOptions removeEnabledSecureTransportProtocol(String protocol) {
      return (WebClientOptions)super.removeEnabledSecureTransportProtocol(protocol);
   }

   public WebClientOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      return (WebClientOptions)super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
   }

   public WebClientOptions setReusePort(boolean reusePort) {
      return (WebClientOptions)super.setReusePort(reusePort);
   }

   public WebClientOptions setTcpFastOpen(boolean tcpFastOpen) {
      return (WebClientOptions)super.setTcpFastOpen(tcpFastOpen);
   }

   public WebClientOptions setTcpCork(boolean tcpCork) {
      return (WebClientOptions)super.setTcpCork(tcpCork);
   }

   public WebClientOptions setTcpQuickAck(boolean tcpQuickAck) {
      return (WebClientOptions)super.setTcpQuickAck(tcpQuickAck);
   }

   public WebClientOptions setHttp2KeepAliveTimeout(int keepAliveTimeout) {
      return (WebClientOptions)super.setHttp2KeepAliveTimeout(keepAliveTimeout);
   }

   public WebClientOptions setForceSni(boolean forceSni) {
      return (WebClientOptions)super.setForceSni(forceSni);
   }

   public WebClientOptions setDecoderInitialBufferSize(int decoderInitialBufferSize) {
      return (WebClientOptions)super.setDecoderInitialBufferSize(decoderInitialBufferSize);
   }

   public WebClientOptions setPoolCleanerPeriod(int poolCleanerPeriod) {
      return (WebClientOptions)super.setPoolCleanerPeriod(poolCleanerPeriod);
   }

   public WebClientOptions setKeepAliveTimeout(int keepAliveTimeout) {
      return (WebClientOptions)super.setKeepAliveTimeout(keepAliveTimeout);
   }

   public WebClientOptions setMaxWebSocketMessageSize(int maxWebsocketMessageSize) {
      return (WebClientOptions)super.setMaxWebSocketMessageSize(maxWebsocketMessageSize);
   }

   public WebClientOptions setMaxInitialLineLength(int maxInitialLineLength) {
      return (WebClientOptions)super.setMaxInitialLineLength(maxInitialLineLength);
   }

   public WebClientOptions setInitialSettings(Http2Settings settings) {
      return (WebClientOptions)super.setInitialSettings(settings);
   }

   public WebClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      return (WebClientOptions)super.setSslHandshakeTimeout(sslHandshakeTimeout);
   }

   public WebClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      return (WebClientOptions)super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
   }

   public WebClientOptions setTryUsePerFrameWebSocketCompression(boolean offer) {
      return (WebClientOptions)super.setTryUsePerFrameWebSocketCompression(offer);
   }

   public WebClientOptions setTryUsePerMessageWebSocketCompression(boolean offer) {
      return (WebClientOptions)super.setTryUsePerMessageWebSocketCompression(offer);
   }

   public WebClientOptions setWebSocketCompressionLevel(int compressionLevel) {
      return (WebClientOptions)super.setWebSocketCompressionLevel(compressionLevel);
   }

   public WebClientOptions setWebSocketCompressionAllowClientNoContext(boolean offer) {
      return (WebClientOptions)super.setWebSocketCompressionAllowClientNoContext(offer);
   }

   public WebClientOptions setWebSocketCompressionRequestServerNoContext(boolean offer) {
      return (WebClientOptions)super.setWebSocketCompressionRequestServerNoContext(offer);
   }

   public WebClientOptions setReadIdleTimeout(int idleTimeout) {
      return (WebClientOptions)super.setReadIdleTimeout(idleTimeout);
   }

   public WebClientOptions setWriteIdleTimeout(int idleTimeout) {
      return (WebClientOptions)super.setWriteIdleTimeout(idleTimeout);
   }

   public WebClientOptions setMaxWebSockets(int maxWebSockets) {
      return (WebClientOptions)super.setMaxWebSockets(maxWebSockets);
   }

   public WebClientOptions setNonProxyHosts(List nonProxyHosts) {
      return (WebClientOptions)super.setNonProxyHosts(nonProxyHosts);
   }

   public WebClientOptions addNonProxyHost(String nonProxyHost) {
      return (WebClientOptions)super.addNonProxyHost(nonProxyHost);
   }

   public WebClientOptions setWebSocketClosingTimeout(int webSocketClosingTimeout) {
      return (WebClientOptions)super.setWebSocketClosingTimeout(webSocketClosingTimeout);
   }

   public WebClientOptions setTracingPolicy(TracingPolicy tracingPolicy) {
      return (WebClientOptions)super.setTracingPolicy(tracingPolicy);
   }

   public WebClientOptions setPoolEventLoopSize(int poolEventLoopSize) {
      return (WebClientOptions)super.setPoolEventLoopSize(poolEventLoopSize);
   }

   public WebClientOptions setShared(boolean shared) {
      return (WebClientOptions)super.setShared(shared);
   }

   public WebClientOptions setName(String name) {
      return (WebClientOptions)super.setName(name);
   }

   public static String loadUserAgent() {
      String userAgent = "Vert.x-WebClient";
      String version = VersionCommand.getVersion();
      if (version.length() > 0) {
         userAgent = userAgent + "/" + version;
      }

      return userAgent;
   }
}
