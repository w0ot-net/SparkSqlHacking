package org.sparkproject.guava.base;

import org.sparkproject.guava.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Supplier extends java.util.function.Supplier {
   @ParametricNullness
   Object get();
}
