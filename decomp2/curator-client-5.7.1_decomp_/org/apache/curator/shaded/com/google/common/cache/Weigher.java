package org.apache.curator.shaded.com.google.common.cache;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Weigher {
   int weigh(Object key, Object value);
}
