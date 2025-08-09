package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractSortedKeySortedSetMultimap extends AbstractSortedSetMultimap {
   AbstractSortedKeySortedSetMultimap(SortedMap map) {
      super(map);
   }

   public SortedMap asMap() {
      return (SortedMap)super.asMap();
   }

   SortedMap backingMap() {
      return (SortedMap)super.backingMap();
   }

   public SortedSet keySet() {
      return (SortedSet)super.keySet();
   }

   Set createKeySet() {
      return this.createMaybeNavigableKeySet();
   }
}
