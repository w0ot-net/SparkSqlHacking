package org.apache.curator.shaded.com.google.common.util.concurrent;

import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMultimap;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
interface ServiceManagerBridge {
   ImmutableMultimap servicesByState();
}
