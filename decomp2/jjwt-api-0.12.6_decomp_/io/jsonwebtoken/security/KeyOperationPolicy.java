package io.jsonwebtoken.security;

import java.util.Collection;

public interface KeyOperationPolicy {
   Collection getOperations();

   void validate(Collection var1) throws IllegalArgumentException;
}
