package org.sparkproject.guava.util.concurrent;

import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.collect.ImmutableMultimap;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
interface ServiceManagerBridge {
   ImmutableMultimap servicesByState();
}
