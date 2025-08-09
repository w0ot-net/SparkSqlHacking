package org.sparkproject.guava.collect;

import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class ForwardingImmutableMap {
   private ForwardingImmutableMap() {
   }
}
