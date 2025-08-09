package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface FloatComparator extends Comparator {
   int compare(float var1, float var2);

   default FloatComparator reversed() {
      return FloatComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Float ok1, Float ok2) {
      return this.compare(ok1, ok2);
   }

   default FloatComparator thenComparing(FloatComparator second) {
      return (FloatComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof FloatComparator ? this.thenComparing((FloatComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$99a1156d$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/floats/FloatComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(FF)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/floats/FloatComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/floats/FloatComparator;FF)I")) {
               FloatComparator var10000 = (FloatComparator)lambda.getCapturedArg(0);
               return (FloatComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
