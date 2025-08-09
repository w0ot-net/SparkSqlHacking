package org.apache.spark.util.kvstore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.spark.annotation.Private;
import org.sparkproject.guava.base.Objects;
import org.sparkproject.guava.base.Preconditions;

@Private
public class InMemoryStore implements KVStore {
   private Object metadata;
   private InMemoryLists inMemoryLists = new InMemoryLists();

   public Object getMetadata(Class klass) {
      return klass.cast(this.metadata);
   }

   public void setMetadata(Object value) {
      this.metadata = value;
   }

   public long count(Class type) {
      InstanceList<?> list = this.inMemoryLists.get(type);
      return list != null ? (long)list.size() : 0L;
   }

   public long count(Class type, String index, Object indexedValue) throws Exception {
      InstanceList<?> list = this.inMemoryLists.get(type);
      int count = 0;
      Object comparable = asKey(indexedValue);
      KVTypeInfo.Accessor accessor = list.getIndexAccessor(index);

      for(Object o : this.view(type)) {
         if (Objects.equal(comparable, asKey(accessor.get(o)))) {
            ++count;
         }
      }

      return (long)count;
   }

   public Object read(Class klass, Object naturalKey) {
      InstanceList<T> list = this.inMemoryLists.get(klass);
      T value = (T)(list != null ? list.get(naturalKey) : null);
      if (value == null) {
         throw new NoSuchElementException();
      } else {
         return value;
      }
   }

   public void write(Object value) throws Exception {
      this.inMemoryLists.write(value);
   }

   public void delete(Class type, Object naturalKey) {
      InstanceList<?> list = this.inMemoryLists.get(type);
      if (list != null) {
         list.delete(naturalKey);
      }

   }

   public KVStoreView view(Class type) {
      InstanceList<T> list = this.inMemoryLists.get(type);
      return (KVStoreView)(list != null ? list.view() : emptyView());
   }

   public void close() {
      this.metadata = null;
      this.inMemoryLists.clear();
   }

   public boolean removeAllByIndexValues(Class klass, String index, Collection indexValues) {
      InstanceList<T> list = this.inMemoryLists.get(klass);
      if (list != null) {
         return list.countingRemoveAllByIndexValues(index, indexValues) > 0;
      } else {
         return false;
      }
   }

   private static Comparable asKey(Object in) {
      if (in.getClass().isArray()) {
         in = ArrayWrappers.forArray(in);
      }

      return (Comparable)in;
   }

   private static KVStoreView emptyView() {
      return InMemoryStore.InMemoryView.EMPTY_VIEW;
   }

   private static class InMemoryLists {
      private final ConcurrentMap data = new ConcurrentHashMap();

      public InstanceList get(Class type) {
         return (InstanceList)this.data.get(type);
      }

      public void write(Object value) throws Exception {
         InstanceList<T> list = (InstanceList)this.data.computeIfAbsent(value.getClass(), InstanceList::new);
         list.put(value);
      }

      public void clear() {
         this.data.clear();
      }
   }

   private static class NaturalKeys extends ConcurrentHashMap {
   }

   private static class InstanceList {
      private final KVTypeInfo ti;
      private final KVTypeInfo.Accessor naturalKey;
      private final ConcurrentMap data;
      private final String naturalParentIndexName;
      private final Boolean hasNaturalParentIndex;
      private final ConcurrentMap parentToChildrenMap;

      private InstanceList(Class klass) {
         this.ti = new KVTypeInfo(klass);
         this.naturalKey = this.ti.getAccessor("__main__");
         this.data = new ConcurrentHashMap();
         this.naturalParentIndexName = this.ti.getParentIndexName("__main__");
         this.parentToChildrenMap = new ConcurrentHashMap();
         this.hasNaturalParentIndex = !this.naturalParentIndexName.isEmpty();
      }

      KVTypeInfo.Accessor getIndexAccessor(String indexName) {
         return this.ti.getAccessor(indexName);
      }

