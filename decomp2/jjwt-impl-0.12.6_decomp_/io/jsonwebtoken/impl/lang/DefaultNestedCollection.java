package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.NestedCollection;
import java.util.Collection;

public class DefaultNestedCollection extends DefaultCollectionMutator implements NestedCollection {
   private final Object parent;

   public DefaultNestedCollection(Object parent, Collection seed) {
      super(seed);
      this.parent = Assert.notNull(parent, "Parent cannot be null.");
   }

   public Object and() {
      return this.parent;
   }
}
