package org.apache.parquet.hadoop.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ConcatenatingKeyValueMetadataMergeStrategy implements KeyValueMetadataMergeStrategy {
   private static final String DEFAULT_DELIMITER = ",";
   private final String delimiter;

   public ConcatenatingKeyValueMetadataMergeStrategy() {
      this.delimiter = ",";
   }

   public ConcatenatingKeyValueMetadataMergeStrategy(String delim) {
      this.delimiter = delim;
   }

   public Map merge(Map keyValueMetaData) {
      Map<String, String> mergedKeyValues = new HashMap();

      for(Map.Entry entry : keyValueMetaData.entrySet()) {
         mergedKeyValues.put(entry.getKey(), ((Set)entry.getValue()).stream().collect(Collectors.joining(this.delimiter)));
      }

      return mergedKeyValues;
   }
}
