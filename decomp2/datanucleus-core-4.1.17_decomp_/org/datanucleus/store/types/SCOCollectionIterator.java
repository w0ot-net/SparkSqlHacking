package org.datanucleus.store.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.CollectionStore;

public class SCOCollectionIterator implements Iterator {
   private final Iterator iter;
   private Object last = null;
   private Collection ownerSCO;

   public SCOCollectionIterator(Collection sco, ObjectProvider op, Collection theDelegate, CollectionStore backingStore, boolean useDelegate) {
      this.ownerSCO = sco;
      List entries = new ArrayList();
      Iterator i = null;
      if (useDelegate) {
         i = theDelegate.iterator();
      } else if (backingStore != null) {
         i = backingStore.iterator(op);
      } else {
         i = theDelegate.iterator();
      }

      while(i.hasNext()) {
         entries.add(i.next());
      }

      this.iter = entries.iterator();
   }

   public boolean hasNext() {
      return this.iter.hasNext();
   }

   public Object next() {
      this.last = this.iter.next();
      return this.last;
   }

   public void remove() {
      if (this.last == null) {
         throw new IllegalStateException();
      } else {
         this.ownerSCO.remove(this.last);
         this.last = null;
      }
   }
}
