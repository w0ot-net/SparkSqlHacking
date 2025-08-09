package org.glassfish.jersey.internal.inject;

import java.util.function.Supplier;

public interface DisposableSupplier extends Supplier {
   void dispose(Object var1);
}
