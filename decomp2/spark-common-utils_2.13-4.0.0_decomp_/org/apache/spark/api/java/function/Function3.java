package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface Function3 extends Serializable {
   Object call(Object var1, Object var2, Object var3) throws Exception;
}
