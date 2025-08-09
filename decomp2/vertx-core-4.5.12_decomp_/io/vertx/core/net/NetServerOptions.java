package io.vertx.core.net;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.json.JsonObject;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class NetServerOptions extends TCPSSLOptions {
   public static final int DEFAULT_PORT = 0;
   public static final String DEFAULT_HOST = "0.0.0.0";
   public static final int DEFAULT_ACCEPT_BACKLOG = -1;
   public static final ClientAuth DEFAULT_CLIENT_AUTH;
   public static final boolean DEFAULT_SNI = false;
   public static final boolean DEFAULT_USE_PROXY_PROTOCOL = false;
   public static final long DEFAULT_PROXY_PROTOCOL_TIMEOUT = 10L;
   public static final TimeUnit DEFAULT_PROXY_PROTOCOL_TIMEOUT_TIME_UNIT;
   public static final boolean DEFAULT_REGISTER_WRITE_HANDLER = false;
   private int port;
   private String host;
   private int acceptBacklog;
   private ClientAuth clientAuth;
   private boolean sni;
   private boolean useProxyProtocol;
   private long proxyProtocolTimeout;
   private TimeUnit proxyProtocolTimeoutUnit;
   private boolean registerWriteHandler;
   private TrafficShapingOptions trafficShapingOptions;

   public NetServerOptions() {
      this.init();
   }

   public NetServerOptions(NetServerOptions other) {
      super((TCPSSLOptions)other);
      this.port = other.getPort();
      this.host = other.getHost();
      this.acceptBacklog = other.getAcceptBacklog();
      this.clientAuth = other.getClientAuth();
      this.sni = other.isSni();
      this.useProxyProtocol = other.isUseProxyProtocol();
      this.proxyProtocolTimeout = other.proxyProtocolTimeout;
      this.proxyProtocolTimeoutUnit = other.getProxyProtocolTimeoutUnit() != null ? other.getProxyProtocolTimeoutUnit() : DEFAULT_PROXY_PROTOCOL_TIMEOUT_TIME_UNIT;
      this.registerWriteHandler = other.registerWriteHandler;
      this.trafficShapingOptions = other.getTrafficShapingOptions();
   }

   public NetServerOptions(JsonObject json) {
      super(json);
      this.init();
      NetServerOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      NetServerOptionsConverter.toJson(this, json);
      return json;
   }

   public NetServerOptions setSendBufferSize(int sendBufferSize) {
      super.setSendBufferSize(sendBufferSize);
      return this;
   }

   public NetServerOptions setReceiveBufferSize(int receiveBufferSize) {
      super.setReceiveBufferSize(receiveBufferSize);
      return this;
   }

   public NetServerOptions setReuseAddress(boolean reuseAddress) {
      super.setReuseAddress(reuseAddress);
      return this;
   }

   public NetServerOptions setReusePort(boolean reusePort) {
      super.setReusePort(reusePort);
      return this;
   }

   public NetServerOptions setTrafficClass(int trafficClass) {
      super.setTrafficClass(trafficClass);
      return this;
   }

   public NetServerOptions setTcpNoDelay(boolean tcpNoDelay) {
      super.setTcpNoDelay(tcpNoDelay);
      return this;
   }

   public NetServerOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      super.setTcpKeepAlive(tcpKeepAlive);
      return this;
   }

   public NetServerOptions setSoLinger(int soLinger) {
      super.setSoLinger(soLinger);
      return this;
   }

   public NetServerOptions setIdleTimeout(int idleTimeout) {
      super.setIdleTimeout(idleTimeout);
      return this;
   }

   public NetServerOptions setReadIdleTimeout(int idleTimeout) {
      super.setReadIdleTimeout(idleTimeout);
      return this;
   }

   public NetServerOptions setWriteIdleTimeout(int idleTimeout) {
      super.setWriteIdleTimeout(idleTimeout);
      return this;
   }

   public NetServerOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      super.setIdleTimeoutUnit(idleTimeoutUnit);
      return this;
   }

   public NetServerOptions setSsl(boolean ssl) {
      super.setSsl(ssl);
      return this;
   }

   public NetServerOptions setUseAlpn(boolean useAlpn) {
      super.setUseAlpn(useAlpn);
      return this;
   }

   public NetServerOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      super.setSslEngineOptions(sslEngineOptions);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (NetServerOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return (NetServerOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   public NetServerOptions setKeyCertOptions(KeyCertOptions options) {
      super.setKeyCertOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setKeyStoreOptions(JksOptions options) {
      super.setKeyStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setPfxKeyCertOptions(PfxOptions options) {
      return (NetServerOptions)super.setPfxKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return (NetServerOptions)super.setPemKeyCertOptions(options);
   }

   public NetServerOptions setTrustOptions(TrustOptions options) {
      super.setTrustOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setTrustStoreOptions(JksOptions options) {
      super.setTrustStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setPfxTrustOptions(PfxOptions options) {
      return (NetServerOptions)super.setPfxTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public NetServerOptions setPemTrustOptions(PemTrustOptions options) {
      return (NetServerOptions)super.setPemTrustOptions(options);
   }

   public NetServerOptions addEnabledCipherSuite(String suite) {
      super.addEnabledCipherSuite(suite);
      return this;
   }

   public NetServerOptions removeEnabledCipherSuite(String suite) {
      super.removeEnabledCipherSuite(suite);
      return this;
   }

   public NetServerOptions addEnabledSecureTransportProtocol(String protocol) {
      super.addEnabledSecureTransportProtocol(protocol);
      return this;
   }

   public NetServerOptions removeEnabledSecureTransportProtocol(String protocol) {
      return (NetServerOptions)super.removeEnabledSecureTransportProtocol(protocol);
   }

   public NetServerOptions setTcpFastOpen(boolean tcpFastOpen) {
      return (NetServerOptions)super.setTcpFastOpen(tcpFastOpen);
   }

   public NetServerOptions setTcpCork(boolean tcpCork) {
      return (NetServerOptions)super.setTcpCork(tcpCork);
   }

   public NetServerOptions setTcpQuickAck(boolean tcpQuickAck) {
      return (NetServerOptions)super.setTcpQuickAck(tcpQuickAck);
   }

   public NetServerOptions addCrlPath(String crlPath) throws NullPointerException {
      return (NetServerOptions)super.addCrlPath(crlPath);
   }

   public NetServerOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      return (NetServerOptions)super.addCrlValue(crlValue);
   }

   public NetServerOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      return (NetServerOptions)super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
   }

   public NetServerOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      return (NetServerOptions)super.setSslHandshakeTimeout(sslHandshakeTimeout);
   }

   public NetServerOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      return (NetServerOptions)super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
   }

   public int getAcceptBacklog() {
      return this.acceptBacklog;
   }

   public NetServerOptions setAcceptBacklog(int acceptBacklog) {
      this.acceptBacklog = acceptBacklog;
      return this;
   }

   public int getPort() {
      return this.port;
   }

   public NetServerOptions setPort(int port) {
      if (port > 65535) {
         throw new IllegalArgumentException("port must be <= 65535");
      } else {
         this.port = port;
         return this;
      }
   }

   public String getHost() {
      return this.host;
   }

   public NetServerOptions setHost(String host) {
      this.host = host;
      return this;
   }

   public ClientAuth getClientAuth() {
      return this.clientAuth;
   }

   public NetServerOptions setClientAuth(ClientAuth clientAuth) {
      this.clientAuth = clientAuth;
      return this;
   }

   public NetServerOptions setLogActivity(boolean logEnabled) {
      return (NetServerOptions)super.setLogActivity(logEnabled);
   }

   public NetServerOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (NetServerOptions)super.setActivityLogDataFormat(activityLogDataFormat);
   }

   public boolean isSni() {
      return this.sni;
   }

   public NetServerOptions setSni(boolean sni) {
      this.sni = sni;
      return this;
   }

   public boolean isUseProxyProtocol() {
      return this.useProxyProtocol;
   }

   public NetServerOptions setUseProxyProtocol(boolean useProxyProtocol) {
      this.useProxyProtocol = useProxyProtocol;
      return this;
   }

   public long getProxyProtocolTimeout() {
      return this.proxyProtocolTimeout;
   }

   public NetServerOptions setProxyProtocolTimeout(long proxyProtocolTimeout) {
      if (proxyProtocolTimeout < 0L) {
         throw new IllegalArgumentException("proxyProtocolTimeout must be >= 0");
      } else {
         this.proxyProtocolTimeout = proxyProtocolTimeout;
         return this;
      }
   }

   public NetServerOptions setProxyProtocolTimeoutUnit(TimeUnit proxyProtocolTimeoutUnit) {
      this.proxyProtocolTimeoutUnit = proxyProtocolTimeoutUnit;
      return this;
   }

   public TimeUnit getProxyProtocolTimeoutUnit() {
      return this.proxyProtocolTimeoutUnit;
   }

   public TrafficShapingOptions getTrafficShapingOptions() {
      return this.trafficShapingOptions;
   }

   public NetServerOptions setTrafficShapingOptions(TrafficShapingOptions trafficShapingOptions) {
      this.trafficShapingOptions = trafficShapingOptions;
      return this;
   }

   private void init() {
      this.port = 0;
      this.host = "0.0.0.0";
      this.acceptBacklog = -1;
      this.clientAuth = DEFAULT_CLIENT_AUTH;
      this.sni = false;
      this.useProxyProtocol = false;
      this.proxyProtocolTimeout = 10L;
      this.proxyProtocolTimeoutUnit = DEFAULT_PROXY_PROTOCOL_TIMEOUT_TIME_UNIT;
      this.registerWriteHandler = false;
   }

   public boolean isRegisterWriteHandler() {
      return this.registerWriteHandler;
   }

   public NetServerOptions setRegisterWriteHandler(boolean registerWriteHandler) {
      this.registerWriteHandler = registerWriteHandler;
      return this;
   }

   static {
      DEFAULT_CLIENT_AUTH = ClientAuth.NONE;
      DEFAULT_PROXY_PROTOCOL_TIMEOUT_TIME_UNIT = TimeUnit.SECONDS;
   }
}
