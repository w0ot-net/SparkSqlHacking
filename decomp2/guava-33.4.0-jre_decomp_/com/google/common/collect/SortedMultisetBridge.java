package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.SortedSet;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
interface SortedMultisetBridge extends Multiset {
   SortedSet elementSet();
}
