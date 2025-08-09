package io.fabric8.kubernetes.client.informers.impl;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.informers.ExceptionHandler;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.cache.Indexer;
import io.fabric8.kubernetes.client.informers.cache.ItemStore;
import io.fabric8.kubernetes.client.informers.cache.Store;
import io.fabric8.kubernetes.client.informers.impl.cache.CacheImpl;
import io.fabric8.kubernetes.client.informers.impl.cache.ProcessorListener;
import io.fabric8.kubernetes.client.informers.impl.cache.ProcessorStore;
import io.fabric8.kubernetes.client.informers.impl.cache.Reflector;
import io.fabric8.kubernetes.client.informers.impl.cache.SharedProcessor;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSharedIndexInformer implements SharedIndexInformer {
   private static final Logger log = LoggerFactory.getLogger(DefaultSharedIndexInformer.class);
   private static final long MINIMUM_RESYNC_PERIOD_MILLIS = 1000L;
   private long resyncCheckPeriodMillis;
   private final long defaultEventHandlerResyncPeriod;
   private final Reflector reflector;
   private final Class apiTypeClass;
   private final ProcessorStore processorStore;
   private final CacheImpl indexer = new CacheImpl();
   private final SharedProcessor processor;
   private final Executor informerExecutor;
   private final String description;
   private final AtomicBoolean started = new AtomicBoolean();
   private volatile boolean stopped = false;
   private Future resyncFuture;
   private Stream initialState;

   public DefaultSharedIndexInformer(Class apiTypeClass, ListerWatcher listerWatcher, long resyncPeriod, Executor informerExecutor) {
      if (resyncPeriod < 0L) {
         throw new IllegalArgumentException("Invalid resync period provided, It should be a non-negative value");
      } else {
         this.resyncCheckPeriodMillis = resyncPeriod;
         this.defaultEventHandlerResyncPeriod = resyncPeriod;
         this.apiTypeClass = apiTypeClass;
         this.description = listerWatcher.getApiEndpointPath();
         this.informerExecutor = informerExecutor;
         this.processor = new SharedProcessor(informerExecutor, this.description);
         this.processorStore = new ProcessorStore(this.indexer, this.processor);
         this.reflector = new Reflector(listerWatcher, this.processorStore, informerExecutor);
      }
   }

   public DefaultSharedIndexInformer addEventHandler(ResourceEventHandler handler) {
      this.addEventHandlerWithResyncPeriod(handler, this.defaultEventHandlerResyncPeriod);
      return this;
   }

   public SharedIndexInformer removeEventHandler(ResourceEventHandler handler) {
      Optional<ProcessorListener<T>> listener = this.processor.removeProcessorListener(handler);
      if (!this.started.get() && listener.isPresent()) {
         long listenerResyncPeriod = ((ProcessorListener)listener.orElseThrow()).getResyncPeriodInMillis();
         if (listenerResyncPeriod != 0L && this.resyncCheckPeriodMillis == listenerResyncPeriod) {
            this.processor.getMinimalNonZeroResyncPeriod().ifPresent((l) -> this.resyncCheckPeriodMillis = l);
         }
      }

      return this;
   }

   public SharedIndexInformer addEventHandlerWithResyncPeriod(ResourceEventHandler handler, long resyncPeriodMillis) {
      if (this.stopped) {
         log.info("DefaultSharedIndexInformer#Handler was not added to {} because it has stopped already", this);
         return this;
      } else {
         if (resyncPeriodMillis > 0L) {
            if (resyncPeriodMillis < 1000L) {
               log.warn("DefaultSharedIndexInformer#resyncPeriod {} is too small for {}. Changing it to minimal allowed value of {}", new Object[]{resyncPeriodMillis, this, 1000L});
               resyncPeriodMillis = 1000L;
            }

            if (resyncPeriodMillis < this.resyncCheckPeriodMillis) {
               if (this.started.get()) {
                  log.warn("DefaultSharedIndexInformer#resyncPeriod {} is smaller than resyncCheckPeriod {} and the {} informer has already started. Changing it to {}", new Object[]{resyncPeriodMillis, this.resyncCheckPeriodMillis, this, this.resyncCheckPeriodMillis});
                  resyncPeriodMillis = this.resyncCheckPeriodMillis;
               } else {
                  this.resyncCheckPeriodMillis = resyncPeriodMillis;
               }
            }
         }

         SharedProcessor var10000 = this.processor;
         long var10002 = this.determineResyncPeriod(resyncPeriodMillis, this.resyncCheckPeriodMillis);
         CacheImpl var10003 = this.indexer;
         Objects.requireNonNull(var10003);
         var10000.addProcessorListener(handler, var10002, var10003::list);
         return this;
      }
   }

   public String lastSyncResourceVersion() {
      return this.reflector.getLastSyncResourceVersion();
   }

   public CompletableFuture start() {
      if (this.stopped) {
         throw new IllegalStateException("Cannot restart a stopped informer");
      } else {
         synchronized(this) {
            if (!this.started.compareAndSet(false, true)) {
               return this.reflector.getStartFuture();
            }

            if (this.initialState != null) {
               Stream var10000 = this.initialState;
               CacheImpl var10001 = this.indexer;
               Objects.requireNonNull(var10001);
               var10000.forEach(var10001::put);
               this.reflector.usingInitialState();
            }
         }

         log.debug("Ready to run resync and reflector for {} with resync {}", this, this.resyncCheckPeriodMillis);
         SharedProcessor var4 = this.processor;
         Objects.requireNonNull(var4);
         this.scheduleResync(var4::shouldResync);
         return this.reflector.start();
      }
   }

   public CompletableFuture started() {
      return this.reflector.getStartFuture();
   }

   public SharedIndexInformer run() {
      Utils.waitUntilReadyOrFail(this.start(), -1L, TimeUnit.MILLISECONDS);
      return this;
   }

   public synchronized void stop() {
      this.stopped = true;
      this.reflector.stop();
      this.stopResync();
      this.processor.stop();
   }

   private synchronized void stopResync() {
      if (this.resyncFuture != null) {
         this.resyncFuture.cancel(true);
         this.resyncFuture = null;
      }

   }

   public SharedIndexInformer addIndexers(Map indexers) {
      this.indexer.addIndexers(indexers);
      return this;
   }

   public Indexer getIndexer() {
      return this.indexer;
   }

   public Store getStore() {
      return this.indexer;
   }

   private long determineResyncPeriod(long desired, long check) {
      if (desired == 0L) {
         return desired;
      } else {
         return check == 0L ? 0L : Math.max(desired, check);
      }
   }

   public boolean isRunning() {
      return !this.stopped && this.started.get() && !this.reflector.isStopped();
   }

   public boolean isWatching() {
      return this.reflector.isWatching();
   }

   synchronized void scheduleResync(BooleanSupplier resyncFunc) {
      if (this.resyncCheckPeriodMillis > 0L) {
         this.resyncFuture = Utils.scheduleAtFixedRate(this.informerExecutor, () -> {
            if (log.isDebugEnabled()) {
               log.debug("Checking for resync at interval for {}", this);
            }

            if (resyncFunc.getAsBoolean()) {
               log.debug("Resync running for {}", this);
               this.processorStore.resync();
            }

         }, this.resyncCheckPeriodMillis, this.resyncCheckPeriodMillis, TimeUnit.MILLISECONDS);
      } else {
         log.debug("Resync skipped due to 0 full resync period for {}", this);
      }

   }

   public long getFullResyncPeriod() {
      return this.resyncCheckPeriodMillis;
   }

   Future getResyncFuture() {
      return this.resyncFuture;
   }

   public Class getApiTypeClass() {
      return this.apiTypeClass;
   }

   public SharedIndexInformer removeIndexer(String name) {
      this.indexer.removeIndexer(name);
      return this;
   }

   public synchronized SharedIndexInformer initialState(Stream items) {
      if (this.started.get()) {
         throw new KubernetesClientException("Informer cannot be running when initial state is added");
      } else {
         this.initialState = items;
         return this;
      }
   }

   public synchronized SharedIndexInformer itemStore(ItemStore itemStore) {
      if (this.started.get()) {
         throw new KubernetesClientException("Informer cannot be running when setting item store");
      } else {
         this.indexer.setItemStore(itemStore);
         return this;
      }
   }

   public String toString() {
      return this.description;
   }

   public CompletableFuture stopped() {
      return this.reflector.getStopFuture();
   }

   public synchronized DefaultSharedIndexInformer exceptionHandler(ExceptionHandler handler) {
      if (this.started.get()) {
         throw new KubernetesClientException("Informer cannot be running when handler is set");
      } else {
         this.reflector.setExceptionHandler(handler);
         return this;
      }
   }
}
