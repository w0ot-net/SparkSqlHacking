package org.apache.curator.shaded.com.google.common.collect;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Predicate;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface FilteredMultimap extends Multimap {
   Multimap unfiltered();

   Predicate entryPredicate();
}
