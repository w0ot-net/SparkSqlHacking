package io.fabric8.kubernetes.client.informers.cache;

import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

public class ReducedStateItemStore implements ItemStore {
   private static final String METADATA = "metadata";
   private final ConcurrentHashMap store = new ConcurrentHashMap();
   private final List fields = new ArrayList();
   private final Class typeClass;
   private final KeyState keyState;
   private KubernetesSerialization serialization;
   public static final KeyState NAME_KEY_STATE = new KeyState(Cache::metaNamespaceKeyFunc, (k) -> {
      int index = k.indexOf("/");
      return index == -1 ? new String[]{null, k} : new String[]{k.substring(0, index), k.substring(index + 1)};
   }, new String[][]{{"metadata", "namespace"}, {"metadata", "name"}});
   public static final KeyState UID_KEY_STATE = new KeyState(Cache::metaUidKeyFunc, (k) -> new String[]{k}, new String[][]{{"metadata", "uid"}});

   public ReducedStateItemStore(KeyState keyState, Class typeClass, KubernetesSerialization serialization, String... valueFields) {
      this.keyState = keyState;
      this.fields.add(new String[]{"metadata", "resourceVersion"});
      if (valueFields != null) {
         for(int i = 0; i < valueFields.length; ++i) {
            this.fields.add(valueFields[i].split("\\."));
         }
      }

      this.typeClass = typeClass;
      this.serialization = serialization;
   }

   Object[] store(HasMetadata value) {
      if (value == null) {
         return null;
      } else {
         Map<String, Object> raw = (Map)this.serialization.convertValue(value, Map.class);
         return this.fields.stream().map((f) -> GenericKubernetesResource.get(raw, (Object[])f)).toArray();
      }
   }

   HasMetadata restore(String key, Object[] values) {
      if (values == null) {
         return null;
      } else {
         Map<String, Object> raw = new HashMap();
         applyFields(values, raw, this.fields);
         String[] keyParts = (String[])this.keyState.keyFieldFunction.apply(key);
         applyFields(keyParts, raw, this.keyState.keyFields);
         return (HasMetadata)this.serialization.convertValue(raw, this.typeClass);
      }
   }

   private static void applyFields(Object[] values, Map raw, List fields) {
      for(int i = 0; i < fields.size(); ++i) {
         Object value = values[i];
         if (value != null) {
            String[] path = (String[])fields.get(i);
            Map<String, Object> parent = raw;

            for(int j = 0; j < path.length - 1; ++j) {
               parent = (Map)parent.computeIfAbsent(path[j], (k) -> new LinkedHashMap());
            }

            parent.put(path[path.length - 1], value);
         }
      }

   }

   public HasMetadata put(String key, HasMetadata obj) {
      return this.restore(key, this.store.put(key, this.store(obj)));
   }

   public HasMetadata remove(String key) {
      return this.restore(key, this.store.remove(key));
   }

   public Stream keySet() {
      return this.store.keySet().stream();
   }

   public Stream values() {
      return this.store.entrySet().stream().map((e) -> this.restore((String)e.getKey(), e.getValue()));
   }

   public HasMetadata get(String key) {
      return this.restore(key, this.store.get(key));
   }

   public String getResourceVersion(String key) {
      return (String)((Object[])this.store.getOrDefault(key, new Object[1]))[0];
   }

   public int size() {
      return this.store.size();
   }

   public String getKey(HasMetadata obj) {
      return (String)this.keyState.keyFunction.apply(obj);
   }

   public boolean isFullState() {
      return false;
   }

   public static class KeyState {
      final Function keyFunction;
      final Function keyFieldFunction;
      final List keyFields;

      public KeyState(Function keyFunction, Function keyFieldFunction, String[]... keyFields) {
         this.keyFunction = keyFunction;
         this.keyFieldFunction = keyFieldFunction;
         this.keyFields = Arrays.asList(keyFields);
      }
   }
}
