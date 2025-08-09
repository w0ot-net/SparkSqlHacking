package io.jsonwebtoken.impl.lang;

public interface CheckedSupplier {
   Object get() throws Exception;
}
