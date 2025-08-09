package io.vertx.core.net;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public abstract class TCPSSLOptions extends NetworkOptions {
   public static final boolean DEFAULT_TCP_NO_DELAY = true;
   public static final boolean DEFAULT_TCP_KEEP_ALIVE = false;
   public static final int DEFAULT_TCP_KEEPALIVE_IDLE_SECONDS = -1;
   public static final int DEFAULT_TCP_KEEPALIVE_COUNT = -1;
   public static final int DEFAULT_TCP_KEEAPLIVE_INTERVAL_SECONDS = -1;
   public static final int DEFAULT_SO_LINGER = -1;
   public static final boolean DEFAULT_SSL = false;
   public static final int DEFAULT_IDLE_TIMEOUT = 0;
   public static final TimeUnit DEFAULT_IDLE_TIMEOUT_TIME_UNIT;
   public static final int DEFAULT_READ_IDLE_TIMEOUT = 0;
   public static final int DEFAULT_WRITE_IDLE_TIMEOUT = 0;
   public static final boolean DEFAULT_USE_ALPN = false;
   public static final SSLEngineOptions DEFAULT_SSL_ENGINE;
   public static final List DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS;
   public static final boolean DEFAULT_TCP_FAST_OPEN = false;
   public static final boolean DEFAULT_TCP_CORK = false;
   public static final boolean DEFAULT_TCP_QUICKACK = false;
   public static final int DEFAULT_TCP_USER_TIMEOUT = 0;
   public static final long DEFAULT_SSL_HANDSHAKE_TIMEOUT = 10L;
   public static final TimeUnit DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT;
   private boolean tcpNoDelay;
   private boolean tcpKeepAlive;
   private int tcpKeepAliveIdleSeconds;
   private int tcpKeepAliveCount;
   private int tcpKeepAliveIntervalSeconds;
   private int soLinger;
   private int idleTimeout;
   private int readIdleTimeout;
   private int writeIdleTimeout;
   private TimeUnit idleTimeoutUnit;
   private boolean ssl;
   private SSLEngineOptions sslEngineOptions;
   private SSLOptions sslOptions;
   private boolean tcpFastOpen;
   private boolean tcpCork;
   private boolean tcpQuickAck;
   private int tcpUserTimeout;

   public TCPSSLOptions() {
      this.init();
   }

   public TCPSSLOptions(TCPSSLOptions other) {
      super((NetworkOptions)other);
      this.tcpNoDelay = other.isTcpNoDelay();
      this.tcpKeepAlive = other.isTcpKeepAlive();
      this.tcpKeepAliveIdleSeconds = other.getTcpKeepAliveIdleSeconds();
      this.tcpKeepAliveCount = other.getTcpKeepAliveCount();
      this.tcpKeepAliveIntervalSeconds = other.getTcpKeepAliveIntervalSeconds();
      this.soLinger = other.getSoLinger();
      this.idleTimeout = other.getIdleTimeout();
      this.idleTimeoutUnit = other.getIdleTimeoutUnit() != null ? other.getIdleTimeoutUnit() : DEFAULT_IDLE_TIMEOUT_TIME_UNIT;
      this.readIdleTimeout = other.getReadIdleTimeout();
      this.writeIdleTimeout = other.getWriteIdleTimeout();
      this.ssl = other.isSsl();
      this.sslEngineOptions = other.sslEngineOptions != null ? other.sslEngineOptions.copy() : null;
      this.tcpFastOpen = other.isTcpFastOpen();
      this.tcpCork = other.isTcpCork();
      this.tcpQuickAck = other.isTcpQuickAck();
      this.tcpUserTimeout = other.getTcpUserTimeout();
      this.sslOptions = new SSLOptions(other.sslOptions);
   }

   public TCPSSLOptions(JsonObject json) {
      super(json);
      this.init();
      TCPSSLOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      TCPSSLOptionsConverter.toJson(this, json);
      return json;
   }

   private void init() {
      this.tcpNoDelay = true;
      this.tcpKeepAlive = false;
      this.tcpKeepAliveIdleSeconds = -1;
      this.tcpKeepAliveCount = -1;
      this.tcpKeepAliveIntervalSeconds = -1;
      this.soLinger = -1;
      this.idleTimeout = 0;
      this.readIdleTimeout = 0;
      this.writeIdleTimeout = 0;
      this.idleTimeoutUnit = DEFAULT_IDLE_TIMEOUT_TIME_UNIT;
      this.ssl = false;
      this.sslEngineOptions = DEFAULT_SSL_ENGINE;
      this.tcpFastOpen = false;
      this.tcpCork = false;
      this.tcpQuickAck = false;
      this.tcpUserTimeout = 0;
      this.sslOptions = new SSLOptions();
   }

   @GenIgnore
   public SSLOptions getSslOptions() {
      return this.sslOptions;
   }

   public boolean isTcpNoDelay() {
      return this.tcpNoDelay;
   }

   public TCPSSLOptions setTcpNoDelay(boolean tcpNoDelay) {
      this.tcpNoDelay = tcpNoDelay;
      return this;
   }

   public boolean isTcpKeepAlive() {
      return this.tcpKeepAlive;
   }

   public TCPSSLOptions setTcpKeepAlive(boolean tcpKeepAlive) {
      this.tcpKeepAlive = tcpKeepAlive;
      return this;
   }

   public int getTcpKeepAliveIdleSeconds() {
      return this.tcpKeepAliveIdleSeconds;
   }

   public TCPSSLOptions setTcpKeepAliveIdleSeconds(int tcpKeepAliveIdleSeconds) {
      Arguments.require(tcpKeepAliveIdleSeconds > 0 || tcpKeepAliveIdleSeconds == -1, "tcpKeepAliveIdleSeconds must be > 0");
      this.tcpKeepAliveIdleSeconds = tcpKeepAliveIdleSeconds;
      return this;
   }

   public int getTcpKeepAliveCount() {
      return this.tcpKeepAliveCount;
   }

   public TCPSSLOptions setTcpKeepAliveCount(int tcpKeepAliveCount) {
      Arguments.require(tcpKeepAliveCount > 0 || tcpKeepAliveCount == -1, "tcpKeepAliveCount must be > 0");
      this.tcpKeepAliveCount = tcpKeepAliveCount;
      return this;
   }

   public int getTcpKeepAliveIntervalSeconds() {
      return this.tcpKeepAliveIntervalSeconds;
   }

   public TCPSSLOptions setTcpKeepAliveIntervalSeconds(int tcpKeepAliveIntervalSeconds) {
      Arguments.require(tcpKeepAliveIntervalSeconds > 0 || tcpKeepAliveIntervalSeconds == -1, "tcpKeepAliveIntervalSeconds must be > 0");
      this.tcpKeepAliveIntervalSeconds = tcpKeepAliveIntervalSeconds;
      return this;
   }

   public int getSoLinger() {
      return this.soLinger;
   }

   public TCPSSLOptions setSoLinger(int soLinger) {
      if (soLinger < 0 && soLinger != -1) {
         throw new IllegalArgumentException("soLinger must be >= 0");
      } else {
         this.soLinger = soLinger;
         return this;
      }
   }

   public TCPSSLOptions setIdleTimeout(int idleTimeout) {
      if (idleTimeout < 0) {
         throw new IllegalArgumentException("idleTimeout must be >= 0");
      } else {
         this.idleTimeout = idleTimeout;
         return this;
      }
   }

   public int getIdleTimeout() {
      return this.idleTimeout;
   }

   public TCPSSLOptions setReadIdleTimeout(int idleTimeout) {
      if (idleTimeout < 0) {
         throw new IllegalArgumentException("readIdleTimeout must be >= 0");
      } else {
         this.readIdleTimeout = idleTimeout;
         return this;
      }
   }

   public int getReadIdleTimeout() {
      return this.readIdleTimeout;
   }

   public TCPSSLOptions setWriteIdleTimeout(int idleTimeout) {
      if (idleTimeout < 0) {
         throw new IllegalArgumentException("writeIdleTimeout must be >= 0");
      } else {
         this.writeIdleTimeout = idleTimeout;
         return this;
      }
   }

   public int getWriteIdleTimeout() {
      return this.writeIdleTimeout;
   }

   public TCPSSLOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
      this.idleTimeoutUnit = idleTimeoutUnit;
      return this;
   }

   public TimeUnit getIdleTimeoutUnit() {
      return this.idleTimeoutUnit;
   }

   public boolean isSsl() {
      return this.ssl;
   }

   public TCPSSLOptions setSsl(boolean ssl) {
      this.ssl = ssl;
      return this;
   }

   @GenIgnore
   public KeyCertOptions getKeyCertOptions() {
      return this.sslOptions.getKeyCertOptions();
   }

   @GenIgnore
   public TCPSSLOptions setKeyCertOptions(KeyCertOptions options) {
      this.sslOptions.setKeyCertOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public JksOptions getKeyStoreOptions() {
      KeyCertOptions keyCertOptions = this.sslOptions.getKeyCertOptions();
      return keyCertOptions instanceof JksOptions ? (JksOptions)keyCertOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setKeyStoreOptions(JksOptions options) {
      return this.setKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public PfxOptions getPfxKeyCertOptions() {
      KeyCertOptions keyCertOptions = this.sslOptions.getKeyCertOptions();
      return keyCertOptions instanceof PfxOptions ? (PfxOptions)keyCertOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setPfxKeyCertOptions(PfxOptions options) {
      return this.setKeyCertOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public PemKeyCertOptions getPemKeyCertOptions() {
      KeyCertOptions keyCertOptions = this.sslOptions.getKeyCertOptions();
      return keyCertOptions instanceof PemKeyCertOptions ? (PemKeyCertOptions)keyCertOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setPemKeyCertOptions(PemKeyCertOptions options) {
      return this.setKeyCertOptions(options);
   }

   public TrustOptions getTrustOptions() {
      return this.sslOptions.getTrustOptions();
   }

   public TCPSSLOptions setTrustOptions(TrustOptions options) {
      this.sslOptions.setTrustOptions(options);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public JksOptions getTrustStoreOptions() {
      TrustOptions trustOptions = this.sslOptions.getTrustOptions();
      return trustOptions instanceof JksOptions ? (JksOptions)trustOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setTrustStoreOptions(JksOptions options) {
      return this.setTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public PfxOptions getPfxTrustOptions() {
      TrustOptions trustOptions = this.sslOptions.getTrustOptions();
      return trustOptions instanceof PfxOptions ? (PfxOptions)trustOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setPfxTrustOptions(PfxOptions options) {
      return this.setTrustOptions(options);
   }

   /** @deprecated */
   @Deprecated
   public PemTrustOptions getPemTrustOptions() {
      TrustOptions trustOptions = this.sslOptions.getTrustOptions();
      return trustOptions instanceof PemTrustOptions ? (PemTrustOptions)trustOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setPemTrustOptions(PemTrustOptions options) {
      return this.setTrustOptions(options);
   }

   public TCPSSLOptions addEnabledCipherSuite(String suite) {
      this.sslOptions.addEnabledCipherSuite(suite);
      return this;
   }

   public TCPSSLOptions removeEnabledCipherSuite(String suite) {
      this.sslOptions.removeEnabledCipherSuite(suite);
      return this;
   }

   public Set getEnabledCipherSuites() {
      return this.sslOptions.getEnabledCipherSuites();
   }

   public List getCrlPaths() {
      return this.sslOptions.getCrlPaths();
   }

   public TCPSSLOptions addCrlPath(String crlPath) {
      this.sslOptions.addCrlPath(crlPath);
      return this;
   }

   public List getCrlValues() {
      return this.sslOptions.getCrlValues();
   }

   public TCPSSLOptions addCrlValue(Buffer crlValue) {
      this.sslOptions.addCrlValue(crlValue);
      return this;
   }

   public boolean isUseAlpn() {
      return this.sslOptions.isUseAlpn();
   }

   public TCPSSLOptions setUseAlpn(boolean useAlpn) {
      this.sslOptions.setUseAlpn(useAlpn);
      return this;
   }

   public SSLEngineOptions getSslEngineOptions() {
      return this.sslEngineOptions;
   }

   public TCPSSLOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
      this.sslEngineOptions = sslEngineOptions;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public JdkSSLEngineOptions getJdkSslEngineOptions() {
      return this.sslEngineOptions instanceof JdkSSLEngineOptions ? (JdkSSLEngineOptions)this.sslEngineOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
      return this.setSslEngineOptions(sslEngineOptions);
   }

   /** @deprecated */
   @Deprecated
   public OpenSSLEngineOptions getOpenSslEngineOptions() {
      return this.sslEngineOptions instanceof OpenSSLEngineOptions ? (OpenSSLEngineOptions)this.sslEngineOptions : null;
   }

   /** @deprecated */
   @Deprecated
   public TCPSSLOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
      return this.setSslEngineOptions(sslEngineOptions);
   }

   public TCPSSLOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      this.sslOptions.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
      return this;
   }

   public TCPSSLOptions addEnabledSecureTransportProtocol(String protocol) {
      this.sslOptions.addEnabledSecureTransportProtocol(protocol);
      return this;
   }

   public TCPSSLOptions removeEnabledSecureTransportProtocol(String protocol) {
      this.sslOptions.removeEnabledSecureTransportProtocol(protocol);
      return this;
   }

   public boolean isTcpFastOpen() {
      return this.tcpFastOpen;
   }

   public TCPSSLOptions setTcpFastOpen(boolean tcpFastOpen) {
      this.tcpFastOpen = tcpFastOpen;
      return this;
   }

   public boolean isTcpCork() {
      return this.tcpCork;
   }

   public TCPSSLOptions setTcpCork(boolean tcpCork) {
      this.tcpCork = tcpCork;
      return this;
   }

   public boolean isTcpQuickAck() {
      return this.tcpQuickAck;
   }

   public TCPSSLOptions setTcpQuickAck(boolean tcpQuickAck) {
      this.tcpQuickAck = tcpQuickAck;
      return this;
   }

   public int getTcpUserTimeout() {
      return this.tcpUserTimeout;
   }

   public TCPSSLOptions setTcpUserTimeout(int tcpUserTimeout) {
      this.tcpUserTimeout = tcpUserTimeout;
      return this;
   }

   public Set getEnabledSecureTransportProtocols() {
      return this.sslOptions.getEnabledSecureTransportProtocols();
   }

   public long getSslHandshakeTimeout() {
      return this.sslOptions.getSslHandshakeTimeout();
   }

   public TCPSSLOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      this.sslOptions.setSslHandshakeTimeout(sslHandshakeTimeout);
      return this;
   }

   public TCPSSLOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      this.sslOptions.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
      return this;
   }

   public TimeUnit getSslHandshakeTimeoutUnit() {
      return this.sslOptions.getSslHandshakeTimeoutUnit();
   }

   public TCPSSLOptions setLogActivity(boolean logEnabled) {
      return (TCPSSLOptions)super.setLogActivity(logEnabled);
   }

   public TCPSSLOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (TCPSSLOptions)super.setActivityLogDataFormat(activityLogDataFormat);
   }

   public TCPSSLOptions setSendBufferSize(int sendBufferSize) {
      return (TCPSSLOptions)super.setSendBufferSize(sendBufferSize);
   }

   public TCPSSLOptions setReceiveBufferSize(int receiveBufferSize) {
      return (TCPSSLOptions)super.setReceiveBufferSize(receiveBufferSize);
   }

   public TCPSSLOptions setReuseAddress(boolean reuseAddress) {
      return (TCPSSLOptions)super.setReuseAddress(reuseAddress);
   }

   public TCPSSLOptions setTrafficClass(int trafficClass) {
      return (TCPSSLOptions)super.setTrafficClass(trafficClass);
   }

   public TCPSSLOptions setReusePort(boolean reusePort) {
      return (TCPSSLOptions)super.setReusePort(reusePort);
   }

   static {
      DEFAULT_IDLE_TIMEOUT_TIME_UNIT = TimeUnit.SECONDS;
      DEFAULT_SSL_ENGINE = null;
      DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS = SSLOptions.DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS;
      DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT = SSLOptions.DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT;
   }
}
