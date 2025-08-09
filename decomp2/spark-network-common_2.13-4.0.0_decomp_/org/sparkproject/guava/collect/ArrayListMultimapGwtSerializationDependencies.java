package org.sparkproject.guava.collect;

import java.util.Collection;
import java.util.Map;
import org.sparkproject.guava.annotations.GwtCompatible;

@GwtCompatible(
   emulated = true
)
abstract class ArrayListMultimapGwtSerializationDependencies extends AbstractListMultimap {
   ArrayListMultimapGwtSerializationDependencies(Map map) {
      super(map);
   }
}
