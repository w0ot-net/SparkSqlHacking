package org.apache.commons.lang3.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class MultiBackgroundInitializer extends BackgroundInitializer {
   private final Map childInitializers = new HashMap();

   public MultiBackgroundInitializer() {
   }

   public MultiBackgroundInitializer(ExecutorService exec) {
      super(exec);
   }

   public void addInitializer(String name, BackgroundInitializer backgroundInitializer) {
      Objects.requireNonNull(name, "name");
      Objects.requireNonNull(backgroundInitializer, "backgroundInitializer");
      synchronized(this) {
         if (this.isStarted()) {
            throw new IllegalStateException("addInitializer() must not be called after start()!");
         } else {
            this.childInitializers.put(name, backgroundInitializer);
         }
      }
   }

   public void close() throws ConcurrentException {
      ConcurrentException exception = null;

      for(BackgroundInitializer child : this.childInitializers.values()) {
         try {
            child.close();
         } catch (Exception e) {
            if (exception == null) {
               exception = new ConcurrentException();
            }

            if (e instanceof ConcurrentException) {
               exception.addSuppressed(e.getCause());
            } else {
               exception.addSuppressed(e);
            }
         }
      }

      if (exception != null) {
         throw exception;
      }
   }

   protected int getTaskCount() {
      return 1 + this.childInitializers.values().stream().mapToInt(BackgroundInitializer::getTaskCount).sum();
   }

   protected MultiBackgroundInitializerResults initialize() throws Exception {
      Map<String, BackgroundInitializer<?>> inits;
      synchronized(this) {
         inits = new HashMap(this.childInitializers);
      }

      ExecutorService exec = this.getActiveExecutor();
      inits.values().forEach((bi) -> {
         if (bi.getExternalExecutor() == null) {
            bi.setExternalExecutor(exec);
         }

         bi.start();
      });
      Map<String, Object> results = new HashMap();
      Map<String, ConcurrentException> excepts = new HashMap();
      inits.forEach((k, v) -> {
         try {
            results.put(k, v.get());
         } catch (ConcurrentException cex) {
            excepts.put(k, cex);
         }

      });
      return new MultiBackgroundInitializerResults(inits, results, excepts);
   }

   public boolean isInitialized() {
      return this.childInitializers.isEmpty() ? false : this.childInitializers.values().stream().allMatch(BackgroundInitializer::isInitialized);
   }

   public static class MultiBackgroundInitializerResults {
      private final Map initializers;
      private final Map resultObjects;
      private final Map exceptions;

      private MultiBackgroundInitializerResults(Map inits, Map results, Map excepts) {
         this.initializers = inits;
         this.resultObjects = results;
         this.exceptions = excepts;
      }

      private BackgroundInitializer checkName(String name) {
         BackgroundInitializer<?> init = (BackgroundInitializer)this.initializers.get(name);
         if (init == null) {
            throw new NoSuchElementException("No child initializer with name " + name);
         } else {
            return init;
         }
      }

      public ConcurrentException getException(String name) {
         this.checkName(name);
         return (ConcurrentException)this.exceptions.get(name);
      }

      public BackgroundInitializer getInitializer(String name) {
         return this.checkName(name);
      }

      public Object getResultObject(String name) {
         this.checkName(name);
         return this.resultObjects.get(name);
      }

      public Set initializerNames() {
         return Collections.unmodifiableSet(this.initializers.keySet());
      }

      public boolean isException(String name) {
         this.checkName(name);
         return this.exceptions.containsKey(name);
      }

      public boolean isSuccessful() {
         return this.exceptions.isEmpty();
      }
   }
}
