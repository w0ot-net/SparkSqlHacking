package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.tls.SslContextFactory;

public abstract class SSLEngineOptions {
   public static final boolean DEFAULT_USE_WORKER_POOL = false;
   private boolean useWorkerThread;

   public abstract SSLEngineOptions copy();

   public SSLEngineOptions() {
      this.useWorkerThread = false;
   }

   public SSLEngineOptions(SSLEngineOptions that) {
      this.useWorkerThread = that.useWorkerThread;
   }

   public SSLEngineOptions(JsonObject json) {
      this.useWorkerThread = json.getBoolean("useWorkerThread", false);
   }

   public abstract SslContextFactory sslContextFactory();

   public boolean getUseWorkerThread() {
      return this.useWorkerThread;
   }

   public SSLEngineOptions setUseWorkerThread(boolean useWorkerThread) {
      this.useWorkerThread = useWorkerThread;
      return this;
   }
}
