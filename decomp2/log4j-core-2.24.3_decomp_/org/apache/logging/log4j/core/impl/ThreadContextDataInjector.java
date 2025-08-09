package org.apache.logging.log4j.core.impl;

import aQute.bnd.annotation.spi.ServiceConsumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.ContextDataInjector;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.util.ContextDataProvider;
import org.apache.logging.log4j.spi.ReadOnlyThreadContextMap;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.ServiceLoaderUtil;
import org.apache.logging.log4j.util.StringMap;

@ServiceConsumer(
   value = ContextDataProvider.class,
   resolution = "optional",
   cardinality = "multiple"
)
public class ThreadContextDataInjector {
   private static final Logger LOGGER = StatusLogger.getLogger();
   public static Collection contextDataProviders = new ConcurrentLinkedDeque();
   private static final List SERVICE_PROVIDERS = getServiceProviders();

   /** @deprecated */
   @Deprecated
   public static void initServiceProviders() {
   }

   private static List getServiceProviders() {
      List<ContextDataProvider> providers = new ArrayList();
      Stream var10000 = ServiceLoaderUtil.safeStream(ContextDataProvider.class, ServiceLoader.load(ContextDataProvider.class, ThreadContextDataInjector.class.getClassLoader()), LOGGER);
      Objects.requireNonNull(providers);
      var10000.forEach(providers::add);
      return Collections.unmodifiableList(providers);
   }

   public static void copyProperties(final List properties, final StringMap result) {
      if (properties != null) {
         for(int i = 0; i < properties.size(); ++i) {
            Property prop = (Property)properties.get(i);
            result.putValue(prop.getName(), prop.getValue());
         }
      }

   }

   private static List getProviders() {
      List<ContextDataProvider> providers = new ArrayList(contextDataProviders.size() + SERVICE_PROVIDERS.size());
      providers.addAll(contextDataProviders);
      providers.addAll(SERVICE_PROVIDERS);
      return providers;
   }

   private abstract static class AbstractContextDataInjector implements ContextDataInjector {
      final List providers = ThreadContextDataInjector.getProviders();

      AbstractContextDataInjector() {
      }

      public Object getValue(String key) {
         for(ContextDataProvider provider : this.providers) {
            Object value = provider.getValue(key);
            if (value != null) {
               return value;
            }
         }

         return null;
      }
   }

   public static class ForDefaultThreadContextMap extends AbstractContextDataInjector {
      public StringMap injectContextData(final List props, final StringMap ignore) {
         Map<String, String> copy;
         if (this.providers.size() == 1) {
            copy = ((ContextDataProvider)this.providers.get(0)).supplyContextData();
         } else {
            copy = new HashMap();

            for(ContextDataProvider provider : this.providers) {
               copy.putAll(provider.supplyContextData());
            }
         }

         if (props != null && !props.isEmpty()) {
            StringMap result = new JdkMapAdapterStringMap(new HashMap(copy), false);

            for(int i = 0; i < props.size(); ++i) {
               Property prop = (Property)props.get(i);
               if (!copy.containsKey(prop.getName())) {
                  result.putValue(prop.getName(), prop.getValue());
               }
            }

            result.freeze();
            return result;
         } else {
            return (StringMap)(copy.isEmpty() ? ContextDataFactory.emptyFrozenContextData() : frozenStringMap(copy));
         }
      }

      private static JdkMapAdapterStringMap frozenStringMap(final Map copy) {
         return new JdkMapAdapterStringMap(copy, true);
      }

      public ReadOnlyStringMap rawContextData() {
         ReadOnlyThreadContextMap map = ThreadContext.getThreadContextMap();
         if (map != null) {
            return map.getReadOnlyContextData();
         } else {
            Map<String, String> copy = ThreadContext.getImmutableContext();
            return (ReadOnlyStringMap)(copy.isEmpty() ? ContextDataFactory.emptyFrozenContextData() : new JdkMapAdapterStringMap(copy, true));
         }
      }
   }

   public static class ForGarbageFreeThreadContextMap extends AbstractContextDataInjector {
      public StringMap injectContextData(final List props, final StringMap reusable) {
         ThreadContextDataInjector.copyProperties(props, reusable);

         for(int i = 0; i < this.providers.size(); ++i) {
            reusable.putAll(((ContextDataProvider)this.providers.get(i)).supplyStringMap());
         }

         return reusable;
      }

      public ReadOnlyStringMap rawContextData() {
         return ThreadContext.getThreadContextMap().getReadOnlyContextData();
      }
   }

   public static class ForCopyOnWriteThreadContextMap extends AbstractContextDataInjector {
      public StringMap injectContextData(final List props, final StringMap ignore) {
         if (this.providers.size() != 1 || props != null && !props.isEmpty()) {
            int count = props == null ? 0 : props.size();
            StringMap[] maps = new StringMap[this.providers.size()];

            for(int i = 0; i < this.providers.size(); ++i) {
               maps[i] = ((ContextDataProvider)this.providers.get(i)).supplyStringMap();
               count += maps[i].size();
            }

            StringMap result = ContextDataFactory.createContextData(count);
            ThreadContextDataInjector.copyProperties(props, result);

            for(StringMap map : maps) {
               result.putAll(map);
            }

            return result;
         } else {
            return ((ContextDataProvider)this.providers.get(0)).supplyStringMap();
         }
      }

      public ReadOnlyStringMap rawContextData() {
         return ThreadContext.getThreadContextMap().getReadOnlyContextData();
      }
   }
}
