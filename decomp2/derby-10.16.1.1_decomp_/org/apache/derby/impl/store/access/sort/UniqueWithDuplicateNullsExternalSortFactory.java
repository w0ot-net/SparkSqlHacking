package org.apache.derby.impl.store.access.sort;

public class UniqueWithDuplicateNullsExternalSortFactory extends ExternalSortFactory {
   private static final String IMPLEMENTATIONID = "sort almost unique external";

   protected MergeSort getMergeSort() {
      return new UniqueWithDuplicateNullsMergeSort();
   }

   public String primaryImplementationType() {
      return "sort almost unique external";
   }

   public boolean supportsImplementation(String var1) {
      return "sort almost unique external".equals(var1);
   }
}
