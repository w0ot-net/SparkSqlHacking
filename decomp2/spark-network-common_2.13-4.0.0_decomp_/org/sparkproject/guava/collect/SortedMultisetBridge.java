package org.sparkproject.guava.collect;

import java.util.SortedSet;
import org.sparkproject.guava.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
interface SortedMultisetBridge extends Multiset {
   SortedSet elementSet();
}
