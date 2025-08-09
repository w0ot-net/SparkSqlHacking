package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface RemovalListener {
   void onRemoval(RemovalNotification notification);
}
