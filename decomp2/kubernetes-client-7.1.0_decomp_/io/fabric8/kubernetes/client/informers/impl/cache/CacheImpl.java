package io.fabric8.kubernetes.client.informers.impl.cache;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.informers.cache.BasicItemStore;
import io.fabric8.kubernetes.client.informers.cache.Cache;
import io.fabric8.kubernetes.client.informers.cache.ItemStore;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CacheImpl implements Cache {
   public static final String NAMESPACE_INDEX = "namespace";
   private final Map indexers;
   private ItemStore items;
   private final ConcurrentMap indices;

   public CacheImpl() {
      this("namespace", Cache::metaNamespaceIndexFunc, Cache::metaNamespaceKeyFunc);
   }

   public CacheImpl(String indexName, Function indexFunc, Function keyFunc) {
      this.indexers = Collections.synchronizedMap(new HashMap());
      this.indices = new ConcurrentHashMap();
      this.items = new BasicItemStore(keyFunc);
      this.addIndexFunc(indexName, indexFunc);
   }

   public void setItemStore(ItemStore items) {
      this.items = items;
   }

   public Map getIndexers() {
      synchronized(this.indexers) {
         return Collections.unmodifiableMap(this.indexers);
      }
   }

   public synchronized void addIndexers(Map indexersNew) {
      Set<String> intersection = new HashSet(this.indexers.keySet());
      intersection.retainAll(indexersNew.keySet());
      if (!intersection.isEmpty()) {
         throw new IllegalArgumentException("Indexer conflict: " + intersection);
      } else {
         for(Map.Entry indexEntry : indexersNew.entrySet()) {
            this.addIndexFunc((String)indexEntry.getKey(), (Function)indexEntry.getValue());
         }

      }
   }

   public synchronized HasMetadata put(HasMetadata obj) {
      if (obj == null) {
         return null;
      } else {
         String key = this.getKey(obj);
         T oldObj = (T)((HasMetadata)this.items.put(key, obj));
         this.updateIndices(oldObj, obj, key);
         return oldObj;
      }
   }

   public synchronized HasMetadata remove(HasMetadata obj) {
      String key = this.getKey(obj);
      T old = (T)((HasMetadata)this.items.remove(key));
      if (old != null) {
         this.updateIndices(old, (HasMetadata)null, key);
      }

      return old;
   }

   public List listKeys() {
      return (List)this.items.keySet().collect(Collectors.toList());
   }

   public HasMetadata get(HasMetadata obj) {
      String key = this.getKey(obj);
      return this.getByKey(key);
   }

   public String getKey(HasMetadata obj) {
      String result = this.items.getKey(obj);
      return result == null ? "" : result;
   }

   public List list() {
      return (List)this.items.values().collect(Collectors.toList());
   }

   public HasMetadata getByKey(String key) {
      return (HasMetadata)this.items.get(key);
   }

   public List index(String indexName, HasMetadata obj) {
      Function<T, List<String>> indexFunc = (Function)this.indexers.get(indexName);
      if (indexFunc == null) {
         throw new IllegalArgumentException(String.format("index %s doesn't exist!", indexName));
      } else {
         Index index = this.getIndex(indexName);
         List<String> indexKeys = (List)indexFunc.apply(obj);
         Set<String> returnKeySet = new HashSet();

         for(String indexKey : indexKeys) {
            returnKeySet.addAll(index.get(indexKey));
         }

         return this.getItems(returnKeySet);
      }
   }

   private List getItems(Set returnKeySet) {
      List<T> items = new ArrayList(returnKeySet.size());

      for(String absoluteKey : returnKeySet) {
         Optional var10000 = Optional.ofNullable((HasMetadata)this.items.get(absoluteKey));
         Objects.requireNonNull(items);
         var10000.ifPresent(items::add);
      }

      return items;
   }

   private Index getIndex(String indexName) {
      return (Index)Optional.ofNullable((Index)this.indices.get(indexName)).orElseThrow(() -> new IllegalArgumentException(String.format("index %s doesn't exist!", indexName)));
   }

   public List indexKeys(String indexName, String indexKey) {
      Index index = this.getIndex(indexName);
      return new ArrayList(index.get(indexKey));
   }

   public List byIndex(String indexName, String indexKey) {
      Index index = this.getIndex(indexName);
      return this.getItems(index.get(indexKey));
   }

   private void updateIndices(HasMetadata oldObj, HasMetadata newObj, String key) {
      for(Map.Entry indexEntry : this.indexers.entrySet()) {
         String indexName = (String)indexEntry.getKey();
         Function<T, List<String>> indexFunc = (Function)indexEntry.getValue();
         Index index = (Index)this.indices.get(indexName);
         if (index != null) {
            if (oldObj != null) {
               this.updateIndex(key, oldObj, indexFunc, index, true);
            }

            if (newObj != null) {
               this.updateIndex(key, newObj, indexFunc, index, false);
            }
         }
      }

   }

   private void updateIndex(String key, HasMetadata obj, Function indexFunc, Index index, boolean remove) {
      List<String> indexValues = (List)indexFunc.apply(obj);
      if (indexValues != null && !indexValues.isEmpty()) {
         for(String indexValue : indexValues) {
            index.update(indexValue, key, remove);
         }
      }

   }

   public synchronized CacheImpl addIndexFunc(String indexName, Function indexFunc) {
      if (this.indices.containsKey(indexName)) {
         throw new IllegalArgumentException("Indexer conflict: " + indexName);
      } else {
         Index index = new Index();
         this.indices.put(indexName, index);
         this.indexers.put(indexName, indexFunc);
         this.items.values().forEach((v) -> this.updateIndex(this.getKey(v), v, indexFunc, index, false));
         return this;
      }
   }

   public static String metaNamespaceKeyFunc(Object obj) {
      if (obj == null) {
         return "";
      } else {
         ObjectMeta metadata = null;
         if (obj instanceof String) {
            return (String)obj;
         } else {
            if (obj instanceof ObjectMeta) {
               metadata = (ObjectMeta)obj;
            } else if (obj instanceof HasMetadata) {
               metadata = ((HasMetadata)obj).getMetadata();
            }

            if (metadata == null) {
               throw new RuntimeException("Object is bad :" + obj);
            } else {
               return namespaceKeyFunc(metadata.getNamespace(), metadata.getName());
            }
         }
      }
   }

   public static String namespaceKeyFunc(String objectNamespace, String objectName) {
      return Utils.isNullOrEmpty(objectNamespace) ? objectName : objectNamespace + "/" + objectName;
   }

   public static List metaNamespaceIndexFunc(Object obj) {
      ObjectMeta metadata;
      if (obj instanceof HasMetadata) {
         metadata = ((HasMetadata)obj).getMetadata();
      } else if (obj instanceof ObjectMeta) {
         metadata = (ObjectMeta)obj;
      } else {
         metadata = null;
      }

      return metadata == null ? Collections.emptyList() : Collections.singletonList(metadata.getNamespace());
   }

   public synchronized void removeIndexer(String name) {
      this.indices.remove(name);
      this.indexers.remove(name);
   }

   public boolean isFullState() {
      return this.items.isFullState();
   }

   public Object getLockObject() {
      return this;
   }

   private static class Index {
      private Map values = new ConcurrentHashMap();

      public void update(String indexKey, String key, boolean remove) {
         if (remove) {
            this.values.computeIfPresent(indexKey == null ? this : indexKey, (k, v) -> {
               v.remove(key);
               return v.isEmpty() ? null : v;
            });
         } else {
            ((Set)this.values.computeIfAbsent(indexKey == null ? this : indexKey, (k) -> ConcurrentHashMap.newKeySet())).add(key);
         }

      }

      public Set get(String indexKey) {
         return (Set)this.values.getOrDefault(indexKey == null ? this : indexKey, Collections.emptySet());
      }
   }
}
