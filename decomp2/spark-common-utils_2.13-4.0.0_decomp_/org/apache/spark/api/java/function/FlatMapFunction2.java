package org.apache.spark.api.java.function;

import java.io.Serializable;
import java.util.Iterator;

@FunctionalInterface
public interface FlatMapFunction2 extends Serializable {
   Iterator call(Object var1, Object var2) throws Exception;
}