      int countingRemoveAllByIndexValues(String index, Collection indexValues) {
         int count = 0;
         if ("__main__".equals(index)) {
            for(Object naturalKey : indexValues) {
               count += this.delete(InMemoryStore.asKey(naturalKey)) ? 1 : 0;
            }

            return count;
         } else if (this.hasNaturalParentIndex && this.naturalParentIndexName.equals(index)) {
            for(Object indexValue : indexValues) {
               Comparable<Object> parentKey = InMemoryStore.asKey(indexValue);
               NaturalKeys children = (NaturalKeys)this.parentToChildrenMap.getOrDefault(parentKey, new NaturalKeys());

               for(Comparable naturalKey : children.keySet()) {
                  this.data.remove(naturalKey);
                  ++count;
               }

               this.parentToChildrenMap.remove(parentKey);
            }

            return count;
         } else {
            Predicate<? super T> filter = getPredicate(this.ti.getAccessor(index), indexValues);
            CountingRemoveIfForEach<T> callback = new CountingRemoveIfForEach(this, filter);
            this.data.forEach(callback);
            return callback.count();
         }
      }

      public Object get(Object key) {
         return this.data.get(InMemoryStore.asKey(key));
      }

      public void put(Object value) throws Exception {
         this.data.put(InMemoryStore.asKey(this.naturalKey.get(value)), value);
         if (this.hasNaturalParentIndex) {
            Comparable<Object> parentKey = InMemoryStore.asKey(this.getIndexAccessor(this.naturalParentIndexName).get(value));
            NaturalKeys children = (NaturalKeys)this.parentToChildrenMap.computeIfAbsent(parentKey, (k) -> new NaturalKeys());
            children.put(InMemoryStore.asKey(this.naturalKey.get(value)), true);
         }

      }

      public boolean delete(Object key) {
         boolean entryExists = this.data.remove(InMemoryStore.asKey(key)) != null;
         if (entryExists) {
            this.deleteParentIndex(key);
         }

         return entryExists;
      }

      public boolean delete(Object key, Object value) {
         boolean entryExists = this.data.remove(InMemoryStore.asKey(key), value);
         if (entryExists) {
            this.deleteParentIndex(key);
         }

         return entryExists;
      }

      private void deleteParentIndex(Object key) {
         if (this.hasNaturalParentIndex) {
            for(NaturalKeys v : this.parentToChildrenMap.values()) {
               if (v.remove(InMemoryStore.asKey(key)) != null) {
                  break;
               }
            }
         }

      }

      public int size() {
         return this.data.size();
      }

      public InMemoryView view() {
         return new InMemoryView(this.data, this.ti, this.naturalParentIndexName, this.parentToChildrenMap);
      }

      private static Predicate getPredicate(KVTypeInfo.Accessor getter, Collection values) {
         if (Comparable.class.isAssignableFrom(getter.getType())) {
            HashSet<?> set = new HashSet(values);
            return (value) -> set.contains(indexValueForEntity(getter, value));
         } else {
            HashSet<Comparable<?>> set = new HashSet(values.size());

            for(Object key : values) {
               set.add(InMemoryStore.asKey(key));
            }

            return (value) -> set.contains(InMemoryStore.asKey(indexValueForEntity(getter, value)));
         }
      }

      private static Object indexValueForEntity(KVTypeInfo.Accessor getter, Object entity) {
         try {
            return getter.get(entity);
         } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
         }
      }

      private static class CountingRemoveIfForEach implements BiConsumer {
         private final InstanceList instanceList;
         private final Predicate filter;
         private int count = 0;

         CountingRemoveIfForEach(InstanceList instanceList, Predicate filter) {
            this.instanceList = instanceList;
            this.filter = filter;
         }

         public void accept(Comparable key, Object value) {
            if (this.filter.test(value) && this.instanceList.delete(key, value)) {
               ++this.count;
            }

         }

