package org.glassfish.jersey.internal.util.collection;

import jakarta.ws.rs.core.AbstractMultivaluedMap;

public class StringKeyIgnoreCaseMultivaluedMap extends AbstractMultivaluedMap {
   public StringKeyIgnoreCaseMultivaluedMap() {
      super(new KeyComparatorLinkedHashMap(StringIgnoreCaseKeyComparator.SINGLETON));
   }
}
