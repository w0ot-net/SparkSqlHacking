package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface Function2 extends Serializable {
   Object call(Object var1, Object var2) throws Exception;
}
