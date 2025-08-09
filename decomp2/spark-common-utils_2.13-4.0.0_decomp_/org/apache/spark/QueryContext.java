package org.apache.spark;

import org.apache.spark.annotation.Evolving;

@Evolving
public interface QueryContext {
   QueryContextType contextType();

   String objectType();

   String objectName();

   int startIndex();

   int stopIndex();

   String fragment();

   String callSite();

   String summary();
}
