package org.datanucleus.store.query;

import java.util.ListIterator;
import org.datanucleus.util.Localiser;

public abstract class AbstractQueryResultIterator implements ListIterator {
   public void add(Object arg0) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public abstract boolean hasNext();

   public abstract boolean hasPrevious();

   public abstract Object next();

   public abstract int nextIndex();

   public abstract Object previous();

   public abstract int previousIndex();

   public void remove() {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public void set(Object arg0) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }
}
