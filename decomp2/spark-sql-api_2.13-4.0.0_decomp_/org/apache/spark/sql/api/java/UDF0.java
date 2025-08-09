package org.apache.spark.sql.api.java;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;

@Stable
public interface UDF0 extends Serializable {
   Object call() throws Exception;
}
