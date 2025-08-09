package org.apache.curator.framework.listen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.curator.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingListenerManager implements ListenerManager {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final Map listeners = new ConcurrentHashMap();
   private final Function mapper;

   public static ListenerManager mapping(Function mapper) {
      return new MappingListenerManager(mapper);
   }

   public void addListener(Object listener) {
      this.addListener(listener, Runnable::run);
   }

   public void addListener(Object listener, Executor executor) {
      V mapped = (V)this.mapper.apply(listener);
      this.listeners.put(listener, new ListenerEntry(mapped, executor));
   }

   public void removeListener(Object listener) {
      if (listener != null) {
         this.listeners.remove(listener);
      }

   }

   public void clear() {
      this.listeners.clear();
   }

   public int size() {
      return this.listeners.size();
   }

   public void forEach(Consumer function) {
      for(ListenerEntry entry : this.listeners.values()) {
         entry.executor.execute(() -> {
            try {
               function.accept(entry.listener);
            } catch (Throwable e) {
               ThreadUtils.checkInterrupted(e);
               this.log.error(String.format("Listener (%s) threw an exception", entry.listener), e);
            }

         });
      }

   }

   MappingListenerManager(Function mapper) {
      this.mapper = mapper;
   }
}
