package io.fabric8.kubernetes.client.informers.impl.cache;

import io.fabric8.kubernetes.api.model.HasMetadata;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ProcessorStore {
   private CacheImpl cache;
   private SharedProcessor processor;
   private AtomicBoolean synced = new AtomicBoolean();
   private List deferredAdd = new ArrayList();

   public ProcessorStore(CacheImpl cache, SharedProcessor processor) {
      this.cache = cache;
      this.processor = processor;
   }

   public void add(HasMetadata obj) {
      this.update(obj);
   }

   public void update(List items) {
      items.stream().map(this::updateInternal).filter(Objects::nonNull).forEach((n) -> this.processor.distribute(n, false));
   }

   private ProcessorListener.Notification updateInternal(HasMetadata obj) {
      T oldObj = (T)this.cache.put(obj);
      ProcessorListener.Notification<T> notification = null;
      if (oldObj != null) {
         if (!Objects.equals(oldObj.getMetadata().getResourceVersion(), obj.getMetadata().getResourceVersion())) {
            notification = new ProcessorListener.UpdateNotification(oldObj, obj);
         }
      } else if (!this.synced.get() && this.cache.isFullState()) {
         this.deferredAdd.add(this.getKey(obj));
      } else {
         notification = new ProcessorListener.AddNotification(obj);
      }

      return notification;
   }

   public void update(HasMetadata obj) {
      ProcessorListener.Notification<T> notification = this.updateInternal(obj);
      if (notification != null) {
         this.processor.distribute(notification, false);
      }

   }

   public void delete(HasMetadata obj) {
      Object oldObj = this.cache.remove(obj);
      if (oldObj != null) {
         this.processor.distribute((ProcessorListener.Notification)(new ProcessorListener.DeleteNotification(obj, false)), false);
      }

   }

   public List list() {
      return this.cache.list();
   }

   public List listKeys() {
      return this.cache.listKeys();
   }

   public HasMetadata get(HasMetadata object) {
      return this.cache.get(object);
   }

   public HasMetadata getByKey(String key) {
      return this.cache.getByKey(key);
   }

   public void retainAll(Set nextKeys, Consumer cacheStateComplete) {
      if (this.synced.compareAndSet(false, true)) {
         Stream var10000 = this.deferredAdd.stream();
         CacheImpl var10001 = this.cache;
         Objects.requireNonNull(var10001);
         var10000.map(var10001::getByKey).filter(Objects::nonNull).forEach((v) -> this.processor.distribute((ProcessorListener.Notification)(new ProcessorListener.AddNotification(v)), false));
         this.deferredAdd.clear();
      }

      List<T> current = this.cache.list();
      if (nextKeys.isEmpty() && current.isEmpty()) {
         this.processor.distribute((Consumer)((l) -> l.getHandler().onNothing()), false);
      }

      current.forEach((v) -> {
         String key = this.cache.getKey(v);
         if (!nextKeys.contains(key)) {
            this.cache.remove(v);
            this.processor.distribute((ProcessorListener.Notification)(new ProcessorListener.DeleteNotification(v, true)), false);
         }

      });
      if (cacheStateComplete != null) {
         SharedProcessor var4 = this.processor;
         Objects.requireNonNull(var4);
         cacheStateComplete.accept(var4::executeIfPossible);
      }

   }

   public String getKey(HasMetadata obj) {
      return this.cache.getKey(obj);
   }

   public void resync() {
      synchronized(this.cache.getLockObject()) {
         this.cache.list().forEach((i) -> this.processor.distribute((ProcessorListener.Notification)(new ProcessorListener.UpdateNotification(i, i)), true));
      }
   }
}
