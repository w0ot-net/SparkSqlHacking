package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Weigher {
   int weigh(Object key, Object value);
}
