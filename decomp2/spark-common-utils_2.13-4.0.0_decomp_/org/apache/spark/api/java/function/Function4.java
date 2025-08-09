package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface Function4 extends Serializable {
   Object call(Object var1, Object var2, Object var3, Object var4) throws Exception;
}
