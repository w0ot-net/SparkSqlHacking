package org.apache.spark.ml.util;

import org.apache.spark.util.Utils.;
import scala.Function1;

public final class MLAllowListedLoader$ {
   public static final MLAllowListedLoader$ MODULE$ = new MLAllowListedLoader$();
   private static final Function1 safeMLClassLoader = liftedTree1$1();

   public Function1 safeMLClassLoader() {
      return safeMLClassLoader;
   }

   public Class load(final String className) {
      return this.safeMLClassLoader() == null ? .MODULE$.classForName(className, .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3()) : (Class)this.safeMLClassLoader().apply(className);
   }

   // $FF: synthetic method
   private static final Function1 liftedTree1$1() {
      Function1 var10000;
      try {
         Class MLHandlerClazz = .MODULE$.classForName("org.apache.spark.sql.connect.ml.MLHandler", .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3());
         var10000 = (Function1)MLHandlerClazz.getMethod("safeMLClassLoader").invoke((Object)null);
      } catch (ClassNotFoundException var1) {
         var10000 = null;
      }

      return var10000;
   }

   private MLAllowListedLoader$() {
   }
}
