package org.datanucleus.store.scostore;

import org.datanucleus.state.ObjectProvider;

public interface PersistableRelationStore extends Store {
   boolean add(ObjectProvider var1, ObjectProvider var2);

   boolean remove(ObjectProvider var1);

   boolean update(ObjectProvider var1, ObjectProvider var2);
}
