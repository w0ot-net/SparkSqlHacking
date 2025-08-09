package io.jsonwebtoken.impl;

import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.impl.lang.DefaultNestedCollection;
import java.util.Collection;

abstract class AbstractAudienceCollection extends DefaultNestedCollection implements ClaimsMutator.AudienceCollection {
   protected AbstractAudienceCollection(Object parent, Collection seed) {
      super(parent, seed);
   }
}
