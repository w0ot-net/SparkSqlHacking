package org.apache.spark.sql.catalyst.trees;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;

public final class PySparkCurrentOrigin$ {
   public static final PySparkCurrentOrigin$ MODULE$ = new PySparkCurrentOrigin$();
   private static final ThreadLocal pysparkErrorContext = new ThreadLocal() {
      public Option initialValue() {
         return .MODULE$;
      }
   };

   private ThreadLocal pysparkErrorContext() {
      return pysparkErrorContext;
   }

   public void set(final String fragment, final String callSite) {
      this.pysparkErrorContext().set(new Some(new Tuple2(fragment, callSite)));
   }

   public Option get() {
      return (Option)this.pysparkErrorContext().get();
   }

   public void clear() {
      this.pysparkErrorContext().remove();
   }

   private PySparkCurrentOrigin$() {
   }
}
