package org.apache.spark.api.java.function;

import java.io.Serializable;
import scala.Tuple2;

@FunctionalInterface
public interface PairFunction extends Serializable {
   Tuple2 call(Object var1) throws Exception;
}
