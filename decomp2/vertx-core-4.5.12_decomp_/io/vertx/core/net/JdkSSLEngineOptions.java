package io.vertx.core.net;

import io.netty.handler.ssl.SslProvider;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.tls.DefaultSslContextFactory;
import io.vertx.core.spi.tls.SslContextFactory;
import javax.net.ssl.SSLEngine;

@DataObject
public class JdkSSLEngineOptions extends SSLEngineOptions {
   private static Boolean jdkAlpnAvailable;
   private boolean pooledHeapBuffers = false;

   public static synchronized boolean isAlpnAvailable() {
      if (jdkAlpnAvailable == null) {
         boolean available = false;

         try {
            SSLEngine.class.getDeclaredMethod("getApplicationProtocol");
            available = true;
         } catch (Exception var8) {
            try {
               JdkSSLEngineOptions.class.getClassLoader().loadClass("sun.security.ssl.ALPNExtension");
               available = true;
            } catch (Exception var7) {
            }
         } finally {
            jdkAlpnAvailable = available;
         }
      }

      return jdkAlpnAvailable;
   }

   public JdkSSLEngineOptions() {
   }

   public JdkSSLEngineOptions(JsonObject json) {
      super(json);
      this.pooledHeapBuffers = json.getBoolean("pooledHeapBuffers", false);
   }

   public JdkSSLEngineOptions(JdkSSLEngineOptions that) {
      super((SSLEngineOptions)that);
      this.pooledHeapBuffers = that.pooledHeapBuffers;
   }

   public JdkSSLEngineOptions setPooledHeapBuffers(boolean pooledHeapBuffers) {
      this.pooledHeapBuffers = pooledHeapBuffers;
      return this;
   }

   public boolean isPooledHeapBuffers() {
      return this.pooledHeapBuffers;
   }

   public JdkSSLEngineOptions setUseWorkerThread(boolean useWorkerThread) {
      return (JdkSSLEngineOptions)super.setUseWorkerThread(useWorkerThread);
   }

   public JsonObject toJson() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.put("pooledHeapBuffers", this.pooledHeapBuffers);
      return jsonObject;
   }

   public JdkSSLEngineOptions copy() {
      return new JdkSSLEngineOptions(this);
   }

   public SslContextFactory sslContextFactory() {
      return new DefaultSslContextFactory(SslProvider.JDK, false);
   }
}
