package org.jvnet.hk2.internal;

import java.util.LinkedList;
import java.util.List;
import org.glassfish.hk2.api.ActiveDescriptor;

public class ImmediateResults {
   private final NarrowResults timelessResults;
   private final List validatedImmediateResults = new LinkedList();

   ImmediateResults(NarrowResults cachedResults) {
      if (cachedResults == null) {
         this.timelessResults = new NarrowResults();
      } else {
         this.timelessResults = cachedResults;
      }

   }

   NarrowResults getTimelessResults() {
      return this.timelessResults;
   }

   List getImmediateResults() {
      return this.validatedImmediateResults;
   }

   void addValidatedResult(ActiveDescriptor addMe) {
      this.validatedImmediateResults.add(addMe);
   }

   public String toString() {
      NarrowResults var10000 = this.timelessResults;
      return "ImmediateResults(" + var10000 + "," + this.validatedImmediateResults + "," + System.identityHashCode(this) + ")";
   }
}
