package io.vertx.core.http;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ClientOptionsBase;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class HttpClientOptions extends ClientOptionsBase {
   public static final int DEFAULT_MAX_POOL_SIZE = 5;
   public static final int DEFAULT_HTTP2_MAX_POOL_SIZE = 1;
   public static final int DEFAULT_HTTP2_MULTIPLEXING_LIMIT = -1;
   public static final int DEFAULT_HTTP2_CONNECTION_WINDOW_SIZE = -1;
   public static final int DEFAULT_HTTP2_KEEP_ALIVE_TIMEOUT = 60;
   public static final boolean DEFAULT_KEEP_ALIVE = true;
   public static final boolean DEFAULT_PIPELINING = false;
   public static final int DEFAULT_PIPELINING_LIMIT = 10;
   public static final int DEFAULT_KEEP_ALIVE_TIMEOUT = 60;
   /** @deprecated */
   @Deprecated
   public static final boolean DEFAULT_TRY_USE_COMPRESSION = false;
   public static final boolean DEFAULT_DECOMPRESSION_SUPPORTED = false;
   public static final boolean DEFAULT_VERIFY_HOST = true;
   public static final int DEFAULT_MAX_WEBSOCKET_FRAME_SIZE = 65536;
   public static final int DEFAULT_MAX_WEBSOCKET_MESSAGE_SIZE = 262144;
   public static final int DEFAULT_MAX_WEBSOCKETS = 50;
   public static final String DEFAULT_DEFAULT_HOST = "localhost";
   public static final int DEFAULT_DEFAULT_PORT = 80;
   public static final HttpVersion DEFAULT_PROTOCOL_VERSION;
   public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
   public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
   public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
   public static final int DEFAULT_MAX_WAIT_QUEUE_SIZE = -1;
   public static final List DEFAULT_ALPN_VERSIONS;
   public static final boolean DEFAULT_HTTP2_CLEAR_TEXT_UPGRADE = true;
   public static final boolean DEFAULT_HTTP2_CLEAR_TEXT_UPGRADE_WITH_PREFLIGHT_REQUEST = false;
   public static final boolean DEFAULT_SEND_UNMASKED_FRAMES = false;
   public static final int DEFAULT_MAX_REDIRECTS = 16;
   public static final boolean DEFAULT_FORCE_SNI = false;
   public static final int DEFAULT_DECODER_INITIAL_BUFFER_SIZE = 128;
   public static final boolean DEFAULT_TRY_USE_PER_FRAME_WEBSOCKET_COMPRESSION = false;
   public static final boolean DEFAULT_TRY_USE_PER_MESSAGE_WEBSOCKET_COMPRESSION = false;
   public static final int DEFAULT_WEBSOCKET_COMPRESSION_LEVEL = 6;
   public static final boolean DEFAULT_WEBSOCKET_ALLOW_CLIENT_NO_CONTEXT = false;
   public static final boolean DEFAULT_WEBSOCKET_REQUEST_SERVER_NO_CONTEXT = false;
   public static final int DEFAULT_POOL_CLEANER_PERIOD = 1000;
   public static final int DEFAULT_POOL_EVENT_LOOP_SIZE = 0;
   public static final int DEFAULT_WEBSOCKET_CLOSING_TIMEOUT = 10;
   public static final TracingPolicy DEFAULT_TRACING_POLICY;
   public static final boolean DEFAULT_SHARED = false;
   public static final String DEFAULT_NAME = "__vertx.DEFAULT";
   private boolean verifyHost = true;
   private boolean keepAlive;
   private int keepAliveTimeout;
   private int pipeliningLimit;
   private boolean pipelining;
   private int http2MultiplexingLimit;
   private int http2ConnectionWindowSize;
   private int http2KeepAliveTimeout;
   private boolean decompressionSupported;
   private int maxWebSocketFrameSize;
   private int maxWebSocketMessageSize;
   private int maxWebSockets;
   private String defaultHost;
   private int defaultPort;
   private HttpVersion protocolVersion;
   private int maxChunkSize;
   private int maxInitialLineLength;
   private int maxHeaderSize;
   private Http2Settings initialSettings;
   private List alpnVersions;
   private boolean http2ClearTextUpgrade;
   private boolean http2ClearTextUpgradeWithPreflightRequest;
   private boolean sendUnmaskedFrames;
   private int maxRedirects;
   private boolean forceSni;
   private int decoderInitialBufferSize;
   private boolean tryUsePerFrameWebSocketCompression;
   private boolean tryUsePerMessageWebSocketCompression;
   private int webSocketCompressionLevel;
   private boolean webSocketAllowClientNoContext;
   private boolean webSocketRequestServerNoContext;
   private int webSocketClosingTimeout;
   private TracingPolicy tracingPolicy;
   private boolean shared;
   private String name;
   private PoolOptions poolOptions;

   public HttpClientOptions() {
      this.init();
   }

   public HttpClientOptions(ClientOptionsBase other) {
      super(other);
      this.init();
   }

   public HttpClientOptions(HttpClientOptions other) {
      super((ClientOptionsBase)other);
      this.verifyHost = other.isVerifyHost();
      this.keepAlive = other.isKeepAlive();
      this.keepAliveTimeout = other.getKeepAliveTimeout();
      this.pipelining = other.isPipelining();
      this.pipeliningLimit = other.getPipeliningLimit();
      this.http2MultiplexingLimit = other.http2MultiplexingLimit;
      this.http2ConnectionWindowSize = other.http2ConnectionWindowSize;
      this.http2KeepAliveTimeout = other.getHttp2KeepAliveTimeout();
      this.decompressionSupported = other.decompressionSupported;
      this.maxWebSocketFrameSize = other.maxWebSocketFrameSize;
      this.maxWebSocketMessageSize = other.maxWebSocketMessageSize;
      this.maxWebSockets = other.maxWebSockets;
      this.defaultHost = other.defaultHost;
      this.defaultPort = other.defaultPort;
      this.protocolVersion = other.protocolVersion;
      this.maxChunkSize = other.maxChunkSize;
      this.maxInitialLineLength = other.getMaxInitialLineLength();
      this.maxHeaderSize = other.getMaxHeaderSize();
      this.initialSettings = other.initialSettings != null ? new Http2Settings(other.initialSettings) : null;
      this.alpnVersions = other.alpnVersions != null ? new ArrayList(other.alpnVersions) : null;
      this.http2ClearTextUpgrade = other.http2ClearTextUpgrade;
      this.http2ClearTextUpgradeWithPreflightRequest = other.http2ClearTextUpgradeWithPreflightRequest;
      this.sendUnmaskedFrames = other.isSendUnmaskedFrames();
      this.maxRedirects = other.maxRedirects;
      this.forceSni = other.forceSni;
      this.decoderInitialBufferSize = other.getDecoderInitialBufferSize();
      this.tryUsePerFrameWebSocketCompression = other.tryUsePerFrameWebSocketCompression;
      this.tryUsePerMessageWebSocketCompression = other.tryUsePerMessageWebSocketCompression;
      this.webSocketAllowClientNoContext = other.webSocketAllowClientNoContext;
      this.webSocketCompressionLevel = other.webSocketCompressionLevel;
      this.webSocketRequestServerNoContext = other.webSocketRequestServerNoContext;
      this.webSocketClosingTimeout = other.webSocketClosingTimeout;
      this.tracingPolicy = other.tracingPolicy;
      this.shared = other.shared;
      this.name = other.name;
      this.poolOptions = other.poolOptions != null ? new PoolOptions(other.poolOptions) : new PoolOptions();
   }

   public HttpClientOptions(JsonObject json) {
      super(json);
      this.init();
      HttpClientOptionsConverter.fromJson(json, this);
      if (!json.containsKey("decompressionSupported") && json.getValue("tryUseCompression") instanceof Boolean) {
         this.setDecompressionSupported(json.getBoolean("tryUseCompression"));
      }

   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      HttpClientOptionsConverter.toJson(this, json);
      return json;
   }

   private void init() {
      this.verifyHost = true;
      this.keepAlive = true;
      this.keepAliveTimeout = 60;
      this.pipelining = false;
      this.pipeliningLimit = 10;
      this.http2MultiplexingLimit = -1;
      this.http2ConnectionWindowSize = -1;
      this.http2KeepAliveTimeout = 60;
      this.decompressionSupported = false;
      this.maxWebSocketFrameSize = 65536;
      this.maxWebSocketMessageSize = 262144;
      this.maxWebSockets = 50;
      this.defaultHost = "localhost";
      this.defaultPort = 80;
      this.protocolVersion = DEFAULT_PROTOCOL_VERSION;
      this.maxChunkSize = 8192;
      this.maxInitialLineLength = 4096;
      this.maxHeaderSize = 8192;
      this.initialSettings = new Http2Settings();
      this.alpnVersions = new ArrayList(DEFAULT_ALPN_VERSIONS);
      this.http2ClearTextUpgrade = true;
      this.http2ClearTextUpgradeWithPreflightRequest = false;
      this.sendUnmaskedFrames = false;
      this.maxRedirects = 16;
      this.forceSni = false;
      this.decoderInitialBufferSize = 128;
      this.tryUsePerFrameWebSocketCompression = false;
      this.tryUsePerMessageWebSocketCompression = false;
      this.webSocketCompressionLevel = 6;
      this.webSocketAllowClientNoContext = false;
      this.webSocketRequestServerNoContext = false;
      this.webSocketClosingTimeout = 10;
      this.tracingPolicy = DEFAULT_TRACING_POLICY;
      this.shared = false;
      this.name = "__vertx.DEFAULT";
      this.poolOptions = new PoolOptions();
   }

   public HttpClientOptions setSendBufferSize(int sendBufferSize) {
      super.setSendBufferSize(sendBufferSize);
      return this;
   }

   public HttpClientOptions setReceiveBufferSize(int receiveBufferSize) {
      super.setReceiveBufferSize(receiveBufferSize);
      return this;
   }

   public HttpClientOptions setReuseAddress(boolean reuseAddress) {
      super.setReuseAddress(reuseAddress);
      return this;
   }

   public HttpClientOptions setReusePort(boolean reusePort) {
      super.setReusePort(reusePort);
      return this;
   }

   public HttpClientOptions setTrafficClass(int trafficClass) {
      super.setTrafficClass(trafficClass);
      return this;
   }

   public HttpClientOptions setTcpNoDelay(boolean tcpNoDelay) {
      super.setTcpNoDelay(tcpNoDelay);
      return this;
   }

   public HttpClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      super.setTcpKeepAlive(tcpKeepAlive);
      return this;
   }

   public HttpClientOptions setSoLinger(int soLinger) {
      super.setSoLinger(soLinger);
      return this;
   }

   public HttpClientOptions setIdleTimeout(int idleTimeout) {
      super.setIdleTimeout(idleTimeout);
      return this;
   }

   public HttpClientOptions setReadIdleTimeout(int idleTimeout) {
      super.setReadIdleTimeout(idleTimeout);
      return this;
   }

   public HttpClientOptions setWriteIdleTimeout(int idleTimeout) {
      super.setWriteIdleTimeout(idleTimeout);
      return this;
   }

   public HttpClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      super.setIdleTimeoutUnit(idleTimeoutUnit);
      return this;
   }

   public HttpClientOptions setSsl(boolean ssl) {
      super.setSsl(ssl);
      return this;
   }

   public HttpClientOptions setKeyCertOptions(KeyCertOptions options) {
      super.setKeyCertOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setKeyStoreOptions(JksOptions options) {
      super.setKeyStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setPfxKeyCertOptions(PfxOptions options) {
      return (HttpClientOptions)super.setPfxKeyCertOptions(options);
   }

   public HttpClientOptions setTrustOptions(TrustOptions options) {
      super.setTrustOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return (HttpClientOptions)super.setPemKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setTrustStoreOptions(JksOptions options) {
      super.setTrustStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setPfxTrustOptions(PfxOptions options) {
      return (HttpClientOptions)super.setPfxTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setPemTrustOptions(PemTrustOptions options) {
      return (HttpClientOptions)super.setPemTrustOptions(options);
   }

   public HttpClientOptions addEnabledCipherSuite(String suite) {
      super.addEnabledCipherSuite(suite);
      return this;
   }

   public HttpClientOptions removeEnabledCipherSuite(String suite) {
      super.removeEnabledCipherSuite(suite);
      return this;
   }

   public HttpClientOptions addEnabledSecureTransportProtocol(String protocol) {
      super.addEnabledSecureTransportProtocol(protocol);
      return this;
   }

   public HttpClientOptions removeEnabledSecureTransportProtocol(String protocol) {
      return (HttpClientOptions)super.removeEnabledSecureTransportProtocol(protocol);
   }

   public HttpClientOptions setTcpFastOpen(boolean tcpFastOpen) {
      return (HttpClientOptions)super.setTcpFastOpen(tcpFastOpen);
   }

   public HttpClientOptions setTcpCork(boolean tcpCork) {
      return (HttpClientOptions)super.setTcpCork(tcpCork);
   }

   public HttpClientOptions setTcpQuickAck(boolean tcpQuickAck) {
      return (HttpClientOptions)super.setTcpQuickAck(tcpQuickAck);
   }

   public HttpClientOptions setTcpUserTimeout(int tcpUserTimeout) {
      return (HttpClientOptions)super.setTcpUserTimeout(tcpUserTimeout);
   }

   public HttpClientOptions addCrlPath(String crlPath) throws NullPointerException {
      return (HttpClientOptions)super.addCrlPath(crlPath);
   }

   public HttpClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      return (HttpClientOptions)super.addCrlValue(crlValue);
   }

   public HttpClientOptions setConnectTimeout(int connectTimeout) {
      super.setConnectTimeout(connectTimeout);
      return this;
   }

   public HttpClientOptions setTrustAll(boolean trustAll) {
      super.setTrustAll(trustAll);
      return this;
   }

   public HttpClientOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
      return this;
   }

   public HttpClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      super.setSslHandshakeTimeout(sslHandshakeTimeout);
      return this;
   }

   public HttpClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
      return this;
   }

   public int getMaxPoolSize() {
      return this.poolOptions.getHttp1MaxSize();
   }

   public HttpClientOptions setMaxPoolSize(int maxPoolSize) {
      this.poolOptions.setHttp1MaxSize(maxPoolSize);
      return this;
   }

   public int getHttp2MultiplexingLimit() {
      return this.http2MultiplexingLimit;
   }

   public HttpClientOptions setHttp2MultiplexingLimit(int limit) {
      if (limit != 0 && limit >= -1) {
         this.http2MultiplexingLimit = limit;
         return this;
      } else {
         throw new IllegalArgumentException("maxPoolSize must be > 0 or -1 (disabled)");
      }
   }

   public int getHttp2MaxPoolSize() {
      return this.poolOptions.getHttp2MaxSize();
   }

   public HttpClientOptions setHttp2MaxPoolSize(int max) {
      this.poolOptions.setHttp2MaxSize(max);
      return this;
   }

   public int getHttp2ConnectionWindowSize() {
      return this.http2ConnectionWindowSize;
   }

   public HttpClientOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize) {
      this.http2ConnectionWindowSize = http2ConnectionWindowSize;
      return this;
   }

   public int getHttp2KeepAliveTimeout() {
      return this.http2KeepAliveTimeout;
   }

   public HttpClientOptions setHttp2KeepAliveTimeout(int keepAliveTimeout) {
      if (keepAliveTimeout < 0) {
         throw new IllegalArgumentException("HTTP/2 keepAliveTimeout must be >= 0");
      } else {
         this.http2KeepAliveTimeout = keepAliveTimeout;
         return this;
      }
   }

   public boolean isKeepAlive() {
      return this.keepAlive;
   }

   public HttpClientOptions setKeepAlive(boolean keepAlive) {
      this.keepAlive = keepAlive;
      return this;
   }

   public int getKeepAliveTimeout() {
      return this.keepAliveTimeout;
   }

   public HttpClientOptions setKeepAliveTimeout(int keepAliveTimeout) {
      if (keepAliveTimeout < 0) {
         throw new IllegalArgumentException("keepAliveTimeout must be >= 0");
      } else {
         this.keepAliveTimeout = keepAliveTimeout;
         return this;
      }
   }

   public boolean isPipelining() {
      return this.pipelining;
   }

   public HttpClientOptions setPipelining(boolean pipelining) {
      this.pipelining = pipelining;
      return this;
   }

   public int getPipeliningLimit() {
      return this.pipeliningLimit;
   }

   public HttpClientOptions setPipeliningLimit(int limit) {
      if (limit < 1) {
         throw new IllegalArgumentException("pipeliningLimit must be > 0");
      } else {
         this.pipeliningLimit = limit;
         return this;
      }
   }

   public boolean isVerifyHost() {
      return this.verifyHost;
   }

   public HttpClientOptions setVerifyHost(boolean verifyHost) {
      this.verifyHost = verifyHost;
      return this;
   }

   /** @deprecated */
   @Deprecated
   @GenIgnore
   public boolean isTryUseCompression() {
      return this.decompressionSupported;
   }

   /** @deprecated */
   @Deprecated
   @GenIgnore
   public HttpClientOptions setTryUseCompression(boolean tryUseCompression) {
      this.decompressionSupported = tryUseCompression;
      return this;
   }

   public boolean isDecompressionSupported() {
      return this.decompressionSupported;
   }

   public HttpClientOptions setDecompressionSupported(boolean decompressionSupported) {
      this.decompressionSupported = decompressionSupported;
      return this;
   }

   public boolean isSendUnmaskedFrames() {
      return this.sendUnmaskedFrames;
   }

   public HttpClientOptions setSendUnmaskedFrames(boolean sendUnmaskedFrames) {
      this.sendUnmaskedFrames = sendUnmaskedFrames;
      return this;
   }

   public int getMaxWebSocketFrameSize() {
      return this.maxWebSocketFrameSize;
   }

   public HttpClientOptions setMaxWebSocketFrameSize(int maxWebSocketFrameSize) {
      this.maxWebSocketFrameSize = maxWebSocketFrameSize;
      return this;
   }

   public int getMaxWebSocketMessageSize() {
      return this.maxWebSocketMessageSize;
   }

   public HttpClientOptions setMaxWebSocketMessageSize(int maxWebSocketMessageSize) {
      this.maxWebSocketMessageSize = maxWebSocketMessageSize;
      return this;
   }

   public int getMaxWebSockets() {
      return this.maxWebSockets;
   }

   public HttpClientOptions setMaxWebSockets(int maxWebSockets) {
      if (maxWebSockets != 0 && maxWebSockets >= -1) {
         this.maxWebSockets = maxWebSockets;
         return this;
      } else {
         throw new IllegalArgumentException("maxWebSockets must be > 0 or -1 (disabled)");
      }
   }

   public String getDefaultHost() {
      return this.defaultHost;
   }

   public HttpClientOptions setDefaultHost(String defaultHost) {
      this.defaultHost = defaultHost;
      return this;
   }

   public int getDefaultPort() {
      return this.defaultPort;
   }

   public HttpClientOptions setDefaultPort(int defaultPort) {
      this.defaultPort = defaultPort;
      return this;
   }

   public HttpVersion getProtocolVersion() {
      return this.protocolVersion;
   }

   public HttpClientOptions setProtocolVersion(HttpVersion protocolVersion) {
      if (protocolVersion == null) {
         throw new IllegalArgumentException("protocolVersion must not be null");
      } else {
         this.protocolVersion = protocolVersion;
         return this;
      }
   }

   public HttpClientOptions setMaxChunkSize(int maxChunkSize) {
      this.maxChunkSize = maxChunkSize;
      return this;
   }

   public int getMaxChunkSize() {
      return this.maxChunkSize;
   }

   public int getMaxInitialLineLength() {
      return this.maxInitialLineLength;
   }

   public HttpClientOptions setMaxInitialLineLength(int maxInitialLineLength) {
      this.maxInitialLineLength = maxInitialLineLength;
      return this;
   }

   public int getMaxHeaderSize() {
      return this.maxHeaderSize;
   }

   public HttpClientOptions setMaxHeaderSize(int maxHeaderSize) {
      this.maxHeaderSize = maxHeaderSize;
      return this;
   }

   public HttpClientOptions setMaxWaitQueueSize(int maxWaitQueueSize) {
      this.poolOptions.setMaxWaitQueueSize(maxWaitQueueSize);
      return this;
   }

   public int getMaxWaitQueueSize() {
      return this.poolOptions.getMaxWaitQueueSize();
   }

   public Http2Settings getInitialSettings() {
      return this.initialSettings;
   }

   public HttpClientOptions setInitialSettings(Http2Settings settings) {
      this.initialSettings = settings;
      return this;
   }

   public HttpClientOptions setUseAlpn(boolean useAlpn) {
      return (HttpClientOptions)super.setUseAlpn(useAlpn);
   }

   public HttpClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      return (HttpClientOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (HttpClientOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return (HttpClientOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   public List getAlpnVersions() {
      return this.alpnVersions;
   }

   public HttpClientOptions setAlpnVersions(List alpnVersions) {
      this.alpnVersions = alpnVersions;
      return this;
   }

   public boolean isHttp2ClearTextUpgrade() {
      return this.http2ClearTextUpgrade;
   }

   public HttpClientOptions setHttp2ClearTextUpgrade(boolean value) {
      this.http2ClearTextUpgrade = value;
      return this;
   }

   public boolean isHttp2ClearTextUpgradeWithPreflightRequest() {
      return this.http2ClearTextUpgradeWithPreflightRequest;
   }

   public HttpClientOptions setHttp2ClearTextUpgradeWithPreflightRequest(boolean value) {
      this.http2ClearTextUpgradeWithPreflightRequest = value;
      return this;
   }

   public int getMaxRedirects() {
      return this.maxRedirects;
   }

   public HttpClientOptions setMaxRedirects(int maxRedirects) {
      this.maxRedirects = maxRedirects;
      return this;
   }

   public boolean isForceSni() {
      return this.forceSni;
   }

   public HttpClientOptions setForceSni(boolean forceSni) {
      this.forceSni = forceSni;
      return this;
   }

   public HttpClientOptions setMetricsName(String metricsName) {
      return (HttpClientOptions)super.setMetricsName(metricsName);
   }

   public HttpClientOptions setProxyOptions(ProxyOptions proxyOptions) {
      return (HttpClientOptions)super.setProxyOptions(proxyOptions);
   }

   public HttpClientOptions setNonProxyHosts(List nonProxyHosts) {
      return (HttpClientOptions)super.setNonProxyHosts(nonProxyHosts);
   }

   public HttpClientOptions addNonProxyHost(String nonProxyHost) {
      return (HttpClientOptions)super.addNonProxyHost(nonProxyHost);
   }

   public HttpClientOptions setLocalAddress(String localAddress) {
      return (HttpClientOptions)super.setLocalAddress(localAddress);
   }

   public HttpClientOptions setLogActivity(boolean logEnabled) {
      return (HttpClientOptions)super.setLogActivity(logEnabled);
   }

   public HttpClientOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (HttpClientOptions)super.setActivityLogDataFormat(activityLogDataFormat);
   }

   public HttpClientOptions setTryUsePerFrameWebSocketCompression(boolean offer) {
      this.tryUsePerFrameWebSocketCompression = offer;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public boolean getTryWebSocketDeflateFrameCompression() {
      return this.tryUsePerFrameWebSocketCompression;
   }

   public boolean getTryUsePerFrameWebSocketCompression() {
      return this.tryUsePerFrameWebSocketCompression;
   }

   public HttpClientOptions setTryUsePerMessageWebSocketCompression(boolean offer) {
      this.tryUsePerMessageWebSocketCompression = offer;
      return this;
   }

   public boolean getTryUsePerMessageWebSocketCompression() {
      return this.tryUsePerMessageWebSocketCompression;
   }

   public HttpClientOptions setWebSocketCompressionLevel(int compressionLevel) {
      this.webSocketCompressionLevel = compressionLevel;
      return this;
   }

   public int getWebSocketCompressionLevel() {
      return this.webSocketCompressionLevel;
   }

   public HttpClientOptions setWebSocketCompressionAllowClientNoContext(boolean offer) {
      this.webSocketAllowClientNoContext = offer;
      return this;
   }

   public boolean getWebSocketCompressionAllowClientNoContext() {
      return this.webSocketAllowClientNoContext;
   }

   public HttpClientOptions setWebSocketCompressionRequestServerNoContext(boolean offer) {
      this.webSocketRequestServerNoContext = offer;
      return this;
   }

   public boolean getWebSocketCompressionRequestServerNoContext() {
      return this.webSocketRequestServerNoContext;
   }

   public int getWebSocketClosingTimeout() {
      return this.webSocketClosingTimeout;
   }

   public HttpClientOptions setWebSocketClosingTimeout(int webSocketClosingTimeout) {
      this.webSocketClosingTimeout = webSocketClosingTimeout;
      return this;
   }

   public int getDecoderInitialBufferSize() {
      return this.decoderInitialBufferSize;
   }

   public HttpClientOptions setDecoderInitialBufferSize(int decoderInitialBufferSize) {
      Arguments.require(decoderInitialBufferSize > 0, "initialBufferSizeHttpDecoder must be > 0");
      this.decoderInitialBufferSize = decoderInitialBufferSize;
      return this;
   }

   public int getPoolCleanerPeriod() {
      return this.poolOptions.getCleanerPeriod();
   }

   public HttpClientOptions setPoolCleanerPeriod(int poolCleanerPeriod) {
      this.poolOptions.setCleanerPeriod(poolCleanerPeriod);
      return this;
   }

   public int getPoolEventLoopSize() {
      return this.poolOptions.getEventLoopSize();
   }

   public HttpClientOptions setPoolEventLoopSize(int poolEventLoopSize) {
      this.poolOptions.setEventLoopSize(poolEventLoopSize);
      return this;
   }

   public TracingPolicy getTracingPolicy() {
      return this.tracingPolicy;
   }

   public HttpClientOptions setTracingPolicy(TracingPolicy tracingPolicy) {
      this.tracingPolicy = tracingPolicy;
      return this;
   }

   public boolean isShared() {
      return this.shared;
   }

   public HttpClientOptions setShared(boolean shared) {
      this.shared = shared;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public HttpClientOptions setName(String name) {
      Objects.requireNonNull(name, "Client name cannot be null");
      this.name = name;
      return this;
   }

   @GenIgnore
   public PoolOptions getPoolOptions() {
      return this.poolOptions;
   }

   static {
      DEFAULT_PROTOCOL_VERSION = HttpVersion.HTTP_1_1;
      DEFAULT_ALPN_VERSIONS = Collections.emptyList();
      DEFAULT_TRACING_POLICY = TracingPolicy.PROPAGATE;
   }
}
