package org.apache.spark.sql.api.java;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;

@Stable
public interface UDF4 extends Serializable {
   Object call(Object var1, Object var2, Object var3, Object var4) throws Exception;
}
