package org.apache.commons.collections.list;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections.collection.SynchronizedCollection;

public class SynchronizedList extends SynchronizedCollection implements List {
   private static final long serialVersionUID = -1403835447328619437L;

   public static List decorate(List list) {
      return new SynchronizedList(list);
   }

   protected SynchronizedList(List list) {
      super(list);
   }

   protected SynchronizedList(List list, Object lock) {
      super(list, lock);
   }

   protected List getList() {
      return (List)this.collection;
   }

   public void add(int index, Object object) {
      synchronized(this.lock) {
         this.getList().add(index, object);
      }
   }

   public boolean addAll(int index, Collection coll) {
      synchronized(this.lock) {
         return this.getList().addAll(index, coll);
      }
   }

   public Object get(int index) {
      synchronized(this.lock) {
         return this.getList().get(index);
      }
   }

   public int indexOf(Object object) {
      synchronized(this.lock) {
         return this.getList().indexOf(object);
      }
   }

   public int lastIndexOf(Object object) {
      synchronized(this.lock) {
         return this.getList().lastIndexOf(object);
      }
   }

   public ListIterator listIterator() {
      return this.getList().listIterator();
   }

   public ListIterator listIterator(int index) {
      return this.getList().listIterator(index);
   }

   public Object remove(int index) {
      synchronized(this.lock) {
         return this.getList().remove(index);
      }
   }

   public Object set(int index, Object object) {
      synchronized(this.lock) {
         return this.getList().set(index, object);
      }
   }

   public List subList(int fromIndex, int toIndex) {
      synchronized(this.lock) {
         List list = this.getList().subList(fromIndex, toIndex);
         return new SynchronizedList(list, this.lock);
      }
   }
}
