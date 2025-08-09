package org.apache.spark.sql.api.java;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;

@Stable
public interface UDF3 extends Serializable {
   Object call(Object var1, Object var2, Object var3) throws Exception;
}
