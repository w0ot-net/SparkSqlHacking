package org.apache.logging.log4j;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.spi.CleanableThreadContextMap;
import org.apache.logging.log4j.spi.DefaultThreadContextMap;
import org.apache.logging.log4j.spi.DefaultThreadContextStack;
import org.apache.logging.log4j.spi.MutableThreadContextStack;
import org.apache.logging.log4j.spi.ReadOnlyThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextMap2;
import org.apache.logging.log4j.spi.ThreadContextMapFactory;
import org.apache.logging.log4j.spi.ThreadContextStack;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ProviderUtil;

public final class ThreadContext {
   public static final Map EMPTY_MAP = Collections.emptyMap();
   public static final ThreadContextStack EMPTY_STACK = new EmptyThreadContextStack();
   private static final String DISABLE_STACK = "disableThreadContextStack";
   private static final String DISABLE_ALL = "disableThreadContext";
   private static ThreadContextStack contextStack;
   private static ThreadContextMap contextMap;
   private static ReadOnlyThreadContextMap readOnlyContextMap;

   private ThreadContext() {
   }

   public static void init() {
      PropertiesUtil properties = PropertiesUtil.getProperties();
      contextStack = (ThreadContextStack)(!properties.getBooleanProperty("disableThreadContextStack") && !properties.getBooleanProperty("disableThreadContext") ? new DefaultThreadContextStack() : new NoOpThreadContextStack());
      ThreadContextMapFactory.init();
      contextMap = ProviderUtil.getProvider().getThreadContextMapInstance();
      readOnlyContextMap = contextMap instanceof ReadOnlyThreadContextMap ? (ReadOnlyThreadContextMap)contextMap : null;
   }

   public static void put(final String key, final String value) {
      contextMap.put(key, value);
   }

   public static void putIfNull(final String key, final String value) {
      if (!contextMap.containsKey(key)) {
         contextMap.put(key, value);
      }

   }

   public static void putAll(final Map m) {
      if (contextMap instanceof ThreadContextMap2) {
         ((ThreadContextMap2)contextMap).putAll(m);
      } else if (contextMap instanceof DefaultThreadContextMap) {
         ((DefaultThreadContextMap)contextMap).putAll(m);
      } else {
         for(Map.Entry entry : m.entrySet()) {
            contextMap.put((String)entry.getKey(), (String)entry.getValue());
         }
      }

   }

   public static String get(final String key) {
      return contextMap.get(key);
   }

   public static void remove(final String key) {
      contextMap.remove(key);
   }

   public static void removeAll(final Iterable keys) {
      if (contextMap instanceof CleanableThreadContextMap) {
         ((CleanableThreadContextMap)contextMap).removeAll(keys);
      } else if (contextMap instanceof DefaultThreadContextMap) {
         ((DefaultThreadContextMap)contextMap).removeAll(keys);
      } else {
         for(String key : keys) {
            contextMap.remove(key);
         }
      }

   }

   public static void clearMap() {
      contextMap.clear();
   }

   public static void clearAll() {
      clearMap();
      clearStack();
   }

   public static boolean containsKey(final String key) {
      return contextMap.containsKey(key);
   }

   public static Map getContext() {
      return contextMap.getCopy();
   }

   public static Map getImmutableContext() {
      Map<String, String> map = contextMap.getImmutableMapOrNull();
      return map == null ? EMPTY_MAP : map;
   }

   public static ReadOnlyThreadContextMap getThreadContextMap() {
      return readOnlyContextMap;
   }

   public static boolean isEmpty() {
      return contextMap.isEmpty();
   }

   public static void clearStack() {
      contextStack.clear();
   }

   public static ContextStack cloneStack() {
      return contextStack.copy();
   }

   public static ContextStack getImmutableStack() {
      ContextStack result = contextStack.getImmutableStackOrNull();
      return (ContextStack)(result == null ? EMPTY_STACK : result);
   }

   public static void setStack(final Collection stack) {
      if (!stack.isEmpty()) {
         contextStack.clear();
         contextStack.addAll(stack);
      }
   }

   public static int getDepth() {
      return contextStack.getDepth();
   }

   public static String pop() {
      return contextStack.pop();
   }

   public static String peek() {
      return contextStack.peek();
   }

   public static void push(final String message) {
      contextStack.push(message);
   }

   public static void push(final String message, final Object... args) {
      contextStack.push(ParameterizedMessage.format(message, args));
   }

   public static void removeStack() {
      contextStack.clear();
   }

   public static void trim(final int depth) {
      contextStack.trim(depth);
   }

   static {
      init();
   }

   private static class EmptyThreadContextStack extends AbstractCollection implements ThreadContextStack {
      private static final long serialVersionUID = 1L;

      private EmptyThreadContextStack() {
      }

      public String pop() {
         return null;
      }

      public String peek() {
         return null;
      }

      public void push(final String message) {
         throw new UnsupportedOperationException();
      }

      public int getDepth() {
         return 0;
      }

      public List asList() {
         return Collections.emptyList();
      }

      public void trim(final int depth) {
      }

      public boolean equals(final Object o) {
         return o instanceof Collection && ((Collection)o).isEmpty();
      }

      public int hashCode() {
         return 1;
      }

      public ContextStack copy() {
         return new MutableThreadContextStack();
      }

      public Object[] toArray(final Object[] ignored) {
         throw new UnsupportedOperationException();
      }

      public boolean add(final String ignored) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
      }

      public boolean containsAll(final Collection ignored) {
         return false;
      }

      public boolean addAll(final Collection ignored) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(final Collection ignored) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(final Collection ignored) {
         throw new UnsupportedOperationException();
      }

      public Iterator iterator() {
         return Collections.emptyIterator();
      }

      public int size() {
         return 0;
      }

      public ContextStack getImmutableStackOrNull() {
         return this;
      }
   }

   private static final class NoOpThreadContextStack extends EmptyThreadContextStack {
      private NoOpThreadContextStack() {
      }

      public boolean add(final String ignored) {
         return false;
      }

      public boolean addAll(final Collection ignored) {
         return false;
      }

      public void push(final String ignored) {
      }

      public boolean remove(final Object ignored) {
         return false;
      }

      public boolean removeAll(final Collection ignored) {
         return false;
      }

      public boolean retainAll(final Collection ignored) {
         return false;
      }
   }

   public interface ContextStack extends Serializable, Collection {
      String pop();

      String peek();

      void push(String message);

      int getDepth();

      List asList();

      void trim(int depth);

      ContextStack copy();

      ContextStack getImmutableStackOrNull();
   }
}
