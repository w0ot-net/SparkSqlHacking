package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface VoidFunction extends Serializable {
   void call(Object var1) throws Exception;
}
