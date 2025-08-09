package io.vertx.core.http;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class WebSocketClientOptions extends ClientOptionsBase {
   private String defaultHost;
   private int defaultPort;
   private boolean verifyHost;
   private int maxFrameSize;
   private int maxMessageSize;
   private int maxConnections;
   private boolean sendUnmaskedFrames;
   private boolean tryUsePerFrameCompression;
   private boolean tryUsePerMessageCompression;
   private int compressionLevel;
   private boolean allowClientNoContext;
   private boolean requestServerNoContext;
   private int closingTimeout;
   private boolean shared;
   private String name;

   public WebSocketClientOptions() {
      this.init();
   }

   public WebSocketClientOptions(WebSocketClientOptions other) {
      super((ClientOptionsBase)other);
      this.defaultHost = other.defaultHost;
      this.defaultPort = other.defaultPort;
      this.verifyHost = other.verifyHost;
      this.maxFrameSize = other.maxFrameSize;
      this.maxMessageSize = other.maxMessageSize;
      this.maxConnections = other.maxConnections;
      this.sendUnmaskedFrames = other.sendUnmaskedFrames;
      this.tryUsePerFrameCompression = other.tryUsePerFrameCompression;
      this.tryUsePerMessageCompression = other.tryUsePerMessageCompression;
      this.allowClientNoContext = other.allowClientNoContext;
      this.compressionLevel = other.compressionLevel;
      this.requestServerNoContext = other.requestServerNoContext;
      this.closingTimeout = other.closingTimeout;
      this.shared = other.shared;
      this.name = other.name;
   }

   public WebSocketClientOptions(JsonObject json) {
      super(json);
      this.init();
      WebSocketClientOptionsConverter.fromJson(json, this);
   }

   private void init() {
      this.verifyHost = true;
      this.defaultHost = "localhost";
      this.defaultPort = 80;
      this.maxFrameSize = 65536;
      this.maxMessageSize = 262144;
      this.maxConnections = 50;
      this.sendUnmaskedFrames = false;
      this.tryUsePerFrameCompression = false;
      this.tryUsePerMessageCompression = false;
      this.compressionLevel = 6;
      this.allowClientNoContext = false;
      this.requestServerNoContext = false;
      this.closingTimeout = 10;
      this.shared = false;
      this.name = "__vertx.DEFAULT";
   }

   public String getDefaultHost() {
      return this.defaultHost;
   }

   public WebSocketClientOptions setDefaultHost(String defaultHost) {
      this.defaultHost = defaultHost;
      return this;
   }

   public int getDefaultPort() {
      return this.defaultPort;
   }

   public WebSocketClientOptions setDefaultPort(int defaultPort) {
      this.defaultPort = defaultPort;
      return this;
   }

   public boolean isVerifyHost() {
      return this.verifyHost;
   }

   public WebSocketClientOptions setVerifyHost(boolean verifyHost) {
      this.verifyHost = verifyHost;
      return this;
   }

   public boolean isSendUnmaskedFrames() {
      return this.sendUnmaskedFrames;
   }

   public WebSocketClientOptions setSendUnmaskedFrames(boolean sendUnmaskedFrames) {
      this.sendUnmaskedFrames = sendUnmaskedFrames;
      return this;
   }

   public int getMaxFrameSize() {
      return this.maxFrameSize;
   }

   public WebSocketClientOptions setMaxFrameSize(int maxFrameSize) {
      this.maxFrameSize = maxFrameSize;
      return this;
   }

   public int getMaxMessageSize() {
      return this.maxMessageSize;
   }

   public WebSocketClientOptions setMaxMessageSize(int maxMessageSize) {
      this.maxMessageSize = maxMessageSize;
      return this;
   }

   public int getMaxConnections() {
      return this.maxConnections;
   }

   public WebSocketClientOptions setMaxConnections(int maxConnections) {
      if (maxConnections != 0 && maxConnections >= -1) {
         this.maxConnections = maxConnections;
         return this;
      } else {
         throw new IllegalArgumentException("maxWebSockets must be > 0 or -1 (disabled)");
      }
   }

   public WebSocketClientOptions setTryUsePerFrameCompression(boolean offer) {
      this.tryUsePerFrameCompression = offer;
      return this;
   }

   public boolean getTryUsePerFrameCompression() {
      return this.tryUsePerFrameCompression;
   }

   public WebSocketClientOptions setTryUsePerMessageCompression(boolean offer) {
      this.tryUsePerMessageCompression = offer;
      return this;
   }

   public boolean getTryUsePerMessageCompression() {
      return this.tryUsePerMessageCompression;
   }

   public WebSocketClientOptions setCompressionLevel(int compressionLevel) {
      this.compressionLevel = compressionLevel;
      return this;
   }

   public int getCompressionLevel() {
      return this.compressionLevel;
   }

   public WebSocketClientOptions setCompressionAllowClientNoContext(boolean offer) {
      this.allowClientNoContext = offer;
      return this;
   }

   public boolean getCompressionAllowClientNoContext() {
      return this.allowClientNoContext;
   }

   public WebSocketClientOptions setCompressionRequestServerNoContext(boolean offer) {
      this.requestServerNoContext = offer;
      return this;
   }

   public boolean getCompressionRequestServerNoContext() {
      return this.requestServerNoContext;
   }

   public int getClosingTimeout() {
      return this.closingTimeout;
   }

   public WebSocketClientOptions setClosingTimeout(int closingTimeout) {
      this.closingTimeout = closingTimeout;
      return this;
   }

   public boolean isShared() {
      return this.shared;
   }

   public WebSocketClientOptions setShared(boolean shared) {
      this.shared = shared;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public WebSocketClientOptions setName(String name) {
      Objects.requireNonNull(name, "Client name cannot be null");
      this.name = name;
      return this;
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      WebSocketClientOptionsConverter.toJson(this, json);
      return json;
   }

   public WebSocketClientOptions setTrustAll(boolean trustAll) {
      return (WebSocketClientOptions)super.setTrustAll(trustAll);
   }

   public WebSocketClientOptions setConnectTimeout(int connectTimeout) {
      return (WebSocketClientOptions)super.setConnectTimeout(connectTimeout);
   }

   public WebSocketClientOptions setMetricsName(String metricsName) {
      return (WebSocketClientOptions)super.setMetricsName(metricsName);
   }

   public WebSocketClientOptions setProxyOptions(ProxyOptions proxyOptions) {
      return (WebSocketClientOptions)super.setProxyOptions(proxyOptions);
   }

   public WebSocketClientOptions setNonProxyHosts(List nonProxyHosts) {
      return (WebSocketClientOptions)super.setNonProxyHosts(nonProxyHosts);
   }

   public WebSocketClientOptions setLocalAddress(String localAddress) {
      return (WebSocketClientOptions)super.setLocalAddress(localAddress);
   }

   public WebSocketClientOptions setLogActivity(boolean logEnabled) {
      return (WebSocketClientOptions)super.setLogActivity(logEnabled);
   }

   public WebSocketClientOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (WebSocketClientOptions)super.setActivityLogDataFormat(activityLogDataFormat);
   }

   public WebSocketClientOptions setTcpNoDelay(boolean tcpNoDelay) {
      return (WebSocketClientOptions)super.setTcpNoDelay(tcpNoDelay);
   }

   public WebSocketClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      return (WebSocketClientOptions)super.setTcpKeepAlive(tcpKeepAlive);
   }

   public WebSocketClientOptions setSoLinger(int soLinger) {
      return (WebSocketClientOptions)super.setSoLinger(soLinger);
   }

   public WebSocketClientOptions setIdleTimeout(int idleTimeout) {
      return (WebSocketClientOptions)super.setIdleTimeout(idleTimeout);
   }

   public WebSocketClientOptions setReadIdleTimeout(int idleTimeout) {
      return (WebSocketClientOptions)super.setReadIdleTimeout(idleTimeout);
   }

   public WebSocketClientOptions setWriteIdleTimeout(int idleTimeout) {
      return (WebSocketClientOptions)super.setWriteIdleTimeout(idleTimeout);
   }

   public WebSocketClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      return (WebSocketClientOptions)super.setIdleTimeoutUnit(idleTimeoutUnit);
   }

   public WebSocketClientOptions setSsl(boolean ssl) {
      return (WebSocketClientOptions)super.setSsl(ssl);
   }

   public WebSocketClientOptions setKeyCertOptions(KeyCertOptions options) {
      return (WebSocketClientOptions)super.setKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setKeyStoreOptions(JksOptions options) {
      return (WebSocketClientOptions)super.setKeyStoreOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setPfxKeyCertOptions(PfxOptions options) {
      return (WebSocketClientOptions)super.setPfxKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return (WebSocketClientOptions)super.setPemKeyCertOptions(options);
   }

   public WebSocketClientOptions setTrustOptions(TrustOptions options) {
      return (WebSocketClientOptions)super.setTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setTrustStoreOptions(JksOptions options) {
      return (WebSocketClientOptions)super.setTrustStoreOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setPfxTrustOptions(PfxOptions options) {
      return (WebSocketClientOptions)super.setPfxTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setPemTrustOptions(PemTrustOptions options) {
      return (WebSocketClientOptions)super.setPemTrustOptions(options);
   }

   public WebSocketClientOptions setUseAlpn(boolean useAlpn) {
      return (WebSocketClientOptions)super.setUseAlpn(useAlpn);
   }

   public WebSocketClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      return (WebSocketClientOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (WebSocketClientOptions)super.setJdkSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public WebSocketClientOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return (WebSocketClientOptions)super.setOpenSslEngineOptions(sslEngineOptions);
   }

   public WebSocketClientOptions setSendBufferSize(int sendBufferSize) {
      return (WebSocketClientOptions)super.setSendBufferSize(sendBufferSize);
   }

   public WebSocketClientOptions setReceiveBufferSize(int receiveBufferSize) {
      return (WebSocketClientOptions)super.setReceiveBufferSize(receiveBufferSize);
   }

   public WebSocketClientOptions setReuseAddress(boolean reuseAddress) {
      return (WebSocketClientOptions)super.setReuseAddress(reuseAddress);
   }

   public WebSocketClientOptions setReusePort(boolean reusePort) {
      return (WebSocketClientOptions)super.setReusePort(reusePort);
   }

   public WebSocketClientOptions setTrafficClass(int trafficClass) {
      return (WebSocketClientOptions)super.setTrafficClass(trafficClass);
   }

   public WebSocketClientOptions setTcpFastOpen(boolean tcpFastOpen) {
      return (WebSocketClientOptions)super.setTcpFastOpen(tcpFastOpen);
   }

   public WebSocketClientOptions setTcpCork(boolean tcpCork) {
      return (WebSocketClientOptions)super.setTcpCork(tcpCork);
   }

   public WebSocketClientOptions setTcpQuickAck(boolean tcpQuickAck) {
      return (WebSocketClientOptions)super.setTcpQuickAck(tcpQuickAck);
   }

   public WebSocketClientOptions setTcpUserTimeout(int tcpUserTimeout) {
      return (WebSocketClientOptions)super.setTcpUserTimeout(tcpUserTimeout);
   }

   public WebSocketClientOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      return (WebSocketClientOptions)super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
   }

   public WebSocketClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      return (WebSocketClientOptions)super.setSslHandshakeTimeout(sslHandshakeTimeout);
   }

   public WebSocketClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      return (WebSocketClientOptions)super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
   }

   public WebSocketClientOptions addNonProxyHost(String host) {
      return (WebSocketClientOptions)super.addNonProxyHost(host);
   }

   public WebSocketClientOptions addEnabledCipherSuite(String suite) {
      return (WebSocketClientOptions)super.addEnabledCipherSuite(suite);
   }

   public WebSocketClientOptions removeEnabledCipherSuite(String suite) {
      return (WebSocketClientOptions)super.removeEnabledCipherSuite(suite);
   }

   public WebSocketClientOptions addCrlPath(String crlPath) throws NullPointerException {
      return (WebSocketClientOptions)super.addCrlPath(crlPath);
   }

   public WebSocketClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      return (WebSocketClientOptions)super.addCrlValue(crlValue);
   }

   public WebSocketClientOptions addEnabledSecureTransportProtocol(String protocol) {
      return (WebSocketClientOptions)super.addEnabledSecureTransportProtocol(protocol);
   }
}
