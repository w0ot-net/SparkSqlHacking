package io.fabric8.kubernetes.client.informers.impl.cache;

import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.utils.internal.SerialExecutor;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedProcessor {
   private static final Logger log = LoggerFactory.getLogger(SharedProcessor.class);
   private final ReadWriteLock lock;
   private final List listeners;
   private final List syncingListeners;
   private final SerialExecutor executor;
   private final String informerDescription;

   public SharedProcessor() {
      this(Runnable::run, "informer");
   }

   public SharedProcessor(Executor executor, String informerDescription) {
      this.lock = new ReentrantReadWriteLock();
      this.listeners = new ArrayList();
      this.syncingListeners = new ArrayList();
      this.executor = new SerialExecutor(executor);
      this.informerDescription = informerDescription;
   }

   public void addListener(ProcessorListener processorListener) {
      this.lock.writeLock().lock();

      try {
         this.listeners.add(processorListener);
         if (processorListener.isReSync()) {
            this.syncingListeners.add(processorListener);
         }
      } finally {
         this.lock.writeLock().unlock();
      }

   }

   public void distribute(ProcessorListener.Notification obj, boolean isSync) {
      this.distribute((Consumer)((l) -> l.add(obj)), isSync);
   }

   public void distribute(Consumer operation, boolean isSync) {
      this.lock.readLock().lock();

      List<ProcessorListener<T>> toCall;
      try {
         if (isSync) {
            toCall = new ArrayList(this.syncingListeners);
         } else {
            toCall = new ArrayList(this.listeners);
         }
      } finally {
         this.lock.readLock().unlock();
      }

      try {
         this.executor.execute(() -> {
            for(ProcessorListener listener : toCall) {
               try {
                  operation.accept(listener);
               } catch (Exception ex) {
                  log.error("{} failed invoking {} event handler: {}", new Object[]{this.informerDescription, listener.getHandler(), ex.getMessage(), ex});
               }
            }

         });
      } catch (RejectedExecutionException var7) {
      }

   }

   public boolean shouldResync() {
      this.lock.writeLock().lock();
      boolean resyncNeeded = false;

      try {
         this.syncingListeners.clear();
         ZonedDateTime now = ZonedDateTime.now();

         for(ProcessorListener listener : this.listeners) {
            if (listener.shouldResync(now)) {
               resyncNeeded = true;
               this.syncingListeners.add(listener);
               listener.determineNextResync(now);
            }
         }
      } finally {
         this.lock.writeLock().unlock();
      }

      return resyncNeeded;
   }

   public void stop() {
      this.executor.shutdownNow();
      this.lock.writeLock().lock();

      try {
         this.syncingListeners.clear();
         this.listeners.clear();
      } finally {
         this.lock.writeLock().unlock();
      }

   }

   public ProcessorListener addProcessorListener(ResourceEventHandler handler, long resyncPeriodMillis, Supplier initialItems) {
      this.lock.writeLock().lock();

      ProcessorListener var11;
      try {
         ProcessorListener<T> listener = new ProcessorListener(handler, resyncPeriodMillis);

         for(Object item : (Collection)initialItems.get()) {
            listener.add(new ProcessorListener.AddNotification(item));
         }

         this.addListener(listener);
         var11 = listener;
      } finally {
         this.lock.writeLock().unlock();
      }

      return var11;
   }

   public Optional removeProcessorListener(ResourceEventHandler handler) {
      this.lock.writeLock().lock();

      Optional var3;
      try {
         Optional<ProcessorListener<T>> targetListener = this.listeners.stream().filter((l) -> l.getHandler() == handler).findFirst();
         targetListener.ifPresent((l) -> {
            this.listeners.remove(l);
            if (l.isReSync()) {
               this.syncingListeners.remove(l);
            }

         });
         var3 = targetListener;
      } finally {
         this.lock.writeLock().unlock();
      }

      return var3;
   }

   public Optional getMinimalNonZeroResyncPeriod() {
      this.lock.readLock().lock();

      Optional var1;
      try {
         var1 = this.listeners.stream().map(ProcessorListener::getResyncPeriodInMillis).filter((p) -> p > 0L).min(Long::compareTo);
      } finally {
         this.lock.readLock().unlock();
      }

      return var1;
   }

   public void executeIfPossible(Runnable runnable) {
      try {
         this.executor.execute(runnable);
      } catch (RejectedExecutionException var3) {
      }

   }
}
