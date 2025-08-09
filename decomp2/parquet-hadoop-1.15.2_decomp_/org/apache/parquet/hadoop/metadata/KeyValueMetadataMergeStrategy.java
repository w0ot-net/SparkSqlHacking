package org.apache.parquet.hadoop.metadata;

import java.io.Serializable;
import java.util.Map;

public interface KeyValueMetadataMergeStrategy extends Serializable {
   Map merge(Map var1);
}
