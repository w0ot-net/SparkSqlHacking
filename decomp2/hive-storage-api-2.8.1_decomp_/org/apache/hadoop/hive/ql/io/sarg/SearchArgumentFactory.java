package org.apache.hadoop.hive.ql.io.sarg;

import org.apache.hadoop.conf.Configuration;

public class SearchArgumentFactory {
   public static SearchArgument.Builder newBuilder() {
      return newBuilder((Configuration)null);
   }

   public static SearchArgument.Builder newBuilder(Configuration conf) {
      return new SearchArgumentImpl.BuilderImpl(conf);
   }

   public static void setPredicateLeafColumn(PredicateLeaf leaf, String newName) {
      SearchArgumentImpl.PredicateLeafImpl.setColumnName(leaf, newName);
   }
}
