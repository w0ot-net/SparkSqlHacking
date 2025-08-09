package org.jvnet.hk2.internal;

import java.util.LinkedList;
import java.util.List;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.MultiException;

public class NarrowResults {
   private List unnarrowedResults;
   private final List goodResults = new LinkedList();
   private final List errors = new LinkedList();

   void addGoodResult(ActiveDescriptor result) {
      this.goodResults.add(result);
   }

   void addError(ActiveDescriptor fail, Injectee injectee, MultiException me) {
      this.errors.add(new ErrorResults(fail, injectee, me));
   }

   List getResults() {
      return this.goodResults;
   }

   List getErrors() {
      return this.errors;
   }

   void setUnnarrowedResults(List unnarrowed) {
      this.unnarrowedResults = unnarrowed;
   }

   ActiveDescriptor removeUnnarrowedResult() {
      return this.unnarrowedResults != null && !this.unnarrowedResults.isEmpty() ? (ActiveDescriptor)this.unnarrowedResults.remove(0) : null;
   }

   public String toString() {
      int var10000 = this.goodResults.size();
      return "NarrowResults(goodResultsSize=" + var10000 + ",errorsSize=" + this.errors.size() + "," + System.identityHashCode(this) + ")";
   }
}
