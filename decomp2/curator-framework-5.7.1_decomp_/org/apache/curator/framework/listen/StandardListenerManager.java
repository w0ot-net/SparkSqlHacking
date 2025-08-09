package org.apache.curator.framework.listen;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class StandardListenerManager implements UnaryListenerManager {
   private final ListenerManager container;

   public static StandardListenerManager standard() {
      MappingListenerManager<T, T> container = new MappingListenerManager(Function.identity());
      return new StandardListenerManager(container);
   }

   public static StandardListenerManager mappingStandard(UnaryOperator mapper) {
      MappingListenerManager<T, T> container = new MappingListenerManager(mapper);
      return new StandardListenerManager(container);
   }

   public void addListener(Object listener) {
      this.container.addListener(listener);
   }

   public void addListener(Object listener, Executor executor) {
      this.container.addListener(listener, executor);
   }

   public void removeListener(Object listener) {
      this.container.removeListener(listener);
   }

   public void clear() {
      this.container.clear();
   }

   public int size() {
      return this.container.size();
   }

   public void forEach(Consumer function) {
      this.container.forEach(function);
   }

   private StandardListenerManager(ListenerManager container) {
      this.container = container;
   }
}
