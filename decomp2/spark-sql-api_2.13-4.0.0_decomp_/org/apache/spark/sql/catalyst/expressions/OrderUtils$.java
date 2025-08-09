package org.apache.spark.sql.catalyst.expressions;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.AtomicType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.NullType$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.sql.types.VariantType$;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;

public final class OrderUtils$ {
   public static final OrderUtils$ MODULE$ = new OrderUtils$();

   public boolean isOrderable(final DataType dataType) {
      while(!NullType$.MODULE$.equals(dataType)) {
         if (VariantType$.MODULE$.equals(dataType)) {
            return false;
         }

         if (dataType instanceof AtomicType) {
            return true;
         }

         if (dataType instanceof StructType var5) {
            return .MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps(var5.fields()), (f) -> BoxesRunTime.boxToBoolean($anonfun$isOrderable$1(f)));
         }

         if (dataType instanceof ArrayType var6) {
            dataType = var6.elementType();
         } else {
            if (!(dataType instanceof UserDefinedType)) {
               return false;
            }

            UserDefinedType var7 = (UserDefinedType)dataType;
            dataType = var7.sqlType();
         }
      }

      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isOrderable$1(final StructField f) {
      return MODULE$.isOrderable(f.dataType());
   }

   private OrderUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
