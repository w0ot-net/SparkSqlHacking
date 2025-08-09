package io.fabric8.kubernetes.client.informers.impl.cache;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ListOptionsBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.fabric8.kubernetes.client.dsl.internal.AbstractWatchManager;
import io.fabric8.kubernetes.client.informers.ExceptionHandler;
import io.fabric8.kubernetes.client.informers.impl.ListerWatcher;
import io.fabric8.kubernetes.client.utils.ExponentialBackoffIntervalCalculator;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.LongSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reflector {
   private static final Logger log = LoggerFactory.getLogger(Reflector.class);
   private static long MIN_TIMEOUT;
   private volatile String lastSyncResourceVersion;
   private final ListerWatcher listerWatcher;
   private final ProcessorStore store;
   private final ReflectorWatcher watcher;
   private volatile boolean watching;
   private volatile CompletableFuture watchFuture;
   private volatile CompletableFuture reconnectFuture;
   private final CompletableFuture startFuture;
   private final CompletableFuture stopFuture;
   private final ExponentialBackoffIntervalCalculator retryIntervalCalculator;
   private final Executor executor;
   private volatile ExceptionHandler handler;
   private long minTimeout;
   private CompletableFuture timeoutFuture;
   private boolean cachedListing;

   public Reflector(ListerWatcher listerWatcher, ProcessorStore store) {
      this(listerWatcher, store, Runnable::run);
   }

   public Reflector(ListerWatcher listerWatcher, ProcessorStore store, Executor executor) {
      this.startFuture = new CompletableFuture();
      this.stopFuture = new CompletableFuture();
      this.handler = (b, t) -> b && !(t instanceof WatcherException);
      this.minTimeout = MIN_TIMEOUT;
      this.cachedListing = true;
      this.listerWatcher = listerWatcher;
      this.store = store;
      this.watcher = new ReflectorWatcher();
      this.retryIntervalCalculator = new ExponentialBackoffIntervalCalculator(listerWatcher.getWatchReconnectInterval(), -1);
      this.executor = executor;
   }

   public CompletableFuture start() {
      this.listSyncAndWatch();
      return this.startFuture;
   }

   public CompletableFuture getStartFuture() {
      return this.startFuture;
   }

   public void stop() {
      this.startFuture.completeExceptionally(new KubernetesClientException("informer manually stopped before starting"));
      Future<?> future = this.reconnectFuture;
      if (future != null) {
         future.cancel(true);
      }

      this.stopWatcher();
      this.stopFuture.complete((Object)null);
   }

   private synchronized void stopWatcher() {
      Optional.ofNullable(this.watchFuture).ifPresent((theFuture) -> {
         this.watchFuture = null;
         theFuture.whenComplete((w, t) -> {
            if (w != null) {
               this.stopWatch(w);
            }

         });
      });
      if (this.timeoutFuture != null) {
         this.timeoutFuture.cancel(true);
      }

   }

   public CompletableFuture listSyncAndWatch() {
      if (this.isStopped()) {
         return CompletableFuture.completedFuture((Object)null);
      } else {
         Set<String> nextKeys = new ConcurrentSkipListSet();
         CompletableFuture<Void> theFuture = this.processList(nextKeys, (String)null).thenCompose((result) -> {
            String latestResourceVersion = result.getMetadata().getResourceVersion();
            log.debug("Listing items ({}) for {} at v{}", new Object[]{nextKeys.size(), this, latestResourceVersion});
            CompletableFuture<?> cf = new CompletableFuture();
            this.store.retainAll(nextKeys, (executor) -> {
               boolean startWatchImmediately = this.cachedListing && this.lastSyncResourceVersion == null;
               this.lastSyncResourceVersion = latestResourceVersion;
               if (startWatchImmediately) {
                  cf.complete((Object)null);
               } else {
                  executor.execute(() -> cf.complete((Object)null));
               }

            });
            return cf.thenCompose((ignored) -> this.startWatcher(latestResourceVersion));
         }).thenAccept((w) -> {
            if (w != null) {
               if (!this.isStopped()) {
                  if (log.isDebugEnabled()) {
                     log.debug("Watch started for {}", this);
                  }

                  this.watching = true;
               } else {
                  this.stopWatch(w);
               }
            }

         });
         theFuture.whenComplete((v, t) -> {
            if (t != null) {
               this.onException("listSyncAndWatch", t);
            } else {
               this.startFuture.complete((Object)null);
               this.retryIntervalCalculator.resetReconnectAttempts();
            }

         });
         return theFuture;
      }
   }

   private void onException(String operation, Throwable t) {
      if (this.handler.retryAfterException(this.startFuture.isDone() && !this.startFuture.isCompletedExceptionally(), t)) {
         log.warn("{} failed for {}, will retry", new Object[]{operation, this, t});
         this.reconnect();
      } else {
         log.error("{} failed for {}, will stop", new Object[]{operation, this, t});
         this.startFuture.completeExceptionally(t);
         this.stopFuture.completeExceptionally(t);
      }

   }

   protected void reconnect() {
      if (!this.isStopped()) {
         this.reconnectFuture = Utils.schedule(this.executor, this::listSyncAndWatch, this.retryIntervalCalculator.nextReconnectInterval(), TimeUnit.MILLISECONDS);
      }
   }

   private CompletableFuture processList(Set nextKeys, String continueVal) {
      CompletableFuture<L> futureResult = this.listerWatcher.submitList(((ListOptionsBuilder)((ListOptionsBuilder)((ListOptionsBuilder)(new ListOptionsBuilder()).withResourceVersion(this.isCachedListing(continueVal) ? "0" : null)).withLimit(this.listerWatcher.getLimit())).withContinue(continueVal)).build());
      return futureResult.thenCompose((result) -> {
         result.getItems().forEach((i) -> {
            String key = this.store.getKey(i);
            nextKeys.add(key);
         });
         this.store.update(result.getItems());
         String nextContinueVal = result.getMetadata().getContinue();
         return Utils.isNotNullOrEmpty(nextContinueVal) ? this.processList(nextKeys, nextContinueVal) : CompletableFuture.completedFuture(result);
      });
   }

   private boolean isCachedListing(String continueVal) {
      return this.cachedListing && this.listerWatcher.getLimit() == null && this.lastSyncResourceVersion == null && continueVal == null;
   }

   private void stopWatch(Watch w) {
      log.debug("Stopping watcher for {} at v{}", this, this.lastSyncResourceVersion);
      w.close();
      this.watchStopped();
   }

   private synchronized CompletableFuture startWatcher(String latestResourceVersion) {
      if (this.isStopped()) {
         return CompletableFuture.completedFuture((Object)null);
      } else {
         log.debug("Starting watcher for {} at v{}", this, latestResourceVersion);
         CompletableFuture<AbstractWatchManager<T>> future = this.listerWatcher.submitWatch(((ListOptionsBuilder)((ListOptionsBuilder)(new ListOptionsBuilder()).withResourceVersion(latestResourceVersion)).withTimeoutSeconds(this.minTimeout * 2L)).build(), this.watcher);
         LongSupplier timeout = () -> (long)((Math.random() + (double)1.0F) * (double)this.minTimeout);
         if (this.timeoutFuture != null) {
            this.timeoutFuture.cancel(true);
         }

         this.timeoutFuture = new CompletableFuture();
         Utils.scheduleWithVariableRate(this.timeoutFuture, this.executor, () -> future.thenAccept(AbstractWatchManager::closeRequest), timeout.getAsLong(), timeout, TimeUnit.SECONDS);
         this.watchFuture = future;
         return this.watchFuture;
      }
   }

   public void setMinTimeout(long minTimeout) {
      this.minTimeout = minTimeout;
   }

   private synchronized void watchStopped() {
      this.watching = false;
   }

   public String getLastSyncResourceVersion() {
      return this.lastSyncResourceVersion;
   }

   public boolean isStopped() {
      return this.stopFuture.isDone();
   }

   public boolean isWatching() {
      return this.watching;
   }

   ReflectorWatcher getWatcher() {
      return this.watcher;
   }

   public String toString() {
      return this.listerWatcher.getApiEndpointPath();
   }

   public CompletableFuture getStopFuture() {
      return this.stopFuture;
   }

   public void setExceptionHandler(ExceptionHandler handler) {
      this.handler = handler;
   }

   public void usingInitialState() {
      this.cachedListing = false;
   }

   static {
      MIN_TIMEOUT = TimeUnit.MINUTES.toSeconds(5L);
   }

   class ReflectorWatcher implements Watcher {
      public void eventReceived(Watcher.Action action, HasMetadata resource) {
         if (action == null) {
            throw new KubernetesClientException("Unrecognized event for " + Reflector.this);
         } else if (resource == null) {
            throw new KubernetesClientException("Unrecognized resource for " + Reflector.this);
         } else {
            if (Reflector.log.isDebugEnabled()) {
               Reflector.log.debug("Event received {} {} resourceVersion v{} for {}", new Object[]{action.name(), resource.getKind(), resource.getMetadata().getResourceVersion(), Reflector.this});
            }

            switch (action) {
               case ERROR:
                  throw new KubernetesClientException("ERROR event");
               case ADDED:
                  Reflector.this.store.add(resource);
                  break;
               case MODIFIED:
                  Reflector.this.store.update(resource);
                  break;
               case DELETED:
                  Reflector.this.store.delete(resource);
            }

            Reflector.this.lastSyncResourceVersion = resource.getMetadata().getResourceVersion();
         }
      }

      public void onClose(WatcherException exception) {
         Reflector.this.watchStopped();
         if (exception.isHttpGone()) {
            if (Reflector.log.isDebugEnabled()) {
               Reflector.log.debug("Watch restarting due to http gone for {}", Reflector.this);
            }

            Reflector.this.reconnect();
         } else {
            Reflector.this.onException("watch", exception);
         }

      }

      public void onClose() {
         Reflector.this.watchStopped();
         Reflector.log.debug("Watch gracefully closed for {}", Reflector.this);
      }

      public boolean reconnecting() {
         return true;
      }
   }
}
