package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;

@GwtCompatible(
   emulated = true
)
abstract class ArrayListMultimapGwtSerializationDependencies extends AbstractListMultimap {
   ArrayListMultimapGwtSerializationDependencies(Map map) {
      super(map);
   }
}
