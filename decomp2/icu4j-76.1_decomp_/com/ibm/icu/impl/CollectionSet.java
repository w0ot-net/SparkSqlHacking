package com.ibm.icu.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class CollectionSet implements Set {
   private final Collection data;

   public CollectionSet(Collection data) {
      this.data = data;
   }

   public int size() {
      return this.data.size();
   }

   public boolean isEmpty() {
      return this.data.isEmpty();
   }

   public boolean contains(Object o) {
      return this.data.contains(o);
   }

   public Iterator iterator() {
      return this.data.iterator();
   }

   public Object[] toArray() {
      return this.data.toArray();
   }

   public Object[] toArray(Object[] a) {
      return this.data.toArray(a);
   }

   public boolean add(Object e) {
      return this.data.add(e);
   }

   public boolean remove(Object o) {
      return this.data.remove(o);
   }

   public boolean containsAll(Collection c) {
      return this.data.containsAll(c);
   }

   public boolean addAll(Collection c) {
      return this.data.addAll(c);
   }

   public boolean retainAll(Collection c) {
      return this.data.retainAll(c);
   }

   public boolean removeAll(Collection c) {
      return this.data.removeAll(c);
   }

   public void clear() {
      this.data.clear();
   }
}
