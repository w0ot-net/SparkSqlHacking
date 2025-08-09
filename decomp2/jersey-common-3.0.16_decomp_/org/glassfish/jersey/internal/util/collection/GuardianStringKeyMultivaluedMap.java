package org.glassfish.jersey.internal.util.collection;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class GuardianStringKeyMultivaluedMap implements MultivaluedMap {
   private final MultivaluedMap inner;
   private final Map guards = new HashMap();

   private static boolean isMutable(Object mutable) {
      return !String.class.isInstance(mutable) && !MediaType.class.isInstance(mutable);
   }

   public GuardianStringKeyMultivaluedMap(MultivaluedMap inner) {
      this.inner = inner;
   }

   public void putSingle(String key, Object value) {
      this.observe(key);
      this.inner.putSingle(key, value);
   }

   public void add(String key, Object value) {
      this.observe(key);
      this.inner.add(key, value);
   }

   public Object getFirst(String key) {
      V first = (V)this.inner.getFirst(key);
      if (isMutable(key)) {
         this.observe(key);
      }

      return first;
   }

   public void addAll(String key, Object... newValues) {
      this.observe(key);
      this.inner.addAll(key, newValues);
   }

   public void addAll(String key, List valueList) {
      this.observe(key);
      this.inner.addAll(key, valueList);
   }

   public void addFirst(String key, Object value) {
      this.observe(key);
      this.inner.addFirst(key, value);
   }

   public boolean equalsIgnoreValueOrder(MultivaluedMap otherMap) {
      return this.inner.equalsIgnoreValueOrder(otherMap);
   }

   public int size() {
      return this.inner.size();
   }

   public boolean isEmpty() {
      return this.inner.isEmpty();
   }

   public boolean containsKey(Object key) {
      return this.inner.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.inner.containsValue(value);
   }

   public List get(Object key) {
      List<V> innerList = (List)this.inner.get(key);
      if (innerList != null) {
         for(Map.Entry guard : this.guards.entrySet()) {
            if (((String)guard.getKey()).equals(key)) {
               return new GuardianList(innerList, guard);
            }
         }
      }

      return innerList;
   }

   public List put(String key, List value) {
      this.observe(key);
      return (List)this.inner.put(key, value);
   }

   public List remove(Object key) {
      if (key != null) {
         this.observe(key.toString());
      }

      return (List)this.inner.remove(key);
   }

   public void putAll(Map m) {
      for(String key : m.keySet()) {
         this.observe(key);
      }

      this.inner.putAll(m);
   }

   public void clear() {
      this.observeAll();
      this.inner.clear();
   }

   public Set keySet() {
      return this.inner.keySet();
   }

   public Collection values() {
      this.observeAll();
      return this.inner.values();
   }

   public Set entrySet() {
      this.observeAll();
      return this.inner.entrySet();
   }

   public void setGuard(String key) {
      this.guards.put(key, false);
   }

   public Set getGuards() {
      return this.guards.keySet();
   }

   public boolean isObservedAndReset(String key) {
      Boolean observed = (Boolean)this.guards.get(key);
      if (observed != null) {
         this.guards.put(key, false);
      }

      return observed != null && observed;
   }

   private void observe(String key) {
      for(Map.Entry guard : this.guards.entrySet()) {
         if (((String)guard.getKey()).equals(key)) {
            guard.setValue(true);
            break;
         }
      }

   }

   private void observeAll() {
      for(Map.Entry guard : this.guards.entrySet()) {
         guard.setValue(true);
      }

   }

   public String toString() {
      return this.inner.toString();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         GuardianStringKeyMultivaluedMap<?> that = (GuardianStringKeyMultivaluedMap)o;
         return this.inner.equals(that.inner) && this.guards.equals(that.guards);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.inner, this.guards});
   }

   private static class MutableGuardian {
      protected final Map.Entry guard;

      private MutableGuardian(Map.Entry guard) {
         this.guard = guard;
      }

      protected Object guardMutable(Object mutable) {
         if (GuardianStringKeyMultivaluedMap.isMutable(mutable)) {
            this.guard.setValue(true);
         }

         return mutable;
      }
   }

   private static class GuardianList extends MutableGuardian implements List {
      private final List guarded;

      public GuardianList(List guarded, Map.Entry guard) {
         super(guard, null);
         this.guarded = guarded;
      }

      public int size() {
         return this.guarded.size();
      }

      public boolean isEmpty() {
         return this.guarded.isEmpty();
      }

      public boolean contains(Object o) {
         return this.guarded.contains(o);
      }

      public Iterator iterator() {
         return new GuardianIterator(this.guarded.iterator(), this.guard);
      }

      public Object[] toArray() {
         this.guard.setValue(true);
         return this.guarded.toArray();
      }

      public Object[] toArray(Object[] a) {
         this.guard.setValue(true);
         return this.guarded.toArray(a);
      }

      public boolean add(Object e) {
         this.guard.setValue(true);
         return this.guarded.add(e);
      }

      public boolean remove(Object o) {
         this.guard.setValue(true);
         return this.guarded.remove(o);
      }

      public boolean containsAll(Collection c) {
         return this.guarded.containsAll(c);
      }

      public boolean addAll(Collection c) {
         this.guard.setValue(true);
         return this.guarded.addAll(c);
      }

      public boolean addAll(int index, Collection c) {
         this.guard.setValue(true);
         return this.guarded.addAll(index, c);
      }

      public boolean removeAll(Collection c) {
         this.guard.setValue(true);
         return this.guarded.removeAll(c);
      }

      public boolean retainAll(Collection c) {
         this.guard.setValue(true);
         return this.guarded.retainAll(c);
      }

      public void clear() {
         this.guard.setValue(true);
         this.guarded.clear();
      }

      public Object get(int index) {
         return this.guardMutable(this.guarded.get(index));
      }

      public Object set(int index, Object element) {
         this.guard.setValue(true);
         return this.guarded.set(index, element);
      }

      public void add(int index, Object element) {
         this.guard.setValue(true);
         this.guarded.add(index, element);
      }

      public Object remove(int index) {
         this.guard.setValue(true);
         return this.guarded.remove(index);
      }

      public int indexOf(Object o) {
         return this.guarded.indexOf(o);
      }

      public int lastIndexOf(Object o) {
         return this.guarded.lastIndexOf(o);
      }

      public ListIterator listIterator() {
         return new GuardianListIterator(this.guarded.listIterator(), this.guard);
      }

      public ListIterator listIterator(int index) {
         return new GuardianListIterator(this.guarded.listIterator(index), this.guard);
      }

      public List subList(int fromIndex, int toIndex) {
         List<V> sublist = this.guarded.subList(fromIndex, toIndex);
         return (List)(sublist != null ? new GuardianList(sublist, this.guard) : sublist);
      }

      public String toString() {
         return this.guarded.toString();
      }

      public boolean equals(Object obj) {
         return GuardianList.class.isInstance(obj) ? this.guarded.equals(((GuardianList)obj).guarded) : this.guarded.equals(obj);
      }

      public int hashCode() {
         return this.guarded.hashCode();
      }
   }

   private static class GuardianIterator extends MutableGuardian implements Iterator {
      protected final Iterator guarded;

      public GuardianIterator(Iterator guarded, Map.Entry guard) {
         super(guard, null);
         this.guarded = guarded;
      }

      public boolean hasNext() {
         return this.guarded.hasNext();
      }

      public Object next() {
         return this.guardMutable(this.guarded.next());
      }

      public void remove() {
         this.guard.setValue(true);
         this.guarded.remove();
      }

      public void forEachRemaining(Consumer action) {
         this.guarded.forEachRemaining(action);
      }

      public String toString() {
         return this.guarded.toString();
      }
   }

   private static class GuardianListIterator extends GuardianIterator implements ListIterator {
      public GuardianListIterator(Iterator guarded, Map.Entry guard) {
         super(guarded, guard);
      }

      public boolean hasPrevious() {
         return ((ListIterator)this.guarded).hasPrevious();
      }

      public Object previous() {
         return this.guardMutable(((ListIterator)this.guarded).previous());
      }

      public int nextIndex() {
         return ((ListIterator)this.guarded).nextIndex();
      }

      public int previousIndex() {
         return ((ListIterator)this.guarded).previousIndex();
      }

      public void set(Object v) {
         ((ListIterator)this.guarded).set(v);
         this.guard.setValue(true);
      }

      public void add(Object v) {
         ((ListIterator)this.guarded).add(v);
         this.guard.setValue(true);
      }
   }
}
