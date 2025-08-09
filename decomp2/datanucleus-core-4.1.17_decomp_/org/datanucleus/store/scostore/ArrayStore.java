package org.datanucleus.store.scostore;

import java.util.Iterator;
import java.util.List;
import org.datanucleus.state.ObjectProvider;

public interface ArrayStore extends Store {
   Iterator iterator(ObjectProvider var1);

   List getArray(ObjectProvider var1);

   int size(ObjectProvider var1);

   void clear(ObjectProvider var1);

   boolean set(ObjectProvider var1, Object var2);
}
