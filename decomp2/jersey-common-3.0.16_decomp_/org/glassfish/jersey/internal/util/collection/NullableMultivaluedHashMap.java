package org.glassfish.jersey.internal.util.collection;

import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.List;

public class NullableMultivaluedHashMap extends MultivaluedHashMap {
   public NullableMultivaluedHashMap() {
   }

   public NullableMultivaluedHashMap(int initialCapacity) {
      super(initialCapacity);
   }

   public NullableMultivaluedHashMap(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
   }

   public NullableMultivaluedHashMap(MultivaluedMap map) {
      super(map);
   }

   protected void addFirstNull(List values) {
      values.add((Object)null);
   }

   protected void addNull(List values) {
      values.add((Object)null);
   }
}
