package com.ibm.icu.message2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** @deprecated */
@Deprecated
public class MFFunctionRegistry {
   private final Map formattersMap;
   private final Map selectorsMap;
   private final Map classToFormatter;

   private MFFunctionRegistry(Builder builder) {
      this.formattersMap = new HashMap(builder.formattersMap);
      this.selectorsMap = new HashMap(builder.selectorsMap);
      this.classToFormatter = new HashMap(builder.classToFormatter);
   }

   /** @deprecated */
   @Deprecated
   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public FormatterFactory getFormatter(String formatterName) {
      return (FormatterFactory)this.formattersMap.get(formatterName);
   }

   /** @deprecated */
   @Deprecated
   public Set getFormatterNames() {
      return this.formattersMap.keySet();
   }

   /** @deprecated */
   @Deprecated
   public String getDefaultFormatterNameForType(Class clazz) {
      String result = (String)this.classToFormatter.get(clazz);
      if (result != null) {
         return result;
      } else {
         for(Map.Entry e : this.classToFormatter.entrySet()) {
            if (((Class)e.getKey()).isAssignableFrom(clazz)) {
               return (String)e.getValue();
            }
         }

         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public Set getDefaultFormatterTypes() {
      return this.classToFormatter.keySet();
   }

   /** @deprecated */
   @Deprecated
   public SelectorFactory getSelector(String selectorName) {
      return (SelectorFactory)this.selectorsMap.get(selectorName);
   }

   /** @deprecated */
   @Deprecated
   public Set getSelectorNames() {
      return this.selectorsMap.keySet();
   }

   /** @deprecated */
   @Deprecated
   public static class Builder {
      private final Map formattersMap;
      private final Map selectorsMap;
      private final Map classToFormatter;

      private Builder() {
         this.formattersMap = new HashMap();
         this.selectorsMap = new HashMap();
         this.classToFormatter = new HashMap();
      }

      /** @deprecated */
      @Deprecated
      public Builder addAll(MFFunctionRegistry functionRegistry) {
         this.formattersMap.putAll(functionRegistry.formattersMap);
         this.selectorsMap.putAll(functionRegistry.selectorsMap);
         this.classToFormatter.putAll(functionRegistry.classToFormatter);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setFormatter(String formatterName, FormatterFactory formatterFactory) {
         this.formattersMap.put(formatterName, formatterFactory);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder removeFormatter(String formatterName) {
         this.formattersMap.remove(formatterName);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder clearFormatters() {
         this.formattersMap.clear();
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setDefaultFormatterNameForType(Class clazz, String formatterName) {
         this.classToFormatter.put(clazz, formatterName);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder removeDefaultFormatterNameForType(Class clazz) {
         this.classToFormatter.remove(clazz);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder clearDefaultFormatterNames() {
         this.classToFormatter.clear();
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setSelector(String selectorName, SelectorFactory selectorFactory) {
         this.selectorsMap.put(selectorName, selectorFactory);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder removeSelector(String selectorName) {
         this.selectorsMap.remove(selectorName);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder clearSelectors() {
         this.selectorsMap.clear();
         return this;
      }

      /** @deprecated */
      @Deprecated
      public MFFunctionRegistry build() {
         return new MFFunctionRegistry(this);
      }
   }
}
