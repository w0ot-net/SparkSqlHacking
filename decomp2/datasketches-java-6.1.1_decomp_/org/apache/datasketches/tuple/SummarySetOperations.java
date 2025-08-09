package org.apache.datasketches.tuple;

public interface SummarySetOperations {
   Summary union(Summary var1, Summary var2);

   Summary intersection(Summary var1, Summary var2);
}
