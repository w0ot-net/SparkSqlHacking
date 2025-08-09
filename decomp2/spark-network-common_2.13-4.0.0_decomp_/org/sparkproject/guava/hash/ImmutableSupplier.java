package org.sparkproject.guava.hash;

import com.google.errorprone.annotations.Immutable;
import org.sparkproject.guava.base.Supplier;

@Immutable
@ElementTypesAreNonnullByDefault
interface ImmutableSupplier extends Supplier {
}
