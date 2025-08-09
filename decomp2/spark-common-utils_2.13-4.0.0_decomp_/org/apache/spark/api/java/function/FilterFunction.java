package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface FilterFunction extends Serializable {
   boolean call(Object var1) throws Exception;
}
