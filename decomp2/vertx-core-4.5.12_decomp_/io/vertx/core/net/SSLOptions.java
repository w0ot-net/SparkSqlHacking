package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class SSLOptions {
   public static final boolean DEFAULT_USE_ALPN = false;
   public static final long DEFAULT_SSL_HANDSHAKE_TIMEOUT = 10L;
   public static final TimeUnit DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT;
   public static final List DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS;
   private long sslHandshakeTimeout;
   private TimeUnit sslHandshakeTimeoutUnit;
   private KeyCertOptions keyCertOptions;
   private TrustOptions trustOptions;
   private Set enabledCipherSuites;
   private ArrayList crlPaths;
   private ArrayList crlValues;
   private boolean useAlpn;
   private Set enabledSecureTransportProtocols;

   public SSLOptions(JsonObject json) {
      this();
      SSLOptionsConverter.fromJson(json, this);
   }

   public SSLOptions() {
      this.init();
   }

   public SSLOptions(SSLOptions other) {
      this.sslHandshakeTimeout = other.sslHandshakeTimeout;
      this.sslHandshakeTimeoutUnit = other.getSslHandshakeTimeoutUnit() != null ? other.getSslHandshakeTimeoutUnit() : DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT;
      this.keyCertOptions = other.getKeyCertOptions() != null ? other.getKeyCertOptions().copy() : null;
      this.trustOptions = other.getTrustOptions() != null ? other.getTrustOptions().copy() : null;
      this.enabledCipherSuites = other.getEnabledCipherSuites() == null ? new LinkedHashSet() : new LinkedHashSet(other.getEnabledCipherSuites());
      this.crlPaths = new ArrayList(other.getCrlPaths());
      this.crlValues = new ArrayList(other.getCrlValues());
      this.useAlpn = other.useAlpn;
      this.enabledSecureTransportProtocols = other.getEnabledSecureTransportProtocols() == null ? new LinkedHashSet() : new LinkedHashSet(other.getEnabledSecureTransportProtocols());
   }

   private void init() {
      this.sslHandshakeTimeout = 10L;
      this.sslHandshakeTimeoutUnit = DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT;
      this.enabledCipherSuites = new LinkedHashSet();
      this.crlPaths = new ArrayList();
      this.crlValues = new ArrayList();
      this.useAlpn = false;
      this.enabledSecureTransportProtocols = new LinkedHashSet(DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS);
   }

   @GenIgnore
   public KeyCertOptions getKeyCertOptions() {
      return this.keyCertOptions;
   }

   @GenIgnore
   public SSLOptions setKeyCertOptions(KeyCertOptions options) {
      this.keyCertOptions = options;
      return this;
   }

   public TrustOptions getTrustOptions() {
      return this.trustOptions;
   }

   public SSLOptions setTrustOptions(TrustOptions options) {
      this.trustOptions = options;
      return this;
   }

   public SSLOptions addEnabledCipherSuite(String suite) {
      this.enabledCipherSuites.add(suite);
      return this;
   }

   public SSLOptions removeEnabledCipherSuite(String suite) {
      this.enabledCipherSuites.remove(suite);
      return this;
   }

   public Set getEnabledCipherSuites() {
      return this.enabledCipherSuites;
   }

   public List getCrlPaths() {
      return this.crlPaths;
   }

   public SSLOptions addCrlPath(String crlPath) throws NullPointerException {
      Objects.requireNonNull(crlPath, "No null crl accepted");
      this.crlPaths.add(crlPath);
      return this;
   }

   public List getCrlValues() {
      return this.crlValues;
   }

   public SSLOptions addCrlValue(Buffer crlValue) throws NullPointerException {
      Objects.requireNonNull(crlValue, "No null crl accepted");
      this.crlValues.add(crlValue);
      return this;
   }

   public boolean isUseAlpn() {
      return this.useAlpn;
   }

   public SSLOptions setUseAlpn(boolean useAlpn) {
      this.useAlpn = useAlpn;
      return this;
   }

   public Set getEnabledSecureTransportProtocols() {
      return new LinkedHashSet(this.enabledSecureTransportProtocols);
   }

   public long getSslHandshakeTimeout() {
      return this.sslHandshakeTimeout;
   }

   public SSLOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
      if (sslHandshakeTimeout < 0L) {
         throw new IllegalArgumentException("sslHandshakeTimeout must be >= 0");
      } else {
         this.sslHandshakeTimeout = sslHandshakeTimeout;
         return this;
      }
   }

   public SSLOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
      this.sslHandshakeTimeoutUnit = sslHandshakeTimeoutUnit;
      return this;
   }

   public TimeUnit getSslHandshakeTimeoutUnit() {
      return this.sslHandshakeTimeoutUnit;
   }

   public SSLOptions setEnabledSecureTransportProtocols(Set enabledSecureTransportProtocols) {
      this.enabledSecureTransportProtocols = enabledSecureTransportProtocols;
      return this;
   }

   public SSLOptions addEnabledSecureTransportProtocol(String protocol) {
      this.enabledSecureTransportProtocols.add(protocol);
      return this;
   }

   public SSLOptions removeEnabledSecureTransportProtocol(String protocol) {
      this.enabledSecureTransportProtocols.remove(protocol);
      return this;
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof SSLOptions)) {
         return false;
      } else {
         SSLOptions that = (SSLOptions)obj;
         return this.sslHandshakeTimeoutUnit.toNanos(this.sslHandshakeTimeout) == that.sslHandshakeTimeoutUnit.toNanos(that.sslHandshakeTimeout) && Objects.equals(this.keyCertOptions, that.keyCertOptions) && Objects.equals(this.trustOptions, that.trustOptions) && Objects.equals(this.enabledCipherSuites, that.enabledCipherSuites) && Objects.equals(this.crlPaths, that.crlPaths) && Objects.equals(this.crlValues, that.crlValues) && this.useAlpn == that.useAlpn && Objects.equals(this.enabledSecureTransportProtocols, that.enabledSecureTransportProtocols);
      }
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      SSLOptionsConverter.toJson(this, json);
      return json;
   }

   static {
      DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT = TimeUnit.SECONDS;
      DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS = Collections.unmodifiableList(Arrays.asList("TLSv1.2", "TLSv1.3"));
   }
}
