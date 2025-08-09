package org.glassfish.jersey.internal.guava;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

abstract class AbstractSetMultimap extends AbstractMapBasedMultimap implements SetMultimap {
   private static final long serialVersionUID = 7431625294878419160L;

   AbstractSetMultimap(Map map) {
      super(map);
   }

   abstract Set createCollection();

   Set createUnmodifiableEmptyCollection() {
      return Collections.emptySet();
   }

   public Set get(Object key) {
      return (Set)super.get(key);
   }

   public Set entries() {
      return (Set)super.entries();
   }

   public Set removeAll(Object key) {
      return (Set)super.removeAll(key);
   }

   public Map asMap() {
      return super.asMap();
   }

   public boolean put(Object key, Object value) {
      return super.put(key, value);
   }

   public boolean equals(Object object) {
      return super.equals(object);
   }
}
