package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@GwtCompatible(
   emulated = true
)
abstract class HashMultimapGwtSerializationDependencies extends AbstractSetMultimap {
   HashMultimapGwtSerializationDependencies(Map map) {
      super(map);
   }
}
