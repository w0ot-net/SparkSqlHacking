package org.apache.commons.collections4.multimap;

import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.FluentIterable;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;

public class TransformedMultiValuedMap extends AbstractMultiValuedMapDecorator {
   private static final long serialVersionUID = 20150612L;
   private final Transformer keyTransformer;
   private final Transformer valueTransformer;

   public static TransformedMultiValuedMap transformingMap(MultiValuedMap map, Transformer keyTransformer, Transformer valueTransformer) {
      return new TransformedMultiValuedMap(map, keyTransformer, valueTransformer);
   }

   public static TransformedMultiValuedMap transformedMap(MultiValuedMap map, Transformer keyTransformer, Transformer valueTransformer) {
      TransformedMultiValuedMap<K, V> decorated = new TransformedMultiValuedMap(map, keyTransformer, valueTransformer);
      if (!map.isEmpty()) {
         MultiValuedMap<K, V> mapCopy = new ArrayListValuedHashMap(map);
         decorated.clear();
         decorated.putAll(mapCopy);
      }

      return decorated;
   }

   protected TransformedMultiValuedMap(MultiValuedMap map, Transformer keyTransformer, Transformer valueTransformer) {
      super(map);
      this.keyTransformer = keyTransformer;
      this.valueTransformer = valueTransformer;
   }

   protected Object transformKey(Object object) {
      return this.keyTransformer == null ? object : this.keyTransformer.transform(object);
   }

   protected Object transformValue(Object object) {
      return this.valueTransformer == null ? object : this.valueTransformer.transform(object);
   }

   public boolean put(Object key, Object value) {
      return this.decorated().put(this.transformKey(key), this.transformValue(value));
   }

   public boolean putAll(Object key, Iterable values) {
      if (values == null) {
         throw new NullPointerException("Values must not be null.");
      } else {
         Iterable<V> transformedValues = FluentIterable.of(values).transform(this.valueTransformer);
         Iterator<? extends V> it = transformedValues.iterator();
         return it.hasNext() && CollectionUtils.addAll(this.decorated().get(this.transformKey(key)), it);
      }
   }

   public boolean putAll(Map map) {
      if (map == null) {
         throw new NullPointerException("Map must not be null.");
      } else {
         boolean changed = false;

         for(Map.Entry entry : map.entrySet()) {
            changed |= this.put(entry.getKey(), entry.getValue());
         }

         return changed;
      }
   }

   public boolean putAll(MultiValuedMap map) {
      if (map == null) {
         throw new NullPointerException("Map must not be null.");
      } else {
         boolean changed = false;

         for(Map.Entry entry : map.entries()) {
            changed |= this.put(entry.getKey(), entry.getValue());
         }

         return changed;
      }
   }
}
