package org.glassfish.jersey.internal.guava;

import java.util.List;
import java.util.Map;

public interface ListMultimap extends Multimap {
   List get(Object var1);

   List removeAll(Object var1);

   Map asMap();

   boolean equals(Object var1);
}
