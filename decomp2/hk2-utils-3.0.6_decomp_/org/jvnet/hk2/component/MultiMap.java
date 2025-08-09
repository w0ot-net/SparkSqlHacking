package org.jvnet.hk2.component;

import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMap implements Serializable, Cloneable {
   private static final long serialVersionUID = 893592003056170756L;
   private final Map store;
   private static final String NEWLINE = (String)AccessController.doPrivileged(new PrivilegedAction() {
      public String run() {
         return System.getProperty("line.separator");
      }
   });

   public MultiMap() {
      this.store = new LinkedHashMap();
   }

   public MultiMap(MultiMap base) {
      this();

      for(Map.Entry e : base.entrySet()) {
         List<V> value = this.newList((Collection)e.getValue());
         if (!value.isEmpty()) {
            this.store.put(e.getKey(), this.newList((Collection)e.getValue()));
         }
      }

   }

   private List newList(Collection initialVals) {
      return null == initialVals ? new LinkedList() : new LinkedList(initialVals);
   }

   public Set keySet() {
      return this.store.keySet();
   }

   public final void add(Object k, Object v) {
      List<V> l = (List)this.store.get(k);
      if (l == null) {
         l = this.newList((Collection)null);
         this.store.put(k, l);
      }

      l.add(v);
   }

   public void set(Object k, Collection v) {
      List<V> addMe = this.newList(v);
      if (addMe.isEmpty()) {
         this.store.remove(k);
      } else {
         this.store.put(k, this.newList(v));
      }

   }

   public void set(Object k, Object v) {
      List<V> vlist = this.newList((Collection)null);
      vlist.add(v);
      this.store.put(k, vlist);
   }

   public final List get(Object k) {
      List<V> l = (List)this.store.get(k);
      return l == null ? Collections.emptyList() : Collections.unmodifiableList(l);
   }

   public void mergeAll(MultiMap another) {
      if (another != null) {
         for(Map.Entry entry : another.entrySet()) {
            List<V> ourList = (List)this.store.get(entry.getKey());
            if (null == ourList) {
               ourList = this.newList((Collection)entry.getValue());
               if (!ourList.isEmpty()) {
                  this.store.put(entry.getKey(), ourList);
               }
            } else {
               for(Object v : (List)entry.getValue()) {
                  if (!ourList.contains(v)) {
                     ourList.add(v);
                  }
               }
            }
         }

      }
   }

   private final List _get(Object k) {
      List<V> l = (List)this.store.get(k);
      return l == null ? Collections.emptyList() : l;
   }

   public boolean containsKey(Object k) {
      return !this.get(k).isEmpty();
   }

   public boolean contains(Object k1, Object k2) {
      List<V> list = this._get(k1);
      return list.contains(k2);
   }

   public List remove(Object key) {
      return (List)this.store.remove(key);
   }

   public boolean remove(Object key, Object entry) {
      List<V> list = (List)this.store.get(key);
      if (list == null) {
         return false;
      } else {
         boolean retVal = list.remove(entry);
         if (list.isEmpty()) {
            this.store.remove(key);
         }

         return retVal;
      }
   }

   public Object getOne(Object k) {
      return this.getFirst(k);
   }

   private Object getFirst(Object k) {
      List<V> lst = (List)this.store.get(k);
      if (null == lst) {
         return null;
      } else {
         return lst.isEmpty() ? null : lst.get(0);
      }
   }

   public Set entrySet() {
      return this.store.entrySet();
   }

   public String toCommaSeparatedString() {
      StringBuilder buf = new StringBuilder();

      for(Map.Entry e : this.entrySet()) {
         for(Object v : (List)e.getValue()) {
            if (buf.length() > 0) {
               buf.append(',');
            }

            buf.append(e.getKey()).append('=').append(v);
         }
      }

      return buf.toString();
   }

   public MultiMap clone() throws CloneNotSupportedException {
      super.clone();
      return new MultiMap(this);
   }

   public int size() {
      return this.store.size();
   }

   public int hashCode() {
      return this.store.hashCode();
   }

   public boolean equals(Object another) {
      if (another != null && another instanceof MultiMap) {
         MultiMap<K, V> other = (MultiMap)another;
         return this.store.equals(other.store);
      } else {
         return false;
      }
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("{");

      for(Object key : this.store.keySet()) {
         builder.append(key).append(": ");
         builder.append(this.store.get(key));
         builder.append(NEWLINE);
      }

      builder.append("}");
      return builder.toString();
   }
}
