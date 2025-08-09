package org.glassfish.jersey.internal.util.collection;

import java.util.function.Supplier;

public interface Value extends Supplier {
   Object get();
}
