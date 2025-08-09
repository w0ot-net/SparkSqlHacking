package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface Function extends Serializable {
   Object call(Object var1) throws Exception;
}
