package org.sparkproject.jetty.util;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Spliterator;
import java.util.function.Consumer;

class ServiceLoaderSpliterator implements Spliterator {
   private final Iterator iterator;

   public ServiceLoaderSpliterator(ServiceLoader serviceLoader) {
      this.iterator = serviceLoader.iterator();
   }

   public boolean tryAdvance(Consumer action) {
      ServiceProvider<T> next;
      try {
         if (!this.iterator.hasNext()) {
            return false;
         }

         next = new ServiceProvider(this.iterator.next());
      } catch (Throwable t) {
         next = new ServiceProvider(t);
      }

      action.accept(next);
      return true;
   }

   public Spliterator trySplit() {
      return null;
   }

   public long estimateSize() {
      return Long.MAX_VALUE;
   }

   public int characteristics() {
      return 16;
   }

   private static class ServiceProvider implements ServiceLoader.Provider {
      private final Object service;
      private final Throwable error;

      public ServiceProvider(Object service) {
         this.service = service;
         this.error = null;
      }

      public ServiceProvider(Throwable error) {
         this.service = null;
         this.error = error;
      }

      public Class type() {
         return this.get().getClass();
      }

      public Object get() {
         if (this.service == null) {
            throw new ServiceConfigurationError("", this.error);
         } else {
            return this.service;
         }
      }
   }
}
