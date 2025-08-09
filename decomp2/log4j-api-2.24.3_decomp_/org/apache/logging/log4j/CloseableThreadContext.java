package org.apache.logging.log4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloseableThreadContext {
   private CloseableThreadContext() {
   }

   public static Instance push(final String message) {
      return (new Instance()).push(message);
   }

   public static Instance push(final String message, final Object... args) {
      return (new Instance()).push(message, args);
   }

   public static Instance put(final String key, final String value) {
      return (new Instance()).put(key, value);
   }

   public static Instance pushAll(final List messages) {
      return (new Instance()).pushAll(messages);
   }

   public static Instance putAll(final Map values) {
      return (new Instance()).putAll(values);
   }

   public static class Instance implements AutoCloseable {
      private int pushCount;
      private final Map originalValues;

      private Instance() {
         this.pushCount = 0;
         this.originalValues = new HashMap();
      }

      public Instance push(final String message) {
         ThreadContext.push(message);
         ++this.pushCount;
         return this;
      }

      public Instance push(final String message, final Object[] args) {
         ThreadContext.push(message, args);
         ++this.pushCount;
         return this;
      }

      public Instance put(final String key, final String value) {
         if (!this.originalValues.containsKey(key)) {
            this.originalValues.put(key, ThreadContext.get(key));
         }

         ThreadContext.put(key, value);
         return this;
      }

      public Instance putAll(final Map values) {
         Map<String, String> currentValues = ThreadContext.getContext();
         ThreadContext.putAll(values);

         for(String key : values.keySet()) {
            if (!this.originalValues.containsKey(key)) {
               this.originalValues.put(key, (String)currentValues.get(key));
            }
         }

         return this;
      }

      public Instance pushAll(final List messages) {
         for(String message : messages) {
            this.push(message);
         }

         return this;
      }

      public void close() {
         this.closeStack();
         this.closeMap();
      }

      private void closeMap() {
         Map<String, String> valuesToReplace = new HashMap(this.originalValues.size());
         List<String> keysToRemove = new ArrayList(this.originalValues.size());

         for(Map.Entry entry : this.originalValues.entrySet()) {
            String key = (String)entry.getKey();
            String originalValue = (String)entry.getValue();
            if (null == originalValue) {
               keysToRemove.add(key);
            } else {
               valuesToReplace.put(key, originalValue);
            }
         }

         if (!valuesToReplace.isEmpty()) {
            ThreadContext.putAll(valuesToReplace);
         }

         if (!keysToRemove.isEmpty()) {
            ThreadContext.removeAll(keysToRemove);
         }

      }

      private void closeStack() {
         for(int i = 0; i < this.pushCount; ++i) {
            ThreadContext.pop();
         }

         this.pushCount = 0;
      }
   }
}
