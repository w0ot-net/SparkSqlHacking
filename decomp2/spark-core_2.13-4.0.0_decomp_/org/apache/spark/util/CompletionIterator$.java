package org.apache.spark.util;

import scala.Function0;
import scala.collection.Iterator;

public final class CompletionIterator$ {
   public static final CompletionIterator$ MODULE$ = new CompletionIterator$();

   public CompletionIterator apply(final Iterator sub, final Function0 completionFunction) {
      return new CompletionIterator(sub, completionFunction) {
         private final Function0 completionFunction$1;

         public void completion() {
            this.completionFunction$1.apply$mcV$sp();
         }

         public {
            this.completionFunction$1 = completionFunction$1;
         }
      };
   }

   private CompletionIterator$() {
   }
}
