package org.apache.logging.slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.status.StatusLogger;
import org.slf4j.spi.MDCAdapter;

public class Log4jMDCAdapter implements MDCAdapter {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private final ThreadLocalMapOfStacks mapOfStacks = new ThreadLocalMapOfStacks();

   public void put(final String key, final String val) {
      ThreadContext.put(key, val);
   }

   public String get(final String key) {
      return ThreadContext.get(key);
   }

   public void remove(final String key) {
      ThreadContext.remove(key);
   }

   public void clear() {
      ThreadContext.clearMap();
   }

   public Map getCopyOfContextMap() {
      return ThreadContext.getContext();
   }

   public void setContextMap(final Map map) {
      ThreadContext.clearMap();
      ThreadContext.putAll(map);
   }

   public void pushByKey(final String key, final String value) {
      if (key == null) {
         ThreadContext.push(value);
      } else {
         String oldValue = this.mapOfStacks.peekByKey(key);
         if (!Objects.equals(ThreadContext.get(key), oldValue)) {
            LOGGER.warn("The key {} was used in both the string and stack-valued MDC.", key);
         }

         this.mapOfStacks.pushByKey(key, value);
         ThreadContext.put(key, value);
      }

   }

   public String popByKey(final String key) {
      if (key == null) {
         return ThreadContext.getDepth() > 0 ? ThreadContext.pop() : null;
      } else {
         String value = this.mapOfStacks.popByKey(key);
         if (!Objects.equals(ThreadContext.get(key), value)) {
            LOGGER.warn("The key {} was used in both the string and stack-valued MDC.", key);
         }

         ThreadContext.put(key, this.mapOfStacks.peekByKey(key));
         return value;
      }
   }

   public Deque getCopyOfDequeByKey(final String key) {
      if (key == null) {
         ThreadContext.ContextStack stack = ThreadContext.getImmutableStack();
         Deque<String> copy = new ArrayDeque(stack.size());
         Objects.requireNonNull(copy);
         stack.forEach(copy::push);
         return copy;
      } else {
         return this.mapOfStacks.getCopyOfDequeByKey(key);
      }
   }

   public void clearDequeByKey(final String key) {
      if (key == null) {
         ThreadContext.clearStack();
      } else {
         this.mapOfStacks.clearByKey(key);
         ThreadContext.put(key, (String)null);
      }

   }

   private static class ThreadLocalMapOfStacks {
      private final ThreadLocal tlMapOfStacks;

      private ThreadLocalMapOfStacks() {
         this.tlMapOfStacks = ThreadLocal.withInitial(HashMap::new);
      }

      public void pushByKey(final String key, final String value) {
         ((Deque)((Map)this.tlMapOfStacks.get()).computeIfAbsent(key, (ignored) -> new ArrayDeque())).push(value);
      }

      public String popByKey(final String key) {
         Deque<String> deque = (Deque)((Map)this.tlMapOfStacks.get()).get(key);
         return deque != null ? (String)deque.poll() : null;
      }

      public Deque getCopyOfDequeByKey(final String key) {
         Deque<String> deque = (Deque)((Map)this.tlMapOfStacks.get()).get(key);
         return deque != null ? new ArrayDeque(deque) : null;
      }

      public void clearByKey(final String key) {
         Deque<String> deque = (Deque)((Map)this.tlMapOfStacks.get()).get(key);
         if (deque != null) {
            deque.clear();
         }

      }

      public String peekByKey(final String key) {
         Deque<String> deque = (Deque)((Map)this.tlMapOfStacks.get()).get(key);
         return deque != null ? (String)deque.peek() : null;
      }
   }
}
