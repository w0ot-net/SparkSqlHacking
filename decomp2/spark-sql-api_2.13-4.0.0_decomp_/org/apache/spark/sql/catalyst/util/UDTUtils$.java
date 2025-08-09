package org.apache.spark.sql.catalyst.util;

import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.util.SparkClassUtils.;
import scala.Option;

public final class UDTUtils$ implements UDTUtils {
   public static final UDTUtils$ MODULE$ = new UDTUtils$();
   private static final UDTUtils delegate = liftedTree1$1();

   private UDTUtils delegate() {
      return delegate;
   }

   public Object toRow(final Object value, final UserDefinedType udt) {
      return this.delegate().toRow(value, udt);
   }

   // $FF: synthetic method
   private static final UDTUtils liftedTree1$1() {
      Object var10000;
      try {
         Class cls = .MODULE$.classForName("org.apache.spark.sql.catalyst.util.UDTUtilsImpl", .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3());
         var10000 = (UDTUtils)cls.getConstructor().newInstance();
      } catch (Throwable var5) {
         if (var5 != null) {
            Option var4 = scala.util.control.NonFatal..MODULE$.unapply(var5);
            if (!var4.isEmpty()) {
               var10000 = DefaultUDTUtils$.MODULE$;
               return (UDTUtils)var10000;
            }
         }

         throw var5;
      }

      return (UDTUtils)var10000;
   }

   private UDTUtils$() {
   }
}
