package io.jsonwebtoken.lang;

import java.util.Collection;

public interface CollectionMutator {
   CollectionMutator add(Object var1);

   CollectionMutator add(Collection var1);

   CollectionMutator clear();

   CollectionMutator remove(Object var1);
}
