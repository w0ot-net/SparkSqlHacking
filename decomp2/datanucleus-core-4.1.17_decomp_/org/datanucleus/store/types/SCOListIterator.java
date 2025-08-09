package org.datanucleus.store.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.ListStore;

public class SCOListIterator implements ListIterator {
   private final ListIterator iter;
   private final List ownerSCO;
   private boolean reverse;

   public SCOListIterator(List sco, ObjectProvider sm, List theDelegate, ListStore theStore, boolean useDelegate, int startIndex) {
      this.ownerSCO = sco;
      List<E> entries = new ArrayList();
      Iterator<E> i = null;
      if (useDelegate) {
         i = theDelegate.iterator();
      } else if (theStore != null) {
         i = theStore.iterator(sm);
      } else {
         i = theDelegate.iterator();
      }

      while(i.hasNext()) {
         entries.add(i.next());
      }

      if (startIndex >= 0) {
         this.iter = entries.listIterator(startIndex);
      } else {
         this.iter = entries.listIterator();
      }

   }

   public void add(Object o) {
      this.iter.add(o);
      this.ownerSCO.add(this.iter.previousIndex(), o);
   }

   public boolean hasNext() {
      return this.iter.hasNext();
   }

   public boolean hasPrevious() {
      return this.iter.hasPrevious();
   }

   public Object next() {
      E result = (E)this.iter.next();
      this.reverse = false;
      return result;
   }

   public int nextIndex() {
      return this.iter.nextIndex();
   }

   public Object previous() {
      E result = (E)this.iter.previous();
      this.reverse = true;
      return result;
   }

   public int previousIndex() {
      return this.iter.previousIndex();
   }

   public void remove() {
      this.iter.remove();
      this.ownerSCO.remove(this.iter.nextIndex());
   }

   public void set(Object o) {
      this.iter.set(o);
      this.ownerSCO.set(this.reverse ? this.iter.nextIndex() : this.iter.previousIndex(), o);
   }
}
