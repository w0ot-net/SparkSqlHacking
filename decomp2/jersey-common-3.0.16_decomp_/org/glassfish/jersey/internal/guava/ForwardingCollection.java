package org.glassfish.jersey.internal.guava;

import java.util.Collection;
import java.util.Iterator;

public abstract class ForwardingCollection extends ForwardingObject implements Collection {
   ForwardingCollection() {
   }

   protected abstract Collection delegate();

   public Iterator iterator() {
      return this.delegate().iterator();
   }

   public int size() {
      return this.delegate().size();
   }

   public boolean removeAll(Collection collection) {
      return this.delegate().removeAll(collection);
   }

   public boolean isEmpty() {
      return this.delegate().isEmpty();
   }

   public boolean contains(Object object) {
      return this.delegate().contains(object);
   }

   public boolean add(Object element) {
      return this.delegate().add(element);
   }

   public boolean remove(Object object) {
      return this.delegate().remove(object);
   }

   public boolean containsAll(Collection collection) {
      return this.delegate().containsAll(collection);
   }

   public boolean addAll(Collection collection) {
      return this.delegate().addAll(collection);
   }

   public boolean retainAll(Collection collection) {
      return this.delegate().retainAll(collection);
   }

   public void clear() {
      this.delegate().clear();
   }

   public Object[] toArray() {
      return this.delegate().toArray();
   }

   public Object[] toArray(Object[] array) {
      return this.delegate().toArray(array);
   }
}
