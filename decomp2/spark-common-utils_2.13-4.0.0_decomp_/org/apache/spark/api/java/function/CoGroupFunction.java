package org.apache.spark.api.java.function;

import java.io.Serializable;
import java.util.Iterator;

@FunctionalInterface
public interface CoGroupFunction extends Serializable {
   Iterator call(Object var1, Iterator var2, Iterator var3) throws Exception;
}
