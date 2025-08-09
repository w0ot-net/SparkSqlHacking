package org.apache.curator.shaded.com.google.common.collect;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface FilteredSetMultimap extends FilteredMultimap, SetMultimap {
   SetMultimap unfiltered();
}
