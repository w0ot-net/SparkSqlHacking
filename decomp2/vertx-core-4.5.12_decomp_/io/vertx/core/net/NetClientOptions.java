package io.vertx.core.net;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class NetClientOptions extends ClientOptionsBase {
   public static final int DEFAULT_RECONNECT_ATTEMPTS = 0;
   public static final long DEFAULT_RECONNECT_INTERVAL = 1000L;
   public static final String DEFAULT_HOSTNAME_VERIFICATION_ALGORITHM = null;
   public static final boolean DEFAULT_REGISTER_WRITE_HANDLER = false;
   private int reconnectAttempts;
   private long reconnectInterval;
   private String hostnameVerificationAlgorithm;
   private List applicationLayerProtocols;
   private boolean registerWriteHandler;

   public NetClientOptions() {
      this.init();
   }

   public NetClientOptions(NetClientOptions other) {
      super((ClientOptionsBase)other);
      this.reconnectAttempts = other.getReconnectAttempts();
      this.reconnectInterval = other.getReconnectInterval();
      this.hostnameVerificationAlgorithm = other.getHostnameVerificationAlgorithm();
      this.applicationLayerProtocols = other.applicationLayerProtocols != null ? new ArrayList(other.applicationLayerProtocols) : null;
      this.registerWriteHandler = other.registerWriteHandler;
   }

   public NetClientOptions(ClientOptionsBase other) {
      super(other);
   }

   public NetClientOptions(JsonObject json) {
      super(json);
      this.init();
      NetClientOptionsConverter.fromJson(json, this);
   }

   private void init() {
      this.reconnectAttempts = 0;
      this.reconnectInterval = 1000L;
      this.hostnameVerificationAlgorithm = DEFAULT_HOSTNAME_VERIFICATION_ALGORITHM;
      this.registerWriteHandler = false;
   }

   public NetClientOptions setSendBufferSize(int sendBufferSize) {
      super.setSendBufferSize(sendBufferSize);
      return this;
   }

   public NetClientOptions setReceiveBufferSize(int receiveBufferSize) {
      super.setReceiveBufferSize(receiveBufferSize);
      return this;
   }

   public NetClientOptions setReuseAddress(boolean reuseAddress) {
      super.setReuseAddress(reuseAddress);
      return this;
   }

   public NetClientOptions setReusePort(boolean reusePort) {
      super.setReusePort(reusePort);
      return this;
   }

   public NetClientOptions setTrafficClass(int trafficClass) {
      super.setTrafficClass(trafficClass);
      return this;
   }

   public NetClientOptions setTcpNoDelay(boolean tcpNoDelay) {
      super.setTcpNoDelay(tcpNoDelay);
      return this;
   }

   public NetClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      super.setTcpKeepAlive(tcpKeepAlive);
      return this;
   }

   public NetClientOptions setSoLinger(int soLinger) {
      super.setSoLinger(soLinger);
      return this;
   }

   public NetClientOptions setIdleTimeout(int idleTimeout) {
      super.setIdleTimeout(idleTimeout);
      return this;
   }

   public NetClientOptions setReadIdleTimeout(int idleTimeout) {
      super.setReadIdleTimeout(idleTimeout);
      return this;
   }

   public NetClientOptions setWriteIdleTimeout(int idleTimeout) {
      super.setWriteIdleTimeout(idleTimeout);
      return this;
   }

   public NetClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      super.setIdleTimeoutUnit(idleTimeoutUnit);
      return this;
   }

   public NetClientOptions setSsl(boolean ssl) {
      super.setSsl(ssl);
      return this;
   }

   public NetClientOptions setKeyCertOptions(KeyCertOptions options) {
      super.setKeyCertOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetClientOptions setKeyStoreOptions(JksOptions options) {
      super.setKeyStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetClientOptions setPfxKeyCertOptions(PfxOptions options) {
      return (NetClientOptions)super.setPfxKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public NetClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return (NetClientOptions)super.setPemKeyCertOptions(options);
   }

   public NetClientOptions setTrustOptions(TrustOptions options) {
      super.setTrustOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetClientOptions setTrustStoreOptions(JksOptions options) {
      super.setTrustStoreOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public NetClientOptions setPemTrustOptions(PemTrustOptions options) {
      return (NetClientOptions)super.setPemTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public NetClientOptions setPfxTrustOptions(PfxOptions options) {
      return (NetClientOptions)super.setPfxTrustOptions(options);
   }

   public NetClientOptions addEnabledCipherSuite(String suite) {
      super.addEnabledCipherSuite(suite);
      return this;
   }

   public NetClientOptions removeEnabledCipherSuite(String suite) {
      super.removeEnabledCipherSuite(suite);
      return this;
   }

   public NetClientOptions addEnabledSecureTransportProtocol(String protocol) {
      super.addEnabledSecureTransportProtocol(protocol);
      return this;
   }

   public NetClientOptions removeEnabledSecureTransportProtocol(String protocol) {
      return (NetClientOptions)super.removeEnabledSecureTransportProtocol(protocol);
   }

   public NetClientOptions setUseAlpn(boolean useAlpn) {
      return (NetClientOptions)super.setUseAlpn(useAlpn);
   }

   public NetClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      return (NetClientOptions)super.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public NetClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (NetClientOptions)super.setJdkSslEngineOptions(sslEngineOptions);
   }

   public NetClientOptions setTcpFastOpen(boolean tcpFastOpen) {
      return (NetClientOptions)super.setTcpFastOpen(tcpFastOpen);
   }

   public NetClientOptions setTcpCork(boolean tcpCork) {
      return (NetClientOptions)super.setTcpCork(tcpCork);
   }

   public NetClientOptions setTcpQuickAck(boolean tcpQuickAck) {
      return (NetClientOptions)super.setTcpQuickAck(tcpQuickAck);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return super.setOpenSslEngineOptions(sslEngineOptions);
   }

   public NetClientOptions addCrlPath(String crlPath) throws NullPointerException {
      return (NetClientOptions)super.addCrlPath(crlPath);
   }

   public NetClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      return (NetClientOptions)super.addCrlValue(crlValue);
   }

   public NetClientOptions setTrustAll(boolean trustAll) {
      super.setTrustAll(trustAll);
      return this;
   }

   public NetClientOptions setConnectTimeout(int connectTimeout) {
      super.setConnectTimeout(connectTimeout);
      return this;
   }

   public NetClientOptions setMetricsName(String metricsName) {
      return (NetClientOptions)super.setMetricsName(metricsName);
   }

   public NetClientOptions setReconnectAttempts(int attempts) {
      if (attempts < -1) {
         throw new IllegalArgumentException("reconnect attempts must be >= -1");
      } else {
         this.reconnectAttempts = attempts;
         return this;
      }
   }

   public int getReconnectAttempts() {
      return this.reconnectAttempts;
   }

   public NetClientOptions setReconnectInterval(long interval) {
      if (interval < 1L) {
         throw new IllegalArgumentException("reconnect interval must be >= 1");
      } else {
         this.reconnectInterval = interval;
         return this;
      }
   }

   public String getHostnameVerificationAlgorithm() {
      return this.hostnameVerificationAlgorithm;
   }

   public NetClientOptions setHostnameVerificationAlgorithm(String hostnameVerificationAlgorithm) {
      Objects.requireNonNull(hostnameVerificationAlgorithm, "hostnameVerificationAlgorithm can not be null!");
      this.hostnameVerificationAlgorithm = hostnameVerificationAlgorithm;
      return this;
   }

   public List getApplicationLayerProtocols() {
      return this.applicationLayerProtocols;
   }

   public NetClientOptions setApplicationLayerProtocols(List protocols) {
      this.applicationLayerProtocols = protocols;
      return this;
   }

   public long getReconnectInterval() {
      return this.reconnectInterval;
   }

   public NetClientOptions setLogActivity(boolean logEnabled) {
      return (NetClientOptions)super.setLogActivity(logEnabled);
   }

   public NetClientOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (NetClientOptions)super.setActivityLogDataFormat(activityLogDataFormat);
   }

   public NetClientOptions setProxyOptions(ProxyOptions proxyOptions) {
      return (NetClientOptions)super.setProxyOptions(proxyOptions);
   }

   public NetClientOptions setNonProxyHosts(List nonProxyHosts) {
      return (NetClientOptions)super.setNonProxyHosts(nonProxyHosts);
   }

   public NetClientOptions addNonProxyHost(String nonProxyHost) {
      return (NetClientOptions)super.addNonProxyHost(nonProxyHost);
   }

   public NetClientOptions setLocalAddress(String localAddress) {
      return (NetClientOptions)super.setLocalAddress(localAddress);
   }

   public NetClientOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      return (NetClientOptions)super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
   }

   public NetClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      return (NetClientOptions)super.setSslHandshakeTimeout(sslHandshakeTimeout);
   }

   public NetClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      return (NetClientOptions)super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      NetClientOptionsConverter.toJson(this, json);
      return json;
   }

   public boolean isRegisterWriteHandler() {
      return this.registerWriteHandler;
   }

   public NetClientOptions setRegisterWriteHandler(boolean registerWriteHandler) {
      this.registerWriteHandler = registerWriteHandler;
      return this;
   }
}
