package io.fabric8.kubernetes.client.informers.cache;

import io.fabric8.kubernetes.api.model.HasMetadata;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Stream;

public class BasicItemStore implements ItemStore {
   private final Function keyFunction;
   private final ConcurrentMap store = new ConcurrentHashMap();

   public BasicItemStore(Function keyFunction) {
      this.keyFunction = keyFunction;
   }

   public String getKey(HasMetadata obj) {
      return (String)this.keyFunction.apply(obj);
   }

   public HasMetadata put(String key, HasMetadata obj) {
      return (HasMetadata)this.store.put(key, obj);
   }

   public HasMetadata remove(String key) {
      return (HasMetadata)this.store.remove(key);
   }

   public Stream keySet() {
      return this.store.keySet().stream();
   }

   public Stream values() {
      return this.store.values().stream();
   }

   public HasMetadata get(String key) {
      return (HasMetadata)this.store.get(key);
   }

   public int size() {
      return this.store.size();
   }
}
