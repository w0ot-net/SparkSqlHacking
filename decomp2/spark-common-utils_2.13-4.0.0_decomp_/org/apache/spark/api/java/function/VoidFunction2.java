package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface VoidFunction2 extends Serializable {
   void call(Object var1, Object var2) throws Exception;
}
