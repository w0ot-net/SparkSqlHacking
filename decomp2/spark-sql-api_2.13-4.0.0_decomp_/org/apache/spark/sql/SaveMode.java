package org.apache.spark.sql;

import org.apache.spark.annotation.Stable;

@Stable
public enum SaveMode {
   Append,
   Overwrite,
   ErrorIfExists,
   Ignore;

   // $FF: synthetic method
   private static SaveMode[] $values() {
      return new SaveMode[]{Append, Overwrite, ErrorIfExists, Ignore};
   }
}
