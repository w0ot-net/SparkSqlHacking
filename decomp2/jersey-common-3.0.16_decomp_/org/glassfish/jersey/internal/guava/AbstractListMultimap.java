package org.glassfish.jersey.internal.guava;

import java.util.Collection;
import java.util.List;
import java.util.Map;

abstract class AbstractListMultimap extends AbstractMapBasedMultimap implements ListMultimap {
   private static final long serialVersionUID = 6588350623831699109L;

   AbstractListMultimap(Map map) {
      super(map);
   }

   abstract List createCollection();

   public List get(Object key) {
      return (List)super.get(key);
   }

   public List removeAll(Object key) {
      return (List)super.removeAll(key);
   }

   public boolean put(Object key, Object value) {
      return super.put(key, value);
   }

   public Map asMap() {
      return super.asMap();
   }

   public boolean equals(Object object) {
      return super.equals(object);
   }
}
