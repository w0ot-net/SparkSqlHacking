package org.apache.parquet.hadoop.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrictKeyValueMetadataMergeStrategy implements KeyValueMetadataMergeStrategy {
   public Map merge(Map keyValueMetaData) {
      Map<String, String> mergedKeyValues = new HashMap();

      for(Map.Entry entry : keyValueMetaData.entrySet()) {
         if (((Set)entry.getValue()).size() > 1) {
            throw new RuntimeException("could not merge metadata: key " + (String)entry.getKey() + " has conflicting values: " + entry.getValue());
         }

         mergedKeyValues.put(entry.getKey(), ((Set)entry.getValue()).iterator().next());
      }

      return mergedKeyValues;
   }
}
