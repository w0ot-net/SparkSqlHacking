package org.apache.curator.shaded.com.google.common.collect;

import java.util.SortedSet;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
interface SortedMultisetBridge extends Multiset {
   SortedSet elementSet();
}
