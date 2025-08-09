package jakarta.ws.rs.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMultivaluedMap implements MultivaluedMap, Serializable {
   protected final Map store;

   public AbstractMultivaluedMap(Map store) {
      if (store == null) {
         throw new NullPointerException("Underlying store must not be 'null'.");
      } else {
         this.store = store;
      }
   }

   public final void putSingle(Object key, Object value) {
      List<V> values = this.getValues(key);
      values.clear();
      if (value != null) {
         values.add(value);
      } else {
         this.addNull(values);
      }

   }

   protected void addNull(List values) {
   }

   protected void addFirstNull(List values) {
   }

   public final void add(Object key, Object value) {
      List<V> values = this.getValues(key);
      if (value != null) {
         values.add(value);
      } else {
         this.addNull(values);
      }

   }

   public final void addAll(Object key, Object... newValues) {
      if (newValues == null) {
         throw new NullPointerException("Supplied array of values must not be null.");
      } else if (newValues.length != 0) {
         List<V> values = this.getValues(key);

         for(Object value : newValues) {
            if (value != null) {
               values.add(value);
            } else {
               this.addNull(values);
            }
         }

      }
   }

   public final void addAll(Object key, List valueList) {
      if (valueList == null) {
         throw new NullPointerException("Supplied list of values must not be null.");
      } else if (!valueList.isEmpty()) {
         List<V> values = this.getValues(key);

         for(Object value : valueList) {
            if (value != null) {
               values.add(value);
            } else {
               this.addNull(values);
            }
         }

      }
   }

   public final Object getFirst(Object key) {
      List<V> values = (List)this.store.get(key);
      return values != null && values.size() > 0 ? values.get(0) : null;
   }

   public final void addFirst(Object key, Object value) {
      List<V> values = this.getValues(key);
      if (value != null) {
         values.add(0, value);
      } else {
         this.addFirstNull(values);
      }

   }

   protected final List getValues(Object key) {
      List<V> l = (List)this.store.get(key);
      if (l == null) {
         l = new LinkedList();
         this.store.put(key, l);
      }

      return l;
   }

   public String toString() {
      return this.store.toString();
   }

   public int hashCode() {
      return this.store.hashCode();
   }

   public boolean equals(Object o) {
      return this.store.equals(o);
   }

   public Collection values() {
      return this.store.values();
   }

   public int size() {
      return this.store.size();
   }

   public List remove(Object key) {
      return (List)this.store.remove(key);
   }

   public void putAll(Map m) {
      this.store.putAll(m);
   }

   public List put(Object key, List value) {
      return (List)this.store.put(key, value);
   }

   public Set keySet() {
      return this.store.keySet();
   }

   public boolean isEmpty() {
      return this.store.isEmpty();
   }

   public List get(Object key) {
      return (List)this.store.get(key);
   }

   public Set entrySet() {
      return this.store.entrySet();
   }

   public boolean containsValue(Object value) {
      return this.store.containsValue(value);
   }

   public boolean containsKey(Object key) {
      return this.store.containsKey(key);
   }

   public void clear() {
      this.store.clear();
   }

   public boolean equalsIgnoreValueOrder(MultivaluedMap omap) {
      if (this == omap) {
         return true;
      } else if (!this.keySet().equals(omap.keySet())) {
         return false;
      } else {
         for(Map.Entry e : this.entrySet()) {
            List<V> olist = (List)omap.get(e.getKey());
            if (((List)e.getValue()).size() != olist.size()) {
               return false;
            }

            for(Object v : (List)e.getValue()) {
               if (!olist.contains(v)) {
                  return false;
               }
            }
         }

         return true;
      }
   }
}
