package io.vertx.core.net;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public abstract class ClientOptionsBase extends TCPSSLOptions {
   public static final int DEFAULT_CONNECT_TIMEOUT = 60000;
   public static final boolean DEFAULT_TRUST_ALL = false;
   public static final String DEFAULT_METRICS_NAME = "";
   private int connectTimeout;
   private boolean trustAll;
   private String metricsName;
   private ProxyOptions proxyOptions;
   private String localAddress;
   private List nonProxyHosts;

   public ClientOptionsBase() {
      this.init();
   }

   public ClientOptionsBase(ClientOptionsBase other) {
      super((TCPSSLOptions)other);
      this.connectTimeout = other.getConnectTimeout();
      this.trustAll = other.isTrustAll();
      this.metricsName = other.metricsName;
      this.proxyOptions = other.proxyOptions != null ? new ProxyOptions(other.proxyOptions) : null;
      this.localAddress = other.localAddress;
      this.nonProxyHosts = other.nonProxyHosts != null ? new ArrayList(other.nonProxyHosts) : null;
   }

   public ClientOptionsBase(JsonObject json) {
      super(json);
      this.init();
      ClientOptionsBaseConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      ClientOptionsBaseConverter.toJson(this, json);
      return json;
   }

   private void init() {
      this.connectTimeout = 60000;
      this.trustAll = false;
      this.metricsName = "";
      this.proxyOptions = null;
      this.localAddress = null;
   }

   public boolean isTrustAll() {
      return this.trustAll;
   }

   public ClientOptionsBase setTrustAll(boolean trustAll) {
      this.trustAll = trustAll;
      return this;
   }

   public int getConnectTimeout() {
      return this.connectTimeout;
   }

   public ClientOptionsBase setConnectTimeout(int connectTimeout) {
      if (connectTimeout < 0) {
         throw new IllegalArgumentException("connectTimeout must be >= 0");
      } else {
         this.connectTimeout = connectTimeout;
         return this;
      }
   }

   public String getMetricsName() {
      return this.metricsName;
   }

   public ClientOptionsBase setMetricsName(String metricsName) {
      this.metricsName = metricsName;
      return this;
   }

   public ClientOptionsBase setProxyOptions(ProxyOptions proxyOptions) {
      this.proxyOptions = proxyOptions;
      return this;
   }

   public ProxyOptions getProxyOptions() {
      return this.proxyOptions;
   }

   public List getNonProxyHosts() {
      return this.nonProxyHosts;
   }

   public ClientOptionsBase setNonProxyHosts(List nonProxyHosts) {
      this.nonProxyHosts = nonProxyHosts;
      return this;
   }

   public ClientOptionsBase addNonProxyHost(String host) {
      if (this.nonProxyHosts == null) {
         this.nonProxyHosts = new ArrayList();
      }

      this.nonProxyHosts.add(host);
      return this;
   }

   public String getLocalAddress() {
      return this.localAddress;
   }

   public ClientOptionsBase setLocalAddress(String localAddress) {
      this.localAddress = localAddress;
      return this;
   }

   public ClientOptionsBase setLogActivity(boolean logEnabled) {
      return (ClientOptionsBase)super.setLogActivity(logEnabled);
   }

   public ClientOptionsBase setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (ClientOptionsBase)super.setActivityLogDataFormat(activityLogDataFormat);
   }

   public ClientOptionsBase setTcpNoDelay(boolean tcpNoDelay) {
      return (ClientOptionsBase)super.setTcpNoDelay(tcpNoDelay);
   }

   public ClientOptionsBase setTcpKeepAlive(boolean tcpKeepAlive) {
      return (ClientOptionsBase)super.setTcpKeepAlive(tcpKeepAlive);
   }

   public ClientOptionsBase setSoLinger(int soLinger) {
      return (ClientOptionsBase)super.setSoLinger(soLinger);
   }

   public ClientOptionsBase setIdleTimeout(int idleTimeout) {
      return (ClientOptionsBase)super.setIdleTimeout(idleTimeout);
   }

   public ClientOptionsBase setReadIdleTimeout(int idleTimeout) {
      return (ClientOptionsBase)super.setReadIdleTimeout(idleTimeout);
   }

   public ClientOptionsBase setWriteIdleTimeout(int idleTimeout) {
      return (ClientOptionsBase)super.setWriteIdleTimeout(idleTimeout);
   }

   public ClientOptionsBase setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      return (ClientOptionsBase)super.setIdleTimeoutUnit(idleTimeoutUnit);
   }

   public ClientOptionsBase setSsl(boolean ssl) {
      return (ClientOptionsBase)super.setSsl(ssl);
   }

   public ClientOptionsBase setKeyCertOptions(KeyCertOptions options) {
      return (ClientOptionsBase)super.setKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setKeyStoreOptions(JksOptions options) {
      return (ClientOptionsBase)super.setKeyStoreOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setPfxKeyCertOptions(PfxOptions options) {
      return (ClientOptionsBase)super.setPfxKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setPemKeyCertOptions(PemKeyCertOptions options) {
      return (ClientOptionsBase)super.setPemKeyCertOptions(options);
   }

   public ClientOptionsBase setTrustOptions(TrustOptions options) {
      return (ClientOptionsBase)super.setTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setTrustStoreOptions(JksOptions options) {
      return (ClientOptionsBase)super.setTrustStoreOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setPfxTrustOptions(PfxOptions options) {
      return (ClientOptionsBase)super.setPfxTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setPemTrustOptions(PemTrustOptions options) {
      return (ClientOptionsBase)super.setPemTrustOptions(options);
   }

   public ClientOptionsBase setUseAlpn(boolean useAlpn) {
      return (ClientOptionsBase)super.setUseAlpn(useAlpn);
   }

   public ClientOptionsBase setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      return (ClientOptionsBase)super.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return (ClientOptionsBase)super.setJdkSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public ClientOptionsBase setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return (ClientOptionsBase)super.setOpenSslEngineOptions(sslEngineOptions);
   }

   public ClientOptionsBase setSendBufferSize(int sendBufferSize) {
      return (ClientOptionsBase)super.setSendBufferSize(sendBufferSize);
   }

   public ClientOptionsBase setReceiveBufferSize(int receiveBufferSize) {
      return (ClientOptionsBase)super.setReceiveBufferSize(receiveBufferSize);
   }

   public ClientOptionsBase setReuseAddress(boolean reuseAddress) {
      return (ClientOptionsBase)super.setReuseAddress(reuseAddress);
   }

   public ClientOptionsBase setReusePort(boolean reusePort) {
      return (ClientOptionsBase)super.setReusePort(reusePort);
   }

   public ClientOptionsBase setTrafficClass(int trafficClass) {
      return (ClientOptionsBase)super.setTrafficClass(trafficClass);
   }

   public ClientOptionsBase addEnabledCipherSuite(String suite) {
      return (ClientOptionsBase)super.addEnabledCipherSuite(suite);
   }

   public ClientOptionsBase removeEnabledCipherSuite(String suite) {
      return (ClientOptionsBase)super.removeEnabledCipherSuite(suite);
   }

   public ClientOptionsBase addCrlPath(String crlPath) throws NullPointerException {
      return (ClientOptionsBase)super.addCrlPath(crlPath);
   }

   public ClientOptionsBase addCrlValue(Buffer crlValue) throws NullPointerException {
      return (ClientOptionsBase)super.addCrlValue(crlValue);
   }

   public ClientOptionsBase addEnabledSecureTransportProtocol(String protocol) {
      return (ClientOptionsBase)super.addEnabledSecureTransportProtocol(protocol);
   }

   public ClientOptionsBase removeEnabledSecureTransportProtocol(String protocol) {
      return (ClientOptionsBase)super.removeEnabledSecureTransportProtocol(protocol);
   }

   public ClientOptionsBase setTcpFastOpen(boolean tcpFastOpen) {
      return (ClientOptionsBase)super.setTcpFastOpen(tcpFastOpen);
   }

   public ClientOptionsBase setTcpCork(boolean tcpCork) {
      return (ClientOptionsBase)super.setTcpCork(tcpCork);
   }

   public ClientOptionsBase setTcpQuickAck(boolean tcpQuickAck) {
      return (ClientOptionsBase)super.setTcpQuickAck(tcpQuickAck);
   }

   public ClientOptionsBase setTcpUserTimeout(int tcpUserTimeout) {
      return (ClientOptionsBase)super.setTcpUserTimeout(tcpUserTimeout);
   }
}
