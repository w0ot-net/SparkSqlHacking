package org.apache.spark.sql.api.java;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;

@Stable
public interface UDF2 extends Serializable {
   Object call(Object var1, Object var2) throws Exception;
}
