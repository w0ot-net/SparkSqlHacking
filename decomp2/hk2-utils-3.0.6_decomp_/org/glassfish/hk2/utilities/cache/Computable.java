package org.glassfish.hk2.utilities.cache;

public interface Computable {
   Object compute(Object var1) throws ComputationErrorException;
}
