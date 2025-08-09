package org.apache.spark.util;

import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.util.ClosureCleaner.;
import scala.collection.mutable.Map;

public final class SparkClosureCleaner$ {
   public static final SparkClosureCleaner$ MODULE$ = new SparkClosureCleaner$();

   public void clean(final Object closure, final boolean checkSerializable, final boolean cleanTransitively) {
      if (.MODULE$.clean(closure, cleanTransitively, (Map)scala.collection.mutable.Map..MODULE$.empty())) {
         try {
            if (checkSerializable && SparkEnv$.MODULE$.get() != null) {
               SparkEnv$.MODULE$.get().closureSerializer().newInstance().serialize(closure, scala.reflect.ClassTag..MODULE$.AnyRef());
            }

         } catch (Exception var5) {
            throw new SparkException("Task not serializable", var5);
         }
      }
   }

   public boolean clean$default$2() {
      return true;
   }

   public boolean clean$default$3() {
      return true;
   }

   private SparkClosureCleaner$() {
   }
}
