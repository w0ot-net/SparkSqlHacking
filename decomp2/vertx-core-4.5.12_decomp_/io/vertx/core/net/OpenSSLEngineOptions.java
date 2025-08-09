package io.vertx.core.net;

import io.netty.handler.ssl.OpenSsl;
import io.netty.handler.ssl.SslProvider;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.tls.DefaultSslContextFactory;
import io.vertx.core.spi.tls.SslContextFactory;

@DataObject
@JsonGen(
   publicConverter = false
)
public class OpenSSLEngineOptions extends SSLEngineOptions {
   public static final boolean DEFAULT_SESSION_CACHE_ENABLED = true;
   private boolean sessionCacheEnabled;

   public static boolean isAvailable() {
      return OpenSsl.isAvailable();
   }

   public static boolean isAlpnAvailable() {
      return OpenSsl.isAlpnSupported();
   }

   public OpenSSLEngineOptions() {
      this.sessionCacheEnabled = true;
   }

   public OpenSSLEngineOptions(JsonObject json) {
      super(json);
      OpenSSLEngineOptionsConverter.fromJson(json, this);
   }

   public OpenSSLEngineOptions(OpenSSLEngineOptions other) {
      super((SSLEngineOptions)other);
      this.sessionCacheEnabled = other.isSessionCacheEnabled();
   }

   public OpenSSLEngineOptions setSessionCacheEnabled(boolean sessionCacheEnabled) {
      this.sessionCacheEnabled = sessionCacheEnabled;
      return this;
   }

   public boolean isSessionCacheEnabled() {
      return this.sessionCacheEnabled;
   }

   public OpenSSLEngineOptions setUseWorkerThread(boolean useWorkerThread) {
      return (OpenSSLEngineOptions)super.setUseWorkerThread(useWorkerThread);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      OpenSSLEngineOptionsConverter.toJson(this, json);
      return json;
   }

   public OpenSSLEngineOptions copy() {
      return new OpenSSLEngineOptions(this);
   }

   public SslContextFactory sslContextFactory() {
      return new DefaultSslContextFactory(SslProvider.OPENSSL, this.sessionCacheEnabled);
   }
}
