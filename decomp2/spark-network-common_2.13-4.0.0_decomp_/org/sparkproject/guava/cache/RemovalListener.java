package org.sparkproject.guava.cache;

import org.sparkproject.guava.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface RemovalListener {
   void onRemoval(RemovalNotification notification);
}
