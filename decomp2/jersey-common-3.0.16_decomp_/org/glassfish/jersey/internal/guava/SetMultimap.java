package org.glassfish.jersey.internal.guava;

import java.util.Map;
import java.util.Set;

public interface SetMultimap extends Multimap {
   Set get(Object var1);

   Set removeAll(Object var1);

   Set entries();

   Map asMap();

   boolean equals(Object var1);
}