         public int count() {
            return this.count;
         }
      }
   }

   private static class InMemoryView extends KVStoreView {
      private static final InMemoryView EMPTY_VIEW = new InMemoryView(new ConcurrentHashMap(), (KVTypeInfo)null, "", new ConcurrentHashMap());
      private final ConcurrentMap data;
      private final KVTypeInfo ti;
      private final KVTypeInfo.Accessor natural;
      private final ConcurrentMap parentToChildrenMap;
      private final String naturalParentIndexName;
      private final Boolean hasNaturalParentIndex;

      InMemoryView(ConcurrentMap data, KVTypeInfo ti, String naturalParentIndexName, ConcurrentMap parentToChildrenMap) {
         this.data = data;
         this.ti = ti;
         this.natural = ti != null ? ti.getAccessor("__main__") : null;
         this.naturalParentIndexName = naturalParentIndexName;
         this.parentToChildrenMap = parentToChildrenMap;
         this.hasNaturalParentIndex = !naturalParentIndexName.isEmpty();
      }

      public Iterator iterator() {
         if (this.data.isEmpty()) {
            return new InMemoryIterator(Collections.emptyIterator());
         } else {
            KVTypeInfo.Accessor getter = this.index != null ? this.ti.getAccessor(this.index) : null;
            int modifier = this.ascending ? 1 : -1;
            List<T> sorted = this.copyElements();
            sorted.sort((e1, e2) -> modifier * this.compare(e1, e2, getter));
            Stream<T> stream = sorted.stream();
            if (this.first != null) {
               Comparable<?> firstKey = InMemoryStore.asKey(this.first);
               stream = stream.filter((e) -> modifier * this.compare(e, getter, firstKey) >= 0);
            }

            if (this.last != null) {
               Comparable<?> lastKey = InMemoryStore.asKey(this.last);
               stream = stream.filter((e) -> modifier * this.compare(e, getter, lastKey) <= 0);
            }

            if (this.skip > 0L) {
               stream = stream.skip(this.skip);
            }

            if (this.max < (long)sorted.size()) {
               stream = stream.limit((long)((int)this.max));
            }

            return new InMemoryIterator(stream.iterator());
         }
      }

      private List copyElements() {
         if (this.parent == null) {
            return new ArrayList(this.data.values());
         } else {
            Comparable<Object> parentKey = InMemoryStore.asKey(this.parent);
            if (this.hasNaturalParentIndex && this.naturalParentIndexName.equals(this.ti.getParentIndexName(this.index))) {
               NaturalKeys children = (NaturalKeys)this.parentToChildrenMap.getOrDefault(parentKey, new NaturalKeys());
               ArrayList<T> elements = new ArrayList();

               for(Comparable naturalKey : children.keySet()) {
                  this.data.computeIfPresent(naturalKey, (k, v) -> {
                     elements.add(v);
                     return v;
                  });
               }

               return elements;
            } else {
               KVTypeInfo.Accessor parentGetter = this.ti.getParentAccessor(this.index);
               Preconditions.checkArgument(parentGetter != null, "Parent filter for non-child index.");
               return (List)this.data.values().stream().filter((e) -> this.compare(e, parentGetter, parentKey) == 0).collect(Collectors.toList());
            }
         }
      }

      private int compare(Object e1, Object e2, KVTypeInfo.Accessor getter) {
         try {
            int diff = this.compare(e1, getter, InMemoryStore.asKey(getter.get(e2)));
            if (diff == 0 && getter != this.natural) {
               diff = this.compare(e1, this.natural, InMemoryStore.asKey(this.natural.get(e2)));
            }

            return diff;
         } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
         }
      }

      private int compare(Object e1, KVTypeInfo.Accessor getter, Comparable v2) {
         try {
            return InMemoryStore.asKey(getter.get(e1)).compareTo(v2);
         } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
         }
      }
   }

   private static class InMemoryIterator implements KVStoreIterator {
      private final Iterator iter;

      InMemoryIterator(Iterator iter) {
         this.iter = iter;
      }

      public boolean hasNext() {
         return this.iter.hasNext();
      }

      public Object next() {
         return this.iter.next();
      }

      public List next(int max) {
         List<T> list = new ArrayList(max);

         while(this.hasNext() && list.size() < max) {
            list.add(this.next());
         }

         return list;
      }

      public boolean skip(long n) {
         for(long skipped = 0L; skipped < n; ++skipped) {
            if (!this.hasNext()) {
               return false;
            }

            this.next();
         }

         return this.hasNext();
      }

      public void close() {
      }
   }
}
