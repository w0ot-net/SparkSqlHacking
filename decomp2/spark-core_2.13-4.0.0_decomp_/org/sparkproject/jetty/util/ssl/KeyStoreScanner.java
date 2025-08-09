package org.sparkproject.jetty.util.ssl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Scanner;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.thread.Scheduler;

public class KeyStoreScanner extends ContainerLifeCycle implements Scanner.DiscreteListener {
   private static final Logger LOG = LoggerFactory.getLogger(KeyStoreScanner.class);
   private final SslContextFactory sslContextFactory;
   private final File keystoreFile;
   private final Scanner _scanner;

   public KeyStoreScanner(SslContextFactory sslContextFactory) {
      this.sslContextFactory = sslContextFactory;

      try {
         Resource keystoreResource = sslContextFactory.getKeyStoreResource();
         File monitoredFile = keystoreResource.getFile();
         if (monitoredFile == null || !monitoredFile.exists()) {
            throw new IllegalArgumentException("keystore file does not exist");
         }

         if (monitoredFile.isDirectory()) {
            throw new IllegalArgumentException("expected keystore file not directory");
         }

         this.keystoreFile = monitoredFile;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Monitored Keystore File: {}", monitoredFile);
         }
      } catch (IOException e) {
         throw new IllegalArgumentException("could not obtain keystore file", e);
      }

      File parentFile = this.keystoreFile.getParentFile();
      if (parentFile.exists() && parentFile.isDirectory()) {
         this._scanner = new Scanner((Scheduler)null, false);
         this._scanner.addDirectory(parentFile.toPath());
         this._scanner.setScanInterval(1);
         this._scanner.setReportDirs(false);
         this._scanner.setReportExistingFilesOnStartup(false);
         this._scanner.setScanDepth(1);
         this._scanner.addListener(this);
         this.addBean(this._scanner);
      } else {
         throw new IllegalArgumentException("error obtaining keystore dir");
      }
   }

   private Path getRealKeyStorePath() {
      try {
         return this.keystoreFile.toPath().toRealPath();
      } catch (IOException var2) {
         return this.keystoreFile.toPath();
      }
   }

   public void fileAdded(String filename) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("fileAdded {} - keystoreFile.toReal {}", filename, this.getRealKeyStorePath());
      }

      if (this.keystoreFile.toPath().toString().equals(filename)) {
         this.reload();
      }

   }

   public void fileChanged(String filename) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("fileChanged {} - keystoreFile.toReal {}", filename, this.getRealKeyStorePath());
      }

      if (this.keystoreFile.toPath().toString().equals(filename)) {
         this.reload();
      }

   }

   public void fileRemoved(String filename) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("fileRemoved {} - keystoreFile.toReal {}", filename, this.getRealKeyStorePath());
      }

      if (this.keystoreFile.toPath().toString().equals(filename)) {
         this.reload();
      }

   }

   @ManagedOperation(
      value = "Scan for changes in the SSL Keystore",
      impact = "ACTION"
   )
   public boolean scan(long waitMillis) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("scanning");
      }

      CompletableFuture<Boolean> cf = new CompletableFuture();

      try {
         Scanner var10000 = this._scanner;
         Runnable var10001 = () -> {
            Scanner var10000 = this._scanner;
            Runnable var10001 = () -> cf.complete(true);
            Objects.requireNonNull(cf);
            var10000.scan(Callback.from(var10001, cf::completeExceptionally));
         };
         Objects.requireNonNull(cf);
         var10000.scan(Callback.from(var10001, cf::completeExceptionally));
         return (Boolean)cf.get(waitMillis, TimeUnit.MILLISECONDS);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @ManagedOperation(
      value = "Reload the SSL Keystore",
      impact = "ACTION"
   )
   public void reload() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("reloading keystore file {}", this.keystoreFile);
      }

      try {
         this.sslContextFactory.reload((scf) -> {
         });
      } catch (Throwable t) {
         LOG.warn("Keystore Reload Failed", t);
      }

   }

   @ManagedAttribute("scanning interval to detect changes which need reloaded")
   public int getScanInterval() {
      return this._scanner.getScanInterval();
   }

   public void setScanInterval(int scanInterval) {
      this._scanner.setScanInterval(scanInterval);
   }
}
