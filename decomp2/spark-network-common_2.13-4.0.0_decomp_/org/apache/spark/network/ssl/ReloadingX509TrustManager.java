package org.apache.spark.network.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.sparkproject.guava.annotations.VisibleForTesting;

public final class ReloadingX509TrustManager implements X509TrustManager, Runnable {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ReloadingX509TrustManager.class);
   private final String type;
   private final File file;
   private String canonicalPath;
   private final String password;
   private long lastLoaded;
   private final long reloadInterval;
   @VisibleForTesting
   protected volatile int reloadCount;
   @VisibleForTesting
   protected volatile int needsReloadCheckCounts;
   private final AtomicReference trustManagerRef;
   private Thread reloader;
   private static final X509Certificate[] EMPTY = new X509Certificate[0];

   public ReloadingX509TrustManager(String type, File trustStore, String password, long reloadInterval) throws IOException, GeneralSecurityException {
      this.type = type;
      this.file = trustStore;
      this.canonicalPath = this.file.getCanonicalPath();
      this.password = password;
      this.trustManagerRef = new AtomicReference();
      this.trustManagerRef.set(this.loadTrustManager());
      this.reloadInterval = reloadInterval;
      this.reloadCount = 0;
      this.needsReloadCheckCounts = 0;
   }

   public void init() {
      this.reloader = new Thread(this, "Truststore reloader thread");
      this.reloader.setDaemon(true);
      this.reloader.start();
   }

   public void destroy() throws InterruptedException {
      this.reloader.interrupt();
      this.reloader.join();
   }

   public long getReloadInterval() {
      return this.reloadInterval;
   }

   public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      X509TrustManager tm = (X509TrustManager)this.trustManagerRef.get();
      if (tm != null) {
         tm.checkClientTrusted(chain, authType);
      } else {
         throw new CertificateException("Unknown client chain certificate: " + chain[0].toString() + ". Please ensure the correct trust store is specified in the config");
      }
   }

   public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      X509TrustManager tm = (X509TrustManager)this.trustManagerRef.get();
      if (tm != null) {
         tm.checkServerTrusted(chain, authType);
      } else {
         throw new CertificateException("Unknown server chain certificate: " + chain[0].toString() + ". Please ensure the correct trust store is specified in the config");
      }
   }

   public X509Certificate[] getAcceptedIssuers() {
      X509Certificate[] issuers = EMPTY;
      X509TrustManager tm = (X509TrustManager)this.trustManagerRef.get();
      if (tm != null) {
         issuers = tm.getAcceptedIssuers();
      }

      return issuers;
   }

   boolean needsReload() throws IOException {
      boolean reload = true;
      File latestCanonicalFile = this.file.getCanonicalFile();
      if (this.file.exists() && latestCanonicalFile.exists()) {
         if (latestCanonicalFile.getPath().equals(this.canonicalPath) && latestCanonicalFile.lastModified() == this.lastLoaded) {
            reload = false;
         }
      } else {
         this.lastLoaded = 0L;
      }

      return reload;
   }

   X509TrustManager loadTrustManager() throws IOException, GeneralSecurityException {
      X509TrustManager trustManager = null;
      KeyStore ks = KeyStore.getInstance(this.type);
      File latestCanonicalFile = this.file.getCanonicalFile();
      this.canonicalPath = latestCanonicalFile.getPath();
      this.lastLoaded = latestCanonicalFile.lastModified();
      FileInputStream in = new FileInputStream(latestCanonicalFile);

      try {
         char[] passwordCharacters = this.password != null ? this.password.toCharArray() : null;
         ks.load(in, passwordCharacters);
         logger.debug("Loaded truststore '" + String.valueOf(this.file) + "'");
      } catch (Throwable var12) {
         try {
            in.close();
         } catch (Throwable var11) {
            var12.addSuppressed(var11);
         }

         throw var12;
      }

      in.close();
      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      trustManagerFactory.init(ks);
      TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

      for(TrustManager trustManager1 : trustManagers) {
         if (trustManager1 instanceof X509TrustManager x509TrustManager) {
            trustManager = x509TrustManager;
            break;
         }
      }

      return trustManager;
   }

   public void run() {
      for(boolean running = true; running; ++this.needsReloadCheckCounts) {
         try {
            Thread.sleep(this.reloadInterval);
         } catch (InterruptedException var5) {
            running = false;
         }

         try {
            if (running && this.needsReload()) {
               try {
                  this.trustManagerRef.set(this.loadTrustManager());
                  ++this.reloadCount;
               } catch (Exception ex) {
                  logger.warn("Could not load truststore (keep using existing one) : ", ex);
               }
            }
         } catch (IOException ex) {
            logger.warn("Could not check whether truststore needs reloading: ", ex);
         }
      }

   }
}
