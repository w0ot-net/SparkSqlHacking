package org.apache.curator.shaded.com.google.common.base;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Supplier extends java.util.function.Supplier {
   @ParametricNullness
   Object get();
}
