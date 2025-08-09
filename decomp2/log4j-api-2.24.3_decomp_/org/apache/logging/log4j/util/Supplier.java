package org.apache.logging.log4j.util;

@FunctionalInterface
@InternalApi
public interface Supplier extends java.util.function.Supplier {
   Object get();
}
