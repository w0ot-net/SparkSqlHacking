package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface DoubleFunction extends Serializable {
   double call(Object var1) throws Exception;
}
