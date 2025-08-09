package org.apache.spark.api.java.function;

import java.io.Serializable;

@FunctionalInterface
public interface Function0 extends Serializable {
   Object call() throws Exception;
}
