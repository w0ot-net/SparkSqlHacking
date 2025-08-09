package io.fabric8.kubernetes.api.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public interface VisitorListener {
   AtomicBoolean loaded = new AtomicBoolean();
   Set listeners = new HashSet();

   static Set getListeners() {
      if (loaded.get()) {
         return listeners;
      } else {
         synchronized(loaded) {
            listeners.add(new VisitorListener() {
            });

            for(VisitorListener listener : ServiceLoader.load(VisitorListener.class, VisitorListener.class.getClassLoader())) {
               listeners.add(listener);
            }

            if (Thread.currentThread().getContextClassLoader() != null) {
               for(VisitorListener listener : ServiceLoader.load(VisitorListener.class, Thread.currentThread().getContextClassLoader())) {
                  listeners.add(listener);
               }
            }

            loaded.set(true);
         }

         return listeners;
      }
   }

   static Visitor wrap(Visitor visitor) {
      return VisitorWiretap.create(visitor, getListeners());
   }

   static void register(VisitorListener listener) {
      listeners.add(listener);
   }

   static void unregister(VisitorListener listener) {
      listeners.add(listener);
   }

   default void beforeVisit(Visitor v, List path, Object target) {
   }

   default void afterVisit(Visitor v, List path, Object target) {
   }

   default void onCheck(Visitor v, boolean canVisit, Object target) {
   }
}
