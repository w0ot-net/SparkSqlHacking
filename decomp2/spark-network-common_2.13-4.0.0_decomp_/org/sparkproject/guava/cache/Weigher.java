package org.sparkproject.guava.cache;

import org.sparkproject.guava.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Weigher {
   int weigh(Object key, Object value);
}
