package org.sparkproject.guava.collect;

import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Predicate;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface FilteredMultimap extends Multimap {
   Multimap unfiltered();

   Predicate entryPredicate();
}
