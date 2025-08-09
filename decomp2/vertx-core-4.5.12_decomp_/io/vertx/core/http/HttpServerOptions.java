package io.vertx.core.http;

import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TrafficShapingOptions;
import io.vertx.core.net.TrustOptions;
import io.vertx.core.tracing.TracingPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class HttpServerOptions extends NetServerOptions {
   public static final int DEFAULT_PORT = 80;
   public static final boolean DEFAULT_COMPRESSION_SUPPORTED = false;
   public static final int DEFAULT_COMPRESSION_LEVEL = 6;
   public static final int DEFAULT_MAX_WEBSOCKET_FRAME_SIZE = 65536;
   public static final int DEFAULT_MAX_WEBSOCKET_MESSAGE_SIZE = 262144;
   public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
   public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
   public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
   public static final int DEFAULT_MAX_FORM_ATTRIBUTE_SIZE = 8192;
   public static final int DEFAULT_MAX_FORM_FIELDS = 256;
   public static final int DEFAULT_MAX_FORM_BUFFERED_SIZE = 1024;
   public static final boolean DEFAULT_HANDLE_100_CONTINE_AUTOMATICALLY = false;
   public static final List DEFAULT_ALPN_VERSIONS;
   public static final boolean DEFAULT_HTTP2_CLEAR_TEXT_ENABLED = true;
   public static final long DEFAULT_INITIAL_SETTINGS_MAX_CONCURRENT_STREAMS = 100L;
   public static final int DEFAULT_HTTP2_CONNECTION_WINDOW_SIZE = -1;
   public static final boolean DEFAULT_DECOMPRESSION_SUPPORTED = false;
   public static final boolean DEFAULT_ACCEPT_UNMASKED_FRAMES = false;
   public static final int DEFAULT_DECODER_INITIAL_BUFFER_SIZE = 128;
   public static final boolean DEFAULT_PER_FRAME_WEBSOCKET_COMPRESSION_SUPPORTED = true;
   public static final boolean DEFAULT_PER_MESSAGE_WEBSOCKET_COMPRESSION_SUPPORTED = true;
   public static final int DEFAULT_WEBSOCKET_COMPRESSION_LEVEL = 6;
   public static final boolean DEFAULT_WEBSOCKET_ALLOW_SERVER_NO_CONTEXT = false;
   public static final boolean DEFAULT_WEBSOCKET_PREFERRED_CLIENT_NO_CONTEXT = false;
   public static final int DEFAULT_WEBSOCKET_CLOSING_TIMEOUT = 10;
   public static final TracingPolicy DEFAULT_TRACING_POLICY;
   public static final boolean DEFAULT_REGISTER_WEBSOCKET_WRITE_HANDLERS = false;
   public static final int DEFAULT_HTTP2_RST_FLOOD_MAX_RST_FRAME_PER_WINDOW = 200;
   public static final int DEFAULT_HTTP2_RST_FLOOD_WINDOW_DURATION = 30;
   public static final TimeUnit DEFAULT_HTTP2_RST_FLOOD_WINDOW_DURATION_TIME_UNIT;
   private boolean compressionSupported;
   private int compressionLevel;
   private List compressors;
   private int maxWebSocketFrameSize;
   private int maxWebSocketMessageSize;
   private List webSocketSubProtocols;
   private boolean handle100ContinueAutomatically;
   private int maxChunkSize;
   private int maxInitialLineLength;
   private int maxHeaderSize;
   private int maxFormAttributeSize;
   private int maxFormFields;
   private int maxFormBufferedBytes;
   private Http2Settings initialSettings;
   private List alpnVersions;
   private boolean http2ClearTextEnabled;
   private int http2ConnectionWindowSize;
   private boolean decompressionSupported;
   private boolean acceptUnmaskedFrames;
   private int decoderInitialBufferSize;
   private boolean perFrameWebSocketCompressionSupported;
   private boolean perMessageWebSocketCompressionSupported;
   private int webSocketCompressionLevel;
   private boolean webSocketAllowServerNoContext;
   private boolean webSocketPreferredClientNoContext;
   private int webSocketClosingTimeout;
   private TracingPolicy tracingPolicy;
   private boolean registerWebSocketWriteHandlers;
   private int http2RstFloodMaxRstFramePerWindow;
   private int http2RstFloodWindowDuration;
   private TimeUnit http2RstFloodWindowDurationTimeUnit;

   public HttpServerOptions() {
      this.init();
      this.setPort(80);
   }

   public HttpServerOptions(HttpServerOptions other) {
      super((NetServerOptions)other);
      this.compressionSupported = other.isCompressionSupported();
      this.compressionLevel = other.getCompressionLevel();
      this.compressors = other.compressors != null ? new ArrayList(other.compressors) : null;
      this.maxWebSocketFrameSize = other.maxWebSocketFrameSize;
      this.maxWebSocketMessageSize = other.maxWebSocketMessageSize;
      this.webSocketSubProtocols = other.webSocketSubProtocols != null ? new ArrayList(other.webSocketSubProtocols) : null;
      this.handle100ContinueAutomatically = other.handle100ContinueAutomatically;
      this.maxChunkSize = other.getMaxChunkSize();
      this.maxInitialLineLength = other.getMaxInitialLineLength();
      this.maxHeaderSize = other.getMaxHeaderSize();
      this.maxFormAttributeSize = other.getMaxFormAttributeSize();
      this.maxFormFields = other.getMaxFormFields();
      this.maxFormBufferedBytes = other.getMaxFormBufferedBytes();
      this.initialSettings = other.initialSettings != null ? new Http2Settings(other.initialSettings) : null;
      this.alpnVersions = other.alpnVersions != null ? new ArrayList(other.alpnVersions) : null;
      this.http2ClearTextEnabled = other.http2ClearTextEnabled;
      this.http2ConnectionWindowSize = other.http2ConnectionWindowSize;
      this.decompressionSupported = other.isDecompressionSupported();
      this.acceptUnmaskedFrames = other.isAcceptUnmaskedFrames();
      this.decoderInitialBufferSize = other.getDecoderInitialBufferSize();
      this.perFrameWebSocketCompressionSupported = other.perFrameWebSocketCompressionSupported;
      this.perMessageWebSocketCompressionSupported = other.perMessageWebSocketCompressionSupported;
      this.webSocketCompressionLevel = other.webSocketCompressionLevel;
      this.webSocketPreferredClientNoContext = other.webSocketPreferredClientNoContext;
      this.webSocketAllowServerNoContext = other.webSocketAllowServerNoContext;
      this.webSocketClosingTimeout = other.webSocketClosingTimeout;
      this.tracingPolicy = other.tracingPolicy;
      this.registerWebSocketWriteHandlers = other.registerWebSocketWriteHandlers;
      this.http2RstFloodMaxRstFramePerWindow = other.http2RstFloodMaxRstFramePerWindow;
      this.http2RstFloodWindowDuration = other.http2RstFloodWindowDuration;
      this.http2RstFloodWindowDurationTimeUnit = other.http2RstFloodWindowDurationTimeUnit;
   }

   public HttpServerOptions(JsonObject json) {
      super(json);
      this.init();
      this.setPort(json.getInteger("port", 80));
      HttpServerOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      HttpServerOptionsConverter.toJson(this, json);
      return json;
   }

   private void init() {
      this.compressionSupported = false;
      this.compressionLevel = 6;
      this.maxWebSocketFrameSize = 65536;
      this.maxWebSocketMessageSize = 262144;
      this.handle100ContinueAutomatically = false;
      this.maxChunkSize = 8192;
      this.maxInitialLineLength = 4096;
      this.maxHeaderSize = 8192;
      this.maxFormAttributeSize = 8192;
      this.maxFormFields = 256;
      this.maxFormBufferedBytes = 1024;
      this.initialSettings = (new Http2Settings()).setMaxConcurrentStreams(100L);
      this.alpnVersions = new ArrayList(DEFAULT_ALPN_VERSIONS);
      this.http2ClearTextEnabled = true;
      this.http2ConnectionWindowSize = -1;
      this.decompressionSupported = false;
      this.acceptUnmaskedFrames = false;
      this.decoderInitialBufferSize = 128;
      this.perFrameWebSocketCompressionSupported = true;
      this.perMessageWebSocketCompressionSupported = true;
      this.webSocketCompressionLevel = 6;
      this.webSocketPreferredClientNoContext = false;
      this.webSocketAllowServerNoContext = false;
      this.webSocketClosingTimeout = 10;
      this.tracingPolicy = DEFAULT_TRACING_POLICY;
      this.registerWebSocketWriteHandlers = false;
      this.http2RstFloodMaxRstFramePerWindow = 200;
      this.http2RstFloodWindowDuration = 30;
      this.http2RstFloodWindowDurationTimeUnit = DEFAULT_HTTP2_RST_FLOOD_WINDOW_DURATION_TIME_UNIT;
   }

   public HttpServerOptions setSendBufferSize(int sendBufferSize) {
      super.setSendBufferSize(sendBufferSize);
      return this;
   }

   public HttpServerOptions setReceiveBufferSize(int receiveBufferSize) {
      super.setReceiveBufferSize(receiveBufferSize);
      return this;
   }

   public HttpServerOptions setReuseAddress(boolean reuseAddress) {
      super.setReuseAddress(reuseAddress);
      return this;
   }

   public HttpServerOptions setReusePort(boolean reusePort) {
      super.setReusePort(reusePort);
      return this;
   }

   public HttpServerOptions setTrafficClass(int trafficClass) {
      super.setTrafficClass(trafficClass);
      return this;
   }

   public HttpServerOptions setTcpNoDelay(boolean tcpNoDelay) {
      super.setTcpNoDelay(tcpNoDelay);
      return this;
   }

   public HttpServerOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      super.setTcpKeepAlive(tcpKeepAlive);
      return this;
   }

   public HttpServerOptions setSoLinger(int soLinger) {
      super.setSoLinger(soLinger);
      return this;
   }

   public HttpServerOptions setIdleTimeout(int idleTimeout) {
      super.setIdleTimeout(idleTimeout);
      return this;
   }

   public HttpServerOptions setReadIdleTimeout(int idleTimeout) {
      super.setReadIdleTimeout(idleTimeout);
      return this;
   }

   public HttpServerOptions setWriteIdleTimeout(int idleTimeout) {
      super.setWriteIdleTimeout(idleTimeout);
      return this;
   }

   public HttpServerOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      super.setIdleTimeoutUnit(idleTimeoutUnit);
      return this;
   }

   public HttpServerOptions setSsl(boolean ssl) {
      super.setSsl(ssl);
      return this;
   }

   public HttpServerOptions setUseAlpn(boolean useAlpn) {
      super.setUseAlpn(useAlpn);
      return this;
   }

   public HttpServerOptions setKeyCertOptions(KeyCertOptions options) {
      super.setKeyCertOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setKeyStoreOptions(JksOptions options) {
      super.setKeyStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setPfxKeyCertOptions(PfxOptions options) {
      return (HttpServerOptions)super.setPfxKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return (HttpServerOptions)super.setPemKeyCertOptions(options);
   }

   public HttpServerOptions setTrustOptions(TrustOptions options) {
      super.setTrustOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setTrustStoreOptions(JksOptions options) {
      super.setTrustStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setPemTrustOptions(PemTrustOptions options) {
      return (HttpServerOptions)super.setPemTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setPfxTrustOptions(PfxOptions options) {
      return (HttpServerOptions)super.setPfxTrustOptions(options);
   }

   public HttpServerOptions addEnabledCipherSuite(String suite) {
      super.addEnabledCipherSuite(suite);
      return this;
   }

   public HttpServerOptions removeEnabledCipherSuite(String suite) {
      super.removeEnabledCipherSuite(suite);
      return this;
   }

   public HttpServerOptions addEnabledSecureTransportProtocol(String protocol) {
      super.addEnabledSecureTransportProtocol(protocol);
      return this;
   }

   public HttpServerOptions removeEnabledSecureTransportProtocol(String protocol) {
      return (HttpServerOptions)super.removeEnabledSecureTransportProtocol(protocol);
   }

   public HttpServerOptions setTcpFastOpen(boolean tcpFastOpen) {
      return (HttpServerOptions)super.setTcpFastOpen(tcpFastOpen);
   }

   public HttpServerOptions setTcpCork(boolean tcpCork) {
      return (HttpServerOptions)super.setTcpCork(tcpCork);
   }

   public HttpServerOptions setTcpQuickAck(boolean tcpQuickAck) {
      return (HttpServerOptions)super.setTcpQuickAck(tcpQuickAck);
   }

   public HttpServerOptions addCrlPath(String crlPath) throws NullPointerException {
      return (HttpServerOptions)super.addCrlPath(crlPath);
   }

   public HttpServerOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      return (HttpServerOptions)super.addCrlValue(crlValue);
   }

   public HttpServerOptions setAcceptBacklog(int acceptBacklog) {
      super.setAcceptBacklog(acceptBacklog);
      return this;
   }

   public HttpServerOptions setPort(int port) {
      super.setPort(port);
      return this;
   }

   public HttpServerOptions setHost(String host) {
      super.setHost(host);
      return this;
   }

   public HttpServerOptions setClientAuth(ClientAuth clientAuth) {
      super.setClientAuth(clientAuth);
      return this;
   }

   public HttpServerOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      super.setSslEngineOptions(sslEngineOptions);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (HttpServerOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public HttpServerOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return (HttpServerOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   public HttpServerOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      return (HttpServerOptions)super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
   }

   public HttpServerOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      return (HttpServerOptions)super.setSslHandshakeTimeout(sslHandshakeTimeout);
   }

   public HttpServerOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      return (HttpServerOptions)super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
   }

   public boolean isCompressionSupported() {
      return this.compressionSupported;
   }

   public HttpServerOptions setCompressionSupported(boolean compressionSupported) {
      this.compressionSupported = compressionSupported;
      return this;
   }

   public int getCompressionLevel() {
      return this.compressionLevel;
   }

   public HttpServerOptions setCompressionLevel(int compressionLevel) {
      this.compressionLevel = compressionLevel;
      return this;
   }

   public List getCompressors() {
      return this.compressors;
   }

   public HttpServerOptions addCompressor(CompressionOptions compressor) {
      if (this.compressors == null) {
         this.compressors = new ArrayList();
      }

      this.compressors.add(compressor);
      return this;
   }

   public HttpServerOptions setCompressors(List compressors) {
      this.compressors = compressors;
      return this;
   }

   public boolean isAcceptUnmaskedFrames() {
      return this.acceptUnmaskedFrames;
   }

   public HttpServerOptions setAcceptUnmaskedFrames(boolean acceptUnmaskedFrames) {
      this.acceptUnmaskedFrames = acceptUnmaskedFrames;
      return this;
   }

   public int getMaxWebSocketFrameSize() {
      return this.maxWebSocketFrameSize;
   }

   public HttpServerOptions setMaxWebSocketFrameSize(int maxWebSocketFrameSize) {
      this.maxWebSocketFrameSize = maxWebSocketFrameSize;
      return this;
   }

   public int getMaxWebSocketMessageSize() {
      return this.maxWebSocketMessageSize;
   }

   public HttpServerOptions setMaxWebSocketMessageSize(int maxWebSocketMessageSize) {
      this.maxWebSocketMessageSize = maxWebSocketMessageSize;
      return this;
   }

   public HttpServerOptions addWebSocketSubProtocol(String subProtocol) {
      Objects.requireNonNull(subProtocol, "Cannot add a null WebSocket sub-protocol");
      if (this.webSocketSubProtocols == null) {
         this.webSocketSubProtocols = new ArrayList();
      }

      this.webSocketSubProtocols.add(subProtocol);
      return this;
   }

   public HttpServerOptions setWebSocketSubProtocols(List subProtocols) {
      this.webSocketSubProtocols = subProtocols;
      return this;
   }

   public List getWebSocketSubProtocols() {
      return this.webSocketSubProtocols;
   }

   public boolean isHandle100ContinueAutomatically() {
      return this.handle100ContinueAutomatically;
   }

   public HttpServerOptions setHandle100ContinueAutomatically(boolean handle100ContinueAutomatically) {
      this.handle100ContinueAutomatically = handle100ContinueAutomatically;
      return this;
   }

   public HttpServerOptions setMaxChunkSize(int maxChunkSize) {
      this.maxChunkSize = maxChunkSize;
      return this;
   }

   public int getMaxChunkSize() {
      return this.maxChunkSize;
   }

   public int getMaxInitialLineLength() {
      return this.maxInitialLineLength;
   }

   public HttpServerOptions setMaxInitialLineLength(int maxInitialLineLength) {
      this.maxInitialLineLength = maxInitialLineLength;
      return this;
   }

   public int getMaxHeaderSize() {
      return this.maxHeaderSize;
   }

   public HttpServerOptions setMaxHeaderSize(int maxHeaderSize) {
      this.maxHeaderSize = maxHeaderSize;
      return this;
   }

   public int getMaxFormAttributeSize() {
      return this.maxFormAttributeSize;
   }

   public HttpServerOptions setMaxFormAttributeSize(int maxSize) {
      this.maxFormAttributeSize = maxSize;
      return this;
   }

   public int getMaxFormFields() {
      return this.maxFormFields;
   }

   public HttpServerOptions setMaxFormFields(int maxFormFields) {
      this.maxFormFields = maxFormFields;
      return this;
   }

   public int getMaxFormBufferedBytes() {
      return this.maxFormBufferedBytes;
   }

   public HttpServerOptions setMaxFormBufferedBytes(int maxFormBufferedBytes) {
      this.maxFormBufferedBytes = maxFormBufferedBytes;
      return this;
   }

   public Http2Settings getInitialSettings() {
      return this.initialSettings;
   }

   public HttpServerOptions setInitialSettings(Http2Settings settings) {
      this.initialSettings = settings;
      return this;
   }

   public List getAlpnVersions() {
      return this.alpnVersions;
   }

   public HttpServerOptions setAlpnVersions(List alpnVersions) {
      this.alpnVersions = alpnVersions;
      return this;
   }

   public boolean isHttp2ClearTextEnabled() {
      return this.http2ClearTextEnabled;
   }

   public HttpServerOptions setHttp2ClearTextEnabled(boolean http2ClearTextEnabled) {
      this.http2ClearTextEnabled = http2ClearTextEnabled;
      return this;
   }

   public int getHttp2ConnectionWindowSize() {
      return this.http2ConnectionWindowSize;
   }

   public HttpServerOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize) {
      this.http2ConnectionWindowSize = http2ConnectionWindowSize;
      return this;
   }

   public HttpServerOptions setLogActivity(boolean logEnabled) {
      return (HttpServerOptions)super.setLogActivity(logEnabled);
   }

   public HttpServerOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (HttpServerOptions)super.setActivityLogDataFormat(activityLogDataFormat);
   }

   public HttpServerOptions setSni(boolean sni) {
      return (HttpServerOptions)super.setSni(sni);
   }

   public HttpServerOptions setUseProxyProtocol(boolean useProxyProtocol) {
      return (HttpServerOptions)super.setUseProxyProtocol(useProxyProtocol);
   }

   public HttpServerOptions setProxyProtocolTimeout(long proxyProtocolTimeout) {
      return (HttpServerOptions)super.setProxyProtocolTimeout(proxyProtocolTimeout);
   }

   public HttpServerOptions setProxyProtocolTimeoutUnit(TimeUnit proxyProtocolTimeoutUnit) {
      return (HttpServerOptions)super.setProxyProtocolTimeoutUnit(proxyProtocolTimeoutUnit);
   }

   public boolean isDecompressionSupported() {
      return this.decompressionSupported;
   }

   public HttpServerOptions setDecompressionSupported(boolean decompressionSupported) {
      this.decompressionSupported = decompressionSupported;
      return this;
   }

   public int getDecoderInitialBufferSize() {
      return this.decoderInitialBufferSize;
   }

   public HttpServerOptions setDecoderInitialBufferSize(int decoderInitialBufferSize) {
      Arguments.require(decoderInitialBufferSize > 0, "initialBufferSizeHttpDecoder must be > 0");
      this.decoderInitialBufferSize = decoderInitialBufferSize;
      return this;
   }

   public HttpServerOptions setPerFrameWebSocketCompressionSupported(boolean supported) {
      this.perFrameWebSocketCompressionSupported = supported;
      return this;
   }

   public boolean getPerFrameWebSocketCompressionSupported() {
      return this.perFrameWebSocketCompressionSupported;
   }

   public HttpServerOptions setPerMessageWebSocketCompressionSupported(boolean supported) {
      this.perMessageWebSocketCompressionSupported = supported;
      return this;
   }

   public boolean getPerMessageWebSocketCompressionSupported() {
      return this.perMessageWebSocketCompressionSupported;
   }

   public HttpServerOptions setWebSocketCompressionLevel(int compressionLevel) {
      this.webSocketCompressionLevel = compressionLevel;
      return this;
   }

   public int getWebSocketCompressionLevel() {
      return this.webSocketCompressionLevel;
   }

   public HttpServerOptions setWebSocketAllowServerNoContext(boolean accept) {
      this.webSocketAllowServerNoContext = accept;
      return this;
   }

   public boolean getWebSocketAllowServerNoContext() {
      return this.webSocketAllowServerNoContext;
   }

   public HttpServerOptions setWebSocketPreferredClientNoContext(boolean accept) {
      this.webSocketPreferredClientNoContext = accept;
      return this;
   }

   public boolean getWebSocketPreferredClientNoContext() {
      return this.webSocketPreferredClientNoContext;
   }

   public int getWebSocketClosingTimeout() {
      return this.webSocketClosingTimeout;
   }

   public HttpServerOptions setWebSocketClosingTimeout(int webSocketClosingTimeout) {
      this.webSocketClosingTimeout = webSocketClosingTimeout;
      return this;
   }

   public HttpServerOptions setTrafficShapingOptions(TrafficShapingOptions trafficShapingOptions) {
      return (HttpServerOptions)super.setTrafficShapingOptions(trafficShapingOptions);
   }

   public TracingPolicy getTracingPolicy() {
      return this.tracingPolicy;
   }

   public HttpServerOptions setTracingPolicy(TracingPolicy tracingPolicy) {
      this.tracingPolicy = tracingPolicy;
      return this;
   }

   public boolean isRegisterWriteHandler() {
      return false;
   }

   public HttpServerOptions setRegisterWriteHandler(boolean registerWriteHandler) {
      return this;
   }

   public boolean isRegisterWebSocketWriteHandlers() {
      return this.registerWebSocketWriteHandlers;
   }

   public HttpServerOptions setRegisterWebSocketWriteHandlers(boolean registerWebSocketWriteHandlers) {
      this.registerWebSocketWriteHandlers = registerWebSocketWriteHandlers;
      return this;
   }

   public int getHttp2RstFloodMaxRstFramePerWindow() {
      return this.http2RstFloodMaxRstFramePerWindow;
   }

   public HttpServerOptions setHttp2RstFloodMaxRstFramePerWindow(int http2RstFloodMaxRstFramePerWindow) {
      this.http2RstFloodMaxRstFramePerWindow = http2RstFloodMaxRstFramePerWindow;
      return this;
   }

   public int getHttp2RstFloodWindowDuration() {
      return this.http2RstFloodWindowDuration;
   }

   public HttpServerOptions setHttp2RstFloodWindowDuration(int http2RstFloodWindowDuration) {
      this.http2RstFloodWindowDuration = http2RstFloodWindowDuration;
      return this;
   }

   public TimeUnit getHttp2RstFloodWindowDurationTimeUnit() {
      return this.http2RstFloodWindowDurationTimeUnit;
   }

   public HttpServerOptions setHttp2RstFloodWindowDurationTimeUnit(TimeUnit http2RstFloodWindowDurationTimeUnit) {
      if (http2RstFloodWindowDurationTimeUnit == null) {
         throw new NullPointerException();
      } else {
         this.http2RstFloodWindowDurationTimeUnit = http2RstFloodWindowDurationTimeUnit;
         return this;
      }
   }

   static {
      DEFAULT_ALPN_VERSIONS = Collections.unmodifiableList(Arrays.asList(HttpVersion.HTTP_2, HttpVersion.HTTP_1_1));
      DEFAULT_TRACING_POLICY = TracingPolicy.ALWAYS;
      DEFAULT_HTTP2_RST_FLOOD_WINDOW_DURATION_TIME_UNIT = TimeUnit.SECONDS;
   }
}
