package org.apache.spark.api.java.function;

import java.io.Serializable;
import java.util.Iterator;

@FunctionalInterface
public interface MapPartitionsFunction extends Serializable {
   Iterator call(Iterator var1) throws Exception;
}
