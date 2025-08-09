package org.apache.spark;

import org.apache.spark.annotation.Evolving;

@Evolving
public enum QueryContextType {
   SQL,
   DataFrame;

   // $FF: synthetic method
   private static QueryContextType[] $values() {
      return new QueryContextType[]{SQL, DataFrame};
   }
}
