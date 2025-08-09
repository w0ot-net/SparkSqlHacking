package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Supplier extends java.util.function.Supplier {
   @ParametricNullness
   Object get();
}
