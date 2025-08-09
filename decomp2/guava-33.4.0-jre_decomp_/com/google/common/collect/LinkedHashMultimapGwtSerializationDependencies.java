package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;

@GwtCompatible(
   emulated = true
)
abstract class LinkedHashMultimapGwtSerializationDependencies extends AbstractSetMultimap {
   LinkedHashMultimapGwtSerializationDependencies(Map map) {
      super(map);
   }
}
