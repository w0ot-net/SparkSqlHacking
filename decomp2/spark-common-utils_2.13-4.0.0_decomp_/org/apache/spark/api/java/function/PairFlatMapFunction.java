package org.apache.spark.api.java.function;

import java.io.Serializable;
import java.util.Iterator;

@FunctionalInterface
public interface PairFlatMapFunction extends Serializable {
   Iterator call(Object var1) throws Exception;
}
